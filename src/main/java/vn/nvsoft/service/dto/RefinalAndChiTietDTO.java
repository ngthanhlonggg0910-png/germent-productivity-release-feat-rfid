package vn.nvsoft.service.dto;

import java.util.List;

public class RefinalAndChiTietDTO {
    private ProRefinalDTO m_proRefinalDTO;
    private List<ProRefinalChiTietLoiDTO> m_proRefinalChiTietLoiDTO;
    public RefinalAndChiTietDTO() {
    }

    public ProRefinalDTO getProRefinalDTO() {
        return m_proRefinalDTO;
    }

    public void setProRefinalDTO(ProRefinalDTO proRefinalDTO) {
        m_proRefinalDTO = proRefinalDTO;
    }

    public List<ProRefinalChiTietLoiDTO> getProRefinalChiTietLoiDTO() {
        return m_proRefinalChiTietLoiDTO;
    }

    public void setProRefinalChiTietLoiDTO(List<ProRefinalChiTietLoiDTO> proRefinalChiTietLoiDTO) {
        m_proRefinalChiTietLoiDTO = proRefinalChiTietLoiDTO;
    }
}
