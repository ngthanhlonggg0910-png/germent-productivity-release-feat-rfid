package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A QuyCachNPL.
 */
@Entity
@Table(name = "quy_cach_npl")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuyCachNPL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_npl")
    private String tenNPL;

    @Column(name = "ma_npl")
    private String maNPL;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "user_id")
    private Integer userId;

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

    public QuyCachNPL tenNPL(String tenNPL) {
        this.tenNPL = tenNPL;
        return this;
    }

    public void setTenNPL(String tenNPL) {
        this.tenNPL = tenNPL;
    }

    public String getMaNPL() {
        return maNPL;
    }

    public QuyCachNPL maNPL(String maNPL) {
        this.maNPL = maNPL;
        return this;
    }

    public void setMaNPL(String maNPL) {
        this.maNPL = maNPL;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public QuyCachNPL nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public QuyCachNPL ngayTao(String ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getUserId() {
        return userId;
    }

    public QuyCachNPL userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuyCachNPL)) {
            return false;
        }
        return id != null && id.equals(((QuyCachNPL) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QuyCachNPL{" +
            "id=" + getId() +
            ", tenNPL='" + getTenNPL() + "'" +
            ", maNPL='" + getMaNPL() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
