package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DungSaiMapperTest {

    private DungSaiMapper dungSaiMapper;

    @BeforeEach
    public void setUp() {
        dungSaiMapper = new DungSaiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(dungSaiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dungSaiMapper.fromId(null)).isNull();
    }
}
