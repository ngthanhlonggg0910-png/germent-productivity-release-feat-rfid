package vn.nvsoft.repository;
import vn.nvsoft.domain.ProRefinal;
import vn.nvsoft.domain.ProRefinalChiTietLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ProRefinalChiTietLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProRefinalChiTietLoiRepository extends JpaRepository<ProRefinalChiTietLoi, Long> {

    List<ProRefinalChiTietLoi> findAllByProRefinal(ProRefinal refinal);
}
