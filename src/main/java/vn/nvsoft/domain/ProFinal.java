package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ProFinal.
 */
@Entity
@Table(name = "pro_final")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProFinal implements Serializable {

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
    private Integer soThuTuThung;

    @Column(name = "do_kim_md")
    private String doKimMd;

    @Column(name = "aql")
    private String aql;

    @Column(name = "max_loi_cho_phep")
    private Integer maxLoiChoPhep;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "so_luong_po_kiem")
    private Integer soLuongPoKiem;

    @Column(name = "pass")
    private Boolean pass;

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

    public ProFinal khxhId(Long khxhId) {
        this.khxhId = khxhId;
        return this;
    }

    public void setKhxhId(Long khxhId) {
        this.khxhId = khxhId;
    }

    public String getMaHang() {
        return maHang;
    }

    public ProFinal maHang(String maHang) {
        this.maHang = maHang;
        return this;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPoNo() {
        return poNo;
    }

    public ProFinal poNo(String poNo) {
        this.poNo = poNo;
        return this;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Instant getNgayKiemFinal() {
        return ngayKiemFinal;
    }

    public ProFinal ngayKiemFinal(Instant ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
        return this;
    }

    public void setNgayKiemFinal(Instant ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public ProFinal tenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
        return this;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Instant getNgayGioKiem() {
        return ngayGioKiem;
    }

    public ProFinal ngayGioKiem(Instant ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
        return this;
    }

    public void setNgayGioKiem(Instant ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public Integer getSoLuongKiemAql() {
        return soLuongKiemAql;
    }

    public ProFinal soLuongKiemAql(Integer soLuongKiemAql) {
        this.soLuongKiemAql = soLuongKiemAql;
        return this;
    }

    public void setSoLuongKiemAql(Integer soLuongKiemAql) {
        this.soLuongKiemAql = soLuongKiemAql;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public ProFinal soLuong(Integer soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDoAm() {
        return doAm;
    }

    public ProFinal doAm(Float doAm) {
        this.doAm = doAm;
        return this;
    }

    public void setDoAm(Float doAm) {
        this.doAm = doAm;
    }

    public Integer getTongSoLoi() {
        return tongSoLoi;
    }

    public ProFinal tongSoLoi(Integer tongSoLoi) {
        this.tongSoLoi = tongSoLoi;
        return this;
    }

    public void setTongSoLoi(Integer tongSoLoi) {
        this.tongSoLoi = tongSoLoi;
    }

    public Integer getSoThuTuThung() {
        return soThuTuThung;
    }

    public ProFinal soThuTuThung(Integer soThuTuThung) {
        this.soThuTuThung = soThuTuThung;
        return this;
    }

    public void setSoThuTuThung(Integer soThuTuThung) {
        this.soThuTuThung = soThuTuThung;
    }

    public String getDoKimMd() {
        return doKimMd;
    }

    public ProFinal doKimMd(String doKimMd) {
        this.doKimMd = doKimMd;
        return this;
    }

    public void setDoKimMd(String doKimMd) {
        this.doKimMd = doKimMd;
    }

    public String getAql() {
        return aql;
    }

    public ProFinal aql(String aql) {
        this.aql = aql;
        return this;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public Integer getMaxLoiChoPhep() {
        return maxLoiChoPhep;
    }

    public ProFinal maxLoiChoPhep(Integer maxLoiChoPhep) {
        this.maxLoiChoPhep = maxLoiChoPhep;
        return this;
    }

    public void setMaxLoiChoPhep(Integer maxLoiChoPhep) {
        this.maxLoiChoPhep = maxLoiChoPhep;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public ProFinal completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getSoLuongPoKiem() {
        return soLuongPoKiem;
    }

    public ProFinal soLuongPoKiem(Integer soLuongPoKiem) {
        this.soLuongPoKiem = soLuongPoKiem;
        return this;
    }

    public void setSoLuongPoKiem(Integer soLuongPoKiem) {
        this.soLuongPoKiem = soLuongPoKiem;
    }

    public Boolean isPass() {
        return pass;
    }

    public ProFinal pass(Boolean pass) {
        this.pass = pass;
        return this;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getNguoiKiem() {
        return nguoiKiem;
    }

    public ProFinal nguoiKiem(String nguoiKiem) {
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
        if (!(o instanceof ProFinal)) {
            return false;
        }
        return id != null && id.equals(((ProFinal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProFinal{" +
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
            ", soThuTuThung=" + getSoThuTuThung() +
            ", doKimMd='" + getDoKimMd() + "'" +
            ", aql='" + getAql() + "'" +
            ", maxLoiChoPhep=" + getMaxLoiChoPhep() +
            ", completed='" + isCompleted() + "'" +
            ", soLuongPoKiem=" + getSoLuongPoKiem() +
            ", pass='" + isPass() + "'" +
            ", nguoiKiem='" + getNguoiKiem() + "'" +
            "}";
    }
}
