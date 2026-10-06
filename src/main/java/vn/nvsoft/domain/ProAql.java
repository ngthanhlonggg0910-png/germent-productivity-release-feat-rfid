package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProAql.
 */
@Entity
@Table(name = "pro_aql")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProAql implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "aql")
    private String aql;

    @Column(name = "so_luong_don_hang_tu")
    private Integer soLuongDonHangTu;

    @Column(name = "so_luong_don_hang_den")
    private Integer soLuongDonHangDen;

    @Column(name = "so_luong_kiem")
    private Integer soLuongKiem;

    @Column(name = "so_luong_se_fail")
    private Integer soLuongSeFail;

    @Column(name = "so_luong_se_pass")
    private Integer soLuongSePass;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAql() {
        return aql;
    }

    public ProAql aql(String aql) {
        this.aql = aql;
        return this;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public Integer getSoLuongDonHangTu() {
        return soLuongDonHangTu;
    }

    public ProAql soLuongDonHangTu(Integer soLuongDonHangTu) {
        this.soLuongDonHangTu = soLuongDonHangTu;
        return this;
    }

    public void setSoLuongDonHangTu(Integer soLuongDonHangTu) {
        this.soLuongDonHangTu = soLuongDonHangTu;
    }

    public Integer getSoLuongDonHangDen() {
        return soLuongDonHangDen;
    }

    public ProAql soLuongDonHangDen(Integer soLuongDonHangDen) {
        this.soLuongDonHangDen = soLuongDonHangDen;
        return this;
    }

    public void setSoLuongDonHangDen(Integer soLuongDonHangDen) {
        this.soLuongDonHangDen = soLuongDonHangDen;
    }

    public Integer getSoLuongKiem() {
        return soLuongKiem;
    }

    public ProAql soLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
        return this;
    }

    public void setSoLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
    }

    public Integer getSoLuongSeFail() {
        return soLuongSeFail;
    }

    public ProAql soLuongSeFail(Integer soLuongSeFail) {
        this.soLuongSeFail = soLuongSeFail;
        return this;
    }

    public void setSoLuongSeFail(Integer soLuongSeFail) {
        this.soLuongSeFail = soLuongSeFail;
    }

    public Integer getSoLuongSePass() {
        return soLuongSePass;
    }

    public ProAql soLuongSePass(Integer soLuongSePass) {
        this.soLuongSePass = soLuongSePass;
        return this;
    }

    public void setSoLuongSePass(Integer soLuongSePass) {
        this.soLuongSePass = soLuongSePass;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProAql)) {
            return false;
        }
        return id != null && id.equals(((ProAql) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProAql{" +
            "id=" + getId() +
            ", aql='" + getAql() + "'" +
            ", soLuongDonHangTu=" + getSoLuongDonHangTu() +
            ", soLuongDonHangDen=" + getSoLuongDonHangDen() +
            ", soLuongKiem=" + getSoLuongKiem() +
            ", soLuongSeFail=" + getSoLuongSeFail() +
            ", soLuongSePass=" + getSoLuongSePass() +
            "}";
    }
}
