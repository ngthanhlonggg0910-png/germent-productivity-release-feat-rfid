package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ThuVienLoi} entity.
 */
public class ThuVienLoiDTO implements Serializable {

    private Long id;

    private String maLoi;

    private String tenLoi;

    private String nguoiTao;

    private Instant ngayTao;

    private Integer userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThuVienLoiDTO thuVienLoiDTO = (ThuVienLoiDTO) o;
        if (thuVienLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thuVienLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThuVienLoiDTO{" +
            "id=" + getId() +
            ", maLoi='" + getMaLoi() + "'" +
            ", tenLoi='" + getTenLoi() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
