package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProChungLoaiSanPhamDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProChungLoaiSanPhamDTO.class);
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO1 = new ProChungLoaiSanPhamDTO();
        proChungLoaiSanPhamDTO1.setId(1L);
        ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO2 = new ProChungLoaiSanPhamDTO();
        assertThat(proChungLoaiSanPhamDTO1).isNotEqualTo(proChungLoaiSanPhamDTO2);
        proChungLoaiSanPhamDTO2.setId(proChungLoaiSanPhamDTO1.getId());
        assertThat(proChungLoaiSanPhamDTO1).isEqualTo(proChungLoaiSanPhamDTO2);
        proChungLoaiSanPhamDTO2.setId(2L);
        assertThat(proChungLoaiSanPhamDTO1).isNotEqualTo(proChungLoaiSanPhamDTO2);
        proChungLoaiSanPhamDTO1.setId(null);
        assertThat(proChungLoaiSanPhamDTO1).isNotEqualTo(proChungLoaiSanPhamDTO2);
    }
}
