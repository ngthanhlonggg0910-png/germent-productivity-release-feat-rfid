package vn.nvsoft.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DungSai.
 */
@Entity
@Table(name = "pro_kiem_kiem_vuot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProKiemKiemVuot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pro_san_pham_id")
    private Long proSanPhamId;

    @Column(name = "chuyen_id")
    private Long chuyenId;

    @Column(name = "so_luong_don_hang")
    private Integer soLuongDonHang;

    @Column(name = "so_luong_vao_chuyen")
    private Integer soLuongVaoChuyen;

    @Column(name = "so_luong_cat")
    private Integer soLuongCat;

    @Column(name = "so_luong_may_ra")
    private Integer soLuongMayRa;

    @Column(name = "so_luong_nhap_kho")
    private Integer soLuongNhapKho;

    @Column(name = "dhid")
    private Long dhid;

    @Column(name = "ma_hang")
    private String maHang;

    @Column(name = "cum_po")
    private String cumPo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public Long getChuyenId() {
        return chuyenId;
    }

    public void setChuyenId(Long chuyenId) {
        this.chuyenId = chuyenId;
    }

    public Integer getSoLuongDonHang() {
        return soLuongDonHang;
    }

    public void setSoLuongDonHang(Integer soLuongDonHang) {
        this.soLuongDonHang = soLuongDonHang;
    }

    public Integer getSoLuongVaoChuyen() {
        return soLuongVaoChuyen;
    }

    public void setSoLuongVaoChuyen(Integer soLuongVaoChuyen) {
        this.soLuongVaoChuyen = soLuongVaoChuyen;
    }

    public Integer getSoLuongCat() {
        return soLuongCat;
    }

    public void setSoLuongCat(Integer soLuongCat) {
        this.soLuongCat = soLuongCat;
    }

    public Integer getSoLuongMayRa() {
        return soLuongMayRa;
    }

    public void setSoLuongMayRa(Integer soLuongMayRa) {
        this.soLuongMayRa = soLuongMayRa;
    }

    public Integer getSoLuongNhapKho() {
        return soLuongNhapKho;
    }

    public void setSoLuongNhapKho(Integer soLuongNhapKho) {
        this.soLuongNhapKho = soLuongNhapKho;
    }

    public Long getDhid() {
        return dhid;
    }

    public void setDhid(Long dhid) {
        this.dhid = dhid;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getCumPo() {
        return cumPo;
    }

    public void setCumPo(String cumPo) {
        this.cumPo = cumPo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProKiemKiemVuot that = (ProKiemKiemVuot) o;
        return id.equals(that.id) && Objects.equals(proSanPhamId, that.proSanPhamId) && Objects.equals(chuyenId, that.chuyenId) && Objects.equals(soLuongDonHang, that.soLuongDonHang) && Objects.equals(soLuongVaoChuyen, that.soLuongVaoChuyen) && Objects.equals(soLuongCat, that.soLuongCat) && Objects.equals(soLuongMayRa, that.soLuongMayRa) && Objects.equals(soLuongNhapKho, that.soLuongNhapKho) && Objects.equals(dhid, that.dhid) && Objects.equals(maHang, that.maHang) && Objects.equals(cumPo, that.cumPo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proSanPhamId, chuyenId, soLuongDonHang, soLuongVaoChuyen, soLuongCat, soLuongMayRa, soLuongNhapKho, dhid, maHang, cumPo);
    }
}
