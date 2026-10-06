package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.DungSai} entity.
 */
public class DungSaiDTO implements Serializable {

    private Long id;

    private String cmLabel;

    private String inchLabel;

    private Float cmValue;

    private Float inchValue;

    private Boolean khongLoadTablet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmLabel() {
        return cmLabel;
    }

    public void setCmLabel(String cmLabel) {
        this.cmLabel = cmLabel;
    }

    public String getInchLabel() {
        return inchLabel;
    }

    public void setInchLabel(String inchLabel) {
        this.inchLabel = inchLabel;
    }

    public Float getCmValue() {
        return cmValue;
    }

    public void setCmValue(Float cmValue) {
        this.cmValue = cmValue;
    }

    public Float getInchValue() {
        return inchValue;
    }

    public void setInchValue(Float inchValue) {
        this.inchValue = inchValue;
    }

    public Boolean getKhongLoadTablet() {
        return khongLoadTablet;
    }

    public void setKhongLoadTablet(Boolean khongLoadTablet) {
        this.khongLoadTablet = khongLoadTablet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DungSaiDTO dungSaiDTO = (DungSaiDTO) o;
        if (dungSaiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dungSaiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DungSaiDTO{" +
            "id=" + getId() +
            ", cmLabel='" + getCmLabel() + "'" +
            ", inchLabel='" + getInchLabel() + "'" +
            ", cmValue=" + getCmValue() +
            ", inchValue=" + getInchValue() +
            "}";
    }
}
