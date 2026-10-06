package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProIssueLoi} entity.
 */
public class ProIssueLoiDTO implements Serializable {

    private Long id;

    private String maLoi;

    private String cusCode;

    private String tenLoi;

    private String tenTiengAnh;

    private String label;

    private Integer stt;

    private String mauSac;

    private String ghiChu;


    private Long proIssueCumLoiId;

    private String proIssueCumLoiTen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getTenTiengAnh() {
        return tenTiengAnh;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Long getProIssueCumLoiId() {
        return proIssueCumLoiId;
    }

    public void setProIssueCumLoiId(Long proIssueCumLoiId) {
        this.proIssueCumLoiId = proIssueCumLoiId;
    }

    public String getProIssueCumLoiTen() {
        return proIssueCumLoiTen;
    }

    public void setProIssueCumLoiTen(String proIssueCumLoiTen) {
        this.proIssueCumLoiTen = proIssueCumLoiTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProIssueLoiDTO proIssueLoiDTO = (ProIssueLoiDTO) o;
        if (proIssueLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proIssueLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProIssueLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", cusCode='" + getCusCode() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            ", label='" + getLabel() + "'" +
            ", stt=" + getStt() +
            ", mauSac='" + getMauSac() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", proIssueCumLoi=" + getProIssueCumLoiId() +
            ", proIssueCumLoi='" + getProIssueCumLoiTen() + "'" +
            "}";
    }
}
