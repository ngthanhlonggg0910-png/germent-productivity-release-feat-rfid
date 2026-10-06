package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DeviceTokenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviceTokenDTO.class);
        DeviceTokenDTO deviceTokenDTO1 = new DeviceTokenDTO();
        deviceTokenDTO1.setId(1L);
        DeviceTokenDTO deviceTokenDTO2 = new DeviceTokenDTO();
        assertThat(deviceTokenDTO1).isNotEqualTo(deviceTokenDTO2);
        deviceTokenDTO2.setId(deviceTokenDTO1.getId());
        assertThat(deviceTokenDTO1).isEqualTo(deviceTokenDTO2);
        deviceTokenDTO2.setId(2L);
        assertThat(deviceTokenDTO1).isNotEqualTo(deviceTokenDTO2);
        deviceTokenDTO1.setId(null);
        assertThat(deviceTokenDTO1).isNotEqualTo(deviceTokenDTO2);
    }
}
