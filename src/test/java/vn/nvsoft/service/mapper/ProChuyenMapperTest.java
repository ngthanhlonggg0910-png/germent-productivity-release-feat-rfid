package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProChuyenMapperTest {

    private ProChuyenMapper proChuyenMapper;

    @BeforeEach
    public void setUp() {
        proChuyenMapper = new ProChuyenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proChuyenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proChuyenMapper.fromId(null)).isNull();
    }
}
