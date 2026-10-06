package vn.nvsoft.service.dto;

import java.util.Date;
import java.util.List;

public class RefinalCCCreateInfoDTO {
    private Date ngayGioKiem = new Date();
    private List<String> doKimMds;
    private List<String> aqls;
    private List<ProAqlDTO> proAqls;
    private List<DoAmDTO> doAms;
    private List<Integer> soLuongPoKiems;

    public RefinalCCCreateInfoDTO() {
    }

    public Date getNgayGioKiem() {
        return ngayGioKiem;
    }

    public void setNgayGioKiem(Date ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public List<String> getDoKimMds() {
        return doKimMds;
    }

    public void setDoKimMds(List<String> doKimMds) {
        this.doKimMds = doKimMds;
    }

    public List<String> getAqls() {
        return aqls;
    }

    public void setAqls(List<String> aqls) {
        this.aqls = aqls;
    }

    public List<ProAqlDTO> getProAqls() {
        return proAqls;
    }

    public void setProAqls(List<ProAqlDTO> proAqls) {
        this.proAqls = proAqls;
    }

    public List<DoAmDTO> getDoAms() {
        return doAms;
    }

    public void setDoAms(List<DoAmDTO> doAms) {
        this.doAms = doAms;
    }

    public List<Integer> getSoLuongPoKiems() {
        return soLuongPoKiems;
    }

    public void setSoLuongPoKiems(List<Integer> soLuongPoKiems) {
        this.soLuongPoKiems = soLuongPoKiems;
    }
}
