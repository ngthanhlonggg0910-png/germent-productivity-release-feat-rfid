package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GYCLQuyCachNPLTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GYCLQuyCachNPL.class);
        GYCLQuyCachNPL gYCLQuyCachNPL1 = new GYCLQuyCachNPL();
        gYCLQuyCachNPL1.setId(1L);
        GYCLQuyCachNPL gYCLQuyCachNPL2 = new GYCLQuyCachNPL();
        gYCLQuyCachNPL2.setId(gYCLQuyCachNPL1.getId());
        assertThat(gYCLQuyCachNPL1).isEqualTo(gYCLQuyCachNPL2);
        gYCLQuyCachNPL2.setId(2L);
        assertThat(gYCLQuyCachNPL1).isNotEqualTo(gYCLQuyCachNPL2);
        gYCLQuyCachNPL1.setId(null);
        assertThat(gYCLQuyCachNPL1).isNotEqualTo(gYCLQuyCachNPL2);
    }
}
