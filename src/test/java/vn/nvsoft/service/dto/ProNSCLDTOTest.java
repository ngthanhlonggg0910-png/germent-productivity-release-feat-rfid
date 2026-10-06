package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNSCLDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNSCLDTO.class);
        ProNSCLDTO proNSCLDTO1 = new ProNSCLDTO();
        proNSCLDTO1.setId(1L);
        ProNSCLDTO proNSCLDTO2 = new ProNSCLDTO();
        assertThat(proNSCLDTO1).isNotEqualTo(proNSCLDTO2);
        proNSCLDTO2.setId(proNSCLDTO1.getId());
        assertThat(proNSCLDTO1).isEqualTo(proNSCLDTO2);
        proNSCLDTO2.setId(2L);
        assertThat(proNSCLDTO1).isNotEqualTo(proNSCLDTO2);
        proNSCLDTO1.setId(null);
        assertThat(proNSCLDTO1).isNotEqualTo(proNSCLDTO2);
    }
}
