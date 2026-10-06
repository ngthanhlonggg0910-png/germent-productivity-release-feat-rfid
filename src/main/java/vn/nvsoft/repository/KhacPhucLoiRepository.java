package vn.nvsoft.repository;
import vn.nvsoft.domain.KhacPhucLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the KhacPhucLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KhacPhucLoiRepository extends JpaRepository<KhacPhucLoi, Long> {

}
