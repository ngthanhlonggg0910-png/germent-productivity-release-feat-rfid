package vn.nvsoft.repository.egmf;

/**
 * SQL fragments cho {@code CUTTING_TemBarcode_TachCay}.
 * Giới hạn mapping theo prefix {@code Code} (tem barcode tách cây):
 * - BTP: {@code SoLopTrai} gốc
 * - BTH: {@code SoLopTrai} * số bàn may (distinct items trong {@code BanMay}, phân tách '-')
 *
 * Alias kết quả dùng {@code soLopTraiHieuLuc} / {@code tongSoTem} — tránh alias {@code soLopTrai}
 * vì Spring Data native projection dễ gắn nhầm giá trị cột gốc {@code SoLopTrai} trong CASE.
 */
public final class TemBarcodeTachCaySqlFragments {

    private TemBarcodeTachCaySqlFragments() {}

    public static final String SO_LOP_TRAI_T =
        "(CASE LEFT(t.Code, 3) " +
        "    WHEN 'BTP' THEN t.SoLopTrai " +
        "    WHEN 'BTH' THEN t.SoLopTrai * (SELECT COUNT(DISTINCT items) FROM eGMF.dbo.SplitString(t.BanMay, '-')) " +
        "END)";

    public static final String SO_LOP_TRAI_TC =
        "(CASE LEFT(tc.Code, 3) " +
        "    WHEN 'BTP' THEN tc.SoLopTrai " +
        "    WHEN 'BTH' THEN tc.SoLopTrai * (SELECT COUNT(DISTINCT items) FROM eGMF.dbo.SplitString(tc.BanMay, '-')) " +
        "END)";

    public static final String SO_LOP_TRAI_TC2 =
        "(CASE LEFT(tc2.Code, 3) " +
        "    WHEN 'BTP' THEN tc2.SoLopTrai " +
        "    WHEN 'BTH' THEN tc2.SoLopTrai * (SELECT COUNT(DISTINCT items) FROM eGMF.dbo.SplitString(tc2.BanMay, '-')) " +
        "END)";

    /**
     * Map HiPro {@code pro_nha_may.ten} → eGMF {@code TenPhanXuong} (ví dụ {@code Xí Nghiệp 01} → {@code XN1}).
     */
    public static final String TEN_PHAN_XUONG_FROM_NHAMAY = "'XN' + RIGHT(nhamay.ten, 1)";

    /**
     * Join ngữ cảnh KHSX đích khi đếm mapping (chuyền, xí nghiệp, PO, mã hàng).
     */
    public static final String KHSX_CNT_JOINS =
        "INNER JOIN pro_ke_hoach_san_xuat khsx_cnt ON khsx_cnt.id = :khsxId " +
        "INNER JOIN pro_chuyen chuyen_cnt ON khsx_cnt.pro_chuyen_id = chuyen_cnt.id " +
        "INNER JOIN pro_nha_may nhamay_cnt ON chuyen_cnt.pro_nha_may_id = nhamay_cnt.id " +
        "INNER JOIN pro_san_pham sp_cnt ON khsx_cnt.pro_san_pham_id = sp_cnt.id ";

    /**
     * Join KHSX của dòng mapping để khớp chuyền / xí nghiệp. Alias bảng mapping: {@code m}.
     */
    public static final String MAPPING_ROW_KHSX_JOINS =
        "INNER JOIN pro_ke_hoach_san_xuat khsx_m ON khsx_m.id = m.khsx_id " +
        "INNER JOIN pro_chuyen chuyen_m ON khsx_m.pro_chuyen_id = chuyen_m.id " +
        "INNER JOIN pro_nha_may nhamay_m ON chuyen_m.pro_nha_may_id = nhamay_m.id ";

    /**
     * Lọc mapping theo PO, mã hàng, chuyền, xí nghiệp cùng ngữ cảnh KHSX. Alias mapping: {@code m}.
     */
    public static final String MAPPING_KHSX_CONTEXT_WHERE =
        "  AND m.po = sp_cnt.po " +
        "  AND m.productcode = sp_cnt.ma_san_pham " +
        "  AND chuyen_m.id = chuyen_cnt.id " +
        "  AND nhamay_m.id = nhamay_cnt.id ";
}
