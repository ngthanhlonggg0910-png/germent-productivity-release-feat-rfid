package vn.nvsoft.service.viewmodel;

import java.util.Date;

public class KeHoachXuatHangDTO {
    private Long id;
    private String maHang;
    private String pono;
    private Integer soLuong;
    private Date ngayKiemFinal;
    private String tenChuyen;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getPono() {
        return pono;
    }

    public void setPono(String pono) {
        this.pono = pono;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayKiemFinal() {
        return ngayKiemFinal;
    }

    public void setNgayKiemFinal(Date ngayKiemFinal) {
        this.ngayKiemFinal = ngayKiemFinal;
    }

    public String getTenChuyen() {
        return tenChuyen;
    }

    public void setTenChuyen(String tenChuyen) {
        this.tenChuyen = tenChuyen;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PASS,
        FAIL,
        PENDING
    }
}
