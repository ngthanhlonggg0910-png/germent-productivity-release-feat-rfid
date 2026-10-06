package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.service.dto.CreateKiemTrongChiTietLoiRequest;
import vn.nvsoft.service.dto.KiemTrongSessionDTO;
import vn.nvsoft.service.dto.MarkKiemTrongSuaRequest;
import vn.nvsoft.service.dto.ProKiemtrongChiTietLoiDTO;

public interface ProKiemtrongService {

    KiemTrongSessionDTO getSession(Long checkpointId, Long khsxId);

    List<ProKiemtrongChiTietLoiDTO> addChiTietLoi(CreateKiemTrongChiTietLoiRequest request);

    void deleteChiTietLoi(Long id);

    /**
     * Đếm số chi tiết lỗi đã ghi cho RFID (displayname) trong session checkpoint+khsx.
     */
    int countLoiByRfid(Long checkpointId, Long khsxId, String rfidDisplayName);

    /**
     * Xóa toàn bộ chi tiết lỗi + sanpham gắn với RFID trong session.
     * @return số chi tiết lỗi đã xóa
     */
    int deleteLoiByRfid(Long checkpointId, Long khsxId, String rfidDisplayName);

    /**
     * Thêm dòng SUA cho RFID (giữ lịch sử LOI). Trả session đã refresh.
     */
    KiemTrongSessionDTO markSuaByRfid(MarkKiemTrongSuaRequest request);
}
