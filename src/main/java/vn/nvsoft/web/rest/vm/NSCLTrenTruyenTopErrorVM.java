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
public class NSCLTrenTruyenTopErrorVM {

    private String chuyen;
    private Instant ngayCapNhat;
    private String po;
    private List<DangLoiVM> topLoiCaoNhat;
    private String urlHinhMatTruoc;
    private String urlHinhMatSau;
    private List<ChiTietLoiVM> viTriLoiMatTruoc;
    private List<ChiTietLoiVM> viTriLoiMatSau;
}
