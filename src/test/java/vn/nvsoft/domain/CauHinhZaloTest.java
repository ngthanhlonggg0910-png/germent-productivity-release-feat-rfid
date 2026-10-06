package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class CauHinhZaloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhZalo.class);
        CauHinhZalo cauHinhZalo1 = new CauHinhZalo();
        cauHinhZalo1.setId(1L);
        CauHinhZalo cauHinhZalo2 = new CauHinhZalo();
        cauHinhZalo2.setId(cauHinhZalo1.getId());
        assertThat(cauHinhZalo1).isEqualTo(cauHinhZalo2);
        cauHinhZalo2.setId(2L);
        assertThat(cauHinhZalo1).isNotEqualTo(cauHinhZalo2);
        cauHinhZalo1.setId(null);
        assertThat(cauHinhZalo1).isNotEqualTo(cauHinhZalo2);
    }
}
