package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SysRoleHasFunctionMapperTest {

    private SysRoleHasFunctionMapper sysRoleHasFunctionMapper;

    @BeforeEach
    public void setUp() {
        sysRoleHasFunctionMapper = new SysRoleHasFunctionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sysRoleHasFunctionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysRoleHasFunctionMapper.fromId(null)).isNull();
    }
}
