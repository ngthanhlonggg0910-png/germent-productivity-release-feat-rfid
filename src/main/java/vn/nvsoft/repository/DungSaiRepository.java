package vn.nvsoft.repository;
import vn.nvsoft.domain.DungSai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the DungSai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DungSaiRepository extends JpaRepository<DungSai, Long> {

    List<DungSai> findAllByOrderByCmValueAsc();
}
