package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO cho thông tin barcode mã cây
 */
public class TemBarcodeTachCayDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String barcode;
    private String rfid;
    private String rfidHex;
    private Long tkId;
    private String nguoiMap;
    private Instant thoiGianMap;
    private Instant ngayTao;
    private String lenhSanXuat;
    private String maHang;
    private String loaiNguyenLieu;
    private String nguoiCat;
    private String nguoiTrai;
    private String anhMauPhieu;
    private String tenPhongBan;
    private String mua;
    private String banMay;
    private String tenSize;
    private String idBanMay;
    private Long phieuHoachToanId;
    private String dan;
    private Integer soLuong;
    private String tenChiTiet;
    private String po;
    private String part;
    private Integer stt;
    private String maCay;
    private Integer soLopTrai; // Giới hạn mapping hiệu lực (BTP: SoLopTrai; BTH: SoLopTrai * số bàn may)
    private String lot;
    private String anhMau;
    private Instant ngayCapNhat;
    private String code;

    // Mã hàng (ProductCode) lấy từ CUTTING_PhieuCapBTP
    private String productCode;

    // Số lượng RFID đã mapping
    private Integer soLuongDaMap;
    
    // Số lượng còn lại có thể mapping
    private Integer soLuongConLai;

    /** Tên khách hàng (distinct, nối bằng dấu phẩy nếu nhiều) */
    private String khachHang;

    /** true nếu mã cây thuộc khách hàng DECATHLON — bắt buộc RFID strict (01)14 số(21)12 số */
    private Boolean decathlonCustomer;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getRfid() { return rfid; }
    public void setRfid(String rfid) { this.rfid = rfid; }

    public String getRfidHex() { return rfidHex; }
    public void setRfidHex(String rfidHex) { this.rfidHex = rfidHex; }

    public Long getTkId() { return tkId; }
    public void setTkId(Long tkId) { this.tkId = tkId; }

    public String getNguoiMap() { return nguoiMap; }
    public void setNguoiMap(String nguoiMap) { this.nguoiMap = nguoiMap; }

    public Instant getThoiGianMap() { return thoiGianMap; }
    public void setThoiGianMap(Instant thoiGianMap) { this.thoiGianMap = thoiGianMap; }

    public Instant getNgayTao() { return ngayTao; }
    public void setNgayTao(Instant ngayTao) { this.ngayTao = ngayTao; }

    public String getLenhSanXuat() { return lenhSanXuat; }
    public void setLenhSanXuat(String lenhSanXuat) { this.lenhSanXuat = lenhSanXuat; }

    public String getMaHang() { return maHang; }
    public void setMaHang(String maHang) { this.maHang = maHang; }

    public String getLoaiNguyenLieu() { return loaiNguyenLieu; }
    public void setLoaiNguyenLieu(String loaiNguyenLieu) { this.loaiNguyenLieu = loaiNguyenLieu; }

    public String getNguoiCat() { return nguoiCat; }
    public void setNguoiCat(String nguoiCat) { this.nguoiCat = nguoiCat; }

    public String getNguoiTrai() { return nguoiTrai; }
    public void setNguoiTrai(String nguoiTrai) { this.nguoiTrai = nguoiTrai; }

    public String getAnhMauPhieu() { return anhMauPhieu; }
    public void setAnhMauPhieu(String anhMauPhieu) { this.anhMauPhieu = anhMauPhieu; }

    public String getTenPhongBan() { return tenPhongBan; }
    public void setTenPhongBan(String tenPhongBan) { this.tenPhongBan = tenPhongBan; }

    public String getMua() { return mua; }
    public void setMua(String mua) { this.mua = mua; }

    public String getBanMay() { return banMay; }
    public void setBanMay(String banMay) { this.banMay = banMay; }

    public String getTenSize() { return tenSize; }
    public void setTenSize(String tenSize) { this.tenSize = tenSize; }

    public String getIdBanMay() { return idBanMay; }
    public void setIdBanMay(String idBanMay) { this.idBanMay = idBanMay; }

    public Long getPhieuHoachToanId() { return phieuHoachToanId; }
    public void setPhieuHoachToanId(Long phieuHoachToanId) { this.phieuHoachToanId = phieuHoachToanId; }

    public String getDan() { return dan; }
    public void setDan(String dan) { this.dan = dan; }

    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

    public String getTenChiTiet() { return tenChiTiet; }
    public void setTenChiTiet(String tenChiTiet) { this.tenChiTiet = tenChiTiet; }

    public String getPo() { return po; }
    public void setPo(String po) { this.po = po; }

    public String getPart() { return part; }
    public void setPart(String part) { this.part = part; }

    public Integer getStt() { return stt; }
    public void setStt(Integer stt) { this.stt = stt; }

    public String getMaCay() { return maCay; }
    public void setMaCay(String maCay) { this.maCay = maCay; }

    public Integer getSoLopTrai() { return soLopTrai; }
    public void setSoLopTrai(Integer soLopTrai) { this.soLopTrai = soLopTrai; }

    public String getLot() { return lot; }
    public void setLot(String lot) { this.lot = lot; }

    public String getAnhMau() { return anhMau; }
    public void setAnhMau(String anhMau) { this.anhMau = anhMau; }

    public Instant getNgayCapNhat() { return ngayCapNhat; }
    public void setNgayCapNhat(Instant ngayCapNhat) { this.ngayCapNhat = ngayCapNhat; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }

    public Integer getSoLuongDaMap() { return soLuongDaMap; }
    public void setSoLuongDaMap(Integer soLuongDaMap) { this.soLuongDaMap = soLuongDaMap; }

    public Integer getSoLuongConLai() { return soLuongConLai; }
    public void setSoLuongConLai(Integer soLuongConLai) { this.soLuongConLai = soLuongConLai; }

    public String getKhachHang() { return khachHang; }
    public void setKhachHang(String khachHang) { this.khachHang = khachHang; }

    public Boolean getDecathlonCustomer() { return decathlonCustomer; }
    public void setDecathlonCustomer(Boolean decathlonCustomer) { this.decathlonCustomer = decathlonCustomer; }
}
