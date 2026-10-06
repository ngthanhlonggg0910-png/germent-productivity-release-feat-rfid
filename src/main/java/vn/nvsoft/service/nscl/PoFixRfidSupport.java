package vn.nvsoft.service.nscl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.repository.ProNSCLRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Hỗ trợ sửa PO qua ngày cho sản phẩm có RFID (tracking {@code pro_nscl_sanpham}).
 * <p>
 * Nhận diện RFID: {@code displayname} không rỗng (cùng quy ước {@code countLoiWithRfidByProNsclId}).
 * Trạng thái: bản ghi mới nhất theo {@code displayname} trên từng {@code pro_nscl_id} phải là {@code LOI}.
 */
public final class PoFixRfidSupport {

    private static final Logger log = LoggerFactory.getLogger(PoFixRfidSupport.class);

    private PoFixRfidSupport() {
    }

    /**
     * Đếm LOI RFID chưa có SUA (latest state) trên pool nscl quá khứ.
     */
    public static int countUnmatchedLoiRfid(ProNSCLRepository repository, List<ProNSCL> pastRows) {
        List<Long> nsclIds = collectNsclIds(pastRows);
        if (nsclIds.isEmpty()) {
            return 0;
        }
        Long count = repository.countUnmatchedLoiRfidForPoFix(nsclIds);
        return count != null ? count.intValue() : 0;
    }

    /**
     * Insert tối đa {@code limit} dòng {@code SUA} (native INSERT, không dùng proc) trước khi cộng {@code sua_po_luy_ke}.
     *
     * @return số dòng đã insert (≤ limit, ≤ số LOI RFID khớp điều kiện)
     */
    public static int insertSuaForUnmatchedLoiRfid(
        ProNSCLRepository repository,
        List<ProNSCL> pastRows,
        int limit,
        String nguoiQuet
    ) {
        if (limit <= 0) {
            return 0;
        }
        List<Long> nsclIds = collectNsclIds(pastRows);
        if (nsclIds.isEmpty()) {
            log.debug("PoFix RFID: no past nscl ids — skip SUA insert");
            return 0;
        }
        int available = countUnmatchedLoiRfid(repository, pastRows);
        if (available == 0) {
            log.debug("PoFix RFID: no unmatched LOI RFID in pool — skip SUA insert");
            return 0;
        }
        int toInsert = Math.min(limit, available);
        int inserted = repository.insertPoFixSuaForUnmatchedLoiRfid(nsclIds, toInsert, nguoiQuet);
        log.info(
            "PoFix RFID: inserted {} SUA row(s) (requested limit={}, available LOI RFID={}) across {} nscl",
            inserted,
            limit,
            available,
            nsclIds.size()
        );
        return inserted;
    }

    private static List<Long> collectNsclIds(List<ProNSCL> pastRows) {
        List<Long> ids = new ArrayList<>();
        if (pastRows == null) {
            return ids;
        }
        for (ProNSCL row : pastRows) {
            if (row != null && row.getId() != null) {
                ids.add(row.getId());
            }
        }
        return ids;
    }
}
