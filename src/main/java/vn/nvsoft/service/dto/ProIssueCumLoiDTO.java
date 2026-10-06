package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProIssueCumLoi} entity.
 */
public class ProIssueCumLoiDTO implements Serializable {

    private Long id;

    private String ten;

    private String mau;

    private Boolean toTruong;

    private Boolean kyThuat;

    private Boolean qa;

    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Boolean isToTruong() {
        return toTruong;
    }

    public void setToTruong(Boolean toTruong) {
        this.toTruong = toTruong;
    }

    public Boolean isKyThuat() {
        return kyThuat;
    }

    public void setKyThuat(Boolean kyThuat) {
        this.kyThuat = kyThuat;
    }

    public Boolean isQa() {
        return qa;
    }

    public void setQa(Boolean qa) {
        this.qa = qa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProIssueCumLoiDTO proIssueCumLoiDTO = (ProIssueCumLoiDTO) o;
        if (proIssueCumLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proIssueCumLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProIssueCumLoiDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", mau='" + getMau() + "'" +
            ", toTruong='" + isToTruong() + "'" +
            ", kyThuat='" + isKyThuat() + "'" +
            ", qa='" + isQa() + "'" +
            "}";
    }
}
