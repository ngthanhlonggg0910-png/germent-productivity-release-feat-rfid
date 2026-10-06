package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProNhomCongDoanMapperTest {

    private ProNhomCongDoanMapper proNhomCongDoanMapper;

    @BeforeEach
    public void setUp() {
        proNhomCongDoanMapper = new ProNhomCongDoanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proNhomCongDoanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proNhomCongDoanMapper.fromId(null)).isNull();
    }
}
