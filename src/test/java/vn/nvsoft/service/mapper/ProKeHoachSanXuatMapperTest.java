package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProKeHoachSanXuatMapperTest {

    private ProKeHoachSanXuatMapper proKeHoachSanXuatMapper;

    @BeforeEach
    public void setUp() {
        proKeHoachSanXuatMapper = new ProKeHoachSanXuatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proKeHoachSanXuatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proKeHoachSanXuatMapper.fromId(null)).isNull();
    }
}
