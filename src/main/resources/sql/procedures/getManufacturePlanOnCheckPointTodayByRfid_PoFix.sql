USE [HiPro]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- KHSX hôm nay trên checkpoint theo tem RFID đã map (RFID / RFID_Hex / RFID_Barcode, CI).
-- CHỈ đọc — KHÔNG gọi getAllManufacturePlanOnCheckPointToday_PoFix (tránh sync/cursor mất phút).
-- Client đã load danh sách đầy đủ qua manuplan-on-checkpoint-today-po-fix; API này chỉ tìm item trong list.
--
-- LỌC KHÔNG TRẢ RỖNG KHI PO/PRODUCTCODE LỆCH (tránh mất bước ghi nhận NSCL):
--   * PO: gộp PO đã lưu lúc map + PO suy từ mã cây (phiếu cấp BTP) -> không trượt do PO lệch.
--   * Mã hàng (productcode): bộ lọc MỀM -> chỉ thu hẹp khi có ít nhất 1 kế hoạch khớp,
--     nếu không khớp dòng nào thì giữ nguyên toàn bộ ứng viên (không loại sạch).
--
-- exec [dbo].[getManufacturePlanOnCheckPointTodayByRfid_PoFix] @rfid = N'(01)03608393569882(21)38939', @checkpointId = 8145

IF OBJECT_ID(N'dbo.getManufacturePlanOnCheckPointTodayByRfid_PoFix', N'P') IS NOT NULL
    DROP PROCEDURE [dbo].[getManufacturePlanOnCheckPointTodayByRfid_PoFix];
GO

CREATE PROCEDURE [dbo].[getManufacturePlanOnCheckPointTodayByRfid_PoFix]
    @rfid         nvarchar(max),
    @checkpointId bigint,
    @idNhom       bigint = NULL  /* giữ tham số tương thích API; lọc nhóm bổ sung sau */
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @rfidNorm nvarchar(max) = LTRIM(RTRIM(ISNULL(@rfid, '')));

    /* JDBC/Hibernate cần ResultSet kể cả khi không có dữ liệu — không được RETURN trần. */
    IF @rfidNorm = ''
    BEGIN
        SELECT CAST(NULL AS bigint) AS planId WHERE 1 = 0;
        RETURN;
    END;

    DECLARE @barcodeTachCay     nvarchar(500);
    DECLARE @poFromMapping      nvarchar(500);
    DECLARE @khsxIdMapping      bigint;
    DECLARE @nsclIdMapping      bigint;
    DECLARE @productcodeMapping nvarchar(200);

    /* Bước 1: RFID -> mã cây + PO/KHSX/NSCL/mã hàng đã lưu lúc map (1 round-trip eGMF, không join nặng).
       Mã cây có thể gắn NHIỀU PO nên ưu tiên PO + mã hàng đã lưu trong mapping. */
    SELECT TOP 1
           @barcodeTachCay     = m.BarcodeTachCay,
           @poFromMapping      = m.po,
           @khsxIdMapping      = m.khsx_id,
           @nsclIdMapping      = m.nscl_id,
           @productcodeMapping = m.productcode
    FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m WITH (NOLOCK)
    WHERE LTRIM(RTRIM(ISNULL(m.RFID, '')))         COLLATE Latin1_General_CI_AI = @rfidNorm COLLATE Latin1_General_CI_AI
       OR LTRIM(RTRIM(ISNULL(m.RFID_Hex, '')))     COLLATE Latin1_General_CI_AI = @rfidNorm COLLATE Latin1_General_CI_AI
       OR LTRIM(RTRIM(ISNULL(m.RFID_Barcode, ''))) COLLATE Latin1_General_CI_AI = @rfidNorm COLLATE Latin1_General_CI_AI;

    IF @barcodeTachCay IS NULL
    BEGIN
        SELECT CAST(NULL AS bigint) AS planId WHERE 1 = 0;
        RETURN;
    END;

    DECLARE @poMapping nvarchar(500) = NULLIF(LTRIM(RTRIM(ISNULL(@poFromMapping, ''))), '');
    DECLARE @pc        nvarchar(200) = NULLIF(LTRIM(RTRIM(ISNULL(@productcodeMapping, ''))), '');

    /* Bước 2: xác định tập PO để lọc kế hoạch — GỘP để không trả rỗng do PO lệch.
       - PO đã lưu trong mapping (chính xác cho mã cây nhiều PO), VÀ
       - PO suy ra từ mã cây qua phiếu cấp BTP (an toàn khi mapping cũ/khác định dạng).
       Độ chính xác cho mã cây nhiều PO vẫn được giữ nhờ bộ lọc mã hàng (productcode) ở Bước 4. */
    DECLARE @tblPo TABLE (
        PO nvarchar(500) NOT NULL
    );

    IF @poMapping IS NOT NULL
        INSERT INTO @tblPo (PO) VALUES (@poMapping);

    INSERT INTO @tblPo (PO)
    SELECT DISTINCT LTRIM(RTRIM(bcct.PO))
    FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc WITH (NOLOCK)
    INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct WITH (NOLOCK)
        ON bcct.TemBarcodeBTP = tc.Barcode
    WHERE tc.Code = @barcodeTachCay
      AND bcct.PO IS NOT NULL
      AND LTRIM(RTRIM(bcct.PO)) <> ''
      AND LTRIM(RTRIM(bcct.PO)) NOT IN (SELECT PO FROM @tblPo);

    IF NOT EXISTS (SELECT 1 FROM @tblPo)
    BEGIN
        SELECT CAST(NULL AS bigint) AS planId WHERE 1 = 0;
        RETURN;
    END;

    DECLARE @NgayKiemTra datetime = GETDATE();

    /*
     * Bước 3: dựng tập ứng viên (#cand) — CHƯA lọc theo mã hàng.
     * Cùng output getAllManufacturePlanOnCheckPointToday_PoFix, lọc sớm theo PO (không sync, không INSERT-EXEC).
     */
    IF OBJECT_ID('tempdb..#cand') IS NOT NULL
        DROP TABLE #cand;

    SELECT khsx.ID AS planId, khsx.NGAY_SAN_XUAT AS planManufactureDate,
           khsx.SO_LUONG_YEU_CAU AS planRequestedQuantity,
           khsx.TINH_TRANG AS planStatus, khsx.GHI_CHU AS planNote,
           khsx.SAM AS planSam, khsx.LOT_NO AS planLotNumber,
           chuyen.ID AS bandId, chuyen.TEN AS bandName, chuyen.STT AS bandIndex,
           sp.ID AS spId, sp.MA_SAN_PHAM AS spMaSanPham, sp.MA_DON_HANG AS spMaDonHang,
           sp.po + CHAR(10) + '(' + SPPO.CumPO + ')' AS spPo, '-' AS spColor,
           sp.SO_LUONG_SAN_XUAT AS spSoLuongSanXuat,
           sp.PRO_KHACH_HANG_ID AS spKhachHangId, kh.TEN AS khTen,
           sp.PRO_TEN_MUA_ID AS spTenMuaId,
           (
               SELECT SeasonCode
               FROM eGMF.dbo.TEC_ProductInformation tec1 WITH (NOLOCK)
               INNER JOIN eGMF.dbo.OMM_PO tec2 WITH (NOLOCK) ON tec1.Id = tec2.DHId
               WHERE tec1.ProductCode = sp.ma_don_hang AND tec2.PONo = sp.po
           ) tenMua,
           sp.HINH_MAT_TRUOC_URL AS spFrontImgUrl1, sp.HINH_MAT_SAU_URL AS spBehindImgUrl1,
           b3.DVT, b3.MaChungLoai, SPPO.SanPham,
           (
               SELECT ISNULL((
                   SELECT SUM(
                       qty_1_h + qty_2_h + qty_3_h + qty_4_h + qty_5_h + qty_6_h + qty_7_h + qty_8_h
                       + qty_9_h + qty_10_h + qty_11_h + qty_12_h + qty_13_h + qty_14_h
                       + fix_1_h + fix_2_h + fix_3_h + fix_4_h + fix_5_h + fix_6_h + fix_7_h + fix_8_h
                       + fix_9_h + fix_10_h + fix_11_h + fix_12_h + fix_13_h + fix_14_h
                       + ISNULL(a1.sua_po_luy_ke, 0)
                   )
                   FROM pro_nscl a1 WITH (NOLOCK)
                   INNER JOIN pro_ke_hoach_san_xuat b1 WITH (NOLOCK) ON a1.pro_ke_hoach_san_xuat_id = b1.id
                   LEFT JOIN NV_KeHoachSanXuat b2 ON b1.oid_mapping = b2.Oid
                   INNER JOIN pro_san_pham c1 WITH (NOLOCK) ON b1.pro_san_pham_id = c1.id
                   LEFT JOIN DM_SanPham_PO ON c1.oid_mapping = DM_SanPham_PO.Oid
                   WHERE c1.ma_don_hang = sp.ma_san_pham
                     AND DM_SanPham_PO.CumPO = SPPO.CumPO
                     AND b2.Chuyen = chuyen.oid_mapping
                     AND DATEDIFF(day, b1.ngay_san_xuat, @NgayKiemTra) >= 0
                     AND b2.Oid = khsx.oid_mapping
               ), 0)
           ) AS slDaKiem,
           b2.STT
    INTO #cand
    FROM PRO_CHECK_POINT cp WITH (NOLOCK)
    INNER JOIN PRO_CHUYEN chuyen WITH (NOLOCK)
        ON cp.PRO_CHUYEN_ID = chuyen.ID AND cp.ID = @checkpointId
    INNER JOIN PRO_KE_HOACH_SAN_XUAT khsx WITH (NOLOCK)
        ON khsx.PRO_CHUYEN_ID = chuyen.ID
       AND DATEDIFF(day, khsx.NGAY_SAN_XUAT, @NgayKiemTra) = 0
    INNER JOIN PRO_SAN_PHAM sp WITH (NOLOCK)
        ON khsx.PRO_SAN_PHAM_ID = sp.ID
    INNER JOIN DM_SanPham_PO SPPO WITH (NOLOCK)
        ON SPPO.Oid = sp.oid_mapping
    INNER JOIN PRO_KHACH_HANG kh WITH (NOLOCK)
        ON sp.PRO_KHACH_HANG_ID = kh.ID
    LEFT JOIN NV_KeHoachSanXuat b1 WITH (NOLOCK)
        ON khsx.oid_mapping = b1.Oid
    LEFT JOIN eGMF.dbo.OMM_KeHoachThang b2 WITH (NOLOCK)
        ON b2.KHTId = b1.KHTId
    LEFT JOIN eGMF.dbo.OMM_PO_ChiTiet b3 WITH (NOLOCK)
        ON b2.CTPOId = b3.POCTId
    WHERE ISNULL(khsx.isCompleted, 0) = 0
      AND b2.TinhTrang = 'MoiTao'
      AND EXISTS (
          SELECT 1
          FROM @tblPo mp
          WHERE mp.PO COLLATE Latin1_General_CI_AI = SPPO.PO COLLATE Latin1_General_CI_AI
             OR mp.PO COLLATE Latin1_General_CI_AI = sp.po COLLATE Latin1_General_CI_AI
      );

    /* Bước 4: lọc MỀM theo mã hàng (productcode) đã lưu trong mapping.
       - Mapping cũ chưa có productcode -> bỏ qua (giữ tương thích).
       - Nếu có ít nhất 1 ứng viên khớp productcode -> chỉ trả các ứng viên khớp (đảm bảo chính xác cho mã cây nhiều PO).
       - Nếu KHÔNG ứng viên nào khớp -> KHÔNG loại sạch, trả nguyên tập ứng viên (tránh mất bước ghi nhận). */
    DECLARE @pcHasMatch bit = 0;
    IF @pc IS NOT NULL AND EXISTS (
        SELECT 1 FROM #cand
        WHERE spMaSanPham COLLATE Latin1_General_CI_AI = @pc COLLATE Latin1_General_CI_AI
           OR spMaDonHang COLLATE Latin1_General_CI_AI = @pc COLLATE Latin1_General_CI_AI
    )
        SET @pcHasMatch = 1;

    SELECT *
        , CASE WHEN DVT = 'SET' THEN (
              SELECT TOP 1 URL FROM DM_HinhSanPham
              WHERE SanPham = a.SanPham AND HinhMatTruoc = 1 AND ChungLoaiChiTiet = MaChungLoai
          ) ELSE spFrontImgUrl1 END spFrontImgUrl
        , CASE WHEN DVT = 'SET' THEN (
              SELECT TOP 1 URL FROM DM_HinhSanPham
              WHERE SanPham = a.SanPham AND HinhMatTruoc = 0 AND ChungLoaiChiTiet = MaChungLoai
          ) ELSE spBehindImgUrl1 END spBehindImgUrl
    FROM #cand a
    WHERE @pcHasMatch = 0
       OR a.spMaSanPham COLLATE Latin1_General_CI_AI = @pc COLLATE Latin1_General_CI_AI
       OR a.spMaDonHang COLLATE Latin1_General_CI_AI = @pc COLLATE Latin1_General_CI_AI
    ORDER BY STT;

    IF OBJECT_ID('tempdb..#cand') IS NOT NULL
        DROP TABLE #cand;
END
GO
