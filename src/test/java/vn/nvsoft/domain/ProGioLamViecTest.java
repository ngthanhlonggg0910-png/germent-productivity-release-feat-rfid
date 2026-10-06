package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProGioLamViecTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProGioLamViec.class);
        ProGioLamViec proGioLamViec1 = new ProGioLamViec();
        proGioLamViec1.setId(1L);
        ProGioLamViec proGioLamViec2 = new ProGioLamViec();
        proGioLamViec2.setId(proGioLamViec1.getId());
        assertThat(proGioLamViec1).isEqualTo(proGioLamViec2);
        proGioLamViec2.setId(2L);
        assertThat(proGioLamViec1).isNotEqualTo(proGioLamViec2);
        proGioLamViec1.setId(null);
        assertThat(proGioLamViec1).isNotEqualTo(proGioLamViec2);
    }
}
