package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QuyCachNPLMapperTest {

    private QuyCachNPLMapper quyCachNPLMapper;

    @BeforeEach
    public void setUp() {
        quyCachNPLMapper = new QuyCachNPLMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(quyCachNPLMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(quyCachNPLMapper.fromId(null)).isNull();
    }
}
