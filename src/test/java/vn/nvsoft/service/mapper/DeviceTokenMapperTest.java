package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTokenMapperTest {

    private DeviceTokenMapper deviceTokenMapper;

    @BeforeEach
    public void setUp() {
        deviceTokenMapper = new DeviceTokenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(deviceTokenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(deviceTokenMapper.fromId(null)).isNull();
    }
}
