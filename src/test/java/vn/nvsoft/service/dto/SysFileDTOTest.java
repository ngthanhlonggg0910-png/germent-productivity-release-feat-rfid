package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysFileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFileDTO.class);
        SysFileDTO sysFileDTO1 = new SysFileDTO();
        sysFileDTO1.setId(1L);
        SysFileDTO sysFileDTO2 = new SysFileDTO();
        assertThat(sysFileDTO1).isNotEqualTo(sysFileDTO2);
        sysFileDTO2.setId(sysFileDTO1.getId());
        assertThat(sysFileDTO1).isEqualTo(sysFileDTO2);
        sysFileDTO2.setId(2L);
        assertThat(sysFileDTO1).isNotEqualTo(sysFileDTO2);
        sysFileDTO1.setId(null);
        assertThat(sysFileDTO1).isNotEqualTo(sysFileDTO2);
    }
}
