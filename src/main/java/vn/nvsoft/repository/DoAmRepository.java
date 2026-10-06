package vn.nvsoft.repository;
import vn.nvsoft.domain.DoAm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DoAm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoAmRepository extends JpaRepository<DoAm, Long> {

}
