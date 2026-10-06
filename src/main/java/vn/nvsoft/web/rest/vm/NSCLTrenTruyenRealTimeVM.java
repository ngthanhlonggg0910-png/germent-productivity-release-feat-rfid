package vn.nvsoft.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NSCLTrenTruyenRealTimeVM {

    private NSCLTrenTruyenTheoGioVM nsclTrenTruyenTheoGioVM;
    private NSCLTrenTruyenTheoDonHangVM nsclTrenTruyenTheoDonHangVM;
    private NSCLTrenTruyenTopErrorVM nsclTrenTruyenTopErrorVM;

}
