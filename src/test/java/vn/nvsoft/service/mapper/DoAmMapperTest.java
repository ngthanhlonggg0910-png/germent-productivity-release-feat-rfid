package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DoAmMapperTest {

    private DoAmMapper doAmMapper;

    @BeforeEach
    public void setUp() {
        doAmMapper = new DoAmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(doAmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(doAmMapper.fromId(null)).isNull();
    }
}
