package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.LibSoLuongPoKiem} entity.
 */
public class LibSoLuongPoKiemDTO implements Serializable {

    private Long id;

    private Integer value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO = (LibSoLuongPoKiemDTO) o;
        if (libSoLuongPoKiemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), libSoLuongPoKiemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LibSoLuongPoKiemDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}
