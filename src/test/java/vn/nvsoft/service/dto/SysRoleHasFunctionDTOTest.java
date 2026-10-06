package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysRoleHasFunctionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRoleHasFunctionDTO.class);
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO1 = new SysRoleHasFunctionDTO();
        sysRoleHasFunctionDTO1.setId(1L);
        SysRoleHasFunctionDTO sysRoleHasFunctionDTO2 = new SysRoleHasFunctionDTO();
        assertThat(sysRoleHasFunctionDTO1).isNotEqualTo(sysRoleHasFunctionDTO2);
        sysRoleHasFunctionDTO2.setId(sysRoleHasFunctionDTO1.getId());
        assertThat(sysRoleHasFunctionDTO1).isEqualTo(sysRoleHasFunctionDTO2);
        sysRoleHasFunctionDTO2.setId(2L);
        assertThat(sysRoleHasFunctionDTO1).isNotEqualTo(sysRoleHasFunctionDTO2);
        sysRoleHasFunctionDTO1.setId(null);
        assertThat(sysRoleHasFunctionDTO1).isNotEqualTo(sysRoleHasFunctionDTO2);
    }
}
