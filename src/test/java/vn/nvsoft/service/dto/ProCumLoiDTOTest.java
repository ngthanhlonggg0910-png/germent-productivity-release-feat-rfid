package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCumLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCumLoiDTO.class);
        ProCumLoiDTO proCumLoiDTO1 = new ProCumLoiDTO();
        proCumLoiDTO1.setId(1L);
        ProCumLoiDTO proCumLoiDTO2 = new ProCumLoiDTO();
        assertThat(proCumLoiDTO1).isNotEqualTo(proCumLoiDTO2);
        proCumLoiDTO2.setId(proCumLoiDTO1.getId());
        assertThat(proCumLoiDTO1).isEqualTo(proCumLoiDTO2);
        proCumLoiDTO2.setId(2L);
        assertThat(proCumLoiDTO1).isNotEqualTo(proCumLoiDTO2);
        proCumLoiDTO1.setId(null);
        assertThat(proCumLoiDTO1).isNotEqualTo(proCumLoiDTO2);
    }
}
