package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProCongDoan.
 */
@Entity
@Table(name = "pro_cong_doan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProCongDoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "nhom_cong_doan")
    private String nhomCongDoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proCongDoans")
    private ProNhomCongDoan proNhomCongDoan;

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

    public ProCongDoan ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNhomCongDoan() {
        return nhomCongDoan;
    }

    public ProCongDoan nhomCongDoan(String nhomCongDoan) {
        this.nhomCongDoan = nhomCongDoan;
        return this;
    }

    public void setNhomCongDoan(String nhomCongDoan) {
        this.nhomCongDoan = nhomCongDoan;
    }

    public ProNhomCongDoan getProNhomCongDoan() {
        return proNhomCongDoan;
    }

    public ProCongDoan proNhomCongDoan(ProNhomCongDoan proNhomCongDoan) {
        this.proNhomCongDoan = proNhomCongDoan;
        return this;
    }

    public void setProNhomCongDoan(ProNhomCongDoan proNhomCongDoan) {
        this.proNhomCongDoan = proNhomCongDoan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProCongDoan)) {
            return false;
        }
        return id != null && id.equals(((ProCongDoan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProCongDoan{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", nhomCongDoan='" + getNhomCongDoan() + "'" +
            "}";
    }
}
