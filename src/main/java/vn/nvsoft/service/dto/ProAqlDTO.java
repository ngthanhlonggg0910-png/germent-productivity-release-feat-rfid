package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProAql} entity.
 */
public class ProAqlDTO implements Serializable {

    private Long id;

    private String aql;

    private Integer soLuongDonHangTu;

    private Integer soLuongDonHangDen;

    private Integer soLuongKiem;

    private Integer soLuongSeFail;

    private Integer soLuongSePass;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAql() {
        return aql;
    }

    public void setAql(String aql) {
        this.aql = aql;
    }

    public Integer getSoLuongDonHangTu() {
        return soLuongDonHangTu;
    }

    public void setSoLuongDonHangTu(Integer soLuongDonHangTu) {
        this.soLuongDonHangTu = soLuongDonHangTu;
    }

    public Integer getSoLuongDonHangDen() {
        return soLuongDonHangDen;
    }

    public void setSoLuongDonHangDen(Integer soLuongDonHangDen) {
        this.soLuongDonHangDen = soLuongDonHangDen;
    }

    public Integer getSoLuongKiem() {
        return soLuongKiem;
    }

    public void setSoLuongKiem(Integer soLuongKiem) {
        this.soLuongKiem = soLuongKiem;
    }

    public Integer getSoLuongSeFail() {
        return soLuongSeFail;
    }

    public void setSoLuongSeFail(Integer soLuongSeFail) {
        this.soLuongSeFail = soLuongSeFail;
    }

    public Integer getSoLuongSePass() {
        return soLuongSePass;
    }

    public void setSoLuongSePass(Integer soLuongSePass) {
        this.soLuongSePass = soLuongSePass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProAqlDTO proAqlDTO = (ProAqlDTO) o;
        if (proAqlDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proAqlDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProAqlDTO{" +
            "id=" + getId() +
            ", aql='" + getAql() + "'" +
            ", soLuongDonHangTu=" + getSoLuongDonHangTu() +
            ", soLuongDonHangDen=" + getSoLuongDonHangDen() +
            ", soLuongKiem=" + getSoLuongKiem() +
            ", soLuongSeFail=" + getSoLuongSeFail() +
            ", soLuongSePass=" + getSoLuongSePass() +
            "}";
    }
}
