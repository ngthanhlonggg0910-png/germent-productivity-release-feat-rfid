package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalCCTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalCC.class);
        ProRefinalCC proRefinalCC1 = new ProRefinalCC();
        proRefinalCC1.setId(1L);
        ProRefinalCC proRefinalCC2 = new ProRefinalCC();
        proRefinalCC2.setId(proRefinalCC1.getId());
        assertThat(proRefinalCC1).isEqualTo(proRefinalCC2);
        proRefinalCC2.setId(2L);
        assertThat(proRefinalCC1).isNotEqualTo(proRefinalCC2);
        proRefinalCC1.setId(null);
        assertThat(proRefinalCC1).isNotEqualTo(proRefinalCC2);
    }
}
