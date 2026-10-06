package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SysFunctionMapperTest {

    private SysFunctionMapper sysFunctionMapper;

    @BeforeEach
    public void setUp() {
        sysFunctionMapper = new SysFunctionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sysFunctionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysFunctionMapper.fromId(null)).isNull();
    }
}
