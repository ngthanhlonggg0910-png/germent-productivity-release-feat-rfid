package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class LibSoLuongPoKiemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibSoLuongPoKiem.class);
        LibSoLuongPoKiem libSoLuongPoKiem1 = new LibSoLuongPoKiem();
        libSoLuongPoKiem1.setId(1L);
        LibSoLuongPoKiem libSoLuongPoKiem2 = new LibSoLuongPoKiem();
        libSoLuongPoKiem2.setId(libSoLuongPoKiem1.getId());
        assertThat(libSoLuongPoKiem1).isEqualTo(libSoLuongPoKiem2);
        libSoLuongPoKiem2.setId(2L);
        assertThat(libSoLuongPoKiem1).isNotEqualTo(libSoLuongPoKiem2);
        libSoLuongPoKiem1.setId(null);
        assertThat(libSoLuongPoKiem1).isNotEqualTo(libSoLuongPoKiem2);
    }
}
