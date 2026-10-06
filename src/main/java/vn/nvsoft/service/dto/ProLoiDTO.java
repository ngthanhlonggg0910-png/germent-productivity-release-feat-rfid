package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProLoi} entity.
 */
public class ProLoiDTO implements Serializable {

    private Long id;

    private String maLoi;

    private String cusCode;

    private String tenLoi;

    private String tenTiengAnh;

    private String label;

    private Integer stt;

    private String mauSac;

    private Boolean qc;

    private Boolean qa;

    private Boolean checked;

    private String ghiChu;


    private Long proCumLoiId;

    private String proCumLoiTen;

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

    public Boolean isQc() {
        return qc;
    }

    public void setQc(Boolean qc) {
        this.qc = qc;
    }

    public Boolean isQa() {
        return qa;
    }

    public void setQa(Boolean qa) {
        this.qa = qa;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Long getProCumLoiId() {
        return proCumLoiId;
    }

    public void setProCumLoiId(Long proCumLoiId) {
        this.proCumLoiId = proCumLoiId;
    }

    public String getProCumLoiTen() {
        return proCumLoiTen;
    }

    public void setProCumLoiTen(String proCumLoiTen) {
        this.proCumLoiTen = proCumLoiTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProLoiDTO proLoiDTO = (ProLoiDTO) o;
        if (proLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", cusCode='" + getCusCode() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            ", label='" + getLabel() + "'" +
            ", stt=" + getStt() +
            ", mauSac='" + getMauSac() + "'" +
            ", qc='" + isQc() + "'" +
            ", qa='" + isQa() + "'" +
            ", checked='" + isChecked() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", proCumLoi=" + getProCumLoiId() +
            ", proCumLoi='" + getProCumLoiTen() + "'" +
            "}";
    }
}
