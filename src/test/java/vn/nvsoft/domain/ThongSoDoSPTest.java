package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoSPTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDoSP.class);
        ThongSoDoSP thongSoDoSP1 = new ThongSoDoSP();
        thongSoDoSP1.setId(1L);
        ThongSoDoSP thongSoDoSP2 = new ThongSoDoSP();
        thongSoDoSP2.setId(thongSoDoSP1.getId());
        assertThat(thongSoDoSP1).isEqualTo(thongSoDoSP2);
        thongSoDoSP2.setId(2L);
        assertThat(thongSoDoSP1).isNotEqualTo(thongSoDoSP2);
        thongSoDoSP1.setId(null);
        assertThat(thongSoDoSP1).isNotEqualTo(thongSoDoSP2);
    }
}
