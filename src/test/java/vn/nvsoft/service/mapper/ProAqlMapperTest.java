package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProAqlMapperTest {

    private ProAqlMapper proAqlMapper;

    @BeforeEach
    public void setUp() {
        proAqlMapper = new ProAqlMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proAqlMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proAqlMapper.fromId(null)).isNull();
    }
}
