package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.KhacPhucLoi} entity.
 */
public class KhacPhucLoiDTO implements Serializable {

    private Long id;

    private String maKP;

    private String tenKP;

    private String nguoiTao;

    private String ngayTao;

    private Integer userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKP() {
        return maKP;
    }

    public void setMaKP(String maKP) {
        this.maKP = maKP;
    }

    public String getTenKP() {
        return tenKP;
    }

    public void setTenKP(String tenKP) {
        this.tenKP = tenKP;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
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

        KhacPhucLoiDTO khacPhucLoiDTO = (KhacPhucLoiDTO) o;
        if (khacPhucLoiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), khacPhucLoiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KhacPhucLoiDTO{" +
            "id=" + getId() +
            ", maKP='" + getMaKP() + "'" +
            ", tenKP='" + getTenKP() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
