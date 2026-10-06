package vn.nvsoft.repository;
import vn.nvsoft.domain.ProCongDoan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProCongDoan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProCongDoanRepository extends JpaRepository<ProCongDoan, Long> {

}
