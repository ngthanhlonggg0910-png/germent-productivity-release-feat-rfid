package vn.nvsoft.repository;
import vn.nvsoft.domain.ProCongTy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProCongTy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProCongTyRepository extends JpaRepository<ProCongTy, Long> {

}
