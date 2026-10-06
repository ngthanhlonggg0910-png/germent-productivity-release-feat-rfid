package vn.nvsoft.repository;
import vn.nvsoft.domain.ProSanPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProSanPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProSanPhamRepository extends JpaRepository<ProSanPham, Long>, JpaSpecificationExecutor<ProSanPham> {

}
