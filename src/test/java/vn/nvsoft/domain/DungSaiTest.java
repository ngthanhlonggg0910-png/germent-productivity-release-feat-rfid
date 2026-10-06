package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DungSaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DungSai.class);
        DungSai dungSai1 = new DungSai();
        dungSai1.setId(1L);
        DungSai dungSai2 = new DungSai();
        dungSai2.setId(dungSai1.getId());
        assertThat(dungSai1).isEqualTo(dungSai2);
        dungSai2.setId(2L);
        assertThat(dungSai1).isNotEqualTo(dungSai2);
        dungSai1.setId(null);
        assertThat(dungSai1).isNotEqualTo(dungSai2);
    }
}
