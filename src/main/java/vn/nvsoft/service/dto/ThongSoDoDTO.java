package vn.nvsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ThongSoDo} entity.
 */
public class ThongSoDoDTO implements Serializable {

    private Long id;

    
    private String code;

    private String ten;

    private String quyCach;

    private Long dungSaiId;

    private String dungSaiInchLabel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Long getDungSaiId() {
        return dungSaiId;
    }

    public void setDungSaiId(Long dungSaiId) {
        this.dungSaiId = dungSaiId;
    }

    public String getDungSaiInchLabel() {
        return dungSaiInchLabel;
    }

    public void setDungSaiInchLabel(String dungSaiInchLabel) {
        this.dungSaiInchLabel = dungSaiInchLabel;
    }

    public String getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(String quyCach) {
        this.quyCach = quyCach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThongSoDoDTO thongSoDoDTO = (ThongSoDoDTO) o;
        if (thongSoDoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongSoDoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongSoDoDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", ten='" + getTen() + "'" +
            ", dungSai=" + getDungSaiId() +
            ", dungSai='" + getDungSaiInchLabel() + "'" +
            "}";
    }
}
