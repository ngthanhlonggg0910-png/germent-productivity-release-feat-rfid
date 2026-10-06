package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class CauHinhZaloDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhZaloDTO.class);
        CauHinhZaloDTO cauHinhZaloDTO1 = new CauHinhZaloDTO();
        cauHinhZaloDTO1.setId(1L);
        CauHinhZaloDTO cauHinhZaloDTO2 = new CauHinhZaloDTO();
        assertThat(cauHinhZaloDTO1).isNotEqualTo(cauHinhZaloDTO2);
        cauHinhZaloDTO2.setId(cauHinhZaloDTO1.getId());
        assertThat(cauHinhZaloDTO1).isEqualTo(cauHinhZaloDTO2);
        cauHinhZaloDTO2.setId(2L);
        assertThat(cauHinhZaloDTO1).isNotEqualTo(cauHinhZaloDTO2);
        cauHinhZaloDTO1.setId(null);
        assertThat(cauHinhZaloDTO1).isNotEqualTo(cauHinhZaloDTO2);
    }
}
