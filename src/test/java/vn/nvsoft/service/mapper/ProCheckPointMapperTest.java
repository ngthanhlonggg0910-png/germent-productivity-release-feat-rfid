package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProCheckPointMapperTest {

    private ProCheckPointMapper proCheckPointMapper;

    @BeforeEach
    public void setUp() {
        proCheckPointMapper = new ProCheckPointMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proCheckPointMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proCheckPointMapper.fromId(null)).isNull();
    }
}
