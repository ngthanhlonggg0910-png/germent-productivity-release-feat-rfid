package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProFinalChiTietLoiMapperTest {

    private ProFinalChiTietLoiMapper proFinalChiTietLoiMapper;

    @BeforeEach
    public void setUp() {
        proFinalChiTietLoiMapper = new ProFinalChiTietLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proFinalChiTietLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proFinalChiTietLoiMapper.fromId(null)).isNull();
    }
}
