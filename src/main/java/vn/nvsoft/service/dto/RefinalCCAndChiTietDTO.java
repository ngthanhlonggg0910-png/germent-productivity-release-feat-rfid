package vn.nvsoft.service.dto;

import java.util.List;

public class RefinalCCAndChiTietDTO {
    private ProRefinalCCDTO proRefinalCCDTO;
    private List<ProRefinalCCChiTietLoiDTO> proRefinalCCChiTietLoiDTO;
    public RefinalCCAndChiTietDTO() {
    }

    public ProRefinalCCDTO getProRefinalCCDTO() {
        return proRefinalCCDTO;
    }

    public void setProRefinalCCDTO(ProRefinalCCDTO proRefinalCCDTO) {
        this.proRefinalCCDTO = proRefinalCCDTO;
    }

    public List<ProRefinalCCChiTietLoiDTO> getProRefinalCCChiTietLoiDTO() {
        return proRefinalCCChiTietLoiDTO;
    }

    public void setProRefinalCCChiTietLoiDTO(List<ProRefinalCCChiTietLoiDTO> proRefinalCCChiTietLoiDTO) {
        this.proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiDTO;
    }
}
