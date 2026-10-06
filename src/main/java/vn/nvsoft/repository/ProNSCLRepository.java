package vn.nvsoft.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.chuyen.CheckPointCode;
import vn.nvsoft.repository.egmf.TemBarcodeTachCaySqlFragments;
import vn.nvsoft.domain.heso.HeSoNhanVien;
import vn.nvsoft.domain.nscl.MaHangChinhPhu;
import vn.nvsoft.domain.nscl.BamAnLuong;

/**
 * Spring Data repository for the ProNSCL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProNSCLRepository extends JpaRepository<ProNSCL, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM pro_nscl nscl WHERE nscl.PRO_KE_HOACH_SAN_XUAT_ID = :proKHSX AND nscl.PRO_CHECK_POINT_ID = :proCP")
    List<ProNSCL> findByProKeHoachSanXuatAndProCheckPoint(@Param("proCP") Long proCP, @Param("proKHSX") Long proKHSX);

    @Query(nativeQuery = true, value = "EXEC GET_CHECKPOINT_CODE  :checkpointId")
    List<CheckPointCode> getCheckPointCode(@Param("checkpointId") Long checkpointId);

    @Query(nativeQuery = true, value = "SELECT * FROM pro_nscl nscl WHERE nscl.PRO_KE_HOACH_SAN_XUAT_ID = :proKHSX")
    List<ProNSCL> findByProKeHoachSanXuat(@Param("proKHSX") Long proKHSX);

    /**
     * Lấy tất cả ProNSCL theo chuyen_id và pro_san_pham_id (không filter theo checkpoint)
     * Để tính tổng may ra từ tất cả các ngày
     */
    @Query(nativeQuery = true, value = "SELECT nscl.* FROM pro_nscl nscl " +
            "INNER JOIN pro_ke_hoach_san_xuat khsx ON nscl.pro_ke_hoach_san_xuat_id = khsx.id " +
            "WHERE khsx.pro_chuyen_id = :chuyenId AND khsx.pro_san_pham_id = :proSanPhamId")
    List<ProNSCL> findByChuyenIdAndProSanPhamId(@Param("chuyenId") Long chuyenId, @Param("proSanPhamId") Long proSanPhamId);

    @Query(value = "EXEC LCD_Truc_Quan_Chuyen_NangSuatChatLuongTheoGio :chuyen", nativeQuery = true)
    List<NsclTheoGio> getNSCLTheoGioTrenChuyen(@Param("chuyen") Integer chuyen);

    @Query(value = "EXEC LCD_Truc_Quan_Chuyen_ThongTinChung :chuyen", nativeQuery = true)
    NsclTheoGioChuyenThongTinChung getNSCLTheoGioTrenChuyenThongTinChung(@Param("chuyen") Integer chuyen);

    // @Query(nativeQuery = true, value = "SELECT ch.id chuyenId, ch.stt chuyen,
    // khsx.id khsxId, nscl.id nsclId, ctl.id ctlId, ctl.dx dx, ctl.dy dy,
    // ctl.is_front front, ctl.is_inside inside, loi.id "
    // + "loiId, loi.ten_loi tenLoi, loi.mau_sac mauSac, loi.ma_loi maLoi ,
    // sanPham.hinh_mat_truoc_url hinhMatTruocUrl, sanPham.hinh_mat_sau_url
    // hinhMatSauUrl "
    // + " FROM pro_chuyen as ch "
    // + " INNER JOIN pro_ke_hoach_san_xuat as khsx on ch.id = khsx.pro_chuyen_id "
    // + " INNER JOIN pro_nscl as nscl on khsx.id = nscl.pro_ke_hoach_san_xuat_id "
    // + " INNER JOIN pro_san_pham as sanPham on khsx.pro_san_pham_id = sanPham.id "
    // + " INNER JOIN pro_nscl_chi_tiet_loi as ctl on ctl.pronscl_id = nscl.id "
    // + " INNER JOIN pro_loi as loi on ctl.pro_loi_id = loi.id "
    // + " where ch.stt = :chuyen and DATEDIFF(day, khsx.ngay_san_xuat,GETDATE()) =
    // 0 and ctl.gio_lam is not null")
    @Query(value = "EXEC get_chi_tiet_nscl_theo_thoi_gian_tren_chuyen :chuyen", nativeQuery = true)
    List<ChiTietNSCLTheoThoiGian> getChiTietNSCLTheoThoiGianTrenChuyen(@Param("chuyen") Integer chuyen);

    @Query(nativeQuery = true, value = "EXEC LCD_Truc_Quan_Chuyen_ThongTinChung_NSLD_Ngay :chuyen")
    NangSuatLaoDongNgay getNangSuatLaoDongNgayTrenChuyen(@Param("chuyen") Integer chuyen);

    /**
     * Lay danh sach loai ma hang chinh phu cho giao user chon ma chinh phu truoc
     * khi bam nscl
     *
     * @param proKHSXId
     * @return
     */
    @Query(value = "EXEC PRO_GET_MA_CHUNG_LOAI_KEHOACHXUATHANG :proKHSXId", nativeQuery = true)
    List<MaHangChinhPhu> findMaHangChinhPhuByProKHSXId(@Param("proKHSXId") Long proKHSXId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC PRO_UPDATE_MA_CHUNG_LOAI_KEHOACHXUATHANG :proKHSXId, :maChungLoai")
    void updateMaChungLoaiToProKHSX(@Param("proKHSXId") Long proKHSXId, @Param("maChungLoai") String maChungLoai);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC PRO_CAP_NHAT_BAM_AN_LUONG :proKHSXId, :proCheckPointID")
    List<BamAnLuong> capNhatBamAnLuong(@Param("proKHSXId") Long proKHSXId,
            @Param("proCheckPointID") Long proCheckPointID);

    @Query(value = "SELECT d.* " +
            "FROM pro_san_pham a " +
            "INNER JOIN pro_ke_hoach_san_xuat b ON a.id = b.pro_san_pham_id " +
            "INNER JOIN pro_check_point c on b.pro_chuyen_id = c.pro_chuyen_id " +
            "INNER JOIN pro_nscl d on c.id = d.pro_check_point_id and b.id = d.pro_ke_hoach_san_xuat_id " +
            "WHERE CAST(b.ngay_san_xuat AS date) = CAST(GETDATE() AS date) " +
            "and c.id = :checkPointId " +
            "and po = :po", nativeQuery = true)
    List<ProNSCL> findByCheckPointIdAndPoAndNgaySanXuatToday(@Param("checkPointId") Long checkPointId,
            @Param("po") String po);

    public static interface ProNSCLSanPhamCreateResult {
        Long getNewId();

        String getError();
    }

    @Transactional
    @Query(value = "EXEC pro_nscl_sanpham_create :proNsclId, :nguoiQuet, :proCheckpointId, :proKhsxId, :checkedType, :displayName, :proNsclChiTietLoiId, :checkedTime", nativeQuery = true)
    ProNSCLSanPhamCreateResult proNsclSanPhamCreate(
            @Param("proNsclId") Long proNsclId,
            @Param("nguoiQuet") String nguoiQuet,
            @Param("proCheckpointId") Long proCheckpointId,
            @Param("proKhsxId") Long proKhsxId,
            @Param("checkedType") String checkedType,
            @Param("displayName") String displayName,
            @Param("proNsclChiTietLoiId") Long proNsclChiTietLoiId,
            @Param("checkedTime") String checkedTime);

    /**
     * Kiểm tra đã kiểm tra NSCL với displayName (rfid/barcode) và checked_type
     * ('DAT', 'SUA')
     */
    @Query(value = "SELECT id FROM pro_nscl_sanpham WHERE displayname = :displayName AND checked_type IN ('DAT', 'SUA')", nativeQuery = true)
    List<Long> findCheckedNSCLIdByDisplayName(@Param("displayName") String displayName);

    /**
     * Lấy trạng thái checked_type mới nhất của displayname (rfid/barcode)
     */
    @Query(value = "SELECT TOP 1 checked_type FROM pro_nscl_sanpham WHERE displayname = :displayName ORDER BY thoigianquet DESC", nativeQuery = true)
    String findLatestCheckedTypeByDisplayName(@Param("displayName") String displayName);

    /**
     * Lấy pro_nscl_id của bản ghi gần nhất theo displayname
     */
    @Query(value = "SELECT TOP 1 pro_nscl_id FROM pro_nscl_sanpham WHERE displayname = :displayName ORDER BY thoigianquet DESC", nativeQuery = true)
    Long findLastProNsclIdByDisplayName(@Param("displayName") String displayName);

    /**
     * Lấy gio_lam của bản ghi gần nhất theo displayname
     */
    @Query(value = "SELECT TOP 1 checked_time FROM pro_nscl_sanpham WHERE displayname = :displayName ORDER BY thoigianquet DESC", nativeQuery = true)
    String findLastGioLamByDisplayName(@Param("displayName") String displayName);

    /**
     * Interface projection cho ProNsclSanPham
     */
    public static interface ProNsclSanPhamProjection {
        Long getId();
        Long getProNsclId();
        String getNguoiquet();
        java.time.Instant getThoigianquet();
        Long getProCheckpointId();
        Long getProKhsxId();
        String getCheckedType();
        String getDisplayname();
        Long getProNsclChiTietLoiId();
        String getCheckedTime();
        Boolean getIsTracking();
        String getValueHEX();
    }

    /**
     * Lấy bản ghi có thoigianquet gần đây nhất (TOP 1) theo displayname
     * Sắp xếp theo thoigianquet DESC để lấy dòng mới nhất trước
     * Map kết quả vào ProNsclSanPhamProjection interface
     */
    @Query(value = "SELECT TOP 1 id, pro_nscl_id as proNsclId, nguoiquet, thoigianquet, pro_checkpoint_id as proCheckpointId, pro_khsx_id as proKhsxId, checked_type as checkedType, displayname, pro_nscl_chi_tiet_loi_id as proNsclChiTietLoiId, checked_time as checkedTime, isTracking, valueHEX " +
            "FROM pro_nscl_sanpham WHERE displayname = :displayName ORDER BY thoigianquet DESC", nativeQuery = true)
    ProNsclSanPhamProjection findTop1ByDisplaynameOrderByThoigianquetDesc(@Param("displayName") String displayName);

    /**
     * Đếm số lượng lỗi (LOI) có RFID (displayName IS NOT NULL) theo pro_nscl_id
     * Chỉ đếm những sản phẩm có RFID mà trạng thái cuối cùng là LOI (chưa được sửa)
     * Sử dụng ROW_NUMBER để lấy bản ghi mới nhất của mỗi displayname
     */
    @Query(value = "SELECT COUNT(*) FROM ( " +
            "    SELECT displayname, " +
            "           checked_type, " +
            "           ROW_NUMBER() OVER (PARTITION BY displayname ORDER BY thoigianquet DESC) as rn " +
            "    FROM pro_nscl_sanpham " +
            "    WHERE pro_nscl_id = :proNsclId " +
            "    AND displayname IS NOT NULL AND displayname != '' " +
            ") as latest " +
            "WHERE rn = 1 AND checked_type = 'LOI'", nativeQuery = true)
    Long countLoiWithRfidByProNsclId(@Param("proNsclId") Long proNsclId);

    /**
     * Đếm số lượng sửa (SUA) có RFID (displayName IS NOT NULL) theo pro_nscl_id
     * Chỉ đếm những sản phẩm có RFID mà trạng thái cuối cùng là SUA
     * Sử dụng ROW_NUMBER để lấy bản ghi mới nhất của mỗi displayname
     */
    @Query(value = "SELECT COUNT(*) FROM ( " +
            "    SELECT displayname, " +
            "           checked_type, " +
            "           ROW_NUMBER() OVER (PARTITION BY displayname ORDER BY thoigianquet DESC) as rn " +
            "    FROM pro_nscl_sanpham " +
            "    WHERE pro_nscl_id = :proNsclId " +
            "    AND displayname IS NOT NULL AND displayname != '' " +
            ") as latest " +
            "WHERE rn = 1 AND checked_type = 'SUA'", nativeQuery = true)
    Long countFixWithRfidByProNsclId(@Param("proNsclId") Long proNsclId);

    /**
     * Gọi stored procedure pro_nscl_decrease_checked để trừ số lượng
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "EXEC pro_nscl_decrease_checked :lastProNsclId, :checkedType, :checkedTime", nativeQuery = true)
    void callProNsclDecreaseChecked(
            @Param("lastProNsclId") Long lastProNsclId,
            @Param("checkedType") String checkedType,
            @Param("checkedTime") String checkedTime);

    /**
     * Gọi stored procedure pro_nscl_sanpham_codeinjection (với rfidBarcode)
     * Luôn trả về 3 cột: id, Error, action
     */
    public static interface ProNSCLSanPhamCodeInjectionResult {
        Long getId();

        String getError();

        String getAction();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "EXEC pro_nscl_sanpham_codeinjection :proNsclId, :nguoiQuet, :proCheckpointId, :proKhsxId, :checkedType, :displayName, :proNsclChiTietLoiId, :checkedTime, :rfidBarcode", nativeQuery = true)
    ProNSCLSanPhamCodeInjectionResult proNsclSanPhamCodeInjection(
            @Param("proNsclId") Long proNsclId,
            @Param("nguoiQuet") String nguoiQuet,
            @Param("proCheckpointId") Long proCheckpointId,
            @Param("proKhsxId") Long proKhsxId,
            @Param("checkedType") String checkedType,
            @Param("displayName") String displayName,
            @Param("proNsclChiTietLoiId") Long proNsclChiTietLoiId,
            @Param("checkedTime") String checkedTime,
            @Param("rfidBarcode") String rfidBarcode);

    /**
     * Gọi stored procedure pro_nscl_sanpham_codeinjection_updated (với valueHEX và rfidBarcode)
     * Luôn trả về 3 cột: id, Error, action
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "EXEC pro_nscl_sanpham_codeinjection_updated :proNsclId, :nguoiQuet, :proCheckpointId, :proKhsxId, :checkedType, :displayName, :proNsclChiTietLoiId, :checkedTime, :valueHEX, :rfidBarcode", nativeQuery = true)
    ProNSCLSanPhamCodeInjectionResult proNsclSanPhamCodeInjectionUpdated(
            @Param("proNsclId") Long proNsclId,
            @Param("nguoiQuet") String nguoiQuet,
            @Param("proCheckpointId") Long proCheckpointId,
            @Param("proKhsxId") Long proKhsxId,
            @Param("checkedType") String checkedType,
            @Param("displayName") String displayName,
            @Param("proNsclChiTietLoiId") Long proNsclChiTietLoiId,
            @Param("checkedTime") String checkedTime,
            @Param("valueHEX") String valueHEX,
            @Param("rfidBarcode") String rfidBarcode);

    /**
     * Lấy số lượng giới hạn từ bảng Summary_PO_QuetNhan dựa trên ma_san_pham, po và khsx_id
     * Query này join với các bảng liên quan để lấy Quantity từ Summary_PO_QuetNhan
     */
    @Query(value = "SELECT DISTINCT TOP 1 qn.Quantity " +
            "FROM pro_ke_hoach_san_xuat khsx " +
            "INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
            "INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
            "INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
            "INNER JOIN eGMF.dbo.Summary_PO_QuetNhan qn ON sp.ma_san_pham = qn.MaHang AND sp.po = qn.PO AND RIGHT(nhamay.ten, 1) = RIGHT(qn.XiNghiep, 1) " +
            "AND chuyen.ten = qn.ToMay " +
            "WHERE khsx.id = :khsxId", nativeQuery = true)
    Integer getQuantityLimitFromSummaryPO(@Param("khsxId") Long khsxId);

    /**
     * Interface projection cho danh sách mã cây theo PO
     */
    public static interface MaCayByPOProjection {
        String getCode();
        String getLot();
        Integer getSoTemDaQuet();
        Integer getTongSoTem();
        String getProductCode();
    }

    /**
     * Lấy danh sách mã cây theo PO (và mã hàng) với thông tin Lot, số tem đã quét và tổng tem.
     * Join sang CUTTING_PhieuCapBTP để lấy đúng mã hàng (ProductCode) của phiếu cấp BTP.
     *
     * @param po     Mã PO cần tìm
     * @param mahang Mã hàng (ProductCode). Nếu null/rỗng thì không lọc theo mã hàng.
     * @return Danh sách mã cây với thông tin chi tiết
     */
    @Query(value = "SELECT DISTINCT " +
            "tc.Code as code, " +
            "tc.Lot as lot, " +
            "ISNULL((SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m WHERE m.BarcodeTachCay = tc.Code), 0) as soTemDaQuet, " +
            TemBarcodeTachCaySqlFragments.SO_LOP_TRAI_TC + " as tongSoTem, " +
            "cap.ProductCode as productCode " +
            "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
            "INNER JOIN eGMF.dbo.CUTTING_TemBarcode_TachCay tc ON bcct.TemBarcodeBTP = tc.Barcode " +
            "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON cap.SoPhieuCapBTP = bcct.SoPhieuCapBTP " +
            "WHERE bcct.PO = :po " +
            "AND (:mahang IS NULL OR LTRIM(RTRIM(:mahang)) = '' " +
            "     OR cap.ProductCode COLLATE Latin1_General_CI_AI = LTRIM(RTRIM(:mahang)) COLLATE Latin1_General_CI_AI)", nativeQuery = true)
    List<MaCayByPOProjection> findMaCayByPO(@Param("po") String po, @Param("mahang") String mahang);

    /**
     * Interface projection cho tổng may ra (tổng hàng đã kiểm: Sửa + Đạt)
     */
    public static interface TongMayRaProjection {
        Long getSanphamId();
        Long getChuyenId();
        Long getTotalQty();
        Long getTotalFix();
        Long getTongMayRa();
    }

    /**
     * Gọi stored procedure SP_TongMayRa_ByKHSX để lấy tổng hàng đã kiểm (Sửa + Đạt)
     * theo kế hoạch sản xuất
     * 
     * @param khsxId ID của kế hoạch sản xuất
     * @return Tổng may ra (Qty + Fix) lũy kế theo lịch sử của sản phẩm trên chuyền
     */
    @Query(value = "EXEC SP_TongMayRa_ByKHSX :khsxId", nativeQuery = true)
    TongMayRaProjection getTongMayRaByKHSX(@Param("khsxId") Long khsxId);

    /**
     * Anchor mapping query 2 từ khsx đang làm.
     */
    interface KhsxQuery2AnchorProjection {
        String getMaSanPham();

        String getCumPo();

        String getChuyenOidMapping();

        String getKhsxOidMapping();

        String getPoNoFromOmm();

        String getSpPo();
    }

    @Query(nativeQuery = true, value =
        "SELECT TOP 1 " +
        "  sp.ma_san_pham AS maSanPham, " +
        "  SPPO.CumPO AS cumPo, " +
        "  ch.oid_mapping AS chuyenOidMapping, " +
        "  khsx.oid_mapping AS khsxOidMapping, " +
        "  po.PONo AS poNoFromOmm, " +
        "  sp.po AS spPo " +
        "FROM pro_ke_hoach_san_xuat khsx " +
        "INNER JOIN pro_chuyen ch ON khsx.pro_chuyen_id = ch.id " +
        "INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
        "INNER JOIN DM_SanPham_PO SPPO ON SPPO.Oid = sp.oid_mapping " +
        "LEFT JOIN NV_KeHoachSanXuat nv ON khsx.oid_mapping = nv.Oid " +
        "LEFT JOIN eGMF.dbo.OMM_KeHoachThang omm_kht ON omm_kht.KHTId = nv.KHTId " +
        "LEFT JOIN eGMF.dbo.OMM_PO_ChiTiet omm_ct ON omm_kht.CTPOId = omm_ct.POCTId " +
        "LEFT JOIN eGMF.dbo.OMM_PO po ON omm_ct.POId = po.POId " +
        "WHERE khsx.id = :khsxId")
    Optional<KhsxQuery2AnchorProjection> findQuery2AnchorByKhsxId(@Param("khsxId") Long khsxId);

    /**
     * Pool pro_nscl — mapping y hệt subquery slDaKiem query 2 + lọc PONo.
     */
    @Query(nativeQuery = true, value =
        "SELECT nscl.* FROM pro_nscl nscl " +
        "INNER JOIN pro_ke_hoach_san_xuat b1 ON nscl.pro_ke_hoach_san_xuat_id = b1.id " +
        "LEFT JOIN NV_KeHoachSanXuat b2x ON b1.oid_mapping = b2x.Oid " +
        "INNER JOIN pro_san_pham c1 ON b1.pro_san_pham_id = c1.id " +
        "LEFT JOIN DM_SanPham_PO spo ON c1.oid_mapping = spo.Oid " +
        "WHERE c1.ma_don_hang = :maSanPhamAnchor " +
        "  AND spo.CumPO = :cumPoAnchor " +
        "  AND b2x.Chuyen = :chuyenOidMappingAnchor " +
        "  AND DATEDIFF(day, b1.ngay_san_xuat, GETDATE()) >= 0 " +
        "  AND b2x.Oid = :khsxOidMappingAnchor " +
        "  AND EXISTS ( " +
        "    SELECT 1 FROM NV_KeHoachSanXuat nv_po " +
        "    LEFT JOIN eGMF.dbo.OMM_KeHoachThang omm_kht ON omm_kht.KHTId = nv_po.KHTId " +
        "    LEFT JOIN eGMF.dbo.OMM_PO_ChiTiet omm_ct ON omm_kht.CTPOId = omm_ct.POCTId " +
        "    INNER JOIN eGMF.dbo.OMM_PO po ON omm_ct.POId = po.POId AND po.PONo = :poNo " +
        "    WHERE nv_po.Oid = b1.oid_mapping " +
        "  )")
    List<ProNSCL> findNsclPoolByQuery2Mapping(
        @Param("poNo") String poNo,
        @Param("maSanPhamAnchor") String maSanPhamAnchor,
        @Param("cumPoAnchor") String cumPoAnchor,
        @Param("chuyenOidMappingAnchor") String chuyenOidMappingAnchor,
        @Param("khsxOidMappingAnchor") String khsxOidMappingAnchor);

    /**
     * Đếm LOI RFID (latest state theo displayname trên từng nscl) chưa chuyển sang SUA.
     */
    @Query(nativeQuery = true, value =
        "WITH Latest AS ( " +
        "  SELECT sp.checked_type, " +
        "         ROW_NUMBER() OVER (PARTITION BY sp.pro_nscl_id, sp.displayname ORDER BY sp.thoigianquet DESC, sp.id DESC) AS rn " +
        "  FROM pro_nscl_sanpham sp " +
        "  WHERE sp.pro_nscl_id IN (:nsclIds) " +
        "    AND sp.displayname IS NOT NULL AND LTRIM(RTRIM(sp.displayname)) <> '' " +
        ") " +
        "SELECT COUNT(*) FROM Latest WHERE rn = 1 AND checked_type = 'LOI'")
    Long countUnmatchedLoiRfidForPoFix(@Param("nsclIds") List<Long> nsclIds);

    /**
     * Insert tối đa {@code limit} bản ghi {@code SUA} cho LOI RFID (latest state / displayname)
     * trên các {@code pro_nscl_id} quá khứ trong pool po-fix. FIFO theo {@code thoigianquet} ASC.
     *
     * @return số dòng đã insert
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value =
        "WITH Latest AS ( " +
        "  SELECT sp.pro_nscl_id, sp.nguoiquet, sp.pro_checkpoint_id, sp.pro_khsx_id, " +
        "         sp.displayname, sp.checked_time, sp.isTracking, sp.valueHEX, sp.RFID_Barcode, " +
        "         sp.checked_type, sp.thoigianquet, sp.id, " +
        "         ROW_NUMBER() OVER (PARTITION BY sp.pro_nscl_id, sp.displayname ORDER BY sp.thoigianquet DESC, sp.id DESC) AS rn " +
        "  FROM pro_nscl_sanpham sp " +
        "  WHERE sp.pro_nscl_id IN (:nsclIds) " +
        "    AND sp.displayname IS NOT NULL AND LTRIM(RTRIM(sp.displayname)) <> '' " +
        "), " +
        "UnmatchedLoi AS ( " +
        "  SELECT TOP (:limit) " +
        "         l.pro_nscl_id, l.nguoiquet, l.pro_checkpoint_id, l.pro_khsx_id, " +
        "         l.displayname, l.checked_time, l.isTracking, l.valueHEX, l.RFID_Barcode, " +
        "         l.thoigianquet, l.id " +
        "  FROM Latest l " +
        "  WHERE l.rn = 1 AND l.checked_type = 'LOI' " +
        "  ORDER BY l.thoigianquet ASC, l.id ASC " +
        ") " +
        "INSERT INTO pro_nscl_sanpham ( " +
        "  pro_nscl_id, nguoiquet, thoigianquet, pro_checkpoint_id, pro_khsx_id, " +
        "  checked_type, displayname, pro_nscl_chi_tiet_loi_id, checked_time, " +
        "  isTracking, valueHEX, RFID_Barcode " +
        ") " +
        "SELECT " +
        "  u.pro_nscl_id, COALESCE(:nguoiQuet, u.nguoiquet), GETDATE(), u.pro_checkpoint_id, u.pro_khsx_id, " +
        "  'SUA', u.displayname, NULL, u.checked_time, " +
        "  u.isTracking, u.valueHEX, u.RFID_Barcode " +
        "FROM UnmatchedLoi u")
    int insertPoFixSuaForUnmatchedLoiRfid(
        @Param("nsclIds") List<Long> nsclIds,
        @Param("limit") int limit,
        @Param("nguoiQuet") String nguoiQuet);
}
