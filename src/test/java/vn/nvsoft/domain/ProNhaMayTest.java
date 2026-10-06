package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNhaMayTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNhaMay.class);
        ProNhaMay proNhaMay1 = new ProNhaMay();
        proNhaMay1.setId(1L);
        ProNhaMay proNhaMay2 = new ProNhaMay();
        proNhaMay2.setId(proNhaMay1.getId());
        assertThat(proNhaMay1).isEqualTo(proNhaMay2);
        proNhaMay2.setId(2L);
        assertThat(proNhaMay1).isNotEqualTo(proNhaMay2);
        proNhaMay1.setId(null);
        assertThat(proNhaMay1).isNotEqualTo(proNhaMay2);
    }
}
