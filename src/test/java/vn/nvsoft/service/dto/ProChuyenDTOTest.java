package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProChuyenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProChuyenDTO.class);
        ProChuyenDTO proChuyenDTO1 = new ProChuyenDTO();
        proChuyenDTO1.setId(1L);
        ProChuyenDTO proChuyenDTO2 = new ProChuyenDTO();
        assertThat(proChuyenDTO1).isNotEqualTo(proChuyenDTO2);
        proChuyenDTO2.setId(proChuyenDTO1.getId());
        assertThat(proChuyenDTO1).isEqualTo(proChuyenDTO2);
        proChuyenDTO2.setId(2L);
        assertThat(proChuyenDTO1).isNotEqualTo(proChuyenDTO2);
        proChuyenDTO1.setId(null);
        assertThat(proChuyenDTO1).isNotEqualTo(proChuyenDTO2);
    }
}
