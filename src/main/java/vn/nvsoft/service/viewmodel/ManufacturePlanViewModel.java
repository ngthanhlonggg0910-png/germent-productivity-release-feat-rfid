package vn.nvsoft.service.viewmodel;

import java.time.Instant;

public interface ManufacturePlanViewModel {

    Long getPlanId();
    Instant getPlanManufactureDate();
    Long getPlanRequestedQuantity();
    String getPlanStatus();
    String getPlanNote();
    Long getPlanSam();
    Long getPlanLotNumber();

    Long getBandId();
    String getBandName();
    Long getBandIndex();

    Long getSpId();
    String getSpMaSanPham();
    String getSpMaDonHang();
    String getSpPo();
    String getSpColor();
    Long getSpSoLuongSanXuat();
    Long getSpKhachHangId();
    Long getSpTenMuaId();
    String getSpFrontImgUrl();
    String getSpBehindImgUrl();

    String getKhTen();
    String getTenMua();
    Integer getSlDaKiem();

    String getCanhBaoChatLuong();
}
