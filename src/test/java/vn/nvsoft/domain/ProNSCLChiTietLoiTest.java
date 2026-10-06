package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNSCLChiTietLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNSCLChiTietLoi.class);
        ProNSCLChiTietLoi proNSCLChiTietLoi1 = new ProNSCLChiTietLoi();
        proNSCLChiTietLoi1.setId(1L);
        ProNSCLChiTietLoi proNSCLChiTietLoi2 = new ProNSCLChiTietLoi();
        proNSCLChiTietLoi2.setId(proNSCLChiTietLoi1.getId());
        assertThat(proNSCLChiTietLoi1).isEqualTo(proNSCLChiTietLoi2);
        proNSCLChiTietLoi2.setId(2L);
        assertThat(proNSCLChiTietLoi1).isNotEqualTo(proNSCLChiTietLoi2);
        proNSCLChiTietLoi1.setId(null);
        assertThat(proNSCLChiTietLoi1).isNotEqualTo(proNSCLChiTietLoi2);
    }
}
