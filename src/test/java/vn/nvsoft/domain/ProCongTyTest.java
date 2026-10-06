package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCongTyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCongTy.class);
        ProCongTy proCongTy1 = new ProCongTy();
        proCongTy1.setId(1L);
        ProCongTy proCongTy2 = new ProCongTy();
        proCongTy2.setId(proCongTy1.getId());
        assertThat(proCongTy1).isEqualTo(proCongTy2);
        proCongTy2.setId(2L);
        assertThat(proCongTy1).isNotEqualTo(proCongTy2);
        proCongTy1.setId(null);
        assertThat(proCongTy1).isNotEqualTo(proCongTy2);
    }
}
