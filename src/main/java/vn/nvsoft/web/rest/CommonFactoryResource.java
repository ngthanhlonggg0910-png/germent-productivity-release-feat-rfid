package vn.nvsoft.web.rest;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;

import vn.nvsoft.security.AuthoritiesConstants;
import vn.nvsoft.service.CommonFactoryService;
import vn.nvsoft.service.dto.BtnSXXongPermissionDTO;
import vn.nvsoft.service.dto.DanhSachNhomChuyenDTO;
import vn.nvsoft.service.dto.ManufacturePlanByPoDTO;
import vn.nvsoft.service.dto.ManufacturePlanViewModelDTO;
import vn.nvsoft.service.dto.SynchResultDTO;
import vn.nvsoft.service.viewmodel.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommonFactoryResource {
    private final Logger log = LoggerFactory.getLogger(CommonFactoryResource.class);
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommonFactoryService commonFactoryService;

    public CommonFactoryResource(CommonFactoryService commonFactoryService) {
        this.commonFactoryService = commonFactoryService;
    }

    @Secured(AuthoritiesConstants.ADMIN)
    @GetMapping("/employee-working-data/{username}")
    public ResponseEntity<List<UserCheckPointViewModel>> getUserWorkingData(@PathVariable("username") String username) {
        log.debug("REST request to get getEmployeeWorkingData : {}", username);
        Optional<List<UserCheckPointViewModel>> workingDataViewModel = Optional.ofNullable(commonFactoryService.getUserWorkingCheckpoints(username));
        return ResponseUtil.wrapOrNotFound(workingDataViewModel);
    }

    @GetMapping("/user-working-checkpoints")
    public ResponseEntity<List<UserCheckPointViewModel>> getCurrentUserCheckpoints() {
        log.info("========== API: GET /api/user-working-checkpoints ==========");
        log.info("Request - Getting user working checkpoints");
        
        List<UserCheckPointViewModel> checkpoints = commonFactoryService.getAllCheckPointForCurrentUser();
        
        if (checkpoints != null) {
            log.info("Response - Number of checkpoints: {}", checkpoints.size());
            for (UserCheckPointViewModel cp : checkpoints) {
                log.info("  Checkpoint - ID: {}, Name: {}, Type: {}, BandId: {}, BandName: {}", 
                    cp.getCheckpointId(), cp.getCheckpointName(), cp.getType(), cp.getChuyenId(), cp.getTenChuyen());
            }
        } else {
            log.warn("Response - Checkpoints list is null");
        }
        log.info("========== End API: GET /api/user-working-checkpoints ==========");
        
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkpoints));
    }

    @GetMapping("/manuplan-on-checkpoint/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getAllOrdersOnCheckpoint(@PathVariable("checkpointId") Long checkpointId) {
        log.debug("REST request to get getAllOrdersOnCheckpoint : {}", checkpointId);
        return ResponseUtil.wrapOrNotFound(Optional.of(commonFactoryService.getAllManufacturePlansOnCheckpoint(checkpointId)));
    }

    @PutMapping("/manuplan-on-checkpoint-today/active/{proKHSXId}/{proChuyenId}")
    public ResponseEntity<Void> updateActiveProKHSXToday(@PathVariable("proKHSXId") Long proKHSXId, @PathVariable("proChuyenId") Long proChuyenId) {
        log.info("========== API: PUT /api/manuplan-on-checkpoint-today/active/{proKHSXId}/{proChuyenId} ==========");
        log.info("Request Parameters - ProKHSXId: {}, ProChuyenId: {}", proKHSXId, proChuyenId);
        
        commonFactoryService.updateActiveProKHSXToday(proKHSXId, proChuyenId);
        
        log.info("Response - Successfully activated production plan. ProKHSXId: {}, ProChuyenId: {}", proKHSXId, proChuyenId);
        log.info("========== End API: PUT /api/manuplan-on-checkpoint-today/active/{proKHSXId}/{proChuyenId} ==========");
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/manuplan-on-checkpoint-today/complete/{proKHSXId}")
    public ResponseEntity<Void> updateCompleteProKHSXToday(@PathVariable("proKHSXId") Long proKHSXId) {
        log.debug("REST request to update complete Pro KHSX to day : KHSX id {} ", proKHSXId);
        commonFactoryService.updateCompleteProKHSXToday(proKHSXId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/manuplan-on-checkpoint-today/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpoint(@PathVariable("checkpointId") Long checkpointId) {
        log.info("========== API: GET /api/manuplan-on-checkpoint-today/{checkpointId} ==========");
        log.info("Request Parameters - CheckpointId: {}", checkpointId);
        
        List<ManufacturePlanViewModelDTO> orders = commonFactoryService.getAllManufacturePlansOnCheckpointToday(checkpointId);
        
        if (orders != null) {
            log.info("Response - Number of production plans: {}", orders.size());
            for (ManufacturePlanViewModelDTO plan : orders) {
                log.info("  Plan - PlanId: {}, MaSanPham: {}, MaDonHang: {}, PO: {}, SoLuongYeuCau: {}, BandName: {}", 
                    plan.getPlanId(), plan.getSpMaSanPham(), plan.getSpMaDonHang(), 
                    plan.getSpPo(), plan.getPlanRequestedQuantity(), plan.getBandName());
            }
        } else {
            log.warn("Response - Production plans list is null");
        }
        log.info("========== End API: GET /api/manuplan-on-checkpoint-today/{checkpointId} ==========");
        
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * API mới: Lấy danh sách kế hoạch sản xuất hôm nay với logic cập nhật
     * 
     * Endpoint: GET /api/manuplan-on-checkpoint-today-updated/{checkpointId}
     * 
     * Khác biệt với API cũ:
     * - API cũ (/manuplan-on-checkpoint-today): slDaKiem = QTY (Đạt) + FIX (Sửa)
     * - API mới (/manuplan-on-checkpoint-today-updated): slDaKiem = QTY (Đạt) + FIX (Sửa) + REJECT (Lỗi)
     * 
     * Lý do: Nhất quán với TongMayRa = Đạt + Sửa + Lỗi trong code Java
     * 
     * @param checkpointId ID của checkpoint
     * @return Danh sách kế hoạch sản xuất với tongHangDaKiem bao gồm cả lỗi
     */
    @GetMapping("/manuplan-on-checkpoint-today-updated/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpointUpdated(@PathVariable("checkpointId") Long checkpointId) {
        log.info("========== API: GET /api/manuplan-on-checkpoint-today-updated/{checkpointId} ==========");
        log.info("Request Parameters - CheckpointId: {}", checkpointId);
        log.info("Note: This API uses updated logic - slDaKiem includes REJECT (Lỗi) in addition to QTY (Đạt) and FIX (Sửa)");
        
        List<ManufacturePlanViewModelDTO> orders = commonFactoryService.getAllManufacturePlansOnCheckpointTodayUpdated(checkpointId);
        
        if (orders != null) {
            log.info("Response - Number of production plans: {}", orders.size());
            for (ManufacturePlanViewModelDTO plan : orders) {
                log.info("  Plan - PlanId: {}, MaSanPham: {}, MaDonHang: {}, PO: {}, SoLuongYeuCau: {}, TongHangDaKiem: {}, BandName: {}", 
                    plan.getPlanId(), plan.getSpMaSanPham(), plan.getSpMaDonHang(), 
                    plan.getSpPo(), plan.getPlanRequestedQuantity(), plan.getTongHangDaKiem(), plan.getBandName());
            }
        } else {
            log.warn("Response - Production plans list is null");
        }
        log.info("========== End API: GET /api/manuplan-on-checkpoint-today-updated/{checkpointId} ==========");
        
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * Kế hoạch SX hôm nay (proc {@code getAllManufacturePlanOnCheckPointToday_PoFix}) — không thay proc production.
     *
     * slDaKiem = QTY + FIX + sua_po_luy_ke (mapping pool pro_nscl giữ nguyên proc gốc).
     * {@code idNhom}: tùy chọn, cùng id với {@code GET /api/chuyen-danh-sach-nhom}; bỏ qua hoặc null = không lọc.
     */
    @GetMapping("/manuplan-on-checkpoint-today-po-fix/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpointPoFix(
        @PathVariable("checkpointId") Long checkpointId,
        @RequestParam(value = "idNhom", required = false) Long idNhom
    ) {
        log.info("========== API: GET /api/manuplan-on-checkpoint-today-po-fix/{checkpointId} ==========");
        log.info("Request Parameters - CheckpointId: {}, idNhom: {}", checkpointId, idNhom);
        List<ManufacturePlanViewModelDTO> orders =
            commonFactoryService.getAllManufacturePlansOnCheckpointTodayPoFix(checkpointId, idNhom);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * KHSX hôm nay trên checkpoint theo tem RFID đã map (proc
     * {@code getManufacturePlanOnCheckPointTodayByRfid_PoFix}).
     * {@code rfid}: chấp nhận RFID / RFID_Hex / RFID_Barcode (so khớp không phân biệt hoa thường).
     * Trả về item cùng format {@code GET /api/manuplan-on-checkpoint-today-po-fix/{checkpointId}}.
     */
    @GetMapping("/manuplan-on-checkpoint-today-po-fix-by-rfid/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayManufacturePlansOnCheckpointPoFixByRfid(
        @PathVariable("checkpointId") Long checkpointId,
        @RequestParam("rfid") String rfid,
        @RequestParam(value = "idNhom", required = false) Long idNhom
    ) {
        log.info("========== API: GET /api/manuplan-on-checkpoint-today-po-fix-by-rfid/{checkpointId} ==========");
        log.info("Request Parameters - CheckpointId: {}, idNhom: {}, rfid: {}", checkpointId, idNhom, rfid);
        if (rfid == null || rfid.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<ManufacturePlanViewModelDTO> orders =
            commonFactoryService.getManufacturePlansOnCheckpointTodayPoFixByRfid(checkpointId, rfid, idNhom);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * Kế hoạch SX hôm nay theo {@code OMM_PO.PONo} — slDaKiem = Qty + Fix + sua_po_luy_ke (pool query 2).
     * Dùng refresh UI sau {@code POST /api/pro-nscls/po-fix}.
     */
    @GetMapping("/manuplan-by-po")
    public ResponseEntity<List<ManufacturePlanByPoDTO>> getManufacturePlansByPo(@RequestParam("poNo") String poNo) {
        log.info("========== API: GET /api/manuplan-by-po?poNo={} ==========", poNo);
        if (poNo == null || poNo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<ManufacturePlanByPoDTO> plans = commonFactoryService.getManufacturePlansByPoNo(poNo);
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/manuplan-on-checkpoint-today-complete/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpointAllComplete(@PathVariable("checkpointId") Long checkpointId) {
        log.debug("REST request to get getAllOrdersOnCheckpointToday : {}", checkpointId);
        List<ManufacturePlanViewModelDTO> orders = commonFactoryService.getAllManufacturePlansOnCheckpointTodayAllComplete(checkpointId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    @GetMapping("/work-shift-on-checkpoint/{checkpointId}")
    public ResponseEntity<CheckpointLocationViewModel> getCurrentCheckpointWorkShift(@PathVariable("checkpointId") Long checkpointId) {
        log.debug("REST request to get EmployeeIntroViewModel : {}", checkpointId);
        List<CheckpointLocationViewModel> checkpointWorkShift = commonFactoryService.getCurrentCheckpointWorkShift(checkpointId);
        if (checkpointWorkShift.size() > 1) {
            log.error("more than 1 employee working on the same checkpoint", checkpointWorkShift);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(checkpointWorkShift.size() > 0 ? checkpointWorkShift.get(0) : null));
    }

    @GetMapping("/user-location-info")
    public ResponseEntity<UserWorkingLocationViewModel> getCurrentUserLocation() {
        log.info("========== API: GET /api/user-location-info ==========");
        log.info("Request - Getting current user location information");
        
        UserWorkingLocationViewModel location = commonFactoryService.getUserWorkingLocation();
        
        if (location != null && location.getCheckpointLocation() != null) {
            log.info("Response - CheckpointId: {}, CheckpointName: {}, BandId: {}, BandName: {}, FactoryId: {}, FactoryName: {}", 
                location.getCheckpointLocation().getCheckpointId(),
                location.getCheckpointLocation().getCheckpointName(),
                location.getCheckpointLocation().getBandId(),
                location.getCheckpointLocation().getBandName(),
                location.getCheckpointLocation().getFactoryId(),
                location.getCheckpointLocation().getFactoryName());
        } else {
            log.warn("Response - Location is null");
        }
        log.info("========== End API: GET /api/user-location-info ==========");

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(location));
    }

    @GetMapping("/manuplan-on-checkpoint-today/do-thong-so/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpointDoThongSo(@PathVariable("checkpointId") Long checkpointId) {
        log.debug("REST request to get getAllOrdersOnCheckpointToday cho DoThongSo cua check point id: {}", checkpointId);
        List<ManufacturePlanViewModelDTO> orders = commonFactoryService.getAllManufacturePlansOnCheckpointTodayOnDoThongSo(checkpointId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * Call getAllManufacturePlanOnCheckPointToday_Mau :khsxId
     * @param khsxId
     * @return
     */
    @GetMapping("/manuplan-on-checkpoint-today/do-thong-so/mau/{khsxId}")
    public ResponseEntity<List<KhsxMau>> getKHSXMauToDay(@PathVariable("khsxId") Long khsxId) {
        log.debug("REST request to get getKHSXMauToDay tat ca Mau cho KHSX ID : {}", khsxId);
        List<KhsxMau> orders = commonFactoryService.getKhsxMauToDay(khsxId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    @GetMapping("/manuplan-on-checkpoint-today/refinal/{checkpointId}")
    public ResponseEntity<List<ManufacturePlanViewModelDTO>> getTodayAllManufacturePlansOnCheckpointRefinal(@PathVariable("checkpointId") Long checkpointId) {
        log.debug("REST request to get getAllOrdersOnCheckpointToday cho refinal cua check point id: {}", checkpointId);
        List<ManufacturePlanViewModelDTO> orders = commonFactoryService.getAllManufacturePlansOnCheckpointTodayOnRefinal(checkpointId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orders));
    }

    /**
     * Gọi proc getAllManufacturePlanOnCheckPointToday_Synch.
     * Kết quả proc trả về:
     * - result: 1/0 (thành công/thất bại)
     * - message: thông điệp tương ứng
     */
    @PostMapping("/manuplan-on-checkpoint-today/synch/{checkpointId}")
    public ResponseEntity<SynchResultDTO> synchManufacturePlanOnCheckPointToday(@PathVariable("checkpointId") Long checkpointId) {
        log.info("========== API: POST /api/manuplan-on-checkpoint-today/synch/{} ==========", checkpointId);
        log.info("Request Parameters - CheckpointId: {}", checkpointId);
        log.info("Action: Gọi proc getAllManufacturePlanOnCheckPointToday_Synch");

        SynchResultDTO result = commonFactoryService.synchManufacturePlanOnCheckPointToday(checkpointId);

        log.info("Response - result: {}, message: {}", result.getResult(), result.getMessage());
        log.info("========== End API: POST /api/manuplan-on-checkpoint-today/synch/{} ==========", checkpointId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET /api/chuyen-danh-sach-nhom} : Gọi {@code dbo.SP_GetDanhSachNhom} — danh sách nhóm của chuyền theo checkpoint và ngày.
     *
     * @param checkpointId tham số {@code @CheckpointId}
     * @param chuyenId     tham số {@code @ChuyenId}
     * @param ngay         tham số {@code @Ngay} (ISO-8601 date, ví dụ 2026-05-12)
     */
    @GetMapping("/chuyen-danh-sach-nhom")
    public ResponseEntity<List<DanhSachNhomChuyenDTO>> getDanhSachNhomChuyen(
        @RequestParam Long checkpointId,
        @RequestParam Long chuyenId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date ngay
    ) {
        log.debug("REST request SP_GetDanhSachNhom checkpointId={}, chuyenId={}, ngay={}", checkpointId, chuyenId, ngay);
        List<DanhSachNhomChuyenDTO> list = commonFactoryService.getDanhSachNhomChuyen(checkpointId, chuyenId, ngay);
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    /**
     * {@code GET /api/permission-checkpoint-btn-sx-xong} : Gọi {@code dbo.SP_PermissionCheckpointOnBtnSXXong}.
     *
     * @param checkpointId tham số {@code @CheckpointId}
     * @param proNSCLId    tham số {@code @ProNSCLId}
     */
    @GetMapping("/permission-checkpoint-btn-sx-xong")
    public ResponseEntity<BtnSXXongPermissionDTO> getPermissionBtnSXXong(
        @RequestParam Long checkpointId,
        @RequestParam("proNSCLId") Long proNSCLId
    ) {
        log.debug(
            "REST request SP_PermissionCheckpointOnBtnSXXong checkpointId={}, proNSCLId={}",
            checkpointId,
            proNSCLId
        );
        BtnSXXongPermissionDTO dto = commonFactoryService.getPermissionBtnSXXong(checkpointId, proNSCLId);
        return ResponseEntity.ok(dto != null ? dto : new BtnSXXongPermissionDTO(null));
    }

}
