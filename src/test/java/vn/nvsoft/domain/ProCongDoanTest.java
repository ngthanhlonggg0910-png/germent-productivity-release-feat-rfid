package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCongDoanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCongDoan.class);
        ProCongDoan proCongDoan1 = new ProCongDoan();
        proCongDoan1.setId(1L);
        ProCongDoan proCongDoan2 = new ProCongDoan();
        proCongDoan2.setId(proCongDoan1.getId());
        assertThat(proCongDoan1).isEqualTo(proCongDoan2);
        proCongDoan2.setId(2L);
        assertThat(proCongDoan1).isNotEqualTo(proCongDoan2);
        proCongDoan1.setId(null);
        assertThat(proCongDoan1).isNotEqualTo(proCongDoan2);
    }
}
