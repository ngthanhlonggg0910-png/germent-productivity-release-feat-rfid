package vn.nvsoft.service.nscl;

import vn.nvsoft.domain.ProNSCL;

import java.util.List;

/**
 * Tổng hợp metric lũy kế từ danh sách {@link ProNSCL} (pool query 2).
 */
public final class NsclMetricsAggregator {

    private NsclMetricsAggregator() {
    }

    public static final class Metrics {
        private int totalQty;
        private int totalFix;
        private int totalReject;
        private int totalSuaPo;

        public int getTotalQty() {
            return totalQty;
        }

        public int getTotalFix() {
            return totalFix;
        }

        public int getTotalReject() {
            return totalReject;
        }

        public int getTotalSuaPo() {
            return totalSuaPo;
        }

        /** Sản phẩm OK: Qty + Fix + sua_po_luy_ke */
        public int getTongMayRa() {
            return totalQty + totalFix + totalSuaPo;
        }

        /** Giới hạn tổng bấm: Qty + Fix + Reject + sua_po_luy_ke */
        public int getTongBam() {
            return totalQty + totalFix + totalReject + totalSuaPo;
        }
    }

    public static Metrics aggregate(List<ProNSCL> rows) {
        Metrics m = new Metrics();
        if (rows == null || rows.isEmpty()) {
            return m;
        }
        for (ProNSCL row : rows) {
            m.totalQty += sumQty(row);
            m.totalFix += sumFix(row);
            m.totalReject += sumReject(row);
            m.totalSuaPo += nullToZero(row.getSuaPoLuyKe());
        }
        return m;
    }

    public static int sumReject(ProNSCL row) {
        if (row == null) {
            return 0;
        }
        return nullToZero(row.getReject1h()) + nullToZero(row.getReject2h()) + nullToZero(row.getReject3h())
            + nullToZero(row.getReject4h()) + nullToZero(row.getReject5h()) + nullToZero(row.getReject6h())
            + nullToZero(row.getReject7h()) + nullToZero(row.getReject8h()) + nullToZero(row.getReject9h())
            + nullToZero(row.getReject10h()) + nullToZero(row.getReject11h()) + nullToZero(row.getReject12h())
            + nullToZero(row.getReject13h()) + nullToZero(row.getReject14h());
    }

    private static int sumQty(ProNSCL row) {
        return nullToZero(row.getQty1h()) + nullToZero(row.getQty2h()) + nullToZero(row.getQty3h())
            + nullToZero(row.getQty4h()) + nullToZero(row.getQty5h()) + nullToZero(row.getQty6h())
            + nullToZero(row.getQty7h()) + nullToZero(row.getQty8h()) + nullToZero(row.getQty9h())
            + nullToZero(row.getQty10h()) + nullToZero(row.getQty11h()) + nullToZero(row.getQty12h())
            + nullToZero(row.getQty13h()) + nullToZero(row.getQty14h());
    }

    private static int sumFix(ProNSCL row) {
        return nullToZero(row.getFix1h()) + nullToZero(row.getFix2h()) + nullToZero(row.getFix3h())
            + nullToZero(row.getFix4h()) + nullToZero(row.getFix5h()) + nullToZero(row.getFix6h())
            + nullToZero(row.getFix7h()) + nullToZero(row.getFix8h()) + nullToZero(row.getFix9h())
            + nullToZero(row.getFix10h()) + nullToZero(row.getFix11h()) + nullToZero(row.getFix12h())
            + nullToZero(row.getFix13h()) + nullToZero(row.getFix14h());
    }

    private static int nullToZero(Integer value) {
        return value != null ? value : 0;
    }
}
