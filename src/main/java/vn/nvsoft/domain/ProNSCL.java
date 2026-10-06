package vn.nvsoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * A ProNSCL.
 */
@Entity
@Table(name = "pro_nscl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProNSCL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tai_khoan")
    private String taiKhoan;

    @Column(name = "ngay_san_xuat")
    private Instant ngaySanXuat;

    @Column(name = "stt")
    private Integer stt;

    @Column(name = "muc_tieu_ngay")
    private Integer mucTieuNgay;

    @Generated( value = GenerationTime.ALWAYS )
    @Column(name = "thuc_hien_ngay", columnDefinition =
        "qty1h + qty2h + qty3h + qty4h + qty5h + qty6h + qty7h + qty8h + qty9h + qty10h + qty11h + qty12h + qty13h + qty14h")
    private Integer thucHienNgay;

    @Column(name = "loi_ngay")
    private Integer loiNgay = 0;

    @Column(name = "sua_ngay")
    private Integer suaNgay = 0;

    @Column(name = "btp_cap")
    private Integer btpCap = 0;

    @NotNull
    @Column(name = "qty_1_h", nullable = false)
    private Integer qty1h = 0;

    @NotNull
    @Column(name = "qty_2_h", nullable = false)
    private Integer qty2h = 0;

    @NotNull
    @Column(name = "qty_3_h", nullable = false)
    private Integer qty3h = 0;

    @NotNull
    @Column(name = "qty_4_h", nullable = false)
    private Integer qty4h = 0;

    @NotNull
    @Column(name = "qty_5_h", nullable = false)
    private Integer qty5h = 0;

    @NotNull
    @Column(name = "qty_6_h", nullable = false)
    private Integer qty6h = 0;

    @NotNull
    @Column(name = "qty_7_h", nullable = false)
    private Integer qty7h = 0;

    @NotNull
    @Column(name = "qty_8_h", nullable = false)
    private Integer qty8h = 0;

    @NotNull
    @Column(name = "qty_9_h", nullable = false)
    private Integer qty9h = 0;

    @NotNull
    @Column(name = "qty_10_h", nullable = false)
    private Integer qty10h = 0;

    @NotNull
    @Column(name = "qty_11_h", nullable = false)
    private Integer qty11h = 0;

    @NotNull
    @Column(name = "qty_12_h", nullable = false)
    private Integer qty12h = 0;

    @NotNull
    @Column(name = "qty_13_h", nullable = false)
    private Integer qty13h = 0;

    @NotNull
    @Column(name = "qty_14_h", nullable = false)
    private Integer qty14h = 0;

    @NotNull
    @Column(name = "reject_1_h", nullable = false)
    private Integer reject1h = 0;

    @NotNull
    @Column(name = "reject_2_h", nullable = false)
    private Integer reject2h = 0;

    @NotNull
    @Column(name = "reject_3_h", nullable = false)
    private Integer reject3h = 0;

    @NotNull
    @Column(name = "reject_4_h", nullable = false)
    private Integer reject4h = 0;

    @NotNull
    @Column(name = "reject_5_h", nullable = false)
    private Integer reject5h = 0;

    @NotNull
    @Column(name = "reject_6_h", nullable = false)
    private Integer reject6h = 0;

    @NotNull
    @Column(name = "reject_7_h", nullable = false)
    private Integer reject7h = 0;

    @NotNull
    @Column(name = "reject_8_h", nullable = false)
    private Integer reject8h = 0;

    @NotNull
    @Column(name = "reject_9_h", nullable = false)
    private Integer reject9h = 0;

    @NotNull
    @Column(name = "reject_10_h", nullable = false)
    private Integer reject10h = 0;

    @NotNull
    @Column(name = "reject_11_h", nullable = false)
    private Integer reject11h = 0;

    @NotNull
    @Column(name = "reject_12_h", nullable = false)
    private Integer reject12h = 0;

    @NotNull
    @Column(name = "reject_13_h", nullable = false)
    private Integer reject13h = 0;

    @NotNull
    @Column(name = "reject_14_h", nullable = false)
    private Integer reject14h = 0;

    @NotNull
    @Column(name = "man_1_h", nullable = false)
    private Integer man1h = 0;

    @NotNull
    @Column(name = "man_2_h", nullable = false)
    private Integer man2h = 0;

    @NotNull
    @Column(name = "man_3_h", nullable = false)
    private Integer man3h = 0;

    @NotNull
    @Column(name = "man_4_h", nullable = false)
    private Integer man4h = 0;

    @NotNull
    @Column(name = "man_5_h", nullable = false)
    private Integer man5h = 0;

    @NotNull
    @Column(name = "man_6_h", nullable = false)
    private Integer man6h = 0;

    @NotNull
    @Column(name = "man_7_h", nullable = false)
    private Integer man7h = 0;

    @NotNull
    @Column(name = "man_8_h", nullable = false)
    private Integer man8h = 0;

    @NotNull
    @Column(name = "man_9_h", nullable = false)
    private Integer man9h = 0;

    @NotNull
    @Column(name = "man_10_h", nullable = false)
    private Integer man10h = 0;

    @NotNull
    @Column(name = "man_11_h", nullable = false)
    private Integer man11h = 0;

    @NotNull
    @Column(name = "man_12_h", nullable = false)
    private Integer man12h = 0;

    @NotNull
    @Column(name = "man_13_h", nullable = false)
    private Integer man13h = 0;

    @NotNull
    @Column(name = "man_14_h", nullable = false)
    private Integer man14h = 0;

    @NotNull
    @Column(name = "man_only_7_hours", nullable = false)
    private Integer manOnly7Hours = 0;

    @NotNull
    @Column(name = "fix_1_h", nullable = false)
    private Integer fix1h = 0;

    @NotNull
    @Column(name = "fix_2_h", nullable = false)
    private Integer fix2h = 0;

    @NotNull
    @Column(name = "fix_3_h", nullable = false)
    private Integer fix3h = 0;

    @NotNull
    @Column(name = "fix_4_h", nullable = false)
    private Integer fix4h = 0;

    @NotNull
    @Column(name = "fix_5_h", nullable = false)
    private Integer fix5h = 0;

    @NotNull
    @Column(name = "fix_6_h", nullable = false)
    private Integer fix6h = 0;

    @NotNull
    @Column(name = "fix_7_h", nullable = false)
    private Integer fix7h = 0;

    @NotNull
    @Column(name = "fix_8_h", nullable = false)
    private Integer fix8h = 0;

    @NotNull
    @Column(name = "fix_9_h", nullable = false)
    private Integer fix9h = 0;

    @NotNull
    @Column(name = "fix_10_h", nullable = false)
    private Integer fix10h = 0;

    @NotNull
    @Column(name = "fix_11_h", nullable = false)
    private Integer fix11h = 0;

    @NotNull
    @Column(name = "fix_12_h", nullable = false)
    private Integer fix12h = 0;

    @NotNull
    @Column(name = "fix_13_h", nullable = false)
    private Integer fix13h = 0;

    @NotNull
    @Column(name = "fix_14_h", nullable = false)
    private Integer fix14h = 0;

    @NotNull
    @Column(name = "sua_po_luy_ke", nullable = false)
    private Integer suaPoLuyKe = 0;

    @NotNull
    @Column(name = "ngay_tao", nullable = true)
    private Instant ngayTao = Instant.now();

    @ManyToOne
    @JsonIgnoreProperties("proNSCLS")
    private ProKeHoachSanXuat proKeHoachSanXuat;

    @ManyToOne
    @JsonIgnoreProperties("proNSCLS")
    private ProCheckPoint proCheckPoint;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public ProNSCL taiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
        return this;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Instant getNgaySanXuat() {
        return ngaySanXuat;
    }

    public ProNSCL ngaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
        return this;
    }

    public void setNgaySanXuat(Instant ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Integer getStt() {
        return stt;
    }

    public ProNSCL stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getMucTieuNgay() {
        return mucTieuNgay;
    }

    public ProNSCL mucTieuNgay(Integer mucTieuNgay) {
        this.mucTieuNgay = mucTieuNgay;
        return this;
    }

    public void setMucTieuNgay(Integer mucTieuNgay) {
        this.mucTieuNgay = mucTieuNgay;
    }

    public Integer getThucHienNgay() {
        return thucHienNgay;
    }

    public ProNSCL thucHienNgay(Integer thucHienNgay) {
        this.thucHienNgay = thucHienNgay;
        return this;
    }

    public void setThucHienNgay(Integer thucHienNgay) {
        this.thucHienNgay = thucHienNgay;
    }

    public Integer getLoiNgay() {
        return loiNgay;
    }

    public ProNSCL loiNgay(Integer loiNgay) {
        this.loiNgay = loiNgay;
        return this;
    }

    public void setLoiNgay(Integer loiNgay) {
        this.loiNgay = loiNgay;
    }

    public Integer getSuaNgay() {
        return suaNgay;
    }

    public ProNSCL suaNgay(Integer suaNgay) {
        this.suaNgay = suaNgay;
        return this;
    }

    public void setSuaNgay(Integer suaNgay) {
        this.suaNgay = suaNgay;
    }

    public Integer getBtpCap() {
        return btpCap;
    }

    public ProNSCL btpCap(Integer btpCap) {
        this.btpCap = btpCap;
        return this;
    }

    public void setBtpCap(Integer btpCap) {
        this.btpCap = btpCap;
    }

    public Integer getQty1h() {
        return qty1h;
    }

    public ProNSCL qty1h(Integer qty1h) {
        this.qty1h = qty1h;
        return this;
    }

    public void setQty1h(Integer qty1h) {
        this.qty1h = qty1h;
    }

    public Integer getQty2h() {
        return qty2h;
    }

    public ProNSCL qty2h(Integer qty2h) {
        this.qty2h = qty2h;
        return this;
    }

    public void setQty2h(Integer qty2h) {
        this.qty2h = qty2h;
    }

    public Integer getQty3h() {
        return qty3h;
    }

    public ProNSCL qty3h(Integer qty3h) {
        this.qty3h = qty3h;
        return this;
    }

    public void setQty3h(Integer qty3h) {
        this.qty3h = qty3h;
    }

    public Integer getQty4h() {
        return qty4h;
    }

    public ProNSCL qty4h(Integer qty4h) {
        this.qty4h = qty4h;
        return this;
    }

    public void setQty4h(Integer qty4h) {
        this.qty4h = qty4h;
    }

    public Integer getQty5h() {
        return qty5h;
    }

    public ProNSCL qty5h(Integer qty5h) {
        this.qty5h = qty5h;
        return this;
    }

    public void setQty5h(Integer qty5h) {
        this.qty5h = qty5h;
    }

    public Integer getQty6h() {
        return qty6h;
    }

    public ProNSCL qty6h(Integer qty6h) {
        this.qty6h = qty6h;
        return this;
    }

    public void setQty6h(Integer qty6h) {
        this.qty6h = qty6h;
    }

    public Integer getQty7h() {
        return qty7h;
    }

    public ProNSCL qty7h(Integer qty7h) {
        this.qty7h = qty7h;
        return this;
    }

    public void setQty7h(Integer qty7h) {
        this.qty7h = qty7h;
    }

    public Integer getQty8h() {
        return qty8h;
    }

    public ProNSCL qty8h(Integer qty8h) {
        this.qty8h = qty8h;
        return this;
    }

    public void setQty8h(Integer qty8h) {
        this.qty8h = qty8h;
    }

    public Integer getQty9h() {
        return qty9h;
    }

    public ProNSCL qty9h(Integer qty9h) {
        this.qty9h = qty9h;
        return this;
    }

    public void setQty9h(Integer qty9h) {
        this.qty9h = qty9h;
    }

    public Integer getQty10h() {
        return qty10h;
    }

    public ProNSCL qty10h(Integer qty10h) {
        this.qty10h = qty10h;
        return this;
    }

    public void setQty10h(Integer qty10h) {
        this.qty10h = qty10h;
    }

    public Integer getQty11h() {
        return qty11h;
    }

    public ProNSCL qty11h(Integer qty11h) {
        this.qty11h = qty11h;
        return this;
    }

    public void setQty11h(Integer qty11h) {
        this.qty11h = qty11h;
    }

    public Integer getQty12h() {
        return qty12h;
    }

    public ProNSCL qty12h(Integer qty12h) {
        this.qty12h = qty12h;
        return this;
    }

    public void setQty12h(Integer qty12h) {
        this.qty12h = qty12h;
    }

    public Integer getQty13h() {
        return qty13h;
    }

    public ProNSCL qty13h(Integer qty13h) {
        this.qty13h = qty13h;
        return this;
    }

    public void setQty13h(Integer qty13h) {
        this.qty13h = qty13h;
    }

    public Integer getQty14h() {
        return qty14h;
    }

    public ProNSCL qty14h(Integer qty14h) {
        this.qty14h = qty14h;
        return this;
    }

    public void setQty14h(Integer qty14h) {
        this.qty14h = qty14h;
    }

    public Integer getReject1h() {
        return reject1h;
    }

    public ProNSCL reject1h(Integer reject1h) {
        this.reject1h = reject1h;
        return this;
    }

    public void setReject1h(Integer reject1h) {
        this.reject1h = reject1h;
    }

    public Integer getReject2h() {
        return reject2h;
    }

    public ProNSCL reject2h(Integer reject2h) {
        this.reject2h = reject2h;
        return this;
    }

    public void setReject2h(Integer reject2h) {
        this.reject2h = reject2h;
    }

    public Integer getReject3h() {
        return reject3h;
    }

    public ProNSCL reject3h(Integer reject3h) {
        this.reject3h = reject3h;
        return this;
    }

    public void setReject3h(Integer reject3h) {
        this.reject3h = reject3h;
    }

    public Integer getReject4h() {
        return reject4h;
    }

    public ProNSCL reject4h(Integer reject4h) {
        this.reject4h = reject4h;
        return this;
    }

    public void setReject4h(Integer reject4h) {
        this.reject4h = reject4h;
    }

    public Integer getReject5h() {
        return reject5h;
    }

    public ProNSCL reject5h(Integer reject5h) {
        this.reject5h = reject5h;
        return this;
    }

    public void setReject5h(Integer reject5h) {
        this.reject5h = reject5h;
    }

    public Integer getReject6h() {
        return reject6h;
    }

    public ProNSCL reject6h(Integer reject6h) {
        this.reject6h = reject6h;
        return this;
    }

    public void setReject6h(Integer reject6h) {
        this.reject6h = reject6h;
    }

    public Integer getReject7h() {
        return reject7h;
    }

    public ProNSCL reject7h(Integer reject7h) {
        this.reject7h = reject7h;
        return this;
    }

    public void setReject7h(Integer reject7h) {
        this.reject7h = reject7h;
    }

    public Integer getReject8h() {
        return reject8h;
    }

    public ProNSCL reject8h(Integer reject8h) {
        this.reject8h = reject8h;
        return this;
    }

    public void setReject8h(Integer reject8h) {
        this.reject8h = reject8h;
    }

    public Integer getReject9h() {
        return reject9h;
    }

    public ProNSCL reject9h(Integer reject9h) {
        this.reject9h = reject9h;
        return this;
    }

    public void setReject9h(Integer reject9h) {
        this.reject9h = reject9h;
    }

    public Integer getReject10h() {
        return reject10h;
    }

    public ProNSCL reject10h(Integer reject10h) {
        this.reject10h = reject10h;
        return this;
    }

    public void setReject10h(Integer reject10h) {
        this.reject10h = reject10h;
    }

    public Integer getReject11h() {
        return reject11h;
    }

    public ProNSCL reject11h(Integer reject11h) {
        this.reject11h = reject11h;
        return this;
    }

    public void setReject11h(Integer reject11h) {
        this.reject11h = reject11h;
    }

    public Integer getReject12h() {
        return reject12h;
    }

    public ProNSCL reject12h(Integer reject12h) {
        this.reject12h = reject12h;
        return this;
    }

    public void setReject12h(Integer reject12h) {
        this.reject12h = reject12h;
    }

    public Integer getReject13h() {
        return reject13h;
    }

    public ProNSCL reject13h(Integer reject13h) {
        this.reject13h = reject13h;
        return this;
    }

    public void setReject13h(Integer reject13h) {
        this.reject13h = reject13h;
    }

    public Integer getReject14h() {
        return reject14h;
    }

    public ProNSCL reject14h(Integer reject14h) {
        this.reject14h = reject14h;
        return this;
    }

    public void setReject14h(Integer reject14h) {
        this.reject14h = reject14h;
    }

    public Integer getMan1h() {
        return man1h;
    }

    public ProNSCL man1h(Integer man1h) {
        this.man1h = man1h;
        return this;
    }

    public void setMan1h(Integer man1h) {
        this.man1h = man1h;
    }

    public Integer getMan2h() {
        return man2h;
    }

    public ProNSCL man2h(Integer man2h) {
        this.man2h = man2h;
        return this;
    }

    public void setMan2h(Integer man2h) {
        this.man2h = man2h;
    }

    public Integer getMan3h() {
        return man3h;
    }

    public ProNSCL man3h(Integer man3h) {
        this.man3h = man3h;
        return this;
    }

    public void setMan3h(Integer man3h) {
        this.man3h = man3h;
    }

    public Integer getMan4h() {
        return man4h;
    }

    public ProNSCL man4h(Integer man4h) {
        this.man4h = man4h;
        return this;
    }

    public void setMan4h(Integer man4h) {
        this.man4h = man4h;
    }

    public Integer getMan5h() {
        return man5h;
    }

    public ProNSCL man5h(Integer man5h) {
        this.man5h = man5h;
        return this;
    }

    public void setMan5h(Integer man5h) {
        this.man5h = man5h;
    }

    public Integer getMan6h() {
        return man6h;
    }

    public ProNSCL man6h(Integer man6h) {
        this.man6h = man6h;
        return this;
    }

    public void setMan6h(Integer man6h) {
        this.man6h = man6h;
    }

    public Integer getMan7h() {
        return man7h;
    }

    public ProNSCL man7h(Integer man7h) {
        this.man7h = man7h;
        return this;
    }

    public void setMan7h(Integer man7h) {
        this.man7h = man7h;
    }

    public Integer getMan8h() {
        return man8h;
    }

    public ProNSCL man8h(Integer man8h) {
        this.man8h = man8h;
        return this;
    }

    public void setMan8h(Integer man8h) {
        this.man8h = man8h;
    }

    public Integer getMan9h() {
        return man9h;
    }

    public ProNSCL man9h(Integer man9h) {
        this.man9h = man9h;
        return this;
    }

    public void setMan9h(Integer man9h) {
        this.man9h = man9h;
    }

    public Integer getMan10h() {
        return man10h;
    }

    public ProNSCL man10h(Integer man10h) {
        this.man10h = man10h;
        return this;
    }

    public void setMan10h(Integer man10h) {
        this.man10h = man10h;
    }

    public Integer getMan11h() {
        return man11h;
    }

    public ProNSCL man11h(Integer man11h) {
        this.man11h = man11h;
        return this;
    }

    public void setMan11h(Integer man11h) {
        this.man11h = man11h;
    }

    public Integer getMan12h() {
        return man12h;
    }

    public ProNSCL man12h(Integer man12h) {
        this.man12h = man12h;
        return this;
    }

    public void setMan12h(Integer man12h) {
        this.man12h = man12h;
    }

    public Integer getMan13h() {
        return man13h;
    }

    public ProNSCL man13h(Integer man13h) {
        this.man13h = man13h;
        return this;
    }

    public void setMan13h(Integer man13h) {
        this.man13h = man13h;
    }

    public Integer getMan14h() {
        return man14h;
    }

    public ProNSCL man14h(Integer man14h) {
        this.man14h = man14h;
        return this;
    }

    public void setMan14h(Integer man14h) {
        this.man14h = man14h;
    }

    public Integer getManOnly7Hours() {
        return manOnly7Hours;
    }

    public ProNSCL manOnly7Hours(Integer manOnly7Hours) {
        this.manOnly7Hours = manOnly7Hours;
        return this;
    }

    public void setManOnly7Hours(Integer manOnly7Hours) {
        this.manOnly7Hours = manOnly7Hours;
    }

    public Integer getFix1h() {
        return fix1h;
    }

    public ProNSCL fix1h(Integer fix1h) {
        this.fix1h = fix1h;
        return this;
    }

    public void setFix1h(Integer fix1h) {
        this.fix1h = fix1h;
    }

    public Integer getFix2h() {
        return fix2h;
    }

    public ProNSCL fix2h(Integer fix2h) {
        this.fix2h = fix2h;
        return this;
    }

    public void setFix2h(Integer fix2h) {
        this.fix2h = fix2h;
    }

    public Integer getFix3h() {
        return fix3h;
    }

    public ProNSCL fix3h(Integer fix3h) {
        this.fix3h = fix3h;
        return this;
    }

    public void setFix3h(Integer fix3h) {
        this.fix3h = fix3h;
    }

    public Integer getFix4h() {
        return fix4h;
    }

    public ProNSCL fix4h(Integer fix4h) {
        this.fix4h = fix4h;
        return this;
    }

    public void setFix4h(Integer fix4h) {
        this.fix4h = fix4h;
    }

    public Integer getFix5h() {
        return fix5h;
    }

    public ProNSCL fix5h(Integer fix5h) {
        this.fix5h = fix5h;
        return this;
    }

    public void setFix5h(Integer fix5h) {
        this.fix5h = fix5h;
    }

    public Integer getFix6h() {
        return fix6h;
    }

    public ProNSCL fix6h(Integer fix6h) {
        this.fix6h = fix6h;
        return this;
    }

    public void setFix6h(Integer fix6h) {
        this.fix6h = fix6h;
    }

    public Integer getFix7h() {
        return fix7h;
    }

    public ProNSCL fix7h(Integer fix7h) {
        this.fix7h = fix7h;
        return this;
    }

    public void setFix7h(Integer fix7h) {
        this.fix7h = fix7h;
    }

    public Integer getFix8h() {
        return fix8h;
    }

    public ProNSCL fix8h(Integer fix8h) {
        this.fix8h = fix8h;
        return this;
    }

    public void setFix8h(Integer fix8h) {
        this.fix8h = fix8h;
    }

    public Integer getFix9h() {
        return fix9h;
    }

    public ProNSCL fix9h(Integer fix9h) {
        this.fix9h = fix9h;
        return this;
    }

    public void setFix9h(Integer fix9h) {
        this.fix9h = fix9h;
    }

    public Integer getFix10h() {
        return fix10h;
    }

    public ProNSCL fix10h(Integer fix10h) {
        this.fix10h = fix10h;
        return this;
    }

    public void setFix10h(Integer fix10h) {
        this.fix10h = fix10h;
    }

    public Integer getFix11h() {
        return fix11h;
    }

    public ProNSCL fix11h(Integer fix11h) {
        this.fix11h = fix11h;
        return this;
    }

    public void setFix11h(Integer fix11h) {
        this.fix11h = fix11h;
    }

    public Integer getFix12h() {
        return fix12h;
    }

    public ProNSCL fix12h(Integer fix12h) {
        this.fix12h = fix12h;
        return this;
    }

    public void setFix12h(Integer fix12h) {
        this.fix12h = fix12h;
    }

    public Integer getFix13h() {
        return fix13h;
    }

    public ProNSCL fix13h(Integer fix13h) {
        this.fix13h = fix13h;
        return this;
    }

    public void setFix13h(Integer fix13h) {
        this.fix13h = fix13h;
    }

    public Integer getFix14h() {
        return fix14h;
    }

    public ProNSCL fix14h(Integer fix14h) {
        this.fix14h = fix14h;
        return this;
    }

    public void setFix14h(Integer fix14h) {
        this.fix14h = fix14h;
    }

    public Integer getSuaPoLuyKe() {
        return suaPoLuyKe;
    }

    public ProNSCL suaPoLuyKe(Integer suaPoLuyKe) {
        this.suaPoLuyKe = suaPoLuyKe;
        return this;
    }

    public void setSuaPoLuyKe(Integer suaPoLuyKe) {
        this.suaPoLuyKe = suaPoLuyKe;
    }

    public ProKeHoachSanXuat getProKeHoachSanXuat() {
        return proKeHoachSanXuat;
    }

    public ProNSCL proKeHoachSanXuat(ProKeHoachSanXuat proKeHoachSanXuat) {
        this.proKeHoachSanXuat = proKeHoachSanXuat;
        return this;
    }

    public void setProKeHoachSanXuat(ProKeHoachSanXuat proKeHoachSanXuat) {
        this.proKeHoachSanXuat = proKeHoachSanXuat;
    }

    public ProCheckPoint getProCheckPoint() {
        return proCheckPoint;
    }

    public ProNSCL proCheckPoint(ProCheckPoint proCheckPoint) {
        this.proCheckPoint = proCheckPoint;
        return this;
    }

    public void setProCheckPoint(ProCheckPoint proCheckPoint) {
        this.proCheckPoint = proCheckPoint;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNSCL)) {
            return false;
        }
        return id != null && id.equals(((ProNSCL) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProNSCL{" +
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
            "}";
    }
}
