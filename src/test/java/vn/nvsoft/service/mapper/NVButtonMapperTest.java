package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NVButtonMapperTest {

    private NVButtonMapper nVButtonMapper;

    @BeforeEach
    public void setUp() {
        nVButtonMapper = new NVButtonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nVButtonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nVButtonMapper.fromId(null)).isNull();
    }
}
