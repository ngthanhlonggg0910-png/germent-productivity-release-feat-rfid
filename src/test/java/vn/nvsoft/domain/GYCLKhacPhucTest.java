package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GYCLKhacPhucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GYCLKhacPhuc.class);
        GYCLKhacPhuc gYCLKhacPhuc1 = new GYCLKhacPhuc();
        gYCLKhacPhuc1.setId(1L);
        GYCLKhacPhuc gYCLKhacPhuc2 = new GYCLKhacPhuc();
        gYCLKhacPhuc2.setId(gYCLKhacPhuc1.getId());
        assertThat(gYCLKhacPhuc1).isEqualTo(gYCLKhacPhuc2);
        gYCLKhacPhuc2.setId(2L);
        assertThat(gYCLKhacPhuc1).isNotEqualTo(gYCLKhacPhuc2);
        gYCLKhacPhuc1.setId(null);
        assertThat(gYCLKhacPhuc1).isNotEqualTo(gYCLKhacPhuc2);
    }
}
