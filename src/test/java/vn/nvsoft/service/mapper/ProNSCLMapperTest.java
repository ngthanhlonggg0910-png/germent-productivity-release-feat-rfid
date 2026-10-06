package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProNSCLMapperTest {

    private ProNSCLMapper proNSCLMapper;

    @BeforeEach
    public void setUp() {
        proNSCLMapper = new ProNSCLMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(proNSCLMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proNSCLMapper.fromId(null)).isNull();
    }
}
