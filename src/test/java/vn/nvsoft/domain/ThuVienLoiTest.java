package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThuVienLoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuVienLoi.class);
        ThuVienLoi thuVienLoi1 = new ThuVienLoi();
        thuVienLoi1.setId(1L);
        ThuVienLoi thuVienLoi2 = new ThuVienLoi();
        thuVienLoi2.setId(thuVienLoi1.getId());
        assertThat(thuVienLoi1).isEqualTo(thuVienLoi2);
        thuVienLoi2.setId(2L);
        assertThat(thuVienLoi1).isNotEqualTo(thuVienLoi2);
        thuVienLoi1.setId(null);
        assertThat(thuVienLoi1).isNotEqualTo(thuVienLoi2);
    }
}
