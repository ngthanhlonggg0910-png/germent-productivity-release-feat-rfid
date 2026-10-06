package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;
/**
 * A DTO for the {@link vn.nvsoft.domain.ProRefinalTheoGioDTO} entity.
 */
public class ProRefinalTheoGioDTO implements Serializable {

    private String caption;
    private Integer soLuongLoi;


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    public Integer getSoLuongLoi() {
        return soLuongLoi;
    }

    public void setSoLuongLoi(Integer soLuongLoi) {
        this.soLuongLoi = soLuongLoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProRefinalTheoGioDTO proRefinalTheoGioDTO = (ProRefinalTheoGioDTO) o;
        if (proRefinalTheoGioDTO.getCaption() == null || getCaption() == null) {
            return false;
        }
        return Objects.equals(getCaption(), proRefinalTheoGioDTO.getCaption());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCaption());
    }

    @Override
    public String toString() {
        return "ProRefinalTheoGioDTO{" +
            "caption=" + getCaption() +
            ", soLuongLoi=" + getSoLuongLoi() +
            "}";
    }
}
