package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProTaiKhoanCheckPointTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProTaiKhoanCheckPoint.class);
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint1 = new ProTaiKhoanCheckPoint();
        proTaiKhoanCheckPoint1.setId(1L);
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint2 = new ProTaiKhoanCheckPoint();
        proTaiKhoanCheckPoint2.setId(proTaiKhoanCheckPoint1.getId());
        assertThat(proTaiKhoanCheckPoint1).isEqualTo(proTaiKhoanCheckPoint2);
        proTaiKhoanCheckPoint2.setId(2L);
        assertThat(proTaiKhoanCheckPoint1).isNotEqualTo(proTaiKhoanCheckPoint2);
        proTaiKhoanCheckPoint1.setId(null);
        assertThat(proTaiKhoanCheckPoint1).isNotEqualTo(proTaiKhoanCheckPoint2);
    }
}
