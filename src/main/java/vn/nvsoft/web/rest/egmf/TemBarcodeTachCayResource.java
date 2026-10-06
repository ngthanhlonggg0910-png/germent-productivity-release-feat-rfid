package vn.nvsoft.web.rest.egmf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nvsoft.service.dto.PoMaHangDTO;
import vn.nvsoft.service.dto.TemBarcodeTachCayDTO;
import vn.nvsoft.service.egmf.DecathlonRfidItemValidator;
import vn.nvsoft.service.egmf.TemBarcodeTachCayService;
import vn.nvsoft.service.egmf.WorkShiftGuard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller để quản lý barcode mã cây và mapping RFID
 * Dữ liệu từ database eGMF
 */
@RestController
@RequestMapping("/api/egmf")
public class TemBarcodeTachCayResource {

    private final Logger log = LoggerFactory.getLogger(TemBarcodeTachCayResource.class);

    private final TemBarcodeTachCayService temBarcodeTachCayService;

    public TemBarcodeTachCayResource(TemBarcodeTachCayService temBarcodeTachCayService) {
        this.temBarcodeTachCayService = temBarcodeTachCayService;
    }

    /**
     * GET /api/egmf/tem-barcode-tach-cay/{code} : Lấy thông tin barcode mã cây
     * GET /api/egmf/tem-barcode-tach-cay/{code}?po={po} : Lấy thông tin barcode mã cây và kiểm tra PO
     *
     * Client gửi request với mã cây (bắt đầu bằng "BTP")
     * Nếu có tham số PO, server sẽ kiểm tra mã cây có khớp với PO không (join qua CUTTING_PhieuCapBTP_BarcodeChiTiet)
     * Server kiểm tra và trả về thông tin bao gồm:
     * - Thông tin chi tiết mã cây
     * - Số lượng RFID giới hạn (SoLopTrai)
     * - Số lượng RFID đã mapping
     * - Số lượng còn lại có thể mapping
     *
     * @param code Mã cây (cột Code trong CUTTING_TemBarcode_TachCay)
     * @param po (Optional) Mã PO để kiểm tra khớp với mã cây
     * @return ResponseEntity với TemBarcodeTachCayDTO hoặc error response
     */
    @GetMapping("/tem-barcode-tach-cay/{code}")
    public ResponseEntity<?> getByCode(
            @PathVariable String code,
            @RequestParam(required = false) String po,
            @RequestParam(required = false) String mahang) {

        log.info("========== API: GET /api/egmf/tem-barcode-tach-cay/{} ==========", code);
        log.info("Request Parameters - Code: {}, PO: {}, MaHang: {}", code, po, mahang);

        try {
            Optional<TemBarcodeTachCayDTO> result;

            // Nếu có PO, kiểm tra mã cây có khớp với PO (và mã hàng) không
            if (po != null && !po.trim().isEmpty()) {
                log.info("Action: Lấy thông tin mã cây và kiểm tra PO");
                result = temBarcodeTachCayService.getByCodeAndPo(code, po, mahang);

                if (!result.isPresent()) {
                    Map<String, Object> errorResponse = new HashMap<>();
                    String mahangTrimmed = mahang != null ? mahang.trim() : "";
                    boolean hasMahangFilter = !mahangTrimmed.isEmpty();

                    // PO khớp nhưng mã hàng (ProductCode) không khớp — join CUTTING_PhieuCapBTP
                    if (hasMahangFilter) {
                        Optional<TemBarcodeTachCayDTO> poOnly =
                            temBarcodeTachCayService.getByCodeAndPo(code, po, null);
                        if (poOnly.isPresent()) {
                            errorResponse.put("success", false);
                            errorResponse.put("exists", true);
                            errorResponse.put("poMatched", true);
                            errorResponse.put("mahangMatched", false);
                            errorResponse.put("message",
                                "Mã cây " + code + " không thuộc mã hàng " + mahangTrimmed + " của PO " + po);
                            errorResponse.put("requestedMahang", mahangTrimmed);
                            errorResponse.put("actualProductCode", poOnly.get().getProductCode());
                            log.warn("Mã cây {} khớp PO {} nhưng không khớp mã hàng {}. ProductCode thực tế: {}",
                                code, po, mahangTrimmed, poOnly.get().getProductCode());
                            log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (MAHANG_MISMATCH) ==========", code);
                            return ResponseEntity.status(400).body(errorResponse);
                        }
                    }

                    // Kiểm tra xem mã cây có tồn tại không (không quan tâm PO)
                    Optional<TemBarcodeTachCayDTO> codeOnly = temBarcodeTachCayService.getByCode(code);

                    if (codeOnly.isPresent()) {
                        // Mã cây tồn tại nhưng PO không khớp
                        errorResponse.put("success", false);
                        errorResponse.put("exists", true);
                        errorResponse.put("poMatched", false);
                        errorResponse.put("mahangMatched", hasMahangFilter ? false : null);
                        errorResponse.put("message", "Mã cây " + code + " không thuộc PO " + po);
                        errorResponse.put("actualPo", codeOnly.get().getPo());
                        errorResponse.put("requestedPo", po);
                        if (hasMahangFilter) {
                            errorResponse.put("requestedMahang", mahangTrimmed);
                        }
                        log.warn("Mã cây {} tồn tại nhưng không khớp với PO {}. PO thực tế: {}",
                            code, po, codeOnly.get().getPo());
                        log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (PO_MISMATCH) ==========", code);
                        return ResponseEntity.status(400).body(errorResponse);
                    } else {
                        // Mã cây không tồn tại
                        errorResponse.put("success", false);
                        errorResponse.put("exists", false);
                        errorResponse.put("poMatched", false);
                        errorResponse.put("message", "Không tìm thấy mã cây " + code);
                        log.warn("Không tìm thấy mã cây {}", code);
                        log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (NOT_FOUND) ==========", code);
                        return ResponseEntity.status(404).body(errorResponse);
                    }
                }

                // Mã cây tồn tại và PO khớp - Trả về DTO trực tiếp (giống format cũ)
                log.info("Mã cây {} khớp với PO {}", code, po);
                log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (SUCCESS) ==========", code);
                return ResponseEntity.ok(result.get());

            } else {
                // Không có PO, chỉ lấy thông tin mã cây - Trả về DTO trực tiếp (giữ nguyên format cũ)
                log.info("Action: Lấy thông tin mã cây (không kiểm tra PO)");
                result = temBarcodeTachCayService.getByCode(code);

                if (!result.isPresent()) {
                    log.warn("Không tìm thấy mã cây {}", code);
                    log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (NOT_FOUND) ==========", code);
                    return ResponseEntity.notFound().build();
                }

                log.info("Lấy thông tin mã cây {} thành công", code);
                log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (SUCCESS) ==========", code);
                return ResponseEntity.ok(result.get());
            }

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Lỗi hệ thống: " + e.getMessage());
            log.error("Error getting TemBarcodeTachCay - Code: {}, PO: {}, Error: {}",
                code, po, e.getMessage(), e);
            log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{} (ERROR) ==========", code);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    /**
     * GET /api/egmf/tem-barcode-tach-cay/{code}/po-list : Danh sách PO (distinct) kèm mã hàng theo mã cây
     *
     * @param code Mã cây (cột Code trong CUTTING_TemBarcode_TachCay, ví dụ BTPxxx-xx)
     * @return 200 và danh sách (PO, ProductCode); có thể rỗng nếu không có dòng chi tiết phiếu cấp BTP tương ứng
     */
    @GetMapping("/tem-barcode-tach-cay/{code}/po-list")
    public ResponseEntity<List<PoMaHangDTO>> getPoListByCode(@PathVariable String code) {
        log.info("========== API: GET /api/egmf/tem-barcode-tach-cay/{}/po-list ==========", code);
        List<PoMaHangDTO> pos = temBarcodeTachCayService.getDistinctPosByCode(code);
        log.info("Response - code: {}, PO count: {}", code, pos != null ? pos.size() : 0);
        log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/{}/po-list ==========", code);
        return ResponseEntity.ok(pos);
    }

    /**
     * GET /api/egmf/tem-barcode-tach-cay/{code}/can-map : Kiểm tra có thể mapping thêm RFID không
     *
     * @param code Mã cây
     * @param khsxId (Optional) Nếu có thì kiểm tra thêm trần TongSLThucNhan (nhận tem BTP) theo mã cây + KHSX
     * @return ResponseEntity với thông tin có thể mapping hay không
     */
    @GetMapping("/tem-barcode-tach-cay/{code}/can-map")
    public ResponseEntity<Map<String, Object>> canMapMoreRfid(
            @PathVariable String code,
            @RequestParam(required = false) Long khsxId,
            @RequestParam(required = false) Long proCheckPointId,
            @RequestParam(required = false) Long nsclId) {
        log.debug("REST request to check if can map more RFID for code: {}, khsxId: {}, proCheckPointId: {}", code, khsxId, proCheckPointId);

        Map<String, Object> response = new HashMap<>();

        // Kiểm tra mã cây có tồn tại không
        if (!temBarcodeTachCayService.existsByCode(code)) {
            response.put("exists", false);
            response.put("canMap", false);
            response.put("message", "Mã cây không tồn tại");
            return ResponseEntity.ok(response);
        }

        Optional<TemBarcodeTachCayDTO> dto = temBarcodeTachCayService.getByCode(code);
        if (!dto.isPresent()) {
            response.put("exists", false);
            response.put("canMap", false);
            response.put("message", "Không thể lấy thông tin mã cây");
            return ResponseEntity.ok(response);
        }

        TemBarcodeTachCayDTO data = dto.get();

        if (!temBarcodeTachCayService.isWithinWorkShiftForMapping(proCheckPointId, nsclId)) {
            response.put("exists", true);
            response.put("canMap", false);
            response.put("soLopTrai", data.getSoLopTrai());
            response.put("soLuongDaMap", data.getSoLuongDaMap());
            response.put("soLuongConLai", data.getSoLuongConLai());
            response.put("errorCode", TemBarcodeTachCayService.ERROR_NGOAI_CA_LAM_VIEC);
            response.put("message", WorkShiftGuard.MESSAGE_NGOAI_CA);
            return ResponseEntity.ok(response);
        }

        boolean canMap = khsxId != null
            ? temBarcodeTachCayService.canMapMoreRfid(code, khsxId)
            : temBarcodeTachCayService.canMapMoreRfid(code);

        response.put("exists", true);
        response.put("canMap", canMap);
        response.put("soLopTrai", data.getSoLopTrai());
        response.put("soLuongDaMap", data.getSoLuongDaMap());
        response.put("soLuongConLai", data.getSoLuongConLai());

        if (khsxId != null) {
            Integer quantityLimitBtp = temBarcodeTachCayService.getQuantityLimitBtp(code, khsxId);
            Integer quantityLimitParentBtp = temBarcodeTachCayService.getQuantityLimitParentBtp(code, khsxId);
            Integer soLuongDaMapMaCay = temBarcodeTachCayService.countMappedByCodeAndKhsx(code, khsxId);
            Integer soLuongDaMapTongBtp = temBarcodeTachCayService.countMappedByParentBarcode(code, khsxId);
            response.put("khsxId", khsxId);
            response.put("quantityLimitBtp", quantityLimitBtp);
            response.put("tongSlThucNhan", quantityLimitBtp);
            response.put("quantityLimitParentBtp", quantityLimitParentBtp);
            response.put("tongSlThucNhanTongBtp", quantityLimitParentBtp);
            response.put("soLuongDaMapKhsx", soLuongDaMapMaCay);
            response.put("soLuongDaMapMaCay", soLuongDaMapMaCay);
            response.put("soLuongDaMapTongBtp", soLuongDaMapTongBtp);
            if (quantityLimitBtp != null && quantityLimitBtp > 0) {
                response.put("soLuongConLaiBtp", Math.max(0, quantityLimitBtp - soLuongDaMapMaCay));
            } else {
                response.put("soLuongConLaiBtp", 0);
            }
            if (quantityLimitParentBtp != null && quantityLimitParentBtp > 0) {
                response.put("soLuongConLaiTongBtp", Math.max(0, quantityLimitParentBtp - soLuongDaMapTongBtp));
            } else {
                response.put("soLuongConLaiTongBtp", 0);
            }
        }

        if (!canMap) {
            if (khsxId != null) {
                String errorCode = temBarcodeTachCayService.resolveCannotMapErrorCode(code, khsxId);
                response.put("errorCode", errorCode);
                if (TemBarcodeTachCayService.ERROR_BTP_CHUA_QUET.equals(errorCode)) {
                    response.put("message", "BTP chưa được quét nhận hoặc số lượng nhận BTP bằng 0, không thể mapping RFID");
                } else if (TemBarcodeTachCayService.ERROR_VUOT_TONG_BTP.equals(errorCode)) {
                    response.put("message", "Số lượng mapping RFID vượt quá số lượng nhận tem tổng BTP");
                } else if (TemBarcodeTachCayService.ERROR_VUOT_SUMMARY_PO.equals(errorCode)) {
                    response.put("message", "Số lượng mapping RFID vượt quá số lượng nhận tem BTP của mã cây");
                } else {
                    response.put("message", "Đã đạt giới hạn số lượng RFID cho mã cây này");
                }
            } else {
                response.put("message", "Đã đạt giới hạn số lượng RFID cho mã cây này");
            }
        } else {
            response.put("message", "Có thể mapping thêm " + data.getSoLuongConLai() + " RFID");
        }

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/egmf/tem-barcode-tach-cay/check-rfid/{rfid} : Kiểm tra RFID đã được mapping chưa
     *
     * Response:
     * - HTTP 200 + body: RFID đã được mapping, trả về thông tin mã cây
     * - HTTP 204 No Content: RFID chưa được mapping
     *
     * @param rfid RFID cần kiểm tra (GS1 format đã decode)
     * @return ResponseEntity với thông tin mã cây đã mapping (nếu có)
     */
    @GetMapping("/tem-barcode-tach-cay/check-rfid/{rfid}")
    public ResponseEntity<Map<String, Object>> checkRfidMapped(@PathVariable String rfid) {
        log.info("========== API: GET /api/egmf/tem-barcode-tach-cay/check-rfid/{} ==========", rfid);
        log.info("Request Parameters - RFID: {}", rfid);
        log.info("Action: Kiểm tra RFID đã được mapping với mã cây chưa");

        try {
            // Lấy mã cây đã mapping với RFID (nếu có)
            String barcodeTachCay = temBarcodeTachCayService.getBarcodeTachCayByRfid(rfid);

            if (barcodeTachCay != null && !barcodeTachCay.trim().isEmpty()) {
                // RFID đã được mapping -> Trả về HTTP 200 + thông tin mã cây
                Map<String, Object> response = new HashMap<>();
                response.put("rfid", rfid);
                response.put("barcodeTachCay", barcodeTachCay);

                log.info("RFID {} đã được mapping với mã cây: {}", rfid, barcodeTachCay);
                log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/check-rfid/{} (MAPPED) ==========", rfid);
                return ResponseEntity.ok(response);
            } else {
                // RFID chưa được mapping -> Trả về HTTP 204 No Content
                log.info("RFID {} chưa được mapping", rfid);
                log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/check-rfid/{} (NOT_MAPPED) ==========", rfid);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error("Error checking RFID mapping - RFID: {}, Error: {}", rfid, e.getMessage(), e);
            log.info("========== End API: GET /api/egmf/tem-barcode-tach-cay/check-rfid/{} (ERROR) ==========", rfid);

            // Trả về HTTP 500 với thông báo lỗi
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("rfid", rfid);
            errorResponse.put("isMapped", false);
            errorResponse.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    /**
     * POST /api/egmf/tem-barcode-tach-cay/mapping : Thực hiện mapping RFID với barcode mã cây
     *
     * Kiểm tra:
     * - khsxId bắt buộc
     * - Mã cây có tồn tại không
     * - Số lượng mapping đã đạt giới hạn chưa (soLuongDaMap < soLopTrai)
     * - Tổng mapping theo mã cây chưa vượt TongSLThucNhan (nhận tem BTP theo Code + KHSX)
     * - RFID đã được mapping với mã cây khác chưa
     *
     * Nếu pass tất cả validation, thực hiện insert vào bảng CUTTING_TemBarcode_TachCay_RFID_Mapping
     *
     * @param mappingRequest Request body chứa thông tin mapping
     * @return ResponseEntity với kết quả mapping
     */
    @PostMapping("/tem-barcode-tach-cay/mapping")
    public ResponseEntity<Map<String, Object>> mapRfidToBarcode(@RequestBody Map<String, Object> mappingRequest) {
        log.info("========== API: POST /api/egmf/tem-barcode-tach-cay/mapping ==========");
        log.info("Request Body: {}", mappingRequest);

        String rfidBarcode = (String) mappingRequest.get("rfidBarcode");
        String barcodeTachCay = (String) mappingRequest.get("barcodeTachCay");
        String rfid = (String) mappingRequest.get("rfid");
        String rfidHex = (String) mappingRequest.get("rfidHex");
        String nguoiMap = (String) mappingRequest.get("nguoiMap");
        Object tkIdObj = mappingRequest.get("tkId");
        Long tkId = tkIdObj != null ? Long.valueOf(tkIdObj.toString()) : null;
        String codeRfid = (String) mappingRequest.get("codeRfid");
        String codeRfidHex = (String) mappingRequest.get("codeRfidHex");
        Object khsxIdObj = mappingRequest.get("khsxId");
        Long khsxId = khsxIdObj != null ? Long.valueOf(khsxIdObj.toString()) : null;
        Object nsclIdObj = mappingRequest.get("nsclId");
        Long nsclId = nsclIdObj != null ? Long.valueOf(nsclIdObj.toString()) : null;
        Object proCheckPointIdObj = mappingRequest.get("proCheckPointId");
        Long proCheckPointId = proCheckPointIdObj != null ? Long.valueOf(proCheckPointIdObj.toString()) : null;
        String po = (String) mappingRequest.get("po");
        String productcode = (String) mappingRequest.get("productcode");

        log.info("Mapping parameters - RFID_Barcode: {}, BarcodeTachCay: {}, RFID: {}, NguoiMap: {}, TkId: {}, KhsxId: {}, NsclId: {}, ProCheckPointId: {}, PO: {}, ProductCode: {}",
            rfidBarcode, barcodeTachCay, rfid, nguoiMap, tkId, khsxId, nsclId, proCheckPointId, po, productcode);

        Map<String, Object> response = new HashMap<>();

        try {
            // Validate input
            if (barcodeTachCay == null || barcodeTachCay.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Mã cây không được để trống");
                log.warn("Validation failed: barcodeTachCay is empty");
                return ResponseEntity.badRequest().body(response);
            }

            if (rfid == null || rfid.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "RFID không được để trống");
                log.warn("Validation failed: rfid is empty");
                return ResponseEntity.badRequest().body(response);
            }

            if (khsxId == null) {
                response.put("success", false);
                response.put("message", "khsxId không được để trống khi mapping RFID");
                response.put("errorCode", TemBarcodeTachCayService.ERROR_KHSX_REQUIRED);
                log.warn("Validation failed: khsxId is empty");
                return ResponseEntity.badRequest().body(response);
            }

            try {
                temBarcodeTachCayService.validateWorkShiftForMapping(proCheckPointId, nsclId);
                temBarcodeTachCayService.validateRfidForMapping(barcodeTachCay, rfid, po);
            } catch (IllegalStateException e) {
                response.put("success", false);
                response.put("message", e.getMessage());
                if (WorkShiftGuard.MESSAGE_NGOAI_CA.equals(e.getMessage())) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_NGOAI_CA_LAM_VIEC);
                } else {
                    String decathlonErrorCode = DecathlonRfidItemValidator.resolveErrorCode(e.getMessage());
                    if (decathlonErrorCode != null) {
                        response.put("errorCode", decathlonErrorCode);
                    } else {
                        response.put("errorCode", TemBarcodeTachCayService.ERROR_RFID_INVALID_FORMAT);
                    }
                }
                log.warn("Validation failed: RFID - barcodeTachCay={}, rfid={}, error={}", barcodeTachCay, rfid, e.getMessage());
                return ResponseEntity.badRequest().body(response);
            }

            // Kiểm tra mã cây có tồn tại không
            if (!temBarcodeTachCayService.existsByCode(barcodeTachCay)) {
                response.put("success", false);
                response.put("message", "Không tìm thấy mã cây: " + barcodeTachCay);
                log.warn("Barcode not found: {}", barcodeTachCay);
                return ResponseEntity.status(404).body(response);
            }

            // Kiểm tra có thể mapping thêm không (SoLopTrai + TongSLThucNhan)
            if (!temBarcodeTachCayService.canMapMoreRfid(barcodeTachCay, khsxId)) {
                Optional<TemBarcodeTachCayDTO> dto = temBarcodeTachCayService.getByCode(barcodeTachCay);
                String errorCode = temBarcodeTachCayService.resolveCannotMapErrorCode(barcodeTachCay, khsxId);
                String message;
                if (TemBarcodeTachCayService.ERROR_BTP_CHUA_QUET.equals(errorCode)) {
                    message = "BTP chưa được quét nhận hoặc số lượng nhận BTP bằng 0, không thể mapping RFID";
                } else if (TemBarcodeTachCayService.ERROR_VUOT_TONG_BTP.equals(errorCode)) {
                    message = "Số lượng mapping RFID vượt quá số lượng nhận tem tổng BTP";
                } else if (TemBarcodeTachCayService.ERROR_VUOT_SUMMARY_PO.equals(errorCode)) {
                    message = "Số lượng mapping RFID vượt quá số lượng nhận tem BTP của mã cây";
                } else {
                    message = "Mã cây " + barcodeTachCay + " đã đạt giới hạn mapping";
                    if (dto.isPresent()) {
                        message += " (" + dto.get().getSoLuongDaMap() + "/" + dto.get().getSoLopTrai() + ")";
                    }
                    errorCode = TemBarcodeTachCayService.ERROR_SLOT_MACAY_FULL;
                }
                response.put("success", false);
                response.put("message", message);
                response.put("errorCode", errorCode);
                log.warn("Mapping blocked - barcode: {}, khsxId: {}, errorCode: {}", barcodeTachCay, khsxId, errorCode);
                return ResponseEntity.badRequest().body(response);
            }

            // Kiểm tra RFID đã được mapping chưa
            if (temBarcodeTachCayService.isRfidAlreadyMapped(rfid)) {
                response.put("success", false);
                response.put("message", "RFID " + rfid + " đã được mapping với mã cây khác");
                response.put("errorCode", "DUPLICATE_RFID");
                log.warn("RFID already mapped: {}", rfid);
                return ResponseEntity.status(409).body(response);
            }

            // Thực hiện mapping
            try {
                temBarcodeTachCayService.mapRfidToBarcode(
                    rfidBarcode, barcodeTachCay, rfid, rfidHex, nguoiMap, tkId, codeRfid, codeRfidHex,
                    khsxId, nsclId, proCheckPointId, po, productcode
                );

                Optional<TemBarcodeTachCayDTO> updatedDto = temBarcodeTachCayService.getByCode(barcodeTachCay);

                response.put("success", true);
                response.put("message", "Mapping thành công");
                response.put("rfidBarcode", rfidBarcode);
                response.put("barcodeTachCay", barcodeTachCay);
                response.put("rfid", rfid);
                response.put("nguoiMap", nguoiMap);
                response.put("khsxId", khsxId);
                response.put("nsclId", nsclId);
                response.put("po", po);
                response.put("productcode", productcode);

                if (updatedDto.isPresent()) {
                    response.put("soLuongDaMap", updatedDto.get().getSoLuongDaMap());
                    response.put("soLuongConLai", updatedDto.get().getSoLuongConLai());
                }

                log.info("Mapping successful - BarcodeTachCay: {}, RFID: {}", barcodeTachCay, rfid);
                log.info("========== End API: POST /api/egmf/tem-barcode-tach-cay/mapping (SUCCESS) ==========");
                return ResponseEntity.ok(response);

            } catch (IllegalStateException e) {
                String msg = e.getMessage() != null ? e.getMessage() : "";
                response.put("success", false);
                response.put("message", msg);
                if (msg.contains("BTP chưa được quét")) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_BTP_CHUA_QUET);
                    log.warn("BTP quantity missing - BarcodeTachCay: {}, RFID: {}", barcodeTachCay, rfid);
                    return ResponseEntity.badRequest().body(response);
                }
                if (msg.contains("vượt quá số lượng lũy kế nhận BTP")
                    || msg.contains("vượt quá số lượng nhận tem BTP")) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_VUOT_SUMMARY_PO);
                    log.warn("BTP quantity exceeded - BarcodeTachCay: {}, RFID: {}", barcodeTachCay, rfid);
                    return ResponseEntity.badRequest().body(response);
                }
                if (msg.contains("khsxId không được để trống")) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_KHSX_REQUIRED);
                    return ResponseEntity.badRequest().body(response);
                }
                if (msg.contains("RFID không đúng format bắt buộc cho khách DECATHLON")) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_RFID_INVALID_FORMAT);
                    return ResponseEntity.badRequest().body(response);
                }
                if (WorkShiftGuard.MESSAGE_NGOAI_CA.equals(msg)) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_NGOAI_CA_LAM_VIEC);
                    return ResponseEntity.badRequest().body(response);
                }
                String decathlonErrorCode = DecathlonRfidItemValidator.resolveErrorCode(msg);
                if (decathlonErrorCode != null) {
                    response.put("errorCode", decathlonErrorCode);
                    return ResponseEntity.badRequest().body(response);
                }
                if (msg.contains("đã đạt giới hạn mapping")) {
                    response.put("errorCode", TemBarcodeTachCayService.ERROR_SLOT_MACAY_FULL);
                    return ResponseEntity.badRequest().body(response);
                }
                response.put("errorCode", "DUPLICATE_RFID");
                log.error("Duplicate RFID error - BarcodeTachCay: {}, RFID: {}, Error: {}",
                    barcodeTachCay, rfid, e.getMessage());
                log.info("========== End API: POST /api/egmf/tem-barcode-tach-cay/mapping (DUPLICATE) ==========");
                return ResponseEntity.status(409).body(response);
            } catch (RuntimeException e) {
                response.put("success", false);
                response.put("message", e.getMessage());
                response.put("errorCode", "MAPPING_ERROR");
                log.error("Mapping error - BarcodeTachCay: {}, RFID: {}, Error: {}",
                    barcodeTachCay, rfid, e.getMessage());
                log.info("========== End API: POST /api/egmf/tem-barcode-tach-cay/mapping (ERROR) ==========");
                return ResponseEntity.status(500).body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi hệ thống: " + e.getMessage());
            log.error("Error during mapping - BarcodeTachCay: {}, RFID: {}, Error: {}",
                barcodeTachCay, rfid, e.getMessage(), e);
            log.info("========== End API: POST /api/egmf/tem-barcode-tach-cay/mapping (ERROR) ==========");
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * GET /api/egmf/tem-barcode-tach-cay/{code}/mappings : Lấy danh sách RFID đã mapping
     *
     * @param code Mã cây
     * @return ResponseEntity với danh sách RFID đã mapping
     */
    @GetMapping("/tem-barcode-tach-cay/{code}/mappings")
    public ResponseEntity<Map<String, Object>> getMappedRfids(@PathVariable String code) {
        log.debug("REST request to get mapped RFIDs for code: {}", code);

        Map<String, Object> response = new HashMap<>();

        // Kiểm tra mã cây có tồn tại không
        if (!temBarcodeTachCayService.existsByCode(code)) {
            response.put("exists", false);
            response.put("message", "Mã cây không tồn tại");
            response.put("mappings", new java.util.ArrayList<>());
            return ResponseEntity.ok(response);
        }

        // Lấy danh sách mapping
        java.util.List<Map<String, Object>> mappings = temBarcodeTachCayService.getMappedRfids(code);

        response.put("exists", true);
        response.put("barcodeTachCay", code);
        response.put("totalMappings", mappings.size());
        response.put("mappings", mappings);

        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/egmf/tem-barcode-tach-cay/mapping/{rfid} : Xóa mapping RFID
     *
     * Xóa mapping của RFID khỏi bảng CUTTING_TemBarcode_TachCay_RFID_Mapping
     * Tìm kiếm theo RFID, RFID_Barcode hoặc RFID_Hex
     *
     * @param rfid RFID cần xóa mapping
     * @return ResponseEntity với kết quả xóa
     */
    @DeleteMapping("/tem-barcode-tach-cay/mapping/{rfid}")
    public ResponseEntity<Map<String, Object>> deleteMappingByRfid(@PathVariable String rfid) {
        log.info("========== API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} ==========", rfid);
        log.info("Request Parameters - RFID: {}", rfid);
        log.info("Action: Xóa mapping RFID");

        Map<String, Object> response = new HashMap<>();

        try {
            // Validate input
            if (rfid == null || rfid.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "RFID không được để trống");
                log.warn("Validation failed: rfid is empty");
                log.info("========== End API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} (INVALID_INPUT) ==========", rfid);
                return ResponseEntity.badRequest().body(response);
            }

            // Kiểm tra RFID có được mapping không trước khi xóa
            String barcodeTachCay = temBarcodeTachCayService.getBarcodeTachCayByRfid(rfid);
            if (barcodeTachCay == null || barcodeTachCay.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "RFID " + rfid + " chưa được mapping");
                response.put("rfid", rfid);
                log.warn("RFID not mapped: {}", rfid);
                log.info("========== End API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} (NOT_MAPPED) ==========", rfid);
                return ResponseEntity.status(404).body(response);
            }

            // Thực hiện xóa mapping
            int deletedCount = temBarcodeTachCayService.deleteMappingByRfid(rfid);

            if (deletedCount > 0) {
                response.put("success", true);
                response.put("message", "Xóa mapping thành công");
                response.put("rfid", rfid);
                response.put("barcodeTachCay", barcodeTachCay);
                response.put("deletedCount", deletedCount);

                log.info("Delete mapping successful - RFID: {}, BarcodeTachCay: {}, DeletedCount: {}",
                    rfid, barcodeTachCay, deletedCount);
                log.info("========== End API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} (SUCCESS) ==========", rfid);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Không thể xóa mapping RFID " + rfid);
                response.put("rfid", rfid);
                log.warn("Failed to delete mapping - RFID: {}, DeletedCount: {}", rfid, deletedCount);
                log.info("========== End API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} (DELETE_FAILED) ==========", rfid);
                return ResponseEntity.status(500).body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi hệ thống: " + e.getMessage());
            response.put("rfid", rfid);
            log.error("Error deleting mapping - RFID: {}, Error: {}", rfid, e.getMessage(), e);
            log.info("========== End API: DELETE /api/egmf/tem-barcode-tach-cay/mapping/{} (ERROR) ==========", rfid);
            return ResponseEntity.status(500).body(response);
        }
    }
}
