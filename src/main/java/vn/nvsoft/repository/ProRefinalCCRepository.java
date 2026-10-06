package vn.nvsoft.repository;
import vn.nvsoft.domain.ProRefinalCC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ProRefinalCC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProRefinalCCRepository extends JpaRepository<ProRefinalCC, Long> {

    List<ProRefinalCC> findAllByKhxhId(Long khxhId);
}
