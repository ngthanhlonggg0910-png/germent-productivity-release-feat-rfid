package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProSanPhamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProSanPhamDTO.class);
        ProSanPhamDTO proSanPhamDTO1 = new ProSanPhamDTO();
        proSanPhamDTO1.setId(1L);
        ProSanPhamDTO proSanPhamDTO2 = new ProSanPhamDTO();
        assertThat(proSanPhamDTO1).isNotEqualTo(proSanPhamDTO2);
        proSanPhamDTO2.setId(proSanPhamDTO1.getId());
        assertThat(proSanPhamDTO1).isEqualTo(proSanPhamDTO2);
        proSanPhamDTO2.setId(2L);
        assertThat(proSanPhamDTO1).isNotEqualTo(proSanPhamDTO2);
        proSanPhamDTO1.setId(null);
        assertThat(proSanPhamDTO1).isNotEqualTo(proSanPhamDTO2);
    }
}
