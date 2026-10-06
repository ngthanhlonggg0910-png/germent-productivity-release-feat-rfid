package vn.nvsoft.service.egmf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.domain.ProChuyen;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.service.dto.ProGioLamViecDTO;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkShiftGuardTest {

    private ProGioLamViecService proGioLamViecService;
    private ProCheckPointService proCheckPointService;
    private ProNSCLRepository proNSCLRepository;
    private WorkShiftGuard guard;

    @BeforeEach
    void setUp() {
        proGioLamViecService = mock(ProGioLamViecService.class);
        proCheckPointService = mock(ProCheckPointService.class);
        proNSCLRepository = mock(ProNSCLRepository.class);
        guard = new WorkShiftGuard(proGioLamViecService, proCheckPointService, proNSCLRepository);
    }

    @Test
    void throwsWhenOutsideWorkShiftForCheckpoint() {
        ProCheckPoint checkpoint = new ProCheckPoint();
        ProChuyen chuyen = new ProChuyen();
        chuyen.setStt(15);
        checkpoint.setProChuyen(chuyen);
        when(proCheckPointService.findOneProCheckPoint(10L)).thenReturn(Optional.of(checkpoint));
        when(proGioLamViecService.getGioLamViecHienTai("15")).thenReturn(null);

        assertThatThrownBy(() -> guard.assertWithinWorkShift(10L, null))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage(WorkShiftGuard.MESSAGE_NGOAI_CA);
    }

    @Test
    void passesWhenInsideWorkShiftForCheckpoint() {
        ProCheckPoint checkpoint = new ProCheckPoint();
        ProChuyen chuyen = new ProChuyen();
        chuyen.setStt(15);
        checkpoint.setProChuyen(chuyen);
        when(proCheckPointService.findOneProCheckPoint(10L)).thenReturn(Optional.of(checkpoint));
        when(proGioLamViecService.getGioLamViecHienTai("15")).thenReturn(new ProGioLamViecDTO());

        assertThatCode(() -> guard.assertWithinWorkShift(10L, null))
            .doesNotThrowAnyException();
    }

    @Test
    void resolvesCheckpointFromNsclId() {
        ProCheckPoint checkpoint = new ProCheckPoint();
        checkpoint.setId(99L);
        ProNSCL nscl = new ProNSCL();
        nscl.setProCheckPoint(checkpoint);
        when(proNSCLRepository.findById(5L)).thenReturn(Optional.of(nscl));

        assertThatCode(() -> guard.resolveCheckPointId(null, 5L)).doesNotThrowAnyException();
    }
}
