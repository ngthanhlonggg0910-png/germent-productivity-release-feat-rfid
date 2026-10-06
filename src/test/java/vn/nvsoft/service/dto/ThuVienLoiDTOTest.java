package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThuVienLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThuVienLoiDTO.class);
        ThuVienLoiDTO thuVienLoiDTO1 = new ThuVienLoiDTO();
        thuVienLoiDTO1.setId(1L);
        ThuVienLoiDTO thuVienLoiDTO2 = new ThuVienLoiDTO();
        assertThat(thuVienLoiDTO1).isNotEqualTo(thuVienLoiDTO2);
        thuVienLoiDTO2.setId(thuVienLoiDTO1.getId());
        assertThat(thuVienLoiDTO1).isEqualTo(thuVienLoiDTO2);
        thuVienLoiDTO2.setId(2L);
        assertThat(thuVienLoiDTO1).isNotEqualTo(thuVienLoiDTO2);
        thuVienLoiDTO1.setId(null);
        assertThat(thuVienLoiDTO1).isNotEqualTo(thuVienLoiDTO2);
    }
}
