package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ButtonTrackingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ButtonTracking.class);
        ButtonTracking buttonTracking1 = new ButtonTracking();
        buttonTracking1.setId(1L);
        ButtonTracking buttonTracking2 = new ButtonTracking();
        buttonTracking2.setId(buttonTracking1.getId());
        assertThat(buttonTracking1).isEqualTo(buttonTracking2);
        buttonTracking2.setId(2L);
        assertThat(buttonTracking1).isNotEqualTo(buttonTracking2);
        buttonTracking1.setId(null);
        assertThat(buttonTracking1).isNotEqualTo(buttonTracking2);
    }
}
