package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProSanPhamMapperTest {

    private ProSanPhamMapper proSanPhamMapper;

    @BeforeEach
    public void setUp() {
        proSanPhamMapper = new ProSanPhamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proSanPhamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proSanPhamMapper.fromId(null)).isNull();
    }
}
