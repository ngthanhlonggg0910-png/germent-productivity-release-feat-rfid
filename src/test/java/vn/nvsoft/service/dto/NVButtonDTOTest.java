package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class NVButtonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NVButtonDTO.class);
        NVButtonDTO nVButtonDTO1 = new NVButtonDTO();
        nVButtonDTO1.setId(1L);
        NVButtonDTO nVButtonDTO2 = new NVButtonDTO();
        assertThat(nVButtonDTO1).isNotEqualTo(nVButtonDTO2);
        nVButtonDTO2.setId(nVButtonDTO1.getId());
        assertThat(nVButtonDTO1).isEqualTo(nVButtonDTO2);
        nVButtonDTO2.setId(2L);
        assertThat(nVButtonDTO1).isNotEqualTo(nVButtonDTO2);
        nVButtonDTO1.setId(null);
        assertThat(nVButtonDTO1).isNotEqualTo(nVButtonDTO2);
    }
}
