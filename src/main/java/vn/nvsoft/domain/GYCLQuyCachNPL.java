package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GYCLQuyCachNPL.
 */
@Entity
@Table(name = "gycl_quy_cach_npl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GYCLQuyCachNPL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_npl")
    private String tenNPL;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "yes_no")
    private Boolean yesNo;

    @Column(name = "n_a")
    private String nA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("gYCLQuyCachNPLS")
    private GopYChatLuong proGopYChatLuong;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNPL() {
        return tenNPL;
    }

    public GYCLQuyCachNPL tenNPL(String tenNPL) {
        this.tenNPL = tenNPL;
        return this;
    }

    public void setTenNPL(String tenNPL) {
        this.tenNPL = tenNPL;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public GYCLQuyCachNPL nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public GYCLQuyCachNPL ngayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getUserId() {
        return userId;
    }

    public GYCLQuyCachNPL userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean isYesNo() {
        return yesNo;
    }

    public GYCLQuyCachNPL yesNo(Boolean yesNo) {
        this.yesNo = yesNo;
        return this;
    }

    public void setYesNo(Boolean yesNo) {
        this.yesNo = yesNo;
    }

    public String getnA() {
        return nA;
    }

    public GYCLQuyCachNPL nA(String nA) {
        this.nA = nA;
        return this;
    }

    public void setnA(String nA) {
        this.nA = nA;
    }

    public GopYChatLuong getProGopYChatLuong() {
        return proGopYChatLuong;
    }

    public GYCLQuyCachNPL proGopYChatLuong(GopYChatLuong gopYChatLuong) {
        this.proGopYChatLuong = gopYChatLuong;
        return this;
    }

    public void setProGopYChatLuong(GopYChatLuong gopYChatLuong) {
        this.proGopYChatLuong = gopYChatLuong;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GYCLQuyCachNPL)) {
            return false;
        }
        return id != null && id.equals(((GYCLQuyCachNPL) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GYCLQuyCachNPL{" +
            "id=" + getId() +
            ", tenNPL='" + getTenNPL() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            ", yesNo='" + isYesNo() + "'" +
            ", nA='" + getnA() + "'" +
            "}";
    }
}
