package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GroupTopicDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupTopicDTO.class);
        GroupTopicDTO groupTopicDTO1 = new GroupTopicDTO();
        groupTopicDTO1.setId(1L);
        GroupTopicDTO groupTopicDTO2 = new GroupTopicDTO();
        assertThat(groupTopicDTO1).isNotEqualTo(groupTopicDTO2);
        groupTopicDTO2.setId(groupTopicDTO1.getId());
        assertThat(groupTopicDTO1).isEqualTo(groupTopicDTO2);
        groupTopicDTO2.setId(2L);
        assertThat(groupTopicDTO1).isNotEqualTo(groupTopicDTO2);
        groupTopicDTO1.setId(null);
        assertThat(groupTopicDTO1).isNotEqualTo(groupTopicDTO2);
    }
}
