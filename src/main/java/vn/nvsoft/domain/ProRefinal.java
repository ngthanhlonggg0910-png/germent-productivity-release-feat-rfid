package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * A ProRefinal.
 */
@Entity
@Table(name = "pro_refinal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProRefinal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pro_khsx_id")
    private Long proKHSXId;

    @Column(name = "check_point_id")
    private Long checkPointID;

    @Column(name = "pro_nscl_id")
    private Long proNSCLId;

    @Column(name = "ngay_gio_kiem")
    private Date ngayGioKiem = new Date();

    @Column(name = "so_luong_kiem")
    private Integer soLuongKiem;

    @Column(name = "hang_dat_hien_tai")
    private Integer hangDatHienTai;

    @Column(name = "do_am")
    private Float doAm;

    @Column(name = "so_loi")
    private Integer soLoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProKHSXId() {
        return proKHSXId;
    }

    public ProRefinal proKHSXId(Long proKHSXId) {
        this.proKHSXId = proKHSXId;
        return this;
    }

    public void setProKHSXId(Long proKHSXId) {
        this.proKHSXId = proKHSXId;
    }

    public Long getCheckPointID() {
        return checkPointID;
    }

    public ProRefinal checkPointID(Long checkPointID) {
        this.checkPointID = checkPointID;
        return this;
    }

    public void setCheckPointID(Long checkPointID) {
        this.checkPointID = checkPointID;
    }

    public Long getProNSCLId() {
        return proNSCLId;
    }

    public ProRefinal proNSCLId(Long proNSCLId) {
        this.proNSCLId = proNSCLId;
        return this;
    }

    public void setProNSCLId(Long proNSCLId) {
        this.proNSCLId = proNSCLId;
    }

    public Date getNgayGioKiem() {
        return ngayGioKiem;
    }

    public ProRefinal ngayGioKiem(Date ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
        return this;
    }

    public void setNgayGioKiem(Date ngayGioKiem) {
        this.ngayGioKiem = ngayGioKiem;
    }

    public Integer getSoLuongKiem() {
        return soLuongKiem;
    }

    public ProRefinal soLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
        return this;
    }

    public void setSoLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
    }

    public Integer getHangDatHienTai() {
        return hangDatHienTai;
    }

    public ProRefinal hangDatHienTai(Integer hangDatHienTai) {
        this.hangDatHienTai = hangDatHienTai;
        return this;
    }

    public void setHangDatHienTai(Integer hangDatHienTai) {
        this.hangDatHienTai = hangDatHienTai;
    }

    public Float getDoAm() {
        return doAm;
    }

    public ProRefinal doAm(Float doAm) {
        this.doAm = doAm;
        return this;
    }

    public void setDoAm(Float doAm) {
        this.doAm = doAm;
    }

    public Integer getSoLoi() {
        return soLoi;
    }

    public void setSoLoi(Integer soLoi) {
        this.soLoi = soLoi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProRefinal)) {
            return false;
        }
        return id != null && id.equals(((ProRefinal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProRefinal{" +
            "id=" + getId() +
            ", proKHSXId=" + getProKHSXId() +
            ", checkPointID=" + getCheckPointID() +
            ", proNSCLId=" + getProNSCLId() +
            ", ngayGioKiem='" + getNgayGioKiem() + "'" +
            ", soLuongKiem=" + getSoLuongKiem() +
            ", hangDatHienTai=" + getHangDatHienTai() +
            ", doAm=" + getDoAm() +
            "}";
    }
}
