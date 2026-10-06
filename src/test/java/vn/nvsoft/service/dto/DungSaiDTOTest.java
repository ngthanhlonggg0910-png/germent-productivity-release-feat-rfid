package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DungSaiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DungSaiDTO.class);
        DungSaiDTO dungSaiDTO1 = new DungSaiDTO();
        dungSaiDTO1.setId(1L);
        DungSaiDTO dungSaiDTO2 = new DungSaiDTO();
        assertThat(dungSaiDTO1).isNotEqualTo(dungSaiDTO2);
        dungSaiDTO2.setId(dungSaiDTO1.getId());
        assertThat(dungSaiDTO1).isEqualTo(dungSaiDTO2);
        dungSaiDTO2.setId(2L);
        assertThat(dungSaiDTO1).isNotEqualTo(dungSaiDTO2);
        dungSaiDTO1.setId(null);
        assertThat(dungSaiDTO1).isNotEqualTo(dungSaiDTO2);
    }
}
