package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DoKimMdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoKimMd.class);
        DoKimMd doKimMd1 = new DoKimMd();
        doKimMd1.setId(1L);
        DoKimMd doKimMd2 = new DoKimMd();
        doKimMd2.setId(doKimMd1.getId());
        assertThat(doKimMd1).isEqualTo(doKimMd2);
        doKimMd2.setId(2L);
        assertThat(doKimMd1).isNotEqualTo(doKimMd2);
        doKimMd1.setId(null);
        assertThat(doKimMd1).isNotEqualTo(doKimMd2);
    }
}
