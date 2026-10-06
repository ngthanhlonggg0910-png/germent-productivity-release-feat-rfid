package vn.nvsoft.repository;
import java.util.List;

import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.domain.ThongSoDoSP;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ThongSoDoSPQuyCach;


/**
 * Spring Data  repository for the ThongSoDoSP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongSoDoSPRepository extends JpaRepository<ThongSoDoSP, Long> {

    List<ThongSoDoSP> findAllByProSanPham(ProSanPham proSanPham);

    @Query(nativeQuery = true, value = "EXEC getDanhSachThongSoDoTheoChuyenVaProSanPhamId :spId, :chuyenId")
    List<ThongSoDoSP> findAllByProSanPhamAndChuyenId(@Param("spId") Long spId, @Param("chuyenId") Long chuyenId);
}
