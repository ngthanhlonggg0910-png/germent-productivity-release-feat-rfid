package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.DoKimMd} entity.
 */
public class DoKimMdDTO implements Serializable {

    private Long id;

    private Float value;

    private String display;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoKimMdDTO doKimMdDTO = (DoKimMdDTO) o;
        if (doKimMdDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doKimMdDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DoKimMdDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", display='" + getDisplay() + "'" +
            "}";
    }
}
