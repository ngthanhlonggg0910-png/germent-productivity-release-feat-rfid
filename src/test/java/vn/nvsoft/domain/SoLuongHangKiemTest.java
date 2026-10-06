package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SoLuongHangKiemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoLuongHangKiem.class);
        SoLuongHangKiem soLuongHangKiem1 = new SoLuongHangKiem();
        soLuongHangKiem1.setId(1L);
        SoLuongHangKiem soLuongHangKiem2 = new SoLuongHangKiem();
        soLuongHangKiem2.setId(soLuongHangKiem1.getId());
        assertThat(soLuongHangKiem1).isEqualTo(soLuongHangKiem2);
        soLuongHangKiem2.setId(2L);
        assertThat(soLuongHangKiem1).isNotEqualTo(soLuongHangKiem2);
        soLuongHangKiem1.setId(null);
        assertThat(soLuongHangKiem1).isNotEqualTo(soLuongHangKiem2);
    }
}
