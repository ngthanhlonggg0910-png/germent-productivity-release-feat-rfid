package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GopYChatLuong.
 */
@Entity
@Table(name = "gop_y_chat_luong")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GopYChatLuong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cus_id")
    private Integer cusId;

    @Column(name = "cus_name")
    private String cusName;

    @Column(name = "chuyen_id")
    private Integer chuyenId;

    @Column(name = "chuyen_name")
    private String chuyenName;

    @Column(name = "ma_hang")
    private String maHang;

    @Column(name = "po")
    private String po;

    @Column(name = "po_id")
    private Integer poId;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "first_cut")
    private Boolean firstCut;

    @Column(name = "inline")
    private Boolean inline;

    @Column(name = "lan_ban_hanh")
    private Integer lanBanHanh;

    @Column(name = "ngay_bao_cao")
    private Instant ngayBaoCao;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "user_id")
    private Instant userId;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "ten_quan_ly_xuong")
    private String tenQuanLyXuong;

    @Column(name = "ten_ki_thuat")
    private String tenKiThuat;

    @Column(name = "ten_qan_ly_cat")
    private String tenQanLyCat;

    @Column(name = "ten_qa")
    private String tenQA;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCusId() {
        return cusId;
    }

    public GopYChatLuong cusId(Integer cusId) {
        this.cusId = cusId;
        return this;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public GopYChatLuong cusName(String cusName) {
        this.cusName = cusName;
        return this;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Integer getChuyenId() {
        return chuyenId;
    }

    public GopYChatLuong chuyenId(Integer chuyenId) {
        this.chuyenId = chuyenId;
        return this;
    }

    public void setChuyenId(Integer chuyenId) {
        this.chuyenId = chuyenId;
    }

    public String getChuyenName() {
        return chuyenName;
    }

    public GopYChatLuong chuyenName(String chuyenName) {
        this.chuyenName = chuyenName;
        return this;
    }

    public void setChuyenName(String chuyenName) {
        this.chuyenName = chuyenName;
    }

    public String getMaHang() {
        return maHang;
    }

    public GopYChatLuong maHang(String maHang) {
        this.maHang = maHang;
        return this;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPo() {
        return po;
    }

    public GopYChatLuong po(String po) {
        this.po = po;
        return this;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public Integer getPoId() {
        return poId;
    }

    public GopYChatLuong poId(Integer poId) {
        this.poId = poId;
        return this;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getColor() {
        return color;
    }

    public GopYChatLuong color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public GopYChatLuong size(String size) {
        this.size = size;
        return this;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean isFirstCut() {
        return firstCut;
    }

    public GopYChatLuong firstCut(Boolean firstCut) {
        this.firstCut = firstCut;
        return this;
    }

    public void setFirstCut(Boolean firstCut) {
        this.firstCut = firstCut;
    }

    public Boolean isInline() {
        return inline;
    }

    public GopYChatLuong inline(Boolean inline) {
        this.inline = inline;
        return this;
    }

    public void setInline(Boolean inline) {
        this.inline = inline;
    }

    public Integer getLanBanHanh() {
        return lanBanHanh;
    }

    public GopYChatLuong lanBanHanh(Integer lanBanHanh) {
        this.lanBanHanh = lanBanHanh;
        return this;
    }

    public void setLanBanHanh(Integer lanBanHanh) {
        this.lanBanHanh = lanBanHanh;
    }

    public Instant getNgayBaoCao() {
        return ngayBaoCao;
    }

    public GopYChatLuong ngayBaoCao(Instant ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
        return this;
    }

    public void setNgayBaoCao(Instant ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public GopYChatLuong nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getUserId() {
        return userId;
    }

    public GopYChatLuong userId(Instant userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Instant userId) {
        this.userId = userId;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public GopYChatLuong ngayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenQuanLyXuong() {
        return tenQuanLyXuong;
    }

    public GopYChatLuong tenQuanLyXuong(String tenQuanLyXuong) {
        this.tenQuanLyXuong = tenQuanLyXuong;
        return this;
    }

    public void setTenQuanLyXuong(String tenQuanLyXuong) {
        this.tenQuanLyXuong = tenQuanLyXuong;
    }

    public String getTenKiThuat() {
        return tenKiThuat;
    }

    public GopYChatLuong tenKiThuat(String tenKiThuat) {
        this.tenKiThuat = tenKiThuat;
        return this;
    }

    public void setTenKiThuat(String tenKiThuat) {
        this.tenKiThuat = tenKiThuat;
    }

    public String getTenQanLyCat() {
        return tenQanLyCat;
    }

    public GopYChatLuong tenQanLyCat(String tenQanLyCat) {
        this.tenQanLyCat = tenQanLyCat;
        return this;
    }

    public void setTenQanLyCat(String tenQanLyCat) {
        this.tenQanLyCat = tenQanLyCat;
    }

    public String getTenQA() {
        return tenQA;
    }

    public GopYChatLuong tenQA(String tenQA) {
        this.tenQA = tenQA;
        return this;
    }

    public void setTenQA(String tenQA) {
        this.tenQA = tenQA;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GopYChatLuong)) {
            return false;
        }
        return id != null && id.equals(((GopYChatLuong) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GopYChatLuong{" +
            "id=" + getId() +
            ", cusId=" + getCusId() +
            ", cusName='" + getCusName() + "'" +
            ", chuyenId=" + getChuyenId() +
            ", chuyenName='" + getChuyenName() + "'" +
            ", maHang='" + getMaHang() + "'" +
            ", po='" + getPo() + "'" +
            ", poId=" + getPoId() +
            ", color='" + getColor() + "'" +
            ", size='" + getSize() + "'" +
            ", firstCut='" + isFirstCut() + "'" +
            ", inline='" + isInline() + "'" +
            ", lanBanHanh=" + getLanBanHanh() +
            ", ngayBaoCao='" + getNgayBaoCao() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", userId='" + getUserId() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", tenQuanLyXuong='" + getTenQuanLyXuong() + "'" +
            ", tenKiThuat='" + getTenKiThuat() + "'" +
            ", tenQanLyCat='" + getTenQanLyCat() + "'" +
            ", tenQA='" + getTenQA() + "'" +
            "}";
    }
}
