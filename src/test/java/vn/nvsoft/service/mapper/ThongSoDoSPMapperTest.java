package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ThongSoDoSPMapperTest {

    private ThongSoDoSPMapper thongSoDoSPMapper;

    @BeforeEach
    public void setUp() {
        thongSoDoSPMapper = new ThongSoDoSPMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(thongSoDoSPMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thongSoDoSPMapper.fromId(null)).isNull();
    }
}
