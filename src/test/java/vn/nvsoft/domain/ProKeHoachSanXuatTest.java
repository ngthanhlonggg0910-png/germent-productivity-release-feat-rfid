package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProKeHoachSanXuatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProKeHoachSanXuat.class);
        ProKeHoachSanXuat proKeHoachSanXuat1 = new ProKeHoachSanXuat();
        proKeHoachSanXuat1.setId(1L);
        ProKeHoachSanXuat proKeHoachSanXuat2 = new ProKeHoachSanXuat();
        proKeHoachSanXuat2.setId(proKeHoachSanXuat1.getId());
        assertThat(proKeHoachSanXuat1).isEqualTo(proKeHoachSanXuat2);
        proKeHoachSanXuat2.setId(2L);
        assertThat(proKeHoachSanXuat1).isNotEqualTo(proKeHoachSanXuat2);
        proKeHoachSanXuat1.setId(null);
        assertThat(proKeHoachSanXuat1).isNotEqualTo(proKeHoachSanXuat2);
    }
}
