package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProKhachHangMapperTest {

    private ProKhachHangMapper proKhachHangMapper;

    @BeforeEach
    public void setUp() {
        proKhachHangMapper = new ProKhachHangMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proKhachHangMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proKhachHangMapper.fromId(null)).isNull();
    }
}
