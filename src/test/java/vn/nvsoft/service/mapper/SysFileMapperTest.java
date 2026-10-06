package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SysFileMapperTest {

    private SysFileMapper sysFileMapper;

    @BeforeEach
    public void setUp() {
        sysFileMapper = new SysFileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sysFileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysFileMapper.fromId(null)).isNull();
    }
}
