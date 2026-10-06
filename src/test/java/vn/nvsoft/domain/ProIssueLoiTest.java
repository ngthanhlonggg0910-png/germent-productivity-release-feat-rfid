package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProIssueLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProIssueLoi.class);
        ProIssueLoi proIssueLoi1 = new ProIssueLoi();
        proIssueLoi1.setId(1L);
        ProIssueLoi proIssueLoi2 = new ProIssueLoi();
        proIssueLoi2.setId(proIssueLoi1.getId());
        assertThat(proIssueLoi1).isEqualTo(proIssueLoi2);
        proIssueLoi2.setId(2L);
        assertThat(proIssueLoi1).isNotEqualTo(proIssueLoi2);
        proIssueLoi1.setId(null);
        assertThat(proIssueLoi1).isNotEqualTo(proIssueLoi2);
    }
}
