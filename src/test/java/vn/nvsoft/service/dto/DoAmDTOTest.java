package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class DoAmDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DoAmDTO.class);
        DoAmDTO doAmDTO1 = new DoAmDTO();
        doAmDTO1.setId(1L);
        DoAmDTO doAmDTO2 = new DoAmDTO();
        assertThat(doAmDTO1).isNotEqualTo(doAmDTO2);
        doAmDTO2.setId(doAmDTO1.getId());
        assertThat(doAmDTO1).isEqualTo(doAmDTO2);
        doAmDTO2.setId(2L);
        assertThat(doAmDTO1).isNotEqualTo(doAmDTO2);
        doAmDTO1.setId(null);
        assertThat(doAmDTO1).isNotEqualTo(doAmDTO2);
    }
}
