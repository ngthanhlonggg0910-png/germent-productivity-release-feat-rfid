package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A KhacPhucLoi.
 */
@Entity
@Table(name = "khac_phuc_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KhacPhucLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_kp")
    private String maKP;

    @Column(name = "ten_kp")
    private String tenKP;

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

    public String getMaKP() {
        return maKP;
    }

    public KhacPhucLoi maKP(String maKP) {
        this.maKP = maKP;
        return this;
    }

    public void setMaKP(String maKP) {
        this.maKP = maKP;
    }

    public String getTenKP() {
        return tenKP;
    }

    public KhacPhucLoi tenKP(String tenKP) {
        this.tenKP = tenKP;
        return this;
    }

    public void setTenKP(String tenKP) {
        this.tenKP = tenKP;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public KhacPhucLoi nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public KhacPhucLoi ngayTao(String ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getUserId() {
        return userId;
    }

    public KhacPhucLoi userId(Integer userId) {
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
        if (!(o instanceof KhacPhucLoi)) {
            return false;
        }
        return id != null && id.equals(((KhacPhucLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "KhacPhucLoi{" +
            "id=" + getId() +
            ", maKP='" + getMaKP() + "'" +
            ", tenKP='" + getTenKP() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
