package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ThuVienLoi.
 */
@Entity
@Table(name = "thu_vien_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ThuVienLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ma_loi")
    private String maLoi;

    @Column(name = "ten_loi")
    private String tenLoi;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @Column(name = "user_id")
    private Integer userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public ThuVienLoi maLoi(String maLoi) {
        this.maLoi = maLoi;
        return this;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public ThuVienLoi tenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
        return this;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public ThuVienLoi nguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
        return this;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public ThuVienLoi ngayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
        return this;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getUserId() {
        return userId;
    }

    public ThuVienLoi userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThuVienLoi)) {
            return false;
        }
        return id != null && id.equals(((ThuVienLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ThuVienLoi{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
