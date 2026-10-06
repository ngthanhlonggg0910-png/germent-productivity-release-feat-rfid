package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueCumLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssueCumLoi.class);
        ProIssueCumLoi proIssueCumLoi1 = new ProIssueCumLoi();
        proIssueCumLoi1.setId(1L);
        ProIssueCumLoi proIssueCumLoi2 = new ProIssueCumLoi();
        proIssueCumLoi2.setId(proIssueCumLoi1.getId());
        assertThat(proIssueCumLoi1).isEqualTo(proIssueCumLoi2);
        proIssueCumLoi2.setId(2L);
        assertThat(proIssueCumLoi1).isNotEqualTo(proIssueCumLoi2);
        proIssueCumLoi1.setId(null);
        assertThat(proIssueCumLoi1).isNotEqualTo(proIssueCumLoi2);
    }
}
