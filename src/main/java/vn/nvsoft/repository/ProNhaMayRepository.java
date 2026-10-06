package vn.nvsoft.repository;
import vn.nvsoft.domain.ProNhaMay;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProNhaMay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProNhaMayRepository extends JpaRepository<ProNhaMay, Long> {

}
