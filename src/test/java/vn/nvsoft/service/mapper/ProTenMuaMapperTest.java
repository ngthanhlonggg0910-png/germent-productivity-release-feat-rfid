package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProTenMuaMapperTest {

    private ProTenMuaMapper proTenMuaMapper;

    @BeforeEach
    public void setUp() {
        proTenMuaMapper = new ProTenMuaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proTenMuaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proTenMuaMapper.fromId(null)).isNull();
    }
}
