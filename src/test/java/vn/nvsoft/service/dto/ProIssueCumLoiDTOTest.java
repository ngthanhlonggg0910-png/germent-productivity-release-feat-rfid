package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueCumLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssueCumLoiDTO.class);
        ProIssueCumLoiDTO proIssueCumLoiDTO1 = new ProIssueCumLoiDTO();
        proIssueCumLoiDTO1.setId(1L);
        ProIssueCumLoiDTO proIssueCumLoiDTO2 = new ProIssueCumLoiDTO();
        assertThat(proIssueCumLoiDTO1).isNotEqualTo(proIssueCumLoiDTO2);
        proIssueCumLoiDTO2.setId(proIssueCumLoiDTO1.getId());
        assertThat(proIssueCumLoiDTO1).isEqualTo(proIssueCumLoiDTO2);
        proIssueCumLoiDTO2.setId(2L);
        assertThat(proIssueCumLoiDTO1).isNotEqualTo(proIssueCumLoiDTO2);
        proIssueCumLoiDTO1.setId(null);
        assertThat(proIssueCumLoiDTO1).isNotEqualTo(proIssueCumLoiDTO2);
    }
}
