package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNhomCongDoanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNhomCongDoanDTO.class);
        ProNhomCongDoanDTO proNhomCongDoanDTO1 = new ProNhomCongDoanDTO();
        proNhomCongDoanDTO1.setId(1L);
        ProNhomCongDoanDTO proNhomCongDoanDTO2 = new ProNhomCongDoanDTO();
        assertThat(proNhomCongDoanDTO1).isNotEqualTo(proNhomCongDoanDTO2);
        proNhomCongDoanDTO2.setId(proNhomCongDoanDTO1.getId());
        assertThat(proNhomCongDoanDTO1).isEqualTo(proNhomCongDoanDTO2);
        proNhomCongDoanDTO2.setId(2L);
        assertThat(proNhomCongDoanDTO1).isNotEqualTo(proNhomCongDoanDTO2);
        proNhomCongDoanDTO1.setId(null);
        assertThat(proNhomCongDoanDTO1).isNotEqualTo(proNhomCongDoanDTO2);
    }
}
