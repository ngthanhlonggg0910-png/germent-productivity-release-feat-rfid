package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SoLuongHangKiemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLuongHangKiemDTO.class);
        SoLuongHangKiemDTO soLuongHangKiemDTO1 = new SoLuongHangKiemDTO();
        soLuongHangKiemDTO1.setId(1L);
        SoLuongHangKiemDTO soLuongHangKiemDTO2 = new SoLuongHangKiemDTO();
        assertThat(soLuongHangKiemDTO1).isNotEqualTo(soLuongHangKiemDTO2);
        soLuongHangKiemDTO2.setId(soLuongHangKiemDTO1.getId());
        assertThat(soLuongHangKiemDTO1).isEqualTo(soLuongHangKiemDTO2);
        soLuongHangKiemDTO2.setId(2L);
        assertThat(soLuongHangKiemDTO1).isNotEqualTo(soLuongHangKiemDTO2);
        soLuongHangKiemDTO1.setId(null);
        assertThat(soLuongHangKiemDTO1).isNotEqualTo(soLuongHangKiemDTO2);
    }
}
