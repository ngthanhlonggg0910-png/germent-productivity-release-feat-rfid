package vn.nvsoft.repository;
import vn.nvsoft.domain.CauHinhZalo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CauHinhZalo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHinhZaloRepository extends JpaRepository<CauHinhZalo, Long> {

}
