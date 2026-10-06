package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CauHinhZaloMapperTest {

    private CauHinhZaloMapper cauHinhZaloMapper;

    @BeforeEach
    public void setUp() {
        cauHinhZaloMapper = new CauHinhZaloMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cauHinhZaloMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cauHinhZaloMapper.fromId(null)).isNull();
    }
}
