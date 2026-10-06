package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.QuyCachNPL} entity.
 */
public class QuyCachNPLDTO implements Serializable {

    private Long id;

    private String tenNPL;

    private String maNPL;

    private String nguoiTao;

    private String ngayTao;

    private Integer userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNPL() {
        return tenNPL;
    }

    public void setTenNPL(String tenNPL) {
        this.tenNPL = tenNPL;
    }

    public String getMaNPL() {
        return maNPL;
    }

    public void setMaNPL(String maNPL) {
        this.maNPL = maNPL;
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

        QuyCachNPLDTO quyCachNPLDTO = (QuyCachNPLDTO) o;
        if (quyCachNPLDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quyCachNPLDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuyCachNPLDTO{" +
            "id=" + getId() +
            ", tenNPL='" + getTenNPL() + "'" +
            ", maNPL='" + getMaNPL() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
