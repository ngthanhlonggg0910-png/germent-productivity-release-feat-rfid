package vn.nvsoft.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.GioLam;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProGioLamViec} entity.
 */
public class ProGioLamViecDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant gioBatDau;

    @NotNull
    private Instant gioKetThuc;

    private GioLam gioLam;

    private Integer thoiGian;

    private String captionTV;

    private String chuyen;

    private Boolean thuBay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Instant gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Instant getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Instant gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public GioLam getGioLam() {
        return gioLam;
    }

    public void setGioLam(GioLam gioLam) {
        this.gioLam = gioLam;
    }

    public Integer getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Integer thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getCaptionTV() {
        return captionTV;
    }

    public void setCaptionTV(String captionTV) {
        this.captionTV = captionTV;
    }

    public String getChuyen() {
        return chuyen;
    }

    public void setChuyen(String chuyen) {
        this.chuyen = chuyen;
    }

    public Boolean getThuBay() {
        return thuBay;
    }

    public void setThuBay(Boolean thuBay) {
        this.thuBay = thuBay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProGioLamViecDTO proGioLamViecDTO = (ProGioLamViecDTO) o;
        if (proGioLamViecDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proGioLamViecDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProGioLamViecDTO{" +
            "id=" + getId() +
            ", gioBatDau='" + getGioBatDau() + "'" +
            ", gioKetThuc='" + getGioKetThuc() + "'" +
            ", gioLam='" + getGioLam() + "'" +
            ", thoiGian=" + getThoiGian() +
            ", captionTV='" + getCaptionTV() + "'" +
            "}";
    }
}
