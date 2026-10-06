package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProLoiDTO.class);
        ProLoiDTO proLoiDTO1 = new ProLoiDTO();
        proLoiDTO1.setId(1L);
        ProLoiDTO proLoiDTO2 = new ProLoiDTO();
        assertThat(proLoiDTO1).isNotEqualTo(proLoiDTO2);
        proLoiDTO2.setId(proLoiDTO1.getId());
        assertThat(proLoiDTO1).isEqualTo(proLoiDTO2);
        proLoiDTO2.setId(2L);
        assertThat(proLoiDTO1).isNotEqualTo(proLoiDTO2);
        proLoiDTO1.setId(null);
        assertThat(proLoiDTO1).isNotEqualTo(proLoiDTO2);
    }
}
