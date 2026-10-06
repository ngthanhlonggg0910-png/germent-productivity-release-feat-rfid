package vn.nvsoft.repository;
import vn.nvsoft.domain.PrefinalTheoGio;
import vn.nvsoft.domain.ProRefinal;
import vn.nvsoft.domain.nscl.BamAnLuong;
import vn.nvsoft.service.dto.ProRefinalTheoGioDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Spring Data  repository for the ProRefinal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProRefinalRepository extends JpaRepository<ProRefinal, Long> {

    @Query(nativeQuery = true, value = "EXEC GET_DATE")
    Date getCurrentDate();

    List<ProRefinal> findAllByProNSCLId(Long id);

    List<ProRefinal> findAllByProKHSXId(Long proKHSXId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC Pro_GetData_Refinal_Detail :chuyen")
    List<PrefinalTheoGio> getAllProRefinalTheoGios(@Param("chuyen") Long chuyen);
}


