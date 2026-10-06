package vn.nvsoft.repository;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProTaiKhoanCheckPoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProTaiKhoanCheckPointRepository extends JpaRepository<ProTaiKhoanCheckPoint, Long> {

}
