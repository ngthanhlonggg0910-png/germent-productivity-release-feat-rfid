package vn.nvsoft.repository.chuyen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.Authority;
import vn.nvsoft.domain.chuyen.NangSuatChuyen;
import vn.nvsoft.domain.chuyen.NgayMayRa;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.domain.chuyen.ThongTinToTruong;

import java.sql.Date;
import java.util.List;

/**
 * Spring Data JPA repository query data from egmf
 */
public interface EgmfRepository extends JpaRepository<Authority, String> {

    @Query(value = "EXEC Lay_Thong_Tin_To_Truong :proNSCLId", nativeQuery = true)
    List<ThongTinToTruong> getToTruongByNSCLId(@Param("proNSCLId") Long proNSCLId);

    @Query(value = "EXEC TABLET_TOTRUONG :chuyenId", nativeQuery = true)
    NangSuatChuyen getNangSuatChuyen(@Param("chuyenId") Long chuyenId);

    @Query(value = "EXEC LIB_CUMCHUYEN_LAYDANHSACH_THEO_TKID :tkId", nativeQuery = true)
    ThongTinChuyen getThongTinChuyen(@Param("tkId") String tkId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC PRO_UPDATE_PASS_FAIL_KEHOACHXUATHANG :passFail, :khxhId")
    void updateResultKeHoachXuatHang(@Param("passFail") Boolean passFail, @Param("khxhId") Long khxhId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC PRO_CHOT_MAY_RA_NGAY :checkPointId")
    NgayMayRa updateChotMayRaNgay(@Param("checkPointId") Long checkPointId);

    @Query(value = "EXEC PRO_CHOT_MAY_RA_NGAY_LAN_CUOI_UPDATE :checkPointId", nativeQuery = true)
    NgayMayRa getChotMayRaNgayLanCuoi(@Param("checkPointId") Long checkPointId);
}
