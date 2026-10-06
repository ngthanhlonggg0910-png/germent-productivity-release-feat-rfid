package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProIssueLoiMapperTest {

    private ProIssueLoiMapper proIssueLoiMapper;

    @BeforeEach
    public void setUp() {
        proIssueLoiMapper = new ProIssueLoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proIssueLoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proIssueLoiMapper.fromId(null)).isNull();
    }
}
