package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysFunctionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFunction.class);
        SysFunction sysFunction1 = new SysFunction();
        sysFunction1.setId(1L);
        SysFunction sysFunction2 = new SysFunction();
        sysFunction2.setId(sysFunction1.getId());
        assertThat(sysFunction1).isEqualTo(sysFunction2);
        sysFunction2.setId(2L);
        assertThat(sysFunction1).isNotEqualTo(sysFunction2);
        sysFunction1.setId(null);
        assertThat(sysFunction1).isNotEqualTo(sysFunction2);
    }
}
