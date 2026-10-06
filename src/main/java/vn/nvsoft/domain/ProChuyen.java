package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProChuyen.
 */
@Entity
@Table(name = "pro_chuyen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProChuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "stt")
    private Integer stt;

    @Column(name = "ten_loai_chuyen")
    private String tenLoaiChuyen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proChuyens")
    private ProLoaiChuyen proLoaiChuyen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("proChuyens")
    private ProNhaMay proNhaMay;

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

    public ProChuyen ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getStt() {
        return stt;
    }

    public ProChuyen stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getTenLoaiChuyen() {
        return tenLoaiChuyen;
    }

    public ProChuyen tenLoaiChuyen(String tenLoaiChuyen) {
        this.tenLoaiChuyen = tenLoaiChuyen;
        return this;
    }

    public void setTenLoaiChuyen(String tenLoaiChuyen) {
        this.tenLoaiChuyen = tenLoaiChuyen;
    }

    public ProLoaiChuyen getProLoaiChuyen() {
        return proLoaiChuyen;
    }

    public ProChuyen proLoaiChuyen(ProLoaiChuyen proLoaiChuyen) {
        this.proLoaiChuyen = proLoaiChuyen;
        return this;
    }

    public void setProLoaiChuyen(ProLoaiChuyen proLoaiChuyen) {
        this.proLoaiChuyen = proLoaiChuyen;
    }

    public ProNhaMay getProNhaMay() {
        return proNhaMay;
    }

    public ProChuyen proNhaMay(ProNhaMay proNhaMay) {
        this.proNhaMay = proNhaMay;
        return this;
    }

    public void setProNhaMay(ProNhaMay proNhaMay) {
        this.proNhaMay = proNhaMay;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProChuyen)) {
            return false;
        }
        return id != null && id.equals(((ProChuyen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProChuyen{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", stt=" + getStt() +
            ", tenLoaiChuyen='" + getTenLoaiChuyen() + "'" +
            "}";
    }
}
