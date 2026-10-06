package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KiemTrongSessionDTO implements Serializable {

    private Long checkpointId;
    private Long khsxId;
    private List<ProKiemtrongChiTietLoiDTO> chiTietLois = new ArrayList<>();
    private List<ProKiemtrongSanPhamDTO> sanPhams = new ArrayList<>();

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public Long getKhsxId() {
        return khsxId;
    }

    public void setKhsxId(Long khsxId) {
        this.khsxId = khsxId;
    }

    public List<ProKiemtrongChiTietLoiDTO> getChiTietLois() {
        return chiTietLois;
    }

    public void setChiTietLois(List<ProKiemtrongChiTietLoiDTO> chiTietLois) {
        this.chiTietLois = chiTietLois;
    }

    public List<ProKiemtrongSanPhamDTO> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<ProKiemtrongSanPhamDTO> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
