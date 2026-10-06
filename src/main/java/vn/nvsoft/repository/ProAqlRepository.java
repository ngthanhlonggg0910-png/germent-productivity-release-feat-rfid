package vn.nvsoft.repository;
import vn.nvsoft.domain.ProAql;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProAql entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProAqlRepository extends JpaRepository<ProAql, Long> {

}
