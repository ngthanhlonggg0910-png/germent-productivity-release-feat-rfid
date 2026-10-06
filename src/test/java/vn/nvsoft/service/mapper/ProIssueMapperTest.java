package vn.nvsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ProIssueMapperTest {

    private ProIssueMapper proIssueMapper;

    @BeforeEach
    public void setUp() {
        proIssueMapper = new ProIssueMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(proIssueMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proIssueMapper.fromId(null)).isNull();
    }
}
