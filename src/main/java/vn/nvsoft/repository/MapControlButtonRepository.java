package vn.nvsoft.repository;
import java.util.List;
import vn.nvsoft.domain.MapControlButton;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.NVButton;


/**
 * Spring Data  repository for the MapControlButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapControlButtonRepository extends JpaRepository<MapControlButton, Long> {

    List<MapControlButton> findAllBySourceButton(NVButton sourceButton);

    List<MapControlButton> findAllByTargetButton(NVButton targetButton);
}
