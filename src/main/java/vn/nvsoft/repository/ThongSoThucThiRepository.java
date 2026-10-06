package vn.nvsoft.repository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongSoThucThi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongSoThucThiRepository extends JpaRepository<ThongSoThucThi, Long> {


    
    List<ThongSoThucThi> findByThongSoDoSPSizeThongSoDoSPAndProKeHoachSanXuatAndCheckPointIdAndMausanpham(@Param("thongSoDoSp") ThongSoDoSP thongSoDoSp,
	@Param("proKeHoachSanXuat") ProKeHoachSanXuat proKeHoachSanXuat
	,@Param("checkPointId") Long checkPointId
	,@Param("mausanpham") String mausanpham);

    @Query(value = "EXEC GetSizePOByThongSoDoSPId :thongSoDoSpId", nativeQuery = true)
    List<OmmPOSize> findSPSizeByThongSoDoSP(@Param("thongSoDoSpId") Long thongSoDoSpId);
}
