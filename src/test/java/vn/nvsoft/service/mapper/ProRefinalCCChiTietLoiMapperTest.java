package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProRefinalCCChiTietLoiMapperTest {

    private ProRefinalCCChiTietLoiMapper proRefinalCCChiTietLoiMapper;

    @BeforeEach
    public void setUp() {
        proRefinalCCChiTietLoiMapper = new ProRefinalCCChiTietLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proRefinalCCChiTietLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proRefinalCCChiTietLoiMapper.fromId(null)).isNull();
    }
}
