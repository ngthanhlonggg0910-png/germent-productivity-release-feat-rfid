package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProCongTy} entity.
 */
public class ProCongTyDTO implements Serializable {

    private Long id;

    private String ten;

    private String diaChi;

    private String mst;


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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProCongTyDTO proCongTyDTO = (ProCongTyDTO) o;
        if (proCongTyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proCongTyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProCongTyDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", mst='" + getMst() + "'" +
            "}";
    }
}
