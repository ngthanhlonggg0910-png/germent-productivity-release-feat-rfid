package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class MapControlButtonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapControlButton.class);
        MapControlButton mapControlButton1 = new MapControlButton();
        mapControlButton1.setId(1L);
        MapControlButton mapControlButton2 = new MapControlButton();
        mapControlButton2.setId(mapControlButton1.getId());
        assertThat(mapControlButton1).isEqualTo(mapControlButton2);
        mapControlButton2.setId(2L);
        assertThat(mapControlButton1).isNotEqualTo(mapControlButton2);
        mapControlButton1.setId(null);
        assertThat(mapControlButton1).isNotEqualTo(mapControlButton2);
    }
}
