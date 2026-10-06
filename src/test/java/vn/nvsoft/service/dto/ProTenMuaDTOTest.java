package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProTenMuaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProTenMuaDTO.class);
        ProTenMuaDTO proTenMuaDTO1 = new ProTenMuaDTO();
        proTenMuaDTO1.setId(1L);
        ProTenMuaDTO proTenMuaDTO2 = new ProTenMuaDTO();
        assertThat(proTenMuaDTO1).isNotEqualTo(proTenMuaDTO2);
        proTenMuaDTO2.setId(proTenMuaDTO1.getId());
        assertThat(proTenMuaDTO1).isEqualTo(proTenMuaDTO2);
        proTenMuaDTO2.setId(2L);
        assertThat(proTenMuaDTO1).isNotEqualTo(proTenMuaDTO2);
        proTenMuaDTO1.setId(null);
        assertThat(proTenMuaDTO1).isNotEqualTo(proTenMuaDTO2);
    }
}
