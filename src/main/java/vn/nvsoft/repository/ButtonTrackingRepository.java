package vn.nvsoft.repository;
import vn.nvsoft.domain.ButtonTracking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ButtonTracking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ButtonTrackingRepository extends JpaRepository<ButtonTracking, Long> {

}
