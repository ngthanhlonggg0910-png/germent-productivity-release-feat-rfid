package vn.nvsoft.service.egmf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.nvsoft.repository.egmf.TemBarcodeTachCayRepository;
import vn.nvsoft.service.viewmodel.TemBarcodeTachCayViewModel;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TemBarcodeTachCayServiceTest {

    private TemBarcodeTachCayRepository repository;
    private DecathlonRfidItemValidator decathlonRfidItemValidator;
    private WorkShiftGuard workShiftGuard;
    private TemBarcodeTachCayService service;

    @BeforeEach
    void setUp() {
        repository = mock(TemBarcodeTachCayRepository.class);
        decathlonRfidItemValidator = mock(DecathlonRfidItemValidator.class);
        workShiftGuard = mock(WorkShiftGuard.class);
        service = new TemBarcodeTachCayService(repository, decathlonRfidItemValidator, workShiftGuard);
        when(repository.findDistinctKhachHangByCode(anyString())).thenReturn(Collections.emptyList());
    }

    private void stubTree(String code, int soLopTrai, int soLuongDaMap) {
        TemBarcodeTachCayViewModel vm = mock(TemBarcodeTachCayViewModel.class);
        when(vm.getCode()).thenReturn(code);
        when(vm.getSoLopTraiHieuLuc()).thenReturn(soLopTrai);
        when(vm.getSoLuongDaMap()).thenReturn(soLuongDaMap);
        when(repository.findByCode(code)).thenReturn(Collections.singletonList(vm));
    }

    @Test
    void canMapMoreRfid_false_whenTongSlZero() {
        stubTree("BTP1-1", 15, 0);
        when(repository.findParentBarcodeByCode("BTP1-1")).thenReturn("BTP1");
        when(repository.getTongSLThucNhanByBarcodeAndKhsx("BTP1", 1L)).thenReturn(100);
        when(repository.countMappedRfidByParentBarcodeAndKhsx("BTP1", 1L)).thenReturn(0);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(0);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isFalse();
        assertThat(service.resolveCannotMapErrorCode("BTP1-1", 1L))
            .isEqualTo(TemBarcodeTachCayService.ERROR_BTP_CHUA_QUET);
    }

    @Test
    void canMapMoreRfid_false_whenParentMappedReachesParentTongSl() {
        stubTree("BTP1-1", 100, 0);
        when(repository.findParentBarcodeByCode("BTP1-1")).thenReturn("BTP1");
        when(repository.getTongSLThucNhanByBarcodeAndKhsx("BTP1", 1L)).thenReturn(85);
        when(repository.countMappedRfidByParentBarcodeAndKhsx("BTP1", 1L)).thenReturn(85);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isFalse();
        assertThat(service.resolveCannotMapErrorCode("BTP1-1", 1L))
            .isEqualTo(TemBarcodeTachCayService.ERROR_VUOT_TONG_BTP);
    }

    @Test
    void canMapMoreRfid_false_whenMappedReachesTongSl() {
        stubTree("BTP1-1", 100, 85);
        when(repository.findParentBarcodeByCode("BTP1-1")).thenReturn("BTP1");
        when(repository.getTongSLThucNhanByBarcodeAndKhsx("BTP1", 1L)).thenReturn(100);
        when(repository.countMappedRfidByParentBarcodeAndKhsx("BTP1", 1L)).thenReturn(10);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        when(repository.countMappedRfidByBarcodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isFalse();
        assertThat(service.resolveCannotMapErrorCode("BTP1-1", 1L))
            .isEqualTo(TemBarcodeTachCayService.ERROR_VUOT_SUMMARY_PO);
    }

    @Test
    void canMapMoreRfid_true_whenUnderBothCeilings() {
        stubTree("BTP1-1", 15, 5);
        when(repository.findParentBarcodeByCode("BTP1-1")).thenReturn("BTP1");
        when(repository.getTongSLThucNhanByBarcodeAndKhsx("BTP1", 1L)).thenReturn(100);
        when(repository.countMappedRfidByParentBarcodeAndKhsx("BTP1", 1L)).thenReturn(5);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        when(repository.countMappedRfidByBarcodeAndKhsx("BTP1-1", 1L)).thenReturn(5);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isTrue();
    }

    @Test
    void getQuantityLimitBtp_returnsTongSl() {
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        assertThat(service.getQuantityLimitBtp("BTP1-1", 1L)).isEqualTo(85);
    }
}
