package vn.nvsoft.domain;
import java.util.Date;

public interface NsclTheoGioChuyenThongTinChung {
    String getCaption();
    String getMahang();
    Integer getKehoach();
    Integer getThuchien();
    Double getKehoach_phantram();
    Double getThuchien_phantram();
    Integer getNhapkho();
    Double getTalktime_kehoach();
    Double getTalktime_thuchien();
    String getTenChuyen();
    Date getNgayXuatHang();
    Date getNgayFinal();
    Integer getSoLuongPhanBo();
    Integer getSoLuongMayRa();
    Double getSoLuongMayRa_Phantram();
    Double getSoLuongConLai_Phantram();
    String getThuchien_color();
    String getTalktime_thuchien_color();
    Date getNgaySanXuatDauTien();
    Date getNgaySanXuatHomNay();
}
