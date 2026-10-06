package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProCongTyMapperTest {

    private ProCongTyMapper proCongTyMapper;

    @BeforeEach
    public void setUp() {
        proCongTyMapper = new ProCongTyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proCongTyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proCongTyMapper.fromId(null)).isNull();
    }
}
