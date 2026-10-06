package vn.nvsoft.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GopYChatLuongTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GopYChatLuong.class);
        GopYChatLuong gopYChatLuong1 = new GopYChatLuong();
        gopYChatLuong1.setId(1L);
        GopYChatLuong gopYChatLuong2 = new GopYChatLuong();
        gopYChatLuong2.setId(gopYChatLuong1.getId());
        assertThat(gopYChatLuong1).isEqualTo(gopYChatLuong2);
        gopYChatLuong2.setId(2L);
        assertThat(gopYChatLuong1).isNotEqualTo(gopYChatLuong2);
        gopYChatLuong1.setId(null);
        assertThat(gopYChatLuong1).isNotEqualTo(gopYChatLuong2);
    }
}
