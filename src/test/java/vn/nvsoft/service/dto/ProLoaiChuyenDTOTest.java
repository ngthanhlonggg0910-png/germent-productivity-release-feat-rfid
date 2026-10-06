package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProLoaiChuyenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProLoaiChuyenDTO.class);
        ProLoaiChuyenDTO proLoaiChuyenDTO1 = new ProLoaiChuyenDTO();
        proLoaiChuyenDTO1.setId(1L);
        ProLoaiChuyenDTO proLoaiChuyenDTO2 = new ProLoaiChuyenDTO();
        assertThat(proLoaiChuyenDTO1).isNotEqualTo(proLoaiChuyenDTO2);
        proLoaiChuyenDTO2.setId(proLoaiChuyenDTO1.getId());
        assertThat(proLoaiChuyenDTO1).isEqualTo(proLoaiChuyenDTO2);
        proLoaiChuyenDTO2.setId(2L);
        assertThat(proLoaiChuyenDTO1).isNotEqualTo(proLoaiChuyenDTO2);
        proLoaiChuyenDTO1.setId(null);
        assertThat(proLoaiChuyenDTO1).isNotEqualTo(proLoaiChuyenDTO2);
    }
}
