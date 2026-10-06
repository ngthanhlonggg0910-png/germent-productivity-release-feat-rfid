package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ProNhaMay} entity.
 */
public class ProNhaMayDTO implements Serializable {

    private Long id;

    private String ten;

    private Integer stt;


    private Long proCongTyId;

    private String proCongTyTen;

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

    public Long getProCongTyId() {
        return proCongTyId;
    }

    public void setProCongTyId(Long proCongTyId) {
        this.proCongTyId = proCongTyId;
    }

    public String getProCongTyTen() {
        return proCongTyTen;
    }

    public void setProCongTyTen(String proCongTyTen) {
        this.proCongTyTen = proCongTyTen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProNhaMayDTO proNhaMayDTO = (ProNhaMayDTO) o;
        if (proNhaMayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proNhaMayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProNhaMayDTO{" +
            "id=" + getId() +
            ", ten='" + getTen() + "'" +
            ", stt=" + getStt() +
            ", proCongTy=" + getProCongTyId() +
            ", proCongTy='" + getProCongTyTen() + "'" +
            "}";
    }
}
