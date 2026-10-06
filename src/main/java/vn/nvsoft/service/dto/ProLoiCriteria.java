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
 * Criteria class for the {@link vn.nvsoft.domain.ProLoi} entity. This class is used
 * in {@link vn.nvsoft.web.rest.ProLoiResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pro-lois?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProLoiCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maLoi;

    private StringFilter cusCode;

    private StringFilter tenLoi;

    private StringFilter tenTiengAnh;

    private StringFilter label;

    private IntegerFilter stt;

    private StringFilter mauSac;

    private BooleanFilter qc;

    private BooleanFilter qa;

    private BooleanFilter checked;

    private StringFilter ghiChu;

    private LongFilter proCumLoiId;

    public ProLoiCriteria(){
    }

    public ProLoiCriteria(ProLoiCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.maLoi = other.maLoi == null ? null : other.maLoi.copy();
        this.cusCode = other.cusCode == null ? null : other.cusCode.copy();
        this.tenLoi = other.tenLoi == null ? null : other.tenLoi.copy();
        this.tenTiengAnh = other.tenTiengAnh == null ? null : other.tenTiengAnh.copy();
        this.label = other.label == null ? null : other.label.copy();
        this.stt = other.stt == null ? null : other.stt.copy();
        this.mauSac = other.mauSac == null ? null : other.mauSac.copy();
        this.qc = other.qc == null ? null : other.qc.copy();
        this.qa = other.qa == null ? null : other.qa.copy();
        this.checked = other.checked == null ? null : other.checked.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.proCumLoiId = other.proCumLoiId == null ? null : other.proCumLoiId.copy();
    }

    @Override
    public ProLoiCriteria copy() {
        return new ProLoiCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(StringFilter maLoi) {
        this.maLoi = maLoi;
    }

    public StringFilter getCusCode() {
        return cusCode;
    }

    public void setCusCode(StringFilter cusCode) {
        this.cusCode = cusCode;
    }

    public StringFilter getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(StringFilter tenLoi) {
        this.tenLoi = tenLoi;
    }

    public StringFilter getTenTiengAnh() {
        return tenTiengAnh;
    }

    public void setTenTiengAnh(StringFilter tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    public StringFilter getLabel() {
        return label;
    }

    public void setLabel(StringFilter label) {
        this.label = label;
    }

    public IntegerFilter getStt() {
        return stt;
    }

    public void setStt(IntegerFilter stt) {
        this.stt = stt;
    }

    public StringFilter getMauSac() {
        return mauSac;
    }

    public void setMauSac(StringFilter mauSac) {
        this.mauSac = mauSac;
    }

    public BooleanFilter getQc() {
        return qc;
    }

    public void setQc(BooleanFilter qc) {
        this.qc = qc;
    }

    public BooleanFilter getQa() {
        return qa;
    }

    public void setQa(BooleanFilter qa) {
        this.qa = qa;
    }

    public BooleanFilter getChecked() {
        return checked;
    }

    public void setChecked(BooleanFilter checked) {
        this.checked = checked;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LongFilter getProCumLoiId() {
        return proCumLoiId;
    }

    public void setProCumLoiId(LongFilter proCumLoiId) {
        this.proCumLoiId = proCumLoiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProLoiCriteria that = (ProLoiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maLoi, that.maLoi) &&
            Objects.equals(cusCode, that.cusCode) &&
            Objects.equals(tenLoi, that.tenLoi) &&
            Objects.equals(tenTiengAnh, that.tenTiengAnh) &&
            Objects.equals(label, that.label) &&
            Objects.equals(stt, that.stt) &&
            Objects.equals(mauSac, that.mauSac) &&
            Objects.equals(qc, that.qc) &&
            Objects.equals(qa, that.qa) &&
            Objects.equals(checked, that.checked) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(proCumLoiId, that.proCumLoiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maLoi,
        cusCode,
        tenLoi,
        tenTiengAnh,
        label,
        stt,
        mauSac,
        qc,
        qa,
        checked,
        ghiChu,
        proCumLoiId
        );
    }

    @Override
    public String toString() {
        return "ProLoiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maLoi != null ? "maLoi=" + maLoi + ", " : "") +
                (cusCode != null ? "cusCode=" + cusCode + ", " : "") +
                (tenLoi != null ? "tenLoi=" + tenLoi + ", " : "") +
                (tenTiengAnh != null ? "tenTiengAnh=" + tenTiengAnh + ", " : "") +
                (label != null ? "label=" + label + ", " : "") +
                (stt != null ? "stt=" + stt + ", " : "") +
                (mauSac != null ? "mauSac=" + mauSac + ", " : "") +
                (qc != null ? "qc=" + qc + ", " : "") +
                (qa != null ? "qa=" + qa + ", " : "") +
                (checked != null ? "checked=" + checked + ", " : "") +
                (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
                (proCumLoiId != null ? "proCumLoiId=" + proCumLoiId + ", " : "") +
            "}";
    }

}
