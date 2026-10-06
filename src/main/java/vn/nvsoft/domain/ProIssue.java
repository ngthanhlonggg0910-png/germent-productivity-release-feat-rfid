package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ProIssue.
 */
@Entity
@Table(name = "pro_issue")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_hang")
    private String maHang;

    @Column(name = "pono")
    private String pono;

    @Column(name = "ten_chuyen")
    private String tenChuyen;

    @Column(name = "ngay_gio_bat_dau")
    private Instant ngayGioBatDau;

    @Column(name = "ngay_gio_ket_thuc")
    private Instant ngayGioKetThuc;

    @Column(name = "ma_loi")
    private String maLoi;

    @Column(name = "mo_ta_loi")
    private String moTaLoi;

    @Column(name = "ma_may")
    private String maMay;

    @Column(name = "so_tram")
    private String soTram;

    @Column(name = "pro_chuyen_id")
    private Long proChuyenId;

    @Column(name = "pro_san_pham_id")
    private Long proSanPhamId;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "check_point_type")
    private String checkPointType;

    @Column(name = "check_point_id")
    private Long checkPointId;

    @Column(name = "check_point_name")
    private String checkPointName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHang() {
        return maHang;
    }

    public ProIssue maHang(String maHang) {
        this.maHang = maHang;
        return this;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPono() {
        return pono;
    }

    public ProIssue pono(String pono) {
        this.pono = pono;
        return this;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public ProIssue tenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
        return this;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Instant getNgayGioBatDau() {
        return ngayGioBatDau;
    }

    public ProIssue ngayGioBatDau(Instant ngayGioBatDau) {
        this.ngayGioBatDau = ngayGioBatDau;
        return this;
    }

    public void setNgayGioBatDau(Instant ngayGioBatDau) {
        this.ngayGioBatDau = ngayGioBatDau;
    }

    public Instant getNgayGioKetThuc() {
        return ngayGioKetThuc;
    }

    public ProIssue ngayGioKetThuc(Instant ngayGioKetThuc) {
        this.ngayGioKetThuc = ngayGioKetThuc;
        return this;
    }

    public void setNgayGioKetThuc(Instant ngayGioKetThuc) {
        this.ngayGioKetThuc = ngayGioKetThuc;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public ProIssue maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public ProIssue moTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
        return this;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public String getMaMay() {
        return maMay;
    }

    public ProIssue maMay(String maMay) {
        this.maMay = maMay;
        return this;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getSoTram() {
        return soTram;
    }

    public ProIssue soTram(String soTram) {
        this.soTram = soTram;
        return this;
    }

    public void setSoTram(String soTram) {
        this.soTram = soTram;
    }

    public Long getProChuyenId() {
        return proChuyenId;
    }

    public ProIssue proChuyenId(Long proChuyenId) {
        this.proChuyenId = proChuyenId;
        return this;
    }

    public void setProChuyenId(Long proChuyenId) {
        this.proChuyenId = proChuyenId;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public ProIssue proSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
        return this;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public ProIssue nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getCheckPointType() {
        return checkPointType;
    }

    public ProIssue checkPointType(String checkPointType) {
        this.checkPointType = checkPointType;
        return this;
    }

    public void setCheckPointType(String checkPointType) {
        this.checkPointType = checkPointType;
    }

    public Long getCheckPointId() {
        return checkPointId;
    }

    public ProIssue checkPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
        return this;
    }

    public void setCheckPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getCheckPointName() {
        return checkPointName;
    }

    public ProIssue checkPointName(String checkPointName) {
        this.checkPointName = checkPointName;
        return this;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProIssue)) {
            return false;
        }
        return id != null && id.equals(((ProIssue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProIssue{" +
            "id=" + getId() +
            ", maHang='" + getMaHang() + "'" +
            ", pono='" + getPono() + "'" +
            ", tenChuyen='" + getTenChuyen() + "'" +
            ", ngayGioBatDau='" + getNgayGioBatDau() + "'" +
            ", ngayGioKetThuc='" + getNgayGioKetThuc() + "'" +
            ", maLoi='" + getMaLoi() + "'" +
            ", moTaLoi='" + getMoTaLoi() + "'" +
            ", maMay='" + getMaMay() + "'" +
            ", soTram='" + getSoTram() + "'" +
            ", proChuyenId=" + getProChuyenId() +
            ", proSanPhamId=" + getProSanPhamId() +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", checkPointType='" + getCheckPointType() + "'" +
            ", checkPointId=" + getCheckPointId() +
            ", checkPointName='" + getCheckPointName() + "'" +
            "}";
    }
}
