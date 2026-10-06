package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProLoiMapperTest {

    private ProLoiMapper proLoiMapper;

    @BeforeEach
    public void setUp() {
        proLoiMapper = new ProLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proLoiMapper.fromId(null)).isNull();
    }
}
