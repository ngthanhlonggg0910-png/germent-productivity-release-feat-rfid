package vn.nvsoft.service.nscl;

import vn.nvsoft.domain.ProNSCL;

import java.util.List;

/**
 * FIFO trừ reject_* từ giờ 1h → 14h trên nhiều dòng nscl (đã sort theo ngay_tao).
 */
public final class NsclRejectFifo {

    private NsclRejectFifo() {
    }

    /**
     * @return số lượng đã trừ thành công (có thể &lt; delta nếu hết lỗi)
     */
    public static int decreaseRejectFifo(List<ProNSCL> sortedPastRows, int delta) {
        int remaining = delta;
        if (sortedPastRows == null || remaining <= 0) {
            return 0;
        }
        for (ProNSCL row : sortedPastRows) {
            while (remaining > 0) {
                int deducted = decreaseOneFromRow(row);
                if (deducted == 0) {
                    break;
                }
                remaining -= deducted;
            }
            if (remaining <= 0) {
                break;
            }
        }
        return delta - remaining;
    }

    private static int decreaseOneFromRow(ProNSCL row) {
        if (decreaseHour(row, 1)) {
            return 1;
        }
        if (decreaseHour(row, 2)) {
            return 1;
        }
        if (decreaseHour(row, 3)) {
            return 1;
        }
        if (decreaseHour(row, 4)) {
            return 1;
        }
        if (decreaseHour(row, 5)) {
            return 1;
        }
        if (decreaseHour(row, 6)) {
            return 1;
        }
        if (decreaseHour(row, 7)) {
            return 1;
        }
        if (decreaseHour(row, 8)) {
            return 1;
        }
        if (decreaseHour(row, 9)) {
            return 1;
        }
        if (decreaseHour(row, 10)) {
            return 1;
        }
        if (decreaseHour(row, 11)) {
            return 1;
        }
        if (decreaseHour(row, 12)) {
            return 1;
        }
        if (decreaseHour(row, 13)) {
            return 1;
        }
        if (decreaseHour(row, 14)) {
            return 1;
        }
        return 0;
    }

    private static boolean decreaseHour(ProNSCL row, int hour) {
        switch (hour) {
            case 1:
                if (nullToZero(row.getReject1h()) > 0) {
                    row.setReject1h(row.getReject1h() - 1);
                    return true;
                }
                break;
            case 2:
                if (nullToZero(row.getReject2h()) > 0) {
                    row.setReject2h(row.getReject2h() - 1);
                    return true;
                }
                break;
            case 3:
                if (nullToZero(row.getReject3h()) > 0) {
                    row.setReject3h(row.getReject3h() - 1);
                    return true;
                }
                break;
            case 4:
                if (nullToZero(row.getReject4h()) > 0) {
                    row.setReject4h(row.getReject4h() - 1);
                    return true;
                }
                break;
            case 5:
                if (nullToZero(row.getReject5h()) > 0) {
                    row.setReject5h(row.getReject5h() - 1);
                    return true;
                }
                break;
            case 6:
                if (nullToZero(row.getReject6h()) > 0) {
                    row.setReject6h(row.getReject6h() - 1);
                    return true;
                }
                break;
            case 7:
                if (nullToZero(row.getReject7h()) > 0) {
                    row.setReject7h(row.getReject7h() - 1);
                    return true;
                }
                break;
            case 8:
                if (nullToZero(row.getReject8h()) > 0) {
                    row.setReject8h(row.getReject8h() - 1);
                    return true;
                }
                break;
            case 9:
                if (nullToZero(row.getReject9h()) > 0) {
                    row.setReject9h(row.getReject9h() - 1);
                    return true;
                }
                break;
            case 10:
                if (nullToZero(row.getReject10h()) > 0) {
                    row.setReject10h(row.getReject10h() - 1);
                    return true;
                }
                break;
            case 11:
                if (nullToZero(row.getReject11h()) > 0) {
                    row.setReject11h(row.getReject11h() - 1);
                    return true;
                }
                break;
            case 12:
                if (nullToZero(row.getReject12h()) > 0) {
                    row.setReject12h(row.getReject12h() - 1);
                    return true;
                }
                break;
            case 13:
                if (nullToZero(row.getReject13h()) > 0) {
                    row.setReject13h(row.getReject13h() - 1);
                    return true;
                }
                break;
            case 14:
                if (nullToZero(row.getReject14h()) > 0) {
                    row.setReject14h(row.getReject14h() - 1);
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    private static int nullToZero(Integer value) {
        return value != null ? value : 0;
    }
}
