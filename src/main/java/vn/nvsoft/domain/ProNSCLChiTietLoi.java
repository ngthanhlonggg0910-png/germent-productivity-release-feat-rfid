package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import vn.nvsoft.domain.enumeration.GioLam;

/**
 * A ProNSCLChiTietLoi.
 */
@Entity
@Table(name = "pro_nscl_chi_tiet_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProNSCLChiTietLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ma_loi", nullable = false)
    private String maLoi;

    @NotNull
    @Column(name = "thoi_gian", nullable = false)
    private Instant thoiGian;

    @Enumerated(EnumType.STRING)
    @Column(name = "gio_lam")
    private GioLam gioLam;

    @Column(name = "dx")
    private Double dx;

    @Column(name = "dy")
    private Double dy;

    @Column(name = "is_front")
    private Boolean isFront;

    @Column(name = "is_inside")
    private Boolean isInside;

    @Column(name = "warning")
    private Boolean warning = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proNSCLChiTietLois")
    private ProNSCL proNSCL;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("proNSCLChiTietLois")
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

    public ProNSCLChiTietLoi maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public Instant getThoiGian() {
        return thoiGian;
    }

    public ProNSCLChiTietLoi thoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
        return this;
    }

    public void setThoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
    }

    public GioLam getGioLam() {
        return gioLam;
    }

    public ProNSCLChiTietLoi gioLam(GioLam gioLam) {
        this.gioLam = gioLam;
        return this;
    }

    public void setGioLam(GioLam gioLam) {
        this.gioLam = gioLam;
    }

    public Double getDx() {
        return dx;
    }

    public ProNSCLChiTietLoi dx(Double dx) {
        this.dx = dx;
        return this;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getDy() {
        return dy;
    }

    public ProNSCLChiTietLoi dy(Double dy) {
        this.dy = dy;
        return this;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Boolean isIsFront() {
        return isFront;
    }

    public ProNSCLChiTietLoi isFront(Boolean isFront) {
        this.isFront = isFront;
        return this;
    }

    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    public Boolean isIsInside() {
        return isInside;
    }

    public ProNSCLChiTietLoi isInside(Boolean isInside) {
        this.isInside = isInside;
        return this;
    }

    public void setIsInside(Boolean isInside) {
        this.isInside = isInside;
    }

    public ProNSCL getProNSCL() {
        return proNSCL;
    }

    public ProNSCLChiTietLoi proNSCL(ProNSCL proNSCL) {
        this.proNSCL = proNSCL;
        return this;
    }

    public void setProNSCL(ProNSCL proNSCL) {
        this.proNSCL = proNSCL;
    }

    public ProLoi getProLoi() {
        return proLoi;
    }

    public ProNSCLChiTietLoi proLoi(ProLoi proLoi) {
        this.proLoi = proLoi;
        return this;
    }

    public void setProLoi(ProLoi proLoi) {
        this.proLoi = proLoi;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProNSCLChiTietLoi)) {
            return false;
        }
        return id != null && id.equals(((ProNSCLChiTietLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProNSCLChiTietLoi{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", thoiGian='" + getThoiGian() + "'" +
            ", gioLam='" + getGioLam() + "'" +
            ", dx=" + getDx() +
            ", dy=" + getDy() +
            ", isFront='" + isIsFront() + "'" +
            ", isInside='" + isIsInside() + "'" +
            "}";
    }
}
