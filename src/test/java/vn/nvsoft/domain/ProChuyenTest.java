package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProChuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProChuyen.class);
        ProChuyen proChuyen1 = new ProChuyen();
        proChuyen1.setId(1L);
        ProChuyen proChuyen2 = new ProChuyen();
        proChuyen2.setId(proChuyen1.getId());
        assertThat(proChuyen1).isEqualTo(proChuyen2);
        proChuyen2.setId(2L);
        assertThat(proChuyen1).isNotEqualTo(proChuyen2);
        proChuyen1.setId(null);
        assertThat(proChuyen1).isNotEqualTo(proChuyen2);
    }
}
