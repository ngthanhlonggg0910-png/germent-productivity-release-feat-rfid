package vn.nvsoft.repository;
import vn.nvsoft.domain.SoLuongHangKiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SoLuongHangKiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoLuongHangKiemRepository extends JpaRepository<SoLuongHangKiem, Long> {

}
