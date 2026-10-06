package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProCongTyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProCongTyDTO.class);
        ProCongTyDTO proCongTyDTO1 = new ProCongTyDTO();
        proCongTyDTO1.setId(1L);
        ProCongTyDTO proCongTyDTO2 = new ProCongTyDTO();
        assertThat(proCongTyDTO1).isNotEqualTo(proCongTyDTO2);
        proCongTyDTO2.setId(proCongTyDTO1.getId());
        assertThat(proCongTyDTO1).isEqualTo(proCongTyDTO2);
        proCongTyDTO2.setId(2L);
        assertThat(proCongTyDTO1).isNotEqualTo(proCongTyDTO2);
        proCongTyDTO1.setId(null);
        assertThat(proCongTyDTO1).isNotEqualTo(proCongTyDTO2);
    }
}
