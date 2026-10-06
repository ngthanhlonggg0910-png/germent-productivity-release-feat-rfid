package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class MapButtonActionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapButtonActionDTO.class);
        MapButtonActionDTO mapButtonActionDTO1 = new MapButtonActionDTO();
        mapButtonActionDTO1.setId(1L);
        MapButtonActionDTO mapButtonActionDTO2 = new MapButtonActionDTO();
        assertThat(mapButtonActionDTO1).isNotEqualTo(mapButtonActionDTO2);
        mapButtonActionDTO2.setId(mapButtonActionDTO1.getId());
        assertThat(mapButtonActionDTO1).isEqualTo(mapButtonActionDTO2);
        mapButtonActionDTO2.setId(2L);
        assertThat(mapButtonActionDTO1).isNotEqualTo(mapButtonActionDTO2);
        mapButtonActionDTO1.setId(null);
        assertThat(mapButtonActionDTO1).isNotEqualTo(mapButtonActionDTO2);
    }
}
