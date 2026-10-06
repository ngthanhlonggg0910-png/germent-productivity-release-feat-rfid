package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProTenMuaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProTenMua.class);
        ProTenMua proTenMua1 = new ProTenMua();
        proTenMua1.setId(1L);
        ProTenMua proTenMua2 = new ProTenMua();
        proTenMua2.setId(proTenMua1.getId());
        assertThat(proTenMua1).isEqualTo(proTenMua2);
        proTenMua2.setId(2L);
        assertThat(proTenMua1).isNotEqualTo(proTenMua2);
        proTenMua1.setId(null);
        assertThat(proTenMua1).isNotEqualTo(proTenMua2);
    }
}
