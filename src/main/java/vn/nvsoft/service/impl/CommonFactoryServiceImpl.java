package vn.nvsoft.service.impl;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.filestorage.StorageProperties;
import vn.nvsoft.repository.CommonFactoryRepository;
import vn.nvsoft.repository.ProKeHoachSanXuatRepository;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.service.*;
import vn.nvsoft.service.dto.BtnSXXongPermissionDTO;
import vn.nvsoft.service.dto.DanhSachNhomChuyenDTO;
import vn.nvsoft.service.dto.ManufacturePlanByPoDTO;
import vn.nvsoft.service.dto.ManufacturePlanViewModelDTO;
import vn.nvsoft.service.dto.SynchResultDTO;
import vn.nvsoft.service.dto.UserDTO;
import vn.nvsoft.service.viewmodel.*;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;

import java.util.*;

@Service
public class CommonFactoryServiceImpl implements CommonFactoryService {

    private final Logger log = LoggerFactory.getLogger(CommonFactoryServiceImpl.class);

    private UserService m_userService;
    private CommonFactoryRepository m_commonFactoryRepository;
    private final StorageProperties m_storageProperties;
    private final ProNSCLRepository m_proNSCLRepository;
    private final ProKeHoachSanXuatRepository m_proKeHoachSanXuatRepository;
    private final ProNSCLService m_proNSCLService;

    public CommonFactoryServiceImpl(
        UserService userService,
        CommonFactoryRepository commonFactoryRepository,
        StorageProperties storageProperties,
        ProNSCLRepository proNSCLRepository,
        ProKeHoachSanXuatRepository proKeHoachSanXuatRepository,
        ProNSCLService proNSCLService
    ) {
        this.m_userService = userService;
        this.m_commonFactoryRepository = commonFactoryRepository;
        m_storageProperties = storageProperties;
        m_proNSCLRepository = proNSCLRepository;
        m_proKeHoachSanXuatRepository = proKeHoachSanXuatRepository;
        m_proNSCLService = proNSCLService;
    }

    @Override
    public List<UserCheckPointViewModel> getUserWorkingCheckpoints(String username) {
        return m_commonFactoryRepository.getAllWorkingCheckPointForUser(username);
    }

    @Override
    public List<UserCheckPointViewModel> getAllCheckPointForCurrentUser() {
        log.debug("Service: getAllCheckPointForCurrentUser - Getting current user");
        UserDTO currentUser = m_userService.getUserWithAuthorities().map(UserDTO::new).get();
        log.debug("Service: getAllCheckPointForCurrentUser - Current user: {}", currentUser.getLogin());

        List<UserCheckPointViewModel> checkPoints = m_commonFactoryRepository.getAllWorkingCheckPointForUser(currentUser.getLogin());
        log.debug("Service: getAllCheckPointForCurrentUser - Found {} checkpoints for user {}",
            checkPoints != null ? checkPoints.size() : 0, currentUser.getLogin());
        return checkPoints ;
    }

    private List<ManufacturePlanViewModelDTO> updateImageURL(List<ManufacturePlanViewModel> manufacturePlanViewModels, Long checkPointId) {
        List<ManufacturePlanViewModelDTO> results = Lists.newArrayList();
        for (ManufacturePlanViewModel manufacturePlanViewModel : manufacturePlanViewModels) {
            ManufacturePlanViewModelDTO manufacturePlanViewModelDTO = new ManufacturePlanViewModelDTO(manufacturePlanViewModel, m_storageProperties);
//            List<ProNSCL> nsclTheoKHAndCheckPoint = m_proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(checkPointId, manufacturePlanViewModelDTO.getPlanId());
//            if (nsclTheoKHAndCheckPoint != null && !nsclTheoKHAndCheckPoint.isEmpty()) {
//                manufacturePlanViewModelDTO.setTongHangDaKiem(sumDaKiem(nsclTheoKHAndCheckPoint.get(0)));
//            }

            results.add(manufacturePlanViewModelDTO);
        }
        return results;
    }

    private Integer sumDaKiem(ProNSCL proNSCL) {
        int result = 0;
        if (proNSCL.getQty1h() != null) {
            result += proNSCL.getQty1h();
        }
        if (proNSCL.getQty2h() != null) {
            result += proNSCL.getQty2h();
        }
        if (proNSCL.getQty3h() != null) {
            result += proNSCL.getQty3h();
        }
        if (proNSCL.getQty4h() != null) {
            result += proNSCL.getQty4h();
        }
        if (proNSCL.getQty5h() != null) {
            result += proNSCL.getQty5h();
        }
        if (proNSCL.getQty6h() != null) {
            result += proNSCL.getQty6h();
        }
        if (proNSCL.getQty7h() != null) {
            result += proNSCL.getQty7h();
        }
        if (proNSCL.getQty8h() != null) {
            result += proNSCL.getQty8h();
        }
        if (proNSCL.getQty9h() != null) {
            result += proNSCL.getQty9h();
        }
        if (proNSCL.getQty10h() != null) {
            result += proNSCL.getQty10h();
        }
        if (proNSCL.getQty11h() != null) {
            result += proNSCL.getQty11h();
        }
        if (proNSCL.getQty12h() != null) {
            result += proNSCL.getQty12h();
        }
        if (proNSCL.getQty13h() != null) {
            result += proNSCL.getQty13h();
        }
        if (proNSCL.getQty14h() != null) {
            result += proNSCL.getQty14h();
        }
        if (proNSCL.getFix1h() != null) {
            result += proNSCL.getFix1h();
        }
        if (proNSCL.getFix2h() != null) {
            result += proNSCL.getFix2h();
        }
        if (proNSCL.getFix3h() != null) {
            result += proNSCL.getFix3h();
        }
        if (proNSCL.getFix4h() != null) {
            result += proNSCL.getFix4h();
        }
        if (proNSCL.getFix5h() != null) {
            result += proNSCL.getFix5h();
        }
        if (proNSCL.getFix6h() != null) {
            result += proNSCL.getFix6h();
        }
        if (proNSCL.getFix7h() != null) {
            result += proNSCL.getFix7h();
        }
        if (proNSCL.getFix8h() != null) {
            result += proNSCL.getFix8h();
        }
        if (proNSCL.getFix9h() != null) {
            result += proNSCL.getFix9h();
        }
        if (proNSCL.getFix10h() != null) {
            result += proNSCL.getFix10h();
        }
        if (proNSCL.getFix11h() != null) {
            result += proNSCL.getFix11h();
        }
        if (proNSCL.getFix12h() != null) {
            result += proNSCL.getFix12h();
        }
        if (proNSCL.getFix13h() != null) {
            result += proNSCL.getFix13h();
        }
        if (proNSCL.getFix14h() != null) {
            result += proNSCL.getFix14h();
        }
        return result;
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpoint(Long checkpointId) {
        return updateImageURL(m_commonFactoryRepository.getAllPlansOnCheckPoint(checkpointId), checkpointId);
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointToday(Long checkpointId) {
        log.debug("Service: getAllManufacturePlansOnCheckpointToday - CheckpointId: {}", checkpointId);
        List<ManufacturePlanViewModel> plans = m_commonFactoryRepository.getAllManufacturePlanOnCheckPointToday(checkpointId);
        log.debug("Service: getAllManufacturePlansOnCheckpointToday - Found {} plans from repository",
            plans != null ? plans.size() : 0);

        List<ManufacturePlanViewModelDTO> result = updateImageURL(plans, checkpointId);
        log.debug("Service: getAllManufacturePlansOnCheckpointToday - Returned {} plans after processing",
            result != null ? result.size() : 0);
        return result;
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayUpdated(Long checkpointId) {
        log.debug("Service: getAllManufacturePlansOnCheckpointTodayUpdated - CheckpointId: {}", checkpointId);
        log.debug("Service: getAllManufacturePlansOnCheckpointTodayUpdated - Using updated stored procedure with REJECT included in slDaKiem");
        List<ManufacturePlanViewModel> plans = m_commonFactoryRepository.getAllManufacturePlanOnCheckPointTodayUpdated(checkpointId);
        log.debug("Service: getAllManufacturePlansOnCheckpointTodayUpdated - Found {} plans from repository",
            plans != null ? plans.size() : 0);

        List<ManufacturePlanViewModelDTO> result = updateImageURL(plans, checkpointId);
        log.debug("Service: getAllManufacturePlansOnCheckpointTodayUpdated - Returned {} plans after processing",
            result != null ? result.size() : 0);
        return result;
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayPoFix(Long checkpointId, Long idNhom) {
        log.debug("Service: getAllManufacturePlansOnCheckpointTodayPoFix - checkpointId: {}, idNhom: {}", checkpointId, idNhom);
        List<ManufacturePlanViewModel> plans =
            m_commonFactoryRepository.getAllManufacturePlanOnCheckPointTodayPoFix(checkpointId, idNhom);
        return updateImageURL(plans, checkpointId);
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getManufacturePlansOnCheckpointTodayPoFixByRfid(
        Long checkpointId,
        String rfid,
        Long idNhom
    ) {
        String trimmedRfid = rfid != null ? rfid.trim() : "";
        log.debug(
            "Service: getManufacturePlansOnCheckpointTodayPoFixByRfid - checkpointId: {}, idNhom: {}, rfid: {}",
            checkpointId,
            idNhom,
            trimmedRfid
        );
        if (trimmedRfid.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            List<ManufacturePlanViewModel> plans =
                m_commonFactoryRepository.getManufacturePlanOnCheckPointTodayByRfidPoFix(
                    trimmedRfid,
                    checkpointId,
                    idNhom
                );
            return updateImageURL(plans, checkpointId);
        } catch (DataAccessException ex) {
            // Proc cũ RETURN sớm khi không map → SQLServer "did not return a result set".
            if (isEmptyResultSetFromStoredProc(ex)) {
                log.warn(
                    "RFID manuplan lookup có early RETURN / không ResultSet (coi như không tìm thấy): {}",
                    trimmedRfid
                );
                return Collections.emptyList();
            }
            throw ex;
        }
    }

    private static boolean isEmptyResultSetFromStoredProc(Throwable ex) {
        Throwable cur = ex;
        while (cur != null) {
            String msg = cur.getMessage();
            if (msg != null && msg.toLowerCase(Locale.ROOT).contains("did not return a result set")) {
                return true;
            }
            cur = cur.getCause();
        }
        return false;
    }

    @Override
    public List<ManufacturePlanByPoDTO> getManufacturePlansByPoNo(String poNo) {
        String trimmed = poNo != null ? poNo.trim() : "";
        log.debug("Service: getManufacturePlansByPoNo - poNo: {}", trimmed);
        if (trimmed.isEmpty()) {
            return Collections.emptyList();
        }
        List<CommonFactoryRepository.ManufacturePlanByPoProjection> rows =
            m_commonFactoryRepository.findManufacturePlansByPoNo(trimmed);
        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }
        List<ManufacturePlanByPoDTO> out = new ArrayList<>(rows.size());
        for (CommonFactoryRepository.ManufacturePlanByPoProjection row : rows) {
            out.add(new ManufacturePlanByPoDTO(row));
        }
        return out;
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayAllComplete(Long checkpointId) {
        return updateImageURL(m_commonFactoryRepository.getAllManufacturePlanOnCheckPointTodayAllComplete(checkpointId), checkpointId);
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayOnDoThongSo(Long checkpointId) {
        return updateImageURL(m_commonFactoryRepository.getAllManufacturePlanOnCheckPointTodayOnDoThongSo(checkpointId), checkpointId);
    }

    @Override
    public List<ManufacturePlanViewModelDTO> getAllManufacturePlansOnCheckpointTodayOnRefinal(Long checkpointId) {
        return updateImageURL(m_commonFactoryRepository.getAllManufacturePlanOnCheckPointTodayOnRefinal(checkpointId), checkpointId);
    }

    @Override
    public List<CheckpointLocationViewModel> getCurrentCheckpointWorkShift(Long checkpointId) {
        return m_commonFactoryRepository.getCurrentCheckpointWorkShift(checkpointId);
    }

    @Override
    public UserWorkingLocationViewModel getUserWorkingLocation() {
        log.debug("Service: getUserWorkingLocation - Getting user working location");
        UserDTO currentUser = m_userService.getUserWithAuthorities().map(UserDTO::new).get();
        log.debug("Service: getUserWorkingLocation - Current user: {}", currentUser.getLogin());

        List<UserCheckPointViewModel> checkPoints = m_commonFactoryRepository.getAllWorkingCheckPointForUser(currentUser.getLogin());
        log.debug("Service: getUserWorkingLocation - Found {} checkpoints", checkPoints != null ? checkPoints.size() : 0);

        if (checkPoints.size() > 0) {
            Long checkpointId = checkPoints.get(0).getCheckpointId();
            log.debug("Service: getUserWorkingLocation - Getting work shift for checkpointId: {}", checkpointId);
            List<CheckpointLocationViewModel> data = getCurrentCheckpointWorkShift(checkpointId);

            if (data != null && !data.isEmpty()) {
                log.debug("Service: getUserWorkingLocation - Found work shift data for checkpointId: {}", checkpointId);
                UserWorkingLocationViewModel userWorkingLocationViewModel = new UserWorkingLocationViewModel(currentUser, data.get(0));
                return userWorkingLocationViewModel;
            }
        }
        log.warn("Service: getUserWorkingLocation - User {} chua duoc phan vo checkpoint", currentUser.getLogin());
        return new UserWorkingLocationViewModel(currentUser, new CheckpointLocationViewModel() {
            @Override
            public Long getCheckpointId() {
                return null;
            }

            @Override
            public String getCheckpointName() {
                return "";
            }

            @Override
            public Long getCheckpointCongDoanId() {
                return null;
            }

            @Override
            public Long getBandId() {
                return null;
            }

            @Override
            public Long getBandIndex() {
                return null;
            }

            @Override
            public String getBandName() {
                return "";
            }

            @Override
            public String getBandTypeName() {
                return null;
            }

            @Override
            public Long getBandTypeId() {
                return null;
            }

            @Override
            public Long getFactoryId() {
                return null;
            }

            @Override
            public String getFactoryName() {
                return "";
            }

            @Override
            public Long getFactoryIndex() {
                return null;
            }

            @Override
            public Long getCompanyId() {
                return null;
            }

            @Override
            public String getCompanyName() {
                return "NV Soft";
            }

            @Override
            public String getCompanyAddr() {
                return null;
            }

            @Override
            public String getCompanyTaxCode() {
                return null;
            }
        });
    }

    @Override
    public void updateActiveProKHSXToday(Long proKHSXId, Long proChuyenId) {
        log.info("Service: updateActiveProKHSXToday - ProKHSXId: {}, ProChuyenId: {}", proKHSXId, proChuyenId);
        log.debug("Service: updateActiveProKHSXToday - Step 1: Unactive all plans of chuyen {}", proChuyenId);
        m_commonFactoryRepository.unActiveAllProKHSXTodayOfChuyen(proChuyenId);
        log.debug("Service: updateActiveProKHSXToday - Step 2: Active plan {}", proKHSXId);
        m_commonFactoryRepository.activeProKHSXTodayByID(proKHSXId);
        log.info("Service: updateActiveProKHSXToday - Successfully updated active plan");
    }

    @Override
    public void updateCompleteProKHSXToday(Long proKHSXId) {
        ProKeHoachSanXuat khsx = m_proKeHoachSanXuatRepository.findById(proKHSXId)
            .orElseThrow(() -> new BadRequestAlertException(
                "Không tìm thấy kế hoạch sản xuất", "proKeHoachSanXuat", "khsx_not_found"));

        int soLuongPhanBo = khsx.getSoLuongYeuCau() != null ? khsx.getSoLuongYeuCau() : 0;
        int tongMayRa = m_proNSCLService.getTongMayRaPoolQuery2ByKhsxId(proKHSXId);

        if (tongMayRa != soLuongPhanBo) {
            throw new BadRequestAlertException(
                String.format(
                    "Chưa đủ số lượng phân bổ để SX Xong: đạt/sửa %d, phân bổ %d",
                    tongMayRa,
                    soLuongPhanBo
                ),
                "proKeHoachSanXuat",
                "sx_xong_so_luong_chua_du"
            );
        }

        m_commonFactoryRepository.updateCompleteKHSXTodayByID(proKHSXId);
    }

    /**
     * Excute getAllManufacturePlanOnCheckPointToday_Mau :khsxId
     * Lau ra tat ca Mau cua KHSX ID
     * @param khsxId
     * @return
     */
    @Override
    public List<KhsxMau> getKhsxMauToDay(Long khsxId) {
        return m_commonFactoryRepository.getKhsxMauToDay(khsxId);
    }

    @Override
    public SynchResultDTO synchManufacturePlanOnCheckPointToday(Long checkpointId) {
        log.info("Service: synchManufacturePlanOnCheckPointToday - CheckpointId: {}", checkpointId);
        CommonFactoryRepository.SynchResultProjection projection =
            m_commonFactoryRepository.synchManufacturePlanOnCheckPointToday(checkpointId);

        if (projection == null) {
            log.warn("Service: synchManufacturePlanOnCheckPointToday - Proc returned null projection");
            return new SynchResultDTO(0, "Không nhận được phản hồi từ stored procedure");
        }

        SynchResultDTO result = new SynchResultDTO(projection.getResult(), projection.getMessage());
        log.info("Service: synchManufacturePlanOnCheckPointToday - Result: {}, Message: {}",
            result.getResult(), result.getMessage());
        return result;
    }

    @Override
    public List<DanhSachNhomChuyenDTO> getDanhSachNhomChuyen(Long checkpointId, Long chuyenId, Date ngay) {
        log.debug("Service: getDanhSachNhomChuyen - checkpointId: {}, chuyenId: {}, ngay: {}", checkpointId, chuyenId, ngay);
        List<CommonFactoryRepository.DanhSachNhomChuyenProjection> rows =
            m_commonFactoryRepository.spGetDanhSachNhom(checkpointId, chuyenId, ngay);
        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }
        List<DanhSachNhomChuyenDTO> out = new ArrayList<>(rows.size());
        for (CommonFactoryRepository.DanhSachNhomChuyenProjection p : rows) {
            out.add(new DanhSachNhomChuyenDTO(p.getIdNhom(), p.getTenNhom()));
        }
        return out;
    }

    @Override
    public BtnSXXongPermissionDTO getPermissionBtnSXXong(Long checkpointId, Long proNSCLId) {
        log.debug("Service: getPermissionBtnSXXong - checkpointId: {}, proNSCLId: {}", checkpointId, proNSCLId);
        List<Object> rows =
            m_commonFactoryRepository.spPermissionCheckpointOnBtnSXXong(checkpointId, proNSCLId);
        if (rows == null || rows.isEmpty()) {
            return new BtnSXXongPermissionDTO(null);
        }
        Object first = rows.get(0);
        Object cell;
        if (first instanceof Object[] && ((Object[]) first).length > 0) {
            cell = ((Object[]) first)[0];
        } else {
            cell = first;
        }
        Boolean coQuyen = parseProcScalarToBoolean(cell);
        if (coQuyen == null) {
            log.warn("Service: getPermissionBtnSXXong - Unrecognized proc cell type: {} (class {})",
                cell, cell != null ? cell.getClass().getName() : "null");
        }
        return new BtnSXXongPermissionDTO(coQuyen);
    }

    /**
     * Proc {@code SP_PermissionCheckpointOnBtnSXXong} trả cột {@code is_valid} (thường BIT hoặc INT).
     */
    private static Boolean parseProcScalarToBoolean(Object cell) {
        if (cell == null) {
            return null;
        }
        if (cell instanceof Boolean) {
            return (Boolean) cell;
        }
        if (cell instanceof Number) {
            return ((Number) cell).intValue() != 0;
        }
        if (cell instanceof String) {
            String s = ((String) cell).trim();
            if (s.isEmpty()) {
                return null;
            }
            if ("1".equals(s) || "true".equalsIgnoreCase(s)) {
                return true;
            }
            if ("0".equals(s) || "false".equalsIgnoreCase(s)) {
                return false;
            }
            try {
                return Integer.parseInt(s) != 0;
            } catch (NumberFormatException ignored) {
                return null;
            }
        }
        return null;
    }
}
