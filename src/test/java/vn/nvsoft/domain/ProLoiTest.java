package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProLoi.class);
        ProLoi proLoi1 = new ProLoi();
        proLoi1.setId(1L);
        ProLoi proLoi2 = new ProLoi();
        proLoi2.setId(proLoi1.getId());
        assertThat(proLoi1).isEqualTo(proLoi2);
        proLoi2.setId(2L);
        assertThat(proLoi1).isNotEqualTo(proLoi2);
        proLoi1.setId(null);
        assertThat(proLoi1).isNotEqualTo(proLoi2);
    }
}
