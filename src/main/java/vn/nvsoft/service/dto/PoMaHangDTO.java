package vn.nvsoft.service.dto;

import java.io.Serializable;

/**
 * DTO cho 1 PO kèm mã hàng (ProductCode) gắn với mã cây
 */
public class PoMaHangDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String po;
    private String productCode;

    public PoMaHangDTO() {
    }

    public PoMaHangDTO(String po, String productCode) {
        this.po = po;
        this.productCode = productCode;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "PoMaHangDTO{" +
                "po='" + po + '\'' +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
