package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProTaiKhoanCheckPointMapperTest {

    private ProTaiKhoanCheckPointMapper proTaiKhoanCheckPointMapper;

    @BeforeEach
    public void setUp() {
        proTaiKhoanCheckPointMapper = new ProTaiKhoanCheckPointMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proTaiKhoanCheckPointMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proTaiKhoanCheckPointMapper.fromId(null)).isNull();
    }
}
