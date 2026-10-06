package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MapControlButtonMapperTest {

    private MapControlButtonMapper mapControlButtonMapper;

    @BeforeEach
    public void setUp() {
        mapControlButtonMapper = new MapControlButtonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(mapControlButtonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mapControlButtonMapper.fromId(null)).isNull();
    }
}
