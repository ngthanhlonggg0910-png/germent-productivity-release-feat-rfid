package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GroupTopicMapperTest {

    private GroupTopicMapper groupTopicMapper;

    @BeforeEach
    public void setUp() {
        groupTopicMapper = new GroupTopicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(groupTopicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(groupTopicMapper.fromId(null)).isNull();
    }
}
