package vn.nvsoft.service.dto;

/**
 * DTO for tổng may ra (tổng hàng đã kiểm: Sửa + Đạt)
 * Kết quả từ stored procedure SP_TongMayRa_ByKHSX
 */
public class TongMayRaDTO {

    private Long sanphamId;
    private Long chuyenId;
    private Long totalQty;      // Tổng Đạt
    private Long totalFix;      // Tổng Sửa
    private Long tongMayRa;     // Tổng hàng đã kiểm = totalQty + totalFix

    public TongMayRaDTO() {
    }

    public TongMayRaDTO(Long sanphamId, Long chuyenId, Long totalQty, Long totalFix, Long tongMayRa) {
        this.sanphamId = sanphamId;
        this.chuyenId = chuyenId;
        this.totalQty = totalQty;
        this.totalFix = totalFix;
        this.tongMayRa = tongMayRa;
    }

    public Long getSanphamId() {
        return sanphamId;
    }

    public void setSanphamId(Long sanphamId) {
        this.sanphamId = sanphamId;
    }

    public Long getChuyenId() {
        return chuyenId;
    }

    public void setChuyenId(Long chuyenId) {
        this.chuyenId = chuyenId;
    }

    public Long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Long totalQty) {
        this.totalQty = totalQty;
    }

    public Long getTotalFix() {
        return totalFix;
    }

    public void setTotalFix(Long totalFix) {
        this.totalFix = totalFix;
    }

    public Long getTongMayRa() {
        return tongMayRa;
    }

    public void setTongMayRa(Long tongMayRa) {
        this.tongMayRa = tongMayRa;
    }

    @Override
    public String toString() {
        return "TongMayRaDTO{" +
                "sanphamId=" + sanphamId +
                ", chuyenId=" + chuyenId +
                ", totalQty=" + totalQty +
                ", totalFix=" + totalFix +
                ", tongMayRa=" + tongMayRa +
                '}';
    }
}
