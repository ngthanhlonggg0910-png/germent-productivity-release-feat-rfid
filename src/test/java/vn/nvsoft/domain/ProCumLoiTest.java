package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCumLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCumLoi.class);
        ProCumLoi proCumLoi1 = new ProCumLoi();
        proCumLoi1.setId(1L);
        ProCumLoi proCumLoi2 = new ProCumLoi();
        proCumLoi2.setId(proCumLoi1.getId());
        assertThat(proCumLoi1).isEqualTo(proCumLoi2);
        proCumLoi2.setId(2L);
        assertThat(proCumLoi1).isNotEqualTo(proCumLoi2);
        proCumLoi1.setId(null);
        assertThat(proCumLoi1).isNotEqualTo(proCumLoi2);
    }
}
