package vn.nvsoft.service.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MarkKiemTrongSuaRequest implements Serializable {

    @NotNull
    private Long checkpointId;

    @NotNull
    private Long khsxId;

    @NotBlank
    private String rfidDisplayName;

    private String rfidHex;
    private String rfidBarcode;

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public Long getKhsxId() {
        return khsxId;
    }

    public void setKhsxId(Long khsxId) {
        this.khsxId = khsxId;
    }

    public String getRfidDisplayName() {
        return rfidDisplayName;
    }

    public void setRfidDisplayName(String rfidDisplayName) {
        this.rfidDisplayName = rfidDisplayName;
    }

    public String getRfidHex() {
        return rfidHex;
    }

    public void setRfidHex(String rfidHex) {
        this.rfidHex = rfidHex;
    }

    public String getRfidBarcode() {
        return rfidBarcode;
    }

    public void setRfidBarcode(String rfidBarcode) {
        this.rfidBarcode = rfidBarcode;
    }
}
