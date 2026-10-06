package vn.nvsoft.repository;
import java.util.List;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.domain.ThongSoDoSPSize;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongSoDoSPSize entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongSoDoSPSizeRepository extends JpaRepository<ThongSoDoSPSize, Long> {

    List<ThongSoDoSPSize> findByThongSoDoSP(ThongSoDoSP thongSoDoSP);
}
