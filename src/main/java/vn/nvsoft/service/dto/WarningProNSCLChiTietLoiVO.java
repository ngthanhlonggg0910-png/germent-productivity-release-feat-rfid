package vn.nvsoft.service.dto;

import java.util.List;

public class WarningProNSCLChiTietLoiVO {
    private List<Long> proNSCLChiTietLoiIds;
    private Boolean warning;
    private Long proNSCLId;
    private String codeName;

    public WarningProNSCLChiTietLoiVO() {
    }

    public List<Long> getProNSCLChiTietLoiIds() {
        return proNSCLChiTietLoiIds;
    }

    public void setProNSCLChiTietLoiIds(List<Long> proNSCLChiTietLoiIds) {
        this.proNSCLChiTietLoiIds = proNSCLChiTietLoiIds;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public Long getProNSCLId() {
        return proNSCLId;
    }

    public void setProNSCLId(Long proNSCLId) {
        this.proNSCLId = proNSCLId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
