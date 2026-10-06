package vn.nvsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ProNsclLog;

import java.util.Optional;

@Repository
public interface ProNsclLogRepository extends JpaRepository<ProNsclLog, Long> {

    Optional<ProNsclLog> findByCheckPointIdAndKhsxId(Long checkPointId, Long khsxId);
}
