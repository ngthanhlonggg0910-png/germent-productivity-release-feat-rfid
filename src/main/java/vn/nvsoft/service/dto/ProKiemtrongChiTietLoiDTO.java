package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import vn.nvsoft.domain.enumeration.GioLam;

public class ProKiemtrongChiTietLoiDTO implements Serializable {

    private Long id;
    private Long proCheckpointId;
    private Long proKeHoachSanXuatId;

    @NotNull
    private String maLoi;

    @NotNull
    private Instant thoiGian;

    private GioLam gioLam;
    private Double dx;
    private Double dy;
    private Boolean isFront;
    private Boolean isInside;
    private String barcodeTachCay;
    private Long proLoiId;
    private ProLoiDTO proLoi;
    private String proLoiMaLoi;

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

    public Long getProLoiId() {
        return proLoiId;
    }

    public void setProLoiId(Long proLoiId) {
        this.proLoiId = proLoiId;
    }

    public ProLoiDTO getProLoi() {
        return proLoi;
    }

    public void setProLoi(ProLoiDTO proLoi) {
        this.proLoi = proLoi;
    }

    public String getProLoiMaLoi() {
        return proLoiMaLoi;
    }

    public void setProLoiMaLoi(String proLoiMaLoi) {
        this.proLoiMaLoi = proLoiMaLoi;
    }
}
