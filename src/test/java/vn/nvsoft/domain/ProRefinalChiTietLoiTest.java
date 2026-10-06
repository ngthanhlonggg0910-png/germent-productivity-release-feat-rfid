package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalChiTietLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalChiTietLoi.class);
        ProRefinalChiTietLoi proRefinalChiTietLoi1 = new ProRefinalChiTietLoi();
        proRefinalChiTietLoi1.setId(1L);
        ProRefinalChiTietLoi proRefinalChiTietLoi2 = new ProRefinalChiTietLoi();
        proRefinalChiTietLoi2.setId(proRefinalChiTietLoi1.getId());
        assertThat(proRefinalChiTietLoi1).isEqualTo(proRefinalChiTietLoi2);
        proRefinalChiTietLoi2.setId(2L);
        assertThat(proRefinalChiTietLoi1).isNotEqualTo(proRefinalChiTietLoi2);
        proRefinalChiTietLoi1.setId(null);
        assertThat(proRefinalChiTietLoi1).isNotEqualTo(proRefinalChiTietLoi2);
    }
}
