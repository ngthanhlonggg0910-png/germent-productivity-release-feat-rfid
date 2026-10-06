package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProNhomCongDoan} entity.
 */
public class ProNhomCongDoanDTO implements Serializable {

    private Long id;

    private String ten;

    private Boolean checkNangSuat;


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

    public Boolean isCheckNangSuat() {
        return checkNangSuat;
    }

    public void setCheckNangSuat(Boolean checkNangSuat) {
        this.checkNangSuat = checkNangSuat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProNhomCongDoanDTO proNhomCongDoanDTO = (ProNhomCongDoanDTO) o;
        if (proNhomCongDoanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proNhomCongDoanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProNhomCongDoanDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", checkNangSuat='" + isCheckNangSuat() + "'" +
            "}";
    }
}
