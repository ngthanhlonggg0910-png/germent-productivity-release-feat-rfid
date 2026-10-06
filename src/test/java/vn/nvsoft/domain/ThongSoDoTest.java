package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ThongSoDoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThongSoDo.class);
        ThongSoDo thongSoDo1 = new ThongSoDo();
        thongSoDo1.setId(1L);
        ThongSoDo thongSoDo2 = new ThongSoDo();
        thongSoDo2.setId(thongSoDo1.getId());
        assertThat(thongSoDo1).isEqualTo(thongSoDo2);
        thongSoDo2.setId(2L);
        assertThat(thongSoDo1).isNotEqualTo(thongSoDo2);
        thongSoDo1.setId(null);
        assertThat(thongSoDo1).isNotEqualTo(thongSoDo2);
    }
}
