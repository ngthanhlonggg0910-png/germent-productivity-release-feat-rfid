package vn.nvsoft.repository;
import vn.nvsoft.domain.ProKeHoachSanXuat;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

/**
 * Spring Data  repository for the ProKeHoachSanXuat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProKeHoachSanXuatRepository extends JpaRepository<ProKeHoachSanXuat, Long>, JpaSpecificationExecutor<ProKeHoachSanXuat> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE ProKeHoachSanXuat k SET k.isActived = false WHERE k.proChuyen.id = :chuyenId AND k.ngaySanXuat >= :dayStart AND k.ngaySanXuat < :dayEnd")
    int deactivateForChuyenAndNgayWindow(@Param("chuyenId") Long chuyenId, @Param("dayStart") Instant dayStart, @Param("dayEnd") Instant dayEnd);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE ProKeHoachSanXuat k SET k.isActived = true, k.activeTime = :activeTime WHERE k.id = :id")
    int activateById(@Param("id") Long id, @Param("activeTime") Instant activeTime);

    @Query("SELECT k FROM ProKeHoachSanXuat k LEFT JOIN FETCH k.proChuyen LEFT JOIN FETCH k.proSanPham WHERE k.id = :id")
    Optional<ProKeHoachSanXuat> findOneWithDetails(@Param("id") Long id);
}
