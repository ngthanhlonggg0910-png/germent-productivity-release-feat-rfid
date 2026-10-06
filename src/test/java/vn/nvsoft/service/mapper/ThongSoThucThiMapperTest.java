package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ThongSoThucThiMapperTest {

    private ThongSoThucThiMapper thongSoThucThiMapper;

    @BeforeEach
    public void setUp() {
        thongSoThucThiMapper = new ThongSoThucThiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(thongSoThucThiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thongSoThucThiMapper.fromId(null)).isNull();
    }
}
