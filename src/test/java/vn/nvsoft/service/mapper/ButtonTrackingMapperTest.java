package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ButtonTrackingMapperTest {

    private ButtonTrackingMapper buttonTrackingMapper;

    @BeforeEach
    public void setUp() {
        buttonTrackingMapper = new ButtonTrackingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(buttonTrackingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(buttonTrackingMapper.fromId(null)).isNull();
    }
}
