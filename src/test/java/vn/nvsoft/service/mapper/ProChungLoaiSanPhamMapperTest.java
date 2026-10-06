package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProChungLoaiSanPhamMapperTest {

    private ProChungLoaiSanPhamMapper proChungLoaiSanPhamMapper;

    @BeforeEach
    public void setUp() {
        proChungLoaiSanPhamMapper = new ProChungLoaiSanPhamMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proChungLoaiSanPhamMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proChungLoaiSanPhamMapper.fromId(null)).isNull();
    }
}
