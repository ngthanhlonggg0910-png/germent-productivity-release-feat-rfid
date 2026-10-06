package vn.nvsoft.repository;
import vn.nvsoft.domain.QuyCachNPL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuyCachNPL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuyCachNPLRepository extends JpaRepository<QuyCachNPL, Long> {

}
