package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ThongSoDoMapperTest {

    private ThongSoDoMapper thongSoDoMapper;

    @BeforeEach
    public void setUp() {
        thongSoDoMapper = new ThongSoDoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(thongSoDoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thongSoDoMapper.fromId(null)).isNull();
    }
}
