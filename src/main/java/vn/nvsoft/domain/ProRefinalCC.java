package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ProRefinalCC.
 */
@Entity
@Table(name = "pro_refinal_cc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProRefinalCC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "khxh_id")
    private Long khxhId;

    @Column(name = "ma_hang")
    private String maHang;

    @Column(name = "po_no")
    private String poNo;

    @Column(name = "ngay_kiem_final")
    private Instant ngayKiemFinal;

    @Column(name = "ten_chuyen")
    private String tenChuyen;

    @Column(name = "ngay_gio_kiem")
    private Instant ngayGioKiem;

    @Column(name = "so_luong_kiem_aql")
    private Integer soLuongKiemAql;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "do_am")
    private Float doAm;

    @Column(name = "tong_so_loi")
    private Integer tongSoLoi;

    @Column(name = "so_thu_tu_thung")
    private String soThuTuThung;

    @Column(name = "do_kim_md")
    private String doKimMd;

    @Column(name = "aql")
    private String aql;

    @Column(name = "max_loi_cho_phep")
    private Integer maxLoiChoPhep;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "nguoi_kiem")
    private String nguoiKiem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKhxhId() {
        return khxhId;
    }

    public ProRefinalCC khxhId(Long khxhId) {
        this.khxhId = khxhId;
        return this;
    }

    public void setKhxhId(Long khxhId) {
        this.khxhId = khxhId;
    }

    public String getMaHang() {
        return maHang;
    }

    public ProRefinalCC maHang(String maHang) {
        this.maHang = maHang;
        return this;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPoNo() {
        return poNo;
    }

    public ProRefinalCC poNo(String poNo) {
        this.poNo = poNo;
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Instant getNgayKiemFinal() {
        return ngayKiemFinal;
    }

    public ProRefinalCC ngayKiemFinal(Instant ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
        return this;
    }

    public void setNgayKiemFinal(Instant ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public ProRefinalCC tenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
        return this;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Instant getNgayGioKiem() {
        return ngayGioKiem;
    }

    public ProRefinalCC ngayGioKiem(Instant ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
        return this;
    }

    public void setNgayGioKiem(Instant ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public Integer getSoLuongKiemAql() {
        return soLuongKiemAql;
    }

    public ProRefinalCC soLuongKiemAql(Integer soLuongKiemAql) {
        this.soLuongKiemAql = soLuongKiemAql;
        return this;
    }

    public void setSoLuongKiemAql(Integer soLuongKiemAql) {
        this.soLuongKiemAql = soLuongKiemAql;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public ProRefinalCC soLuong(Integer soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDoAm() {
        return doAm;
    }

    public ProRefinalCC doAm(Float doAm) {
        this.doAm = doAm;
        return this;
    }

    public void setDoAm(Float doAm) {
        this.doAm = doAm;
    }

    public Integer getTongSoLoi() {
        return tongSoLoi;
    }

    public ProRefinalCC tongSoLoi(Integer tongSoLoi) {
        this.tongSoLoi = tongSoLoi;
        return this;
    }

    public void setTongSoLoi(Integer tongSoLoi) {
        this.tongSoLoi = tongSoLoi;
    }

    public String getSoThuTuThung() {
        return soThuTuThung;
    }

    public ProRefinalCC soThuTuThung(String soThuTuThung) {
        this.soThuTuThung = soThuTuThung;
        return this;
    }

    public void setSoThuTuThung(String soThuTuThung) {
        this.soThuTuThung = soThuTuThung;
    }

    public String getDoKimMd() {
        return doKimMd;
    }

    public ProRefinalCC doKimMd(String doKimMd) {
        this.doKimMd = doKimMd;
        return this;
    }

    public void setDoKimMd(String doKimMd) {
        this.doKimMd = doKimMd;
    }

    public String getAql() {
        return aql;
    }

    public ProRefinalCC aql(String aql) {
        this.aql = aql;
        return this;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public Integer getMaxLoiChoPhep() {
        return maxLoiChoPhep;
    }

    public ProRefinalCC maxLoiChoPhep(Integer maxLoiChoPhep) {
        this.maxLoiChoPhep = maxLoiChoPhep;
        return this;
    }

    public void setMaxLoiChoPhep(Integer maxLoiChoPhep) {
        this.maxLoiChoPhep = maxLoiChoPhep;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public ProRefinalCC completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getNguoiKiem() {
        return nguoiKiem;
    }

    public ProRefinalCC nguoiKiem(String nguoiKiem) {
        this.nguoiKiem = nguoiKiem;
        return this;
    }

    public void setNguoiKiem(String nguoiKiem) {
        this.nguoiKiem = nguoiKiem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProRefinalCC)) {
            return false;
        }
        return id != null && id.equals(((ProRefinalCC) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProRefinalCC{" +
            "id=" + getId() +
            ", khxhId=" + getKhxhId() +
            ", maHang='" + getMaHang() + "'" +
            ", poNo='" + getPoNo() + "'" +
            ", ngayKiemFinal='" + getNgayKiemFinal() + "'" +
            ", tenChuyen='" + getTenChuyen() + "'" +
            ", ngayGioKiem='" + getNgayGioKiem() + "'" +
            ", soLuongKiemAql=" + getSoLuongKiemAql() +
            ", soLuong=" + getSoLuong() +
            ", doAm=" + getDoAm() +
            ", tongSoLoi=" + getTongSoLoi() +
            ", soThuTuThung='" + getSoThuTuThung() + "'" +
            ", doKimMd='" + getDoKimMd() + "'" +
            ", aql='" + getAql() + "'" +
            ", maxLoiChoPhep=" + getMaxLoiChoPhep() +
            ", completed='" + isCompleted() + "'" +
            ", nguoiKiem='" + getNguoiKiem() + "'" +
            "}";
    }
}
