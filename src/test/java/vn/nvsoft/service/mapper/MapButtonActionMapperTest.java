package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MapButtonActionMapperTest {

    private MapButtonActionMapper mapButtonActionMapper;

    @BeforeEach
    public void setUp() {
        mapButtonActionMapper = new MapButtonActionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(mapButtonActionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mapButtonActionMapper.fromId(null)).isNull();
    }
}
