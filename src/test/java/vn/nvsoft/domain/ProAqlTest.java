package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProAqlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProAql.class);
        ProAql proAql1 = new ProAql();
        proAql1.setId(1L);
        ProAql proAql2 = new ProAql();
        proAql2.setId(proAql1.getId());
        assertThat(proAql1).isEqualTo(proAql2);
        proAql2.setId(2L);
        assertThat(proAql1).isNotEqualTo(proAql2);
        proAql1.setId(null);
        assertThat(proAql1).isNotEqualTo(proAql2);
    }
}
