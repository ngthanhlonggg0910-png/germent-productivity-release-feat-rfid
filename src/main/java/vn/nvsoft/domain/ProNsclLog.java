package vn.nvsoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * Log thời gian bấm năng suất chất lượng (đạt / sửa / lỗi) theo checkpoint và kế hoạch sản xuất.
 */
@Entity
@Table(name = "pro_nscl_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProNsclLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "chuyen")
    private String chuyen;

    @Column(name = "ngay_san_xuat")
    private Instant ngaySanXuat;

    @Column(name = "ma_hang")
    private String maHang;

    @Column(name = "po")
    private String po;

    @Column(name = "thoi_gian_bam_lan_dau")
    private Instant thoiGianBamLanDau;

    @Column(name = "thoi_gian_bam_lan_cuoi")
    private Instant thoiGianBamLanCuoi;

    @Column(name = "nguoi_cap_nhat")
    private String nguoiCapNhat;

    @Column(name = "pro_san_pham_id")
    private Long proSanPhamId;

    @Column(name = "check_point_id", nullable = false)
    private Long checkPointId;

    @Column(name = "khsx_id", nullable = false)
    private Long khsxId;

    @Column(name = "pro_nscl_id")
    private Long proNsclId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChuyen() {
        return chuyen;
    }

    public void setChuyen(String chuyen) {
        this.chuyen = chuyen;
    }

    public Instant getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public Instant getThoiGianBamLanDau() {
        return thoiGianBamLanDau;
    }

    public void setThoiGianBamLanDau(Instant thoiGianBamLanDau) {
        this.thoiGianBamLanDau = thoiGianBamLanDau;
    }

    public Instant getThoiGianBamLanCuoi() {
        return thoiGianBamLanCuoi;
    }

    public void setThoiGianBamLanCuoi(Instant thoiGianBamLanCuoi) {
        this.thoiGianBamLanCuoi = thoiGianBamLanCuoi;
    }

    public String getNguoiCapNhat() {
        return nguoiCapNhat;
    }

    public void setNguoiCapNhat(String nguoiCapNhat) {
        this.nguoiCapNhat = nguoiCapNhat;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public Long getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
    }

    public Long getKhsxId() {
        return khsxId;
    }

    public void setKhsxId(Long khsxId) {
        this.khsxId = khsxId;
    }

    public Long getProNsclId() {
        return proNsclId;
    }

    public void setProNsclId(Long proNsclId) {
        this.proNsclId = proNsclId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNsclLog)) {
            return false;
        }
        return id != null && id.equals(((ProNsclLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
