package vn.nvsoft.repository;
import vn.nvsoft.domain.ProLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProLoiRepository extends JpaRepository<ProLoi, Long>, JpaSpecificationExecutor<ProLoi> {

}
