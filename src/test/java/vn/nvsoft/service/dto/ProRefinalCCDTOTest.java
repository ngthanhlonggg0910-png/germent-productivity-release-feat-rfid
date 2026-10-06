package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalCCDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalCCDTO.class);
        ProRefinalCCDTO proRefinalCCDTO1 = new ProRefinalCCDTO();
        proRefinalCCDTO1.setId(1L);
        ProRefinalCCDTO proRefinalCCDTO2 = new ProRefinalCCDTO();
        assertThat(proRefinalCCDTO1).isNotEqualTo(proRefinalCCDTO2);
        proRefinalCCDTO2.setId(proRefinalCCDTO1.getId());
        assertThat(proRefinalCCDTO1).isEqualTo(proRefinalCCDTO2);
        proRefinalCCDTO2.setId(2L);
        assertThat(proRefinalCCDTO1).isNotEqualTo(proRefinalCCDTO2);
        proRefinalCCDTO1.setId(null);
        assertThat(proRefinalCCDTO1).isNotEqualTo(proRefinalCCDTO2);
    }
}
