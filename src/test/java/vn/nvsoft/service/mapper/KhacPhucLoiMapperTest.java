package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class KhacPhucLoiMapperTest {

    private KhacPhucLoiMapper khacPhucLoiMapper;

    @BeforeEach
    public void setUp() {
        khacPhucLoiMapper = new KhacPhucLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(khacPhucLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(khacPhucLoiMapper.fromId(null)).isNull();
    }
}
