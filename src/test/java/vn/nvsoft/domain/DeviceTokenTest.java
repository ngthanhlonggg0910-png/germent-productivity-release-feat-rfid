package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DeviceTokenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceToken.class);
        DeviceToken deviceToken1 = new DeviceToken();
        deviceToken1.setId(1L);
        DeviceToken deviceToken2 = new DeviceToken();
        deviceToken2.setId(deviceToken1.getId());
        assertThat(deviceToken1).isEqualTo(deviceToken2);
        deviceToken2.setId(2L);
        assertThat(deviceToken1).isNotEqualTo(deviceToken2);
        deviceToken1.setId(null);
        assertThat(deviceToken1).isNotEqualTo(deviceToken2);
    }
}
