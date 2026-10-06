package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class NVButtonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NVButton.class);
        NVButton nVButton1 = new NVButton();
        nVButton1.setId(1L);
        NVButton nVButton2 = new NVButton();
        nVButton2.setId(nVButton1.getId());
        assertThat(nVButton1).isEqualTo(nVButton2);
        nVButton2.setId(2L);
        assertThat(nVButton1).isNotEqualTo(nVButton2);
        nVButton1.setId(null);
        assertThat(nVButton1).isNotEqualTo(nVButton2);
    }
}
