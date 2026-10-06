package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProFinalMapperTest {

    private ProFinalMapper proFinalMapper;

    @BeforeEach
    public void setUp() {
        proFinalMapper = new ProFinalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proFinalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proFinalMapper.fromId(null)).isNull();
    }
}
