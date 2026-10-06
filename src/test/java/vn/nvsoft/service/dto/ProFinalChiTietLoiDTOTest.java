package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProFinalChiTietLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProFinalChiTietLoiDTO.class);
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO1 = new ProFinalChiTietLoiDTO();
        proFinalChiTietLoiDTO1.setId(1L);
        ProFinalChiTietLoiDTO proFinalChiTietLoiDTO2 = new ProFinalChiTietLoiDTO();
        assertThat(proFinalChiTietLoiDTO1).isNotEqualTo(proFinalChiTietLoiDTO2);
        proFinalChiTietLoiDTO2.setId(proFinalChiTietLoiDTO1.getId());
        assertThat(proFinalChiTietLoiDTO1).isEqualTo(proFinalChiTietLoiDTO2);
        proFinalChiTietLoiDTO2.setId(2L);
        assertThat(proFinalChiTietLoiDTO1).isNotEqualTo(proFinalChiTietLoiDTO2);
        proFinalChiTietLoiDTO1.setId(null);
        assertThat(proFinalChiTietLoiDTO1).isNotEqualTo(proFinalChiTietLoiDTO2);
    }
}
