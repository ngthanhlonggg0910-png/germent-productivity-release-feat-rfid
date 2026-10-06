package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GopYChatLuongMapperTest {

    private GopYChatLuongMapper gopYChatLuongMapper;

    @BeforeEach
    public void setUp() {
        gopYChatLuongMapper = new GopYChatLuongMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(gopYChatLuongMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gopYChatLuongMapper.fromId(null)).isNull();
    }
}
