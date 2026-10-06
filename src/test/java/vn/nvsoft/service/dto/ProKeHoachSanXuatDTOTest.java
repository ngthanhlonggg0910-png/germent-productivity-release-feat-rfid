package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class ProKeHoachSanXuatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProKeHoachSanXuatDTO.class);
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO1 = new ProKeHoachSanXuatDTO();
        proKeHoachSanXuatDTO1.setId(1L);
        ProKeHoachSanXuatDTO proKeHoachSanXuatDTO2 = new ProKeHoachSanXuatDTO();
        assertThat(proKeHoachSanXuatDTO1).isNotEqualTo(proKeHoachSanXuatDTO2);
        proKeHoachSanXuatDTO2.setId(proKeHoachSanXuatDTO1.getId());
        assertThat(proKeHoachSanXuatDTO1).isEqualTo(proKeHoachSanXuatDTO2);
        proKeHoachSanXuatDTO2.setId(2L);
        assertThat(proKeHoachSanXuatDTO1).isNotEqualTo(proKeHoachSanXuatDTO2);
        proKeHoachSanXuatDTO1.setId(null);
        assertThat(proKeHoachSanXuatDTO1).isNotEqualTo(proKeHoachSanXuatDTO2);
    }
}
