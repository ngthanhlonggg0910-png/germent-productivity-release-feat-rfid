package vn.nvsoft.web.rest.vm;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSCLTrenTruyenTheoGioVM {

    private String chuyen;
    private Instant ngayCapNhat;
    private String po;
    private Integer nangSuatKeHoach;
    private Integer ndsxKeHoach;
    private Integer nangSuatThucHien;
    private Integer ndsxThucHien;
    private Integer nhapKho;
    private Integer ndsxTrungBinh;
    private List<NSTruyenTheoGioLamViecVM<Integer>> nangSuatThucHienTheoGio;
    private List<NSTruyenTheoGioLamViecVM<Float>> phanTram;
}
