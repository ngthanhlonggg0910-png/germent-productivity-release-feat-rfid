package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProChungLoaiSanPhamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProChungLoaiSanPham.class);
        ProChungLoaiSanPham proChungLoaiSanPham1 = new ProChungLoaiSanPham();
        proChungLoaiSanPham1.setId(1L);
        ProChungLoaiSanPham proChungLoaiSanPham2 = new ProChungLoaiSanPham();
        proChungLoaiSanPham2.setId(proChungLoaiSanPham1.getId());
        assertThat(proChungLoaiSanPham1).isEqualTo(proChungLoaiSanPham2);
        proChungLoaiSanPham2.setId(2L);
        assertThat(proChungLoaiSanPham1).isNotEqualTo(proChungLoaiSanPham2);
        proChungLoaiSanPham1.setId(null);
        assertThat(proChungLoaiSanPham1).isNotEqualTo(proChungLoaiSanPham2);
    }
}
