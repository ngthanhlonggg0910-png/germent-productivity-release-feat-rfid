package vn.nvsoft.domain;

import java.io.Serializable;
import java.time.Instant;

/**
 * POJO for pro_kiemtrong_sanpham table (not a JPA entity).
 */
public class ProKiemtrongSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proCheckpointId;
    private Long proKhsxId;
    private String nguoiquet;
    private Instant thoigianquet;
    private String checkedType;
    private String displayname;
    private Long proKiemtrongChiTietLoiId;
    private String checkedTime;
    private Boolean isTracking;
    private String valueHEX;
    private String rfidBarcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProKiemtrongChiTietLoiId() {
        return proKiemtrongChiTietLoiId;
    }

    public void setProKiemtrongChiTietLoiId(Long proKiemtrongChiTietLoiId) {
        this.proKiemtrongChiTietLoiId = proKiemtrongChiTietLoiId;
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

    public String getRfidBarcode() {
        return rfidBarcode;
    }

    public void setRfidBarcode(String rfidBarcode) {
        this.rfidBarcode = rfidBarcode;
    }
}
