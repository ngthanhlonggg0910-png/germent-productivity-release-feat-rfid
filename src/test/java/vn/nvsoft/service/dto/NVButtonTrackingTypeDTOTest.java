package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class NVButtonTrackingTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NVButtonTrackingTypeDTO.class);
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO1 = new NVButtonTrackingTypeDTO();
        nVButtonTrackingTypeDTO1.setId(1L);
        NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO2 = new NVButtonTrackingTypeDTO();
        assertThat(nVButtonTrackingTypeDTO1).isNotEqualTo(nVButtonTrackingTypeDTO2);
        nVButtonTrackingTypeDTO2.setId(nVButtonTrackingTypeDTO1.getId());
        assertThat(nVButtonTrackingTypeDTO1).isEqualTo(nVButtonTrackingTypeDTO2);
        nVButtonTrackingTypeDTO2.setId(2L);
        assertThat(nVButtonTrackingTypeDTO1).isNotEqualTo(nVButtonTrackingTypeDTO2);
        nVButtonTrackingTypeDTO1.setId(null);
        assertThat(nVButtonTrackingTypeDTO1).isNotEqualTo(nVButtonTrackingTypeDTO2);
    }
}
