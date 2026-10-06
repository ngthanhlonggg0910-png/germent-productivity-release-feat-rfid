package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NVButtonTrackingTypeMapperTest {

    private NVButtonTrackingTypeMapper nVButtonTrackingTypeMapper;

    @BeforeEach
    public void setUp() {
        nVButtonTrackingTypeMapper = new NVButtonTrackingTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nVButtonTrackingTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nVButtonTrackingTypeMapper.fromId(null)).isNull();
    }
}
