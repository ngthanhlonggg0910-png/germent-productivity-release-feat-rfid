package vn.nvsoft.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProNSCL} entity.
 */
public class ProNSCLDTO implements Serializable {

    private Long id;

    private String taiKhoan;

    private Instant ngaySanXuat;

    private Integer stt;

    private Integer mucTieuNgay;

    private Integer thucHienNgay;

    private Integer loiNgay;

    private Integer suaNgay;

    private Integer btpCap;
    private Instant ngayTao = Instant.now();

    @NotNull
    private Integer qty1h;

    @NotNull
    private Integer qty2h;

    @NotNull
    private Integer qty3h;

    @NotNull
    private Integer qty4h;

    @NotNull
    private Integer qty5h;

    @NotNull
    private Integer qty6h;

    @NotNull
    private Integer qty7h;

    @NotNull
    private Integer qty8h;

    @NotNull
    private Integer qty9h;

    @NotNull
    private Integer qty10h;

    @NotNull
    private Integer qty11h;

    @NotNull
    private Integer qty12h;

    @NotNull
    private Integer qty13h;

    @NotNull
    private Integer qty14h;

    @NotNull
    private Integer reject1h;

    @NotNull
    private Integer reject2h;

    @NotNull
    private Integer reject3h;

    @NotNull
    private Integer reject4h;

    @NotNull
    private Integer reject5h;

    @NotNull
    private Integer reject6h;

    @NotNull
    private Integer reject7h;

    @NotNull
    private Integer reject8h;

    @NotNull
    private Integer reject9h;

    @NotNull
    private Integer reject10h;

    @NotNull
    private Integer reject11h;

    @NotNull
    private Integer reject12h;

    @NotNull
    private Integer reject13h;

    @NotNull
    private Integer reject14h;

    @NotNull
    private Integer man1h;

    @NotNull
    private Integer man2h;

    @NotNull
    private Integer man3h;

    @NotNull
    private Integer man4h;

    @NotNull
    private Integer man5h;

    @NotNull
    private Integer man6h;

    @NotNull
    private Integer man7h;

    @NotNull
    private Integer man8h;

    @NotNull
    private Integer man9h;

    @NotNull
    private Integer man10h;

    @NotNull
    private Integer man11h;

    @NotNull
    private Integer man12h;

    @NotNull
    private Integer man13h;

    @NotNull
    private Integer man14h;

    @NotNull
    private Integer manOnly7Hours;

    @NotNull
    private Integer fix1h;

    @NotNull
    private Integer fix2h;

    @NotNull
    private Integer fix3h;

    @NotNull
    private Integer fix4h;

    @NotNull
    private Integer fix5h;

    @NotNull
    private Integer fix6h;

    @NotNull
    private Integer fix7h;

    @NotNull
    private Integer fix8h;

    @NotNull
    private Integer fix9h;

    @NotNull
    private Integer fix10h;

    @NotNull
    private Integer fix11h;

    @NotNull
    private Integer fix12h;

    @NotNull
    private Integer fix13h;

    @NotNull
    private Integer fix14h;


    private Long proKeHoachSanXuatId;

    private Long proCheckPointId;

    private String proCheckPointTen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Instant getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getMucTieuNgay() {
        return mucTieuNgay;
    }

    public void setMucTieuNgay(Integer mucTieuNgay) {
        this.mucTieuNgay = mucTieuNgay;
    }

    public Integer getThucHienNgay() {
        return thucHienNgay;
    }

    public void setThucHienNgay(Integer thucHienNgay) {
        this.thucHienNgay = thucHienNgay;
    }

    public Integer getLoiNgay() {
        return loiNgay;
    }

    public void setLoiNgay(Integer loiNgay) {
        this.loiNgay = loiNgay;
    }

    public Integer getSuaNgay() {
        return suaNgay;
    }

    public void setSuaNgay(Integer suaNgay) {
        this.suaNgay = suaNgay;
    }

    public Integer getBtpCap() {
        return btpCap;
    }

    public void setBtpCap(Integer btpCap) {
        this.btpCap = btpCap;
    }

    public Integer getQty1h() {
        return qty1h;
    }

    public void setQty1h(Integer qty1h) {
        this.qty1h = qty1h;
    }

    public Integer getQty2h() {
        return qty2h;
    }

    public void setQty2h(Integer qty2h) {
        this.qty2h = qty2h;
    }

    public Integer getQty3h() {
        return qty3h;
    }

    public void setQty3h(Integer qty3h) {
        this.qty3h = qty3h;
    }

    public Integer getQty4h() {
        return qty4h;
    }

    public void setQty4h(Integer qty4h) {
        this.qty4h = qty4h;
    }

    public Integer getQty5h() {
        return qty5h;
    }

    public void setQty5h(Integer qty5h) {
        this.qty5h = qty5h;
    }

    public Integer getQty6h() {
        return qty6h;
    }

    public void setQty6h(Integer qty6h) {
        this.qty6h = qty6h;
    }

    public Integer getQty7h() {
        return qty7h;
    }

    public void setQty7h(Integer qty7h) {
        this.qty7h = qty7h;
    }

    public Integer getQty8h() {
        return qty8h;
    }

    public void setQty8h(Integer qty8h) {
        this.qty8h = qty8h;
    }

    public Integer getQty9h() {
        return qty9h;
    }

    public void setQty9h(Integer qty9h) {
        this.qty9h = qty9h;
    }

    public Integer getQty10h() {
        return qty10h;
    }

    public void setQty10h(Integer qty10h) {
        this.qty10h = qty10h;
    }

    public Integer getQty11h() {
        return qty11h;
    }

    public void setQty11h(Integer qty11h) {
        this.qty11h = qty11h;
    }

    public Integer getQty12h() {
        return qty12h;
    }

    public void setQty12h(Integer qty12h) {
        this.qty12h = qty12h;
    }

    public Integer getQty13h() {
        return qty13h;
    }

    public void setQty13h(Integer qty13h) {
        this.qty13h = qty13h;
    }

    public Integer getQty14h() {
        return qty14h;
    }

    public void setQty14h(Integer qty14h) {
        this.qty14h = qty14h;
    }

    public Integer getReject1h() {
        return reject1h;
    }

    public void setReject1h(Integer reject1h) {
        this.reject1h = reject1h;
    }

    public Integer getReject2h() {
        return reject2h;
    }

    public void setReject2h(Integer reject2h) {
        this.reject2h = reject2h;
    }

    public Integer getReject3h() {
        return reject3h;
    }

    public void setReject3h(Integer reject3h) {
        this.reject3h = reject3h;
    }

    public Integer getReject4h() {
        return reject4h;
    }

    public void setReject4h(Integer reject4h) {
        this.reject4h = reject4h;
    }

    public Integer getReject5h() {
        return reject5h;
    }

    public void setReject5h(Integer reject5h) {
        this.reject5h = reject5h;
    }

    public Integer getReject6h() {
        return reject6h;
    }

    public void setReject6h(Integer reject6h) {
        this.reject6h = reject6h;
    }

    public Integer getReject7h() {
        return reject7h;
    }

    public void setReject7h(Integer reject7h) {
        this.reject7h = reject7h;
    }

    public Integer getReject8h() {
        return reject8h;
    }

    public void setReject8h(Integer reject8h) {
        this.reject8h = reject8h;
    }

    public Integer getReject9h() {
        return reject9h;
    }

    public void setReject9h(Integer reject9h) {
        this.reject9h = reject9h;
    }

    public Integer getReject10h() {
        return reject10h;
    }

    public void setReject10h(Integer reject10h) {
        this.reject10h = reject10h;
    }

    public Integer getReject11h() {
        return reject11h;
    }

    public void setReject11h(Integer reject11h) {
        this.reject11h = reject11h;
    }

    public Integer getReject12h() {
        return reject12h;
    }

    public void setReject12h(Integer reject12h) {
        this.reject12h = reject12h;
    }

    public Integer getReject13h() {
        return reject13h;
    }

    public void setReject13h(Integer reject13h) {
        this.reject13h = reject13h;
    }

    public Integer getReject14h() {
        return reject14h;
    }

    public void setReject14h(Integer reject14h) {
        this.reject14h = reject14h;
    }

    public Integer getMan1h() {
        return man1h;
    }

    public void setMan1h(Integer man1h) {
        this.man1h = man1h;
    }

    public Integer getMan2h() {
        return man2h;
    }

    public void setMan2h(Integer man2h) {
        this.man2h = man2h;
    }

    public Integer getMan3h() {
        return man3h;
    }

    public void setMan3h(Integer man3h) {
        this.man3h = man3h;
    }

    public Integer getMan4h() {
        return man4h;
    }

    public void setMan4h(Integer man4h) {
        this.man4h = man4h;
    }

    public Integer getMan5h() {
        return man5h;
    }

    public void setMan5h(Integer man5h) {
        this.man5h = man5h;
    }

    public Integer getMan6h() {
        return man6h;
    }

    public void setMan6h(Integer man6h) {
        this.man6h = man6h;
    }

    public Integer getMan7h() {
        return man7h;
    }

    public void setMan7h(Integer man7h) {
        this.man7h = man7h;
    }

    public Integer getMan8h() {
        return man8h;
    }

    public void setMan8h(Integer man8h) {
        this.man8h = man8h;
    }

    public Integer getMan9h() {
        return man9h;
    }

    public void setMan9h(Integer man9h) {
        this.man9h = man9h;
    }

    public Integer getMan10h() {
        return man10h;
    }

    public void setMan10h(Integer man10h) {
        this.man10h = man10h;
    }

    public Integer getMan11h() {
        return man11h;
    }

    public void setMan11h(Integer man11h) {
        this.man11h = man11h;
    }

    public Integer getMan12h() {
        return man12h;
    }

    public void setMan12h(Integer man12h) {
        this.man12h = man12h;
    }

    public Integer getMan13h() {
        return man13h;
    }

    public void setMan13h(Integer man13h) {
        this.man13h = man13h;
    }

    public Integer getMan14h() {
        return man14h;
    }

    public void setMan14h(Integer man14h) {
        this.man14h = man14h;
    }

    public Integer getManOnly7Hours() {
        return manOnly7Hours;
    }

    public void setManOnly7Hours(Integer manOnly7Hours) {
        this.manOnly7Hours = manOnly7Hours;
    }

    public Integer getFix1h() {
        return fix1h;
    }

    public void setFix1h(Integer fix1h) {
        this.fix1h = fix1h;
    }

    public Integer getFix2h() {
        return fix2h;
    }

    public void setFix2h(Integer fix2h) {
        this.fix2h = fix2h;
    }

    public Integer getFix3h() {
        return fix3h;
    }

    public void setFix3h(Integer fix3h) {
        this.fix3h = fix3h;
    }

    public Integer getFix4h() {
        return fix4h;
    }

    public void setFix4h(Integer fix4h) {
        this.fix4h = fix4h;
    }

    public Integer getFix5h() {
        return fix5h;
    }

    public void setFix5h(Integer fix5h) {
        this.fix5h = fix5h;
    }

    public Integer getFix6h() {
        return fix6h;
    }

    public void setFix6h(Integer fix6h) {
        this.fix6h = fix6h;
    }

    public Integer getFix7h() {
        return fix7h;
    }

    public void setFix7h(Integer fix7h) {
        this.fix7h = fix7h;
    }

    public Integer getFix8h() {
        return fix8h;
    }

    public void setFix8h(Integer fix8h) {
        this.fix8h = fix8h;
    }

    public Integer getFix9h() {
        return fix9h;
    }

    public void setFix9h(Integer fix9h) {
        this.fix9h = fix9h;
    }

    public Integer getFix10h() {
        return fix10h;
    }

    public void setFix10h(Integer fix10h) {
        this.fix10h = fix10h;
    }

    public Integer getFix11h() {
        return fix11h;
    }

    public void setFix11h(Integer fix11h) {
        this.fix11h = fix11h;
    }

    public Integer getFix12h() {
        return fix12h;
    }

    public void setFix12h(Integer fix12h) {
        this.fix12h = fix12h;
    }

    public Integer getFix13h() {
        return fix13h;
    }

    public void setFix13h(Integer fix13h) {
        this.fix13h = fix13h;
    }

    public Integer getFix14h() {
        return fix14h;
    }

    public void setFix14h(Integer fix14h) {
        this.fix14h = fix14h;
    }

    public Long getProKeHoachSanXuatId() {
        return proKeHoachSanXuatId;
    }

    public void setProKeHoachSanXuatId(Long proKeHoachSanXuatId) {
        this.proKeHoachSanXuatId = proKeHoachSanXuatId;
    }

    public Long getProCheckPointId() {
        return proCheckPointId;
    }

    public void setProCheckPointId(Long proCheckPointId) {
        this.proCheckPointId = proCheckPointId;
    }

    public String getProCheckPointTen() {
        return proCheckPointTen;
    }

    public void setProCheckPointTen(String proCheckPointTen) {
        this.proCheckPointTen = proCheckPointTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProNSCLDTO proNSCLDTO = (ProNSCLDTO) o;
        if (proNSCLDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proNSCLDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProNSCLDTO{" +
            "id=" + getId() +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", ngaySanXuat='" + getNgaySanXuat() + "'" +
            ", stt=" + getStt() +
            ", mucTieuNgay=" + getMucTieuNgay() +
            ", thucHienNgay=" + getThucHienNgay() +
            ", loiNgay=" + getLoiNgay() +
            ", suaNgay=" + getSuaNgay() +
            ", btpCap=" + getBtpCap() +
            ", qty1h=" + getQty1h() +
            ", qty2h=" + getQty2h() +
            ", qty3h=" + getQty3h() +
            ", qty4h=" + getQty4h() +
            ", qty5h=" + getQty5h() +
            ", qty6h=" + getQty6h() +
            ", qty7h=" + getQty7h() +
            ", qty8h=" + getQty8h() +
            ", qty9h=" + getQty9h() +
            ", qty10h=" + getQty10h() +
            ", qty11h=" + getQty11h() +
            ", qty12h=" + getQty12h() +
            ", qty13h=" + getQty13h() +
            ", qty14h=" + getQty14h() +
            ", reject1h=" + getReject1h() +
            ", reject2h=" + getReject2h() +
            ", reject3h=" + getReject3h() +
            ", reject4h=" + getReject4h() +
            ", reject5h=" + getReject5h() +
            ", reject6h=" + getReject6h() +
            ", reject7h=" + getReject7h() +
            ", reject8h=" + getReject8h() +
            ", reject9h=" + getReject9h() +
            ", reject10h=" + getReject10h() +
            ", reject11h=" + getReject11h() +
            ", reject12h=" + getReject12h() +
            ", reject13h=" + getReject13h() +
            ", reject14h=" + getReject14h() +
            ", man1h=" + getMan1h() +
            ", man2h=" + getMan2h() +
            ", man3h=" + getMan3h() +
            ", man4h=" + getMan4h() +
            ", man5h=" + getMan5h() +
            ", man6h=" + getMan6h() +
            ", man7h=" + getMan7h() +
            ", man8h=" + getMan8h() +
            ", man9h=" + getMan9h() +
            ", man10h=" + getMan10h() +
            ", man11h=" + getMan11h() +
            ", man12h=" + getMan12h() +
            ", man13h=" + getMan13h() +
            ", man14h=" + getMan14h() +
            ", manOnly7Hours=" + getManOnly7Hours() +
            ", fix1h=" + getFix1h() +
            ", fix2h=" + getFix2h() +
            ", fix3h=" + getFix3h() +
            ", fix4h=" + getFix4h() +
            ", fix5h=" + getFix5h() +
            ", fix6h=" + getFix6h() +
            ", fix7h=" + getFix7h() +
            ", fix8h=" + getFix8h() +
            ", fix9h=" + getFix9h() +
            ", fix10h=" + getFix10h() +
            ", fix11h=" + getFix11h() +
            ", fix12h=" + getFix12h() +
            ", fix13h=" + getFix13h() +
            ", fix14h=" + getFix14h() +
            ", proKeHoachSanXuatId=" + getProKeHoachSanXuatId() +
            ", proCheckPointId=" + getProCheckPointId() +
            ", proCheckPointTen='" + getProCheckPointTen() + "'" +
            "}";
    }
}
