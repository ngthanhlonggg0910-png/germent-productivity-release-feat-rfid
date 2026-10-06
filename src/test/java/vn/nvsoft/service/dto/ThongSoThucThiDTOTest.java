package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoThucThiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoThucThiDTO.class);
        ThongSoThucThiDTO thongSoThucThiDTO1 = new ThongSoThucThiDTO();
        thongSoThucThiDTO1.setId(1L);
        ThongSoThucThiDTO thongSoThucThiDTO2 = new ThongSoThucThiDTO();
        assertThat(thongSoThucThiDTO1).isNotEqualTo(thongSoThucThiDTO2);
        thongSoThucThiDTO2.setId(thongSoThucThiDTO1.getId());
        assertThat(thongSoThucThiDTO1).isEqualTo(thongSoThucThiDTO2);
        thongSoThucThiDTO2.setId(2L);
        assertThat(thongSoThucThiDTO1).isNotEqualTo(thongSoThucThiDTO2);
        thongSoThucThiDTO1.setId(null);
        assertThat(thongSoThucThiDTO1).isNotEqualTo(thongSoThucThiDTO2);
    }
}
