package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProRefinalCCChiTietLoi} entity.
 */
public class ProRefinalCCChiTietLoiDTO implements Serializable {

    private Long id;

    private String maLoi;

    private Instant thoiGian;

    private Double dx;

    private Double dy;

    private Boolean isFront;

    private Boolean isInside;

    private Boolean warning;

    private String nguoiKiem;


    private Long proRefinalCCId;

    private Long proLoiId;

    private ProLoiDTO proLoi;

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

    public String getNguoiKiem() {
        return nguoiKiem;
    }

    public void setNguoiKiem(String nguoiKiem) {
        this.nguoiKiem = nguoiKiem;
    }

    public Long getProRefinalCCId() {
        return proRefinalCCId;
    }

    public void setProRefinalCCId(Long proRefinalCCId) {
        this.proRefinalCCId = proRefinalCCId;
    }

    public Long getProLoiId() {
        return proLoiId;
    }

    public void setProLoiId(Long proLoiId) {
        this.proLoiId = proLoiId;
    }

    public ProLoiDTO getProLoi() {
        return proLoi;
    }

    public void setProLoi(ProLoiDTO proLoi) {
        this.proLoi = proLoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO = (ProRefinalCCChiTietLoiDTO) o;
        if (proRefinalCCChiTietLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proRefinalCCChiTietLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProRefinalCCChiTietLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", thoiGian='" + getThoiGian() + "'" +
            ", dx=" + getDx() +
            ", dy=" + getDy() +
            ", isFront='" + isIsFront() + "'" +
            ", isInside='" + isIsInside() + "'" +
            ", warning='" + isWarning() + "'" +
            ", nguoiKiem='" + getNguoiKiem() + "'" +
            ", proRefinalCC=" + getProRefinalCCId() +
            ", proLoi=" + getProLoiId() +
            "}";
    }
}
