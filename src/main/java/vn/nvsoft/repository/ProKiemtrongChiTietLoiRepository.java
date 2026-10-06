package vn.nvsoft.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ProKiemtrongChiTietLoi;

@Repository
public interface ProKiemtrongChiTietLoiRepository extends JpaRepository<ProKiemtrongChiTietLoi, Long> {

    List<ProKiemtrongChiTietLoi> findByProCheckpointIdAndProKeHoachSanXuatIdOrderByThoiGianDesc(
        Long checkpointId, Long khsxId);
}
