package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCheckPointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCheckPoint.class);
        ProCheckPoint proCheckPoint1 = new ProCheckPoint();
        proCheckPoint1.setId(1L);
        ProCheckPoint proCheckPoint2 = new ProCheckPoint();
        proCheckPoint2.setId(proCheckPoint1.getId());
        assertThat(proCheckPoint1).isEqualTo(proCheckPoint2);
        proCheckPoint2.setId(2L);
        assertThat(proCheckPoint1).isNotEqualTo(proCheckPoint2);
        proCheckPoint1.setId(null);
        assertThat(proCheckPoint1).isNotEqualTo(proCheckPoint2);
    }
}
