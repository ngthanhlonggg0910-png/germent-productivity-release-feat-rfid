package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProCheckPoint.
 */
@Entity
@Table(name = "pro_check_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProCheckPoint implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TYPE_QC = "QC";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("proCheckPoints")
    private ProChuyen proChuyen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proCheckPoints")
    private ProCongDoan proCongDoan;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProCheckPoint ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public ProChuyen getProChuyen() {
        return proChuyen;
    }

    public ProCheckPoint proChuyen(ProChuyen proChuyen) {
        this.proChuyen = proChuyen;
        return this;
    }

    public void setProChuyen(ProChuyen proChuyen) {
        this.proChuyen = proChuyen;
    }

    public ProCongDoan getProCongDoan() {
        return proCongDoan;
    }

    public ProCheckPoint proCongDoan(ProCongDoan proCongDoan) {
        this.proCongDoan = proCongDoan;
        return this;
    }

    public void setProCongDoan(ProCongDoan proCongDoan) {
        this.proCongDoan = proCongDoan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProCheckPoint)) {
            return false;
        }
        return id != null && id.equals(((ProCheckPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProCheckPoint{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            "}";
    }
}
