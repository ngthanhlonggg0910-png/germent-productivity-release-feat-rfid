package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssue.class);
        ProIssue proIssue1 = new ProIssue();
        proIssue1.setId(1L);
        ProIssue proIssue2 = new ProIssue();
        proIssue2.setId(proIssue1.getId());
        assertThat(proIssue1).isEqualTo(proIssue2);
        proIssue2.setId(2L);
        assertThat(proIssue1).isNotEqualTo(proIssue2);
        proIssue1.setId(null);
        assertThat(proIssue1).isNotEqualTo(proIssue2);
    }
}
