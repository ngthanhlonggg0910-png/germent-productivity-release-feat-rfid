package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProGioLamViecMapperTest {

    private ProGioLamViecMapper proGioLamViecMapper;

    @BeforeEach
    public void setUp() {
        proGioLamViecMapper = new ProGioLamViecMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proGioLamViecMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proGioLamViecMapper.fromId(null)).isNull();
    }
}
