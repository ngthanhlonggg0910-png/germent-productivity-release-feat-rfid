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
 * Criteria class for the {@link vn.nvsoft.domain.ProKhachHang} entity. This class is used
 * in {@link vn.nvsoft.web.rest.ProKhachHangResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pro-khach-hangs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProKhachHangCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ten;

    private StringFilter nhom;

    private StringFilter diaChi;

    public ProKhachHangCriteria(){
    }

    public ProKhachHangCriteria(ProKhachHangCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ten = other.ten == null ? null : other.ten.copy();
        this.nhom = other.nhom == null ? null : other.nhom.copy();
        this.diaChi = other.diaChi == null ? null : other.diaChi.copy();
    }

    @Override
    public ProKhachHangCriteria copy() {
        return new ProKhachHangCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTen() {
        return ten;
    }

    public void setTen(StringFilter ten) {
        this.ten = ten;
    }

    public StringFilter getNhom() {
        return nhom;
    }

    public void setNhom(StringFilter nhom) {
        this.nhom = nhom;
    }

    public StringFilter getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(StringFilter diaChi) {
        this.diaChi = diaChi;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProKhachHangCriteria that = (ProKhachHangCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ten, that.ten) &&
            Objects.equals(nhom, that.nhom) &&
            Objects.equals(diaChi, that.diaChi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ten,
        nhom,
        diaChi
        );
    }

    @Override
    public String toString() {
        return "ProKhachHangCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ten != null ? "ten=" + ten + ", " : "") +
                (nhom != null ? "nhom=" + nhom + ", " : "") +
                (diaChi != null ? "diaChi=" + diaChi + ", " : "") +
            "}";
    }

}
