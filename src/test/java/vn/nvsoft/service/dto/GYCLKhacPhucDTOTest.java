package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GYCLKhacPhucDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GYCLKhacPhucDTO.class);
        GYCLKhacPhucDTO gYCLKhacPhucDTO1 = new GYCLKhacPhucDTO();
        gYCLKhacPhucDTO1.setId(1L);
        GYCLKhacPhucDTO gYCLKhacPhucDTO2 = new GYCLKhacPhucDTO();
        assertThat(gYCLKhacPhucDTO1).isNotEqualTo(gYCLKhacPhucDTO2);
        gYCLKhacPhucDTO2.setId(gYCLKhacPhucDTO1.getId());
        assertThat(gYCLKhacPhucDTO1).isEqualTo(gYCLKhacPhucDTO2);
        gYCLKhacPhucDTO2.setId(2L);
        assertThat(gYCLKhacPhucDTO1).isNotEqualTo(gYCLKhacPhucDTO2);
        gYCLKhacPhucDTO1.setId(null);
        assertThat(gYCLKhacPhucDTO1).isNotEqualTo(gYCLKhacPhucDTO2);
    }
}
