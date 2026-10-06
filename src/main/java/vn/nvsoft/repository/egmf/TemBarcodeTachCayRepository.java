package vn.nvsoft.repository.egmf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.service.viewmodel.TemBarcodeTachCayViewModel;

import java.util.List;

/**
 * Repository để query cross-database từ eGMF
 * Sử dụng eGMF.dbo.<table_name> để query sang database eGMF
 */
@Repository
public interface TemBarcodeTachCayRepository extends JpaRepository<ProTaiKhoanCheckPoint, Long> {

    String SO_LOP_TRAI_T = TemBarcodeTachCaySqlFragments.SO_LOP_TRAI_T;
    String SO_LOP_TRAI_TC = TemBarcodeTachCaySqlFragments.SO_LOP_TRAI_TC;
    String SO_LOP_TRAI_TC2 = TemBarcodeTachCaySqlFragments.SO_LOP_TRAI_TC2;
    String KHSX_CNT_JOINS = TemBarcodeTachCaySqlFragments.KHSX_CNT_JOINS;
    String MAPPING_ROW_KHSX_JOINS = TemBarcodeTachCaySqlFragments.MAPPING_ROW_KHSX_JOINS;
    String MAPPING_KHSX_CONTEXT_WHERE = TemBarcodeTachCaySqlFragments.MAPPING_KHSX_CONTEXT_WHERE;

    /**
     * Lấy thông tin barcode mã cây theo Code
     * Bao gồm số lượng RFID đã mapping
     */
    @Query(nativeQuery = true, value = 
        "SELECT " +
        "   t.Id as id, " +
        "   t.Barcode as barcode, " +
        "   t.RFID as rfid, " +
        "   t.RFID_Hex as rfidHex, " +
        "   t.TKId as tkId, " +
        "   t.NguoiMap as nguoiMap, " +
        "   t.ThoiGianMap as thoiGianMap, " +
        "   t.NgayTao as ngayTao, " +
        "   t.LenhSanXuat as lenhSanXuat, " +
        "   t.MaHang as maHang, " +
        "   t.LoaiNguyenLieu as loaiNguyenLieu, " +
        "   t.NguoiCat as nguoiCat, " +
        "   t.NguoiTrai as nguoiTrai, " +
        "   t.AnhMau_Phieu as anhMauPhieu, " +
        "   t.TenPhongBan as tenPhongBan, " +
        "   t.Mua as mua, " +
        "   t.BanMay as banMay, " +
        "   t.TenSize as tenSize, " +
        "   t.IdBanMay as idBanMay, " +
        "   t.PhieuHoachToanId as phieuHoachToanId, " +
        "   t.Dan as dan, " +
        "   t.SoLuong as soLuong, " +
        "   t.TenChiTiet as tenChiTiet, " +
        "   t.PO as po, " +
        "   t.Part as part, " +
        "   t.STT as stt, " +
        "   t.MaCay as maCay, " +
        "   " + SO_LOP_TRAI_T + " as soLopTraiHieuLuc, " +
        "   t.Lot as lot, " +
        "   t.AnhMau as anhMau, " +
        "   t.NgayCapNhat as ngayCapNhat, " +
        "   t.Code as code, " +
        "   ISNULL((SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m WHERE m.BarcodeTachCay = t.Code), 0) as soLuongDaMap " +
        "FROM eGMF.dbo.CUTTING_TemBarcode_TachCay t " +
        "WHERE t.Code = :code OR t.Barcode = :code")
    List<TemBarcodeTachCayViewModel> findByCode(@Param("code") String code);

    /**
     * Lấy thông tin barcode mã cây theo Code và PO (và mã hàng)
     * Bao gồm số lượng RFID đã mapping và kiểm tra PO có khớp không
     * Join qua CUTTING_PhieuCapBTP_BarcodeChiTiet để kiểm tra PO
     * và join CUTTING_PhieuCapBTP để lấy/kiểm tra đúng mã hàng (ProductCode)
     */
    @Query(nativeQuery = true, value = 
        "SELECT DISTINCT " +
        "   tc.Id as id, " +
        "   tc.Barcode as barcode, " +
        "   tc.RFID as rfid, " +
        "   tc.RFID_Hex as rfidHex, " +
        "   tc.TKId as tkId, " +
        "   tc.NguoiMap as nguoiMap, " +
        "   tc.ThoiGianMap as thoiGianMap, " +
        "   tc.NgayTao as ngayTao, " +
        "   tc.LenhSanXuat as lenhSanXuat, " +
        "   tc.MaHang as maHang, " +
        "   tc.LoaiNguyenLieu as loaiNguyenLieu, " +
        "   tc.NguoiCat as nguoiCat, " +
        "   tc.NguoiTrai as nguoiTrai, " +
        "   tc.AnhMau_Phieu as anhMauPhieu, " +
        "   tc.TenPhongBan as tenPhongBan, " +
        "   tc.Mua as mua, " +
        "   tc.BanMay as banMay, " +
        "   tc.TenSize as tenSize, " +
        "   tc.IdBanMay as idBanMay, " +
        "   tc.PhieuHoachToanId as phieuHoachToanId, " +
        "   tc.Dan as dan, " +
        "   tc.SoLuong as soLuong, " +
        "   tc.TenChiTiet as tenChiTiet, " +
        "   tc.PO as po, " +
        "   tc.Part as part, " +
        "   tc.STT as stt, " +
        "   tc.MaCay as maCay, " +
        "   " + SO_LOP_TRAI_TC + " as soLopTraiHieuLuc, " +
        "   tc.Lot as lot, " +
        "   tc.AnhMau as anhMau, " +
        "   tc.NgayCapNhat as ngayCapNhat, " +
        "   tc.Code as code, " +
        "   cap.ProductCode as productCode, " +
        "   ISNULL((SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m WHERE m.BarcodeTachCay = tc.Code), 0) as soLuongDaMap " +
        "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
        "INNER JOIN eGMF.dbo.CUTTING_TemBarcode_TachCay tc ON bcct.TemBarcodeBTP = tc.Barcode " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON cap.SoPhieuCapBTP = bcct.SoPhieuCapBTP " +
        "WHERE bcct.PO = :po AND (tc.Code = :code OR tc.Barcode = :code) " +
        "AND (:mahang IS NULL OR LTRIM(RTRIM(:mahang)) = '' " +
        "     OR cap.ProductCode COLLATE Latin1_General_CI_AI = LTRIM(RTRIM(:mahang)) COLLATE Latin1_General_CI_AI)")
    List<TemBarcodeTachCayViewModel> findByCodeAndPo(@Param("code") String code, @Param("po") String po, @Param("mahang") String mahang);

    /**
     * Projection cho danh sách PO kèm mã hàng (ProductCode) theo mã cây.
     */
    public static interface PoMaHangProjection {
        String getPo();
        String getProductCode();
    }

    /**
     * Danh sách PO (kèm mã hàng) gắn với mã cây (Code), qua phiếu cấp BTP và tem BTP.
     * Join CUTTING_PhieuCapBTP để lấy thêm mã hàng (ProductCode) cho từng PO.
     *
     * @param code Mã cây (CUTTING_TemBarcode_TachCay.Code)
     * @return Danh sách (PO, ProductCode) khác nhau gắn với mã cây
     */
    @Query(
        nativeQuery = true,
        value =
            "SELECT DISTINCT a.PO as po, cap.ProductCode as productCode " +
            "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet a " +
            "INNER JOIN eGMF.dbo.CUTTING_TemBarcode_TachCay b ON a.TemBarcodeBTP = b.Barcode " +
            "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON cap.SoPhieuCapBTP = a.SoPhieuCapBTP " +
            "WHERE b.Code = :code"
    )
    List<PoMaHangProjection> findDistinctPoByCode(@Param("code") String code);

    interface DecathlonTreeItemProjection {
        String getSizeItem();
        String getPo();
        String getLenhSanXuat();
    }

    interface RfidMappingContextProjection {
        String getBarcodeTachCay();
        String getPo();
    }

    /**
     * Size_Item (itemId cây) theo mã cây BTP, join LSX/PO Bravo.
     */
    @Query(nativeQuery = true, value =
        "SELECT DISTINCT " +
        "  LTRIM(RTRIM(p.Size_Item)) AS sizeItem, " +
        "  bcct.PO AS po, " +
        "  cap.LenhSanXuat AS lenhSanXuat " +
        "FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct ON tc.Barcode = bcct.TemBarcodeBTP AND ISNULL(bcct.TraBTP, 0) = 0 " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON cap.SoPhieuCapBTP = bcct.SoPhieuCapBTP " +
        "INNER JOIN eGMF.dbo.Bravo_LenhSanXuat_Detail dt ON dt.DocNo = cap.LenhSanXuat " +
        "INNER JOIN eGMF.dbo.Bravo_LenhSanXuat_Detail_PO p ON p.IdDetail = dt.Id AND p.PO = bcct.PO " +
        "WHERE tc.Code = :code " +
        "  AND (:po IS NULL OR LTRIM(RTRIM(:po)) = '' OR bcct.PO = :po)")
    List<DecathlonTreeItemProjection> findDecathlonTreeItemsByCode(
        @Param("code") String code,
        @Param("po") String po);

    /**
     * Tra itemId catalog DECATHLON theo EAN/GTIN.
     */
    @Query(nativeQuery = true, value =
        "SELECT TOP 1 LTRIM(RTRIM(itemId)) " +
        "FROM eGMF.dbo.Decathlon_RFID_ThirdNum_Catalog " +
        "WHERE LTRIM(RTRIM(ean)) = LTRIM(RTRIM(:ean))")
    String findCatalogItemIdByEan(@Param("ean") String ean);

    /**
     * Mã cây + PO gắn với RFID đã map (mới nhất).
     */
    @Query(nativeQuery = true, value =
        "SELECT TOP 1 BarcodeTachCay AS barcodeTachCay, po AS po " +
        "FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE :rfid IN (RFID, RFID_Barcode, RFID_Hex) " +
        "ORDER BY ThoiGianMap DESC")
    RfidMappingContextProjection findLatestMappingContextByRfid(@Param("rfid") String rfid);

    /**
     * Đếm số lượng RFID đã mapping cho một mã cây
     */
    @Query(nativeQuery = true, value = 
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE BarcodeTachCay = :barcodeTachCay")
    Integer countMappedRfidByBarcode(@Param("barcodeTachCay") String barcodeTachCay);

    /**
     * Đếm số lượng RFID đã mapping theo kế hoạch sản xuất (khsx_id)
     */
    @Query(nativeQuery = true, value =
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE khsx_id = :khsxId")
    Integer countMappedRfidByKhsxId(@Param("khsxId") Long khsxId);

    /**
     * Tổng số lượng thực nhận tem BTP ({@code TongSLThucNhan}) theo mã cây + KHSX.
     * Join HiPro KHSX → eGMF phiếu cấp BTP; lọc TraBTP=0, ProductCode/TenCum/PO/TenPhanXuong + Code.
     */
    @Query(nativeQuery = true, value =
        "SELECT SUM(ISNULL(bcct.SoLuongDaNhan, 0)) " +
        "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP " +
        "INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = :khsxId " +
        "INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
        "INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
        "INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
        "WHERE ISNULL(bcct.TraBTP, 0) = 0 " +
        "  AND cap.ProductCode = sp.ma_san_pham " +
        "  AND cap.TenCum = chuyen.ten " +
        "  AND bcct.PO = sp.po " +
        "  AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1) " +
        "  AND EXISTS ( " +
        "      SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc " +
        "      WHERE tc.Barcode = bcct.TemBarcodeBTP " +
        "        AND tc.Code = :code " +
        "  )")
    Integer getTongSLThucNhanByCodeAndKhsx(@Param("code") String code, @Param("khsxId") Long khsxId);

    /**
     * Tổng số lượng thực nhận tem tổng BTP ({@code TongSLThucNhan}) theo Barcode gốc + KHSX.
     * Lọc theo {@code bcct.TemBarcodeBTP} (không lọc Code mã cây tách).
     */
    @Query(nativeQuery = true, value =
        "SELECT SUM(ISNULL(bcct.SoLuongDaNhan, 0)) " +
        "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP " +
        "INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = :khsxId " +
        "INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
        "INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
        "INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
        "WHERE ISNULL(bcct.TraBTP, 0) = 0 " +
        "  AND cap.ProductCode = sp.ma_san_pham " +
        "  AND cap.TenCum = chuyen.ten " +
        "  AND bcct.PO = sp.po " +
        "  AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1) " +
        "  AND bcct.TemBarcodeBTP = :barcodeBtp")
    Integer getTongSLThucNhanByBarcodeAndKhsx(@Param("barcodeBtp") String barcodeBtp, @Param("khsxId") Long khsxId);

    /**
     * Barcode tem tổng BTP (gốc) theo mã cây tách.
     */
    @Query(nativeQuery = true, value =
        "SELECT TOP 1 Barcode FROM eGMF.dbo.CUTTING_TemBarcode_TachCay WHERE Code = :code")
    String findParentBarcodeByCode(@Param("code") String code);

    /**
     * Đếm RFID đã mapping theo mã cây + ngữ cảnh KHSX (chuyền, xí nghiệp, PO, mã hàng).
     */
    @Query(nativeQuery = true, value =
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m " +
        KHSX_CNT_JOINS +
        MAPPING_ROW_KHSX_JOINS +
        "WHERE m.BarcodeTachCay = :barcodeTachCay " +
        MAPPING_KHSX_CONTEXT_WHERE)
    Integer countMappedRfidByBarcodeAndKhsx(
        @Param("barcodeTachCay") String barcodeTachCay,
        @Param("khsxId") Long khsxId);

    /**
     * Đếm tổng RFID đã mapping trên tất cả mã cây tách thuộc cùng tem tổng BTP + ngữ cảnh KHSX.
     */
    @Query(nativeQuery = true, value =
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m " +
        "INNER JOIN eGMF.dbo.CUTTING_TemBarcode_TachCay tc ON m.BarcodeTachCay = tc.Code " +
        KHSX_CNT_JOINS +
        MAPPING_ROW_KHSX_JOINS +
        "WHERE tc.Barcode = :barcodeBtp " +
        MAPPING_KHSX_CONTEXT_WHERE)
    Integer countMappedRfidByParentBarcodeAndKhsx(
        @Param("barcodeBtp") String barcodeBtp,
        @Param("khsxId") Long khsxId);

    /**
     * Kiểm tra RFID đã được mapping chưa
     */
    @Query(nativeQuery = true, value = 
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE RFID = :rfid")
    Integer countByRfid(@Param("rfid") String rfid);

    /**
     * Kiểm tra RFID đã được mapping chưa (match theo RFID / RFID_Barcode / RFID_Hex)
     */
    @Query(nativeQuery = true, value =
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE :rfid IN (RFID, RFID_Barcode, RFID_Hex)")
    Integer countByAnyRfid(@Param("rfid") String rfid);

    /**
     * Lấy thông tin mã cây đã mapping với RFID
     * @param rfid RFID cần kiểm tra
     * @return Mã cây (Code) đã mapping, null nếu chưa mapping
     */
    @Query(nativeQuery = true, value = 
        "SELECT TOP 1 BarcodeTachCay FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE :rfid in (RFID, RFID_Barcode, RFID_Hex) " +
        "ORDER BY ThoiGianMap DESC")
    String findBarcodeTachCayByRfid(@Param("rfid") String rfid);

    /**
     * Kiểm tra mã cây có tồn tại không
     */
    @Query(nativeQuery = true, value = 
        "SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay " +
        "WHERE Code = :code")
    Integer existsByCode(@Param("code") String code);

    /**
     * Lấy danh sách tên khách hàng (distinct) theo mã cây (Code).
     * Join qua phiếu cấp BTP và lệnh sản xuất Bravo.
     */
    @Query(nativeQuery = true, value =
        "SELECT DISTINCT ISNULL(kh.TenKhachHangNew, kh.TenNgan) as khachHang " +
        "FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct ON tc.Barcode = bcct.TemBarcodeBTP AND ISNULL(bcct.TraBTP, 0) = 0 " +
        "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON cap.SoPhieuCapBTP = bcct.SoPhieuCapBTP " +
        "INNER JOIN eGMF.dbo.Bravo_LenhSanXuat_Detail lsx ON lsx.DocNo = cap.LenhSanXuat " +
        "INNER JOIN eGMF.dbo.Lib_KhachHang kh ON kh.MaKhachHang = lsx.CustomerCode " +
        "WHERE tc.Code = :code")
    List<String> findDistinctKhachHangByCode(@Param("code") String code);

    /**
     * Insert mapping RFID với barcode mã cây
     */
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(nativeQuery = true, value = 
        "INSERT INTO eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "(RFID_Barcode, BarcodeTachCay, RFID, RFID_Hex, NguoiMap, ThoiGianMap, TKId, Code_RFID, Code_RFID_Hex, khsx_id, nscl_id, po, productcode) " +
        "VALUES (:rfidBarcode, :barcodeTachCay, :rfid, :rfidHex, :nguoiMap, GETDATE(), :tkId, :codeRfid, :codeRfidHex, :khsxId, :nsclId, :po, :productcode)")
    int insertMapping(
        @Param("rfidBarcode") String rfidBarcode,
        @Param("barcodeTachCay") String barcodeTachCay,
        @Param("rfid") String rfid,
        @Param("rfidHex") String rfidHex,
        @Param("nguoiMap") String nguoiMap,
        @Param("tkId") Long tkId,
        @Param("codeRfid") String codeRfid,
        @Param("codeRfidHex") String codeRfidHex,
        @Param("khsxId") Long khsxId,
        @Param("nsclId") Long nsclId,
        @Param("po") String po,
        @Param("productcode") String productcode
    );

    /**
     * Insert mapping theo cách atomic để tránh race condition vượt giới hạn:
     * - Khóa row mã cây (UPDLOCK, HOLDLOCK) khi đọc SoLopTrai hiệu lực (BTP/BTH)
     * - Khóa tập mapping theo mã cây khi count
     * - Chỉ insert khi count < SoLopTrai hiệu lực và RFID chưa tồn tại (theo RFID/RFID_Barcode/RFID_Hex)
     * - Trần tem tổng BTP: tổng count mapping các mã cây cùng Barcode &lt; TongSLThucNhan (theo TemBarcodeBTP + KHSX), TongSL &gt; 0
     * - Trần BTP theo mã cây: count theo Code &lt; TongSLThucNhan (phiếu cấp BTP + KHSX), TongSL &gt; 0
     *
     * Kết quả:
     * - 1: insert thành công
     * - 0: không insert (hết slot mã cây / vượt TongSL tem tổng / vượt TongSL mã cây / chưa nhận BTP / RFID đã tồn tại / mã cây không tồn tại)
     */
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(nativeQuery = true, value =
        "INSERT INTO eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "    (RFID_Barcode, BarcodeTachCay, RFID, RFID_Hex, NguoiMap, ThoiGianMap, TKId, Code_RFID, Code_RFID_Hex, khsx_id, nscl_id, po, productcode) " +
        "SELECT " +
        "    :rfidBarcode, :barcodeTachCay, :rfid, :rfidHex, :nguoiMap, GETDATE(), :tkId, :codeRfid, :codeRfidHex, :khsxId, :nsclId, :po, :productcode " +
        "WHERE " +
        "    EXISTS (SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc WITH (UPDLOCK, HOLDLOCK) WHERE tc.Code = :barcodeTachCay) " +
        "    AND NOT EXISTS ( " +
        "        SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m WITH (UPDLOCK, HOLDLOCK) " +
        "        WHERE :rfid IN (m.RFID, m.RFID_Barcode, m.RFID_Hex) " +
        "    ) " +
        "    AND ( " +
        "        SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m2 WITH (UPDLOCK, HOLDLOCK) " +
        "        WHERE m2.BarcodeTachCay = :barcodeTachCay " +
        "    ) < ( " +
        "        SELECT ISNULL(" + SO_LOP_TRAI_TC2 + ", 0) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc2 WITH (UPDLOCK, HOLDLOCK) " +
        "        WHERE tc2.Code = :barcodeTachCay " +
        "    ) " +
        "    AND ( " +
        "        SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m_parent WITH (UPDLOCK, HOLDLOCK) " +
        "        INNER JOIN eGMF.dbo.CUTTING_TemBarcode_TachCay tc_parent WITH (UPDLOCK, HOLDLOCK) ON m_parent.BarcodeTachCay = tc_parent.Code " +
        KHSX_CNT_JOINS +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx_m ON khsx_m.id = m_parent.khsx_id " +
        "        INNER JOIN pro_chuyen chuyen_m ON khsx_m.pro_chuyen_id = chuyen_m.id " +
        "        INNER JOIN pro_nha_may nhamay_m ON chuyen_m.pro_nha_may_id = nhamay_m.id " +
        "        WHERE tc_parent.Barcode = ( " +
        "            SELECT TOP 1 tc_bar.Barcode FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc_bar WITH (UPDLOCK, HOLDLOCK) " +
        "            WHERE tc_bar.Code = :barcodeTachCay " +
        "        ) " +
        "          AND m_parent.po = sp_cnt.po " +
        "          AND m_parent.productcode = sp_cnt.ma_san_pham " +
        "          AND chuyen_m.id = chuyen_cnt.id " +
        "          AND nhamay_m.id = nhamay_cnt.id " +
        "    ) < ( " +
        "        SELECT ISNULL(SUM(ISNULL(bcct_parent.SoLuongDaNhan, 0)), 0) " +
        "        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct_parent " +
        "        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap_parent ON bcct_parent.SoPhieuCapBTP = cap_parent.SoPhieuCapBTP " +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx_parent ON khsx_parent.id = :khsxId " +
        "        INNER JOIN pro_chuyen chuyen_parent ON khsx_parent.pro_chuyen_id = chuyen_parent.id " +
        "        INNER JOIN pro_nha_may nhamay_parent ON chuyen_parent.pro_nha_may_id = nhamay_parent.id " +
        "        INNER JOIN pro_san_pham sp_parent ON khsx_parent.pro_san_pham_id = sp_parent.id " +
        "        WHERE ISNULL(bcct_parent.TraBTP, 0) = 0 " +
        "          AND cap_parent.ProductCode = sp_parent.ma_san_pham " +
        "          AND cap_parent.TenCum = chuyen_parent.ten " +
        "          AND bcct_parent.PO = sp_parent.po " +
        "          AND cap_parent.TenPhanXuong = 'XN' + RIGHT(nhamay_parent.ten, 1) " +
        "          AND bcct_parent.TemBarcodeBTP = ( " +
        "              SELECT TOP 1 tc_bar2.Barcode FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc_bar2 WITH (UPDLOCK, HOLDLOCK) " +
        "              WHERE tc_bar2.Code = :barcodeTachCay " +
        "          ) " +
        "    ) " +
        "    AND ( " +
        "        SELECT ISNULL(SUM(ISNULL(bcct_parent2.SoLuongDaNhan, 0)), 0) " +
        "        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct_parent2 " +
        "        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap_parent2 ON bcct_parent2.SoPhieuCapBTP = cap_parent2.SoPhieuCapBTP " +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx_parent2 ON khsx_parent2.id = :khsxId " +
        "        INNER JOIN pro_chuyen chuyen_parent2 ON khsx_parent2.pro_chuyen_id = chuyen_parent2.id " +
        "        INNER JOIN pro_nha_may nhamay_parent2 ON chuyen_parent2.pro_nha_may_id = nhamay_parent2.id " +
        "        INNER JOIN pro_san_pham sp_parent2 ON khsx_parent2.pro_san_pham_id = sp_parent2.id " +
        "        WHERE ISNULL(bcct_parent2.TraBTP, 0) = 0 " +
        "          AND cap_parent2.ProductCode = sp_parent2.ma_san_pham " +
        "          AND cap_parent2.TenCum = chuyen_parent2.ten " +
        "          AND bcct_parent2.PO = sp_parent2.po " +
        "          AND cap_parent2.TenPhanXuong = 'XN' + RIGHT(nhamay_parent2.ten, 1) " +
        "          AND bcct_parent2.TemBarcodeBTP = ( " +
        "              SELECT TOP 1 tc_bar3.Barcode FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc_bar3 WITH (UPDLOCK, HOLDLOCK) " +
        "              WHERE tc_bar3.Code = :barcodeTachCay " +
        "          ) " +
        "    ) > 0 " +
        "    AND ( " +
        "        SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m3 WITH (UPDLOCK, HOLDLOCK) " +
        KHSX_CNT_JOINS +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx_m ON khsx_m.id = m3.khsx_id " +
        "        INNER JOIN pro_chuyen chuyen_m ON khsx_m.pro_chuyen_id = chuyen_m.id " +
        "        INNER JOIN pro_nha_may nhamay_m ON chuyen_m.pro_nha_may_id = nhamay_m.id " +
        "        WHERE m3.BarcodeTachCay = :barcodeTachCay " +
        "          AND m3.po = sp_cnt.po " +
        "          AND m3.productcode = sp_cnt.ma_san_pham " +
        "          AND chuyen_m.id = chuyen_cnt.id " +
        "          AND nhamay_m.id = nhamay_cnt.id " +
        "    ) < ( " +
        "        SELECT ISNULL(SUM(ISNULL(bcct.SoLuongDaNhan, 0)), 0) " +
        "        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
        "        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP " +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = :khsxId " +
        "        INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
        "        INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
        "        INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
        "        WHERE ISNULL(bcct.TraBTP, 0) = 0 " +
        "          AND cap.ProductCode = sp.ma_san_pham " +
        "          AND cap.TenCum = chuyen.ten " +
        "          AND bcct.PO = sp.po " +
        "          AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1) " +
        "          AND EXISTS ( " +
        "              SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc3 " +
        "              WHERE tc3.Barcode = bcct.TemBarcodeBTP AND tc3.Code = :barcodeTachCay " +
        "          ) " +
        "    ) " +
        "    AND ( " +
        "        SELECT ISNULL(SUM(ISNULL(bcct2.SoLuongDaNhan, 0)), 0) " +
        "        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct2 " +
        "        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap2 ON bcct2.SoPhieuCapBTP = cap2.SoPhieuCapBTP " +
        "        INNER JOIN pro_ke_hoach_san_xuat khsx2 ON khsx2.id = :khsxId " +
        "        INNER JOIN pro_chuyen chuyen2 ON khsx2.pro_chuyen_id = chuyen2.id " +
        "        INNER JOIN pro_nha_may nhamay2 ON chuyen2.pro_nha_may_id = nhamay2.id " +
        "        INNER JOIN pro_san_pham sp2 ON khsx2.pro_san_pham_id = sp2.id " +
        "        WHERE ISNULL(bcct2.TraBTP, 0) = 0 " +
        "          AND cap2.ProductCode = sp2.ma_san_pham " +
        "          AND cap2.TenCum = chuyen2.ten " +
        "          AND bcct2.PO = sp2.po " +
        "          AND cap2.TenPhanXuong = 'XN' + RIGHT(nhamay2.ten, 1) " +
        "          AND EXISTS ( " +
        "              SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc4 " +
        "              WHERE tc4.Barcode = bcct2.TemBarcodeBTP AND tc4.Code = :barcodeTachCay " +
        "          ) " +
        "    ) > 0")
    int insertMappingIfAvailable(
        @Param("rfidBarcode") String rfidBarcode,
        @Param("barcodeTachCay") String barcodeTachCay,
        @Param("rfid") String rfid,
        @Param("rfidHex") String rfidHex,
        @Param("nguoiMap") String nguoiMap,
        @Param("tkId") Long tkId,
        @Param("codeRfid") String codeRfid,
        @Param("codeRfidHex") String codeRfidHex,
        @Param("khsxId") Long khsxId,
        @Param("nsclId") Long nsclId,
        @Param("po") String po,
        @Param("productcode") String productcode
    );

    /**
     * Lấy danh sách RFID đã mapping cho mã cây
     */
    @Query(nativeQuery = true, value = 
        "SELECT " +
        "   RFID_Barcode as rfidBarcode, " +
        "   BarcodeTachCay as barcodeTachCay, " +
        "   RFID as rfid, " +
        "   RFID_Hex as rfidHex, " +
        "   NguoiMap as nguoiMap, " +
        "   ThoiGianMap as thoiGianMap, " +
        "   TKId as tkId, " +
        "   Code_RFID as codeRfid, " +
        "   Code_RFID_Hex as codeRfidHex, " +
        "   khsx_id as khsxId, " +
        "   nscl_id as nsclId, " +
        "   po as po, " +
        "   productcode as productcode " +
        "FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE BarcodeTachCay = :barcodeTachCay " +
        "ORDER BY ThoiGianMap DESC")
    java.util.List<java.util.Map<String, Object>> findMappingsByBarcode(@Param("barcodeTachCay") String barcodeTachCay);

    /**
     * Xóa mapping RFID theo RFID
     * @param rfid RFID cần xóa mapping
     * @return Số bản ghi đã xóa
     */
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(nativeQuery = true, value =
        "DELETE FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping " +
        "WHERE RFID = :rfid OR RFID_Barcode = :rfid OR RFID_Hex = :rfid")
    int deleteMappingByRfid(@Param("rfid") String rfid);
}
