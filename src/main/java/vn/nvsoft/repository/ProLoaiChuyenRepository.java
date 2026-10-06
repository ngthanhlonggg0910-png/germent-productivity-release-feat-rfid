package vn.nvsoft.repository;
import vn.nvsoft.domain.ProLoaiChuyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProLoaiChuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProLoaiChuyenRepository extends JpaRepository<ProLoaiChuyen, Long> {

}
