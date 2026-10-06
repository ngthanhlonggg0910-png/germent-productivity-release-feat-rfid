package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProCumLoiMapperTest {

    private ProCumLoiMapper proCumLoiMapper;

    @BeforeEach
    public void setUp() {
        proCumLoiMapper = new ProCumLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proCumLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proCumLoiMapper.fromId(null)).isNull();
    }
}
