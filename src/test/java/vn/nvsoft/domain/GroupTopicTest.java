package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GroupTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupTopic.class);
        GroupTopic groupTopic1 = new GroupTopic();
        groupTopic1.setId(1L);
        GroupTopic groupTopic2 = new GroupTopic();
        groupTopic2.setId(groupTopic1.getId());
        assertThat(groupTopic1).isEqualTo(groupTopic2);
        groupTopic2.setId(2L);
        assertThat(groupTopic1).isNotEqualTo(groupTopic2);
        groupTopic1.setId(null);
        assertThat(groupTopic1).isNotEqualTo(groupTopic2);
    }
}
