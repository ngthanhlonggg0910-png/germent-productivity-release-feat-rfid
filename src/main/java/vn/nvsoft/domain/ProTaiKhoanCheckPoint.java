package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProTaiKhoanCheckPoint.
 */
@Entity
@Table(name = "pro_tai_khoan_check_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProTaiKhoanCheckPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tai_khoan")
    private String taiKhoan;

    @Column(name = "checked")
    private Boolean checked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proTaiKhoanCheckPoints")
    private ProCheckPoint proCheckPoint;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public ProTaiKhoanCheckPoint taiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
        return this;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Boolean isChecked() {
        return checked;
    }

    public ProTaiKhoanCheckPoint checked(Boolean checked) {
        this.checked = checked;
        return this;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public ProCheckPoint getProCheckPoint() {
        return proCheckPoint;
    }

    public ProTaiKhoanCheckPoint proCheckPoint(ProCheckPoint proCheckPoint) {
        this.proCheckPoint = proCheckPoint;
        return this;
    }

    public void setProCheckPoint(ProCheckPoint proCheckPoint) {
        this.proCheckPoint = proCheckPoint;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProTaiKhoanCheckPoint)) {
            return false;
        }
        return id != null && id.equals(((ProTaiKhoanCheckPoint) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProTaiKhoanCheckPoint{" +
            "id=" + getId() +
            ", taiKhoan='" + getTaiKhoan() + "'" +
            ", checked='" + isChecked() + "'" +
            "}";
    }
}
