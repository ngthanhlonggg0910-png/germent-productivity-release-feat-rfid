package vn.nvsoft.service.egmf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.repository.egmf.TemBarcodeTachCayRepository;
import vn.nvsoft.service.dto.PoMaHangDTO;
import vn.nvsoft.service.dto.TemBarcodeTachCayDTO;
import vn.nvsoft.service.viewmodel.TemBarcodeTachCayViewModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service để xử lý logic liên quan đến barcode mã cây và mapping RFID
 */
@Service
@Transactional(readOnly = true)
public class TemBarcodeTachCayService {

    public static final String ERROR_KHSX_REQUIRED = "KHSX_REQUIRED";
    public static final String ERROR_SLOT_MACAY_FULL = "SLOT_MACAY_FULL";
    public static final String ERROR_BTP_CHUA_QUET = "btp_chua_quet_hoac_quantity_0";
    public static final String ERROR_VUOT_SUMMARY_PO = "vuot_gioi_han_summary_po";
    public static final String ERROR_VUOT_TONG_BTP = "vuot_gioi_han_tem_tong_btp";
    public static final String ERROR_MAPPING_BLOCKED = "MAPPING_BLOCKED";
    public static final String ERROR_RFID_INVALID_FORMAT = "RFID_INVALID_FORMAT";
    public static final String ERROR_DECATHLON_EAN_NOT_FOUND = DecathlonRfidItemValidator.ERROR_DECATHLON_EAN_NOT_FOUND;
    public static final String ERROR_DECATHLON_TREE_ITEM_MISSING = DecathlonRfidItemValidator.ERROR_DECATHLON_TREE_ITEM_MISSING;
    public static final String ERROR_DECATHLON_ITEM_MISMATCH = DecathlonRfidItemValidator.ERROR_DECATHLON_ITEM_MISMATCH;
    public static final String ERROR_NGOAI_CA_LAM_VIEC = WorkShiftGuard.ERROR_CODE;

    private final Logger log = LoggerFactory.getLogger(TemBarcodeTachCayService.class);

    private final TemBarcodeTachCayRepository temBarcodeTachCayRepository;
    private final DecathlonRfidItemValidator decathlonRfidItemValidator;
    private final WorkShiftGuard workShiftGuard;

    public TemBarcodeTachCayService(TemBarcodeTachCayRepository temBarcodeTachCayRepository,
                                    DecathlonRfidItemValidator decathlonRfidItemValidator,
                                    WorkShiftGuard workShiftGuard) {
        this.temBarcodeTachCayRepository = temBarcodeTachCayRepository;
        this.decathlonRfidItemValidator = decathlonRfidItemValidator;
        this.workShiftGuard = workShiftGuard;
    }

    /**
     * Lấy thông tin barcode mã cây theo Code
     * @param code Mã cây (bắt đầu bằng "BTP")
     * @return Optional chứa DTO với thông tin mã cây và số lượng mapping
     */
    public Optional<TemBarcodeTachCayDTO> getByCode(String code) {
        log.debug("Request to get TemBarcodeTachCay by code: {}", code);

        List<TemBarcodeTachCayViewModel> results = temBarcodeTachCayRepository.findByCode(code);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        TemBarcodeTachCayViewModel vm = preferExactCodeMatch(results, code);
        return Optional.of(convertToDTO(vm));
    }

    /**
     * Lấy thông tin barcode mã cây theo Code và PO (và mã hàng)
     * Kiểm tra mã cây có tồn tại và có đúng với PO (và mã hàng) không
     * @param code Mã cây (bắt đầu bằng "BTP")
     * @param po Mã PO cần kiểm tra
     * @param mahang Mã hàng (ProductCode). Nếu null/rỗng thì không lọc theo mã hàng.
     * @return Optional chứa DTO với thông tin mã cây và số lượng mapping
     */
    public Optional<TemBarcodeTachCayDTO> getByCodeAndPo(String code, String po, String mahang) {
        log.debug("Request to get TemBarcodeTachCay by code: {}, PO: {}, MaHang: {}", code, po, mahang);

        List<TemBarcodeTachCayViewModel> results = temBarcodeTachCayRepository.findByCodeAndPo(code, po, mahang);

        if (results.isEmpty()) {
            log.warn("Mã cây {} không tồn tại hoặc không khớp với PO {} / mã hàng {}", code, po, mahang);
            return Optional.empty();
        }

        TemBarcodeTachCayViewModel vm = preferExactCodeMatch(results, code);
        log.debug("Found matching TemBarcodeTachCay - Code: {}, PO: {}, ProductCode: {}", vm.getCode(), vm.getPo(), vm.getProductCode());
        return Optional.of(convertToDTO(vm));
    }

    /**
     * Ưu tiên khớp đúng {@code Code}; nếu quét Barcode gốc (không có -1/-2/...) thì lấy bản ghi đầu.
     */
    private TemBarcodeTachCayViewModel preferExactCodeMatch(List<TemBarcodeTachCayViewModel> results, String code) {
        if (code == null) {
            return results.get(0);
        }
        return results.stream()
            .filter(r -> code.equalsIgnoreCase(r.getCode()))
            .findFirst()
            .orElse(results.get(0));
    }

    /**
     * Lấy danh sách PO (distinct) kèm mã hàng (ProductCode) theo mã cây (Code, ví dụ BTPxxx-xx).
     * @param code Mã cây
     * @return Danh sách (PO, ProductCode) gắn với mã cây
     */
    public List<PoMaHangDTO> getDistinctPosByCode(String code) {
        log.debug("Request to get distinct POs (with productCode) by code: {}", code);
        List<TemBarcodeTachCayRepository.PoMaHangProjection> projections =
            temBarcodeTachCayRepository.findDistinctPoByCode(code);
        List<PoMaHangDTO> result = new java.util.ArrayList<>();
        for (TemBarcodeTachCayRepository.PoMaHangProjection p : projections) {
            result.add(new PoMaHangDTO(p.getPo(), p.getProductCode()));
        }
        return result;
    }

    /**
     * Kiểm tra mã cây có tồn tại không
     * @param code Mã cây
     * @return true nếu tồn tại
     */
    public boolean existsByCode(String code) {
        Integer count = temBarcodeTachCayRepository.existsByCode(code);
        return count != null && count > 0;
    }

    /**
     * Kiểm tra có thể mapping thêm RFID cho mã cây không (chỉ theo SoLopTrai)
     * @param code Mã cây
     * @return true nếu còn slot để mapping
     */
    public boolean canMapMoreRfid(String code) {
        Optional<TemBarcodeTachCayDTO> dto = getByCode(code);
        if (!dto.isPresent()) {
            return false;
        }

        Integer soLopTrai = dto.get().getSoLopTrai();
        Integer soLuongDaMap = dto.get().getSoLuongDaMap();

        if (soLopTrai == null || soLuongDaMap == null) {
            return false;
        }

        return soLuongDaMap < soLopTrai;
    }

    /**
     * Kiểm tra có thể mapping thêm RFID theo cả SoLopTrai mã cây, trần tem tổng BTP và trần TongSLThucNhan theo mã cây + khsxId
     */
    public boolean canMapMoreRfid(String code, Long khsxId) {
        if (khsxId == null || !canMapMoreRfid(code)) {
            return false;
        }
        if (!canMapMoreRfidByParentBarcode(code, khsxId)) {
            return false;
        }
        Integer limit = temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
        if (limit == null || limit <= 0) {
            return false;
        }
        Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcodeAndKhsx(code, khsxId);
        int mappedCount = mapped != null ? mapped : 0;
        return mappedCount < limit;
    }

    /**
     * Kiểm tra tổng mapping RFID trên tất cả mã cây tách chưa vượt trần nhận tem tổng BTP.
     */
    public boolean canMapMoreRfidByParentBarcode(String code, Long khsxId) {
        if (code == null || khsxId == null) {
            return false;
        }
        String parentBarcode = temBarcodeTachCayRepository.findParentBarcodeByCode(code);
        if (parentBarcode == null || parentBarcode.trim().isEmpty()) {
            return false;
        }
        Integer parentLimit = temBarcodeTachCayRepository.getTongSLThucNhanByBarcodeAndKhsx(parentBarcode, khsxId);
        if (parentLimit == null || parentLimit <= 0) {
            return false;
        }
        Integer parentMapped = temBarcodeTachCayRepository.countMappedRfidByParentBarcodeAndKhsx(parentBarcode, khsxId);
        int parentMappedCount = parentMapped != null ? parentMapped : 0;
        return parentMappedCount < parentLimit;
    }

    /**
     * Phân loại lý do không thể map (sau khi đã biết canMapMoreRfid(code, khsxId) == false)
     */
    public String resolveCannotMapErrorCode(String code, Long khsxId) {
        if (khsxId == null) {
            return ERROR_KHSX_REQUIRED;
        }
        if (!canMapMoreRfid(code)) {
            return ERROR_SLOT_MACAY_FULL;
        }
        String parentBarcode = temBarcodeTachCayRepository.findParentBarcodeByCode(code);
        if (parentBarcode == null || parentBarcode.trim().isEmpty()) {
            return ERROR_BTP_CHUA_QUET;
        }
        Integer parentLimit = temBarcodeTachCayRepository.getTongSLThucNhanByBarcodeAndKhsx(parentBarcode, khsxId);
        if (parentLimit == null || parentLimit <= 0) {
            return ERROR_BTP_CHUA_QUET;
        }
        Integer parentMapped = temBarcodeTachCayRepository.countMappedRfidByParentBarcodeAndKhsx(parentBarcode, khsxId);
        if ((parentMapped != null ? parentMapped : 0) >= parentLimit) {
            return ERROR_VUOT_TONG_BTP;
        }
        Integer limit = temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
        if (limit == null || limit <= 0) {
            return ERROR_BTP_CHUA_QUET;
        }
        Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcodeAndKhsx(code, khsxId);
        if ((mapped != null ? mapped : 0) >= limit) {
            return ERROR_VUOT_SUMMARY_PO;
        }
        return ERROR_MAPPING_BLOCKED;
    }

    /**
     * Trần nhận tem tổng BTP theo Barcode gốc + KHSX ({@code TongSLThucNhan}).
     */
    public Integer getQuantityLimitParentBtp(String code, Long khsxId) {
        if (code == null || khsxId == null) {
            return null;
        }
        String parentBarcode = temBarcodeTachCayRepository.findParentBarcodeByCode(code);
        if (parentBarcode == null || parentBarcode.trim().isEmpty()) {
            return null;
        }
        return temBarcodeTachCayRepository.getTongSLThucNhanByBarcodeAndKhsx(parentBarcode, khsxId);
    }

    public Integer countMappedByParentBarcode(String code, Long khsxId) {
        if (code == null || khsxId == null) {
            return 0;
        }
        String parentBarcode = temBarcodeTachCayRepository.findParentBarcodeByCode(code);
        if (parentBarcode == null || parentBarcode.trim().isEmpty()) {
            return 0;
        }
        Integer mapped = temBarcodeTachCayRepository.countMappedRfidByParentBarcodeAndKhsx(parentBarcode, khsxId);
        return mapped != null ? mapped : 0;
    }

    public Integer countMappedByCodeAndKhsx(String code, Long khsxId) {
        if (code == null || khsxId == null) {
            return 0;
        }
        Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcodeAndKhsx(code, khsxId);
        return mapped != null ? mapped : 0;
    }

    /**
     * Trần nhận tem BTP theo mã cây + KHSX ({@code TongSLThucNhan}).
     */
    public Integer getQuantityLimitBtp(String code, Long khsxId) {
        if (code == null || khsxId == null) {
            return null;
        }
        return temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
    }

    public Integer countMappedByKhsxId(Long khsxId) {
        if (khsxId == null) {
            return 0;
        }
        Integer mapped = temBarcodeTachCayRepository.countMappedRfidByKhsxId(khsxId);
        return mapped != null ? mapped : 0;
    }

    /**
     * Kiểm tra RFID đã được mapping chưa
     * @param rfid RFID cần kiểm tra
     * @return true nếu đã được mapping
     */
    public boolean isRfidAlreadyMapped(String rfid) {
        Integer count = temBarcodeTachCayRepository.countByAnyRfid(rfid);
        return count != null && count > 0;
    }

    /**
     * Lấy mã cây đã mapping với RFID
     * @param rfid RFID cần kiểm tra
     * @return Mã cây (Code) đã mapping, null nếu chưa mapping
     */
    public String getBarcodeTachCayByRfid(String rfid) {
        log.debug("Service: getBarcodeTachCayByRfid - RFID: {}", rfid);
        String barcodeTachCay = temBarcodeTachCayRepository.findBarcodeTachCayByRfid(rfid);
        log.debug("Service: getBarcodeTachCayByRfid - Result: {}", barcodeTachCay);
        return barcodeTachCay;
    }

    /**
     * Lấy tên khách hàng (distinct) theo mã cây, nối bằng dấu phẩy nếu nhiều.
     */
    public String resolveKhachHang(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        List<String> names = temBarcodeTachCayRepository.findDistinctKhachHangByCode(code.trim());
        if (names == null || names.isEmpty()) {
            return null;
        }
        return names.stream()
            .filter(n -> n != null && !n.trim().isEmpty())
            .map(String::trim)
            .distinct()
            .collect(Collectors.joining(", "));
    }

    /**
     * Kiểm tra mã cây có thuộc khách hàng DECATHLON không.
     */
    public boolean isDecathlonCustomerByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        List<String> names = temBarcodeTachCayRepository.findDistinctKhachHangByCode(code.trim());
        return RfidGs1Validator.isDecathlonCustomer(names);
    }

    /**
     * Kiểm tra format RFID theo khách hàng của mã cây.
     * DECATHLON: bắt buộc decode GS1 (01)14 số(21)12 số.
     * Khách khác: không decode / không ràng format — lưu chuỗi quét gốc.
     */
    public void validateRfidFormatForMapping(String barcodeTachCay, String rfid) {
        if (rfid == null || rfid.trim().isEmpty()) {
            throw new IllegalStateException("RFID không được để trống");
        }
        if (isDecathlonCustomerByCode(barcodeTachCay)) {
            if (!RfidGs1Validator.isValidGs1Strict(rfid)) {
                throw new IllegalStateException(
                    "RFID không đúng format bắt buộc cho khách DECATHLON: (01)<14 chữ số GTIN>(21)<12 chữ số serial>");
            }
        }
    }

    /**
     * Kiểm tra đang trong ca làm việc trước khi map RFID.
     */
    public void validateWorkShiftForMapping(Long proCheckPointId, Long nsclId) {
        workShiftGuard.assertWithinWorkShift(proCheckPointId, nsclId);
    }

    public boolean isWithinWorkShiftForMapping(Long proCheckPointId, Long nsclId) {
        return workShiftGuard.isWithinWorkShift(proCheckPointId, nsclId);
    }

    /**
     * Validate format + itemId DECATHLON (catalog EAN vs Size_Item cây) trước khi map RFID.
     */
    public void validateRfidForMapping(String barcodeTachCay, String rfid, String po) {
        validateRfidFormatForMapping(barcodeTachCay, rfid);
        decathlonRfidItemValidator.validateItemMatch(barcodeTachCay, rfid, po);
    }

    /**
     * Validate itemId DECATHLON khi kiểm chất lượng bằng RFID.
     */
    public void validateDecathlonItemForQualityCheck(String rfid) {
        decathlonRfidItemValidator.validateItemMatchForQualityCheck(rfid);
    }

    /**
     * Lấy số lượng RFID đã mapping cho mã cây
     * @param barcodeTachCay Mã cây
     * @return Số lượng đã mapping
     */
    public Integer countMappedRfid(String barcodeTachCay) {
        return temBarcodeTachCayRepository.countMappedRfidByBarcode(barcodeTachCay);
    }

    /**
     * Thực hiện mapping RFID với barcode mã cây
     * @param rfidBarcode RFID Barcode
     * @param barcodeTachCay Mã cây
     * @param rfid RFID
     * @param rfidHex RFID Hex
     * @param nguoiMap Người thực hiện mapping
     * @param tkId ID tài khoản
     * @param codeRfid Code RFID
     * @param codeRfidHex Code RFID Hex
     * @param khsxId ID kế hoạch sản xuất (PRO_KE_HOACH_SAN_XUAT.ID) gắn với lần map này
     * @param nsclId ID NSCL (PRO_NSCL.ID) gắn với lần map này
     * @param po PO chính xác gắn với mã cây tại thời điểm map (vì 1 mã cây có thể có nhiều PO)
     * @param productcode Mã sản phẩm (mã đơn hàng) gắn với lần map này
     * @return true nếu mapping thành công
     */
    @Transactional
    public boolean mapRfidToBarcode(String rfidBarcode, String barcodeTachCay, String rfid, String rfidHex,
                                     String nguoiMap, Long tkId, String codeRfid, String codeRfidHex,
                                     Long khsxId, Long nsclId, Long proCheckPointId, String po, String productcode) {
        log.info("Service: mapRfidToBarcode - RFID_Barcode: {}, BarcodeTachCay: {}, RFID: {}, NguoiMap: {}, KhsxId: {}, NsclId: {}, ProCheckPointId: {}, PO: {}, ProductCode: {}",
            rfidBarcode, barcodeTachCay, rfid, nguoiMap, khsxId, nsclId, proCheckPointId, po, productcode);

        if (khsxId == null) {
            throw new IllegalStateException("khsxId không được để trống khi mapping RFID");
        }

        validateWorkShiftForMapping(proCheckPointId, nsclId);
        validateRfidForMapping(barcodeTachCay, rfid, po);

        try {
            // Atomic insert (chống race condition vượt slot + chống duplicate theo RFID/RFID_Barcode/RFID_Hex)
            int rowsAffected = temBarcodeTachCayRepository.insertMappingIfAvailable(
                rfidBarcode, barcodeTachCay, rfid, rfidHex, nguoiMap, tkId, codeRfid, codeRfidHex, khsxId, nsclId, po, productcode
            );

            boolean success = rowsAffected > 0;
            log.info("Service: mapRfidToBarcode - Result: {}, RowsAffected: {}", success, rowsAffected);

            if (!success) {
                // Phân loại lỗi để trả message đúng
                if (isRfidAlreadyMapped(rfid)) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected due to duplicate RFID: {}", rfid);
                    throw new IllegalStateException("RFID đã được mapping với mã cây khác");
                }

                Optional<TemBarcodeTachCayDTO> dtoOpt = getByCode(barcodeTachCay);
                if (!dtoOpt.isPresent()) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected because barcode not found: {}", barcodeTachCay);
                    throw new IllegalStateException("Không tìm thấy mã cây: " + barcodeTachCay);
                }

                TemBarcodeTachCayDTO dto = dtoOpt.get();
                Integer soLopTrai = dto.getSoLopTrai() != null ? dto.getSoLopTrai() : 0;
                Integer soLuongDaMap = dto.getSoLuongDaMap() != null ? dto.getSoLuongDaMap() : 0;
                if (soLuongDaMap >= soLopTrai) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected due to limit reached: {} ({}/{})",
                        barcodeTachCay, soLuongDaMap, soLopTrai);
                    throw new IllegalStateException("Mã cây " + barcodeTachCay + " đã đạt giới hạn mapping (" + soLuongDaMap + "/" + soLopTrai + ")");
                }

                String parentBarcode = temBarcodeTachCayRepository.findParentBarcodeByCode(barcodeTachCay);
                Integer parentLimit = parentBarcode != null
                    ? temBarcodeTachCayRepository.getTongSLThucNhanByBarcodeAndKhsx(parentBarcode, khsxId)
                    : null;
                if (parentLimit == null || parentLimit <= 0) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected because parent TongSLThucNhan missing/zero for code={}, parentBarcode={}, khsxId={}",
                        barcodeTachCay, parentBarcode, khsxId);
                    throw new IllegalStateException(
                        "BTP chưa được quét nhận hoặc số lượng nhận tem tổng BTP bằng 0, không thể mapping RFID");
                }
                Integer parentMapped = parentBarcode != null
                    ? temBarcodeTachCayRepository.countMappedRfidByParentBarcodeAndKhsx(parentBarcode, khsxId)
                    : 0;
                int parentMappedCount = parentMapped != null ? parentMapped : 0;
                if (parentMappedCount >= parentLimit) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected due to parent TongSLThucNhan limit: parentBarcode={} ({}/{})",
                        parentBarcode, parentMappedCount, parentLimit);
                    throw new IllegalStateException(
                        "Số lượng mapping RFID vượt quá số lượng nhận tem tổng BTP");
                }

                Integer limit = temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(barcodeTachCay, khsxId);
                if (limit == null || limit <= 0) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected because TongSLThucNhan missing/zero for code={}, khsxId={}",
                        barcodeTachCay, khsxId);
                    throw new IllegalStateException(
                        "BTP chưa được quét nhận hoặc số lượng nhận BTP bằng 0, không thể mapping RFID");
                }
                Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcodeAndKhsx(barcodeTachCay, khsxId);
                int mappedCount = mapped != null ? mapped : 0;
                if (mappedCount >= limit) {
                    log.warn("Service: mapRfidToBarcode - Insert rejected due to TongSLThucNhan limit: code={} ({}/{})",
                        barcodeTachCay, mappedCount, limit);
                    throw new IllegalStateException(
                        "Số lượng mapping RFID vượt quá số lượng nhận tem BTP của mã cây");
                }

                log.warn("Service: mapRfidToBarcode - Insert rejected for unknown reason. BarcodeTachCay: {}, RFID: {}",
                    barcodeTachCay, rfid);
                throw new RuntimeException("Không thể mapping (vui lòng thử lại)");
            }

            return true;
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // Bắt lỗi unique constraint violation từ database
            log.error("Service: mapRfidToBarcode - Duplicate RFID detected by database: {}", rfid);
            throw new IllegalStateException("RFID đã được mapping với mã cây khác (database constraint)");
        } catch (IllegalStateException e) {
            // Re-throw IllegalStateException để controller xử lý
            throw e;
        } catch (Exception e) {
            log.error("Service: mapRfidToBarcode - Error: {}", e.getMessage(), e);
            throw new RuntimeException("Lỗi khi thực hiện mapping: " + e.getMessage());
        }
    }

    /**
     * Lấy danh sách RFID đã mapping cho mã cây
     * @param barcodeTachCay Mã cây
     * @return Danh sách mapping
     */
    public java.util.List<java.util.Map<String, Object>> getMappedRfids(String barcodeTachCay) {
        log.debug("Service: getMappedRfids - BarcodeTachCay: {}", barcodeTachCay);
        return temBarcodeTachCayRepository.findMappingsByBarcode(barcodeTachCay);
    }

    /**
     * Xóa mapping RFID theo RFID
     * @param rfid RFID cần xóa mapping
     * @return Số bản ghi đã xóa
     */
    @Transactional
    public int deleteMappingByRfid(String rfid) {
        log.info("Service: deleteMappingByRfid - RFID: {}", rfid);

        try {
            int deletedCount = temBarcodeTachCayRepository.deleteMappingByRfid(rfid);
            log.info("Service: deleteMappingByRfid - Result: {} records deleted", deletedCount);
            return deletedCount;
        } catch (Exception e) {
            log.error("Service: deleteMappingByRfid - Error: {}", e.getMessage(), e);
            throw new RuntimeException("Lỗi khi xóa mapping: " + e.getMessage());
        }
    }

    /**
     * Convert ViewModel sang DTO
     */
    private TemBarcodeTachCayDTO convertToDTO(TemBarcodeTachCayViewModel vm) {
        TemBarcodeTachCayDTO dto = new TemBarcodeTachCayDTO();
        dto.setId(vm.getId());
        dto.setBarcode(vm.getBarcode());
        dto.setRfid(vm.getRfid());
        dto.setRfidHex(vm.getRfidHex());
        dto.setTkId(vm.getTkId());
        dto.setNguoiMap(vm.getNguoiMap());
        dto.setThoiGianMap(vm.getThoiGianMap());
        dto.setNgayTao(vm.getNgayTao());
        dto.setLenhSanXuat(vm.getLenhSanXuat());
        dto.setMaHang(vm.getMaHang());
        dto.setLoaiNguyenLieu(vm.getLoaiNguyenLieu());
        dto.setNguoiCat(vm.getNguoiCat());
        dto.setNguoiTrai(vm.getNguoiTrai());
        dto.setAnhMauPhieu(vm.getAnhMauPhieu());
        dto.setTenPhongBan(vm.getTenPhongBan());
        dto.setMua(vm.getMua());
        dto.setBanMay(vm.getBanMay());
        dto.setTenSize(vm.getTenSize());
        dto.setIdBanMay(vm.getIdBanMay());
        dto.setPhieuHoachToanId(vm.getPhieuHoachToanId());
        dto.setDan(vm.getDan());
        dto.setSoLuong(vm.getSoLuong());
        dto.setTenChiTiet(vm.getTenChiTiet());
        dto.setPo(vm.getPo());
        dto.setPart(vm.getPart());
        dto.setStt(vm.getStt());
        dto.setMaCay(vm.getMaCay());
        dto.setSoLopTrai(vm.getSoLopTraiHieuLuc());
        dto.setLot(vm.getLot());
        dto.setAnhMau(vm.getAnhMau());
        dto.setNgayCapNhat(vm.getNgayCapNhat());
        dto.setCode(vm.getCode());
        dto.setProductCode(vm.getProductCode());
        dto.setSoLuongDaMap(vm.getSoLuongDaMap());

        // Tính số lượng còn lại có thể mapping
        Integer soLopTrai = vm.getSoLopTraiHieuLuc() != null ? vm.getSoLopTraiHieuLuc() : 0;
        Integer soLuongDaMap = vm.getSoLuongDaMap() != null ? vm.getSoLuongDaMap() : 0;
        dto.setSoLuongConLai(Math.max(0, soLopTrai - soLuongDaMap));

        String khachHang = resolveKhachHang(vm.getCode());
        dto.setKhachHang(khachHang);
        dto.setDecathlonCustomer(isDecathlonCustomerByCode(vm.getCode()));

        return dto;
    }
}
