package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDoDTO.class);
        ThongSoDoDTO thongSoDoDTO1 = new ThongSoDoDTO();
        thongSoDoDTO1.setId(1L);
        ThongSoDoDTO thongSoDoDTO2 = new ThongSoDoDTO();
        assertThat(thongSoDoDTO1).isNotEqualTo(thongSoDoDTO2);
        thongSoDoDTO2.setId(thongSoDoDTO1.getId());
        assertThat(thongSoDoDTO1).isEqualTo(thongSoDoDTO2);
        thongSoDoDTO2.setId(2L);
        assertThat(thongSoDoDTO1).isNotEqualTo(thongSoDoDTO2);
        thongSoDoDTO1.setId(null);
        assertThat(thongSoDoDTO1).isNotEqualTo(thongSoDoDTO2);
    }
}
