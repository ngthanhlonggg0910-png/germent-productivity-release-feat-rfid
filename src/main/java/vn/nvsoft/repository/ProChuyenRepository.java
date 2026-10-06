package vn.nvsoft.repository;
import vn.nvsoft.domain.ProChuyen;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProChuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProChuyenRepository extends JpaRepository<ProChuyen, Long> {

}
