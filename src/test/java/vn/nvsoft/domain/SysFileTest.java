package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysFileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFile.class);
        SysFile sysFile1 = new SysFile();
        sysFile1.setId(1L);
        SysFile sysFile2 = new SysFile();
        sysFile2.setId(sysFile1.getId());
        assertThat(sysFile1).isEqualTo(sysFile2);
        sysFile2.setId(2L);
        assertThat(sysFile1).isNotEqualTo(sysFile2);
        sysFile1.setId(null);
        assertThat(sysFile1).isNotEqualTo(sysFile2);
    }
}
