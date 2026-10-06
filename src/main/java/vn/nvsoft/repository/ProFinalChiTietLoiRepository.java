package vn.nvsoft.repository;
import vn.nvsoft.domain.ProFinal;
import vn.nvsoft.domain.ProFinalChiTietLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;

import java.util.List;


/**
 * Spring Data  repository for the ProFinalChiTietLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProFinalChiTietLoiRepository extends JpaRepository<ProFinalChiTietLoi, Long> {

    List<ProFinalChiTietLoi> findAllByProFinal(ProFinal refinal);
}
