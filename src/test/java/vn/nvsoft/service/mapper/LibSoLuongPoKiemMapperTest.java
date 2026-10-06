package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LibSoLuongPoKiemMapperTest {

    private LibSoLuongPoKiemMapper libSoLuongPoKiemMapper;

    @BeforeEach
    public void setUp() {
        libSoLuongPoKiemMapper = new LibSoLuongPoKiemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(libSoLuongPoKiemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(libSoLuongPoKiemMapper.fromId(null)).isNull();
    }
}
