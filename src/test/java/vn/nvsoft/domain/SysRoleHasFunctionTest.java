package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysRoleHasFunctionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleHasFunction.class);
        SysRoleHasFunction sysRoleHasFunction1 = new SysRoleHasFunction();
        sysRoleHasFunction1.setId(1L);
        SysRoleHasFunction sysRoleHasFunction2 = new SysRoleHasFunction();
        sysRoleHasFunction2.setId(sysRoleHasFunction1.getId());
        assertThat(sysRoleHasFunction1).isEqualTo(sysRoleHasFunction2);
        sysRoleHasFunction2.setId(2L);
        assertThat(sysRoleHasFunction1).isNotEqualTo(sysRoleHasFunction2);
        sysRoleHasFunction1.setId(null);
        assertThat(sysRoleHasFunction1).isNotEqualTo(sysRoleHasFunction2);
    }
}
