package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class QuyCachNPLDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyCachNPLDTO.class);
        QuyCachNPLDTO quyCachNPLDTO1 = new QuyCachNPLDTO();
        quyCachNPLDTO1.setId(1L);
        QuyCachNPLDTO quyCachNPLDTO2 = new QuyCachNPLDTO();
        assertThat(quyCachNPLDTO1).isNotEqualTo(quyCachNPLDTO2);
        quyCachNPLDTO2.setId(quyCachNPLDTO1.getId());
        assertThat(quyCachNPLDTO1).isEqualTo(quyCachNPLDTO2);
        quyCachNPLDTO2.setId(2L);
        assertThat(quyCachNPLDTO1).isNotEqualTo(quyCachNPLDTO2);
        quyCachNPLDTO1.setId(null);
        assertThat(quyCachNPLDTO1).isNotEqualTo(quyCachNPLDTO2);
    }
}
