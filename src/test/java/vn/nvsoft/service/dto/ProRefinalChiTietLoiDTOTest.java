package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalChiTietLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalChiTietLoiDTO.class);
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO1 = new ProRefinalChiTietLoiDTO();
        proRefinalChiTietLoiDTO1.setId(1L);
        ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO2 = new ProRefinalChiTietLoiDTO();
        assertThat(proRefinalChiTietLoiDTO1).isNotEqualTo(proRefinalChiTietLoiDTO2);
        proRefinalChiTietLoiDTO2.setId(proRefinalChiTietLoiDTO1.getId());
        assertThat(proRefinalChiTietLoiDTO1).isEqualTo(proRefinalChiTietLoiDTO2);
        proRefinalChiTietLoiDTO2.setId(2L);
        assertThat(proRefinalChiTietLoiDTO1).isNotEqualTo(proRefinalChiTietLoiDTO2);
        proRefinalChiTietLoiDTO1.setId(null);
        assertThat(proRefinalChiTietLoiDTO1).isNotEqualTo(proRefinalChiTietLoiDTO2);
    }
}
