package vn.nvsoft.domain.heso;

import java.sql.Date;

public interface HeSoNhanVien {
    Long getId();
    Date getNgay_san_xuat();
    Long getPro_chuyen_id();
    Double getHeso();
    String getUrlhinh();
    String getSothe();
    String getHoten();
    Boolean getXacnhan();
    String getChucVu();
}
