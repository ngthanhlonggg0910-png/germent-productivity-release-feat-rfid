package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProRefinalCCChiTietLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProRefinalCCChiTietLoiDTO.class);
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO1 = new ProRefinalCCChiTietLoiDTO();
        proRefinalCCChiTietLoiDTO1.setId(1L);
        ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO2 = new ProRefinalCCChiTietLoiDTO();
        assertThat(proRefinalCCChiTietLoiDTO1).isNotEqualTo(proRefinalCCChiTietLoiDTO2);
        proRefinalCCChiTietLoiDTO2.setId(proRefinalCCChiTietLoiDTO1.getId());
        assertThat(proRefinalCCChiTietLoiDTO1).isEqualTo(proRefinalCCChiTietLoiDTO2);
        proRefinalCCChiTietLoiDTO2.setId(2L);
        assertThat(proRefinalCCChiTietLoiDTO1).isNotEqualTo(proRefinalCCChiTietLoiDTO2);
        proRefinalCCChiTietLoiDTO1.setId(null);
        assertThat(proRefinalCCChiTietLoiDTO1).isNotEqualTo(proRefinalCCChiTietLoiDTO2);
    }
}
