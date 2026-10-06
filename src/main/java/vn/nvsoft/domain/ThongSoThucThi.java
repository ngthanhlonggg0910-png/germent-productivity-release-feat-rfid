package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ThongSoThucThi.
 */
@Entity
@Table(name = "thong_so_thuc_thi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ThongSoThucThi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sp_index")
    private String spIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoThucThis")
    private ThongSoDoSPSize thongSoDoSPSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoThucThis")
    private DungSai dungSai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoThucThis")
    private ProKeHoachSanXuat proKeHoachSanXuat;

    @Column(name = "po")
    private String po;

    @Column(name = "mausanpham")
    private String mausanpham;

    @Column(name = "isqa")
    private Boolean isqa;

    @Column(name = "check_point_id")
    private Long checkPointId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpIndex() {
        return spIndex;
    }

    public ThongSoThucThi spIndex(String spIndex) {
        this.spIndex = spIndex;
        return this;
    }

    public void setSpIndex(String spIndex) {
        this.spIndex = spIndex;
    }

    public ThongSoDoSPSize getThongSoDoSPSize() {
        return thongSoDoSPSize;
    }

    public ThongSoThucThi thongSoDoSPSize(ThongSoDoSPSize thongSoDoSPSize) {
        this.thongSoDoSPSize = thongSoDoSPSize;
        return this;
    }

    public void setThongSoDoSPSize(ThongSoDoSPSize thongSoDoSPSize) {
        this.thongSoDoSPSize = thongSoDoSPSize;
    }

    public DungSai getDungSai() {
        return dungSai;
    }

    public ThongSoThucThi dungSai(DungSai dungSai) {
        this.dungSai = dungSai;
        return this;
    }

    public void setDungSai(DungSai dungSai) {
        this.dungSai = dungSai;
    }

    public ProKeHoachSanXuat getProKeHoachSanXuat() {
        return proKeHoachSanXuat;
    }

    public ThongSoThucThi proKeHoachSanXuat(ProKeHoachSanXuat proKeHoachSanXuat) {
        this.proKeHoachSanXuat = proKeHoachSanXuat;
        return this;
    }

    public void setProKeHoachSanXuat(ProKeHoachSanXuat proKeHoachSanXuat) {
        this.proKeHoachSanXuat = proKeHoachSanXuat;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getMausanpham() {
        return mausanpham;
    }

    public void setMausanpham(String mausanpham) {
        this.mausanpham = mausanpham;
    }
 

    public Boolean getIsqa() {
        return isqa;
    }

    public void setIsqa(Boolean isqa) {
        this.isqa = isqa;
    }

    public Long getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongSoThucThi)) {
            return false;
        }
        return id != null && id.equals(((ThongSoThucThi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ThongSoThucThi{" +
            "id=" + getId() +
            ", spIndex='" + getSpIndex() + "'" +
            "}";
    }
}
