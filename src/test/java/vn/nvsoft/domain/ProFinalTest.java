package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProFinalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProFinal.class);
        ProFinal proFinal1 = new ProFinal();
        proFinal1.setId(1L);
        ProFinal proFinal2 = new ProFinal();
        proFinal2.setId(proFinal1.getId());
        assertThat(proFinal1).isEqualTo(proFinal2);
        proFinal2.setId(2L);
        assertThat(proFinal1).isNotEqualTo(proFinal2);
        proFinal1.setId(null);
        assertThat(proFinal1).isNotEqualTo(proFinal2);
    }
}
