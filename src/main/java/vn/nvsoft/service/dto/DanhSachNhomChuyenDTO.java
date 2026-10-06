package vn.nvsoft.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Một dòng kết quả từ {@code SP_GetDanhSachNhom} (nhóm trên chuyền theo checkpoint và ngày).
 */
public class DanhSachNhomChuyenDTO implements Serializable {

    private Long idNhom;
    private String tenNhom;

    public DanhSachNhomChuyenDTO() {}

    public DanhSachNhomChuyenDTO(Long idNhom, String tenNhom) {
        this.idNhom = idNhom;
        this.tenNhom = tenNhom;
    }

    public Long getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(Long idNhom) {
        this.idNhom = idNhom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DanhSachNhomChuyenDTO that = (DanhSachNhomChuyenDTO) o;
        return Objects.equals(idNhom, that.idNhom) && Objects.equals(tenNhom, that.tenNhom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNhom, tenNhom);
    }
}
