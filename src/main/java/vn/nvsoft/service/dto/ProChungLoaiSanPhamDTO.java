package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProChungLoaiSanPham} entity.
 */
public class ProChungLoaiSanPhamDTO implements Serializable {

    private Long id;

    private String tenChungLoai;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenChungLoai() {
        return tenChungLoai;
    }

    public void setTenChungLoai(String tenChungLoai) {
        this.tenChungLoai = tenChungLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO = (ProChungLoaiSanPhamDTO) o;
        if (proChungLoaiSanPhamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proChungLoaiSanPhamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProChungLoaiSanPhamDTO{" +
            "id=" + getId() +
            ", tenChungLoai='" + getTenChungLoai() + "'" +
            "}";
    }
}
