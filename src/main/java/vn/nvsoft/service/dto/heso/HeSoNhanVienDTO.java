package vn.nvsoft.service.dto.heso;

import vn.nvsoft.domain.heso.HeSoNhanVien;
import vn.nvsoft.filestorage.StorageProperties;

import java.sql.Date;

public class HeSoNhanVienDTO {
    private Long id;
    private Date ngaySanXuat;
    private Long proChuyenId;
    private Double heso;
    private String urlhinh;
    private String sothe;
    private String hoten;
    private Boolean isXacNhan;
    private String avatarName;
    private String chucVu;

    public HeSoNhanVienDTO() {
    }

    public HeSoNhanVienDTO(HeSoNhanVien heSoNhanVien, StorageProperties storageProperties) {
        this.id = heSoNhanVien.getId();
        this.ngaySanXuat = heSoNhanVien.getNgay_san_xuat();
        this.proChuyenId = heSoNhanVien.getPro_chuyen_id();
        this.heso = heSoNhanVien.getHeso();
        this.urlhinh = computeImageUrl(heSoNhanVien.getUrlhinh(), storageProperties);
        this.sothe = heSoNhanVien.getSothe();
        this.hoten = heSoNhanVien.getHoten();
        this.isXacNhan = heSoNhanVien.getXacnhan();
        this.chucVu = heSoNhanVien.getChucVu();
        computeAvatarName();
    }

    private String computeImageUrl(String urlhinh, StorageProperties storageProperties) {
        if (urlhinh != null) {
            return storageProperties.getHostUrl() + "/" + urlhinh;
        }
        return null;
    }

    private void computeAvatarName() {
        String[] tmps = hoten.trim().split(" ");
        if (tmps.length > 0) {
            avatarName = tmps[0].charAt(0) + tmps[tmps.length - 1].charAt(0) + "";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Long getProChuyenId() {
        return proChuyenId;
    }

    public void setProChuyenId(Long proChuyenId) {
        this.proChuyenId = proChuyenId;
    }

    public Double getHeso() {
        return heso;
    }

    public void setHeso(Double heso) {
        this.heso = heso;
    }

    public String getUrlhinh() {
        return urlhinh;
    }

    public void setUrlhinh(String urlhinh) {
        this.urlhinh = urlhinh;
    }

    public String getSothe() {
        return sothe;
    }

    public void setSothe(String sothe) {
        this.sothe = sothe;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Boolean getXacNhan() {
        return isXacNhan;
    }

    public void setXacNhan(Boolean xacNhan) {
        isXacNhan = xacNhan;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
