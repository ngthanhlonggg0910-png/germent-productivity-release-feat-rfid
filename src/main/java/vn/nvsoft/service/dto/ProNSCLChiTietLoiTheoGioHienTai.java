package vn.nvsoft.service.dto;

import java.util.List;

public class ProNSCLChiTietLoiTheoGioHienTai {
    private String urlHinhMatTruoc;
    private String urlHinhMatSau;
    private List<ProNSCLChiTietLoiDTO> chiTietLoiDTOS;
    private List<TopLoi> topLois;

    public ProNSCLChiTietLoiTheoGioHienTai() {
    }

    public String getUrlHinhMatTruoc() {
        return urlHinhMatTruoc;
    }

    public void setUrlHinhMatTruoc(String urlHinhMatTruoc) {
        this.urlHinhMatTruoc = urlHinhMatTruoc;
    }

    public String getUrlHinhMatSau() {
        return urlHinhMatSau;
    }

    public void setUrlHinhMatSau(String urlHinhMatSau) {
        this.urlHinhMatSau = urlHinhMatSau;
    }

    public List<ProNSCLChiTietLoiDTO> getChiTietLoiDTOS() {
        return chiTietLoiDTOS;
    }

    public void setChiTietLoiDTOS(List<ProNSCLChiTietLoiDTO> chiTietLoiDTOS) {
        this.chiTietLoiDTOS = chiTietLoiDTOS;
    }

    public List<TopLoi> getTopLois() {
        return topLois;
    }

    public void setTopLois(List<TopLoi> topLois) {
        this.topLois = topLois;
    }
}
