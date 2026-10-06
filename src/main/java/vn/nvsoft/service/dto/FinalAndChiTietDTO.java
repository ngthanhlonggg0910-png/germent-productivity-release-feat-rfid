package vn.nvsoft.service.dto;

import java.util.List;

public class FinalAndChiTietDTO {
    private ProFinalDTO proFinalDTO;
    private List<ProFinalChiTietLoiDTO> proFinalChiTietLoiDTOS;
    public FinalAndChiTietDTO() {
    }

    public ProFinalDTO getProFinalDTO() {
        return proFinalDTO;
    }

    public void setProFinalDTO(ProFinalDTO proFinalDTO) {
        this.proFinalDTO = proFinalDTO;
    }

    public List<ProFinalChiTietLoiDTO> getProFinalChiTietLoiDTOS() {
        return proFinalChiTietLoiDTOS;
    }

    public void setProFinalChiTietLoiDTOS(List<ProFinalChiTietLoiDTO> proFinalChiTietLoiDTOS) {
        this.proFinalChiTietLoiDTOS = proFinalChiTietLoiDTOS;
    }
}
