package vn.nvsoft.web.rest.vm;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSCLTrenTruyenTheoDonHangVM {

    private String chuyen;
    private Instant ngayCapNhat;
    private String po;
    private String gach;
    private Instant ngayKiemFinalDuKien;
    private Instant ngayXuatHangDuKien;
    private Integer soLuongPhanBo;
    private Integer mayRa;

}
