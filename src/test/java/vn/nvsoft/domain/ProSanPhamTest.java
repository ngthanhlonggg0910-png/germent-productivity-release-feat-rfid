package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProSanPhamTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProSanPham.class);
        ProSanPham proSanPham1 = new ProSanPham();
        proSanPham1.setId(1L);
        ProSanPham proSanPham2 = new ProSanPham();
        proSanPham2.setId(proSanPham1.getId());
        assertThat(proSanPham1).isEqualTo(proSanPham2);
        proSanPham2.setId(2L);
        assertThat(proSanPham1).isNotEqualTo(proSanPham2);
        proSanPham1.setId(null);
        assertThat(proSanPham1).isNotEqualTo(proSanPham2);
    }
}
