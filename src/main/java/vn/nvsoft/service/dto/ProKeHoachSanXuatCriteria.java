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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link vn.nvsoft.domain.ProKeHoachSanXuat} entity. This class is used
 * in {@link vn.nvsoft.web.rest.ProKeHoachSanXuatResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /pro-ke-hoach-san-xuats?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProKeHoachSanXuatCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter ngaySanXuat;

    private IntegerFilter soLuongYeuCau;

    private IntegerFilter tinhTrang;

    private StringFilter ghiChu;

    private IntegerFilter sam;

    private IntegerFilter lotNo;

    private LongFilter proChuyenId;

    private LongFilter proSanPhamId;

    public ProKeHoachSanXuatCriteria(){
    }

    public ProKeHoachSanXuatCriteria(ProKeHoachSanXuatCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.ngaySanXuat = other.ngaySanXuat == null ? null : other.ngaySanXuat.copy();
        this.soLuongYeuCau = other.soLuongYeuCau == null ? null : other.soLuongYeuCau.copy();
        this.tinhTrang = other.tinhTrang == null ? null : other.tinhTrang.copy();
        this.ghiChu = other.ghiChu == null ? null : other.ghiChu.copy();
        this.sam = other.sam == null ? null : other.sam.copy();
        this.lotNo = other.lotNo == null ? null : other.lotNo.copy();
        this.proChuyenId = other.proChuyenId == null ? null : other.proChuyenId.copy();
        this.proSanPhamId = other.proSanPhamId == null ? null : other.proSanPhamId.copy();
    }

    @Override
    public ProKeHoachSanXuatCriteria copy() {
        return new ProKeHoachSanXuatCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(InstantFilter ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public IntegerFilter getSoLuongYeuCau() {
        return soLuongYeuCau;
    }

    public void setSoLuongYeuCau(IntegerFilter soLuongYeuCau) {
        this.soLuongYeuCau = soLuongYeuCau;
    }

    public IntegerFilter getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(IntegerFilter tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
    }

    public IntegerFilter getSam() {
        return sam;
    }

    public void setSam(IntegerFilter sam) {
        this.sam = sam;
    }

    public IntegerFilter getLotNo() {
        return lotNo;
    }

    public void setLotNo(IntegerFilter lotNo) {
        this.lotNo = lotNo;
    }

    public LongFilter getProChuyenId() {
        return proChuyenId;
    }

    public void setProChuyenId(LongFilter proChuyenId) {
        this.proChuyenId = proChuyenId;
    }

    public LongFilter getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(LongFilter proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProKeHoachSanXuatCriteria that = (ProKeHoachSanXuatCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ngaySanXuat, that.ngaySanXuat) &&
            Objects.equals(soLuongYeuCau, that.soLuongYeuCau) &&
            Objects.equals(tinhTrang, that.tinhTrang) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(sam, that.sam) &&
            Objects.equals(lotNo, that.lotNo) &&
            Objects.equals(proChuyenId, that.proChuyenId) &&
            Objects.equals(proSanPhamId, that.proSanPhamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ngaySanXuat,
        soLuongYeuCau,
        tinhTrang,
        ghiChu,
        sam,
        lotNo,
        proChuyenId,
        proSanPhamId
        );
    }

    @Override
    public String toString() {
        return "ProKeHoachSanXuatCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ngaySanXuat != null ? "ngaySanXuat=" + ngaySanXuat + ", " : "") +
                (soLuongYeuCau != null ? "soLuongYeuCau=" + soLuongYeuCau + ", " : "") +
                (tinhTrang != null ? "tinhTrang=" + tinhTrang + ", " : "") +
                (ghiChu != null ? "ghiChu=" + ghiChu + ", " : "") +
                (sam != null ? "sam=" + sam + ", " : "") +
                (lotNo != null ? "lotNo=" + lotNo + ", " : "") +
                (proChuyenId != null ? "proChuyenId=" + proChuyenId + ", " : "") +
                (proSanPhamId != null ? "proSanPhamId=" + proSanPhamId + ", " : "") +
            "}";
    }

}
