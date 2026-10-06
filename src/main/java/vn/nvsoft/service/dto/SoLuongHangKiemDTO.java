package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SoLuongHangKiem} entity.
 */
public class SoLuongHangKiemDTO implements Serializable {

    private Long id;

    private Integer soLuong;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SoLuongHangKiemDTO soLuongHangKiemDTO = (SoLuongHangKiemDTO) o;
        if (soLuongHangKiemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soLuongHangKiemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SoLuongHangKiemDTO{" +
            "id=" + getId() +
            ", soLuong=" + getSoLuong() +
            "}";
    }
}
