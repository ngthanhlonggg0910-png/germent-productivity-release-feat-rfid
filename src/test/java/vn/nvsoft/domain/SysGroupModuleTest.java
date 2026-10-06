package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysGroupModuleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysGroupModule.class);
        SysGroupModule sysGroupModule1 = new SysGroupModule();
        sysGroupModule1.setId(1L);
        SysGroupModule sysGroupModule2 = new SysGroupModule();
        sysGroupModule2.setId(sysGroupModule1.getId());
        assertThat(sysGroupModule1).isEqualTo(sysGroupModule2);
        sysGroupModule2.setId(2L);
        assertThat(sysGroupModule1).isNotEqualTo(sysGroupModule2);
        sysGroupModule1.setId(null);
        assertThat(sysGroupModule1).isNotEqualTo(sysGroupModule2);
    }
}
