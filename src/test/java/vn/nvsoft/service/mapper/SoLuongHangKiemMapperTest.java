package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SoLuongHangKiemMapperTest {

    private SoLuongHangKiemMapper soLuongHangKiemMapper;

    @BeforeEach
    public void setUp() {
        soLuongHangKiemMapper = new SoLuongHangKiemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(soLuongHangKiemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(soLuongHangKiemMapper.fromId(null)).isNull();
    }
}
