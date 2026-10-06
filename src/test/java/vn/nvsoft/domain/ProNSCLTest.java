package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNSCLTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNSCL.class);
        ProNSCL proNSCL1 = new ProNSCL();
        proNSCL1.setId(1L);
        ProNSCL proNSCL2 = new ProNSCL();
        proNSCL2.setId(proNSCL1.getId());
        assertThat(proNSCL1).isEqualTo(proNSCL2);
        proNSCL2.setId(2L);
        assertThat(proNSCL1).isNotEqualTo(proNSCL2);
        proNSCL1.setId(null);
        assertThat(proNSCL1).isNotEqualTo(proNSCL2);
    }
}
