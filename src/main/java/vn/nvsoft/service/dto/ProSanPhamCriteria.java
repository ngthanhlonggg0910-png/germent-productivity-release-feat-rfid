package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.nvsoft.domain.ProSanPham} entity. This class is used
 * in {@link vn.nvsoft.web.rest.ProSanPhamResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pro-san-phams?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProSanPhamCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maSanPham;

    private StringFilter maDonHang;

    private StringFilter po;

    private IntegerFilter soLuongSanXuat;

    private StringFilter thongSoValue1;

    private StringFilter thongSoCaption1;

    private BooleanFilter thongSoCheck1;

    private StringFilter thongSoValue2;

    private StringFilter thongSoCaption2;

    private BooleanFilter thongSoCheck2;

    private StringFilter thongSoValue3;

    private StringFilter thongSoCaption3;

    private BooleanFilter thongSoCheck3;

    private StringFilter thongSoValue4;

    private StringFilter thongSoCaption4;

    private BooleanFilter thongSoCheck4;

    private StringFilter thongSoValue5;

    private StringFilter thongSoCaption5;

    private BooleanFilter thongSoCheck5;

    private StringFilter thongSoValue6;

    private StringFilter thongSoCaption6;

    private BooleanFilter thongSoCheck6;

    private StringFilter thongSoValue7;

    private StringFilter thongSoCaption7;

    private BooleanFilter thongSoCheck7;

    private StringFilter thongSoValue8;

    private StringFilter thongSoCaption8;

    private BooleanFilter thongSoCheck8;

    private StringFilter thongSoValue9;

    private StringFilter thongSoCaption9;

    private BooleanFilter thongSoCheck9;

    private StringFilter thongSoValue10;

    private StringFilter thongSoCaption10;

    private BooleanFilter thongSoCheck10;

    private StringFilter color;

    private StringFilter hinhMatTruocURL;

    private StringFilter hinhMatSauURL;

    private LongFilter proKhachHangId;

    private LongFilter proChungLoaiSanPhamId;

    private LongFilter proTenMuaId;

    public ProSanPhamCriteria(){
    }

    public ProSanPhamCriteria(ProSanPhamCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maSanPham = other.maSanPham == null ? null : other.maSanPham.copy();
        this.maDonHang = other.maDonHang == null ? null : other.maDonHang.copy();
        this.po = other.po == null ? null : other.po.copy();
        this.soLuongSanXuat = other.soLuongSanXuat == null ? null : other.soLuongSanXuat.copy();
        this.thongSoValue1 = other.thongSoValue1 == null ? null : other.thongSoValue1.copy();
        this.thongSoCaption1 = other.thongSoCaption1 == null ? null : other.thongSoCaption1.copy();
        this.thongSoCheck1 = other.thongSoCheck1 == null ? null : other.thongSoCheck1.copy();
        this.thongSoValue2 = other.thongSoValue2 == null ? null : other.thongSoValue2.copy();
        this.thongSoCaption2 = other.thongSoCaption2 == null ? null : other.thongSoCaption2.copy();
        this.thongSoCheck2 = other.thongSoCheck2 == null ? null : other.thongSoCheck2.copy();
        this.thongSoValue3 = other.thongSoValue3 == null ? null : other.thongSoValue3.copy();
        this.thongSoCaption3 = other.thongSoCaption3 == null ? null : other.thongSoCaption3.copy();
        this.thongSoCheck3 = other.thongSoCheck3 == null ? null : other.thongSoCheck3.copy();
        this.thongSoValue4 = other.thongSoValue4 == null ? null : other.thongSoValue4.copy();
        this.thongSoCaption4 = other.thongSoCaption4 == null ? null : other.thongSoCaption4.copy();
        this.thongSoCheck4 = other.thongSoCheck4 == null ? null : other.thongSoCheck4.copy();
        this.thongSoValue5 = other.thongSoValue5 == null ? null : other.thongSoValue5.copy();
        this.thongSoCaption5 = other.thongSoCaption5 == null ? null : other.thongSoCaption5.copy();
        this.thongSoCheck5 = other.thongSoCheck5 == null ? null : other.thongSoCheck5.copy();
        this.thongSoValue6 = other.thongSoValue6 == null ? null : other.thongSoValue6.copy();
        this.thongSoCaption6 = other.thongSoCaption6 == null ? null : other.thongSoCaption6.copy();
        this.thongSoCheck6 = other.thongSoCheck6 == null ? null : other.thongSoCheck6.copy();
        this.thongSoValue7 = other.thongSoValue7 == null ? null : other.thongSoValue7.copy();
        this.thongSoCaption7 = other.thongSoCaption7 == null ? null : other.thongSoCaption7.copy();
        this.thongSoCheck7 = other.thongSoCheck7 == null ? null : other.thongSoCheck7.copy();
        this.thongSoValue8 = other.thongSoValue8 == null ? null : other.thongSoValue8.copy();
        this.thongSoCaption8 = other.thongSoCaption8 == null ? null : other.thongSoCaption8.copy();
        this.thongSoCheck8 = other.thongSoCheck8 == null ? null : other.thongSoCheck8.copy();
        this.thongSoValue9 = other.thongSoValue9 == null ? null : other.thongSoValue9.copy();
        this.thongSoCaption9 = other.thongSoCaption9 == null ? null : other.thongSoCaption9.copy();
        this.thongSoCheck9 = other.thongSoCheck9 == null ? null : other.thongSoCheck9.copy();
        this.thongSoValue10 = other.thongSoValue10 == null ? null : other.thongSoValue10.copy();
        this.thongSoCaption10 = other.thongSoCaption10 == null ? null : other.thongSoCaption10.copy();
        this.thongSoCheck10 = other.thongSoCheck10 == null ? null : other.thongSoCheck10.copy();
        this.color = other.color == null ? null : other.color.copy();
        this.hinhMatTruocURL = other.hinhMatTruocURL == null ? null : other.hinhMatTruocURL.copy();
        this.hinhMatSauURL = other.hinhMatSauURL == null ? null : other.hinhMatSauURL.copy();
        this.proKhachHangId = other.proKhachHangId == null ? null : other.proKhachHangId.copy();
        this.proChungLoaiSanPhamId = other.proChungLoaiSanPhamId == null ? null : other.proChungLoaiSanPhamId.copy();
        this.proTenMuaId = other.proTenMuaId == null ? null : other.proTenMuaId.copy();
    }

    @Override
    public ProSanPhamCriteria copy() {
        return new ProSanPhamCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(StringFilter maSanPham) {
        this.maSanPham = maSanPham;
    }

    public StringFilter getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(StringFilter maDonHang) {
        this.maDonHang = maDonHang;
    }

    public StringFilter getPo() {
        return po;
    }

    public void setPo(StringFilter po) {
        this.po = po;
    }

    public IntegerFilter getSoLuongSanXuat() {
        return soLuongSanXuat;
    }

    public void setSoLuongSanXuat(IntegerFilter soLuongSanXuat) {
        this.soLuongSanXuat = soLuongSanXuat;
    }

    public StringFilter getThongSoValue1() {
        return thongSoValue1;
    }

    public void setThongSoValue1(StringFilter thongSoValue1) {
        this.thongSoValue1 = thongSoValue1;
    }

    public StringFilter getThongSoCaption1() {
        return thongSoCaption1;
    }

    public void setThongSoCaption1(StringFilter thongSoCaption1) {
        this.thongSoCaption1 = thongSoCaption1;
    }

    public BooleanFilter getThongSoCheck1() {
        return thongSoCheck1;
    }

    public void setThongSoCheck1(BooleanFilter thongSoCheck1) {
        this.thongSoCheck1 = thongSoCheck1;
    }

    public StringFilter getThongSoValue2() {
        return thongSoValue2;
    }

    public void setThongSoValue2(StringFilter thongSoValue2) {
        this.thongSoValue2 = thongSoValue2;
    }

    public StringFilter getThongSoCaption2() {
        return thongSoCaption2;
    }

    public void setThongSoCaption2(StringFilter thongSoCaption2) {
        this.thongSoCaption2 = thongSoCaption2;
    }

    public BooleanFilter getThongSoCheck2() {
        return thongSoCheck2;
    }

    public void setThongSoCheck2(BooleanFilter thongSoCheck2) {
        this.thongSoCheck2 = thongSoCheck2;
    }

    public StringFilter getThongSoValue3() {
        return thongSoValue3;
    }

    public void setThongSoValue3(StringFilter thongSoValue3) {
        this.thongSoValue3 = thongSoValue3;
    }

    public StringFilter getThongSoCaption3() {
        return thongSoCaption3;
    }

    public void setThongSoCaption3(StringFilter thongSoCaption3) {
        this.thongSoCaption3 = thongSoCaption3;
    }

    public BooleanFilter getThongSoCheck3() {
        return thongSoCheck3;
    }

    public void setThongSoCheck3(BooleanFilter thongSoCheck3) {
        this.thongSoCheck3 = thongSoCheck3;
    }

    public StringFilter getThongSoValue4() {
        return thongSoValue4;
    }

    public void setThongSoValue4(StringFilter thongSoValue4) {
        this.thongSoValue4 = thongSoValue4;
    }

    public StringFilter getThongSoCaption4() {
        return thongSoCaption4;
    }

    public void setThongSoCaption4(StringFilter thongSoCaption4) {
        this.thongSoCaption4 = thongSoCaption4;
    }

    public BooleanFilter getThongSoCheck4() {
        return thongSoCheck4;
    }

    public void setThongSoCheck4(BooleanFilter thongSoCheck4) {
        this.thongSoCheck4 = thongSoCheck4;
    }

    public StringFilter getThongSoValue5() {
        return thongSoValue5;
    }

    public void setThongSoValue5(StringFilter thongSoValue5) {
        this.thongSoValue5 = thongSoValue5;
    }

    public StringFilter getThongSoCaption5() {
        return thongSoCaption5;
    }

    public void setThongSoCaption5(StringFilter thongSoCaption5) {
        this.thongSoCaption5 = thongSoCaption5;
    }

    public BooleanFilter getThongSoCheck5() {
        return thongSoCheck5;
    }

    public void setThongSoCheck5(BooleanFilter thongSoCheck5) {
        this.thongSoCheck5 = thongSoCheck5;
    }

    public StringFilter getThongSoValue6() {
        return thongSoValue6;
    }

    public void setThongSoValue6(StringFilter thongSoValue6) {
        this.thongSoValue6 = thongSoValue6;
    }

    public StringFilter getThongSoCaption6() {
        return thongSoCaption6;
    }

    public void setThongSoCaption6(StringFilter thongSoCaption6) {
        this.thongSoCaption6 = thongSoCaption6;
    }

    public BooleanFilter getThongSoCheck6() {
        return thongSoCheck6;
    }

    public void setThongSoCheck6(BooleanFilter thongSoCheck6) {
        this.thongSoCheck6 = thongSoCheck6;
    }

    public StringFilter getThongSoValue7() {
        return thongSoValue7;
    }

    public void setThongSoValue7(StringFilter thongSoValue7) {
        this.thongSoValue7 = thongSoValue7;
    }

    public StringFilter getThongSoCaption7() {
        return thongSoCaption7;
    }

    public void setThongSoCaption7(StringFilter thongSoCaption7) {
        this.thongSoCaption7 = thongSoCaption7;
    }

    public BooleanFilter getThongSoCheck7() {
        return thongSoCheck7;
    }

    public void setThongSoCheck7(BooleanFilter thongSoCheck7) {
        this.thongSoCheck7 = thongSoCheck7;
    }

    public StringFilter getThongSoValue8() {
        return thongSoValue8;
    }

    public void setThongSoValue8(StringFilter thongSoValue8) {
        this.thongSoValue8 = thongSoValue8;
    }

    public StringFilter getThongSoCaption8() {
        return thongSoCaption8;
    }

    public void setThongSoCaption8(StringFilter thongSoCaption8) {
        this.thongSoCaption8 = thongSoCaption8;
    }

    public BooleanFilter getThongSoCheck8() {
        return thongSoCheck8;
    }

    public void setThongSoCheck8(BooleanFilter thongSoCheck8) {
        this.thongSoCheck8 = thongSoCheck8;
    }

    public StringFilter getThongSoValue9() {
        return thongSoValue9;
    }

    public void setThongSoValue9(StringFilter thongSoValue9) {
        this.thongSoValue9 = thongSoValue9;
    }

    public StringFilter getThongSoCaption9() {
        return thongSoCaption9;
    }

    public void setThongSoCaption9(StringFilter thongSoCaption9) {
        this.thongSoCaption9 = thongSoCaption9;
    }

    public BooleanFilter getThongSoCheck9() {
        return thongSoCheck9;
    }

    public void setThongSoCheck9(BooleanFilter thongSoCheck9) {
        this.thongSoCheck9 = thongSoCheck9;
    }

    public StringFilter getThongSoValue10() {
        return thongSoValue10;
    }

    public void setThongSoValue10(StringFilter thongSoValue10) {
        this.thongSoValue10 = thongSoValue10;
    }

    public StringFilter getThongSoCaption10() {
        return thongSoCaption10;
    }

    public void setThongSoCaption10(StringFilter thongSoCaption10) {
        this.thongSoCaption10 = thongSoCaption10;
    }

    public BooleanFilter getThongSoCheck10() {
        return thongSoCheck10;
    }

    public void setThongSoCheck10(BooleanFilter thongSoCheck10) {
        this.thongSoCheck10 = thongSoCheck10;
    }

    public StringFilter getColor() {
        return color;
    }

    public void setColor(StringFilter color) {
        this.color = color;
    }

    public StringFilter getHinhMatTruocURL() {
        return hinhMatTruocURL;
    }

    public void setHinhMatTruocURL(StringFilter hinhMatTruocURL) {
        this.hinhMatTruocURL = hinhMatTruocURL;
    }

    public StringFilter getHinhMatSauURL() {
        return hinhMatSauURL;
    }

    public void setHinhMatSauURL(StringFilter hinhMatSauURL) {
        this.hinhMatSauURL = hinhMatSauURL;
    }

    public LongFilter getProKhachHangId() {
        return proKhachHangId;
    }

    public void setProKhachHangId(LongFilter proKhachHangId) {
        this.proKhachHangId = proKhachHangId;
    }

    public LongFilter getProChungLoaiSanPhamId() {
        return proChungLoaiSanPhamId;
    }

    public void setProChungLoaiSanPhamId(LongFilter proChungLoaiSanPhamId) {
        this.proChungLoaiSanPhamId = proChungLoaiSanPhamId;
    }

    public LongFilter getProTenMuaId() {
        return proTenMuaId;
    }

    public void setProTenMuaId(LongFilter proTenMuaId) {
        this.proTenMuaId = proTenMuaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProSanPhamCriteria that = (ProSanPhamCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maSanPham, that.maSanPham) &&
            Objects.equals(maDonHang, that.maDonHang) &&
            Objects.equals(po, that.po) &&
            Objects.equals(soLuongSanXuat, that.soLuongSanXuat) &&
            Objects.equals(thongSoValue1, that.thongSoValue1) &&
            Objects.equals(thongSoCaption1, that.thongSoCaption1) &&
            Objects.equals(thongSoCheck1, that.thongSoCheck1) &&
            Objects.equals(thongSoValue2, that.thongSoValue2) &&
            Objects.equals(thongSoCaption2, that.thongSoCaption2) &&
            Objects.equals(thongSoCheck2, that.thongSoCheck2) &&
            Objects.equals(thongSoValue3, that.thongSoValue3) &&
            Objects.equals(thongSoCaption3, that.thongSoCaption3) &&
            Objects.equals(thongSoCheck3, that.thongSoCheck3) &&
            Objects.equals(thongSoValue4, that.thongSoValue4) &&
            Objects.equals(thongSoCaption4, that.thongSoCaption4) &&
            Objects.equals(thongSoCheck4, that.thongSoCheck4) &&
            Objects.equals(thongSoValue5, that.thongSoValue5) &&
            Objects.equals(thongSoCaption5, that.thongSoCaption5) &&
            Objects.equals(thongSoCheck5, that.thongSoCheck5) &&
            Objects.equals(thongSoValue6, that.thongSoValue6) &&
            Objects.equals(thongSoCaption6, that.thongSoCaption6) &&
            Objects.equals(thongSoCheck6, that.thongSoCheck6) &&
            Objects.equals(thongSoValue7, that.thongSoValue7) &&
            Objects.equals(thongSoCaption7, that.thongSoCaption7) &&
            Objects.equals(thongSoCheck7, that.thongSoCheck7) &&
            Objects.equals(thongSoValue8, that.thongSoValue8) &&
            Objects.equals(thongSoCaption8, that.thongSoCaption8) &&
            Objects.equals(thongSoCheck8, that.thongSoCheck8) &&
            Objects.equals(thongSoValue9, that.thongSoValue9) &&
            Objects.equals(thongSoCaption9, that.thongSoCaption9) &&
            Objects.equals(thongSoCheck9, that.thongSoCheck9) &&
            Objects.equals(thongSoValue10, that.thongSoValue10) &&
            Objects.equals(thongSoCaption10, that.thongSoCaption10) &&
            Objects.equals(thongSoCheck10, that.thongSoCheck10) &&
            Objects.equals(color, that.color) &&
            Objects.equals(hinhMatTruocURL, that.hinhMatTruocURL) &&
            Objects.equals(hinhMatSauURL, that.hinhMatSauURL) &&
            Objects.equals(proKhachHangId, that.proKhachHangId) &&
            Objects.equals(proChungLoaiSanPhamId, that.proChungLoaiSanPhamId) &&
            Objects.equals(proTenMuaId, that.proTenMuaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maSanPham,
        maDonHang,
        po,
        soLuongSanXuat,
        thongSoValue1,
        thongSoCaption1,
        thongSoCheck1,
        thongSoValue2,
        thongSoCaption2,
        thongSoCheck2,
        thongSoValue3,
        thongSoCaption3,
        thongSoCheck3,
        thongSoValue4,
        thongSoCaption4,
        thongSoCheck4,
        thongSoValue5,
        thongSoCaption5,
        thongSoCheck5,
        thongSoValue6,
        thongSoCaption6,
        thongSoCheck6,
        thongSoValue7,
        thongSoCaption7,
        thongSoCheck7,
        thongSoValue8,
        thongSoCaption8,
        thongSoCheck8,
        thongSoValue9,
        thongSoCaption9,
        thongSoCheck9,
        thongSoValue10,
        thongSoCaption10,
        thongSoCheck10,
        color,
        hinhMatTruocURL,
        hinhMatSauURL,
        proKhachHangId,
        proChungLoaiSanPhamId,
        proTenMuaId
        );
    }

    @Override
    public String toString() {
        return "ProSanPhamCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maSanPham != null ? "maSanPham=" + maSanPham + ", " : "") +
                (maDonHang != null ? "maDonHang=" + maDonHang + ", " : "") +
                (po != null ? "po=" + po + ", " : "") +
                (soLuongSanXuat != null ? "soLuongSanXuat=" + soLuongSanXuat + ", " : "") +
                (thongSoValue1 != null ? "thongSoValue1=" + thongSoValue1 + ", " : "") +
                (thongSoCaption1 != null ? "thongSoCaption1=" + thongSoCaption1 + ", " : "") +
                (thongSoCheck1 != null ? "thongSoCheck1=" + thongSoCheck1 + ", " : "") +
                (thongSoValue2 != null ? "thongSoValue2=" + thongSoValue2 + ", " : "") +
                (thongSoCaption2 != null ? "thongSoCaption2=" + thongSoCaption2 + ", " : "") +
                (thongSoCheck2 != null ? "thongSoCheck2=" + thongSoCheck2 + ", " : "") +
                (thongSoValue3 != null ? "thongSoValue3=" + thongSoValue3 + ", " : "") +
                (thongSoCaption3 != null ? "thongSoCaption3=" + thongSoCaption3 + ", " : "") +
                (thongSoCheck3 != null ? "thongSoCheck3=" + thongSoCheck3 + ", " : "") +
                (thongSoValue4 != null ? "thongSoValue4=" + thongSoValue4 + ", " : "") +
                (thongSoCaption4 != null ? "thongSoCaption4=" + thongSoCaption4 + ", " : "") +
                (thongSoCheck4 != null ? "thongSoCheck4=" + thongSoCheck4 + ", " : "") +
                (thongSoValue5 != null ? "thongSoValue5=" + thongSoValue5 + ", " : "") +
                (thongSoCaption5 != null ? "thongSoCaption5=" + thongSoCaption5 + ", " : "") +
                (thongSoCheck5 != null ? "thongSoCheck5=" + thongSoCheck5 + ", " : "") +
                (thongSoValue6 != null ? "thongSoValue6=" + thongSoValue6 + ", " : "") +
                (thongSoCaption6 != null ? "thongSoCaption6=" + thongSoCaption6 + ", " : "") +
                (thongSoCheck6 != null ? "thongSoCheck6=" + thongSoCheck6 + ", " : "") +
                (thongSoValue7 != null ? "thongSoValue7=" + thongSoValue7 + ", " : "") +
                (thongSoCaption7 != null ? "thongSoCaption7=" + thongSoCaption7 + ", " : "") +
                (thongSoCheck7 != null ? "thongSoCheck7=" + thongSoCheck7 + ", " : "") +
                (thongSoValue8 != null ? "thongSoValue8=" + thongSoValue8 + ", " : "") +
                (thongSoCaption8 != null ? "thongSoCaption8=" + thongSoCaption8 + ", " : "") +
                (thongSoCheck8 != null ? "thongSoCheck8=" + thongSoCheck8 + ", " : "") +
                (thongSoValue9 != null ? "thongSoValue9=" + thongSoValue9 + ", " : "") +
                (thongSoCaption9 != null ? "thongSoCaption9=" + thongSoCaption9 + ", " : "") +
                (thongSoCheck9 != null ? "thongSoCheck9=" + thongSoCheck9 + ", " : "") +
                (thongSoValue10 != null ? "thongSoValue10=" + thongSoValue10 + ", " : "") +
                (thongSoCaption10 != null ? "thongSoCaption10=" + thongSoCaption10 + ", " : "") +
                (thongSoCheck10 != null ? "thongSoCheck10=" + thongSoCheck10 + ", " : "") +
                (color != null ? "color=" + color + ", " : "") +
                (hinhMatTruocURL != null ? "hinhMatTruocURL=" + hinhMatTruocURL + ", " : "") +
                (hinhMatSauURL != null ? "hinhMatSauURL=" + hinhMatSauURL + ", " : "") +
                (proKhachHangId != null ? "proKhachHangId=" + proKhachHangId + ", " : "") +
                (proChungLoaiSanPhamId != null ? "proChungLoaiSanPhamId=" + proChungLoaiSanPhamId + ", " : "") +
                (proTenMuaId != null ? "proTenMuaId=" + proTenMuaId + ", " : "") +
            "}";
    }

}
