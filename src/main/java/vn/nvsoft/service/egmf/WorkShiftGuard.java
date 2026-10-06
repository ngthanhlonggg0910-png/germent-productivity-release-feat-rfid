package vn.nvsoft.service.egmf;

import org.springframework.stereotype.Component;
import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.service.dto.ProGioLamViecDTO;

import java.util.Optional;

/**
 * Kiểm tra thời điểm hiện tại có nằm trong ca làm việc hay không.
 */
@Component
public class WorkShiftGuard {

    public static final String MESSAGE_NGOAI_CA = "Ngoài ca làm việc";
    public static final String ERROR_CODE = "ngoai_ca_lam_viec";

    private final ProGioLamViecService proGioLamViecService;
    private final ProCheckPointService proCheckPointService;
    private final ProNSCLRepository proNSCLRepository;

    public WorkShiftGuard(ProGioLamViecService proGioLamViecService,
                          ProCheckPointService proCheckPointService,
                          ProNSCLRepository proNSCLRepository) {
        this.proGioLamViecService = proGioLamViecService;
        this.proCheckPointService = proCheckPointService;
        this.proNSCLRepository = proNSCLRepository;
    }

    public Long resolveCheckPointId(Long proCheckPointId, Long nsclId) {
        if (proCheckPointId != null) {
            return proCheckPointId;
        }
        if (nsclId == null) {
            return null;
        }
        return proNSCLRepository.findById(nsclId)
            .map(ProNSCL::getProCheckPoint)
            .map(ProCheckPoint::getId)
            .orElse(null);
    }

    public boolean isWithinWorkShift(Long proCheckPointId, Long nsclId) {
        return findCurrentWorkShift(proCheckPointId, nsclId) != null;
    }

    public void assertWithinWorkShift(Long proCheckPointId, Long nsclId) {
        if (findCurrentWorkShift(proCheckPointId, nsclId) == null) {
            throw new IllegalStateException(MESSAGE_NGOAI_CA);
        }
    }

    private ProGioLamViecDTO findCurrentWorkShift(Long proCheckPointId, Long nsclId) {
        Long checkpointId = resolveCheckPointId(proCheckPointId, nsclId);
        if (checkpointId != null) {
            return getGioLamViecHienTaiCuaChuyen(checkpointId);
        }
        return proGioLamViecService.getGioLamViecHienTai();
    }

    private ProGioLamViecDTO getGioLamViecHienTaiCuaChuyen(Long proCheckPointId) {
        Optional<ProCheckPoint> checkPoint = proCheckPointService.findOneProCheckPoint(proCheckPointId);
        if (!checkPoint.isPresent() || checkPoint.get().getProChuyen() == null) {
            return null;
        }
        String chuyen = checkPoint.get().getProChuyen().getStt().toString();
        return proGioLamViecService.getGioLamViecHienTai(chuyen);
    }
}
