package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GYCLKhacPhucMapperTest {

    private GYCLKhacPhucMapper gYCLKhacPhucMapper;

    @BeforeEach
    public void setUp() {
        gYCLKhacPhucMapper = new GYCLKhacPhucMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(gYCLKhacPhucMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gYCLKhacPhucMapper.fromId(null)).isNull();
    }
}
