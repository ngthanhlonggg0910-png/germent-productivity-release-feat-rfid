package vn.nvsoft.repository;
import vn.nvsoft.domain.ProCheckPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProCheckPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProCheckPointRepository extends JpaRepository<ProCheckPoint, Long> {

}
