package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProNSCLChiTietLoiMapperTest {

    private ProNSCLChiTietLoiMapper proNSCLChiTietLoiMapper;

    @BeforeEach
    public void setUp() {
        proNSCLChiTietLoiMapper = new ProNSCLChiTietLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proNSCLChiTietLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proNSCLChiTietLoiMapper.fromId(null)).isNull();
    }
}
