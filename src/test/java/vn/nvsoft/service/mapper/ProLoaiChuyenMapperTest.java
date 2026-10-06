package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProLoaiChuyenMapperTest {

    private ProLoaiChuyenMapper proLoaiChuyenMapper;

    @BeforeEach
    public void setUp() {
        proLoaiChuyenMapper = new ProLoaiChuyenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proLoaiChuyenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proLoaiChuyenMapper.fromId(null)).isNull();
    }
}
