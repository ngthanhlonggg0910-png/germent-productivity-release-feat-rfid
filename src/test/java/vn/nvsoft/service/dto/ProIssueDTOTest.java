package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssueDTO.class);
        ProIssueDTO proIssueDTO1 = new ProIssueDTO();
        proIssueDTO1.setId(1L);
        ProIssueDTO proIssueDTO2 = new ProIssueDTO();
        assertThat(proIssueDTO1).isNotEqualTo(proIssueDTO2);
        proIssueDTO2.setId(proIssueDTO1.getId());
        assertThat(proIssueDTO1).isEqualTo(proIssueDTO2);
        proIssueDTO2.setId(2L);
        assertThat(proIssueDTO1).isNotEqualTo(proIssueDTO2);
        proIssueDTO1.setId(null);
        assertThat(proIssueDTO1).isNotEqualTo(proIssueDTO2);
    }
}
