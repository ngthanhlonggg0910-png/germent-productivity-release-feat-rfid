package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysGroupModuleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysGroupModuleDTO.class);
        SysGroupModuleDTO sysGroupModuleDTO1 = new SysGroupModuleDTO();
        sysGroupModuleDTO1.setId(1L);
        SysGroupModuleDTO sysGroupModuleDTO2 = new SysGroupModuleDTO();
        assertThat(sysGroupModuleDTO1).isNotEqualTo(sysGroupModuleDTO2);
        sysGroupModuleDTO2.setId(sysGroupModuleDTO1.getId());
        assertThat(sysGroupModuleDTO1).isEqualTo(sysGroupModuleDTO2);
        sysGroupModuleDTO2.setId(2L);
        assertThat(sysGroupModuleDTO1).isNotEqualTo(sysGroupModuleDTO2);
        sysGroupModuleDTO1.setId(null);
        assertThat(sysGroupModuleDTO1).isNotEqualTo(sysGroupModuleDTO2);
    }
}
