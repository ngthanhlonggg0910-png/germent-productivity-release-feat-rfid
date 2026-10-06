package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProRefinalChiTietLoiMapperTest {

    private ProRefinalChiTietLoiMapper proRefinalChiTietLoiMapper;

    @BeforeEach
    public void setUp() {
        proRefinalChiTietLoiMapper = new ProRefinalChiTietLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proRefinalChiTietLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proRefinalChiTietLoiMapper.fromId(null)).isNull();
    }
}
