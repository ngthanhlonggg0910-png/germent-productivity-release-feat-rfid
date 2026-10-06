package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ThongSoDoSP} entity.
 */
public class ThongSoDoSPDTO implements Serializable {

    private Long id;

    private String note;

    private Long stt;

    private Boolean revised;

    private Long thongSoDoId;

    private String thongSoDoCode;

    private Long dungSaiId;

    private String dungSaiInchLabel;

    private String dungSaiCmLabel;

    private Long dungSaiIdMax;

    private String dungSaiInchLabelMax;

    private String dungSaiCmLabelMax;

    private Long proSanPhamId;

    private String proSanPhamMaSanPham;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStt() {
        return stt;
    }

    public void setStt(Long stt) {
        this.stt = stt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isRevised() {
        return revised;
    }

    public void setRevised(Boolean revised) {
        this.revised = revised;
    }

    public Long getThongSoDoId() {
        return thongSoDoId;
    }

    public void setThongSoDoId(Long thongSoDoId) {
        this.thongSoDoId = thongSoDoId;
    }

    public String getThongSoDoCode() {
        return thongSoDoCode;
    }

    public void setThongSoDoCode(String thongSoDoCode) {
        this.thongSoDoCode = thongSoDoCode;
    }

    public Long getDungSaiId() {
        return dungSaiId;
    }

    public void setDungSaiId(Long dungSaiId) {
        this.dungSaiId = dungSaiId;
    }

    public String getDungSaiInchLabel() {
        return dungSaiInchLabel;
    }

    public String getDungSaiCmLabel() {
        return dungSaiCmLabel;
    }

    public void setDungSaiCmLabel(String dungSaiCmLabel) {
        this.dungSaiCmLabel = dungSaiCmLabel;
    }

    public void setDungSaiInchLabel(String dungSaiInchLabel) {
        this.dungSaiInchLabel = dungSaiInchLabel;
    }

    public Long getProSanPhamId() {
        return proSanPhamId;
    }

    public void setProSanPhamId(Long proSanPhamId) {
        this.proSanPhamId = proSanPhamId;
    }

    public String getProSanPhamMaSanPham() {
        return proSanPhamMaSanPham;
    }

    public void setProSanPhamMaSanPham(String proSanPhamMaSanPham) {
        this.proSanPhamMaSanPham = proSanPhamMaSanPham;
    }

    public Long getDungSaiIdMax() {
        return dungSaiIdMax;
    }

    public void setDungSaiIdMax(Long dungSaiIdMax) {
        this.dungSaiIdMax = dungSaiIdMax;
    }

    public String getDungSaiInchLabelMax() {
        return dungSaiInchLabelMax;
    }

    public void setDungSaiInchLabelMax(String dungSaiInchLabelMax) {
        this.dungSaiInchLabelMax = dungSaiInchLabelMax;
    }

    public String getDungSaiCmLabelMax() {
        return dungSaiCmLabelMax;
    }

    public void setDungSaiCmLabelMax(String dungSaiCmLabelMax) {
        this.dungSaiCmLabelMax = dungSaiCmLabelMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThongSoDoSPDTO thongSoDoSPDTO = (ThongSoDoSPDTO) o;
        if (thongSoDoSPDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongSoDoSPDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongSoDoSPDTO{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            ", thongSoDo=" + getThongSoDoId() +
            ", thongSoDo='" + getThongSoDoCode() + "'" +
            ", dungSai=" + getDungSaiId() +
            ", dungSai='" + getDungSaiInchLabel() + "'" +
            ", proSanPham=" + getProSanPhamId() +
            ", proSanPham='" + getProSanPhamMaSanPham() + "'" +
            "}";
    }
}
