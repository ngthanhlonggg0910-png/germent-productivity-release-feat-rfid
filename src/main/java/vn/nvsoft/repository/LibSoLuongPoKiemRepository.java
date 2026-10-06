package vn.nvsoft.repository;
import vn.nvsoft.domain.LibSoLuongPoKiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LibSoLuongPoKiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibSoLuongPoKiemRepository extends JpaRepository<LibSoLuongPoKiem, Long> {

}
