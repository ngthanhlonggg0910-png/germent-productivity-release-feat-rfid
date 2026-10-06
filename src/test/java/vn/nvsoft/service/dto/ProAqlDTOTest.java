package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProAqlDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProAqlDTO.class);
        ProAqlDTO proAqlDTO1 = new ProAqlDTO();
        proAqlDTO1.setId(1L);
        ProAqlDTO proAqlDTO2 = new ProAqlDTO();
        assertThat(proAqlDTO1).isNotEqualTo(proAqlDTO2);
        proAqlDTO2.setId(proAqlDTO1.getId());
        assertThat(proAqlDTO1).isEqualTo(proAqlDTO2);
        proAqlDTO2.setId(2L);
        assertThat(proAqlDTO1).isNotEqualTo(proAqlDTO2);
        proAqlDTO1.setId(null);
        assertThat(proAqlDTO1).isNotEqualTo(proAqlDTO2);
    }
}
