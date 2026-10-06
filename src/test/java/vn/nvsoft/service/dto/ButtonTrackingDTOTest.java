package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ButtonTrackingDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ButtonTrackingDTO.class);
        ButtonTrackingDTO buttonTrackingDTO1 = new ButtonTrackingDTO();
        buttonTrackingDTO1.setId(1L);
        ButtonTrackingDTO buttonTrackingDTO2 = new ButtonTrackingDTO();
        assertThat(buttonTrackingDTO1).isNotEqualTo(buttonTrackingDTO2);
        buttonTrackingDTO2.setId(buttonTrackingDTO1.getId());
        assertThat(buttonTrackingDTO1).isEqualTo(buttonTrackingDTO2);
        buttonTrackingDTO2.setId(2L);
        assertThat(buttonTrackingDTO1).isNotEqualTo(buttonTrackingDTO2);
        buttonTrackingDTO1.setId(null);
        assertThat(buttonTrackingDTO1).isNotEqualTo(buttonTrackingDTO2);
    }
}
