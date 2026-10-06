package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ThongSoThucThi} entity.
 */
public class ThongSoThucThiDTO implements Serializable {

    private Long id;

    private String spIndex;

//    private Long thongSoDoSPSizeId;
    private ThongSoDoSPSizeDTO thongSoDoSPSize;

    private DungSaiDTO dungSai;
//    private Long dungSaiId;

    private Long proKeHoachSanXuatId;
//    private ProKeHoachSanXuatDTO proKeHoachSanXuat;

    private String po;

    private String mausanpham;
  
    private Boolean isqa;

    private Long checkPointId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpIndex() {
        return spIndex;
    }

    public void setSpIndex(String spIndex) {
        this.spIndex = spIndex;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getMausanpham() {
        return mausanpham;
    }

    public void setMausanpham(String mausanpham) {
        this.mausanpham = mausanpham;
    }
 
    public Boolean getIsqa() {
        return isqa;
    }

    public void setIsqa(Boolean isqa) {
        this.isqa = isqa;
    }

    public Long getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(Long checkPointId) {
        this.checkPointId = checkPointId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThongSoThucThiDTO thongSoThucThiDTO = (ThongSoThucThiDTO) o;
        if (thongSoThucThiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongSoThucThiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public ThongSoDoSPSizeDTO getThongSoDoSPSize() {
        return thongSoDoSPSize;
    }

    public void setThongSoDoSPSize(ThongSoDoSPSizeDTO thongSoDoSPSize) {
        this.thongSoDoSPSize = thongSoDoSPSize;
    }

    public DungSaiDTO getDungSai() {
        return dungSai;
    }

    public void setDungSai(DungSaiDTO dungSai) {
        this.dungSai = dungSai;
    }

    public Long getProKeHoachSanXuatId() {
        return proKeHoachSanXuatId;
    }

    public void setProKeHoachSanXuatId(Long proKeHoachSanXuatId) {
        this.proKeHoachSanXuatId = proKeHoachSanXuatId;
    }
}
