package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SysGroupModuleMapperTest {

    private SysGroupModuleMapper sysGroupModuleMapper;

    @BeforeEach
    public void setUp() {
        sysGroupModuleMapper = new SysGroupModuleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sysGroupModuleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysGroupModuleMapper.fromId(null)).isNull();
    }
}
