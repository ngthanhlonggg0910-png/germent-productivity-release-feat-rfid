package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProKhachHangDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProKhachHangDTO.class);
        ProKhachHangDTO proKhachHangDTO1 = new ProKhachHangDTO();
        proKhachHangDTO1.setId(1L);
        ProKhachHangDTO proKhachHangDTO2 = new ProKhachHangDTO();
        assertThat(proKhachHangDTO1).isNotEqualTo(proKhachHangDTO2);
        proKhachHangDTO2.setId(proKhachHangDTO1.getId());
        assertThat(proKhachHangDTO1).isEqualTo(proKhachHangDTO2);
        proKhachHangDTO2.setId(2L);
        assertThat(proKhachHangDTO1).isNotEqualTo(proKhachHangDTO2);
        proKhachHangDTO1.setId(null);
        assertThat(proKhachHangDTO1).isNotEqualTo(proKhachHangDTO2);
    }
}
