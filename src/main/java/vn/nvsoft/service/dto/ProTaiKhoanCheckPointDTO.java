package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProTaiKhoanCheckPoint} entity.
 */
public class ProTaiKhoanCheckPointDTO implements Serializable {

    private Long id;

    private String taiKhoan;

    private Boolean checked;


    private Long proCheckPointId;

    private String proCheckPointTen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getProCheckPointId() {
        return proCheckPointId;
    }

    public void setProCheckPointId(Long proCheckPointId) {
        this.proCheckPointId = proCheckPointId;
    }

    public String getProCheckPointTen() {
        return proCheckPointTen;
    }

    public void setProCheckPointTen(String proCheckPointTen) {
        this.proCheckPointTen = proCheckPointTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO = (ProTaiKhoanCheckPointDTO) o;
        if (proTaiKhoanCheckPointDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proTaiKhoanCheckPointDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProTaiKhoanCheckPointDTO{" +
            "id=" + getId() +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", checked='" + isChecked() + "'" +
            ", proCheckPoint=" + getProCheckPointId() +
            ", proCheckPoint='" + getProCheckPointTen() + "'" +
            "}";
    }
}
