package vn.nvsoft.service.dto;

/**
 * Lỗi quá khứ còn tồn đọng (đến hết hôm qua) trong pool query 2.
 */
public class PoFixPastDTO {

    private String poNo;
    private Long khsxId;
    private Integer tongLoiQuaKhu;
    /** LOI RFID (latest state) chưa SUA — tối đa số dòng SUA tự insert khi POST po-fix */
    private Integer soLoiRfidChuaSua;

    public PoFixPastDTO() {
    }

    public PoFixPastDTO(String poNo, Long khsxId, Integer tongLoiQuaKhu) {
        this(poNo, khsxId, tongLoiQuaKhu, null);
    }

    public PoFixPastDTO(String poNo, Long khsxId, Integer tongLoiQuaKhu, Integer soLoiRfidChuaSua) {
        this.poNo = poNo;
        this.khsxId = khsxId;
        this.tongLoiQuaKhu = tongLoiQuaKhu;
        this.soLoiRfidChuaSua = soLoiRfidChuaSua;
    }

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

    public Integer getTongLoiQuaKhu() {
        return tongLoiQuaKhu;
    }

    public void setTongLoiQuaKhu(Integer tongLoiQuaKhu) {
        this.tongLoiQuaKhu = tongLoiQuaKhu;
    }

    public Integer getSoLoiRfidChuaSua() {
        return soLoiRfidChuaSua;
    }

    public void setSoLoiRfidChuaSua(Integer soLoiRfidChuaSua) {
        this.soLoiRfidChuaSua = soLoiRfidChuaSua;
    }
}
