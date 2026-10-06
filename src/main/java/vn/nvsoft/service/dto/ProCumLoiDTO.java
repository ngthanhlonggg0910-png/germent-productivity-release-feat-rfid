package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProCumLoi} entity.
 */
public class ProCumLoiDTO implements Serializable {

    private Long id;

    private String ten;

    private String mau;

    private Integer count = 0;


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

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProCumLoiDTO proCumLoiDTO = (ProCumLoiDTO) o;
        if (proCumLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proCumLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProCumLoiDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", mau='" + getMau() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
