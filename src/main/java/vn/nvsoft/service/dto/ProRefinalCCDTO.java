package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProRefinalCC} entity.
 */
public class ProRefinalCCDTO implements Serializable {

    private Long id;

    private Long khxhId;

    private String maHang;

    private String poNo;

    private Instant ngayKiemFinal;

    private String tenChuyen;

    private Instant ngayGioKiem;

    private Integer soLuongKiemAql;

    private Integer soLuong;

    private Float doAm;

    private Integer tongSoLoi;

    private String soThuTuThung;

    private String doKimMd;

    private String aql;

    private Integer maxLoiChoPhep;

    private Boolean completed;

    private String nguoiKiem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKhxhId() {
        return khxhId;
    }

    public void setKhxhId(Long khxhId) {
        this.khxhId = khxhId;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Instant getNgayKiemFinal() {
        return ngayKiemFinal;
    }

    public void setNgayKiemFinal(Instant ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Instant getNgayGioKiem() {
        return ngayGioKiem;
    }

    public void setNgayGioKiem(Instant ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public Integer getSoLuongKiemAql() {
        return soLuongKiemAql;
    }

    public void setSoLuongKiemAql(Integer soLuongKiemAql) {
        this.soLuongKiemAql = soLuongKiemAql;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDoAm() {
        return doAm;
    }

    public void setDoAm(Float doAm) {
        this.doAm = doAm;
    }

    public Integer getTongSoLoi() {
        return tongSoLoi;
    }

    public void setTongSoLoi(Integer tongSoLoi) {
        this.tongSoLoi = tongSoLoi;
    }

    public String getSoThuTuThung() {
        return soThuTuThung;
    }

    public void setSoThuTuThung(String soThuTuThung) {
        this.soThuTuThung = soThuTuThung;
    }

    public String getDoKimMd() {
        return doKimMd;
    }

    public void setDoKimMd(String doKimMd) {
        this.doKimMd = doKimMd;
    }

    public String getAql() {
        return aql;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public Integer getMaxLoiChoPhep() {
        return maxLoiChoPhep;
    }

    public void setMaxLoiChoPhep(Integer maxLoiChoPhep) {
        this.maxLoiChoPhep = maxLoiChoPhep;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getNguoiKiem() {
        return nguoiKiem;
    }

    public void setNguoiKiem(String nguoiKiem) {
        this.nguoiKiem = nguoiKiem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProRefinalCCDTO proRefinalCCDTO = (ProRefinalCCDTO) o;
        if (proRefinalCCDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proRefinalCCDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProRefinalCCDTO{" +
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
