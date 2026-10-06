package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ProRefinalChiTietLoi.
 */
@Entity
@Table(name = "pro_refinal_chi_tiet_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProRefinalChiTietLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_loi")
    private String maLoi;

    @Column(name = "thoi_gian")
    private Instant thoiGian;

    @Column(name = "dx")
    private Double dx;

    @Column(name = "dy")
    private Double dy;

    @Column(name = "is_front")
    private Boolean isFront;

    @Column(name = "is_inside")
    private Boolean isInside;

    @Column(name = "warning")
    private Boolean warning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proRefinalChiTietLois")
    private ProRefinal proRefinal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("proRefinalChiTietLois")
    private ProLoi proLoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public ProRefinalChiTietLoi maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public Instant getThoiGian() {
        return thoiGian;
    }

    public ProRefinalChiTietLoi thoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
        return this;
    }

    public void setThoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Double getDx() {
        return dx;
    }

    public ProRefinalChiTietLoi dx(Double dx) {
        this.dx = dx;
        return this;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getDy() {
        return dy;
    }

    public ProRefinalChiTietLoi dy(Double dy) {
        this.dy = dy;
        return this;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Boolean isIsFront() {
        return isFront;
    }

    public ProRefinalChiTietLoi isFront(Boolean isFront) {
        this.isFront = isFront;
        return this;
    }

    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    public Boolean isIsInside() {
        return isInside;
    }

    public ProRefinalChiTietLoi isInside(Boolean isInside) {
        this.isInside = isInside;
        return this;
    }

    public void setIsInside(Boolean isInside) {
        this.isInside = isInside;
    }

    public Boolean isWarning() {
        return warning;
    }

    public ProRefinalChiTietLoi warning(Boolean warning) {
        this.warning = warning;
        return this;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public ProRefinal getProRefinal() {
        return proRefinal;
    }

    public ProRefinalChiTietLoi proRefinal(ProRefinal proRefinal) {
        this.proRefinal = proRefinal;
        return this;
    }

    public void setProRefinal(ProRefinal proRefinal) {
        this.proRefinal = proRefinal;
    }

    public ProLoi getProLoi() {
        return proLoi;
    }

    public ProRefinalChiTietLoi proLoi(ProLoi proLoi) {
        this.proLoi = proLoi;
        return this;
    }

    public void setProLoi(ProLoi proLoi) {
        this.proLoi = proLoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProRefinalChiTietLoi)) {
            return false;
        }
        return id != null && id.equals(((ProRefinalChiTietLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProRefinalChiTietLoi{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", thoiGian='" + getThoiGian() + "'" +
            ", dx=" + getDx() +
            ", dy=" + getDy() +
            ", isFront='" + isIsFront() + "'" +
            ", isInside='" + isIsInside() + "'" +
            ", warning='" + isWarning() + "'" +
            "}";
    }
}
