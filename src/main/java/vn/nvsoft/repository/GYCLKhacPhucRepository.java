package vn.nvsoft.repository;
import vn.nvsoft.domain.GYCLKhacPhuc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GYCLKhacPhuc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GYCLKhacPhucRepository extends JpaRepository<GYCLKhacPhuc, Long> {

}
