package vn.nvsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ProKiemKiemVuot;

import java.util.Optional;


/**
 * Spring Data  repository for the ProKiemKiemVuot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProKiemKiemVuotRepository extends JpaRepository<ProKiemKiemVuot, Long> {

    Optional<ProKiemKiemVuot> findOneByChuyenIdAndProSanPhamId(Long proChuyenId, Long proSanPhamId);
}
