package vn.nvsoft.repository;
import java.util.List;

import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.ProNSCLChiTietLoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.nvsoft.domain.enumeration.GioLam;


/**
 * Spring Data  repository for the ProNSCLChiTietLoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProNSCLChiTietLoiRepository extends JpaRepository<ProNSCLChiTietLoi, Long> {

    @Query("select u from ProNSCLChiTietLoi u " +
//        " inner join ProLoi l on l.id = u.proLoi.id " +
        " where u.proNSCL.id = ?1")
    List<ProNSCLChiTietLoi> findByProNSCLId(Long proNSCLId);

    @Query("select u from ProNSCLChiTietLoi u where u.proNSCL.id = ?1 and u.gioLam = ?2")
    List<ProNSCLChiTietLoi> findByProNSCLIdAndGioLam(Long proNSCLId, GioLam gioLam);

    @Modifying
    @Query("update ProNSCLChiTietLoi u set u.warning = :isWarning where u.id in :proNSCLChiTietLoiIds")
    void updateWarning(@Param("proNSCLChiTietLoiIds") List<Long> proNSCLChiTietLoiIds, @Param("isWarning") Boolean isWarning);

    @Modifying
    @Transactional
    @Query(value = "delete from pro_nscl_sanpham where pro_nscl_chi_tiet_loi_id = :proNSCLChiTietLoiId", nativeQuery = true)
    void deleteNSCLSanphamByProCTLId(@Param("proNSCLChiTietLoiId") Long proNSCLChiTietLoiId);

}
