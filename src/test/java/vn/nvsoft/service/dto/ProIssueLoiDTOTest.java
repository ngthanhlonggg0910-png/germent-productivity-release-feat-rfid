package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssueLoiDTO.class);
        ProIssueLoiDTO proIssueLoiDTO1 = new ProIssueLoiDTO();
        proIssueLoiDTO1.setId(1L);
        ProIssueLoiDTO proIssueLoiDTO2 = new ProIssueLoiDTO();
        assertThat(proIssueLoiDTO1).isNotEqualTo(proIssueLoiDTO2);
        proIssueLoiDTO2.setId(proIssueLoiDTO1.getId());
        assertThat(proIssueLoiDTO1).isEqualTo(proIssueLoiDTO2);
        proIssueLoiDTO2.setId(2L);
        assertThat(proIssueLoiDTO1).isNotEqualTo(proIssueLoiDTO2);
        proIssueLoiDTO1.setId(null);
        assertThat(proIssueLoiDTO1).isNotEqualTo(proIssueLoiDTO2);
    }
}
