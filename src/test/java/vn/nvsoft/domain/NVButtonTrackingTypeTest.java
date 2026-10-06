package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class NVButtonTrackingTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NVButtonTrackingType.class);
        NVButtonTrackingType nVButtonTrackingType1 = new NVButtonTrackingType();
        nVButtonTrackingType1.setId(1L);
        NVButtonTrackingType nVButtonTrackingType2 = new NVButtonTrackingType();
        nVButtonTrackingType2.setId(nVButtonTrackingType1.getId());
        assertThat(nVButtonTrackingType1).isEqualTo(nVButtonTrackingType2);
        nVButtonTrackingType2.setId(2L);
        assertThat(nVButtonTrackingType1).isNotEqualTo(nVButtonTrackingType2);
        nVButtonTrackingType1.setId(null);
        assertThat(nVButtonTrackingType1).isNotEqualTo(nVButtonTrackingType2);
    }
}
