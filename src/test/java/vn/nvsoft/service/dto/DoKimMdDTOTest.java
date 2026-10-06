package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DoKimMdDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoKimMdDTO.class);
        DoKimMdDTO doKimMdDTO1 = new DoKimMdDTO();
        doKimMdDTO1.setId(1L);
        DoKimMdDTO doKimMdDTO2 = new DoKimMdDTO();
        assertThat(doKimMdDTO1).isNotEqualTo(doKimMdDTO2);
        doKimMdDTO2.setId(doKimMdDTO1.getId());
        assertThat(doKimMdDTO1).isEqualTo(doKimMdDTO2);
        doKimMdDTO2.setId(2L);
        assertThat(doKimMdDTO1).isNotEqualTo(doKimMdDTO2);
        doKimMdDTO1.setId(null);
        assertThat(doKimMdDTO1).isNotEqualTo(doKimMdDTO2);
    }
}
