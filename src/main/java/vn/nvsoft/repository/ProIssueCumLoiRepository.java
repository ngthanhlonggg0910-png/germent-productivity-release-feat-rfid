package vn.nvsoft.repository;
import vn.nvsoft.domain.ProIssueCumLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProIssueCumLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProIssueCumLoiRepository extends JpaRepository<ProIssueCumLoi, Long> {

}
