package vn.nvsoft.service.egmf;

import java.util.regex.Pattern;

/**
 * Kiểm tra format GS1 cho RFID sau khi decode: (01)GTIN(21)Serial
 */
public final class RfidGs1Validator {

    /** (01) + 14 chữ số GTIN + (21) + serial (≥ 1 chữ số) */
    private static final Pattern GS1_LOOSE = Pattern.compile("^\\(01\\)\\d{14}\\(21\\)\\d+$");

    /** (01) + 14 chữ số GTIN + (21) + serial đúng 12 chữ số — bắt buộc cho DECATHLON */
    private static final Pattern GS1_STRICT = Pattern.compile("^\\(01\\)\\d{14}\\(21\\)\\d{12}$");

    private RfidGs1Validator() {
    }

    public static boolean isValidGs1Loose(String rfid) {
        if (rfid == null) {
            return false;
        }
        return GS1_LOOSE.matcher(rfid.trim()).matches();
    }

    public static boolean isValidGs1Strict(String rfid) {
        if (rfid == null) {
            return false;
        }
        return GS1_STRICT.matcher(rfid.trim()).matches();
    }

    public static boolean isDecathlonCustomer(String khachHang) {
        if (khachHang == null || khachHang.trim().isEmpty()) {
            return false;
        }
        return khachHang.toUpperCase().contains("DECATHLON");
    }

    public static boolean isDecathlonCustomer(java.util.Collection<String> khachHangNames) {
        if (khachHangNames == null || khachHangNames.isEmpty()) {
            return false;
        }
        return khachHangNames.stream().anyMatch(RfidGs1Validator::isDecathlonCustomer);
    }

    /**
     * Trích GTIN (14 chữ số) giữa (01) và (21) trong chuỗi GS1.
     */
    public static String extractGtin(String rfid) {
        if (rfid == null) {
            return null;
        }
        java.util.regex.Matcher matcher = java.util.regex.Pattern
            .compile("^\\(01\\)(\\d{14})\\(21\\)")
            .matcher(rfid.trim());
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(1);
    }
}
