package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalCCChiTietLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalCCChiTietLoi.class);
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi1 = new ProRefinalCCChiTietLoi();
        proRefinalCCChiTietLoi1.setId(1L);
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi2 = new ProRefinalCCChiTietLoi();
        proRefinalCCChiTietLoi2.setId(proRefinalCCChiTietLoi1.getId());
        assertThat(proRefinalCCChiTietLoi1).isEqualTo(proRefinalCCChiTietLoi2);
        proRefinalCCChiTietLoi2.setId(2L);
        assertThat(proRefinalCCChiTietLoi1).isNotEqualTo(proRefinalCCChiTietLoi2);
        proRefinalCCChiTietLoi1.setId(null);
        assertThat(proRefinalCCChiTietLoi1).isNotEqualTo(proRefinalCCChiTietLoi2);
    }
}
