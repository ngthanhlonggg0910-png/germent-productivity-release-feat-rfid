package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class TrackingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrackingDTO.class);
        TrackingDTO trackingDTO1 = new TrackingDTO();
        trackingDTO1.setId(1L);
        TrackingDTO trackingDTO2 = new TrackingDTO();
        assertThat(trackingDTO1).isNotEqualTo(trackingDTO2);
        trackingDTO2.setId(trackingDTO1.getId());
        assertThat(trackingDTO1).isEqualTo(trackingDTO2);
        trackingDTO2.setId(2L);
        assertThat(trackingDTO1).isNotEqualTo(trackingDTO2);
        trackingDTO1.setId(null);
        assertThat(trackingDTO1).isNotEqualTo(trackingDTO2);
    }
}
