package vn.nvsoft.repository;
import vn.nvsoft.domain.ProNhomCongDoan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProNhomCongDoan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProNhomCongDoanRepository extends JpaRepository<ProNhomCongDoan, Long> {

}
