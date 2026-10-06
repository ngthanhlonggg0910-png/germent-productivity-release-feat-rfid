package vn.nvsoft.repository;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ProKiemtrongChiTietLoi;

@org.springframework.stereotype.Repository
public interface ProKiemtrongSanPhamRepository
    extends org.springframework.data.repository.Repository<ProKiemtrongChiTietLoi, Long> {

    interface ProKiemtrongSanPhamProjection {
        Long getId();

        Long getProCheckpointId();

        Long getProKhsxId();

        String getNguoiquet();

        Instant getThoigianquet();

        String getCheckedType();

        String getDisplayname();

        Long getProKiemtrongChiTietLoiId();

        String getCheckedTime();

        Boolean getIsTracking();

        String getValueHEX();

        String getRfidBarcode();
    }

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pro_kiemtrong_sanpham (" +
        "id, pro_checkpoint_id, pro_khsx_id, nguoiquet, thoigianquet, checked_type, " +
        "displayname, pro_kiemtrong_chi_tiet_loi_id, checked_time, isTracking, valueHEX, RFID_Barcode) " +
        "VALUES (NEXT VALUE FOR sequence_generator, :checkpointId, :khsxId, :nguoiQuet, :thoigianquet, 'LOI', " +
        ":displayName, :chiTietLoiId, :checkedTime, :isTracking, :valueHex, :rfidBarcode)", nativeQuery = true)
    void insertLoiSanPham(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId,
        @Param("nguoiQuet") String nguoiQuet,
        @Param("thoigianquet") Instant thoigianquet,
        @Param("displayName") String displayName,
        @Param("chiTietLoiId") Long chiTietLoiId,
        @Param("checkedTime") String checkedTime,
        @Param("isTracking") Boolean isTracking,
        @Param("valueHex") String valueHex,
        @Param("rfidBarcode") String rfidBarcode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pro_kiemtrong_sanpham (" +
        "id, pro_checkpoint_id, pro_khsx_id, nguoiquet, thoigianquet, checked_type, " +
        "displayname, pro_kiemtrong_chi_tiet_loi_id, checked_time, isTracking, valueHEX, RFID_Barcode) " +
        "VALUES (NEXT VALUE FOR sequence_generator, :checkpointId, :khsxId, :nguoiQuet, :thoigianquet, 'SUA', " +
        ":displayName, NULL, :checkedTime, :isTracking, :valueHex, :rfidBarcode)", nativeQuery = true)
    void insertSuaSanPham(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId,
        @Param("nguoiQuet") String nguoiQuet,
        @Param("thoigianquet") Instant thoigianquet,
        @Param("displayName") String displayName,
        @Param("checkedTime") String checkedTime,
        @Param("isTracking") Boolean isTracking,
        @Param("valueHex") String valueHex,
        @Param("rfidBarcode") String rfidBarcode);

    @Query(value = "SELECT id, pro_checkpoint_id AS proCheckpointId, pro_khsx_id AS proKhsxId, " +
        "nguoiquet, thoigianquet, checked_type AS checkedType, displayname, " +
        "pro_kiemtrong_chi_tiet_loi_id AS proKiemtrongChiTietLoiId, checked_time AS checkedTime, " +
        "isTracking, valueHEX, RFID_Barcode AS rfidBarcode " +
        "FROM pro_kiemtrong_sanpham " +
        "WHERE pro_checkpoint_id = :checkpointId AND pro_khsx_id = :khsxId AND checked_type = :checkedType " +
        "ORDER BY thoigianquet DESC", nativeQuery = true)
    List<ProKiemtrongSanPhamProjection> findByCheckpointAndKhsxAndCheckedType(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId,
        @Param("checkedType") String checkedType);

    @Query(value = "SELECT id, pro_checkpoint_id AS proCheckpointId, pro_khsx_id AS proKhsxId, " +
        "nguoiquet, thoigianquet, checked_type AS checkedType, displayname, " +
        "pro_kiemtrong_chi_tiet_loi_id AS proKiemtrongChiTietLoiId, checked_time AS checkedTime, " +
        "isTracking, valueHEX, RFID_Barcode AS rfidBarcode " +
        "FROM pro_kiemtrong_sanpham " +
        "WHERE pro_checkpoint_id = :checkpointId AND pro_khsx_id = :khsxId " +
        "AND checked_type IN ('LOI', 'SUA') " +
        "ORDER BY thoigianquet DESC", nativeQuery = true)
    List<ProKiemtrongSanPhamProjection> findLoiAndSuaByCheckpointAndKhsx(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pro_kiemtrong_sanpham WHERE pro_kiemtrong_chi_tiet_loi_id = :chiTietLoiId",
        nativeQuery = true)
    void deleteByChiTietLoiId(@Param("chiTietLoiId") Long chiTietLoiId);

    @Query(value = "SELECT pro_kiemtrong_chi_tiet_loi_id FROM pro_kiemtrong_sanpham " +
        "WHERE pro_checkpoint_id = :checkpointId AND pro_khsx_id = :khsxId " +
        "AND checked_type = 'LOI' AND displayname = :displayName " +
        "AND pro_kiemtrong_chi_tiet_loi_id IS NOT NULL",
        nativeQuery = true)
    List<Long> findChiTietLoiIdsByDisplayName(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId,
        @Param("displayName") String displayName);

    @Query(value = "SELECT COUNT(*) FROM pro_kiemtrong_sanpham " +
        "WHERE pro_checkpoint_id = :checkpointId AND pro_khsx_id = :khsxId " +
        "AND checked_type = 'LOI' AND displayname = :displayName",
        nativeQuery = true)
    int countLoiByDisplayName(
        @Param("checkpointId") Long checkpointId,
        @Param("khsxId") Long khsxId,
        @Param("displayName") String displayName);

    @Query(value = "SELECT COUNT(*) FROM pro_kiemtrong_sanpham " +
        "WHERE pro_checkpoint_id = :checkpointId AND pro_khsx_id = :khsxId AND checked_type = 'LOI'",
        nativeQuery = true)
    int countLoiByCheckpointAndKhsx(@Param("checkpointId") Long checkpointId, @Param("khsxId") Long khsxId);
}
