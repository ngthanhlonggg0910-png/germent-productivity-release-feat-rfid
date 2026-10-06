package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProNhomCongDoanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProNhomCongDoan.class);
        ProNhomCongDoan proNhomCongDoan1 = new ProNhomCongDoan();
        proNhomCongDoan1.setId(1L);
        ProNhomCongDoan proNhomCongDoan2 = new ProNhomCongDoan();
        proNhomCongDoan2.setId(proNhomCongDoan1.getId());
        assertThat(proNhomCongDoan1).isEqualTo(proNhomCongDoan2);
        proNhomCongDoan2.setId(2L);
        assertThat(proNhomCongDoan1).isNotEqualTo(proNhomCongDoan2);
        proNhomCongDoan1.setId(null);
        assertThat(proNhomCongDoan1).isNotEqualTo(proNhomCongDoan2);
    }
}
