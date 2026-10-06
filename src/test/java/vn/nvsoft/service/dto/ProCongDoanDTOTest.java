package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCongDoanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCongDoanDTO.class);
        ProCongDoanDTO proCongDoanDTO1 = new ProCongDoanDTO();
        proCongDoanDTO1.setId(1L);
        ProCongDoanDTO proCongDoanDTO2 = new ProCongDoanDTO();
        assertThat(proCongDoanDTO1).isNotEqualTo(proCongDoanDTO2);
        proCongDoanDTO2.setId(proCongDoanDTO1.getId());
        assertThat(proCongDoanDTO1).isEqualTo(proCongDoanDTO2);
        proCongDoanDTO2.setId(2L);
        assertThat(proCongDoanDTO1).isNotEqualTo(proCongDoanDTO2);
        proCongDoanDTO1.setId(null);
        assertThat(proCongDoanDTO1).isNotEqualTo(proCongDoanDTO2);
    }
}
