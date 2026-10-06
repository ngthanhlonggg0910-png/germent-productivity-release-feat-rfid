package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DoAmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoAm.class);
        DoAm doAm1 = new DoAm();
        doAm1.setId(1L);
        DoAm doAm2 = new DoAm();
        doAm2.setId(doAm1.getId());
        assertThat(doAm1).isEqualTo(doAm2);
        doAm2.setId(2L);
        assertThat(doAm1).isNotEqualTo(doAm2);
        doAm1.setId(null);
        assertThat(doAm1).isNotEqualTo(doAm2);
    }
}
