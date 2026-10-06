package vn.nvsoft.repository;
import vn.nvsoft.domain.GYCLQuyCachNPL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GYCLQuyCachNPL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GYCLQuyCachNPLRepository extends JpaRepository<GYCLQuyCachNPL, Long> {

}
