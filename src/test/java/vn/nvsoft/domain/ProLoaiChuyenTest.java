package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProLoaiChuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProLoaiChuyen.class);
        ProLoaiChuyen proLoaiChuyen1 = new ProLoaiChuyen();
        proLoaiChuyen1.setId(1L);
        ProLoaiChuyen proLoaiChuyen2 = new ProLoaiChuyen();
        proLoaiChuyen2.setId(proLoaiChuyen1.getId());
        assertThat(proLoaiChuyen1).isEqualTo(proLoaiChuyen2);
        proLoaiChuyen2.setId(2L);
        assertThat(proLoaiChuyen1).isNotEqualTo(proLoaiChuyen2);
        proLoaiChuyen1.setId(null);
        assertThat(proLoaiChuyen1).isNotEqualTo(proLoaiChuyen2);
    }
}
