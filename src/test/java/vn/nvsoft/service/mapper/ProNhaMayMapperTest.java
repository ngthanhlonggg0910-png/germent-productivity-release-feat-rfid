package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProNhaMayMapperTest {

    private ProNhaMayMapper proNhaMayMapper;

    @BeforeEach
    public void setUp() {
        proNhaMayMapper = new ProNhaMayMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proNhaMayMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proNhaMayMapper.fromId(null)).isNull();
    }
}
