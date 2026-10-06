package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProKhachHang} entity.
 */
public class ProKhachHangDTO implements Serializable {

    private Long id;

    private String ten;

    private String nhom;

    private String diaChi;


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

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProKhachHangDTO proKhachHangDTO = (ProKhachHangDTO) o;
        if (proKhachHangDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proKhachHangDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProKhachHangDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", nhom='" + getNhom() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            "}";
    }
}
