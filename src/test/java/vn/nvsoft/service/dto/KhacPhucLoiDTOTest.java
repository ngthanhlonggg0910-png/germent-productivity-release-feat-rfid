package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class KhacPhucLoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KhacPhucLoiDTO.class);
        KhacPhucLoiDTO khacPhucLoiDTO1 = new KhacPhucLoiDTO();
        khacPhucLoiDTO1.setId(1L);
        KhacPhucLoiDTO khacPhucLoiDTO2 = new KhacPhucLoiDTO();
        assertThat(khacPhucLoiDTO1).isNotEqualTo(khacPhucLoiDTO2);
        khacPhucLoiDTO2.setId(khacPhucLoiDTO1.getId());
        assertThat(khacPhucLoiDTO1).isEqualTo(khacPhucLoiDTO2);
        khacPhucLoiDTO2.setId(2L);
        assertThat(khacPhucLoiDTO1).isNotEqualTo(khacPhucLoiDTO2);
        khacPhucLoiDTO1.setId(null);
        assertThat(khacPhucLoiDTO1).isNotEqualTo(khacPhucLoiDTO2);
    }
}
