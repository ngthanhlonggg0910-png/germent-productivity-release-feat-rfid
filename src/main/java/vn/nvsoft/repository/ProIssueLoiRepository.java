package vn.nvsoft.repository;
import vn.nvsoft.domain.ProIssueCumLoi;
import vn.nvsoft.domain.ProIssueLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ProIssueLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProIssueLoiRepository extends JpaRepository<ProIssueLoi, Long> {

    List<ProIssueLoi> findAllByProIssueCumLoi(ProIssueCumLoi proIssueCumLoi);
}
