package vn.nvsoft.repository;
import java.util.List;
import vn.nvsoft.domain.MapButtonAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.service.dto.MapButtonActionDTO;


/**
 * Spring Data  repository for the MapButtonAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapButtonActionRepository extends JpaRepository<MapButtonAction, Long> {

    List<MapButtonAction> findBySourceAliasStateAndTrackingType(Integer sourceAliasState, NVButtonTrackingType trackingType);
}
