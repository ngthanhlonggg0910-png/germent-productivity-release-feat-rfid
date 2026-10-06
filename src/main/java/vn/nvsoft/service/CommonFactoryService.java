package vn.nvsoft.service;

import vn.nvsoft.service.dto.BtnSXXongPermissionDTO;
import vn.nvsoft.service.dto.DanhSachNhomChuyenDTO;
import vn.nvsoft.service.dto.ManufacturePlanByPoDTO;
import vn.nvsoft.service.dto.ManufacturePlanViewModelDTO;
import vn.nvsoft.service.dto.SynchResultDTO;
import vn.nvsoft.service.viewmodel.CheckpointLocationViewModel;
import vn.nvsoft.service.viewmodel.KhsxMau;
import vn.nvsoft.service.viewmodel.UserCheckPointViewModel;
import vn.nvsoft.service.viewmodel.UserWorkingLocationViewModel;

import java.util.Date;
import java.util.List;

public interface CommonFactoryService {

    List<UserCheckPointViewModel> getUserWorkingCheckpoints(String username);

    List<UserCheckPointViewModel> getAllCheckPointForCurrentUser();

    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpoint(Long checkpointId);

    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointToday(Long checkpointId);

    /**
     * Lấy danh sách kế hoạch sản xuất hôm nay với logic mới (bao gồm cả lỗi trong slDaKiem)
     * Sử dụng stored procedure: getAllManufacturePlanOnCheckPointToday_Updated
     * Logic: slDaKiem = QTY (Đạt) + FIX (Sửa) + REJECT (Lỗi)
     * 
     * @param checkpointId ID của checkpoint
     * @return Danh sách kế hoạch sản xuất với tongHangDaKiem bao gồm cả lỗi
     */
    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayUpdated(Long checkpointId);

    /**
     * Kế hoạch SX hôm nay — proc {@code getAllManufacturePlanOnCheckPointToday_PoFix}.
     * slDaKiem gồm sua_po_luy_ke; {@code idNhom} có thể null (client cũ / không lọc nhóm).
     */
    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayPoFix(Long checkpointId, Long idNhom);

    /**
     * KHSX hôm nay trên checkpoint theo tem RFID đã map — proc
     * {@code getManufacturePlanOnCheckPointTodayByRfid_PoFix} (RFID / RFID_Hex / RFID_Barcode).
     */
    List<ManufacturePlanViewModelDTO> getManufacturePlansOnCheckpointTodayPoFixByRfid(
        Long checkpointId,
        String rfid,
        Long idNhom
    );

    /**
     * KHSX hôm nay theo {@code OMM_PO.PONo} — slDaKiem (Qty + Fix + sua_po_luy_ke), dùng refresh sau sửa PO.
     */
    List<ManufacturePlanByPoDTO> getManufacturePlansByPoNo(String poNo);

    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayAllComplete(Long checkpointId);

    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayOnDoThongSo(Long checkpointId);

    List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayOnRefinal(Long checkpointId);

    List<CheckpointLocationViewModel> getCurrentCheckpointWorkShift(Long checkpointId);

    UserWorkingLocationViewModel getUserWorkingLocation();

    void updateActiveProKHSXToday(Long proKHSXId, Long proChuyenId);

    void updateCompleteProKHSXToday(Long proKHSXId);

    /**
     * Excute getAllManufacturePlanOnCheckPointToday_Mau :khsxId
     * @param khsxId
     * @return
     */
    List<KhsxMau> getKhsxMauToDay(Long khsxId);

    /**
     * Gọi proc getAllManufacturePlanOnCheckPointToday_Synch theo checkpoint.
     * Kết quả trả về gồm result (1/0) và message.
     */
    SynchResultDTO synchManufacturePlanOnCheckPointToday(Long checkpointId);

    /**
     * Danh sách nhóm của chuyền theo checkpoint và ngày ({@code SP_GetDanhSachNhom}).
     */
    List<DanhSachNhomChuyenDTO> getDanhSachNhomChuyen(Long checkpointId, Long chuyenId, Date ngay);

    /**
     * Phân quyền nút SX xong theo checkpoint và bản ghi NSCL ({@code SP_PermissionCheckpointOnBtnSXXong}).
     */
    BtnSXXongPermissionDTO getPermissionBtnSXXong(Long checkpointId, Long proNSCLId);
}
