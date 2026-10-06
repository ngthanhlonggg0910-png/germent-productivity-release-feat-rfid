package vn.nvsoft.repository.heso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.domain.heso.HeSo;
import vn.nvsoft.domain.heso.HeSoNhanVien;

import java.util.Date;
import java.util.List;

@Repository
public interface HeSoRepository extends JpaRepository<ProTaiKhoanCheckPoint, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_LAYDANHSACHLAODONG :chuyenId")
    List<HeSoNhanVien> getDanhSachLaoCuaChuyen(@Param("chuyenId") Long chuyenId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_LAYDANHSACHLAODONG_BYDATE :chuyenId, :byDate")
    List<HeSoNhanVien> getDanhSachLaoCuaChuyenByDate(@Param("chuyenId") Long chuyenId, @Param("byDate") Date date);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_LAYDANHSACHLAODONG_RESET :chuyenId")
    void resetHeSoCuaChuyen(@Param("chuyenId") Long chuyenId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_LAYDANHSACHLAODONG_UPDATE :id, :heSo")
    void updateHeSoNhanVien(@Param("id") Long id, @Param("heSo") Double heSo);

    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_LAYDANHSACHHESO")
    List<HeSo> getHeSos();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC TABLET_TOTRUONG_XACNHANHESO :chuyenId, :xacNhan")
    void xacNhanDaSuaXongHeSoChoChuyen(@Param("chuyenId") Long chuyenId, @Param("xacNhan") Boolean xacNhan);
}
