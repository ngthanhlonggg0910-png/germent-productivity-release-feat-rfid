package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProSanPham.
 */
@Entity
@Table(name = "pro_san_pham")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ma_don_hang")
    private String maDonHang;

    @Column(name = "po")
    private String po;

    @Column(name = "so_luong_san_xuat")
    private Integer soLuongSanXuat;

    @Column(name = "thong_so_value_1")
    private String thongSoValue1;

    @Column(name = "thong_so_caption_1")
    private String thongSoCaption1;

    @Column(name = "thong_so_check_1")
    private Boolean thongSoCheck1;

    @Column(name = "thong_so_value_2")
    private String thongSoValue2;

    @Column(name = "thong_so_caption_2")
    private String thongSoCaption2;

    @Column(name = "thong_so_check_2")
    private Boolean thongSoCheck2;

    @Column(name = "thong_so_value_3")
    private String thongSoValue3;

    @Column(name = "thong_so_caption_3")
    private String thongSoCaption3;

    @Column(name = "thong_so_check_3")
    private Boolean thongSoCheck3;

    @Column(name = "thong_so_value_4")
    private String thongSoValue4;

    @Column(name = "thong_so_caption_4")
    private String thongSoCaption4;

    @Column(name = "thong_so_check_4")
    private Boolean thongSoCheck4;

    @Column(name = "thong_so_value_5")
    private String thongSoValue5;

    @Column(name = "thong_so_caption_5")
    private String thongSoCaption5;

    @Column(name = "thong_so_check_5")
    private Boolean thongSoCheck5;

    @Column(name = "thong_so_value_6")
    private String thongSoValue6;

    @Column(name = "thong_so_caption_6")
    private String thongSoCaption6;

    @Column(name = "thong_so_check_6")
    private Boolean thongSoCheck6;

    @Column(name = "thong_so_value_7")
    private String thongSoValue7;

    @Column(name = "thong_so_caption_7")
    private String thongSoCaption7;

    @Column(name = "thong_so_check_7")
    private Boolean thongSoCheck7;

    @Column(name = "thong_so_value_8")
    private String thongSoValue8;

    @Column(name = "thong_so_caption_8")
    private String thongSoCaption8;

    @Column(name = "thong_so_check_8")
    private Boolean thongSoCheck8;

    @Column(name = "thong_so_value_9")
    private String thongSoValue9;

    @Column(name = "thong_so_caption_9")
    private String thongSoCaption9;

    @Column(name = "thong_so_check_9")
    private Boolean thongSoCheck9;

    @Column(name = "thong_so_value_10")
    private String thongSoValue10;

    @Column(name = "thong_so_caption_10")
    private String thongSoCaption10;

    @Column(name = "thong_so_check_10")
    private Boolean thongSoCheck10;

    @Column(name = "color")
    private String color;

    @Column(name = "hinh_mat_truoc_url")
    private String hinhMatTruocURL;

    @Column(name = "hinh_mat_sau_url")
    private String hinhMatSauURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proSanPhams")
    private ProKhachHang proKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proSanPhams")
    private ProChungLoaiSanPham proChungLoaiSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proSanPhams")
    private ProTenMua proTenMua;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public ProSanPham maSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
        return this;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public ProSanPham maDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
        return this;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getPo() {
        return po;
    }

    public ProSanPham po(String po) {
        this.po = po;
        return this;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public Integer getSoLuongSanXuat() {
        return soLuongSanXuat;
    }

    public ProSanPham soLuongSanXuat(Integer soLuongSanXuat) {
        this.soLuongSanXuat = soLuongSanXuat;
        return this;
    }

    public void setSoLuongSanXuat(Integer soLuongSanXuat) {
        this.soLuongSanXuat = soLuongSanXuat;
    }

    public String getThongSoValue1() {
        return thongSoValue1;
    }

    public ProSanPham thongSoValue1(String thongSoValue1) {
        this.thongSoValue1 = thongSoValue1;
        return this;
    }

    public void setThongSoValue1(String thongSoValue1) {
        this.thongSoValue1 = thongSoValue1;
    }

    public String getThongSoCaption1() {
        return thongSoCaption1;
    }

    public ProSanPham thongSoCaption1(String thongSoCaption1) {
        this.thongSoCaption1 = thongSoCaption1;
        return this;
    }

    public void setThongSoCaption1(String thongSoCaption1) {
        this.thongSoCaption1 = thongSoCaption1;
    }

    public Boolean isThongSoCheck1() {
        return thongSoCheck1;
    }

    public ProSanPham thongSoCheck1(Boolean thongSoCheck1) {
        this.thongSoCheck1 = thongSoCheck1;
        return this;
    }

    public void setThongSoCheck1(Boolean thongSoCheck1) {
        this.thongSoCheck1 = thongSoCheck1;
    }

    public String getThongSoValue2() {
        return thongSoValue2;
    }

    public ProSanPham thongSoValue2(String thongSoValue2) {
        this.thongSoValue2 = thongSoValue2;
        return this;
    }

    public void setThongSoValue2(String thongSoValue2) {
        this.thongSoValue2 = thongSoValue2;
    }

    public String getThongSoCaption2() {
        return thongSoCaption2;
    }

    public ProSanPham thongSoCaption2(String thongSoCaption2) {
        this.thongSoCaption2 = thongSoCaption2;
        return this;
    }

    public void setThongSoCaption2(String thongSoCaption2) {
        this.thongSoCaption2 = thongSoCaption2;
    }

    public Boolean isThongSoCheck2() {
        return thongSoCheck2;
    }

    public ProSanPham thongSoCheck2(Boolean thongSoCheck2) {
        this.thongSoCheck2 = thongSoCheck2;
        return this;
    }

    public void setThongSoCheck2(Boolean thongSoCheck2) {
        this.thongSoCheck2 = thongSoCheck2;
    }

    public String getThongSoValue3() {
        return thongSoValue3;
    }

    public ProSanPham thongSoValue3(String thongSoValue3) {
        this.thongSoValue3 = thongSoValue3;
        return this;
    }

    public void setThongSoValue3(String thongSoValue3) {
        this.thongSoValue3 = thongSoValue3;
    }

    public String getThongSoCaption3() {
        return thongSoCaption3;
    }

    public ProSanPham thongSoCaption3(String thongSoCaption3) {
        this.thongSoCaption3 = thongSoCaption3;
        return this;
    }

    public void setThongSoCaption3(String thongSoCaption3) {
        this.thongSoCaption3 = thongSoCaption3;
    }

    public Boolean isThongSoCheck3() {
        return thongSoCheck3;
    }

    public ProSanPham thongSoCheck3(Boolean thongSoCheck3) {
        this.thongSoCheck3 = thongSoCheck3;
        return this;
    }

    public void setThongSoCheck3(Boolean thongSoCheck3) {
        this.thongSoCheck3 = thongSoCheck3;
    }

    public String getThongSoValue4() {
        return thongSoValue4;
    }

    public ProSanPham thongSoValue4(String thongSoValue4) {
        this.thongSoValue4 = thongSoValue4;
        return this;
    }

    public void setThongSoValue4(String thongSoValue4) {
        this.thongSoValue4 = thongSoValue4;
    }

    public String getThongSoCaption4() {
        return thongSoCaption4;
    }

    public ProSanPham thongSoCaption4(String thongSoCaption4) {
        this.thongSoCaption4 = thongSoCaption4;
        return this;
    }

    public void setThongSoCaption4(String thongSoCaption4) {
        this.thongSoCaption4 = thongSoCaption4;
    }

    public Boolean isThongSoCheck4() {
        return thongSoCheck4;
    }

    public ProSanPham thongSoCheck4(Boolean thongSoCheck4) {
        this.thongSoCheck4 = thongSoCheck4;
        return this;
    }

    public void setThongSoCheck4(Boolean thongSoCheck4) {
        this.thongSoCheck4 = thongSoCheck4;
    }

    public String getThongSoValue5() {
        return thongSoValue5;
    }

    public ProSanPham thongSoValue5(String thongSoValue5) {
        this.thongSoValue5 = thongSoValue5;
        return this;
    }

    public void setThongSoValue5(String thongSoValue5) {
        this.thongSoValue5 = thongSoValue5;
    }

    public String getThongSoCaption5() {
        return thongSoCaption5;
    }

    public ProSanPham thongSoCaption5(String thongSoCaption5) {
        this.thongSoCaption5 = thongSoCaption5;
        return this;
    }

    public void setThongSoCaption5(String thongSoCaption5) {
        this.thongSoCaption5 = thongSoCaption5;
    }

    public Boolean isThongSoCheck5() {
        return thongSoCheck5;
    }

    public ProSanPham thongSoCheck5(Boolean thongSoCheck5) {
        this.thongSoCheck5 = thongSoCheck5;
        return this;
    }

    public void setThongSoCheck5(Boolean thongSoCheck5) {
        this.thongSoCheck5 = thongSoCheck5;
    }

    public String getThongSoValue6() {
        return thongSoValue6;
    }

    public ProSanPham thongSoValue6(String thongSoValue6) {
        this.thongSoValue6 = thongSoValue6;
        return this;
    }

    public void setThongSoValue6(String thongSoValue6) {
        this.thongSoValue6 = thongSoValue6;
    }

    public String getThongSoCaption6() {
        return thongSoCaption6;
    }

    public ProSanPham thongSoCaption6(String thongSoCaption6) {
        this.thongSoCaption6 = thongSoCaption6;
        return this;
    }

    public void setThongSoCaption6(String thongSoCaption6) {
        this.thongSoCaption6 = thongSoCaption6;
    }

    public Boolean isThongSoCheck6() {
        return thongSoCheck6;
    }

    public ProSanPham thongSoCheck6(Boolean thongSoCheck6) {
        this.thongSoCheck6 = thongSoCheck6;
        return this;
    }

    public void setThongSoCheck6(Boolean thongSoCheck6) {
        this.thongSoCheck6 = thongSoCheck6;
    }

    public String getThongSoValue7() {
        return thongSoValue7;
    }

    public ProSanPham thongSoValue7(String thongSoValue7) {
        this.thongSoValue7 = thongSoValue7;
        return this;
    }

    public void setThongSoValue7(String thongSoValue7) {
        this.thongSoValue7 = thongSoValue7;
    }

    public String getThongSoCaption7() {
        return thongSoCaption7;
    }

    public ProSanPham thongSoCaption7(String thongSoCaption7) {
        this.thongSoCaption7 = thongSoCaption7;
        return this;
    }

    public void setThongSoCaption7(String thongSoCaption7) {
        this.thongSoCaption7 = thongSoCaption7;
    }

    public Boolean isThongSoCheck7() {
        return thongSoCheck7;
    }

    public ProSanPham thongSoCheck7(Boolean thongSoCheck7) {
        this.thongSoCheck7 = thongSoCheck7;
        return this;
    }

    public void setThongSoCheck7(Boolean thongSoCheck7) {
        this.thongSoCheck7 = thongSoCheck7;
    }

    public String getThongSoValue8() {
        return thongSoValue8;
    }

    public ProSanPham thongSoValue8(String thongSoValue8) {
        this.thongSoValue8 = thongSoValue8;
        return this;
    }

    public void setThongSoValue8(String thongSoValue8) {
        this.thongSoValue8 = thongSoValue8;
    }

    public String getThongSoCaption8() {
        return thongSoCaption8;
    }

    public ProSanPham thongSoCaption8(String thongSoCaption8) {
        this.thongSoCaption8 = thongSoCaption8;
        return this;
    }

    public void setThongSoCaption8(String thongSoCaption8) {
        this.thongSoCaption8 = thongSoCaption8;
    }

    public Boolean isThongSoCheck8() {
        return thongSoCheck8;
    }

    public ProSanPham thongSoCheck8(Boolean thongSoCheck8) {
        this.thongSoCheck8 = thongSoCheck8;
        return this;
    }

    public void setThongSoCheck8(Boolean thongSoCheck8) {
        this.thongSoCheck8 = thongSoCheck8;
    }

    public String getThongSoValue9() {
        return thongSoValue9;
    }

    public ProSanPham thongSoValue9(String thongSoValue9) {
        this.thongSoValue9 = thongSoValue9;
        return this;
    }

    public void setThongSoValue9(String thongSoValue9) {
        this.thongSoValue9 = thongSoValue9;
    }

    public String getThongSoCaption9() {
        return thongSoCaption9;
    }

    public ProSanPham thongSoCaption9(String thongSoCaption9) {
        this.thongSoCaption9 = thongSoCaption9;
        return this;
    }

    public void setThongSoCaption9(String thongSoCaption9) {
        this.thongSoCaption9 = thongSoCaption9;
    }

    public Boolean isThongSoCheck9() {
        return thongSoCheck9;
    }

    public ProSanPham thongSoCheck9(Boolean thongSoCheck9) {
        this.thongSoCheck9 = thongSoCheck9;
        return this;
    }

    public void setThongSoCheck9(Boolean thongSoCheck9) {
        this.thongSoCheck9 = thongSoCheck9;
    }

    public String getThongSoValue10() {
        return thongSoValue10;
    }

    public ProSanPham thongSoValue10(String thongSoValue10) {
        this.thongSoValue10 = thongSoValue10;
        return this;
    }

    public void setThongSoValue10(String thongSoValue10) {
        this.thongSoValue10 = thongSoValue10;
    }

    public String getThongSoCaption10() {
        return thongSoCaption10;
    }

    public ProSanPham thongSoCaption10(String thongSoCaption10) {
        this.thongSoCaption10 = thongSoCaption10;
        return this;
    }

    public void setThongSoCaption10(String thongSoCaption10) {
        this.thongSoCaption10 = thongSoCaption10;
    }

    public Boolean isThongSoCheck10() {
        return thongSoCheck10;
    }

    public ProSanPham thongSoCheck10(Boolean thongSoCheck10) {
        this.thongSoCheck10 = thongSoCheck10;
        return this;
    }

    public void setThongSoCheck10(Boolean thongSoCheck10) {
        this.thongSoCheck10 = thongSoCheck10;
    }

    public String getColor() {
        return color;
    }

    public ProSanPham color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHinhMatTruocURL() {
        return hinhMatTruocURL;
    }

    public ProSanPham hinhMatTruocURL(String hinhMatTruocURL) {
        this.hinhMatTruocURL = hinhMatTruocURL;
        return this;
    }

    public void setHinhMatTruocURL(String hinhMatTruocURL) {
        this.hinhMatTruocURL = hinhMatTruocURL;
    }

    public String getHinhMatSauURL() {
        return hinhMatSauURL;
    }

    public ProSanPham hinhMatSauURL(String hinhMatSauURL) {
        this.hinhMatSauURL = hinhMatSauURL;
        return this;
    }

    public void setHinhMatSauURL(String hinhMatSauURL) {
        this.hinhMatSauURL = hinhMatSauURL;
    }

    public ProKhachHang getProKhachHang() {
        return proKhachHang;
    }

    public ProSanPham proKhachHang(ProKhachHang proKhachHang) {
        this.proKhachHang = proKhachHang;
        return this;
    }

    public void setProKhachHang(ProKhachHang proKhachHang) {
        this.proKhachHang = proKhachHang;
    }

    public ProChungLoaiSanPham getProChungLoaiSanPham() {
        return proChungLoaiSanPham;
    }

    public ProSanPham proChungLoaiSanPham(ProChungLoaiSanPham proChungLoaiSanPham) {
        this.proChungLoaiSanPham = proChungLoaiSanPham;
        return this;
    }

    public void setProChungLoaiSanPham(ProChungLoaiSanPham proChungLoaiSanPham) {
        this.proChungLoaiSanPham = proChungLoaiSanPham;
    }

    public ProTenMua getProTenMua() {
        return proTenMua;
    }

    public ProSanPham proTenMua(ProTenMua proTenMua) {
        this.proTenMua = proTenMua;
        return this;
    }

    public void setProTenMua(ProTenMua proTenMua) {
        this.proTenMua = proTenMua;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProSanPham)) {
            return false;
        }
        return id != null && id.equals(((ProSanPham) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProSanPham{" +
            "id=" + getId() +
            ", maSanPham='" + getMaSanPham() + "'" +
            ", maDonHang='" + getMaDonHang() + "'" +
            ", po='" + getPo() + "'" +
            ", soLuongSanXuat=" + getSoLuongSanXuat() +
            ", thongSoValue1='" + getThongSoValue1() + "'" +
            ", thongSoCaption1='" + getThongSoCaption1() + "'" +
            ", thongSoCheck1='" + isThongSoCheck1() + "'" +
            ", thongSoValue2='" + getThongSoValue2() + "'" +
            ", thongSoCaption2='" + getThongSoCaption2() + "'" +
            ", thongSoCheck2='" + isThongSoCheck2() + "'" +
            ", thongSoValue3='" + getThongSoValue3() + "'" +
            ", thongSoCaption3='" + getThongSoCaption3() + "'" +
            ", thongSoCheck3='" + isThongSoCheck3() + "'" +
            ", thongSoValue4='" + getThongSoValue4() + "'" +
            ", thongSoCaption4='" + getThongSoCaption4() + "'" +
            ", thongSoCheck4='" + isThongSoCheck4() + "'" +
            ", thongSoValue5='" + getThongSoValue5() + "'" +
            ", thongSoCaption5='" + getThongSoCaption5() + "'" +
            ", thongSoCheck5='" + isThongSoCheck5() + "'" +
            ", thongSoValue6='" + getThongSoValue6() + "'" +
            ", thongSoCaption6='" + getThongSoCaption6() + "'" +
            ", thongSoCheck6='" + isThongSoCheck6() + "'" +
            ", thongSoValue7='" + getThongSoValue7() + "'" +
            ", thongSoCaption7='" + getThongSoCaption7() + "'" +
            ", thongSoCheck7='" + isThongSoCheck7() + "'" +
            ", thongSoValue8='" + getThongSoValue8() + "'" +
            ", thongSoCaption8='" + getThongSoCaption8() + "'" +
            ", thongSoCheck8='" + isThongSoCheck8() + "'" +
            ", thongSoValue9='" + getThongSoValue9() + "'" +
            ", thongSoCaption9='" + getThongSoCaption9() + "'" +
            ", thongSoCheck9='" + isThongSoCheck9() + "'" +
            ", thongSoValue10='" + getThongSoValue10() + "'" +
            ", thongSoCaption10='" + getThongSoCaption10() + "'" +
            ", thongSoCheck10='" + isThongSoCheck10() + "'" +
            ", color='" + getColor() + "'" +
            ", hinhMatTruocURL='" + getHinhMatTruocURL() + "'" +
            ", hinhMatSauURL='" + getHinhMatSauURL() + "'" +
            "}";
    }
}
