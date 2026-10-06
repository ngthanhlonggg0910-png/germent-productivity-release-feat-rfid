package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysPermissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermission.class);
        SysPermission sysPermission1 = new SysPermission();
        sysPermission1.setId(1L);
        SysPermission sysPermission2 = new SysPermission();
        sysPermission2.setId(sysPermission1.getId());
        assertThat(sysPermission1).isEqualTo(sysPermission2);
        sysPermission2.setId(2L);
        assertThat(sysPermission1).isNotEqualTo(sysPermission2);
        sysPermission1.setId(null);
        assertThat(sysPermission1).isNotEqualTo(sysPermission2);
    }
}
