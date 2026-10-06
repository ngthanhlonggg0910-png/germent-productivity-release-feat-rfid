package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProNhaMay.
 */
@Entity
@Table(name = "pro_nha_may")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProNhaMay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "stt")
    private Integer stt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proNhaMays")
    private ProCongTy proCongTy;

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

    public ProNhaMay ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getStt() {
        return stt;
    }

    public ProNhaMay stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public ProCongTy getProCongTy() {
        return proCongTy;
    }

    public ProNhaMay proCongTy(ProCongTy proCongTy) {
        this.proCongTy = proCongTy;
        return this;
    }

    public void setProCongTy(ProCongTy proCongTy) {
        this.proCongTy = proCongTy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNhaMay)) {
            return false;
        }
        return id != null && id.equals(((ProNhaMay) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProNhaMay{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", stt=" + getStt() +
            "}";
    }
}
