package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProChuyen} entity.
 */
public class ProChuyenDTO implements Serializable {

    private Long id;

    private String ten;

    private Integer stt;

    private String tenLoaiChuyen;


    private Long proLoaiChuyenId;

    private String proLoaiChuyenTen;

    private Long proNhaMayId;

    private String proNhaMayTen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getTenLoaiChuyen() {
        return tenLoaiChuyen;
    }

    public void setTenLoaiChuyen(String tenLoaiChuyen) {
        this.tenLoaiChuyen = tenLoaiChuyen;
    }

    public Long getProLoaiChuyenId() {
        return proLoaiChuyenId;
    }

    public void setProLoaiChuyenId(Long proLoaiChuyenId) {
        this.proLoaiChuyenId = proLoaiChuyenId;
    }

    public String getProLoaiChuyenTen() {
        return proLoaiChuyenTen;
    }

    public void setProLoaiChuyenTen(String proLoaiChuyenTen) {
        this.proLoaiChuyenTen = proLoaiChuyenTen;
    }

    public Long getProNhaMayId() {
        return proNhaMayId;
    }

    public void setProNhaMayId(Long proNhaMayId) {
        this.proNhaMayId = proNhaMayId;
    }

    public String getProNhaMayTen() {
        return proNhaMayTen;
    }

    public void setProNhaMayTen(String proNhaMayTen) {
        this.proNhaMayTen = proNhaMayTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProChuyenDTO proChuyenDTO = (ProChuyenDTO) o;
        if (proChuyenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proChuyenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProChuyenDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", stt=" + getStt() +
            ", tenLoaiChuyen='" + getTenLoaiChuyen() + "'" +
            ", proLoaiChuyen=" + getProLoaiChuyenId() +
            ", proLoaiChuyen='" + getProLoaiChuyenTen() + "'" +
            ", proNhaMay=" + getProNhaMayId() +
            ", proNhaMay='" + getProNhaMayTen() + "'" +
            "}";
    }
}
