package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoSPSizeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDoSPSizeDTO.class);
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO1 = new ThongSoDoSPSizeDTO();
        thongSoDoSPSizeDTO1.setId(1L);
        ThongSoDoSPSizeDTO thongSoDoSPSizeDTO2 = new ThongSoDoSPSizeDTO();
        assertThat(thongSoDoSPSizeDTO1).isNotEqualTo(thongSoDoSPSizeDTO2);
        thongSoDoSPSizeDTO2.setId(thongSoDoSPSizeDTO1.getId());
        assertThat(thongSoDoSPSizeDTO1).isEqualTo(thongSoDoSPSizeDTO2);
        thongSoDoSPSizeDTO2.setId(2L);
        assertThat(thongSoDoSPSizeDTO1).isNotEqualTo(thongSoDoSPSizeDTO2);
        thongSoDoSPSizeDTO1.setId(null);
        assertThat(thongSoDoSPSizeDTO1).isNotEqualTo(thongSoDoSPSizeDTO2);
    }
}
