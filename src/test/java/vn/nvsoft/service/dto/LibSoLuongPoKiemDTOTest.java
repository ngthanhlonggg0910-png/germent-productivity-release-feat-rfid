package vn.nvsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import vn.nvsoft.web.rest.TestUtil;

public class LibSoLuongPoKiemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibSoLuongPoKiemDTO.class);
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO1 = new LibSoLuongPoKiemDTO();
        libSoLuongPoKiemDTO1.setId(1L);
        LibSoLuongPoKiemDTO libSoLuongPoKiemDTO2 = new LibSoLuongPoKiemDTO();
        assertThat(libSoLuongPoKiemDTO1).isNotEqualTo(libSoLuongPoKiemDTO2);
        libSoLuongPoKiemDTO2.setId(libSoLuongPoKiemDTO1.getId());
        assertThat(libSoLuongPoKiemDTO1).isEqualTo(libSoLuongPoKiemDTO2);
        libSoLuongPoKiemDTO2.setId(2L);
        assertThat(libSoLuongPoKiemDTO1).isNotEqualTo(libSoLuongPoKiemDTO2);
        libSoLuongPoKiemDTO1.setId(null);
        assertThat(libSoLuongPoKiemDTO1).isNotEqualTo(libSoLuongPoKiemDTO2);
    }
}
