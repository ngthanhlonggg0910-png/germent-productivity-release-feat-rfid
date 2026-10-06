package vn.nvsoft.repository;
import vn.nvsoft.domain.DoKimMd;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DoKimMd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoKimMdRepository extends JpaRepository<DoKimMd, Long> {

}
