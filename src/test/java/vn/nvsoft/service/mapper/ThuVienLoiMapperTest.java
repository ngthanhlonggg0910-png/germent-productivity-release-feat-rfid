package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ThuVienLoiMapperTest {

    private ThuVienLoiMapper thuVienLoiMapper;

    @BeforeEach
    public void setUp() {
        thuVienLoiMapper = new ThuVienLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(thuVienLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(thuVienLoiMapper.fromId(null)).isNull();
    }
}
