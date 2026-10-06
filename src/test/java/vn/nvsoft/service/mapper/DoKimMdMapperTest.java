package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DoKimMdMapperTest {

    private DoKimMdMapper doKimMdMapper;

    @BeforeEach
    public void setUp() {
        doKimMdMapper = new DoKimMdMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(doKimMdMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(doKimMdMapper.fromId(null)).isNull();
    }
}
