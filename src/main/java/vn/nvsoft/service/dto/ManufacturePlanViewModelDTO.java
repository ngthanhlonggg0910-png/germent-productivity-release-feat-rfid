package vn.nvsoft.service.dto;

import java.time.Instant;
import vn.nvsoft.domain.ConstUtil;
import vn.nvsoft.filestorage.StorageProperties;
import vn.nvsoft.service.viewmodel.ManufacturePlanViewModel;

public class ManufacturePlanViewModelDTO {

    String canhBaoChatLuong;
    Long planId;
    Instant planManufactureDate;
    Long planRequestedQuantity;
    String planStatus;
    String planNote;
    Long planSam;
    Long planLotNumber;

    Long bandId;
    String bandName;
    Long bandIndex;

    Long spId;
    String spMaSanPham;
    String spMaDonHang;
    String spPo;
    String spColor;
    Long spSoLuongSanXuat;
    Long spKhachHangId;
    Long spTenMuaId;
    String spFrontImgUrl;
    String spBehindImgUrl;

    String khTen;
    String tenMua;

    Integer tongHangDaKiem = 0;

    public ManufacturePlanViewModelDTO(ManufacturePlanViewModel manufacturePlanViewModel, StorageProperties storageProperties) {
        planId = manufacturePlanViewModel.getPlanId();
        planManufactureDate = manufacturePlanViewModel.getPlanManufactureDate();
        planRequestedQuantity = manufacturePlanViewModel.getPlanRequestedQuantity();
        planStatus = manufacturePlanViewModel.getPlanStatus();
        planNote = manufacturePlanViewModel.getPlanNote();
        planSam = manufacturePlanViewModel.getPlanSam();
        planLotNumber = manufacturePlanViewModel.getPlanLotNumber();

        bandId = manufacturePlanViewModel.getBandId();
        bandName = manufacturePlanViewModel.getBandName();
        bandIndex = manufacturePlanViewModel.getBandIndex();

        spId = manufacturePlanViewModel.getSpId();
        spMaSanPham = manufacturePlanViewModel.getSpMaSanPham();
        spMaDonHang = manufacturePlanViewModel.getSpMaDonHang();
        spPo = manufacturePlanViewModel.getSpPo();
        spColor = manufacturePlanViewModel.getSpColor();
        spSoLuongSanXuat = manufacturePlanViewModel.getSpSoLuongSanXuat();
        spKhachHangId = manufacturePlanViewModel.getSpKhachHangId();
        spTenMuaId = manufacturePlanViewModel.getSpTenMuaId();
        tongHangDaKiem = manufacturePlanViewModel.getSlDaKiem();
        if (manufacturePlanViewModel.getSpFrontImgUrl() == null || manufacturePlanViewModel.getSpFrontImgUrl().isEmpty()) {
            spFrontImgUrl = storageProperties.getHostUrl() + "/" + ConstUtil.DEFAULT_IMAGE;
        } else {
            spFrontImgUrl = storageProperties.getHostUrl() + "/" + manufacturePlanViewModel.getSpFrontImgUrl();
        }

        if (manufacturePlanViewModel.getSpBehindImgUrl() == null || manufacturePlanViewModel.getSpBehindImgUrl().isEmpty()) {
            spBehindImgUrl = storageProperties.getHostUrl() + "/" + ConstUtil.DEFAULT_IMAGE;
        } else {
            spBehindImgUrl = storageProperties.getHostUrl() + "/" + manufacturePlanViewModel.getSpBehindImgUrl();
        }

        khTen = manufacturePlanViewModel.getKhTen();
        tenMua = manufacturePlanViewModel.getTenMua();
        canhBaoChatLuong = manufacturePlanViewModel.getCanhBaoChatLuong();
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Instant getPlanManufactureDate() {
        return planManufactureDate;
    }

    public void setPlanManufactureDate(Instant planManufactureDate) {
        this.planManufactureDate = planManufactureDate;
    }

    public Long getPlanRequestedQuantity() {
        return planRequestedQuantity;
    }

    public void setPlanRequestedQuantity(Long planRequestedQuantity) {
        this.planRequestedQuantity = planRequestedQuantity;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanNote() {
        return planNote;
    }

    public void setPlanNote(String planNote) {
        this.planNote = planNote;
    }

    public Long getPlanSam() {
        return planSam;
    }

    public void setPlanSam(Long planSam) {
        this.planSam = planSam;
    }

    public Long getPlanLotNumber() {
        return planLotNumber;
    }

    public void setPlanLotNumber(Long planLotNumber) {
        this.planLotNumber = planLotNumber;
    }

    public Long getBandId() {
        return bandId;
    }

    public void setBandId(Long bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Long getBandIndex() {
        return bandIndex;
    }

    public void setBandIndex(Long bandIndex) {
        this.bandIndex = bandIndex;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public String getSpMaSanPham() {
        return spMaSanPham;
    }

    public void setSpMaSanPham(String spMaSanPham) {
        this.spMaSanPham = spMaSanPham;
    }

    public String getSpMaDonHang() {
        return spMaDonHang;
    }

    public void setSpMaDonHang(String spMaDonHang) {
        this.spMaDonHang = spMaDonHang;
    }

    public String getSpPo() {
        return spPo;
    }

    public void setSpPo(String spPo) {
        this.spPo = spPo;
    }

    public String getSpColor() {
        return spColor;
    }

    public void setSpColor(String spColor) {
        this.spColor = spColor;
    }

    public Long getSpSoLuongSanXuat() {
        return spSoLuongSanXuat;
    }

    public void setSpSoLuongSanXuat(Long spSoLuongSanXuat) {
        this.spSoLuongSanXuat = spSoLuongSanXuat;
    }

    public Long getSpKhachHangId() {
        return spKhachHangId;
    }

    public void setSpKhachHangId(Long spKhachHangId) {
        this.spKhachHangId = spKhachHangId;
    }

    public Long getSpTenMuaId() {
        return spTenMuaId;
    }

    public void setSpTenMuaId(Long spTenMuaId) {
        this.spTenMuaId = spTenMuaId;
    }

    public String getSpFrontImgUrl() {
        return spFrontImgUrl;
    }

    public void setSpFrontImgUrl(String spFrontImgUrl) {
        this.spFrontImgUrl = spFrontImgUrl;
    }

    public String getSpBehindImgUrl() {
        return spBehindImgUrl;
    }

    public void setSpBehindImgUrl(String spBehindImgUrl) {
        this.spBehindImgUrl = spBehindImgUrl;
    }

    public String getKhTen() {
        return khTen;
    }

    public void setKhTen(String khTen) {
        this.khTen = khTen;
    }

    public String getTenMua() {
        return tenMua;
    }

    public void setTenMua(String tenMua) {
        this.tenMua = tenMua;
    }

    public Integer getTongHangDaKiem() {
        return tongHangDaKiem;
    }

    public void setTongHangDaKiem(Integer tongHangDaKiem) {
        this.tongHangDaKiem = tongHangDaKiem;
    }

    public String getCanhBaoChatLuong() {
        return canhBaoChatLuong;
    }

    public void setCanhBaoChatLuong(String canhBaoChatLuong) {
        this.canhBaoChatLuong = canhBaoChatLuong;
    }
}
