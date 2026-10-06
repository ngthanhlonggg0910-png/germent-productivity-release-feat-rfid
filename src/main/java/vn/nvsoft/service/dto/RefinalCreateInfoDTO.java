package vn.nvsoft.service.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class RefinalCreateInfoDTO {
    private Long proKHSXId;
    private Long checkPointID;
    private Long proNSCLId;
    private Integer hangDatHienTai;
    private Date gioKiem = new Date();
    List<SoLuongHangKiemDTO> soLuongHangKiems;
    List<DoAmDTO> doAms;

    public RefinalCreateInfoDTO() {
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

    public Integer getHangDatHienTai() {
        return hangDatHienTai;
    }

    public void setHangDatHienTai(Integer hangDatHienTai) {
        this.hangDatHienTai = hangDatHienTai;
    }

    public List<SoLuongHangKiemDTO> getSoLuongHangKiems() {
        return soLuongHangKiems;
    }

    public void setSoLuongHangKiems(List<SoLuongHangKiemDTO> soLuongHangKiems) {
        this.soLuongHangKiems = soLuongHangKiems;
    }

    public List<DoAmDTO> getDoAms() {
        return doAms;
    }

    public void setDoAms(List<DoAmDTO> doAms) {
        this.doAms = doAms;
    }

    public Date getGioKiem() {
        return gioKiem;
    }

    public void setGioKiem(Date gioKiem) {
        this.gioKiem = gioKiem;
    }
}
