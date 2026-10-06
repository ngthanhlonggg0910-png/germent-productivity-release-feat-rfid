package vn.nvsoft.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;

/**
 * Trạng thái kích hoạt của một kế hoạch sản xuất (theo {@code pro_ke_hoach_san_xuat.id}).
 */
public class ProKeHoachSanXuatActivationStatusDTO implements Serializable {

    private Long khsxId;
    private Boolean isActived;
    private Instant activeTime;

    public ProKeHoachSanXuatActivationStatusDTO(Long khsxId, Boolean isActived, Instant activeTime) {
        this.khsxId = khsxId;
        this.isActived = isActived;
        this.activeTime = activeTime;
    }

    public Long getKhsxId() {
        return khsxId;
    }

    /**
     * {@code true}/{@code false} nếu đã cấu hình; {@code null} nếu DB chưa có giá trị (cột null).
     */
    @JsonProperty("isActived")
    public Boolean getIsActived() {
        return isActived;
    }

    public Instant getActiveTime() {
        return activeTime;
    }
}
