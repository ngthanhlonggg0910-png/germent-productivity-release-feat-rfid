package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class QuyCachNPLTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyCachNPL.class);
        QuyCachNPL quyCachNPL1 = new QuyCachNPL();
        quyCachNPL1.setId(1L);
        QuyCachNPL quyCachNPL2 = new QuyCachNPL();
        quyCachNPL2.setId(quyCachNPL1.getId());
        assertThat(quyCachNPL1).isEqualTo(quyCachNPL2);
        quyCachNPL2.setId(2L);
        assertThat(quyCachNPL1).isNotEqualTo(quyCachNPL2);
        quyCachNPL1.setId(null);
        assertThat(quyCachNPL1).isNotEqualTo(quyCachNPL2);
    }
}
