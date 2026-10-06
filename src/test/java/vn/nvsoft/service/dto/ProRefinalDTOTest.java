package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalDTO.class);
        ProRefinalDTO proRefinalDTO1 = new ProRefinalDTO();
        proRefinalDTO1.setId(1L);
        ProRefinalDTO proRefinalDTO2 = new ProRefinalDTO();
        assertThat(proRefinalDTO1).isNotEqualTo(proRefinalDTO2);
        proRefinalDTO2.setId(proRefinalDTO1.getId());
        assertThat(proRefinalDTO1).isEqualTo(proRefinalDTO2);
        proRefinalDTO2.setId(2L);
        assertThat(proRefinalDTO1).isNotEqualTo(proRefinalDTO2);
        proRefinalDTO1.setId(null);
        assertThat(proRefinalDTO1).isNotEqualTo(proRefinalDTO2);
    }
}
