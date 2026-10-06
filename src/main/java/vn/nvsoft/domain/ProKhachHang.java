package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProKhachHang.
 */
@Entity
@Table(name = "pro_khach_hang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProKhachHang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "nhom")
    private String nhom;

    @Column(name = "dia_chi")
    private String diaChi;

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

    public ProKhachHang ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNhom() {
        return nhom;
    }

    public ProKhachHang nhom(String nhom) {
        this.nhom = nhom;
        return this;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public ProKhachHang diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProKhachHang)) {
            return false;
        }
        return id != null && id.equals(((ProKhachHang) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProKhachHang{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", nhom='" + getNhom() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            "}";
    }
}
