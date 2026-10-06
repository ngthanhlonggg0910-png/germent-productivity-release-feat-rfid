package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ThongSoDoSPSizeMapperTest {

    private ThongSoDoSPSizeMapper thongSoDoSPSizeMapper;

    @BeforeEach
    public void setUp() {
        thongSoDoSPSizeMapper = new ThongSoDoSPSizeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(thongSoDoSPSizeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thongSoDoSPSizeMapper.fromId(null)).isNull();
    }
}
