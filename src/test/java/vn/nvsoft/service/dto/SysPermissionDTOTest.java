package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysPermissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysPermissionDTO.class);
        SysPermissionDTO sysPermissionDTO1 = new SysPermissionDTO();
        sysPermissionDTO1.setId(1L);
        SysPermissionDTO sysPermissionDTO2 = new SysPermissionDTO();
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
        sysPermissionDTO2.setId(sysPermissionDTO1.getId());
        assertThat(sysPermissionDTO1).isEqualTo(sysPermissionDTO2);
        sysPermissionDTO2.setId(2L);
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
        sysPermissionDTO1.setId(null);
        assertThat(sysPermissionDTO1).isNotEqualTo(sysPermissionDTO2);
    }
}
