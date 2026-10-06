package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProCongDoan} entity.
 */
public class ProCongDoanDTO implements Serializable {

    private Long id;

    private String ten;

    private String nhomCongDoan;


    private Long proNhomCongDoanId;

    private String proNhomCongDoanTen;

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

    public String getNhomCongDoan() {
        return nhomCongDoan;
    }

    public void setNhomCongDoan(String nhomCongDoan) {
        this.nhomCongDoan = nhomCongDoan;
    }

    public Long getProNhomCongDoanId() {
        return proNhomCongDoanId;
    }

    public void setProNhomCongDoanId(Long proNhomCongDoanId) {
        this.proNhomCongDoanId = proNhomCongDoanId;
    }

    public String getProNhomCongDoanTen() {
        return proNhomCongDoanTen;
    }

    public void setProNhomCongDoanTen(String proNhomCongDoanTen) {
        this.proNhomCongDoanTen = proNhomCongDoanTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProCongDoanDTO proCongDoanDTO = (ProCongDoanDTO) o;
        if (proCongDoanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proCongDoanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProCongDoanDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", nhomCongDoan='" + getNhomCongDoan() + "'" +
            ", proNhomCongDoan=" + getProNhomCongDoanId() +
            ", proNhomCongDoan='" + getProNhomCongDoanTen() + "'" +
            "}";
    }
}
