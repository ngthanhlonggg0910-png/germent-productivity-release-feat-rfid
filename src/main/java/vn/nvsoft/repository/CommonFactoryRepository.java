package vn.nvsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.service.viewmodel.*;

import java.util.Date;
import java.util.List;

@Repository
public interface CommonFactoryRepository extends JpaRepository<ProTaiKhoanCheckPoint, Long> {
    interface SynchResultProjection {
        Integer getResult();
        String getMessage();
    }

    /**
     * Kết quả {@code SP_GetDanhSachNhom} (cột id_nhom, ten_nhom).
     */
    interface DanhSachNhomChuyenProjection {
        Long getIdNhom();

        String getTenNhom();
    }

    /**
     * Kế hoạch SX hôm nay theo {@code OMM_PO.PONo} (query 2 + slDaKiem gồm sua_po_luy_ke).
     */
    interface ManufacturePlanByPoProjection {
        Long getPlanId();

        java.util.Date getPlanManufactureDate();

        Long getPlanRequestedQuantity();

        Long getPlanStatus();

        String getMaSanPham();

        String getMaDonHang();

        String getPo();

        String getCumPo();

        String getBandName();

        Long getBandIndex();

        Integer getKhtStt();

        Integer getSlDaKiem();
    }


    @Query(nativeQuery = true, value = "SELECT tkcp.ID             as tkcpId, " +
        "       tkcp.tai_khoan      as tkcpTaiKhoan, " +
        "       tkcp.checked        as tkcpChecked, " +
        "       cp.ID               as checkpointId, " +
        "       cp.ten              as checkpointName, " +
        "       cp.type              as type, " +
        "       cp.code              as code, " +
        "       cp.pro_chuyen_id    as chuyenId, " +
        "       c.TEN               as tenChuyen, " +
        "       cp.pro_cong_doan_id as congDoanId, " +
        "       cd.TEN              as tenCongDoan " +
        "FROM PRO_CHECK_POINT cp " +
        "         INNER JOIN PRO_TAI_KHOAN_CHECK_POINT tkcp " +
        "                    ON cp.ID = tkcp.PRO_CHECK_POINT_ID " +
        "                        AND tkcp.TAI_KHOAN = :username " +
        "         INNER JOIN PRO_CHUYEN c on cp.PRO_CHUYEN_ID = c.ID " +
        "         INNER JOIN PRO_CONG_DOAN cd on cp.PRO_CONG_DOAN_ID = cd.ID")
    List<UserCheckPointViewModel> getAllWorkingCheckPointForUser(@Param("username") String username);

    @Query(nativeQuery = true, value = "SELECT khsx.ID as planId, khsx.NGAY_SAN_XUAT as planManufactureDate, khsx.SO_LUONG_YEU_CAU as planRequestedQuantity, khsx.TINH_TRANG as planStatus, khsx.GHI_CHU as planNote, khsx.SAM as planSam, khsx.LOT_NO as planLotNumber, " +
        " chuyen.ID as bandId, chuyen.TEN as bandName, chuyen.STT as bandIndex  " +
        " FROM PRO_CHECK_POINT cp " +
        " INNER JOIN PRO_CHUYEN chuyen ON cp.PRO_CHUYEN_ID = chuyen.ID AND cp.ID = :checkpointId " +
        " INNER JOIN PRO_KE_HOACH_SAN_XUAT khsx ON khsx.PRO_CHUYEN_ID = chuyen.ID")
    List<ManufacturePlanViewModel> getAllPlansOnCheckPoint(@Param("checkpointId") Long checkpointId);

    /*
    @Query(nativeQuery = true, value =
        "SELECT khsx.ID               as planId, " +
            "       khsx.NGAY_SAN_XUAT    as planManufactureDate, " +
            "       khsx.SO_LUONG_YEU_CAU as planRequestedQuantity, " +
            "       khsx.TINH_TRANG       as planStatus, " +
            "       khsx.GHI_CHU          as planNote, " +
            "       khsx.SAM              as planSam, " +
            "       khsx.LOT_NO           as planLotNumber, " +
            "       chuyen.ID             as bandId, " +
            "       chuyen.TEN            as bandName, " +
            "       chuyen.STT            as bandIndex, " +
            "       sp.ID                 as spId, " +
            "       sp.MA_SAN_PHAM        as spMaSanPham, " +
            "       sp.MA_DON_HANG        as spMaDonHang, " +
            "       sp.PO                 as spPo, " +
            "       sp.COLOR              as spColor, " +
            "       sp.SO_LUONG_SAN_XUAT  as spSoLuongSanXuat, " +
            "       sp.PRO_KHACH_HANG_ID  as spKhachHangId, " +
            "       kh.TEN                as khTen, " +
            "       sp.PRO_TEN_MUA_ID     as spTenMuaId,  " +
            "       mua.TEN_MUA           as tenMua, " +
            "       sp.HINH_MAT_TRUOC_URL as spFrontImgUrl, " +
            "       sp.HINH_MAT_SAU_URL as spBehindImgUrl " +
            "FROM PRO_CHECK_POINT cp " +
            "         INNER JOIN PRO_CHUYEN chuyen ON cp.PRO_CHUYEN_ID = chuyen.ID AND cp.ID = :checkpointId " +
            "         INNER JOIN PRO_KE_HOACH_SAN_XUAT khsx " +
            "                    ON khsx.PRO_CHUYEN_ID = chuyen.ID AND DATEDIFF(day, khsx.NGAY_SAN_XUAT,GETDATE()) = 0 " +
            "         INNER JOIN PRO_SAN_PHAM sp ON khsx.PRO_SAN_PHAM_ID = sp.ID" +
            "         INNER JOIN PRO_KHACH_HANG kh ON sp.PRO_KHACH_HANG_ID = kh.ID" +
            "         INNER JOIN PRO_TEN_MUA mua ON sp.PRO_TEN_MUA_ID = mua.ID" +
            "")
    */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday :checkpointId")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointToday(@Param("checkpointId") Long checkpointId);

    /**
     * Gọi stored procedure mới getAllManufacturePlanOnCheckPointToday_Updated
     * Logic mới: slDaKiem = QTY (Đạt) + FIX (Sửa) + REJECT (Lỗi)
     * Khác với stored procedure cũ: slDaKiem = QTY (Đạt) + FIX (Sửa)
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_Updated :checkpointId")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointTodayUpdated(@Param("checkpointId") Long checkpointId);

    /**
     * Proc mới {@code getAllManufacturePlanOnCheckPointToday_PoFix} (không đụng proc production).
     * slDaKiem = QTY + FIX + sua_po_luy_ke; {@code idNhom} tùy chọn (NULL = không lọc, lọc nhóm bổ sung sau).
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_PoFix :checkpointId, :idNhom")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointTodayPoFix(
        @Param("checkpointId") Long checkpointId,
        @Param("idNhom") Long idNhom
    );

    /**
     * KHSX hôm nay trên checkpoint theo tem RFID đã map — proc
     * {@code getManufacturePlanOnCheckPointTodayByRfid_PoFix}.
     */
    @Transactional
    @Modifying
    @Query(
        nativeQuery = true,
        value = "EXEC getManufacturePlanOnCheckPointTodayByRfid_PoFix :rfid, :checkpointId, :idNhom"
    )
    List<ManufacturePlanViewModel> getManufacturePlanOnCheckPointTodayByRfidPoFix(
        @Param("rfid") String rfid,
        @Param("checkpointId") Long checkpointId,
        @Param("idNhom") Long idNhom
    );

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_AllCompleted :checkpointId")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointTodayAllComplete(@Param("checkpointId") Long checkpointId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_DoThongSo :checkpointId")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointTodayOnDoThongSo(@Param("checkpointId") Long checkpointId);

    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_refinal :checkpointId")
    List<ManufacturePlanViewModel> getAllManufacturePlanOnCheckPointTodayOnRefinal(@Param("checkpointId") Long checkpointId);

    @Transactional
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_Synch :checkpointId")
    SynchResultProjection synchManufacturePlanOnCheckPointToday(@Param("checkpointId") Long checkpointId);

    @Query(nativeQuery = true, value = "SELECT cp.ID as checkpointId, cp.TEN as checkpointName, cp.PRO_CONG_DOAN_ID as checkpointCongDoanId, " +
        "       ch.ID as bandId, ch.TEN as bandName, ch.STT as bandIndex, ch.TEN_LOAI_CHUYEN as bandTypeName, ch.PRO_LOAI_CHUYEN_ID as bandTypeId, " +
        "       nhm.ID as factoryId, nhm.TEN as factoryName, nhm.STT as factoryIndex, " +
        "       cty.ID as companyId, cty.TEN as companyName, cty.DIA_CHI as companyAddr, cty.MST as companyTaxCode " +
        "FROM PRO_CHECK_POINT cp " +
        "         INNER JOIN PRO_CHUYEN ch ON cp.PRO_CHUYEN_ID = ch.ID " +
        "         INNER JOIN PRO_NHA_MAY nhm ON nhm.ID = ch.PRO_NHA_MAY_ID " +
        "         INNER JOIN PRO_CONG_TY cty ON cty.ID = nhm.PRO_CONG_TY_ID  " +
        "WHERE cp.ID = :checkpointId")
    List<CheckpointLocationViewModel> getCurrentCheckpointWorkShift(@Param("checkpointId") Long checkpointId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE PRO_KE_HOACH_SAN_XUAT SET isActived = 0 WHERE pro_chuyen_id = :chuyenId and DATEDIFF(day, NGAY_SAN_XUAT,GETDATE()) = 0 ")
    void unActiveAllProKHSXTodayOfChuyen(@Param("chuyenId") Long chuyenId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE PRO_KE_HOACH_SAN_XUAT SET isActived = 1, active_time=GETDATE() WHERE id = :khsxId and (isActived = 0 OR isActived IS NULL)")
    void activeProKHSXTodayByID(@Param("khsxId") Long khsxId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC updateCompleteKHSXTodayByID :khsxId")
    void updateCompleteKHSXTodayByID(@Param("khsxId") Long khsxId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "EXEC getAllManufacturePlanOnCheckPointToday_Mau :khsxId")
    List<KhsxMau> getKhsxMauToDay(@Param("khsxId") Long khsxId);

    @Query(nativeQuery = true, value = "EXEC PRO_DANHSACH_KIEMFINAL :chuyen, :byDate")
    List<KeHoachXuatHang> getKeHoachXuatHangToDay(@Param("chuyen") String chuyen, @Param("byDate") Date date);

    @Query(
        nativeQuery = true,
        value = "EXEC dbo.SP_GetDanhSachNhom :checkpointId, :chuyenId, :ngay"
    )
    List<DanhSachNhomChuyenProjection> spGetDanhSachNhom(
        @Param("checkpointId") Long checkpointId,
        @Param("chuyenId") Long chuyenId,
        @Param("ngay") Date ngay
    );

    @Query(
        nativeQuery = true,
        value = "EXEC dbo.SP_PermissionCheckpointOnBtnSXXong :checkpointId, :proNSCLId"
    )
    List<Object> spPermissionCheckpointOnBtnSXXong(
        @Param("checkpointId") Long checkpointId,
        @Param("proNSCLId") Long proNSCLId
    );

    /**
     * Danh sách KHSX hôm nay theo {@code po.PONo} — mapping pool query 2, slDaKiem = Qty + Fix + sua_po_luy_ke.
     */
    @Query(nativeQuery = true, value =
        "SELECT " +
        "  khsx.ID AS planId, " +
        "  khsx.NGAY_SAN_XUAT AS planManufactureDate, " +
        "  khsx.SO_LUONG_YEU_CAU AS planRequestedQuantity, " +
        "  khsx.TINH_TRANG AS planStatus, " +
        "  sp.MA_SAN_PHAM AS maSanPham, " +
        "  sp.MA_DON_HANG AS maDonHang, " +
        "  SPPO.PO AS po, " +
        "  SPPO.CumPO AS cumPo, " +
        "  chuyen.TEN AS bandName, " +
        "  chuyen.STT AS bandIndex, " +
        "  b2.STT AS khtStt, " +
        "  ISNULL(( " +
        "    SELECT SUM( " +
        "      qty_1_h + qty_2_h + qty_3_h + qty_4_h + qty_5_h + qty_6_h + qty_7_h + qty_8_h + " +
        "      qty_9_h + qty_10_h + qty_11_h + qty_12_h + qty_13_h + qty_14_h + " +
        "      fix_1_h + fix_2_h + fix_3_h + fix_4_h + fix_5_h + fix_6_h + fix_7_h + fix_8_h + " +
        "      fix_9_h + fix_10_h + fix_11_h + fix_12_h + fix_13_h + fix_14_h + " +
        "      ISNULL(a1.sua_po_luy_ke, 0) " +
        "    ) " +
        "    FROM pro_nscl a1 " +
        "    INNER JOIN pro_ke_hoach_san_xuat b1 ON a1.pro_ke_hoach_san_xuat_id = b1.id " +
        "    LEFT JOIN NV_KeHoachSanXuat b2x ON b1.oid_mapping = b2x.Oid " +
        "    INNER JOIN pro_san_pham c1 ON b1.pro_san_pham_id = c1.id " +
        "    LEFT JOIN DM_SanPham_PO spo ON c1.oid_mapping = spo.Oid " +
        "    WHERE c1.ma_don_hang = sp.ma_san_pham " +
        "      AND spo.CumPO = SPPO.CumPO " +
        "      AND b2x.Chuyen = chuyen.oid_mapping " +
        "      AND DATEDIFF(day, b1.ngay_san_xuat, GETDATE()) >= 0 " +
        "      AND b2x.Oid = khsx.oid_mapping " +
        "  ), 0) AS slDaKiem " +
        "FROM PRO_KE_HOACH_SAN_XUAT khsx " +
        "INNER JOIN PRO_CHUYEN chuyen ON khsx.PRO_CHUYEN_ID = chuyen.ID " +
        "INNER JOIN PRO_SAN_PHAM sp ON khsx.PRO_SAN_PHAM_ID = sp.ID " +
        "INNER JOIN DM_SanPham_PO SPPO ON SPPO.Oid = sp.oid_mapping " +
        "LEFT JOIN NV_KeHoachSanXuat b1 ON khsx.oid_mapping = b1.Oid " +
        "LEFT JOIN eGMF.dbo.OMM_KeHoachThang b2 ON b2.KHTId = b1.KHTId " +
        "LEFT JOIN eGMF.dbo.OMM_PO_ChiTiet b3 ON b2.CTPOId = b3.POCTId " +
        "INNER JOIN eGMF.dbo.OMM_PO po ON b3.POId = po.POId " +
        "WHERE po.PONo = :poNo " +
        "  AND ISNULL(khsx.isCompleted, 0) = 0 " +
        "  AND b2.TinhTrang = 'MoiTao' " +
        "  AND DATEDIFF(day, khsx.NGAY_SAN_XUAT, GETDATE()) = 0 " +
        "ORDER BY chuyen.STT, b2.STT")
    List<ManufacturePlanByPoProjection> findManufacturePlansByPoNo(@Param("poNo") String poNo);

}
