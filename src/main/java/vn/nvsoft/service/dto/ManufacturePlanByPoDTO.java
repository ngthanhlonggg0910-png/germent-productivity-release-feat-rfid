package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import vn.nvsoft.repository.CommonFactoryRepository.ManufacturePlanByPoProjection;

/**
 * Kế hoạch SX hôm nay theo {@code OMM_PO.PONo} — slDaKiem = Qty + Fix + sua_po_luy_ke (pool query 2).
 */
public class ManufacturePlanByPoDTO implements Serializable {

    private Long planId;
    private Instant planManufactureDate;
    private Long planRequestedQuantity;
    private Long planStatus;
    private String maSanPham;
    private String maDonHang;
    private String po;
    private String cumPo;
    private String bandName;
    private Long bandIndex;
    private Integer khtStt;
    private Integer slDaKiem;

    public ManufacturePlanByPoDTO() {}

    public ManufacturePlanByPoDTO(ManufacturePlanByPoProjection p) {
        planId = p.getPlanId();
        if (p.getPlanManufactureDate() != null) {
            planManufactureDate = p.getPlanManufactureDate().toInstant();
        }
        planRequestedQuantity = p.getPlanRequestedQuantity();
        planStatus = p.getPlanStatus();
        maSanPham = p.getMaSanPham();
        maDonHang = p.getMaDonHang();
        po = p.getPo();
        cumPo = p.getCumPo();
        bandName = p.getBandName();
        bandIndex = p.getBandIndex();
        khtStt = p.getKhtStt();
        slDaKiem = p.getSlDaKiem();
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Instant getPlanManufactureDate() {
        return planManufactureDate;
    }

    public void setPlanManufactureDate(Instant planManufactureDate) {
        this.planManufactureDate = planManufactureDate;
    }

    public Long getPlanRequestedQuantity() {
        return planRequestedQuantity;
    }

    public void setPlanRequestedQuantity(Long planRequestedQuantity) {
        this.planRequestedQuantity = planRequestedQuantity;
    }

    public Long getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Long planStatus) {
        this.planStatus = planStatus;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getCumPo() {
        return cumPo;
    }

    public void setCumPo(String cumPo) {
        this.cumPo = cumPo;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Long getBandIndex() {
        return bandIndex;
    }

    public void setBandIndex(Long bandIndex) {
        this.bandIndex = bandIndex;
    }

    public Integer getKhtStt() {
        return khtStt;
    }

    public void setKhtStt(Integer khtStt) {
        this.khtStt = khtStt;
    }

    public Integer getSlDaKiem() {
        return slDaKiem;
    }

    public void setSlDaKiem(Integer slDaKiem) {
        this.slDaKiem = slDaKiem;
    }

    /** Alias cho client đang dùng {@code tongHangDaKiem} từ API manuplan. */
    public Integer getTongHangDaKiem() {
        return slDaKiem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManufacturePlanByPoDTO that = (ManufacturePlanByPoDTO) o;
        return Objects.equals(planId, that.planId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId);
    }
}
