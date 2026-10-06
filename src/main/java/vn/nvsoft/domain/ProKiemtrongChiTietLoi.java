package vn.nvsoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import vn.nvsoft.domain.enumeration.GioLam;

@Entity
@Table(name = "pro_kiemtrong_chi_tiet_loi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProKiemtrongChiTietLoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "pro_checkpoint_id", nullable = false)
    private Long proCheckpointId;

    @NotNull
    @Column(name = "pro_ke_hoach_san_xuat_id", nullable = false)
    private Long proKeHoachSanXuatId;

    @NotNull
    @Column(name = "ma_loi", nullable = false)
    private String maLoi;

    @NotNull
    @Column(name = "thoi_gian", nullable = false)
    private Instant thoiGian;

    @Enumerated(EnumType.STRING)
    @Column(name = "gio_lam")
    private GioLam gioLam;

    @Column(name = "dx")
    private Double dx;

    @Column(name = "dy")
    private Double dy;

    @Column(name = "is_front")
    private Boolean isFront;

    @Column(name = "is_inside")
    private Boolean isInside;

    @Column(name = "barcode_tach_cay")
    private String barcodeTachCay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("proKiemtrongChiTietLois")
    private ProLoi proLoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProCheckpointId() {
        return proCheckpointId;
    }

    public void setProCheckpointId(Long proCheckpointId) {
        this.proCheckpointId = proCheckpointId;
    }

    public Long getProKeHoachSanXuatId() {
        return proKeHoachSanXuatId;
    }

    public void setProKeHoachSanXuatId(Long proKeHoachSanXuatId) {
        this.proKeHoachSanXuatId = proKeHoachSanXuatId;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public Instant getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Instant thoiGian) {
        this.thoiGian = thoiGian;
    }

    public GioLam getGioLam() {
        return gioLam;
    }

    public void setGioLam(GioLam gioLam) {
        this.gioLam = gioLam;
    }

    public Double getDx() {
        return dx;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getDy() {
        return dy;
    }

    public void setDy(Double dy) {
        this.dy = dy;
    }

    public Boolean getIsFront() {
        return isFront;
    }

    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

    public Boolean getIsInside() {
        return isInside;
    }

    public void setIsInside(Boolean isInside) {
        this.isInside = isInside;
    }

    public String getBarcodeTachCay() {
        return barcodeTachCay;
    }

    public void setBarcodeTachCay(String barcodeTachCay) {
        this.barcodeTachCay = barcodeTachCay;
    }

    public ProLoi getProLoi() {
        return proLoi;
    }

    public void setProLoi(ProLoi proLoi) {
        this.proLoi = proLoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProKiemtrongChiTietLoi)) {
            return false;
        }
        return id != null && id.equals(((ProKiemtrongChiTietLoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
