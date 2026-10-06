package vn.nvsoft.repository;
import vn.nvsoft.domain.ProRefinalCC;
import vn.nvsoft.domain.ProRefinalCCChiTietLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * Spring Data  repository for the ProRefinalCCChiTietLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProRefinalCCChiTietLoiRepository extends JpaRepository<ProRefinalCCChiTietLoi, Long> {

    List<ProRefinalCCChiTietLoi> findAllByProRefinalCC(ProRefinalCC refinal);
}
