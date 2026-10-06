package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TrackingMapperTest {

    private TrackingMapper trackingMapper;

    @BeforeEach
    public void setUp() {
        trackingMapper = new TrackingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(trackingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(trackingMapper.fromId(null)).isNull();
    }
}
