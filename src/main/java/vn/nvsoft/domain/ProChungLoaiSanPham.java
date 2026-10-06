package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProChungLoaiSanPham.
 */
@Entity
@Table(name = "pro_chung_loai_san_pham")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProChungLoaiSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_chung_loai")
    private String tenChungLoai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenChungLoai() {
        return tenChungLoai;
    }

    public ProChungLoaiSanPham tenChungLoai(String tenChungLoai) {
        this.tenChungLoai = tenChungLoai;
        return this;
    }

    public void setTenChungLoai(String tenChungLoai) {
        this.tenChungLoai = tenChungLoai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProChungLoaiSanPham)) {
            return false;
        }
        return id != null && id.equals(((ProChungLoaiSanPham) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProChungLoaiSanPham{" +
            "id=" + getId() +
            ", tenChungLoai='" + getTenChungLoai() + "'" +
            "}";
    }
}
