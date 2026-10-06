package vn.nvsoft.repository;
import vn.nvsoft.domain.ThuVienLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThuVienLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThuVienLoiRepository extends JpaRepository<ThuVienLoi, Long> {

}
