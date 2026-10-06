package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProFinalChiTietLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProFinalChiTietLoi.class);
        ProFinalChiTietLoi proFinalChiTietLoi1 = new ProFinalChiTietLoi();
        proFinalChiTietLoi1.setId(1L);
        ProFinalChiTietLoi proFinalChiTietLoi2 = new ProFinalChiTietLoi();
        proFinalChiTietLoi2.setId(proFinalChiTietLoi1.getId());
        assertThat(proFinalChiTietLoi1).isEqualTo(proFinalChiTietLoi2);
        proFinalChiTietLoi2.setId(2L);
        assertThat(proFinalChiTietLoi1).isNotEqualTo(proFinalChiTietLoi2);
        proFinalChiTietLoi1.setId(null);
        assertThat(proFinalChiTietLoi1).isNotEqualTo(proFinalChiTietLoi2);
    }
}
