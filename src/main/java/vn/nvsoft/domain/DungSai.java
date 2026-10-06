package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DungSai.
 */
@Entity
@Table(name = "dung_sai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DungSai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cm_label")
    private String cmLabel;

    @Column(name = "inch_label")
    private String inchLabel;

    @Column(name = "cm_value")
    private Float cmValue;

    @Column(name = "inch_value")
    private Float inchValue;

    @Column(name = "khong_load_tablet")
    private Boolean khongLoadTablet;

    @OneToMany(mappedBy = "dungSai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongSoThucThi> thongSoThucThis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCmLabel() {
        return cmLabel;
    }

    public DungSai cmLabel(String cmLabel) {
        this.cmLabel = cmLabel;
        return this;
    }

    public void setCmLabel(String cmLabel) {
        this.cmLabel = cmLabel;
    }

    public String getInchLabel() {
        return inchLabel;
    }

    public DungSai inchLabel(String inchLabel) {
        this.inchLabel = inchLabel;
        return this;
    }

    public void setInchLabel(String inchLabel) {
        this.inchLabel = inchLabel;
    }

    public Float getCmValue() {
        return cmValue;
    }

    public DungSai cmValue(Float cmValue) {
        this.cmValue = cmValue;
        return this;
    }

    public void setCmValue(Float cmValue) {
        this.cmValue = cmValue;
    }

    public Float getInchValue() {
        return inchValue;
    }

    public DungSai inchValue(Float inchValue) {
        this.inchValue = inchValue;
        return this;
    }

    public Boolean getKhongLoadTablet() {
        return khongLoadTablet;
    }

    public void setKhongLoadTablet(Boolean khongLoadTablet) {
        this.khongLoadTablet = khongLoadTablet;
    }

    public void setInchValue(Float inchValue) {
        this.inchValue = inchValue;
    }

    public Set<ThongSoThucThi> getThongSoThucThis() {
        return thongSoThucThis;
    }

    public DungSai thongSoThucThis(Set<ThongSoThucThi> thongSoThucThis) {
        this.thongSoThucThis = thongSoThucThis;
        return this;
    }

    public DungSai addThongSoThucThi(ThongSoThucThi thongSoThucThi) {
        this.thongSoThucThis.add(thongSoThucThi);
        thongSoThucThi.setDungSai(this);
        return this;
    }

    public DungSai removeThongSoThucThi(ThongSoThucThi thongSoThucThi) {
        this.thongSoThucThis.remove(thongSoThucThi);
        thongSoThucThi.setDungSai(null);
        return this;
    }

    public void setThongSoThucThis(Set<ThongSoThucThi> thongSoThucThis) {
        this.thongSoThucThis = thongSoThucThis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DungSai)) {
            return false;
        }
        return id != null && id.equals(((DungSai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DungSai{" +
            "id=" + getId() +
            ", cmLabel='" + getCmLabel() + "'" +
            ", inchLabel='" + getInchLabel() + "'" +
            ", cmValue=" + getCmValue() +
            ", inchValue=" + getInchValue() +
            "}";
    }
}
