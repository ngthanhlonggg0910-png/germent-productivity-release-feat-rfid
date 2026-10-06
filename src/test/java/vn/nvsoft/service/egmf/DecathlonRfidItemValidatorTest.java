package vn.nvsoft.service.egmf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.nvsoft.repository.egmf.TemBarcodeTachCayRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DecathlonRfidItemValidatorTest {

    private TemBarcodeTachCayRepository repository;
    private DecathlonRfidItemValidator validator;

    @BeforeEach
    void setUp() {
        repository = mock(TemBarcodeTachCayRepository.class);
        validator = new DecathlonRfidItemValidator(repository);
    }

    @Test
    void skipsNonDecathlonCustomer() {
        when(repository.findDistinctKhachHangByCode("BTP1-1"))
            .thenReturn(Collections.singletonList("NIKE"));

        assertThatCode(() -> validator.validateItemMatch("BTP1-1", "(01)03608393569882(21)000000038939", "PO1"))
            .doesNotThrowAnyException();
    }

    @Test
    void throwsWhenCatalogEanMissing() {
        when(repository.findDistinctKhachHangByCode("BTP1-1"))
            .thenReturn(Collections.singletonList("DECATHLON"));
        when(repository.findCatalogItemIdByEan(any())).thenReturn(null);

        assertThatThrownBy(() -> validator.validateItemMatch("BTP1-1", "(01)03608393569882(21)000000038939", "PO1"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("không tìm thấy trong Catalog DECATHLON");
    }

    @Test
    void throwsWhenTreeItemMissing() {
        when(repository.findDistinctKhachHangByCode("BTP1-1"))
            .thenReturn(Collections.singletonList("DECATHLON"));
        when(repository.findCatalogItemIdByEan("03608393569882")).thenReturn("5678585");
        when(repository.findCatalogItemIdByEan("3608393569882")).thenReturn("5678585");

        TemBarcodeTachCayRepository.DecathlonTreeItemProjection ctx = mock(TemBarcodeTachCayRepository.DecathlonTreeItemProjection.class);
        when(ctx.getSizeItem()).thenReturn("   ");
        when(ctx.getPo()).thenReturn("4525042186");
        when(ctx.getLenhSanXuat()).thenReturn("TRANGDT-0813-2026");
        when(repository.findDecathlonTreeItemsByCode(eq("BTP1-1"), eq("4525042186")))
            .thenReturn(Collections.singletonList(ctx));

        assertThatThrownBy(() -> validator.validateItemMatch("BTP1-1", "(01)03608393569882(21)000000038939", "4525042186"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("không có Item");
    }

    @Test
    void throwsWhenItemMismatch() {
        when(repository.findDistinctKhachHangByCode("BTP1-1"))
            .thenReturn(Collections.singletonList("DECATHLON"));
        when(repository.findCatalogItemIdByEan("03608393569882")).thenReturn("5678585");

        TemBarcodeTachCayRepository.DecathlonTreeItemProjection ctx = mock(TemBarcodeTachCayRepository.DecathlonTreeItemProjection.class);
        when(ctx.getSizeItem()).thenReturn("5334689");
        when(ctx.getPo()).thenReturn("4525042186");
        when(ctx.getLenhSanXuat()).thenReturn("TRANGDT-0813-2026");
        when(repository.findDecathlonTreeItemsByCode(eq("BTP1-1"), eq("4525042186")))
            .thenReturn(Collections.singletonList(ctx));

        assertThatThrownBy(() -> validator.validateItemMatch("BTP1-1", "(01)03608393569882(21)000000038939", "4525042186"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("Item cây 5334689 với Item Catalog 5678585 không khớp");
    }

    @Test
    void passesWhenItemMatchesAnyTreeRow() {
        when(repository.findDistinctKhachHangByCode("BTP1-1"))
            .thenReturn(Collections.singletonList("DECATHLON"));
        when(repository.findCatalogItemIdByEan("03608393569882")).thenReturn("5334689");

        TemBarcodeTachCayRepository.DecathlonTreeItemProjection match = mock(TemBarcodeTachCayRepository.DecathlonTreeItemProjection.class);
        when(match.getSizeItem()).thenReturn("5334689");
        TemBarcodeTachCayRepository.DecathlonTreeItemProjection other = mock(TemBarcodeTachCayRepository.DecathlonTreeItemProjection.class);
        when(other.getSizeItem()).thenReturn("5334691");
        when(repository.findDecathlonTreeItemsByCode(eq("BTP1-1"), eq("4525042186")))
            .thenReturn(Arrays.asList(other, match));

        assertThatCode(() -> validator.validateItemMatch("BTP1-1", "(01)03608393569882(21)000000038939", "4525042186"))
            .doesNotThrowAnyException();
    }
}
