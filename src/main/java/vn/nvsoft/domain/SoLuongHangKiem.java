package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SoLuongHangKiem.
 */
@Entity
@Table(name = "so_luong_hang_kiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SoLuongHangKiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "so_luong")
    private Integer soLuong;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public SoLuongHangKiem soLuong(Integer soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoLuongHangKiem)) {
            return false;
        }
        return id != null && id.equals(((SoLuongHangKiem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SoLuongHangKiem{" +
            "id=" + getId() +
            ", soLuong=" + getSoLuong() +
            "}";
    }
}
