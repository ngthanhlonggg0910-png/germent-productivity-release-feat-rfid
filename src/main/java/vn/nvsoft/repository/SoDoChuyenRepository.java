package vn.nvsoft.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ChuyenTramSound;
import vn.nvsoft.domain.NVButton;


/**
 * Spring Data  repository for the NVButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoDoChuyenRepository extends JpaRepository<NVButton, Long> {

    @Query(value = "EXEC Andon_LayThongTinNutBam :nv_button_id", nativeQuery = true)
    Optional<ChuyenTramSound> findChuyenTramSoundByButton(@Param("nv_button_id") Long buttonId);

}
