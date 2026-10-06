package vn.nvsoft.service;

import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.*;
import vn.nvsoft.domain.heso.HeSoNhanVien;
import vn.nvsoft.domain.nscl.MaHangChinhPhu;
import vn.nvsoft.service.dto.NSCLTheoGioDTO;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiTheoGioHienTai;
import vn.nvsoft.service.dto.ProNSCLDTO;
import vn.nvsoft.service.dto.MaCayByPODTO;
import vn.nvsoft.service.dto.ApplyPoFixRequestDTO;
import vn.nvsoft.service.dto.PoFixPastDTO;
import vn.nvsoft.service.dto.TongMayRaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProNSCL}.
 */
public interface ProNSCLService {

    /**
     * Save a proNSCL.
     *
     * @param proNSCLDTO the entity to save.
     * @return the persisted entity.
     */
    ProNSCLDTO save(ProNSCLDTO proNSCLDTO);

    /**
     * Get all the proNSCLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProNSCLDTO> findAll(Pageable pageable);

    /**
     * Get the "id" proNSCL.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProNSCLDTO> findOne(Long id);

    /**
     * Delete the "id" proNSCL.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    NSCLTheoGioDTO getNSCLTheoGio(Long proCheckPointID, Long proKeHoachSanXuat);

    /**
     * Lấy NSCL theo tổng lũy kế của tất cả các khung giờ
     * Tính tổng: qty = qty1h + qty2h + ... + qty14h
     *           man = man1h + man2h + ... + man14h
     *           fix = fix1h + fix2h + ... + fix14h
     *           reject = reject1h + reject2h + ... + reject14h
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuat ID của kế hoạch sản xuất
     * @return NSCLTheoGioDTO với tổng lũy kế
     */
    NSCLTheoGioDTO getNSCLTheoGioLuyKe(Long proCheckPointID, Long proKeHoachSanXuat);

    public NSCLTheoGioDTO increaseHangDatTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID);

    public NSCLTheoGioDTO increaseHangDatTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, String rfid);

    public NSCLTheoGioDTO increaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, ProLoiDTO proLoiDTO, Boolean firstTime);

    public NSCLTheoGioDTO increaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, ProLoiDTO proLoiDTO, Boolean firstTime, Long chiTietLoiId, String rfid);

    public NSCLTheoGioDTO increaseHangTraTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID);

    public NSCLTheoGioDTO increaseHangTraTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, String rfid);

    /**
     * Giảm số lượng sửa (SUA) và tăng số lượng lỗi (LOI) theo lũy kế
     * Trường hợp không có RFID
     * - Giảm sửa từ giờ muộn nhất (14h) về sớm nhất (1h)
     * - Tăng lỗi vào giờ sớm nhất (1h)
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return NSCLTheoGioDTO với dữ liệu lũy kế sau khi giảm sửa và tăng lỗi
     */
    NSCLTheoGioDTO decreaseHangTraAndIncreaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID);

    public List<ProNSCLChiTietLoiDTO> getAllDetailDefects(Long nsclId);

    List<NsclTheoGio> getNSCLTheoGioTrenChuyen(Integer chuyen);

    NsclTheoGioChuyenThongTinChung getNSCLTheoGioTrenChuyenThongTinChung(Integer chuyen);

    NangSuatLaoDongNgay getNangSuatLaoDongNgayTrenChuyen(Integer chuyen);

    ProNSCLChiTietLoiTheoGioHienTai getChiTietNSCLTheoThoiGianTrenChuyen(Integer chuyen);

    List<ProNSCL> findByProKeHoachSanXuat(Long proKeHoachSanXuat);

    List<MaHangChinhPhu> findMaHangChinhPhuByProKHSXId(Long proKHSXId);
    void updateMaChungLoaiToProKHSX(Long proKHSXId, String maChungLoai);

    /**
     * Get NSCL theo gio hien tai by barcode.
     *
     * @param proCheckPointID the checkpoint id.
     * @param barcode the barcode string.
     * @return the NSCLTheoGioDTO.
     */
    NSCLTheoGioDTO getNSCLTheoGioByBarcode(Long proCheckPointID, String barcode);

    /**
     * Lấy trạng thái checked_type mới nhất của displayname (rfid/barcode)
     */
    String getLatestCheckedTypeByDisplayName(String displayName);

    /**
     * Kiểm tra đã kiểm tra NSCL với displayName (rfid/barcode) và checked_type ('DAT', 'SUA')
     */
    boolean isCheckedNSCLByDisplayName(String displayName);

    /**
     * Lấy số lượng fix (SUA) có RFID theo pro_nscl_id
     * @param proNsclId ID của ProNSCL
     * @return số lượng fix có RFID
     */
    Long getFixCountWithRfidByProNsclId(Long proNsclId);


    /**
     * Gọi stored procedure pro_nscl_sanpham_codeinjection
     * Luôn trả về 3 trường: id, error, action
     */
    public static interface CodeInjectionResult {
        Long getId();
        String getError();
        String getAction();
    }

    /**
     * Gọi stored procedure pro_nscl_sanpham_codeinjection với các tham số đơn giản hóa
     */
    CodeInjectionResult proNsclSanPhamCodeInjection(
        Long proCheckpointId,
        Long proKhsxId,
        String checkedType,
        String displayName,
        boolean isFirstTime,
        Long chiTietLoiId,
        String valueHEX,
        String rfidBarcode
    );

    /**
     * Lấy danh sách mã cây theo PO (và mã hàng) với thông tin chi tiết
     *
     * @param po     Mã PO cần tìm
     * @param mahang Mã hàng (ProductCode). Nếu null/rỗng thì không lọc theo mã hàng.
     * @return Danh sách mã cây với thông tin Lot, số tem đã quét và tổng tem
     */
    List<MaCayByPODTO> getMaCayByPO(String po, String mahang);

    /**
     * Lấy số lượng giới hạn BTP từ bảng Summary_PO_QuetNhan
     * 
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return Số lượng giới hạn BTP, null nếu không tìm thấy
     */
    Integer getQuantityLimitBTP(Long proKeHoachSanXuatID);

    /**
     * Lấy tổng may ra (tổng hàng đã kiểm: Sửa + Đạt) theo kế hoạch sản xuất
     * Gọi stored procedure SP_TongMayRa_ByKHSX
     * 
     * @param khsxId ID của kế hoạch sản xuất
     * @return TongMayRaDTO chứa tổng Qty, Fix và TongMayRa (Qty + Fix)
     */
    TongMayRaDTO getTongMayRaByKHSX(Long khsxId);

    /**
     * Tổng lỗi quá khứ (ngay_tao &lt; hôm nay) trong pool mapping query 2.
     */
    PoFixPastDTO getPoFixPastDefects(String poNo, Long khsxId);

    /**
     * Áp dụng delta sửa PO (pool query 2): insert SUA cho LOI RFID (latest state, tối đa delta),
     * FIFO trừ reject quá khứ, cộng sua_po_luy_ke trên nsclId (không ghi fix_*h).
     */
    void applyPoFixDelta(ApplyPoFixRequestDTO request);

    /**
     * TongMayRa trên pool mapping query 2 của KHSX: {@code Qty + Fix + sua_po_luy_ke} (không gồm reject).
     */
    int getTongMayRaPoolQuery2ByKhsxId(Long khsxId);
}
