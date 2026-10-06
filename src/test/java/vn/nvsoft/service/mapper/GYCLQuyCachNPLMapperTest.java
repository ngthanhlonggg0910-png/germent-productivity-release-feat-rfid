package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GYCLQuyCachNPLMapperTest {

    private GYCLQuyCachNPLMapper gYCLQuyCachNPLMapper;

    @BeforeEach
    public void setUp() {
        gYCLQuyCachNPLMapper = new GYCLQuyCachNPLMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(gYCLQuyCachNPLMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gYCLQuyCachNPLMapper.fromId(null)).isNull();
    }
}
