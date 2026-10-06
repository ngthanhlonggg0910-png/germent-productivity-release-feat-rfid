package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProTaiKhoanCheckPointDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProTaiKhoanCheckPointDTO.class);
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO1 = new ProTaiKhoanCheckPointDTO();
        proTaiKhoanCheckPointDTO1.setId(1L);
        ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO2 = new ProTaiKhoanCheckPointDTO();
        assertThat(proTaiKhoanCheckPointDTO1).isNotEqualTo(proTaiKhoanCheckPointDTO2);
        proTaiKhoanCheckPointDTO2.setId(proTaiKhoanCheckPointDTO1.getId());
        assertThat(proTaiKhoanCheckPointDTO1).isEqualTo(proTaiKhoanCheckPointDTO2);
        proTaiKhoanCheckPointDTO2.setId(2L);
        assertThat(proTaiKhoanCheckPointDTO1).isNotEqualTo(proTaiKhoanCheckPointDTO2);
        proTaiKhoanCheckPointDTO1.setId(null);
        assertThat(proTaiKhoanCheckPointDTO1).isNotEqualTo(proTaiKhoanCheckPointDTO2);
    }
}
