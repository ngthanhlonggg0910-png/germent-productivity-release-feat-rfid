package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProIssueCumLoi.
 */
@Entity
@Table(name = "pro_issue_cum_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProIssueCumLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "mau")
    private String mau;

    @Column(name = "to_truong")
    private Boolean toTruong;

    @Column(name = "ky_thuat")
    private Boolean kyThuat;

    @Column(name = "qa")
    private Boolean qa;

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

    public ProIssueCumLoi ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMau() {
        return mau;
    }

    public ProIssueCumLoi mau(String mau) {
        this.mau = mau;
        return this;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Boolean isToTruong() {
        return toTruong;
    }

    public ProIssueCumLoi toTruong(Boolean toTruong) {
        this.toTruong = toTruong;
        return this;
    }

    public void setToTruong(Boolean toTruong) {
        this.toTruong = toTruong;
    }

    public Boolean isKyThuat() {
        return kyThuat;
    }

    public ProIssueCumLoi kyThuat(Boolean kyThuat) {
        this.kyThuat = kyThuat;
        return this;
    }

    public void setKyThuat(Boolean kyThuat) {
        this.kyThuat = kyThuat;
    }

    public Boolean isQa() {
        return qa;
    }

    public ProIssueCumLoi qa(Boolean qa) {
        this.qa = qa;
        return this;
    }

    public void setQa(Boolean qa) {
        this.qa = qa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProIssueCumLoi)) {
            return false;
        }
        return id != null && id.equals(((ProIssueCumLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProIssueCumLoi{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", mau='" + getMau() + "'" +
            ", toTruong='" + isToTruong() + "'" +
            ", kyThuat='" + isKyThuat() + "'" +
            ", qa='" + isQa() + "'" +
            "}";
    }
}
