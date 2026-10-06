package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Phân quyền nút SX xong trên UI — kết quả từ {@code SP_PermissionCheckpointOnBtnSXXong}.
 * <p>
 * Proc trả cột {@code is_valid} (BIT/INT…); backend map sang {@link #getCoQuyen()} và {@link #getIsValid()}.
 */
public class BtnSXXongPermissionDTO implements Serializable {

    private Boolean coQuyen;

    public BtnSXXongPermissionDTO() {}

    public BtnSXXongPermissionDTO(Boolean coQuyen) {
        this.coQuyen = coQuyen;
    }

    public Boolean getCoQuyen() {
        return coQuyen;
    }

    /**
     * Trùng nghĩa với cột {@code is_valid} trong result set của proc (tiện cho client đọc đúng tên SQL).
     */
    public Boolean getIsValid() {
        return coQuyen;
    }

    public void setCoQuyen(Boolean coQuyen) {
        this.coQuyen = coQuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BtnSXXongPermissionDTO that = (BtnSXXongPermissionDTO) o;
        return Objects.equals(coQuyen, that.coQuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coQuyen);
    }
}
