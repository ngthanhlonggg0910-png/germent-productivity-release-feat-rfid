package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoThucThiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoThucThi.class);
        ThongSoThucThi thongSoThucThi1 = new ThongSoThucThi();
        thongSoThucThi1.setId(1L);
        ThongSoThucThi thongSoThucThi2 = new ThongSoThucThi();
        thongSoThucThi2.setId(thongSoThucThi1.getId());
        assertThat(thongSoThucThi1).isEqualTo(thongSoThucThi2);
        thongSoThucThi2.setId(2L);
        assertThat(thongSoThucThi1).isNotEqualTo(thongSoThucThi2);
        thongSoThucThi1.setId(null);
        assertThat(thongSoThucThi1).isNotEqualTo(thongSoThucThi2);
    }
}
