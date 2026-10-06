package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ThongSoDoSPSize.
 */
@Entity
@Table(name = "thong_so_do_sp_size")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ThongSoDoSPSize implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "size")
    private String size;

    @Column(name = "cm_thong_so_chuan")
    private String cmThongSoChuan;

    @Column(name = "inch_thong_so_chuan")
    private String inchThongSoChuan;

    @Column(name = "cm_thong_so_chuan_value")
    private Float cmThongSoChuanValue;

    @Column(name = "inch_thong_so_chuan_value")
    private Float inchThongSoChuanValue;

    @OneToMany(mappedBy = "thongSoDoSPSize")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongSoThucThi> thongSoThucThis = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPSizes")
    private ThongSoDoSP thongSoDoSP;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public ThongSoDoSPSize size(String size) {
        this.size = size;
        return this;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCmThongSoChuan() {
        return cmThongSoChuan;
    }

    public ThongSoDoSPSize cmThongSoChuan(String cmThongSoChuan) {
        this.cmThongSoChuan = cmThongSoChuan;
        return this;
    }

    public void setCmThongSoChuan(String cmThongSoChuan) {
        this.cmThongSoChuan = cmThongSoChuan;
    }

    public String getInchThongSoChuan() {
        return inchThongSoChuan;
    }

    public ThongSoDoSPSize inchThongSoChuan(String inchThongSoChuan) {
        this.inchThongSoChuan = inchThongSoChuan;
        return this;
    }

    public void setInchThongSoChuan(String inchThongSoChuan) {
        this.inchThongSoChuan = inchThongSoChuan;
    }

    public Float getCmThongSoChuanValue() {
        return cmThongSoChuanValue;
    }

    public ThongSoDoSPSize cmThongSoChuanValue(Float cmThongSoChuanValue) {
        this.cmThongSoChuanValue = cmThongSoChuanValue;
        return this;
    }

    public void setCmThongSoChuanValue(Float cmThongSoChuanValue) {
        this.cmThongSoChuanValue = cmThongSoChuanValue;
    }

    public Float getInchThongSoChuanValue() {
        return inchThongSoChuanValue;
    }

    public ThongSoDoSPSize inchThongSoChuanValue(Float inchThongSoChuanValue) {
        this.inchThongSoChuanValue = inchThongSoChuanValue;
        return this;
    }

    public void setInchThongSoChuanValue(Float inchThongSoChuanValue) {
        this.inchThongSoChuanValue = inchThongSoChuanValue;
    }

    public Set<ThongSoThucThi> getThongSoThucThis() {
        return thongSoThucThis;
    }

    public ThongSoDoSPSize thongSoThucThis(Set<ThongSoThucThi> thongSoThucThis) {
        this.thongSoThucThis = thongSoThucThis;
        return this;
    }

    public ThongSoDoSPSize addThongSoThucThi(ThongSoThucThi thongSoThucThi) {
        this.thongSoThucThis.add(thongSoThucThi);
        thongSoThucThi.setThongSoDoSPSize(this);
        return this;
    }

    public ThongSoDoSPSize removeThongSoThucThi(ThongSoThucThi thongSoThucThi) {
        this.thongSoThucThis.remove(thongSoThucThi);
        thongSoThucThi.setThongSoDoSPSize(null);
        return this;
    }

    public void setThongSoThucThis(Set<ThongSoThucThi> thongSoThucThis) {
        this.thongSoThucThis = thongSoThucThis;
    }

    public ThongSoDoSP getThongSoDoSP() {
        return thongSoDoSP;
    }

    public ThongSoDoSPSize thongSoDoSP(ThongSoDoSP thongSoDoSP) {
        this.thongSoDoSP = thongSoDoSP;
        return this;
    }

    public void setThongSoDoSP(ThongSoDoSP thongSoDoSP) {
        this.thongSoDoSP = thongSoDoSP;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongSoDoSPSize)) {
            return false;
        }
        return id != null && id.equals(((ThongSoDoSPSize) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ThongSoDoSPSize{" +
            "id=" + getId() +
            ", size='" + getSize() + "'" +
            ", cmThongSoChuan='" + getCmThongSoChuan() + "'" +
            ", inchThongSoChuan='" + getInchThongSoChuan() + "'" +
            ", cmThongSoChuanValue=" + getCmThongSoChuanValue() +
            ", inchThongSoChuanValue=" + getInchThongSoChuanValue() +
            "}";
    }
}
