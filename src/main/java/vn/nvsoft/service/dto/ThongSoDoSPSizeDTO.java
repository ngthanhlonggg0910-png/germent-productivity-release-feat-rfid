package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ThongSoDoSPSize} entity.
 */
public class ThongSoDoSPSizeDTO implements Serializable {

    private Long id;

    private String size;

    private String cmThongSoChuan;

    private String inchThongSoChuan;

    private Float cmThongSoChuanValue;

    private Float inchThongSoChuanValue;


    private Long thongSoDoSPId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCmThongSoChuan() {
        return cmThongSoChuan;
    }

    public void setCmThongSoChuan(String cmThongSoChuan) {
        this.cmThongSoChuan = cmThongSoChuan;
    }

    public String getInchThongSoChuan() {
        return inchThongSoChuan;
    }

    public void setInchThongSoChuan(String inchThongSoChuan) {
        this.inchThongSoChuan = inchThongSoChuan;
    }

    public Float getCmThongSoChuanValue() {
        return cmThongSoChuanValue;
    }

    public void setCmThongSoChuanValue(Float cmThongSoChuanValue) {
        this.cmThongSoChuanValue = cmThongSoChuanValue;
    }

    public Float getInchThongSoChuanValue() {
        return inchThongSoChuanValue;
    }

    public void setInchThongSoChuanValue(Float inchThongSoChuanValue) {
        this.inchThongSoChuanValue = inchThongSoChuanValue;
    }

    public Long getThongSoDoSPId() {
        return thongSoDoSPId;
    }

    public void setThongSoDoSPId(Long thongSoDoSPId) {
        this.thongSoDoSPId = thongSoDoSPId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO = (ThongSoDoSPSizeDTO) o;
        if (thongSoDoSPSizeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongSoDoSPSizeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongSoDoSPSizeDTO{" +
            "id=" + getId() +
            ", size='" + getSize() + "'" +
            ", cmThongSoChuan='" + getCmThongSoChuan() + "'" +
            ", inchThongSoChuan='" + getInchThongSoChuan() + "'" +
            ", cmThongSoChuanValue=" + getCmThongSoChuanValue() +
            ", inchThongSoChuanValue=" + getInchThongSoChuanValue() +
            ", thongSoDoSP=" + getThongSoDoSPId() +
            "}";
    }
}
