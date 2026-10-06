package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProCongDoanMapperTest {

    private ProCongDoanMapper proCongDoanMapper;

    @BeforeEach
    public void setUp() {
        proCongDoanMapper = new ProCongDoanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proCongDoanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proCongDoanMapper.fromId(null)).isNull();
    }
}
