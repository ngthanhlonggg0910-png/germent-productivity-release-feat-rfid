package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class GopYChatLuongDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GopYChatLuongDTO.class);
        GopYChatLuongDTO gopYChatLuongDTO1 = new GopYChatLuongDTO();
        gopYChatLuongDTO1.setId(1L);
        GopYChatLuongDTO gopYChatLuongDTO2 = new GopYChatLuongDTO();
        assertThat(gopYChatLuongDTO1).isNotEqualTo(gopYChatLuongDTO2);
        gopYChatLuongDTO2.setId(gopYChatLuongDTO1.getId());
        assertThat(gopYChatLuongDTO1).isEqualTo(gopYChatLuongDTO2);
        gopYChatLuongDTO2.setId(2L);
        assertThat(gopYChatLuongDTO1).isNotEqualTo(gopYChatLuongDTO2);
        gopYChatLuongDTO1.setId(null);
        assertThat(gopYChatLuongDTO1).isNotEqualTo(gopYChatLuongDTO2);
    }
}
