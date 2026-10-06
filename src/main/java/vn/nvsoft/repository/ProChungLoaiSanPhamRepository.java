package vn.nvsoft.repository;
import vn.nvsoft.domain.ProChungLoaiSanPham;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProChungLoaiSanPham entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProChungLoaiSanPhamRepository extends JpaRepository<ProChungLoaiSanPham, Long> {

}
