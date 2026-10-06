package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoSPSizeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDoSPSize.class);
        ThongSoDoSPSize thongSoDoSPSize1 = new ThongSoDoSPSize();
        thongSoDoSPSize1.setId(1L);
        ThongSoDoSPSize thongSoDoSPSize2 = new ThongSoDoSPSize();
        thongSoDoSPSize2.setId(thongSoDoSPSize1.getId());
        assertThat(thongSoDoSPSize1).isEqualTo(thongSoDoSPSize2);
        thongSoDoSPSize2.setId(2L);
        assertThat(thongSoDoSPSize1).isNotEqualTo(thongSoDoSPSize2);
        thongSoDoSPSize1.setId(null);
        assertThat(thongSoDoSPSize1).isNotEqualTo(thongSoDoSPSize2);
    }
}
