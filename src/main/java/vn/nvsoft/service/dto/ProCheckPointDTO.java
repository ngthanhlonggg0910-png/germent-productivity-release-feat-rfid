package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProCheckPoint} entity.
 */
public class ProCheckPointDTO implements Serializable {

    private Long id;

    private String ten;


    private Long proChuyenId;

    private String proChuyenTen;

    private Long proCongDoanId;

    private String proCongDoanTen;

    private String type;

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

    public Long getProCongDoanId() {
        return proCongDoanId;
    }

    public void setProCongDoanId(Long proCongDoanId) {
        this.proCongDoanId = proCongDoanId;
    }

    public String getProCongDoanTen() {
        return proCongDoanTen;
    }

    public void setProCongDoanTen(String proCongDoanTen) {
        this.proCongDoanTen = proCongDoanTen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProCheckPointDTO proCheckPointDTO = (ProCheckPointDTO) o;
        if (proCheckPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proCheckPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProCheckPointDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", proChuyen=" + getProChuyenId() +
            ", proChuyen='" + getProChuyenTen() + "'" +
            ", proCongDoan=" + getProCongDoanId() +
            ", proCongDoan='" + getProCongDoanTen() + "'" +
            "}";
    }
}
