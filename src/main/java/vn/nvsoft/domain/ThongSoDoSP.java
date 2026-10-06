package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ThongSoDoSP.
 */
@Entity
@Table(name = "thong_so_do_sp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ThongSoDoSP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "note")
    private String note;

    @Column(name = "stt")
    private Long stt;

    @Column(name = "revised")
    private Boolean revised;

    @OneToMany(mappedBy = "thongSoDoSP")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongSoDoSPSize> thongSoDoSPSizes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPS")
    private ThongSoDo thongSoDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPS")
    private DungSai dungSai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPS")
    @JoinColumn(name="dung_sai_id_max", nullable=true)
    private DungSai dungSaiMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPS")
    private ProSanPham proSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("thongSoDoSPS")
    @JoinColumn(name="quy_cach_do_id", nullable=true)
    private QuyCachDo quyCachDo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public Boolean isRevised() {
        return revised;
    }

    public void setRevised(Boolean revised) {
        this.revised = revised;
    }

    public ThongSoDoSP note(String note) {
        this.note = note;
        return this;
    }

    public Long getStt() {
        return stt;
    }

    public void setStt(Long stt) {
        this.stt = stt;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<ThongSoDoSPSize> getThongSoDoSPSizes() {
        return thongSoDoSPSizes;
    }

    public ThongSoDoSP thongSoDoSPSizes(Set<ThongSoDoSPSize> thongSoDoSPSizes) {
        this.thongSoDoSPSizes = thongSoDoSPSizes;
        return this;
    }

    public ThongSoDoSP addThongSoDoSPSize(ThongSoDoSPSize thongSoDoSPSize) {
        this.thongSoDoSPSizes.add(thongSoDoSPSize);
        thongSoDoSPSize.setThongSoDoSP(this);
        return this;
    }

    public ThongSoDoSP removeThongSoDoSPSize(ThongSoDoSPSize thongSoDoSPSize) {
        this.thongSoDoSPSizes.remove(thongSoDoSPSize);
        thongSoDoSPSize.setThongSoDoSP(null);
        return this;
    }

    public void setThongSoDoSPSizes(Set<ThongSoDoSPSize> thongSoDoSPSizes) {
        this.thongSoDoSPSizes = thongSoDoSPSizes;
    }

    public ThongSoDo getThongSoDo() {
        return thongSoDo;
    }

    public ThongSoDoSP thongSoDo(ThongSoDo thongSoDo) {
        this.thongSoDo = thongSoDo;
        return this;
    }

    public void setThongSoDo(ThongSoDo thongSoDo) {
        this.thongSoDo = thongSoDo;
    }

    public DungSai getDungSai() {
        return dungSai;
    }

    public ThongSoDoSP dungSai(DungSai dungSai) {
        this.dungSai = dungSai;
        return this;
    }

    public void setDungSai(DungSai dungSai) {
        this.dungSai = dungSai;
    }

    public ProSanPham getProSanPham() {
        return proSanPham;
    }

    public ThongSoDoSP proSanPham(ProSanPham proSanPham) {
        this.proSanPham = proSanPham;
        return this;
    }

    public void setProSanPham(ProSanPham proSanPham) {
        this.proSanPham = proSanPham;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public DungSai getDungSaiMax() {
        return dungSaiMax;
    }

    public void setDungSaiMax(DungSai dungSaiMax) {
        this.dungSaiMax = dungSaiMax;
    }

    public QuyCachDo getQuyCachDo() {
        return quyCachDo;
    }

    public void setQuyCachDo(QuyCachDo quyCachDo) {
        this.quyCachDo = quyCachDo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongSoDoSP)) {
            return false;
        }
        return id != null && id.equals(((ThongSoDoSP) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ThongSoDoSP{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            "}";
    }

    public String buildCodeInfo() {
        if (getThongSoDo() != null) {
            return getThongSoDo().getCode() + " | " + getThongSoDo().getTen();
        }
        return "";
    }
}
