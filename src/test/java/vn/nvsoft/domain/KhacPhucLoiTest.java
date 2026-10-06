package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class KhacPhucLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhacPhucLoi.class);
        KhacPhucLoi khacPhucLoi1 = new KhacPhucLoi();
        khacPhucLoi1.setId(1L);
        KhacPhucLoi khacPhucLoi2 = new KhacPhucLoi();
        khacPhucLoi2.setId(khacPhucLoi1.getId());
        assertThat(khacPhucLoi1).isEqualTo(khacPhucLoi2);
        khacPhucLoi2.setId(2L);
        assertThat(khacPhucLoi1).isNotEqualTo(khacPhucLoi2);
        khacPhucLoi1.setId(null);
        assertThat(khacPhucLoi1).isNotEqualTo(khacPhucLoi2);
    }
}
