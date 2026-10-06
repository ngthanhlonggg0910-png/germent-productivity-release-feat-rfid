package vn.nvsoft.repository;
import vn.nvsoft.domain.ProCumLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProCumLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProCumLoiRepository extends JpaRepository<ProCumLoi, Long> {

}
