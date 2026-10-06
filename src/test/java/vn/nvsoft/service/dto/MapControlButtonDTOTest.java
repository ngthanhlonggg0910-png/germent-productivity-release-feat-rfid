package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class MapControlButtonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapControlButtonDTO.class);
        MapControlButtonDTO mapControlButtonDTO1 = new MapControlButtonDTO();
        mapControlButtonDTO1.setId(1L);
        MapControlButtonDTO mapControlButtonDTO2 = new MapControlButtonDTO();
        assertThat(mapControlButtonDTO1).isNotEqualTo(mapControlButtonDTO2);
        mapControlButtonDTO2.setId(mapControlButtonDTO1.getId());
        assertThat(mapControlButtonDTO1).isEqualTo(mapControlButtonDTO2);
        mapControlButtonDTO2.setId(2L);
        assertThat(mapControlButtonDTO1).isNotEqualTo(mapControlButtonDTO2);
        mapControlButtonDTO1.setId(null);
        assertThat(mapControlButtonDTO1).isNotEqualTo(mapControlButtonDTO2);
    }
}
