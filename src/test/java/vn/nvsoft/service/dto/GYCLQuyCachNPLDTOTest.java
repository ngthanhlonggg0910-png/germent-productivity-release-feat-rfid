package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GYCLQuyCachNPLDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GYCLQuyCachNPLDTO.class);
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO1 = new GYCLQuyCachNPLDTO();
        gYCLQuyCachNPLDTO1.setId(1L);
        GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO2 = new GYCLQuyCachNPLDTO();
        assertThat(gYCLQuyCachNPLDTO1).isNotEqualTo(gYCLQuyCachNPLDTO2);
        gYCLQuyCachNPLDTO2.setId(gYCLQuyCachNPLDTO1.getId());
        assertThat(gYCLQuyCachNPLDTO1).isEqualTo(gYCLQuyCachNPLDTO2);
        gYCLQuyCachNPLDTO2.setId(2L);
        assertThat(gYCLQuyCachNPLDTO1).isNotEqualTo(gYCLQuyCachNPLDTO2);
        gYCLQuyCachNPLDTO1.setId(null);
        assertThat(gYCLQuyCachNPLDTO1).isNotEqualTo(gYCLQuyCachNPLDTO2);
    }
}
