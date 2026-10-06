package vn.nvsoft.service.dto;

import java.io.Serializable;

/**
 * DTO cho thông tin mã cây theo PO
 */
public class MaCayByPODTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String lot;
    private Integer soTemDaQuet;
    private Integer tongSoTem;
    private String productCode;

    public MaCayByPODTO() {
    }

    public MaCayByPODTO(String code, String lot, Integer soTemDaQuet, Integer tongSoTem) {
        this.code = code;
        this.lot = lot;
        this.soTemDaQuet = soTemDaQuet;
        this.tongSoTem = tongSoTem;
    }

    public MaCayByPODTO(String code, String lot, Integer soTemDaQuet, Integer tongSoTem, String productCode) {
        this.code = code;
        this.lot = lot;
        this.soTemDaQuet = soTemDaQuet;
        this.tongSoTem = tongSoTem;
        this.productCode = productCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Integer getSoTemDaQuet() {
        return soTemDaQuet;
    }

    public void setSoTemDaQuet(Integer soTemDaQuet) {
        this.soTemDaQuet = soTemDaQuet;
    }

    public Integer getTongSoTem() {
        return tongSoTem;
    }

    public void setTongSoTem(Integer tongSoTem) {
        this.tongSoTem = tongSoTem;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "MaCayByPODTO{" +
                "code='" + code + '\'' +
                ", lot='" + lot + '\'' +
                ", soTemDaQuet=" + soTemDaQuet +
                ", tongSoTem=" + tongSoTem +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
