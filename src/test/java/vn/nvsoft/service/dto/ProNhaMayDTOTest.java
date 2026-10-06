package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNhaMayDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNhaMayDTO.class);
        ProNhaMayDTO proNhaMayDTO1 = new ProNhaMayDTO();
        proNhaMayDTO1.setId(1L);
        ProNhaMayDTO proNhaMayDTO2 = new ProNhaMayDTO();
        assertThat(proNhaMayDTO1).isNotEqualTo(proNhaMayDTO2);
        proNhaMayDTO2.setId(proNhaMayDTO1.getId());
        assertThat(proNhaMayDTO1).isEqualTo(proNhaMayDTO2);
        proNhaMayDTO2.setId(2L);
        assertThat(proNhaMayDTO1).isNotEqualTo(proNhaMayDTO2);
        proNhaMayDTO1.setId(null);
        assertThat(proNhaMayDTO1).isNotEqualTo(proNhaMayDTO2);
    }
}
