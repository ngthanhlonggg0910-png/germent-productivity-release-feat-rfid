package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ProKeHoachSanXuat.
 */
@Entity
@Table(name = "pro_ke_hoach_san_xuat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProKeHoachSanXuat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ngay_san_xuat")
    private Instant ngaySanXuat;

    @Column(name = "so_luong_yeu_cau")
    private Integer soLuongYeuCau;

    @Column(name = "tinh_trang")
    private Integer tinhTrang;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "sam")
    private Integer sam;

    @Column(name = "lot_no")
    private Integer lotNo;

    @Column(name = "tong_may_ra")
    private Integer tongMayRa = 0;

    /** Cột DB: {@code isActived} — tên cột cố định, không dùng naming strategy snake_case. */
    @Column(name = "isActived")
    private Boolean isActived;

    @Column(name = "active_time")
    private Instant activeTime;

//    @Column(name = "isCompleted")
//    private Boolean isCompleted;
//
//    @Column(name = "complete_time")
//    private Instant completeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proKeHoachSanXuats")
    private ProChuyen proChuyen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proKeHoachSanXuats")
    private ProSanPham proSanPham;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActived() {
        return isActived;
    }

    public void setIsActived(Boolean actived) {
        isActived = actived;
    }

    public Instant getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Instant activeTime) {
        this.activeTime = activeTime;
    }

//    public Boolean getIsCompleted() {
//        return isCompleted;
//    }
//
//    public void setIsCompleted(Boolean completed) {
//        isCompleted = completed;
//    }
//
//    public Instant getCompleteTime() {
//        return completeTime;
//    }
//
//    public void setCompleteTime(Instant completeTime) {
//        this.completeTime = completeTime;
//    }

    public Instant getNgaySanXuat() {
        return ngaySanXuat;
    }

    public ProKeHoachSanXuat ngaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
        return this;
    }

    public void setNgaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Integer getSoLuongYeuCau() {
        return soLuongYeuCau;
    }

    public ProKeHoachSanXuat soLuongYeuCau(Integer soLuongYeuCau) {
        this.soLuongYeuCau = soLuongYeuCau;
        return this;
    }

    public void setSoLuongYeuCau(Integer soLuongYeuCau) {
        this.soLuongYeuCau = soLuongYeuCau;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public ProKeHoachSanXuat tinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
        return this;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public ProKeHoachSanXuat ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getSam() {
        return sam;
    }

    public ProKeHoachSanXuat sam(Integer sam) {
        this.sam = sam;
        return this;
    }

    public void setSam(Integer sam) {
        this.sam = sam;
    }

    public Integer getLotNo() {
        return lotNo;
    }

    public Integer getTongMayRa() {
        return tongMayRa;
    }

    public void setTongMayRa(Integer tongMayRa) {
        this.tongMayRa = tongMayRa;
    }

    public ProKeHoachSanXuat lotNo(Integer lotNo) {
        this.lotNo = lotNo;
        return this;
    }

    public void setLotNo(Integer lotNo) {
        this.lotNo = lotNo;
    }

    public ProChuyen getProChuyen() {
        return proChuyen;
    }

    public ProKeHoachSanXuat proChuyen(ProChuyen proChuyen) {
        this.proChuyen = proChuyen;
        return this;
    }

    public void setProChuyen(ProChuyen proChuyen) {
        this.proChuyen = proChuyen;
    }

    public ProSanPham getProSanPham() {
        return proSanPham;
    }

    public ProKeHoachSanXuat proSanPham(ProSanPham proSanPham) {
        this.proSanPham = proSanPham;
        return this;
    }

    public void setProSanPham(ProSanPham proSanPham) {
        this.proSanPham = proSanPham;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProKeHoachSanXuat)) {
            return false;
        }
        return id != null && id.equals(((ProKeHoachSanXuat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProKeHoachSanXuat{" +
            "id=" + getId() +
            ", ngaySanXuat='" + getNgaySanXuat() + "'" +
            ", soLuongYeuCau=" + getSoLuongYeuCau() +
            ", tinhTrang=" + getTinhTrang() +
            ", ghiChu='" + getGhiChu() + "'" +
            ", sam=" + getSam() +
            ", lotNo=" + getLotNo() +
            "}";
    }
}
