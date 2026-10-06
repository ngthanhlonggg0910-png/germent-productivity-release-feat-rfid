package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProKhachHangTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProKhachHang.class);
        ProKhachHang proKhachHang1 = new ProKhachHang();
        proKhachHang1.setId(1L);
        ProKhachHang proKhachHang2 = new ProKhachHang();
        proKhachHang2.setId(proKhachHang1.getId());
        assertThat(proKhachHang1).isEqualTo(proKhachHang2);
        proKhachHang2.setId(2L);
        assertThat(proKhachHang1).isNotEqualTo(proKhachHang2);
        proKhachHang1.setId(null);
        assertThat(proKhachHang1).isNotEqualTo(proKhachHang2);
    }
}
