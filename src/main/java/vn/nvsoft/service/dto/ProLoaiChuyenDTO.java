package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProLoaiChuyen} entity.
 */
public class ProLoaiChuyenDTO implements Serializable {

    private Long id;

    private String ten;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProLoaiChuyenDTO proLoaiChuyenDTO = (ProLoaiChuyenDTO) o;
        if (proLoaiChuyenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proLoaiChuyenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProLoaiChuyenDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
