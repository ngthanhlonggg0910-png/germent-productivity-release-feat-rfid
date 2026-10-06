package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProRefinalMapperTest {

    private ProRefinalMapper proRefinalMapper;

    @BeforeEach
    public void setUp() {
        proRefinalMapper = new ProRefinalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proRefinalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proRefinalMapper.fromId(null)).isNull();
    }
}
