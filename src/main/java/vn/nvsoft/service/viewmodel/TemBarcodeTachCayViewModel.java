package vn.nvsoft.service.viewmodel;

import java.time.Instant;

/**
 * ViewModel cho thông tin barcode mã cây từ bảng eGMF.dbo.CUTTING_TemBarcode_TachCay
 */
public interface TemBarcodeTachCayViewModel {
    Long getId();
    String getBarcode();
    String getRfid();
    String getRfidHex();
    Long getTkId();
    String getNguoiMap();
    Instant getThoiGianMap();
    Instant getNgayTao();
    String getLenhSanXuat();
    String getMaHang();
    String getLoaiNguyenLieu();
    String getNguoiCat();
    String getNguoiTrai();
    String getAnhMauPhieu();
    String getTenPhongBan();
    String getMua();
    String getBanMay();
    String getTenSize();
    String getIdBanMay();
    Long getPhieuHoachToanId();
    String getDan();
    Integer getSoLuong();
    String getTenChiTiet();
    String getPo();
    String getPart();
    Integer getStt();
    String getMaCay();
    /**
     * Giới hạn mapping hiệu lực (BTP/BTH). Alias SQL {@code soLopTraiHieuLuc}
     * — không dùng tên {@code soLopTrai} để tránh gắn nhầm cột gốc trong CASE.
     */
    Integer getSoLopTraiHieuLuc();
    String getLot();
    String getAnhMau();
    Instant getNgayCapNhat();
    String getCode();

    // Mã hàng (ProductCode) lấy từ CUTTING_PhieuCapBTP
    String getProductCode();

    // Số lượng RFID đã mapping
    Integer getSoLuongDaMap();
}
