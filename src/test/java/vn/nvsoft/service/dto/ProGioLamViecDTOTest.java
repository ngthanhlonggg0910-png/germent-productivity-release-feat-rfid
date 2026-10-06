package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProGioLamViecDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProGioLamViecDTO.class);
        ProGioLamViecDTO proGioLamViecDTO1 = new ProGioLamViecDTO();
        proGioLamViecDTO1.setId(1L);
        ProGioLamViecDTO proGioLamViecDTO2 = new ProGioLamViecDTO();
        assertThat(proGioLamViecDTO1).isNotEqualTo(proGioLamViecDTO2);
        proGioLamViecDTO2.setId(proGioLamViecDTO1.getId());
        assertThat(proGioLamViecDTO1).isEqualTo(proGioLamViecDTO2);
        proGioLamViecDTO2.setId(2L);
        assertThat(proGioLamViecDTO1).isNotEqualTo(proGioLamViecDTO2);
        proGioLamViecDTO1.setId(null);
        assertThat(proGioLamViecDTO1).isNotEqualTo(proGioLamViecDTO2);
    }
}
