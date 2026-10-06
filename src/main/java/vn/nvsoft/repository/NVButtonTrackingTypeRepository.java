package vn.nvsoft.repository;
import vn.nvsoft.domain.NVButtonTrackingType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NVButtonTrackingType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NVButtonTrackingTypeRepository extends JpaRepository<NVButtonTrackingType, Long> {

}
