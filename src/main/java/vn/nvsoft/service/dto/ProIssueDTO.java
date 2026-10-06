package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProIssue} entity.
 */
public class ProIssueDTO implements Serializable {

    private Long id;

    private String maHang;

    private String pono;

    private String tenChuyen;

    private Instant ngayGioBatDau;

    private Instant ngayGioKetThuc;

    private String maLoi;

    private String moTaLoi;

    private String maMay;

    private String soTram;

    private Long proChuyenId;

    private Long proSanPhamId;

    private String nguoiTao;

    private String checkPointType;

    private Long checkPointId;

    private String checkPointName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Instant getNgayGioBatDau() {
        return ngayGioBatDau;
    }

    public void setNgayGioBatDau(Instant ngayGioBatDau) {
        this.ngayGioBatDau = ngayGioBatDau;
    }

    public Instant getNgayGioKetThuc() {
        return ngayGioKetThuc;
    }

    public void setNgayGioKetThuc(Instant ngayGioKetThuc) {
        this.ngayGioKetThuc = ngayGioKetThuc;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getSoTram() {
        return soTram;
    }

    public void setSoTram(String soTram) {
        this.soTram = soTram;
    }

    public Long getProChuyenId() {
        return proChuyenId;
    }

    public void setProChuyenId(Long proChuyenId) {
        this.proChuyenId = proChuyenId;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getCheckPointType() {
        return checkPointType;
    }

    public void setCheckPointType(String checkPointType) {
        this.checkPointType = checkPointType;
    }

    public Long getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getCheckPointName() {
        return checkPointName;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProIssueDTO proIssueDTO = (ProIssueDTO) o;
        if (proIssueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proIssueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProIssueDTO{" +
            "id=" + getId() +
            ", maHang='" + getMaHang() + "'" +
            ", pono='" + getPono() + "'" +
            ", tenChuyen='" + getTenChuyen() + "'" +
            ", ngayGioBatDau='" + getNgayGioBatDau() + "'" +
            ", ngayGioKetThuc='" + getNgayGioKetThuc() + "'" +
            ", maLoi='" + getMaLoi() + "'" +
            ", moTaLoi='" + getMoTaLoi() + "'" +
            ", maMay='" + getMaMay() + "'" +
            ", soTram='" + getSoTram() + "'" +
            ", proChuyenId=" + getProChuyenId() +
            ", proSanPhamId=" + getProSanPhamId() +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", checkPointType='" + getCheckPointType() + "'" +
            ", checkPointId=" + getCheckPointId() +
            ", checkPointName='" + getCheckPointName() + "'" +
            "}";
    }
}
