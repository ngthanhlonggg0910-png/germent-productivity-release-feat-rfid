package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNSCLChiTietLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNSCLChiTietLoiDTO.class);
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO1 = new ProNSCLChiTietLoiDTO();
        proNSCLChiTietLoiDTO1.setId(1L);
        ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO2 = new ProNSCLChiTietLoiDTO();
        assertThat(proNSCLChiTietLoiDTO1).isNotEqualTo(proNSCLChiTietLoiDTO2);
        proNSCLChiTietLoiDTO2.setId(proNSCLChiTietLoiDTO1.getId());
        assertThat(proNSCLChiTietLoiDTO1).isEqualTo(proNSCLChiTietLoiDTO2);
        proNSCLChiTietLoiDTO2.setId(2L);
        assertThat(proNSCLChiTietLoiDTO1).isNotEqualTo(proNSCLChiTietLoiDTO2);
        proNSCLChiTietLoiDTO1.setId(null);
        assertThat(proNSCLChiTietLoiDTO1).isNotEqualTo(proNSCLChiTietLoiDTO2);
    }
}
