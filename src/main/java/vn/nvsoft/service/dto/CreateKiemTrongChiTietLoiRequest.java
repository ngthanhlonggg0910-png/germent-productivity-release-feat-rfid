package vn.nvsoft.service.dto;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateKiemTrongChiTietLoiRequest implements Serializable {

    @NotNull
    private Long checkpointId;

    @NotNull
    private Long khsxId;

    @NotNull
    private Long proLoiId;

    @NotNull
    private String maLoi;

    @Min(1)
    @Max(50)
    private int quantity = 1;

    private String rfidDisplayName;
    private String rfidHex;
    private String rfidBarcode;
    private String barcodeTachCay;

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

    public Long getProLoiId() {
        return proLoiId;
    }

    public void setProLoiId(Long proLoiId) {
        this.proLoiId = proLoiId;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getBarcodeTachCay() {
        return barcodeTachCay;
    }

    public void setBarcodeTachCay(String barcodeTachCay) {
        this.barcodeTachCay = barcodeTachCay;
    }
}
