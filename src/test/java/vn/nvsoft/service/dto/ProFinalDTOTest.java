package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProFinalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProFinalDTO.class);
        ProFinalDTO proFinalDTO1 = new ProFinalDTO();
        proFinalDTO1.setId(1L);
        ProFinalDTO proFinalDTO2 = new ProFinalDTO();
        assertThat(proFinalDTO1).isNotEqualTo(proFinalDTO2);
        proFinalDTO2.setId(proFinalDTO1.getId());
        assertThat(proFinalDTO1).isEqualTo(proFinalDTO2);
        proFinalDTO2.setId(2L);
        assertThat(proFinalDTO1).isNotEqualTo(proFinalDTO2);
        proFinalDTO1.setId(null);
        assertThat(proFinalDTO1).isNotEqualTo(proFinalDTO2);
    }
}
