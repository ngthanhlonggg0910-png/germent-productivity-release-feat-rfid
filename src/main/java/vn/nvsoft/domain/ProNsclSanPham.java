package vn.nvsoft.domain;

import java.io.Serializable;
import java.time.Instant;

/**
 * Model class for pro_nscl_sanpham table.
 * Simple POJO for data mapping, not a JPA entity.
 */
public class ProNsclSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proNsclId;
    private String nguoiquet;
    private Instant thoigianquet;
    private Long proCheckpointId;
    private Long proKhsxId;
    private String checkedType;
    private String displayname;
    private Long proNsclChiTietLoiId;
    private String checkedTime;
    private Boolean isTracking;
    private String valueHEX;

    public ProNsclSanPham() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProNsclId() {
        return proNsclId;
    }

    public void setProNsclId(Long proNsclId) {
        this.proNsclId = proNsclId;
    }

    public String getNguoiquet() {
        return nguoiquet;
    }

    public void setNguoiquet(String nguoiquet) {
        this.nguoiquet = nguoiquet;
    }

    public Instant getThoigianquet() {
        return thoigianquet;
    }

    public void setThoigianquet(Instant thoigianquet) {
        this.thoigianquet = thoigianquet;
    }

    public Long getProCheckpointId() {
        return proCheckpointId;
    }

    public void setProCheckpointId(Long proCheckpointId) {
        this.proCheckpointId = proCheckpointId;
    }

    public Long getProKhsxId() {
        return proKhsxId;
    }

    public void setProKhsxId(Long proKhsxId) {
        this.proKhsxId = proKhsxId;
    }

    public String getCheckedType() {
        return checkedType;
    }

    public void setCheckedType(String checkedType) {
        this.checkedType = checkedType;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Long getProNsclChiTietLoiId() {
        return proNsclChiTietLoiId;
    }

    public void setProNsclChiTietLoiId(Long proNsclChiTietLoiId) {
        this.proNsclChiTietLoiId = proNsclChiTietLoiId;
    }

    public String getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(String checkedTime) {
        this.checkedTime = checkedTime;
    }

    public Boolean getIsTracking() {
        return isTracking;
    }

    public void setIsTracking(Boolean isTracking) {
        this.isTracking = isTracking;
    }

    public String getValueHEX() {
        return valueHEX;
    }

    public void setValueHEX(String valueHEX) {
        this.valueHEX = valueHEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNsclSanPham)) {
            return false;
        }
        return id != null && id.equals(((ProNsclSanPham) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProNsclSanPham{" +
            "id=" + id +
            ", proNsclId=" + proNsclId +
            ", nguoiquet='" + nguoiquet + '\'' +
            ", thoigianquet=" + thoigianquet +
            ", proCheckpointId=" + proCheckpointId +
            ", proKhsxId=" + proKhsxId +
            ", checkedType='" + checkedType + '\'' +
            ", displayname='" + displayname + '\'' +
            ", proNsclChiTietLoiId=" + proNsclChiTietLoiId +
            ", checkedTime='" + checkedTime + '\'' +
            ", isTracking=" + isTracking +
            ", valueHEX='" + valueHEX + '\'' +
            '}';
    }
}
