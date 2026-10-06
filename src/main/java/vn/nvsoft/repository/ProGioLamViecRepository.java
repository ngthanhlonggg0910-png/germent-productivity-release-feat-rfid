package vn.nvsoft.repository;
import java.util.List;
import vn.nvsoft.domain.ProGioLamViec;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProGioLamViec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProGioLamViecRepository extends JpaRepository<ProGioLamViec, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM pro_gio_lam_viec WHERE CURRENT_TIME <= CAST (gio_ket_thuc AS time) AND CURRENT_TIME > CAST (gio_bat_dau AS time)") //H2
    @Query(nativeQuery = true, value = "SELECT * FROM pro_gio_lam_viec WHERE CONVERT(TIME, CURRENT_TIMESTAMP) <= CONVERT(TIME, gio_ket_thuc) AND CONVERT(TIME, CURRENT_TIMESTAMP) > CONVERT(TIME, gio_bat_dau)")
    List<ProGioLamViec> getGioLamViecHienTai();
}
