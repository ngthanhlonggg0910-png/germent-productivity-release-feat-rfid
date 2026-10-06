package vn.nvsoft.service.dto;

import java.util.List;

public class NSCLTheoGioDTO {

    private Long id;
    private Integer qty = 0;
    private Integer man = 0;
    private Integer fix = 0;
    private Integer reject = 0;
    private Float errorRate = 0F;
    private Float qualifiedRate = 0F;
    private List<ProNSCLChiTietLoiDTO> chiTietLoiDTOS;
    private ProGioLamViecDTO gioLamViecDTO;

    public NSCLTheoGioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getMan() {
        return man;
    }

    public void setMan(Integer man) {
        this.man = man;
    }

    public Integer getFix() {
        return fix;
    }

    public void setFix(Integer fix) {
        this.fix = fix;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

    public Float getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(Float errorRate) {
        this.errorRate = errorRate;
    }

    public List<ProNSCLChiTietLoiDTO> getChiTietLoiDTOS() {
        return chiTietLoiDTOS;
    }

    public void setChiTietLoiDTOS(List<ProNSCLChiTietLoiDTO> chiTietLoiDTOS) {
        this.chiTietLoiDTOS = chiTietLoiDTOS;
    }

    public ProGioLamViecDTO getGioLamViecDTO() {
        return gioLamViecDTO;
    }

    public void setGioLamViecDTO(ProGioLamViecDTO gioLamViecDTO) {
        this.gioLamViecDTO = gioLamViecDTO;
    }

    public Float getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(Float qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }
}
