package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProIssueCumLoiMapperTest {

    private ProIssueCumLoiMapper proIssueCumLoiMapper;

    @BeforeEach
    public void setUp() {
        proIssueCumLoiMapper = new ProIssueCumLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proIssueCumLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proIssueCumLoiMapper.fromId(null)).isNull();
    }
}
