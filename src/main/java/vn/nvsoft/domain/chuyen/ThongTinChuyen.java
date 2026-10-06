package vn.nvsoft.domain.chuyen;

import java.sql.Date;

public interface ThongTinChuyen {
    Long getPXId();
    String getMaCum();
    String getTenCum();
    String getNguoiTao();
    String getTenNguoiTao();
//    Date getNgayTao();
    Long getTKId();
    Long getCumId();//chuyenId = cumId
}
