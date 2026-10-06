package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class MapButtonActionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MapButtonAction.class);
        MapButtonAction mapButtonAction1 = new MapButtonAction();
        mapButtonAction1.setId(1L);
        MapButtonAction mapButtonAction2 = new MapButtonAction();
        mapButtonAction2.setId(mapButtonAction1.getId());
        assertThat(mapButtonAction1).isEqualTo(mapButtonAction2);
        mapButtonAction2.setId(2L);
        assertThat(mapButtonAction1).isNotEqualTo(mapButtonAction2);
        mapButtonAction1.setId(null);
        assertThat(mapButtonAction1).isNotEqualTo(mapButtonAction2);
    }
}
