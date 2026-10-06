package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoSPDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDoSPDTO.class);
        ThongSoDoSPDTO thongSoDoSPDTO1 = new ThongSoDoSPDTO();
        thongSoDoSPDTO1.setId(1L);
        ThongSoDoSPDTO thongSoDoSPDTO2 = new ThongSoDoSPDTO();
        assertThat(thongSoDoSPDTO1).isNotEqualTo(thongSoDoSPDTO2);
        thongSoDoSPDTO2.setId(thongSoDoSPDTO1.getId());
        assertThat(thongSoDoSPDTO1).isEqualTo(thongSoDoSPDTO2);
        thongSoDoSPDTO2.setId(2L);
        assertThat(thongSoDoSPDTO1).isNotEqualTo(thongSoDoSPDTO2);
        thongSoDoSPDTO1.setId(null);
        assertThat(thongSoDoSPDTO1).isNotEqualTo(thongSoDoSPDTO2);
    }
}
