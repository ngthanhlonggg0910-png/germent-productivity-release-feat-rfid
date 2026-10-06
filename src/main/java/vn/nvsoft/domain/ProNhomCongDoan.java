package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProNhomCongDoan.
 */
@Entity
@Table(name = "pro_nhom_cong_doan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProNhomCongDoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "check_nang_suat")
    private Boolean checkNangSuat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public ProNhomCongDoan ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Boolean isCheckNangSuat() {
        return checkNangSuat;
    }

    public ProNhomCongDoan checkNangSuat(Boolean checkNangSuat) {
        this.checkNangSuat = checkNangSuat;
        return this;
    }

    public void setCheckNangSuat(Boolean checkNangSuat) {
        this.checkNangSuat = checkNangSuat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNhomCongDoan)) {
            return false;
        }
        return id != null && id.equals(((ProNhomCongDoan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProNhomCongDoan{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", checkNangSuat='" + isCheckNangSuat() + "'" +
            "}";
    }
}
