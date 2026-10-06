package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.GYCLKhacPhuc} entity.
 */
public class GYCLKhacPhucDTO implements Serializable {

    private Long id;

    private Integer cumId;

    private String tenCum;

    private String gopY;

    private String ketQua;

    private String nguoiTao;

    private Instant userId;

    private Instant ngayTao;


    private Long proGopYChatLuongId;

    private Long thuVienLoiId;

    private Long khacPhucLoiId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCumId() {
        return cumId;
    }

    public void setCumId(Integer cumId) {
        this.cumId = cumId;
    }

    public String getTenCum() {
        return tenCum;
    }

    public void setTenCum(String tenCum) {
        this.tenCum = tenCum;
    }

    public String getGopY() {
        return gopY;
    }

    public void setGopY(String gopY) {
        this.gopY = gopY;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getUserId() {
        return userId;
    }

    public void setUserId(Instant userId) {
        this.userId = userId;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Long getProGopYChatLuongId() {
        return proGopYChatLuongId;
    }

    public void setProGopYChatLuongId(Long gopYChatLuongId) {
        this.proGopYChatLuongId = gopYChatLuongId;
    }

    public Long getThuVienLoiId() {
        return thuVienLoiId;
    }

    public void setThuVienLoiId(Long thuVienLoiId) {
        this.thuVienLoiId = thuVienLoiId;
    }

    public Long getKhacPhucLoiId() {
        return khacPhucLoiId;
    }

    public void setKhacPhucLoiId(Long khacPhucLoiId) {
        this.khacPhucLoiId = khacPhucLoiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GYCLKhacPhucDTO gYCLKhacPhucDTO = (GYCLKhacPhucDTO) o;
        if (gYCLKhacPhucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gYCLKhacPhucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GYCLKhacPhucDTO{" +
            "id=" + getId() +
            ", cumId=" + getCumId() +
            ", tenCum='" + getTenCum() + "'" +
            ", gopY='" + getGopY() + "'" +
            ", ketQua='" + getKetQua() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", userId='" + getUserId() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", proGopYChatLuong=" + getProGopYChatLuongId() +
            ", thuVienLoi=" + getThuVienLoiId() +
            ", khacPhucLoi=" + getKhacPhucLoiId() +
            "}";
    }
}
