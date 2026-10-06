package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class SysFunctionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFunctionDTO.class);
        SysFunctionDTO sysFunctionDTO1 = new SysFunctionDTO();
        sysFunctionDTO1.setId(1L);
        SysFunctionDTO sysFunctionDTO2 = new SysFunctionDTO();
        assertThat(sysFunctionDTO1).isNotEqualTo(sysFunctionDTO2);
        sysFunctionDTO2.setId(sysFunctionDTO1.getId());
        assertThat(sysFunctionDTO1).isEqualTo(sysFunctionDTO2);
        sysFunctionDTO2.setId(2L);
        assertThat(sysFunctionDTO1).isNotEqualTo(sysFunctionDTO2);
        sysFunctionDTO1.setId(null);
        assertThat(sysFunctionDTO1).isNotEqualTo(sysFunctionDTO2);
    }
}
