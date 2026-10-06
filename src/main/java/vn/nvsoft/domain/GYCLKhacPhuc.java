package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GYCLKhacPhuc.
 */
@Entity
@Table(name = "gycl_khac_phuc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GYCLKhacPhuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cum_id")
    private Integer cumId;

    @Column(name = "ten_cum")
    private String tenCum;

    @Column(name = "gop_y")
    private String gopY;

    @Column(name = "ket_qua")
    private String ketQua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "user_id")
    private Instant userId;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("gYCLKhacPhucs")
    private GopYChatLuong proGopYChatLuong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("gYCLKhacPhucs")
    private ThuVienLoi thuVienLoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("gYCLKhacPhucs")
    private KhacPhucLoi khacPhucLoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCumId() {
        return cumId;
    }

    public GYCLKhacPhuc cumId(Integer cumId) {
        this.cumId = cumId;
        return this;
    }

    public void setCumId(Integer cumId) {
        this.cumId = cumId;
    }

    public String getTenCum() {
        return tenCum;
    }

    public GYCLKhacPhuc tenCum(String tenCum) {
        this.tenCum = tenCum;
        return this;
    }

    public void setTenCum(String tenCum) {
        this.tenCum = tenCum;
    }

    public String getGopY() {
        return gopY;
    }

    public GYCLKhacPhuc gopY(String gopY) {
        this.gopY = gopY;
        return this;
    }

    public void setGopY(String gopY) {
        this.gopY = gopY;
    }

    public String getKetQua() {
        return ketQua;
    }

    public GYCLKhacPhuc ketQua(String ketQua) {
        this.ketQua = ketQua;
        return this;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public GYCLKhacPhuc nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getUserId() {
        return userId;
    }

    public GYCLKhacPhuc userId(Instant userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Instant userId) {
        this.userId = userId;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public GYCLKhacPhuc ngayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public GopYChatLuong getProGopYChatLuong() {
        return proGopYChatLuong;
    }

    public GYCLKhacPhuc proGopYChatLuong(GopYChatLuong gopYChatLuong) {
        this.proGopYChatLuong = gopYChatLuong;
        return this;
    }

    public void setProGopYChatLuong(GopYChatLuong gopYChatLuong) {
        this.proGopYChatLuong = gopYChatLuong;
    }

    public ThuVienLoi getThuVienLoi() {
        return thuVienLoi;
    }

    public GYCLKhacPhuc thuVienLoi(ThuVienLoi thuVienLoi) {
        this.thuVienLoi = thuVienLoi;
        return this;
    }

    public void setThuVienLoi(ThuVienLoi thuVienLoi) {
        this.thuVienLoi = thuVienLoi;
    }

    public KhacPhucLoi getKhacPhucLoi() {
        return khacPhucLoi;
    }

    public GYCLKhacPhuc khacPhucLoi(KhacPhucLoi khacPhucLoi) {
        this.khacPhucLoi = khacPhucLoi;
        return this;
    }

    public void setKhacPhucLoi(KhacPhucLoi khacPhucLoi) {
        this.khacPhucLoi = khacPhucLoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GYCLKhacPhuc)) {
            return false;
        }
        return id != null && id.equals(((GYCLKhacPhuc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GYCLKhacPhuc{" +
            "id=" + getId() +
            ", cumId=" + getCumId() +
            ", tenCum='" + getTenCum() + "'" +
            ", gopY='" + getGopY() + "'" +
            ", ketQua='" + getKetQua() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", userId='" + getUserId() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            "}";
    }
}
