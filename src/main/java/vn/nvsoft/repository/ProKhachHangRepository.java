package vn.nvsoft.repository;
import vn.nvsoft.domain.ProKhachHang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProKhachHang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProKhachHangRepository extends JpaRepository<ProKhachHang, Long>, JpaSpecificationExecutor<ProKhachHang> {

}
