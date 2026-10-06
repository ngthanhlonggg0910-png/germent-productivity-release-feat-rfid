package vn.nvsoft.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.GioLam;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProNSCLChiTietLoi} entity.
 */
public class ProNSCLChiTietLoiDTO implements Serializable {

    private Long id;

    @NotNull
    private String maLoi;

    @NotNull
    private Instant thoiGian;

    private GioLam gioLam;

    private Double dx;

    private Double dy;

    private Boolean isFront;

    private Boolean isInside;

    private Long proNSCLId;

    private Long proLoiId;

    private ProLoiDTO proLoi;

    private String proLoiMaLoi;

    private Long checkPointID;

    private Long keHoachSanXuatID;

    private Boolean warning = Boolean.FALSE;

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

    public GioLam getGioLam() {
        return gioLam;
    }

    public void setGioLam(GioLam gioLam) {
        this.gioLam = gioLam;
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

    public Long getProNSCLId() {
        return proNSCLId;
    }

    public void setProNSCLId(Long proNSCLId) {
        this.proNSCLId = proNSCLId;
    }

    public Long getProLoiId() {
        return proLoiId;
    }

    public void setProLoiId(Long proLoiId) {
        this.proLoiId = proLoiId;
    }

    public String getProLoiMaLoi() {
        return proLoiMaLoi;
    }

    public void setProLoiMaLoi(String proLoiMaLoi) {
        this.proLoiMaLoi = proLoiMaLoi;
    }

    public Boolean getFront() {
        return isFront;
    }

    public void setFront(Boolean front) {
        isFront = front;
    }

    public Boolean getInside() {
        return isInside;
    }

    public void setInside(Boolean inside) {
        isInside = inside;
    }

    public ProLoiDTO getProLoi() {
        return proLoi;
    }

    public void setProLoi(ProLoiDTO proLoi) {
        this.proLoi = proLoi;
    }

    public Long getCheckPointID() {
        return checkPointID;
    }
    public Long getKeHoachSanXuatID() {
        return keHoachSanXuatID;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO = (ProNSCLChiTietLoiDTO) o;
        if (proNSCLChiTietLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proNSCLChiTietLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProNSCLChiTietLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", thoiGian='" + getThoiGian() + "'" +
            ", gioLam='" + getGioLam() + "'" +
            ", dx=" + getDx() +
            ", dy=" + getDy() +
            ", isFront='" + isIsFront() + "'" +
            ", isInside='" + isIsInside() + "'" +
            ", proNSCL=" + getProNSCLId() +
            ", proLoi=" + getProLoiId() +
            ", proLoi='" + getProLoiMaLoi() + "'" +
            "}";
    }
}
