package vn.nvsoft.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Delta sửa PO qua ngày.
 */
public class ApplyPoFixRequestDTO {

    @NotBlank
    private String poNo;

    @NotNull
    private Long khsxId;

    @NotNull
    private Long nsclId;

    @NotNull
    @Min(1)
    private Integer soLuongSua;

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Long getKhsxId() {
        return khsxId;
    }

    public void setKhsxId(Long khsxId) {
        this.khsxId = khsxId;
    }

    public Long getNsclId() {
        return nsclId;
    }

    public void setNsclId(Long nsclId) {
        this.nsclId = nsclId;
    }

    public Integer getSoLuongSua() {
        return soLuongSua;
    }

    public void setSoLuongSua(Integer soLuongSua) {
        this.soLuongSua = soLuongSua;
    }
}
