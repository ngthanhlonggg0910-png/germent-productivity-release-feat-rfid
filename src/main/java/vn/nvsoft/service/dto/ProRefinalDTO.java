package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProRefinal} entity.
 */
public class ProRefinalDTO implements Serializable {

    private Long id;

    private Long proKHSXId;

    private Long checkPointID;

    private Long proNSCLId;

    private Date ngayGioKiem;

    private Integer soLuongKiem;

    private Integer hangDatHienTai;

    private Float doAm;

    private Integer soLoi;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProKHSXId() {
        return proKHSXId;
    }

    public void setProKHSXId(Long proKHSXId) {
        this.proKHSXId = proKHSXId;
    }

    public Long getCheckPointID() {
        return checkPointID;
    }

    public void setCheckPointID(Long checkPointID) {
        this.checkPointID = checkPointID;
    }

    public Long getProNSCLId() {
        return proNSCLId;
    }

    public void setProNSCLId(Long proNSCLId) {
        this.proNSCLId = proNSCLId;
    }

    public Date getNgayGioKiem() {
        return ngayGioKiem;
    }

    public void setNgayGioKiem(Date ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public Integer getSoLuongKiem() {
        return soLuongKiem;
    }

    public void setSoLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
    }

    public Integer getHangDatHienTai() {
        return hangDatHienTai;
    }

    public void setHangDatHienTai(Integer hangDatHienTai) {
        this.hangDatHienTai = hangDatHienTai;
    }

    public Float getDoAm() {
        return doAm;
    }

    public void setDoAm(Float doAm) {
        this.doAm = doAm;
    }

    public Integer getSoLoi() {
        return soLoi;
    }

    public void setSoLoi(Integer soLoi) {
        this.soLoi = soLoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProRefinalDTO proRefinalDTO = (ProRefinalDTO) o;
        if (proRefinalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proRefinalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProRefinalDTO{" +
            "id=" + getId() +
            ", proKHSXId=" + getProKHSXId() +
            ", checkPointID=" + getCheckPointID() +
            ", proNSCLId=" + getProNSCLId() +
            ", ngayGioKiem='" + getNgayGioKiem() + "'" +
            ", soLuongKiem=" + getSoLuongKiem() +
            ", hangDatHienTai=" + getHangDatHienTai() +
            ", doAm=" + getDoAm() +
            "}";
    }
}
