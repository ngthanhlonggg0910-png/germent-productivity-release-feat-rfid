package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProRefinalCCMapperTest {

    private ProRefinalCCMapper proRefinalCCMapper;

    @BeforeEach
    public void setUp() {
        proRefinalCCMapper = new ProRefinalCCMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proRefinalCCMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proRefinalCCMapper.fromId(null)).isNull();
    }
}
