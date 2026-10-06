package vn.nvsoft.repository;
import vn.nvsoft.domain.ProFinal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the ProFinal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProFinalRepository extends JpaRepository<ProFinal, Long> {

    List<ProFinal> findAllByKhxhId(Long khxhId);
}
