package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCheckPointDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCheckPointDTO.class);
        ProCheckPointDTO proCheckPointDTO1 = new ProCheckPointDTO();
        proCheckPointDTO1.setId(1L);
        ProCheckPointDTO proCheckPointDTO2 = new ProCheckPointDTO();
        assertThat(proCheckPointDTO1).isNotEqualTo(proCheckPointDTO2);
        proCheckPointDTO2.setId(proCheckPointDTO1.getId());
        assertThat(proCheckPointDTO1).isEqualTo(proCheckPointDTO2);
        proCheckPointDTO2.setId(2L);
        assertThat(proCheckPointDTO1).isNotEqualTo(proCheckPointDTO2);
        proCheckPointDTO1.setId(null);
        assertThat(proCheckPointDTO1).isNotEqualTo(proCheckPointDTO2);
    }
}
