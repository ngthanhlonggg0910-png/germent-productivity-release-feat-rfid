package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProRefinalChiTietLoi} entity.
 */
public class ProRefinalChiTietLoiDTO implements Serializable {

    private Long id;

    private String maLoi;

    private Instant thoiGian;

    private Double dx;

    private Double dy;

    private Boolean isFront;

    private Boolean isInside;

    private Boolean warning;


    private Long proRefinalId;

    private Long proLoiId;

    private ProLoiDTO m_proLoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public Instant getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Double getDx() {
        return dx;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getDy() {
        return dy;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Boolean isIsFront() {
        return isFront;
    }

    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    public Boolean isIsInside() {
        return isInside;
    }

    public void setIsInside(Boolean isInside) {
        this.isInside = isInside;
    }

    public Boolean isWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public Long getProRefinalId() {
        return proRefinalId;
    }

    public void setProRefinalId(Long proRefinalId) {
        this.proRefinalId = proRefinalId;
    }

    public Long getProLoiId() {
        return proLoiId;
    }

    public void setProLoiId(Long proLoiId) {
        this.proLoiId = proLoiId;
    }

    public ProLoiDTO getProLoi() {
        return m_proLoi;
    }

    public void setProLoi(ProLoiDTO proLoi) {
        m_proLoi = proLoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO = (ProRefinalChiTietLoiDTO) o;
        if (proRefinalChiTietLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proRefinalChiTietLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProRefinalChiTietLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", thoiGian='" + getThoiGian() + "'" +
            ", dx=" + getDx() +
            ", dy=" + getDy() +
            ", isFront='" + isIsFront() + "'" +
            ", isInside='" + isIsInside() + "'" +
            ", warning='" + isWarning() + "'" +
            ", proRefinal=" + getProRefinalId() +
            ", proLoi=" + getProLoiId() +
            "}";
    }
}
