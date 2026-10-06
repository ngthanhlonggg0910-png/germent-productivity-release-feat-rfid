package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinal.class);
        ProRefinal proRefinal1 = new ProRefinal();
        proRefinal1.setId(1L);
        ProRefinal proRefinal2 = new ProRefinal();
        proRefinal2.setId(proRefinal1.getId());
        assertThat(proRefinal1).isEqualTo(proRefinal2);
        proRefinal2.setId(2L);
        assertThat(proRefinal1).isNotEqualTo(proRefinal2);
        proRefinal1.setId(null);
        assertThat(proRefinal1).isNotEqualTo(proRefinal2);
    }
}
