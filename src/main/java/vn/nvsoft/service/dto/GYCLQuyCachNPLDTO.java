package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.GYCLQuyCachNPL} entity.
 */
public class GYCLQuyCachNPLDTO implements Serializable {

    private Long id;

    private String tenNPL;

    private String nguoiTao;

    private Instant ngayTao;

    private Integer userId;

    private Boolean yesNo;

    private String nA;


    private Long proGopYChatLuongId;

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

    public Boolean isYesNo() {
        return yesNo;
    }

    public void setYesNo(Boolean yesNo) {
        this.yesNo = yesNo;
    }

    public String getnA() {
        return nA;
    }

    public void setnA(String nA) {
        this.nA = nA;
    }

    public Long getProGopYChatLuongId() {
        return proGopYChatLuongId;
    }

    public void setProGopYChatLuongId(Long gopYChatLuongId) {
        this.proGopYChatLuongId = gopYChatLuongId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO = (GYCLQuyCachNPLDTO) o;
        if (gYCLQuyCachNPLDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gYCLQuyCachNPLDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GYCLQuyCachNPLDTO{" +
            "id=" + getId() +
            ", tenNPL='" + getTenNPL() + "'" +
            ", nguoiTao='" + getNguoiTao() + "'" +
            ", ngayTao='" + getNgayTao() + "'" +
            ", userId=" + getUserId() +
            ", yesNo='" + isYesNo() + "'" +
            ", nA='" + getnA() + "'" +
            ", proGopYChatLuong=" + getProGopYChatLuongId() +
            "}";
    }
}
