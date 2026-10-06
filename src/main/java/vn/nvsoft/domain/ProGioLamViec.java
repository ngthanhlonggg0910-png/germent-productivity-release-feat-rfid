package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import vn.nvsoft.domain.enumeration.GioLam;

/**
 * A ProGioLamViec.
 */
@Entity
@Table(name = "pro_gio_lam_viec")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProGioLamViec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "gio_bat_dau", nullable = false)
    private Instant gioBatDau;

    @NotNull
    @Column(name = "gio_ket_thuc", nullable = false)
    private Instant gioKetThuc;

    @Enumerated(EnumType.STRING)
    @Column(name = "gio_lam")
    private GioLam gioLam;

    @Column(name = "thoi_gian")
    private Integer thoiGian;

    @Column(name = "caption_tv")
    private String captionTV;

    @Column(name = "chuyen")
    private String chuyen;

    @Column(name = "thu_bay")
    private Boolean thuBay;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getGioBatDau() {
        return gioBatDau;
    }

    public ProGioLamViec gioBatDau(Instant gioBatDau) {
        this.gioBatDau = gioBatDau;
        return this;
    }

    public void setGioBatDau(Instant gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Instant getGioKetThuc() {
        return gioKetThuc;
    }

    public ProGioLamViec gioKetThuc(Instant gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
        return this;
    }

    public void setGioKetThuc(Instant gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public GioLam getGioLam() {
        return gioLam;
    }

    public ProGioLamViec gioLam(GioLam gioLam) {
        this.gioLam = gioLam;
        return this;
    }

    public void setGioLam(GioLam gioLam) {
        this.gioLam = gioLam;
    }

    public Integer getThoiGian() {
        return thoiGian;
    }

    public ProGioLamViec thoiGian(Integer thoiGian) {
        this.thoiGian = thoiGian;
        return this;
    }

    public void setThoiGian(Integer thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getCaptionTV() {
        return captionTV;
    }

    public ProGioLamViec captionTV(String captionTV) {
        this.captionTV = captionTV;
        return this;
    }

    public void setCaptionTV(String captionTV) {
        this.captionTV = captionTV;
    }

    public String getChuyen() {
        return chuyen;
    }

    public void setChuyen(String chuyen) {
        this.chuyen = chuyen;
    }

    public Boolean getThuBay() {
        return thuBay;
    }

    public void setThuBay(Boolean thuBay) {
        this.thuBay = thuBay;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProGioLamViec)) {
            return false;
        }
        return id != null && id.equals(((ProGioLamViec) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProGioLamViec{" +
            "id=" + getId() +
            ", gioBatDau='" + getGioBatDau() + "'" +
            ", gioKetThuc='" + getGioKetThuc() + "'" +
            ", gioLam='" + getGioLam() + "'" +
            ", thoiGian=" + getThoiGian() +
            ", captionTV='" + getCaptionTV() + "'" +
            "}";
    }
}
