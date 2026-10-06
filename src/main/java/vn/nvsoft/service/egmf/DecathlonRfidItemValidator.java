package vn.nvsoft.service.egmf;

import org.springframework.stereotype.Component;
import vn.nvsoft.repository.egmf.TemBarcodeTachCayRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Kiểm tra itemId RFID DECATHLON (catalog EAN) khớp Size_Item của mã cây BTP.
 */
@Component
public class DecathlonRfidItemValidator {

    public static final String ERROR_DECATHLON_EAN_NOT_FOUND = "decathlon_ean_not_found";
    public static final String ERROR_DECATHLON_TREE_ITEM_MISSING = "decathlon_tree_item_missing";
    public static final String ERROR_DECATHLON_ITEM_MISMATCH = "decathlon_item_mismatch";

    private final TemBarcodeTachCayRepository temBarcodeTachCayRepository;

    public DecathlonRfidItemValidator(TemBarcodeTachCayRepository temBarcodeTachCayRepository) {
        this.temBarcodeTachCayRepository = temBarcodeTachCayRepository;
    }

    /**
     * Ràng itemId khi map RFID (đã biết mã cây + PO).
     */
    public void validateItemMatch(String barcodeTachCay, String rfid, String po) {
        if (!isDecathlonTree(barcodeTachCay)) {
            return;
        }
        String gtin = RfidGs1Validator.extractGtin(rfid);
        if (gtin == null) {
            return;
        }
        String catalogItemId = lookupCatalogItemId(gtin);
        if (catalogItemId == null) {
            throw new IllegalStateException(
                "EAN " + displayEan(gtin) + " không tìm thấy trong Catalog DECATHLON");
        }
        validateAgainstTreeItems(barcodeTachCay, po, catalogItemId);
    }

    /**
     * Ràng itemId khi kiểm chất lượng bằng RFID (lấy mã cây + PO từ bản ghi mapping).
     */
    public void validateItemMatchForQualityCheck(String rfid) {
        if (rfid == null || rfid.trim().isEmpty()) {
            return;
        }
        TemBarcodeTachCayRepository.RfidMappingContextProjection mapping =
            temBarcodeTachCayRepository.findLatestMappingContextByRfid(rfid.trim());
        if (mapping == null || mapping.getBarcodeTachCay() == null || mapping.getBarcodeTachCay().trim().isEmpty()) {
            return;
        }
        validateItemMatch(mapping.getBarcodeTachCay(), rfid, mapping.getPo());
    }

    private boolean isDecathlonTree(String barcodeTachCay) {
        if (barcodeTachCay == null || barcodeTachCay.trim().isEmpty()) {
            return false;
        }
        List<String> names = temBarcodeTachCayRepository.findDistinctKhachHangByCode(barcodeTachCay.trim());
        return RfidGs1Validator.isDecathlonCustomer(names);
    }

    private String lookupCatalogItemId(String gtin14) {
        String trimmed = gtin14.trim();
        String itemId = temBarcodeTachCayRepository.findCatalogItemIdByEan(trimmed);
        if (itemId != null && !itemId.trim().isEmpty()) {
            return itemId.trim();
        }
        if (trimmed.startsWith("0") && trimmed.length() > 1) {
            itemId = temBarcodeTachCayRepository.findCatalogItemIdByEan(trimmed.substring(1));
            if (itemId != null && !itemId.trim().isEmpty()) {
                return itemId.trim();
            }
        }
        return null;
    }

    private void validateAgainstTreeItems(String barcodeTachCay, String po, String catalogItemId) {
        String normalizedPo = normalizePo(po);
        List<TemBarcodeTachCayRepository.DecathlonTreeItemProjection> contexts =
            temBarcodeTachCayRepository.findDecathlonTreeItemsByCode(barcodeTachCay, normalizedPo);

        if (contexts == null || contexts.isEmpty()) {
            throw new IllegalStateException("Mã cây " + barcodeTachCay + " không có thông tin lệnh sản xuất / PO");
        }

        List<TemBarcodeTachCayRepository.DecathlonTreeItemProjection> withItem = contexts.stream()
            .filter(c -> c.getSizeItem() != null && !c.getSizeItem().trim().isEmpty())
            .collect(Collectors.toList());

        if (withItem.isEmpty()) {
            throw new IllegalStateException(buildMissingItemMessage(contexts));
        }

        boolean matched = withItem.stream()
            .anyMatch(c -> catalogItemId.equalsIgnoreCase(c.getSizeItem().trim()));
        if (!matched) {
            String treeItem = withItem.get(0).getSizeItem().trim();
            throw new IllegalStateException(
                "Item cây " + treeItem + " với Item Catalog " + catalogItemId + " không khớp");
        }
    }

    private String buildMissingItemMessage(List<TemBarcodeTachCayRepository.DecathlonTreeItemProjection> contexts) {
        Set<String> messages = new LinkedHashSet<>();
        for (TemBarcodeTachCayRepository.DecathlonTreeItemProjection ctx : contexts) {
            String lsx = ctx.getLenhSanXuat() != null ? ctx.getLenhSanXuat().trim() : "";
            String po = ctx.getPo() != null ? ctx.getPo().trim() : "";
            messages.add("Lệnh sản xuất " + lsx + " - PO " + po + " không có Item");
        }
        return String.join("; ", messages);
    }

    private String normalizePo(String po) {
        if (po == null || po.trim().isEmpty()) {
            return null;
        }
        return po.trim();
    }

    private String displayEan(String gtin14) {
        if (gtin14.startsWith("0") && gtin14.length() > 1) {
            return gtin14.substring(1);
        }
        return gtin14;
    }

    public static String resolveErrorCode(String message) {
        if (message == null) {
            return null;
        }
        if (message.contains("không tìm thấy trong Catalog DECATHLON")) {
            return ERROR_DECATHLON_EAN_NOT_FOUND;
        }
        if (message.contains("không có Item")) {
            return ERROR_DECATHLON_TREE_ITEM_MISSING;
        }
        if (message.contains("không khớp")) {
            return ERROR_DECATHLON_ITEM_MISMATCH;
        }
        return null;
    }
}
