package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SysPermissionMapperTest {

    private SysPermissionMapper sysPermissionMapper;

    @BeforeEach
    public void setUp() {
        sysPermissionMapper = new SysPermissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(sysPermissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sysPermissionMapper.fromId(null)).isNull();
    }
}
