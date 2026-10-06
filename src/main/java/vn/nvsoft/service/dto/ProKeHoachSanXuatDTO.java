package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProKeHoachSanXuat} entity.
 */
public class ProKeHoachSanXuatDTO implements Serializable {

    private Long id;

    private Instant ngaySanXuat;

    private Integer soLuongYeuCau;

    private Integer tinhTrang;

    private String ghiChu;

    private Integer sam;

    private Integer lotNo;

    private Boolean isActived;

    private Instant activeTime;

//    private Boolean isCompleted;
//    private Instant completeTime;
    private Long proChuyenId;

    private String proChuyenTen;

    private Long proSanPhamId;

    private String proSanPhamMaSanPham;

    private Integer tongMayRa = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActived() {
        return isActived;
    }

    public void setIsActived(Boolean actived) {
        isActived = actived;
    }

    public Instant getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Instant activeTime) {
        this.activeTime = activeTime;
    }

//    public Boolean getIsCompleted() {
//        return isCompleted;
//    }
//
//    public void setIsCompleted(Boolean completed) {
//        isCompleted = completed;
//    }
//
//    public Instant getCompleteTime() {
//        return completeTime;
//    }
//
//    public void setCompleteTime(Instant completeTime) {
//        this.completeTime = completeTime;
//    }

    public Instant getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Integer getSoLuongYeuCau() {
        return soLuongYeuCau;
    }

    public void setSoLuongYeuCau(Integer soLuongYeuCau) {
        this.soLuongYeuCau = soLuongYeuCau;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getSam() {
        return sam;
    }

    public void setSam(Integer sam) {
        this.sam = sam;
    }

    public Integer getLotNo() {
        return lotNo;
    }

    public void setLotNo(Integer lotNo) {
        this.lotNo = lotNo;
    }

    public Long getProChuyenId() {
        return proChuyenId;
    }

    public void setProChuyenId(Long proChuyenId) {
        this.proChuyenId = proChuyenId;
    }

    public String getProChuyenTen() {
        return proChuyenTen;
    }

    public void setProChuyenTen(String proChuyenTen) {
        this.proChuyenTen = proChuyenTen;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public String getProSanPhamMaSanPham() {
        return proSanPhamMaSanPham;
    }

    public void setProSanPhamMaSanPham(String proSanPhamMaSanPham) {
        this.proSanPhamMaSanPham = proSanPhamMaSanPham;
    }

    public Integer getTongMayRa() {
        return tongMayRa;
    }

    public void setTongMayRa(Integer tongMayRa) {
        this.tongMayRa = tongMayRa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO = (ProKeHoachSanXuatDTO) o;
        if (proKeHoachSanXuatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proKeHoachSanXuatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProKeHoachSanXuatDTO{" +
            "id=" + getId() +
            ", ngaySanXuat='" + getNgaySanXuat() + "'" +
            ", soLuongYeuCau=" + getSoLuongYeuCau() +
            ", tinhTrang=" + getTinhTrang() +
            ", ghiChu='" + getGhiChu() + "'" +
            ", sam=" + getSam() +
            ", lotNo=" + getLotNo() +
            ", isActived=" + getIsActived() +
            ", activeTime='" + getActiveTime() + "'" +
            ", proChuyen=" + getProChuyenId() +
            ", proChuyen='" + getProChuyenTen() + "'" +
            ", proSanPham=" + getProSanPhamId() +
            ", proSanPham='" + getProSanPhamMaSanPham() + "'" +
            "}";
    }
}
