package vn.nvsoft.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.function.Function;

import vn.nvsoft.domain.*;
import vn.nvsoft.domain.chuyen.CheckPointCode;
import vn.nvsoft.domain.enumeration.GioLam;
import vn.nvsoft.domain.nscl.MaHangChinhPhu;
import vn.nvsoft.domain.nscl.BamAnLuong;
import vn.nvsoft.filestorage.StorageProperties;
import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.service.ProKeHoachSanXuatService;
import vn.nvsoft.service.ProNSCLChiTietLoiService;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.repository.ProKeHoachSanXuatRepository;
import vn.nvsoft.repository.ProNsclLogRepository;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.repository.ProNSCLRepository.ProNSCLSanPhamCreateResult;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.dto.NSCLTheoGioDTO;
import vn.nvsoft.service.dto.ProCheckPointDTO;
import vn.nvsoft.service.dto.ProGioLamViecDTO;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiTheoGioHienTai;
import vn.nvsoft.service.dto.ProNSCLDTO;
import vn.nvsoft.service.dto.TopLoi;
import vn.nvsoft.service.dto.MaCayByPODTO;
import vn.nvsoft.service.dto.ApplyPoFixRequestDTO;
import vn.nvsoft.service.dto.PoFixPastDTO;
import vn.nvsoft.service.dto.TongMayRaDTO;
import vn.nvsoft.service.nscl.NsclMetricsAggregator;
import vn.nvsoft.service.nscl.NsclRejectFifo;
import vn.nvsoft.service.nscl.PoFixRfidSupport;
import vn.nvsoft.service.mapper.NSCLTheoGioMapper;
import vn.nvsoft.service.mapper.ProNSCLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.egmf.TemBarcodeTachCayService;
import vn.nvsoft.service.egmf.DecathlonRfidItemValidator;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProNSCL}.
 */
@Service
@Transactional
public class ProNSCLServiceImpl implements ProNSCLService {

    private final Logger log = LoggerFactory.getLogger(ProNSCLServiceImpl.class);

    private final ProNSCLRepository proNSCLRepository;

    private final ProNsclLogRepository proNsclLogRepository;

    private final ProKeHoachSanXuatRepository proKeHoachSanXuatRepository;

    private final ProNSCLMapper proNSCLMapper;

    private final ProGioLamViecService proGioLamViecService;

    private final NSCLTheoGioMapper nsclTheoGioMapper;
    private final ProKeHoachSanXuatService proKeHoachSanXuatService;
    private final ProCheckPointService proCheckPointService;
    private final ProKiemKiemVuotServiceImpl _proKiemKiemVuotService;

    private final TemBarcodeTachCayService temBarcodeTachCayService;

    private final ProNSCLChiTietLoiService proNSCLChiTietLoiService;
    private final StorageProperties m_storageProperties;

    public ProNSCLServiceImpl(ProNSCLRepository proNSCLRepository, ProNsclLogRepository proNsclLogRepository,
            ProKeHoachSanXuatRepository proKeHoachSanXuatRepository, ProNSCLMapper proNSCLMapper,
            ProGioLamViecService proGioLamViecService, NSCLTheoGioMapper nsclTheoGioMapper,
            ProNSCLChiTietLoiService proNSCLChiTietLoiService, StorageProperties storageProperties,
            ProKeHoachSanXuatService proKeHoachSanXuatService,
            ProCheckPointService proCheckPointService, ProKiemKiemVuotServiceImpl proKiemKiemVuotService,
            TemBarcodeTachCayService temBarcodeTachCayService) {
        this.proNSCLRepository = proNSCLRepository;
        this.proNsclLogRepository = proNsclLogRepository;
        this.proKeHoachSanXuatRepository = proKeHoachSanXuatRepository;
        this.proNSCLMapper = proNSCLMapper;
        this.proGioLamViecService = proGioLamViecService;
        this.nsclTheoGioMapper = nsclTheoGioMapper;
        this.proNSCLChiTietLoiService = proNSCLChiTietLoiService;
        m_storageProperties = storageProperties;
        this.proKeHoachSanXuatService = proKeHoachSanXuatService;
        this.proCheckPointService = proCheckPointService;
        _proKiemKiemVuotService = proKiemKiemVuotService;
        this.temBarcodeTachCayService = temBarcodeTachCayService;
    }

    /**
     * Save a proNSCL.
     *
     * @param proNSCLDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProNSCLDTO save(ProNSCLDTO proNSCLDTO) {
        log.debug("Request to save ProNSCL : {}", proNSCLDTO);
        ProNSCL proNSCL = proNSCLMapper.toEntity(proNSCLDTO);
        proNSCL = proNSCLRepository.save(proNSCL);
        return proNSCLMapper.toDto(proNSCL);
    }

    /**
     * Get all the proNSCLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProNSCLDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProNSCLS");
        return proNSCLRepository.findAll(pageable)
                .map(proNSCLMapper::toDto);
    }

    /**
     * Get one proNSCL by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProNSCLDTO> findOne(Long id) {
        log.debug("Request to get ProNSCL : {}", id);
        return proNSCLRepository.findById(id)
                .map(proNSCLMapper::toDto);
    }

    /**
     * Delete the proNSCL by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProNSCL : {}", id);
        proNSCLRepository.deleteById(id);
    }

    private ProGioLamViecDTO getGioLamViecHienTaiCuaChuyen(Long proCheckPointID) {
        log.debug("Service: getGioLamViecHienTaiCuaChuyen - ProCheckPointID: {}", proCheckPointID);
        Optional<ProCheckPoint> checkPoint = proCheckPointService.findOneProCheckPoint(proCheckPointID);
        String chuyen = ""; // quy uoc lay stt la ten chuyen trong proGrioLamViec
        if (checkPoint.isPresent()) {
            chuyen = checkPoint.get().getProChuyen().getStt().toString();
            log.debug("Service: getGioLamViecHienTaiCuaChuyen - Found checkpoint, chuyen STT: {}", chuyen);
        } else {
            log.warn("Service: getGioLamViecHienTaiCuaChuyen - Can not found check point id {}", proCheckPointID);
            return null;
        }
        ProGioLamViecDTO gioLamViec = proGioLamViecService.getGioLamViecHienTai(chuyen);
        if (gioLamViec != null) {
            log.debug("Service: getGioLamViecHienTaiCuaChuyen - Found work shift: ID={}, GioLam={}, GioBatDau={}, GioKetThuc={}",
                gioLamViec.getId(), gioLamViec.getGioLam(), gioLamViec.getGioBatDau(), gioLamViec.getGioKetThuc());
        } else {
            log.warn("Service: getGioLamViecHienTaiCuaChuyen - No work shift found for chuyen: {}", chuyen);
        }
        return gioLamViec;
    }

    /**
     * Lay NSCL theo gio
     * Nhung chi tiet loi lay het theo proNSCL id
     *
     * @param proCheckPointID
     * @param proKeHoachSanXuat
     * @return
     */
    @Override
    public NSCLTheoGioDTO getNSCLTheoGio(Long proCheckPointID, Long proKeHoachSanXuat) {
        log.debug("Service: getNSCLTheoGio - ProCheckPointID: {}, ProKeHoachSanXuat: {}", proCheckPointID, proKeHoachSanXuat);
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai != null) {
            log.debug("Service: getNSCLTheoGio - Found current work shift: ID={}, GioLam={}",
                gioLamHienTai.getId(), gioLamHienTai.getGioLam());
        } else {
            log.warn("Service: getNSCLTheoGio - No current work shift found for checkpoint {}", proCheckPointID);
        }
        return getNSCLTheoGio(proCheckPointID, proKeHoachSanXuat, gioLamHienTai);
    }

    @Override
    public NSCLTheoGioDTO getNSCLTheoGioLuyKe(Long proCheckPointID, Long proKeHoachSanXuat) {
        log.info("Service: getNSCLTheoGioLuyKe - ProCheckPointID: {}, ProKeHoachSanXuat: {}", proCheckPointID, proKeHoachSanXuat);
        log.info("Service: getNSCLTheoGioLuyKe - Action: Lấy tổng lũy kế cho tất cả các khung giờ");

        // Lấy giờ làm việc hiện tại
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai != null) {
            log.debug("Service: getNSCLTheoGioLuyKe - Current work shift: {}", gioLamHienTai.getGioLam());
        } else {
            log.warn("Service: getNSCLTheoGioLuyKe - No current work shift found");
        }

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID, proKeHoachSanXuat);

        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            ProNSCL proNSCL = proNSCLs.get(0);
            log.info("Service: getNSCLTheoGioLuyKe - Found existing ProNSCL with ID: {}", proNSCL.getId());

            // Lấy danh sách chi tiết lỗi
            List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos = proNSCLChiTietLoiService.getByNSCLId(proNSCL.getId());
            log.debug("Service: getNSCLTheoGioLuyKe - Found {} defect details",
                nsclChiTietLoiDtos != null ? nsclChiTietLoiDtos.size() : 0);

            // Sử dụng mapper để tính tổng lũy kế, truyền thêm giờ làm việc hiện tại
            NSCLTheoGioDTO result = nsclTheoGioMapper.toDtoLuyKe(proNSCL, nsclChiTietLoiDtos, gioLamHienTai);
            log.info("Service: getNSCLTheoGioLuyKe - Result - Qty (lũy kế): {}, Man (lũy kế): {}, Fix (lũy kế): {}, Reject (lũy kế): {}",
                result.getQty(), result.getMan(), result.getFix(), result.getReject());
            return result;
        } else {
            log.info("Service: getNSCLTheoGioLuyKe - No existing ProNSCL found, creating new one");
            ProNSCL proNsCL = new ProNSCL();
            ProCheckPoint proCheckPoint = new ProCheckPoint();
            proCheckPoint.setId(proCheckPointID);
            ProKeHoachSanXuat khsx = new ProKeHoachSanXuat();
            khsx.setId(proKeHoachSanXuat);
            proNsCL.setProCheckPoint(proCheckPoint);
            proNsCL.setProKeHoachSanXuat(khsx);
            ProNSCL proNSCL = proNSCLRepository.saveAndFlush(proNsCL);
            log.info("Service: getNSCLTheoGioLuyKe - Created new ProNSCL with ID: {}", proNSCL.getId());

            NSCLTheoGioDTO result = nsclTheoGioMapper.toDtoLuyKe(proNSCL, new ArrayList<>(), gioLamHienTai);
            log.debug("Service: getNSCLTheoGioLuyKe - Mapped new ProNSCL to DTO (lũy kế)");
            return result;
        }
    }

    private NSCLTheoGioDTO getNSCLTheoGio(Long proCheckPointID, Long proKeHoachSanXuat,
            ProGioLamViecDTO gioLamHienTai) {
        log.debug("Service: getNSCLTheoGio (private) - ProCheckPointID: {}, ProKeHoachSanXuat: {}",
            proCheckPointID, proKeHoachSanXuat);

        if (gioLamHienTai != null) {
            log.debug("Service: getNSCLTheoGio - Searching for existing ProNSCL");
            List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                    proKeHoachSanXuat);

            if (proNSCLs != null && !proNSCLs.isEmpty()) {
                log.info("Service: getNSCLTheoGio - Found existing ProNSCL with ID: {}", proNSCLs.get(0).getId());
                // get gio hien tai
                // List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos =
                // proNSCLChiTietLoiService.getNSCLChiTietTheoGio(proNSCLs.get(0).getId(),
                // gioLamHienTai.getGioLam());
                List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos = proNSCLChiTietLoiService
                        .getByNSCLId(proNSCLs.get(0).getId());
                log.debug("Service: getNSCLTheoGio - Found {} defect details",
                    nsclChiTietLoiDtos != null ? nsclChiTietLoiDtos.size() : 0);

                NSCLTheoGioDTO result = nsclTheoGioMapper.toDto(proNSCLs.get(0), gioLamHienTai, nsclChiTietLoiDtos);
                log.debug("Service: getNSCLTheoGio - Mapped to DTO: ID={}, Qty={}, Man={}, Fix={}, Reject={}",
                    result.getId(), result.getQty(), result.getMan(), result.getFix(), result.getReject());
                return result;
            }

            log.info("Service: getNSCLTheoGio - No existing ProNSCL found, creating new one");
            ProNSCL proNsCL = new ProNSCL();
            ProCheckPoint proCheckPoint = new ProCheckPoint();
            proCheckPoint.setId(proCheckPointID);
            ProKeHoachSanXuat khsx = new ProKeHoachSanXuat();
            khsx.setId(proKeHoachSanXuat);
            proNsCL.setProCheckPoint(proCheckPoint);
            proNsCL.setProKeHoachSanXuat(khsx);
            ProNSCL proNSCL = proNSCLRepository.saveAndFlush(proNsCL);
            log.info("Service: getNSCLTheoGio - Created new ProNSCL with ID: {}", proNSCL.getId());

            NSCLTheoGioDTO result = nsclTheoGioMapper.toDto(proNSCL, gioLamHienTai, new ArrayList<>());
            log.debug("Service: getNSCLTheoGio - Mapped new ProNSCL to DTO");
            return result;
        } else {
            log.warn("Service: getNSCLTheoGio - GioLamHienTai is null, returning null");
            return null;
        }
    }

    /**
     * check rfid code before increate hang dat
     *
     * @param proCheckPointID
     * @param proKeHoachSanXuatID
     * @param rfidCode
     * @return
     */
    public NSCLTheoGioDTO increaseHangDatTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, String rfidCode) {
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);
        List<CheckPointCode> codes = proNSCLRepository.getCheckPointCode(proCheckPointID);
        log.debug("rfidCode: {}", rfidCode);
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("increaseHangDatTheoGio: {}", gioLamHienTai.getGioLam());
        // --- LOG THÔNG TIN YÊU CẦU ---
        String tenCheckpoint = null;
        try {
            Optional<ProCheckPoint> checkPointOpt = proCheckPointService.findOneProCheckPoint(proCheckPointID);
            if (checkPointOpt.isPresent()) {
                tenCheckpoint = checkPointOpt.get().getTen();
            }
        } catch (Exception e) {
            log.warn("Không lấy được tên checkpoint: {}", e.getMessage());
        }

        String tenNguoiDangNhap = null;
        try {
            tenNguoiDangNhap = vn.nvsoft.security.SecurityUtils.getCurrentUserLogin().orElse(null);
        } catch (Exception e) {
            log.warn("Không lấy được tên người đăng nhập: {}", e.getMessage());
        }

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        Long proNSCLId = (proNSCLs != null && !proNSCLs.isEmpty()) ? proNSCLs.get(0).getId() : null;

        log.info("[DEBUG] Checkpoint: {}, User: {}, ProNSCLId: {}", tenCheckpoint, tenNguoiDangNhap, proNSCLId);
        // --- END LOG ---

        validateDecathlonRfidForQualityCheck(rfidCode);

        // Validation theo thứ tự:
        // 1. QuantityLimit (Summary_PO_QuetNhan)
        // 2. SoLuongYeuCau của KHSX đang bấm (PRO_KE_HOACH_SAN_XUAT.SO_LUONG_YEU_CAU)
        // 3. TongMayRa (ProKiemKiemVuot)
        // 4. BamAnLuong
        boolean canIncreaseByQuantityLimit = validateQuantityLimit(proCheckPointID, proKeHoachSanXuatID);
        boolean canIncreaseByTongMayRa = validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
        boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
        log.debug("Service: increaseHangDatTheoGio (RFID) - CanIncreaseByQuantityLimit: {}, CanIncreaseByTongMayRa: {}, AllowBamAnLuong: {}", 
            canIncreaseByQuantityLimit, canIncreaseByTongMayRa, allowBamAnLuong);

        if (!canIncreaseByQuantityLimit || !canIncreaseByTongMayRa || !allowBamAnLuong) {
            log.warn("Service: increaseHangDatTheoGio (RFID) - Cannot increase due to validation checks");
            if (!canIncreaseByQuantityLimit) {
                // Exception đã được throw trong validateQuantityLimit
                return null;
            }
            if (!canIncreaseByTongMayRa) {
                throw new BadRequestAlertException("BTP chưa đồng bộ!", "", "BTP_chua_dong_bo");
            }
            // allowBamAnLuong sẽ throw exception trong method
        }

        ProNSCLSanPhamCreateResult result = proNSCLRepository.proNsclSanPhamCreate(proNSCLId, tenNguoiDangNhap,
                proCheckPointID, proKeHoachSanXuatID, "DAT", rfidCode, null, gioLamHienTai.getGioLam().toString());
        log.debug("result: id - {}, error - {}", result.getNewId(), result.getError());
        if (result.getError() != null && !result.getError().isEmpty()) {
            log.warn("Lỗi khi tạo sản phẩm NSCL: {}", result.getError());
            throw new BadRequestAlertException(result.getError(), "", "Lỗi khi tạo sản phẩm NSCL");
        }
        Long newId = result.getNewId();
        if (newId == null) {
            log.warn("Không tạo được sản phẩm NSCL mới");
            throw new BadRequestAlertException("Không tạo được sản phẩm NSCL mới", "",
                    "Không tạo được sản phẩm NSCL mới");
        } else {
            NSCLTheoGioDTO resultDTO = increaseHangDatTheoGio(proCheckPointID, proKeHoachSanXuatID);
            // Cập nhật lại TongMayRa sau khi đã tăng (method increaseHangDatTheoGio đã cập nhật, nhưng đảm bảo chắc chắn)
            updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
            return resultDTO;
        }
        // log.debug("newId: {}", newId);

        // if (codes.stream().anyMatch(checkPointCode ->
        // checkPointCode.getCode().equalsIgnoreCase(rfidCode))) {
        // return increaseHangDatTheoGio(proCheckPointID, proKeHoachSanXuatID);
        // }
        // return getNSCLTheoGio(proCheckPointID, proKeHoachSanXuatID);
    }

    public NSCLTheoGioDTO increaseHangDatTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID) {
        log.info("Service: increaseHangDatTheoGio - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: increaseHangDatTheoGio - Action: Tăng hàng đạt (DAT) - NÚT XANH");
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Service: increaseHangDatTheoGio - Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("Service: increaseHangDatTheoGio - GioLam: {}", gioLamHienTai.getGioLam());

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        ProNSCL proNsCL = null;
        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            proNsCL = proNSCLs.get(0);
            log.debug("Service: increaseHangDatTheoGio - Found existing ProNSCL ID: {}", proNsCL.getId());
        } else {
            log.info("Service: increaseHangDatTheoGio - Creating new ProNSCL");
            // create new
            proNsCL = new ProNSCL();
            ProCheckPoint proCheckPoint = new ProCheckPoint();
            proCheckPoint.setId(proCheckPointID);
            ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat();
            proKeHoachSanXuat.setId(proKeHoachSanXuatID);
            proNsCL.setProCheckPoint(proCheckPoint);
            proNsCL.setProKeHoachSanXuat(proKeHoachSanXuat);
        }

        boolean canIncreaseByQuantityLimit = validateQuantityLimit(proCheckPointID, proKeHoachSanXuatID);
        boolean canIncreaseByTongMayRa = validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
        boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
        log.debug("Service: increaseHangDatTheoGio - CanIncreaseByQuantityLimit: {}, CanIncreaseByTongMayRa: {}, AllowBamAnLuong: {}", 
            canIncreaseByQuantityLimit, canIncreaseByTongMayRa, allowBamAnLuong);

        if (canIncreaseByQuantityLimit && canIncreaseByTongMayRa && allowBamAnLuong) {
            log.info("Service: increaseHangDatTheoGio - Increasing hang dat for GioLam: {}", gioLamHienTai.getGioLam());
            increaseHangDatTheoGio(proNsCL, gioLamHienTai.getGioLam());
            proNSCLRepository.saveAndFlush(proNsCL);
            // Cập nhật lại TongMayRa sau khi đã tăng
            updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
            log.info("Service: increaseHangDatTheoGio - Successfully increased and saved ProNSCL");
        } else {
            log.warn("Service: increaseHangDatTheoGio - Cannot increase due to validation checks");
        }

        // Trả về dữ liệu lũy kế thay vì theo giờ
        NSCLTheoGioDTO result = getNSCLTheoGioLuyKe(proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: increaseHangDatTheoGio - Result (lũy kế) - Qty: {}, Man (DAT): {}",
            result != null ? result.getQty() : null, result != null ? result.getMan() : null);
        return result;
    }

    /**
     * Ghi log thời gian bấm NSCL: insert lần đầu, update thời gian bấm lần cuối nếu đã có (theo checkpoint + KHSX).
     */
    private void recordNsclBamLog(Long proCheckPointID, Long proKeHoachSanXuatID) {
        try {
            Instant now = Instant.now();
            String nguoiCapNhat = SecurityUtils.getCurrentUserLogin().orElse(null);

            Long proNsclId = null;
            List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
            if (proNSCLs != null && !proNSCLs.isEmpty()) {
                proNsclId = proNSCLs.get(0).getId();
            }

            Optional<ProNsclLog> existing = proNsclLogRepository.findByCheckPointIdAndKhsxId(proCheckPointID,
                proKeHoachSanXuatID);
            if (existing.isPresent()) {
                ProNsclLog nsclLog = existing.get();
                nsclLog.setThoiGianBamLanCuoi(now);
                nsclLog.setNguoiCapNhat(nguoiCapNhat);
                if (proNsclId != null) {
                    nsclLog.setProNsclId(proNsclId);
                }
                proNsclLogRepository.save(nsclLog);
                log.debug("Service: recordNsclBamLog - Updated last press time for checkpoint {} khsx {}",
                    proCheckPointID, proKeHoachSanXuatID);
                return;
            }

            Optional<ProKeHoachSanXuat> khsxOpt = proKeHoachSanXuatRepository.findOneWithDetails(proKeHoachSanXuatID);
            if (!khsxOpt.isPresent()) {
                log.warn("Service: recordNsclBamLog - KHSX {} not found", proKeHoachSanXuatID);
                return;
            }
            ProKeHoachSanXuat khsx = khsxOpt.get();
            ProNsclLog nsclLog = new ProNsclLog();
            nsclLog.setCheckPointId(proCheckPointID);
            nsclLog.setKhsxId(proKeHoachSanXuatID);
            nsclLog.setThoiGianBamLanDau(now);
            nsclLog.setThoiGianBamLanCuoi(now);
            nsclLog.setNguoiCapNhat(nguoiCapNhat);
            nsclLog.setProNsclId(proNsclId);
            nsclLog.setNgaySanXuat(khsx.getNgaySanXuat());
            if (khsx.getProChuyen() != null) {
                nsclLog.setChuyen(khsx.getProChuyen().getTen());
            }
            if (khsx.getProSanPham() != null) {
                nsclLog.setProSanPhamId(khsx.getProSanPham().getId());
                nsclLog.setMaHang(khsx.getProSanPham().getMaSanPham());
                nsclLog.setPo(khsx.getProSanPham().getPo());
            }
            proNsclLogRepository.save(nsclLog);
            log.debug("Service: recordNsclBamLog - Created first press log for checkpoint {} khsx {}",
                proCheckPointID, proKeHoachSanXuatID);
        } catch (Exception e) {
            log.warn("Service: recordNsclBamLog - Failed for checkpoint {} khsx {}: {}",
                proCheckPointID, proKeHoachSanXuatID, e.getMessage());
        }
    }

    private boolean isAllowBamAnLuong(Long proCheckPointID, Long proKeHoachSanXuatID) {
        List<BamAnLuong> bamAnLuongs = proNSCLRepository.capNhatBamAnLuong(proKeHoachSanXuatID, proCheckPointID);
        if (bamAnLuongs.isEmpty()) {
            return true;
        }
        log.warn(
                "Khong the bam vuot so luong {} đã khai báo lương tính từ ngày {} cho checkPoint ID {} va Pro KHSX ID {}",
                bamAnLuongs.get(0).getMaxAnLuong(), bamAnLuongs.get(0).getStartDate(), proCheckPointID,
                proKeHoachSanXuatID);
        throw new BadRequestAlertException(
                "Không thể bấm vượt số lượng " + bamAnLuongs.get(0).getMaxAnLuong() + " đã khai báo lương tính từ ngày "
                        + bamAnLuongs.get(0).getStartDate(),
                "", "Không thể bấm vượt số lượng " + bamAnLuongs.get(0).getMaxAnLuong()
                        + " đã khai báo lương tính từ ngày " + bamAnLuongs.get(0).getStartDate());
    }

    /**
     * Kiểm tra giới hạn số lượng dựa trên bảng Summary_PO_QuetNhan
     * Validate này được thực hiện TRƯỚC khi validate TongMayRa
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return true nếu có thể tăng (chưa vượt giới hạn), false nếu đã vượt hoặc không tìm thấy
     */
    private boolean validateQuantityLimit(Long proCheckPointID, Long proKeHoachSanXuatID) {
        log.debug("Service: validateQuantityLimit - START - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", 
            proCheckPointID, proKeHoachSanXuatID);
        
        Optional<ProCheckPointDTO> checkPointOpt = this.proCheckPointService.findOne(proCheckPointID);
        if (checkPointOpt.isPresent()) {
            ProCheckPointDTO checkPoint = checkPointOpt.get();
            log.debug("Service: validateQuantityLimit - CheckPoint Type: {}", checkPoint.getType());
            
            if (checkPoint.getType() != null && ProCheckPoint.TYPE_QC.equalsIgnoreCase(checkPoint.getType())) {
                Optional<ProKeHoachSanXuatDTO> proKHSXOpt = this.proKeHoachSanXuatService.findOne(proKeHoachSanXuatID);
                if (proKHSXOpt.isPresent()) {
                    ProKeHoachSanXuatDTO proKHSX = proKHSXOpt.get();
                    log.debug("Service: validateQuantityLimit - ProChuyenId: {}, ProSanPhamId: {}", 
                        proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                    
                    // Lấy số lượng giới hạn từ bảng Summary_PO_QuetNhan
                    Integer quantityLimit = proNSCLRepository.getQuantityLimitFromSummaryPO(proKeHoachSanXuatID);
                    
                    if (quantityLimit != null && quantityLimit > 0) {
                        log.debug("Service: validateQuantityLimit - Found quantity limit from Summary_PO_QuetNhan: {}", quantityLimit);
                        
                        List<ProNSCL> pool = loadNsclPoolForKhsx(proKeHoachSanXuatID, null);
                        NsclMetricsAggregator.Metrics metrics = NsclMetricsAggregator.aggregate(pool);
                        int tongBamSauTang = pool.isEmpty() ? 1 : metrics.getTongBam() + 1;

                        log.info("Service: validateQuantityLimit - TongBam sau khi tăng: {}, Quantity Limit: {}",
                            tongBamSauTang, quantityLimit);

                        if (tongBamSauTang <= quantityLimit) {
                            log.debug("Service: validateQuantityLimit - PASS - Có thể tăng (chưa vượt giới hạn Summary_PO_QuetNhan)");
                            return true;
                        } else {
                            log.warn("Service: validateQuantityLimit - FAIL - Vượt giới hạn Summary_PO_QuetNhan. TongBam: {}, Limit: {}",
                                tongBamSauTang, quantityLimit);
                            throw new BadRequestAlertException(
                                "Số lượng đơn hàng đang bấm vượt quá số lượng lũy kế nhận BTP", 
                                "", 
                                "vuot_gioi_han_summary_po");
                        }
                    } else {
                        log.warn(
                            "Service: validateQuantityLimit - FAIL - Chưa có dữ liệu BTP hoặc Quantity không hợp lệ từ Summary_PO_QuetNhan. Quantity: {}",
                            quantityLimit);
                        throw new BadRequestAlertException(
                            "BTP chưa được quét nhận hoặc số lượng nhận BTP bằng 0, không thể bấm NSCL",
                            "",
                            "btp_chua_quet_hoac_quantity_0");
                    }
                } else {
                    log.warn("Service: validateQuantityLimit - Can't found ProKHSX id {}", proKeHoachSanXuatID);
                    return true; // Không tìm thấy KHSX, cho phép tiếp tục
                }
            } else {
                log.debug("Service: validateQuantityLimit - CheckPoint is not QC type, skipping validate");
                return true; // Không phải QC, cho phép tiếp tục
            }
        } else {
            log.warn("Service: validateQuantityLimit - Can't found the check point id {}", proCheckPointID);
            return true; // Không tìm thấy checkpoint, cho phép tiếp tục
        }
    }

    private boolean validatePlanRequestedQuantityByPo(Long proCheckPointID, Long proKeHoachSanXuatID) {
        Optional<ProKeHoachSanXuatDTO> proKHSXOpt = proKeHoachSanXuatService.findOne(proKeHoachSanXuatID);
        if (!proKHSXOpt.isPresent()) {
            log.warn("Service: validatePlanRequestedQuantityByPo - ProKHSX not found: {}", proKeHoachSanXuatID);
            throw new BadRequestAlertException(
                "Không tìm thấy kế hoạch sản xuất",
                "",
                "khsx_not_found");
        }

        Integer soLuongYeuCau = proKHSXOpt.get().getSoLuongYeuCau();
        long planRequestedQuantity = soLuongYeuCau != null ? soLuongYeuCau.longValue() : 0L;

        if (planRequestedQuantity <= 0L) {
            log.warn(
                "Service: validatePlanRequestedQuantityByPo - Invalid soLuongYeuCau for khsxId: {}, checkpointId: {}",
                proKeHoachSanXuatID, proCheckPointID);
            throw new BadRequestAlertException(
                "Chưa có kế hoạch PO hoặc số lượng kế hoạch bằng 0, không thể bấm NSCL",
                "",
                "plan_requested_quantity_invalid");
        }

        List<ProNSCL> pool = loadNsclPoolForKhsx(proKeHoachSanXuatID, null);
        NsclMetricsAggregator.Metrics metrics = NsclMetricsAggregator.aggregate(pool);
        long tongMayRaSauTang = pool.isEmpty() ? 1L : (long) metrics.getTongMayRa() + 1L;

        log.info(
            "Service: validatePlanRequestedQuantityByPo - khsxId: {}, TongMayRa sau tăng: {}, SoLuongYeuCau: {}",
            proKeHoachSanXuatID, tongMayRaSauTang, planRequestedQuantity);

        if (tongMayRaSauTang <= planRequestedQuantity) {
            return true;
        }

        throw new BadRequestAlertException(
            "Số lượng bấm vượt quá kế hoạch PO",
            "",
            "vuot_gioi_han_ke_hoach_po");
    }

    /**
     * Kiểm tra có thể tăng: bước 2 giới hạn {@code SO_LUONG_YEU_CAU} KHSX, bước 3 giới hạn ProKiemKiemVuot
     * Áp dụng cho cả trường hợp có RFID và không có RFID
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return true nếu có thể tăng, false nếu không phải QC hoặc không tìm thấy
     */
    private boolean validateTongMayRa(Long proCheckPointID, Long proKeHoachSanXuatID) {
        Optional<ProCheckPointDTO> checkPointOpt = this.proCheckPointService.findOne(proCheckPointID);
        if (checkPointOpt.isPresent()) {
            ProCheckPointDTO checkPoint = checkPointOpt.get();
            if (checkPoint.getType() != null && ProCheckPoint.TYPE_QC.equalsIgnoreCase(checkPoint.getType())) {
                validatePlanRequestedQuantityByPo(proCheckPointID, proKeHoachSanXuatID);
                Optional<ProKeHoachSanXuatDTO> proKHSXOpt = this.proKeHoachSanXuatService.findOne(proKeHoachSanXuatID);
                if (proKHSXOpt.isPresent()) {
                    ProKeHoachSanXuatDTO proKHSX = proKHSXOpt.get();
                    Optional<ProKiemKiemVuot> proKiemKiemVuotOpt = _proKiemKiemVuotService
                            .findOneByChuyenIdAndProSanPhamId(proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                    if (proKiemKiemVuotOpt.isPresent()) {
                        ProKiemKiemVuot proKiemKiemVuot = proKiemKiemVuotOpt.get();

                        List<ProNSCL> pool = loadNsclPoolForKhsx(proKeHoachSanXuatID, null);
                        NsclMetricsAggregator.Metrics metrics = NsclMetricsAggregator.aggregate(pool);
                        int tongBamSauTang = pool.isEmpty() ? 1 : metrics.getTongBam() + 1;

                        if (proKiemKiemVuot.getSoLuongVaoChuyen() != null
                                && tongBamSauTang <= proKiemKiemVuot.getSoLuongVaoChuyen()
                                && proKiemKiemVuot.getSoLuongDonHang() != null
                                && tongBamSauTang <= proKiemKiemVuot.getSoLuongDonHang()) {
                            log.debug("Service: validateTongMayRa - Có thể tăng, TongBam sau khi tăng sẽ là: {}", tongBamSauTang);
                            return true;
                        } else {
                            log.warn(
                                    "Khong tang vi ProKiemKiemVuot id {} co so luong vao chuyen {} hoac so luong don hang {} va tong luy ke se la {}",
                                    proKiemKiemVuot.getId(), proKiemKiemVuot.getSoLuongVaoChuyen(),
                                    proKiemKiemVuot.getSoLuongDonHang(), tongBamSauTang);
                            throw new BadRequestAlertException("BTP chưa đồng bộ!", "", "BTP_chua_dong_bo");
                        }
                    } else {
                        log.warn("Khong tim thay ProKiemKiemVuot cho Pro Chuyen ID {} va Pro San Pham ID {}",
                                proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                        throw new BadRequestAlertException("Khong tim thay ProKiemKiemVuot cho Pro Chuyen ID"
                                + proKHSX.getProChuyenId() + " va Pro San Pham ID " + proKHSX.getProSanPhamId(), "",
                                "BTP_Kiem_Not_Found");
                    }

                } else {
                    log.warn("Can't found ProKHSX id {}", proKeHoachSanXuatID);
                }
            } else {
                return true;
            }
        } else {
            log.warn("Can't found the check point id {}", proCheckPointID);
        }
        return false;
    }

    /**
     * Cập nhật soLuongMayRa = TongMayRa OK (Qty + Fix + sua_po_luy_ke) trên pool query 2
     * Áp dụng cho cả trường hợp có RFID và không có RFID
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     */
    private void updateTongMayRaAfterIncrease(Long proCheckPointID, Long proKeHoachSanXuatID) {
        log.debug("Service: updateTongMayRaAfterIncrease - START - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        Optional<ProCheckPointDTO> checkPointOpt = this.proCheckPointService.findOne(proCheckPointID);
        if (checkPointOpt.isPresent()) {
            ProCheckPointDTO checkPoint = checkPointOpt.get();
            log.debug("Service: updateTongMayRaAfterIncrease - CheckPoint Type: {}", checkPoint.getType());
            if (checkPoint.getType() != null && ProCheckPoint.TYPE_QC.equalsIgnoreCase(checkPoint.getType())) {
                Optional<ProKeHoachSanXuatDTO> proKHSXOpt = this.proKeHoachSanXuatService.findOne(proKeHoachSanXuatID);
                if (proKHSXOpt.isPresent()) {
                    ProKeHoachSanXuatDTO proKHSX = proKHSXOpt.get();
                    log.debug("Service: updateTongMayRaAfterIncrease - ProChuyenId: {}, ProSanPhamId: {}", proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                    Optional<ProKiemKiemVuot> proKiemKiemVuotOpt = _proKiemKiemVuotService
                            .findOneByChuyenIdAndProSanPhamId(proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                    if (proKiemKiemVuotOpt.isPresent()) {
                        ProKiemKiemVuot proKiemKiemVuot = proKiemKiemVuotOpt.get();
                        log.debug("Service: updateTongMayRaAfterIncrease - Found ProKiemKiemVuot ID: {}, Current soLuongMayRa: {}",
                            proKiemKiemVuot.getId(), proKiemKiemVuot.getSoLuongMayRa());

                        List<ProNSCL> pool = loadNsclPoolForKhsx(proKeHoachSanXuatID, null);
                        NsclMetricsAggregator.Metrics metrics = NsclMetricsAggregator.aggregate(pool);
                        int tongMayRa = metrics.getTongMayRa();
                        log.info("Service: updateTongMayRaAfterIncrease - Pool size: {}, TongMayRa (Qty+Fix+sua_po): {}",
                            pool.size(), tongMayRa);

                        Integer oldSoLuongMayRa = proKiemKiemVuot.getSoLuongMayRa();
                        proKiemKiemVuot.setSoLuongMayRa(tongMayRa);
                        _proKiemKiemVuotService.save(proKiemKiemVuot);
                        log.info("Service: updateTongMayRaAfterIncrease - Updated soLuongMayRa from {} to {} (TongMayRa OK) in ProKiemKiemVuot ID: {}",
                            oldSoLuongMayRa, tongMayRa, proKiemKiemVuot.getId());
                    } else {
                        log.warn("Service: updateTongMayRaAfterIncrease - ProKiemKiemVuot not found for ChuyenId: {}, ProSanPhamId: {}",
                            proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
                    }
                } else {
                    log.warn("Service: updateTongMayRaAfterIncrease - ProKeHoachSanXuat not found for ID: {}", proKeHoachSanXuatID);
                }
            } else {
                log.debug("Service: updateTongMayRaAfterIncrease - CheckPoint is not QC type, skipping update");
            }
        } else {
            log.warn("Service: updateTongMayRaAfterIncrease - ProCheckPoint not found for ID: {}", proCheckPointID);
        }
        log.debug("Service: updateTongMayRaAfterIncrease - END");
    }

    /**
     * @deprecated Sử dụng validateTongMayRa và updateTongMayRaAfterIncrease thay thế
     * Giữ lại để tương thích ngược
     */
    @Deprecated
    private boolean isIncreaseHangDatLoiAndDoIncreaseTongMayRa(Long proCheckPointID, Long proKeHoachSanXuatID) {
        return validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
    }

    /**
     * @deprecated Sử dụng validateTongMayRa thay thế
     * Giữ lại để tương thích ngược
     */
    @Deprecated
    private boolean validateAndUpdateTongMayRa(Long proCheckPointID, Long proKeHoachSanXuatID) {
        return validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
    }

    public NSCLTheoGioDTO increaseHangTraTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID) {
        log.info("Service: increaseHangTraTheoGio - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: increaseHangTraTheoGio - Action: Tăng hàng sửa (SUA) - NÚT VÀNG (không có RFID)");
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Service: increaseHangTraTheoGio - Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("Service: increaseHangTraTheoGio - GioLam: {}", gioLamHienTai.getGioLam());

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        ProNSCL proNsCL = null;
        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            proNsCL = proNSCLs.get(0);
            log.debug("Service: increaseHangTraTheoGio - Found existing ProNSCL ID: {}", proNsCL.getId());
        } else {
            log.info("Service: increaseHangTraTheoGio - Creating new ProNSCL");
            // create new
            proNsCL = new ProNSCL();
            ProCheckPoint proCheckPoint = new ProCheckPoint();
            proCheckPoint.setId(proCheckPointID);
            ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat();
            proKeHoachSanXuat.setId(proKeHoachSanXuatID);
            proNsCL.setProCheckPoint(proCheckPoint);
            proNsCL.setProKeHoachSanXuat(proKeHoachSanXuat);
        }

        // Tính tổng lỗi lũy kế
        Integer totalRejectLuyKe = sumNullable(
            proNsCL.getReject1h(), proNsCL.getReject2h(), proNsCL.getReject3h(), proNsCL.getReject4h(),
            proNsCL.getReject5h(), proNsCL.getReject6h(), proNsCL.getReject7h(), proNsCL.getReject8h(),
            proNsCL.getReject9h(), proNsCL.getReject10h(), proNsCL.getReject11h(), proNsCL.getReject12h(),
            proNsCL.getReject13h(), proNsCL.getReject14h());

        log.debug("Service: increaseHangTraTheoGio - Tổng lỗi lũy kế: {}", totalRejectLuyKe);

        // Đếm số lỗi đã dành cho sản phẩm có RFID
        Long soLoiCoRfid = 0L;
        if (proNsCL.getId() != null) {
            soLoiCoRfid = proNSCLRepository.countLoiWithRfidByProNsclId(proNsCL.getId());
            log.debug("Service: increaseHangTraTheoGio - Số lỗi có RFID: {}", soLoiCoRfid);
        }

        // Số lỗi có thể trừ = tổng lỗi lũy kế - số lỗi đã dành cho RFID
        int soLoiCoTheTru = (totalRejectLuyKe != null ? totalRejectLuyKe : 0) - (soLoiCoRfid != null ? soLoiCoRfid.intValue() : 0);
        log.info("Service: increaseHangTraTheoGio - Số lỗi có thể trừ: {} (Tổng lỗi: {} - Lỗi có RFID: {})",
            soLoiCoTheTru, totalRejectLuyKe, soLoiCoRfid);

        // Kiểm tra điều kiện: không cho tăng sửa nếu hết lỗi có thể trừ
        if (soLoiCoTheTru <= 0) {
            String errorMessage = "Không thể tăng hàng sửa. ";
            if (totalRejectLuyKe == null || totalRejectLuyKe == 0) {
                errorMessage += "Hết số lỗi lũy kế trong NSCL này.";
            } else {
                errorMessage += String.format("Còn %d lỗi nhưng đã dành cho sản phẩm có RFID.", totalRejectLuyKe);
            }
            log.warn("Service: increaseHangTraTheoGio - {}", errorMessage);
            throw new BadRequestAlertException(errorMessage, "", "khong_the_tang_sua");
        }

        // Validation cho nút Sửa: Không cần validate QuantityLimit và TongMayRa
        // vì Sửa = tăng Fix + giảm Reject, tổng không đổi
        // Chỉ validate BamAnLuong để đảm bảo không vượt giới hạn ăn lương
        boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
        log.debug("Service: increaseHangTraTheoGio - AllowBamAnLuong: {}", allowBamAnLuong);

        if (allowBamAnLuong) {
            log.info("Service: increaseHangTraTheoGio - Increasing hang sua for GioLam: {}", gioLamHienTai.getGioLam());
            increaseHangTraTheoGio(proNsCL, gioLamHienTai.getGioLam());

            // Trừ lỗi theo lũy kế từ giờ sớm nhất (1h) đến muộn nhất (14h)
            boolean daTruLoi = decreaseHangLoiLuyKe(proNsCL);
            if (!daTruLoi) {
                log.error("Service: increaseHangTraTheoGio - Không thể trừ lỗi mặc dù đã kiểm tra có lỗi để trừ");
                throw new BadRequestAlertException("Lỗi hệ thống: Không thể trừ lỗi", "", "loi_he_thong");
            }
            log.info("Service: increaseHangTraTheoGio - Đã trừ 1 lỗi theo lũy kế");

            proNSCLRepository.saveAndFlush(proNsCL);
            // Cập nhật lại TongMayRa sau khi đã tăng (tổng không đổi vì tăng sửa = trừ lỗi, nhưng cập nhật để đảm bảo đồng bộ)
            updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
            log.info("Service: increaseHangTraTheoGio - Successfully increased hang sua and decreased reject");
        } else {
            log.warn("Service: increaseHangTraTheoGio - Cannot increase due to validation checks");
        }

        // Trả về dữ liệu lũy kế thay vì theo giờ
        NSCLTheoGioDTO result = getNSCLTheoGioLuyKe(proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: increaseHangTraTheoGio - Result (lũy kế) - Qty: {}, Fix (SUA): {}, Reject: {}",
            result != null ? result.getQty() : null, result != null ? result.getFix() : null, result != null ? result.getReject() : null);
        return result;
    }

    /**
     * Helper method để tính tổng các giá trị Integer (null-safe)
     */
    private Integer sumNullable(Integer... values) {
        int sum = 0;
        for (Integer value : values) {
            if (value != null) {
                sum += value;
            }
        }
        return sum;
    }

    public NSCLTheoGioDTO increaseHangTraTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, String rfid) {
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);
        List<CheckPointCode> codes = proNSCLRepository.getCheckPointCode(proCheckPointID);
        log.debug("rfidCode: {}", rfid);
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("increaseHangTraTheoGio: {}", gioLamHienTai.getGioLam());

        String tenNguoiDangNhap = null;
        try {
            tenNguoiDangNhap = SecurityUtils.getCurrentUserLogin().orElse(null);
        } catch (Exception e) {
            log.warn("Không lấy được tên người đăng nhập: {}", e.getMessage());
        }

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        Long proNSCLId = (proNSCLs != null && !proNSCLs.isEmpty()) ? proNSCLs.get(0).getId() : null;

        validateDecathlonRfidForQualityCheck(rfid);

        // Validation cho nút Sửa: Không cần validate vì Sửa = tăng Fix + giảm Reject, tổng không đổi
        // Chỉ validate BamAnLuong để đảm bảo không vượt giới hạn ăn lương
        boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
        log.debug("Service: increaseHangTraTheoGio (RFID) - AllowBamAnLuong: {}", allowBamAnLuong);

        if (!allowBamAnLuong) {
            log.warn("Service: increaseHangTraTheoGio (RFID) - Cannot increase due to BamAnLuong check");
            // allowBamAnLuong sẽ throw exception trong method
        }

        // Gọi store procedure tạo bản ghi pro_nscl_sanpham với loại SUA
        ProNSCLRepository.ProNSCLSanPhamCreateResult result = proNSCLRepository.proNsclSanPhamCreate(
                proNSCLId, tenNguoiDangNhap, proCheckPointID, proKeHoachSanXuatID, "SUA", rfid, null,
                gioLamHienTai.getGioLam().toString());
        log.debug("result: id - {}, error - {}", result.getNewId(), result.getError());
        if (result.getError() != null && !result.getError().isEmpty()) {
            log.warn("Lỗi khi tạo sản phẩm NSCL: {}", result.getError());
            throw new BadRequestAlertException(result.getError(), "", "Lỗi khi tạo sản phẩm NSCL");
        }
        Long newId = result.getNewId();
        if (newId == null) {
            log.warn("Không tạo được sản phẩm NSCL mới");
            throw new BadRequestAlertException("Không tạo được sản phẩm NSCL mới", "",
                    "Không tạo được sản phẩm NSCL mới");
        } else {
            NSCLTheoGioDTO resultDTO = increaseHangTraTheoGio(proCheckPointID, proKeHoachSanXuatID);
            // Cập nhật lại TongMayRa sau khi đã tăng (tổng không đổi vì tăng sửa = trừ lỗi, nhưng cập nhật để đảm bảo đồng bộ)
            updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
            return resultDTO;
        }
    }

    private void increaseHangTraTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setFix1h(proNsCL.getFix1h() + 1);
                break;
            case C_2H:
                proNsCL.setFix2h(proNsCL.getFix2h() + 1);
                break;
            case C_3H:
                proNsCL.setFix3h(proNsCL.getFix3h() + 1);
                break;
            case C_4H:
                proNsCL.setFix4h(proNsCL.getFix4h() + 1);
                break;
            case C_5H:
                proNsCL.setFix5h(proNsCL.getFix5h() + 1);
                break;
            case C_6H:
                proNsCL.setFix6h(proNsCL.getFix6h() + 1);
                break;
            case C_7H:
                proNsCL.setFix7h(proNsCL.getFix7h() + 1);
                break;
            case C_8H:
                proNsCL.setFix8h(proNsCL.getFix8h() + 1);
                break;
            case C_9H:
                proNsCL.setFix9h(proNsCL.getFix9h() + 1);
                break;
            case C_10H:
                proNsCL.setFix10h(proNsCL.getFix10h() + 1);
                break;
            case C_11H:
                proNsCL.setFix11h(proNsCL.getFix11h() + 1);
                break;
            case C_12H:
                proNsCL.setFix12h(proNsCL.getFix12h() + 1);
                break;
            case C_13H:
                proNsCL.setFix13h(proNsCL.getFix13h() + 1);
                break;
            case C_14H:
                proNsCL.setFix14h(proNsCL.getFix14h() + 1);
                break;
        }
    }

    public NSCLTheoGioDTO increaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, ProLoiDTO proLoiDTO,
            Boolean firstTime) {
        log.info("Service: increaseHangLoiTheoGio - ProCheckPointID: {}, ProKeHoachSanXuatID: {}, ProLoiID: {}, FirstTime: {}",
            proCheckPointID, proKeHoachSanXuatID, proLoiDTO != null ? proLoiDTO.getId() : null, firstTime);
        log.info("Service: increaseHangLoiTheoGio - Action: Tăng hàng lỗi (LOI) - NÚT ĐỎ");
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Service: increaseHangLoiTheoGio - Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("Service: increaseHangLoiTheoGio - GioLam: {}", gioLamHienTai.getGioLam());

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        ProNSCL proNsCL = null;
        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            proNsCL = proNSCLs.get(0);
            log.debug("Service: increaseHangLoiTheoGio - Found existing ProNSCL ID: {}", proNsCL.getId());
        } else {
            log.info("Service: increaseHangLoiTheoGio - Creating new ProNSCL");
            // create new
            proNsCL = new ProNSCL();
            ProCheckPoint proCheckPoint = new ProCheckPoint();
            proCheckPoint.setId(proCheckPointID);
            ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat();
            proKeHoachSanXuat.setId(proKeHoachSanXuatID);
            proNsCL.setProCheckPoint(proCheckPoint);
            proNsCL.setProKeHoachSanXuat(proKeHoachSanXuat);
        }

        boolean shouldIncrease = proNsCL.getId() == null || (firstTime != null && firstTime);
        log.debug("Service: increaseHangLoiTheoGio - ShouldIncrease: {} (ProNSCL ID: {}, FirstTime: {})",
            shouldIncrease, proNsCL.getId(), firstTime);

        if (shouldIncrease) {
            // Validation theo thứ tự: Summary_PO_QuetNhan -> SO_LUONG_YEU_CAU KHSX -> TongMayRa -> BamAnLuong
            boolean canIncreaseByQuantityLimit = validateQuantityLimit(proCheckPointID, proKeHoachSanXuatID);
            boolean canIncreaseByTongMayRa = validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
            boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
            log.debug("Service: increaseHangLoiTheoGio - CanIncreaseByQuantityLimit: {}, CanIncreaseByTongMayRa: {}, AllowBamAnLuong: {}", 
                canIncreaseByQuantityLimit, canIncreaseByTongMayRa, allowBamAnLuong);

            if (canIncreaseByQuantityLimit && canIncreaseByTongMayRa && allowBamAnLuong) {
                log.info("Service: increaseHangLoiTheoGio - Increasing hang loi for GioLam: {}", gioLamHienTai.getGioLam());
                increaseHangLoiTheoGio(proNsCL, gioLamHienTai.getGioLam());
                proNSCLRepository.saveAndFlush(proNsCL);
                // Cập nhật lại TongMayRa sau khi đã tăng
                updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
                log.info("Service: increaseHangLoiTheoGio - Successfully increased and saved ProNSCL");
            } else {
                log.warn("Service: increaseHangLoiTheoGio - Cannot increase due to validation checks");
            }
        } else {
            log.debug("Service: increaseHangLoiTheoGio - Skipping increase (not first time)");
        }

        // Trả về dữ liệu lũy kế thay vì theo giờ
        NSCLTheoGioDTO result = getNSCLTheoGioLuyKe(proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: increaseHangLoiTheoGio - Result (lũy kế) - Qty: {}, Reject (LOI): {}",
            result != null ? result.getQty() : null, result != null ? result.getReject() : null);
        return result;
    }

    @Override
    public NSCLTheoGioDTO increaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID, ProLoiDTO proLoiDTO,
            Boolean firstTime, Long chiTietLoiId, String rfid) {
        recordNsclBamLog(proCheckPointID, proKeHoachSanXuatID);
        // Logic giống increaseHangTraTheoGio với rfid, nhưng truyền chiTietLoiId vào
        // store procedure
        List<CheckPointCode> codes = proNSCLRepository.getCheckPointCode(proCheckPointID);
        log.debug("rfidCode: {}", rfid);
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("increaseHangLoiTheoGio: {}", gioLamHienTai.getGioLam());

        String tenNguoiDangNhap = null;
        try {
            tenNguoiDangNhap = SecurityUtils.getCurrentUserLogin().orElse(null);
        } catch (Exception e) {
            log.warn("Không lấy được tên người đăng nhập: {}", e.getMessage());
        }

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        Long proNSCLId = (proNSCLs != null && !proNSCLs.isEmpty()) ? proNSCLs.get(0).getId() : null;

        validateDecathlonRfidForQualityCheck(rfid);

        // Validation theo thứ tự: Summary_PO_QuetNhan -> SO_LUONG_YEU_CAU KHSX -> TongMayRa -> BamAnLuong
        boolean canIncreaseByQuantityLimit = validateQuantityLimit(proCheckPointID, proKeHoachSanXuatID);
        boolean canIncreaseByTongMayRa = validateTongMayRa(proCheckPointID, proKeHoachSanXuatID);
        boolean allowBamAnLuong = isAllowBamAnLuong(proCheckPointID, proKeHoachSanXuatID);
        log.debug("Service: increaseHangLoiTheoGio (RFID) - CanIncreaseByQuantityLimit: {}, CanIncreaseByTongMayRa: {}, AllowBamAnLuong: {}", 
            canIncreaseByQuantityLimit, canIncreaseByTongMayRa, allowBamAnLuong);

        if (!canIncreaseByQuantityLimit || !canIncreaseByTongMayRa || !allowBamAnLuong) {
            log.warn("Service: increaseHangLoiTheoGio (RFID) - Cannot increase due to validation checks");
            if (!canIncreaseByQuantityLimit) {
                // Exception đã được throw trong validateQuantityLimit
                return null;
            }
            if (!canIncreaseByTongMayRa) {
                throw new BadRequestAlertException("BTP chưa đồng bộ!", "", "BTP_chua_dong_bo");
            }
            // allowBamAnLuong sẽ throw exception trong method
        }

        // Gọi store procedure tạo bản ghi pro_nscl_sanpham với loại LOI, truyền
        // chiTietLoiId
        ProNSCLRepository.ProNSCLSanPhamCreateResult result = proNSCLRepository.proNsclSanPhamCreate(
                proNSCLId, tenNguoiDangNhap, proCheckPointID, proKeHoachSanXuatID, "LOI", rfid, chiTietLoiId,
                gioLamHienTai.getGioLam().toString());
        log.debug("result: id - {}, error - {}", result.getNewId(), result.getError());
        if (result.getError() != null && !result.getError().isEmpty()) {
            log.warn("Lỗi khi tạo sản phẩm NSCL: {}", result.getError());
            throw new BadRequestAlertException(result.getError(), "", "Lỗi khi tạo sản phẩm NSCL");
        }
        Long newId = result.getNewId();
        if (newId == null) {
            log.warn("Không tạo được sản phẩm NSCL mới");
            throw new BadRequestAlertException("Không tạo được sản phẩm NSCL mới", "",
                    "Không tạo được sản phẩm NSCL mới");
        } else {
            NSCLTheoGioDTO resultDTO = increaseHangLoiTheoGio(proCheckPointID, proKeHoachSanXuatID, proLoiDTO, firstTime);
            // Cập nhật lại TongMayRa sau khi đã tăng (method increaseHangLoiTheoGio không có validation, nên cần cập nhật)
            updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
            return resultDTO;
        }
    }

    private void increaseHangLoiTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setReject1h(proNsCL.getReject1h() + 1);
                break;
            case C_2H:
                proNsCL.setReject2h(proNsCL.getReject2h() + 1);
                break;
            case C_3H:
                proNsCL.setReject3h(proNsCL.getReject3h() + 1);
                break;
            case C_4H:
                proNsCL.setReject4h(proNsCL.getReject4h() + 1);
                break;
            case C_5H:
                proNsCL.setReject5h(proNsCL.getReject5h() + 1);
                break;
            case C_6H:
                proNsCL.setReject6h(proNsCL.getReject6h() + 1);
                break;
            case C_7H:
                proNsCL.setReject7h(proNsCL.getReject7h() + 1);
                break;
            case C_8H:
                proNsCL.setReject8h(proNsCL.getReject8h() + 1);
                break;
            case C_9H:
                proNsCL.setReject9h(proNsCL.getReject9h() + 1);
                break;
            case C_10H:
                proNsCL.setReject10h(proNsCL.getReject10h() + 1);
                break;
            case C_11H:
                proNsCL.setReject11h(proNsCL.getReject11h() + 1);
                break;
            case C_12H:
                proNsCL.setReject12h(proNsCL.getReject12h() + 1);
                break;
            case C_13H:
                proNsCL.setReject13h(proNsCL.getReject13h() + 1);
                break;
            case C_14H:
                proNsCL.setReject14h(proNsCL.getReject14h() + 1);
                break;
        }
    }

    private void increaseHangDatTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setQty1h(proNsCL.getQty1h() + 1);
                break;
            case C_2H:
                proNsCL.setQty2h(proNsCL.getQty2h() + 1);
                break;
            case C_3H:
                proNsCL.setQty3h(proNsCL.getQty3h() + 1);
                break;
            case C_4H:
                proNsCL.setQty4h(proNsCL.getQty4h() + 1);
                break;
            case C_5H:
                proNsCL.setQty5h(proNsCL.getQty5h() + 1);
                break;
            case C_6H:
                proNsCL.setQty6h(proNsCL.getQty6h() + 1);
                break;
            case C_7H:
                proNsCL.setQty7h(proNsCL.getQty7h() + 1);
                break;
            case C_8H:
                proNsCL.setQty8h(proNsCL.getQty8h() + 1);
                break;
            case C_9H:
                proNsCL.setQty9h(proNsCL.getQty9h() + 1);
                break;
            case C_10H:
                proNsCL.setQty10h(proNsCL.getQty10h() + 1);
                break;
            case C_11H:
                proNsCL.setQty11h(proNsCL.getQty11h() + 1);
                break;
            case C_12H:
                proNsCL.setQty12h(proNsCL.getQty12h() + 1);
                break;
            case C_13H:
                proNsCL.setQty13h(proNsCL.getQty13h() + 1);
                break;
            case C_14H:
                proNsCL.setQty14h(proNsCL.getQty14h() + 1);
                break;
        }
    }

    @Override
    public List<ProNSCLChiTietLoiDTO> getAllDetailDefects(Long nsclId) {
        return proNSCLChiTietLoiService.getByNSCLId(nsclId);
    }

    /**
     * execute LCD_Truc_Quan_Chuyen_NangSuatChatLuongTheoGio
     *
     * @param chuyen
     * @return
     */
    @Override
    public List<NsclTheoGio> getNSCLTheoGioTrenChuyen(Integer chuyen) {
        return proNSCLRepository.getNSCLTheoGioTrenChuyen(chuyen);
    }

    /**
     * execute LCD_Truc_Quan_Chuyen_ThongTinChung
     *
     * @param chuyen
     * @return
     */
    @Override
    public NsclTheoGioChuyenThongTinChung getNSCLTheoGioTrenChuyenThongTinChung(Integer chuyen) {
        return proNSCLRepository.getNSCLTheoGioTrenChuyenThongTinChung(chuyen);
    }

    public NangSuatLaoDongNgay getNangSuatLaoDongNgayTrenChuyen(Integer chuyen) {
        return proNSCLRepository.getNangSuatLaoDongNgayTrenChuyen(chuyen);
    }

    @Override
    public ProNSCLChiTietLoiTheoGioHienTai getChiTietNSCLTheoThoiGianTrenChuyen(Integer chuyen) {
        List<ChiTietNSCLTheoThoiGian> chiTietNSCLs = proNSCLRepository.getChiTietNSCLTheoThoiGianTrenChuyen(chuyen);

        ProNSCLChiTietLoiTheoGioHienTai results = new ProNSCLChiTietLoiTheoGioHienTai();

        Map<Long, TopLoi> groupLois = new HashMap<>();
        List<ProNSCLChiTietLoiDTO> proNSCLChiTietLoiDTOs = new ArrayList<>();
        String hinhMatTruoc = "";
        String hinhMatSau = "";
        for (ChiTietNSCLTheoThoiGian chiTietNSCL : chiTietNSCLs) {
            TopLoi topLoi = groupLois.computeIfAbsent(chiTietNSCL.getLoiId(), loiid -> buildTopLoi(chiTietNSCL));
            topLoi.setSoLuong(topLoi.getSoLuong() + 1);

            proNSCLChiTietLoiDTOs.add(buildNSCLChiTietLoi(chiTietNSCL));
            hinhMatTruoc = chiTietNSCL.getHinhMatTruocUrl();
            hinhMatSau = chiTietNSCL.getHinhMatSauUrl();
        }
        // top 3 dang loi
        List<TopLoi> allLois = new ArrayList<>(groupLois.values());
        allLois.sort((o1, o2) -> o2.getSoLuong().compareTo(o1.getSoLuong()));
        List<TopLoi> topLois = new ArrayList<>();
        int maxSize = allLois.size() >= 3 ? 3 : allLois.size();
        for (int i = 0; i < maxSize; i++) {
            topLois.add(allLois.get(i));
        }

        results.setUrlHinhMatTruoc(buildImageUrl(hinhMatTruoc));
        results.setUrlHinhMatSau(buildImageUrl(hinhMatSau));
        results.setChiTietLoiDTOS(proNSCLChiTietLoiDTOs);
        results.setTopLois(topLois);
        return results;
    }

    @Override
    public List<ProNSCL> findByProKeHoachSanXuat(Long proKeHoachSanXuat) {
        return proNSCLRepository.findByProKeHoachSanXuat(proKeHoachSanXuat);
    }

    @Override
    public List<MaHangChinhPhu> findMaHangChinhPhuByProKHSXId(Long proKHSXId) {
        log.debug("Service: findMaHangChinhPhuByProKHSXId - ProKHSXId: {}", proKHSXId);
        List<MaHangChinhPhu> result = proNSCLRepository.findMaHangChinhPhuByProKHSXId(proKHSXId);
        log.debug("Service: findMaHangChinhPhuByProKHSXId - Found {} ma hang chinh/phu",
            result != null ? result.size() : 0);
        return result;
    }

    @Override
    public void updateMaChungLoaiToProKHSX(Long proKHSXId, String maChungLoai) {
        proNSCLRepository.updateMaChungLoaiToProKHSX(proKHSXId, maChungLoai);
    }

    private String buildImageUrl(String hinh) {
        if (hinh == null || hinh.isEmpty()) {
            return ConstUtil.STATIC_ASSET + ConstUtil.DEFAULT_IMAGE;
        }
        return ConstUtil.STATIC_ASSET + hinh;
    }

    private ProNSCLChiTietLoiDTO buildNSCLChiTietLoi(ChiTietNSCLTheoThoiGian chiTietNSCL) {
        ProNSCLChiTietLoiDTO chiTietLoiDTO = new ProNSCLChiTietLoiDTO();
        chiTietLoiDTO.setMaLoi(chiTietNSCL.getMaLoi());
        chiTietLoiDTO.setDx(chiTietNSCL.getDx());
        chiTietLoiDTO.setDy(chiTietNSCL.getDy());
        chiTietLoiDTO.setFront(chiTietNSCL.getFront());
        chiTietLoiDTO.setInside(chiTietNSCL.getInside());
        ProLoiDTO proLoiDTO = new ProLoiDTO();
        proLoiDTO.setMauSac(chiTietNSCL.getMauSac());
        chiTietLoiDTO.setProLoi(proLoiDTO);
        return chiTietLoiDTO;
    }

    private TopLoi buildTopLoi(ChiTietNSCLTheoThoiGian chiTietNSCL) {
        return new TopLoi(chiTietNSCL.getTenLoi(), 0, chiTietNSCL.getMauSac());
    }

    @Override
    public NSCLTheoGioDTO getNSCLTheoGioByBarcode(Long proCheckPointID, String barcode) {
        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }
        // Lấy danh sách ProNSCL theo checkpoint, barcode (PO), ngày hiện tại
        List<ProNSCL> proNSCLs = proNSCLRepository.findByCheckPointIdAndPoAndNgaySanXuatToday(proCheckPointID, barcode);
        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos = proNSCLChiTietLoiService
                    .getByNSCLId(proNSCLs.get(0).getId());
            return nsclTheoGioMapper.toDto(proNSCLs.get(0), gioLamHienTai, nsclChiTietLoiDtos);
        }
        return null;
    }

    // Giảm số lượng hàng đạt theo giờ cụ thể
    private void decreaseHangDatTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setQty1h(Math.max(0, proNsCL.getQty1h() - 1));
                break;
            case C_2H:
                proNsCL.setQty2h(Math.max(0, proNsCL.getQty2h() - 1));
                break;
            case C_3H:
                proNsCL.setQty3h(Math.max(0, proNsCL.getQty3h() - 1));
                break;
            case C_4H:
                proNsCL.setQty4h(Math.max(0, proNsCL.getQty4h() - 1));
                break;
            case C_5H:
                proNsCL.setQty5h(Math.max(0, proNsCL.getQty5h() - 1));
                break;
            case C_6H:
                proNsCL.setQty6h(Math.max(0, proNsCL.getQty6h() - 1));
                break;
            case C_7H:
                proNsCL.setQty7h(Math.max(0, proNsCL.getQty7h() - 1));
                break;
            case C_8H:
                proNsCL.setQty8h(Math.max(0, proNsCL.getQty8h() - 1));
                break;
            case C_9H:
                proNsCL.setQty9h(Math.max(0, proNsCL.getQty9h() - 1));
                break;
            case C_10H:
                proNsCL.setQty10h(Math.max(0, proNsCL.getQty10h() - 1));
                break;
            case C_11H:
                proNsCL.setQty11h(Math.max(0, proNsCL.getQty11h() - 1));
                break;
            case C_12H:
                proNsCL.setQty12h(Math.max(0, proNsCL.getQty12h() - 1));
                break;
            case C_13H:
                proNsCL.setQty13h(Math.max(0, proNsCL.getQty13h() - 1));
                break;
            case C_14H:
                proNsCL.setQty14h(Math.max(0, proNsCL.getQty14h() - 1));
                break;
        }
    }

    // Giảm số lượng hàng lỗi theo giờ cụ thể
    private void decreaseHangLoiTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setReject1h(Math.max(0, proNsCL.getReject1h() - 1));
                break;
            case C_2H:
                proNsCL.setReject2h(Math.max(0, proNsCL.getReject2h() - 1));
                break;
            case C_3H:
                proNsCL.setReject3h(Math.max(0, proNsCL.getReject3h() - 1));
                break;
            case C_4H:
                proNsCL.setReject4h(Math.max(0, proNsCL.getReject4h() - 1));
                break;
            case C_5H:
                proNsCL.setReject5h(Math.max(0, proNsCL.getReject5h() - 1));
                break;
            case C_6H:
                proNsCL.setReject6h(Math.max(0, proNsCL.getReject6h() - 1));
                break;
            case C_7H:
                proNsCL.setReject7h(Math.max(0, proNsCL.getReject7h() - 1));
                break;
            case C_8H:
                proNsCL.setReject8h(Math.max(0, proNsCL.getReject8h() - 1));
                break;
            case C_9H:
                proNsCL.setReject9h(Math.max(0, proNsCL.getReject9h() - 1));
                break;
            case C_10H:
                proNsCL.setReject10h(Math.max(0, proNsCL.getReject10h() - 1));
                break;
            case C_11H:
                proNsCL.setReject11h(Math.max(0, proNsCL.getReject11h() - 1));
                break;
            case C_12H:
                proNsCL.setReject12h(Math.max(0, proNsCL.getReject12h() - 1));
                break;
            case C_13H:
                proNsCL.setReject13h(Math.max(0, proNsCL.getReject13h() - 1));
                break;
            case C_14H:
                proNsCL.setReject14h(Math.max(0, proNsCL.getReject14h() - 1));
                break;
        }
    }

    /**
     * Giảm số lượng hàng lỗi theo lũy kế từ giờ sớm nhất (1h) đến muộn nhất (14h)
     * Trừ 1 lỗi từ giờ đầu tiên có lỗi (từ 1h đến 14h)
     *
     * @param proNsCL ProNSCL entity cần trừ lỗi
     * @return true nếu đã trừ được lỗi, false nếu không còn lỗi để trừ
     */
    private boolean decreaseHangLoiLuyKe(ProNSCL proNsCL) {
        // Trừ từ giờ sớm nhất (1h) đến muộn nhất (14h)
        if (proNsCL.getReject1h() != null && proNsCL.getReject1h() > 0) {
            proNsCL.setReject1h(proNsCL.getReject1h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 1h, còn lại: {}", proNsCL.getReject1h());
            return true;
        }
        if (proNsCL.getReject2h() != null && proNsCL.getReject2h() > 0) {
            proNsCL.setReject2h(proNsCL.getReject2h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 2h, còn lại: {}", proNsCL.getReject2h());
            return true;
        }
        if (proNsCL.getReject3h() != null && proNsCL.getReject3h() > 0) {
            proNsCL.setReject3h(proNsCL.getReject3h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 3h, còn lại: {}", proNsCL.getReject3h());
            return true;
        }
        if (proNsCL.getReject4h() != null && proNsCL.getReject4h() > 0) {
            proNsCL.setReject4h(proNsCL.getReject4h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 4h, còn lại: {}", proNsCL.getReject4h());
            return true;
        }
        if (proNsCL.getReject5h() != null && proNsCL.getReject5h() > 0) {
            proNsCL.setReject5h(proNsCL.getReject5h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 5h, còn lại: {}", proNsCL.getReject5h());
            return true;
        }
        if (proNsCL.getReject6h() != null && proNsCL.getReject6h() > 0) {
            proNsCL.setReject6h(proNsCL.getReject6h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 6h, còn lại: {}", proNsCL.getReject6h());
            return true;
        }
        if (proNsCL.getReject7h() != null && proNsCL.getReject7h() > 0) {
            proNsCL.setReject7h(proNsCL.getReject7h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 7h, còn lại: {}", proNsCL.getReject7h());
            return true;
        }
        if (proNsCL.getReject8h() != null && proNsCL.getReject8h() > 0) {
            proNsCL.setReject8h(proNsCL.getReject8h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 8h, còn lại: {}", proNsCL.getReject8h());
            return true;
        }
        if (proNsCL.getReject9h() != null && proNsCL.getReject9h() > 0) {
            proNsCL.setReject9h(proNsCL.getReject9h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 9h, còn lại: {}", proNsCL.getReject9h());
            return true;
        }
        if (proNsCL.getReject10h() != null && proNsCL.getReject10h() > 0) {
            proNsCL.setReject10h(proNsCL.getReject10h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 10h, còn lại: {}", proNsCL.getReject10h());
            return true;
        }
        if (proNsCL.getReject11h() != null && proNsCL.getReject11h() > 0) {
            proNsCL.setReject11h(proNsCL.getReject11h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 11h, còn lại: {}", proNsCL.getReject11h());
            return true;
        }
        if (proNsCL.getReject12h() != null && proNsCL.getReject12h() > 0) {
            proNsCL.setReject12h(proNsCL.getReject12h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 12h, còn lại: {}", proNsCL.getReject12h());
            return true;
        }
        if (proNsCL.getReject13h() != null && proNsCL.getReject13h() > 0) {
            proNsCL.setReject13h(proNsCL.getReject13h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 13h, còn lại: {}", proNsCL.getReject13h());
            return true;
        }
        if (proNsCL.getReject14h() != null && proNsCL.getReject14h() > 0) {
            proNsCL.setReject14h(proNsCL.getReject14h() - 1);
            log.debug("Service: decreaseHangLoiLuyKe - Trừ lỗi ở giờ 14h, còn lại: {}", proNsCL.getReject14h());
            return true;
        }
        // Không còn lỗi để trừ
        log.warn("Service: decreaseHangLoiLuyKe - Không còn lỗi để trừ");
        return false;
    }

    // Giảm số lượng hàng trả theo giờ cụ thể
    private void decreaseHangTraTheoGio(ProNSCL proNsCL, GioLam gioLam) {
        switch (gioLam) {
            case C_1H:
                proNsCL.setFix1h(Math.max(0, proNsCL.getFix1h() - 1));
                break;
            case C_2H:
                proNsCL.setFix2h(Math.max(0, proNsCL.getFix2h() - 1));
                break;
            case C_3H:
                proNsCL.setFix3h(Math.max(0, proNsCL.getFix3h() - 1));
                break;
            case C_4H:
                proNsCL.setFix4h(Math.max(0, proNsCL.getFix4h() - 1));
                break;
            case C_5H:
                proNsCL.setFix5h(Math.max(0, proNsCL.getFix5h() - 1));
                break;
            case C_6H:
                proNsCL.setFix6h(Math.max(0, proNsCL.getFix6h() - 1));
                break;
            case C_7H:
                proNsCL.setFix7h(Math.max(0, proNsCL.getFix7h() - 1));
                break;
            case C_8H:
                proNsCL.setFix8h(Math.max(0, proNsCL.getFix8h() - 1));
                break;
            case C_9H:
                proNsCL.setFix9h(Math.max(0, proNsCL.getFix9h() - 1));
                break;
            case C_10H:
                proNsCL.setFix10h(Math.max(0, proNsCL.getFix10h() - 1));
                break;
            case C_11H:
                proNsCL.setFix11h(Math.max(0, proNsCL.getFix11h() - 1));
                break;
            case C_12H:
                proNsCL.setFix12h(Math.max(0, proNsCL.getFix12h() - 1));
                break;
            case C_13H:
                proNsCL.setFix13h(Math.max(0, proNsCL.getFix13h() - 1));
                break;
            case C_14H:
                proNsCL.setFix14h(Math.max(0, proNsCL.getFix14h() - 1));
                break;
        }
    }

    /**
     * Giảm số lượng hàng sửa theo lũy kế từ giờ muộn nhất (14h) đến sớm nhất (1h)
     * Trừ 1 sửa từ giờ cuối cùng có sửa (từ 14h về 1h)
     *
     * @param proNsCL ProNSCL entity cần trừ sửa
     * @return true nếu đã trừ được sửa, false nếu không còn sửa để trừ
     */
    private boolean decreaseHangTraLuyKe(ProNSCL proNsCL) {
        // Trừ từ giờ muộn nhất (14h) về sớm nhất (1h) - ngược lại với trừ lỗi
        if (proNsCL.getFix14h() != null && proNsCL.getFix14h() > 0) {
            proNsCL.setFix14h(proNsCL.getFix14h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 14h, còn lại: {}", proNsCL.getFix14h());
            return true;
        }
        if (proNsCL.getFix13h() != null && proNsCL.getFix13h() > 0) {
            proNsCL.setFix13h(proNsCL.getFix13h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 13h, còn lại: {}", proNsCL.getFix13h());
            return true;
        }
        if (proNsCL.getFix12h() != null && proNsCL.getFix12h() > 0) {
            proNsCL.setFix12h(proNsCL.getFix12h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 12h, còn lại: {}", proNsCL.getFix12h());
            return true;
        }
        if (proNsCL.getFix11h() != null && proNsCL.getFix11h() > 0) {
            proNsCL.setFix11h(proNsCL.getFix11h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 11h, còn lại: {}", proNsCL.getFix11h());
            return true;
        }
        if (proNsCL.getFix10h() != null && proNsCL.getFix10h() > 0) {
            proNsCL.setFix10h(proNsCL.getFix10h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 10h, còn lại: {}", proNsCL.getFix10h());
            return true;
        }
        if (proNsCL.getFix9h() != null && proNsCL.getFix9h() > 0) {
            proNsCL.setFix9h(proNsCL.getFix9h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 9h, còn lại: {}", proNsCL.getFix9h());
            return true;
        }
        if (proNsCL.getFix8h() != null && proNsCL.getFix8h() > 0) {
            proNsCL.setFix8h(proNsCL.getFix8h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 8h, còn lại: {}", proNsCL.getFix8h());
            return true;
        }
        if (proNsCL.getFix7h() != null && proNsCL.getFix7h() > 0) {
            proNsCL.setFix7h(proNsCL.getFix7h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 7h, còn lại: {}", proNsCL.getFix7h());
            return true;
        }
        if (proNsCL.getFix6h() != null && proNsCL.getFix6h() > 0) {
            proNsCL.setFix6h(proNsCL.getFix6h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 6h, còn lại: {}", proNsCL.getFix6h());
            return true;
        }
        if (proNsCL.getFix5h() != null && proNsCL.getFix5h() > 0) {
            proNsCL.setFix5h(proNsCL.getFix5h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 5h, còn lại: {}", proNsCL.getFix5h());
            return true;
        }
        if (proNsCL.getFix4h() != null && proNsCL.getFix4h() > 0) {
            proNsCL.setFix4h(proNsCL.getFix4h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 4h, còn lại: {}", proNsCL.getFix4h());
            return true;
        }
        if (proNsCL.getFix3h() != null && proNsCL.getFix3h() > 0) {
            proNsCL.setFix3h(proNsCL.getFix3h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 3h, còn lại: {}", proNsCL.getFix3h());
            return true;
        }
        if (proNsCL.getFix2h() != null && proNsCL.getFix2h() > 0) {
            proNsCL.setFix2h(proNsCL.getFix2h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 2h, còn lại: {}", proNsCL.getFix2h());
            return true;
        }
        if (proNsCL.getFix1h() != null && proNsCL.getFix1h() > 0) {
            proNsCL.setFix1h(proNsCL.getFix1h() - 1);
            log.debug("Service: decreaseHangTraLuyKe - Trừ sửa ở giờ 1h, còn lại: {}", proNsCL.getFix1h());
            return true;
        }
        // Không còn sửa để trừ
        log.warn("Service: decreaseHangTraLuyKe - Không còn sửa để trừ");
        return false;
    }

    /**
     * Tăng số lượng hàng lỗi theo lũy kế vào giờ sớm nhất (1h)
     * Tăng 1 lỗi vào giờ 1h
     *
     * @param proNsCL ProNSCL entity cần tăng lỗi
     * @return true nếu đã tăng được lỗi, false nếu không thể tăng
     */
    private boolean increaseHangLoiLuyKe(ProNSCL proNsCL) {
        // Tăng vào giờ sớm nhất (1h) - giống logic tăng lỗi bình thường
        if (proNsCL.getReject1h() == null) {
            proNsCL.setReject1h(1);
        } else {
            proNsCL.setReject1h(proNsCL.getReject1h() + 1);
        }
        log.debug("Service: increaseHangLoiLuyKe - Tăng lỗi ở giờ 1h, tổng: {}", proNsCL.getReject1h());
        return true;
    }

    @Override
    public NSCLTheoGioDTO decreaseHangTraAndIncreaseHangLoiTheoGio(Long proCheckPointID, Long proKeHoachSanXuatID) {
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Action: Giảm hàng sửa (SUA) và tăng hàng lỗi (LOI) - Không có RFID");

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(proCheckPointID);
        if (gioLamHienTai == null) {
            log.warn("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        log.debug("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - GioLam: {}", gioLamHienTai.getGioLam());

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckPointID,
                proKeHoachSanXuatID);
        ProNSCL proNsCL = null;
        if (proNSCLs != null && !proNSCLs.isEmpty()) {
            proNsCL = proNSCLs.get(0);
            log.debug("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Found existing ProNSCL ID: {}", proNsCL.getId());
        } else {
            log.warn("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Không tìm thấy ProNSCL để giảm sửa");
            throw new BadRequestAlertException("Không tìm thấy dữ liệu NSCL", "", "nscl_not_found");
        }

        // Tính tổng sửa lũy kế
        Integer totalFixLuyKe = sumNullable(
            proNsCL.getFix1h(), proNsCL.getFix2h(), proNsCL.getFix3h(), proNsCL.getFix4h(),
            proNsCL.getFix5h(), proNsCL.getFix6h(), proNsCL.getFix7h(), proNsCL.getFix8h(),
            proNsCL.getFix9h(), proNsCL.getFix10h(), proNsCL.getFix11h(), proNsCL.getFix12h(),
            proNsCL.getFix13h(), proNsCL.getFix14h());

        log.debug("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Tổng sửa lũy kế: {}", totalFixLuyKe);

        // Kiểm tra điều kiện: không cho giảm sửa nếu hết sửa
        if (totalFixLuyKe == null || totalFixLuyKe <= 0) {
            String errorMessage = "Không thể giảm hàng sửa. Hết số lượng sửa lũy kế trong NSCL này.";
            log.warn("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - {}", errorMessage);
            throw new BadRequestAlertException(errorMessage, "", "khong_the_giam_sua");
        }

        // Giảm sửa theo lũy kế từ giờ muộn nhất (14h) về sớm nhất (1h)
        boolean daGiamSua = decreaseHangTraLuyKe(proNsCL);
        if (!daGiamSua) {
            log.error("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Không thể giảm sửa mặc dù đã kiểm tra có sửa để giảm");
            throw new BadRequestAlertException("Lỗi hệ thống: Không thể giảm sửa", "", "loi_he_thong");
        }
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Đã giảm 1 sửa theo lũy kế");

        // Tăng lỗi theo lũy kế vào giờ sớm nhất (1h)
        boolean daTangLoi = increaseHangLoiLuyKe(proNsCL);
        if (!daTangLoi) {
            log.error("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Không thể tăng lỗi");
            throw new BadRequestAlertException("Lỗi hệ thống: Không thể tăng lỗi", "", "loi_he_thong");
        }
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Đã tăng 1 lỗi theo lũy kế");

        proNSCLRepository.saveAndFlush(proNsCL);
        // Cập nhật lại TongMayRa sau khi đã giảm sửa và tăng lỗi (tổng không đổi)
        updateTongMayRaAfterIncrease(proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Successfully decreased hang sua and increased reject");

        // Trả về dữ liệu lũy kế
        NSCLTheoGioDTO result = getNSCLTheoGioLuyKe(proCheckPointID, proKeHoachSanXuatID);
        log.info("Service: decreaseHangTraAndIncreaseHangLoiTheoGio - Result (lũy kế) - Qty: {}, Fix (SUA): {}, Reject (LOI): {}",
            result != null ? result.getQty() : null, result != null ? result.getFix() : null, result != null ? result.getReject() : null);
        return result;
    }

    @Override
    public String getLatestCheckedTypeByDisplayName(String displayName) {
        log.debug("Service: getLatestCheckedTypeByDisplayName - DisplayName: {}", displayName);
        String checkedType = proNSCLRepository.findLatestCheckedTypeByDisplayName(displayName);
        log.debug("Service: getLatestCheckedTypeByDisplayName - DisplayName '{}' latest checkedType: {}", displayName, checkedType);
        return checkedType;
    }

    @Override
    public boolean isCheckedNSCLByDisplayName(String displayName) {
        log.debug("Service: isCheckedNSCLByDisplayName - DisplayName: {}", displayName);
        List<Long> ids = proNSCLRepository.findCheckedNSCLIdByDisplayName(displayName);
        boolean checked = ids != null && !ids.isEmpty();
        log.debug("Service: isCheckedNSCLByDisplayName - DisplayName '{}' checked: {} (Found {} IDs)",
            displayName, checked, ids != null ? ids.size() : 0);
        return checked;
    }

    @Override
    public Long getFixCountWithRfidByProNsclId(Long proNsclId) {
        log.debug("Service: getFixCountWithRfidByProNsclId - ProNsclId: {}", proNsclId);
        Long count = proNSCLRepository.countFixWithRfidByProNsclId(proNsclId);
        log.debug("Service: getFixCountWithRfidByProNsclId - ProNsclId {} has {} fix items with RFID", proNsclId, count);
        return count != null ? count : 0L;
    }

    @Override
    public CodeInjectionResult proNsclSanPhamCodeInjection(
            Long proCheckpointId,
            Long proKhsxId,
            String checkedType,
            String displayName,
            boolean isFirstTime,
            Long chiTietLoiId,
            String valueHEX,
            String rfidBarcode) {
        log.info("Service: proNsclSanPhamCodeInjection - ProCheckpointId: {}, ProKhsxId: {}, CheckedType: {}, DisplayName: {}, FirstTime: {}, ChiTietLoiId: {}, ValueHEX: {}, RfidBarcode: {}",
            proCheckpointId, proKhsxId, checkedType, displayName, isFirstTime, chiTietLoiId, valueHEX, rfidBarcode);
        log.info("Service: proNsclSanPhamCodeInjection - Action: Code injection với checkedType: {} (DAT=Xanh, SUA=Vàng, LOI=Đỏ)", checkedType);

        Long proNsclChiTietLoiId = null;
        List<CheckPointCode> codes = proNSCLRepository.getCheckPointCode(proCheckpointId);
        log.debug("Service: proNsclSanPhamCodeInjection - DisplayName: {}, CheckPointCodes: {}", displayName, codes != null ? codes.size() : 0);

        ProGioLamViecDTO checkedTime = getGioLamViecHienTaiCuaChuyen(proCheckpointId);

        if (checkedTime == null) {
            log.warn("Service: proNsclSanPhamCodeInjection - Khong tim thay khung gio lam viec cho thoi gian hien tai");
            throw new BadRequestAlertException("Ngoài ca làm việc", "", "ngoai_ca_lam_viec");
        }

        String nguoiQuet = null;
        try {
            nguoiQuet = SecurityUtils.getCurrentUserLogin().orElse(null);
            log.debug("Service: proNsclSanPhamCodeInjection - NguoiQuet: {}", nguoiQuet);
        } catch (Exception e) {
            log.warn("Service: proNsclSanPhamCodeInjection - Không lấy được tên người đăng nhập: {}", e.getMessage());
        }

        List<ProNSCL> proNSCLs = proNSCLRepository.findByProKeHoachSanXuatAndProCheckPoint(proCheckpointId,
                proKhsxId);
        ProNSCL proNSCL = (proNSCLs != null && !proNSCLs.isEmpty()) ? proNSCLs.get(0) : null;
        Long proNsclId = (proNSCL != null) ? proNSCL.getId() : null;
        // log.debug("Service: proNsclSanPhamCodeInjection - ProNSCL ID: {}", proNsclId);

        // case chi tiết lỗi khi lỗi
        proNsclChiTietLoiId = chiTietLoiId;

        log.debug(
                "Service: proNsclSanPhamCodeInjection - Parameters - proNsclId: {}, nguoiQuet: {}, proCheckpointId: {}, proKhsxId: {}, checkedType: {}, displayName: {}, proNsclChiTietLoiId: {}, gioLam: {}",
                proNsclId, nguoiQuet, proCheckpointId, proKhsxId, checkedType, displayName, proNsclChiTietLoiId,
                checkedTime.getGioLam().toString());

        String lastCheckedType = (getLatestCheckedTypeByDisplayName(displayName) !=null && !getLatestCheckedTypeByDisplayName(displayName).isEmpty()) ? getLatestCheckedTypeByDisplayName(displayName) : "NULL";
        log.info("Service: proNsclSanPhamCodeInjection - LastCheckedType for displayName '{}': {}", displayName, lastCheckedType);

        // ✅ VALIDATION TRƯỚC KHI GỌI STORED PROCEDURE
        // Chỉ validate khi THÊM MỚI sản phẩm (chưa có RFID trong DB)
        // Nếu đã có RFID (lastCheckedType != "NULL") → chỉ là chuyển trạng thái → KHÔNG cần validate
        boolean isCreateAction = lastCheckedType.equals("NULL");
        if (isCreateAction) {
            log.info("Service: proNsclSanPhamCodeInjection - Detected CREATE action (lastCheckedType=NULL), performing validation");
            
            // Validation theo thứ tự: Summary_PO_QuetNhan -> SO_LUONG_YEU_CAU KHSX -> TongMayRa -> BamAnLuong
            boolean canIncreaseByQuantityLimit = validateQuantityLimit(proCheckpointId, proKhsxId);
            boolean canIncreaseByTongMayRa = validateTongMayRa(proCheckpointId, proKhsxId);
            boolean allowBamAnLuong = isAllowBamAnLuong(proCheckpointId, proKhsxId);
            
            log.debug("Service: proNsclSanPhamCodeInjection - Validation results - CanIncreaseByQuantityLimit: {}, CanIncreaseByTongMayRa: {}, AllowBamAnLuong: {}", 
                canIncreaseByQuantityLimit, canIncreaseByTongMayRa, allowBamAnLuong);
            
            if (!canIncreaseByQuantityLimit || !canIncreaseByTongMayRa || !allowBamAnLuong) {
                log.warn("Service: proNsclSanPhamCodeInjection - Cannot proceed due to validation checks");
                if (!canIncreaseByQuantityLimit) {
                    // Exception đã được throw trong validateQuantityLimit
                    return null;
                }
                if (!canIncreaseByTongMayRa) {
                    throw new BadRequestAlertException("BTP chưa đồng bộ!", "", "BTP_chua_dong_bo");
                }
                // allowBamAnLuong sẽ throw exception trong method
            }
            log.info("Service: proNsclSanPhamCodeInjection - All validations passed, proceeding with code injection");
        } else {
            log.info("Service: proNsclSanPhamCodeInjection - Detected UPDATE/DELETE action (lastCheckedType={}), skipping validation (total quantity unchanged)", lastCheckedType);
        }

        // Lấy last_pro_nscl_id để trừ đúng
        ProNSCLRepository.ProNsclSanPhamProjection lastProNscl = proNSCLRepository.findTop1ByDisplaynameOrderByThoigianquetDesc(displayName);
        if (lastProNscl != null) {
            if (!checkedType.equals(lastCheckedType)){
                switch (lastCheckedType) {
                    case "LOI":
                        decreaseHangLoiTheoGio(proNSCLRepository.getOne(lastProNscl.getProNsclId()), GioLam.valueOf(lastProNscl.getCheckedTime()));
                        break;
                    case "SUA":
                        decreaseHangTraTheoGio(proNSCLRepository.getOne(lastProNscl.getProNsclId()), GioLam.valueOf(lastProNscl.getCheckedTime()));
                        break;
                    case "DAT":
                        decreaseHangDatTheoGio(proNSCLRepository.getOne(lastProNscl.getProNsclId()), GioLam.valueOf(lastProNscl.getCheckedTime()));
                        break;
                    default:
                        log.warn("Service: proNsclSanPhamCodeInjection - Unknown lastCheckedType: {}", lastCheckedType);
                        break;
                }
            }
        }

        // Gọi stored procedure pro_nscl_decrease_checked nếu cần thiết
        // if (lastCheckedType != null && !lastCheckedType.isEmpty() && lastCheckedType.equals("LOI") && (checkedType.equals("DAT") || checkedType.equals("SUA"))) {
        //     log.info("Service: proNsclSanPhamCodeInjection - Chuyển từ LOI -> DAT, cần decrease LOI trước");
        //     // log.debug("Calling pro_nscl_decrease_checked with lastProNsclId: {}, checkedType: {}, checkedTime: {}",
        //     //         lastProNsclId, lastCheckedType, checkedTime.getGioLam().toString());
        //     // try {
        //     //     proNSCLRepository.callProNsclDecreaseChecked(lastProNsclId, lastCheckedType, checkedTime.getGioLam().toString());
        //     //     log.debug("Successfully called pro_nscl_decrease_checked");
        //     // } catch (Exception e) {
        //     //     log.error("Error calling pro_nscl_decrease_checked: {}", e.getMessage());
        //     // }
        //     if (lastProNsclId != null) {
        //         try {
        //             Optional<ProNSCL> lastProNsclOpt = proNSCLRepository.findById(lastProNsclId);
        //             if (lastProNsclOpt.isPresent()) {
        //                 ProNSCL lastProNscl = lastProNsclOpt.get();
        //                 // Lấy gio_lam của lần quét trước theo displayName nếu có
        //                 GioLam gioLamDeGiam = checkedTime.getGioLam();
        //                 try {
        //                     String lastGioLamStr = proNSCLRepository.findLastGioLamByDisplayName(displayName);
        //                     if (lastGioLamStr != null && !lastGioLamStr.isEmpty()) {
        //                         gioLamDeGiam = GioLam.valueOf(lastGioLamStr);
        //                     }
        //                     log.debug("Service: proNsclSanPhamCodeInjection - GioLamDeGiam: {}", gioLamDeGiam);
        //                 } catch (Exception e) {
        //                     log.warn("Could not parse last gio_lam for displayName {}: {}", displayName, e.getMessage());
        //                 }
        //                 decreaseHangLoiTheoGio(lastProNscl, gioLamDeGiam);
        //                 // proNSCLRepository.saveAndFlush(lastProNscl);
        //                 log.debug("Manually decreased LOI on last ProNSCL id {} for time {}", lastProNsclId,
        //                         gioLamDeGiam);
        //             } else {
        //                 log.warn("Could not find last ProNSCL id {} to decrease LOI", lastProNsclId);
        //             }
        //         } catch (Exception e) {
        //             log.error("Error while manually decreasing LOI on last ProNSCL id {}: {}", lastProNsclId,
        //                     e.getMessage());
        //         }
        //     } else {
        //         log.warn("lastProNsclId is null; cannot manually decrease LOI");
        //     }
        // } else if (lastCheckedType != null && !lastCheckedType.isEmpty() && lastCheckedType.equals("SUA") && (checkedType.equals("DAT") || checkedType.equals("LOI"))) {

        // }

        // // proNSCLRepository.flush();

        // log.info("Service: proNsclSanPhamCodeInjection - Calling stored procedure pro_nscl_sanpham_codeinjection");

        ProNSCLRepository.ProNSCLSanPhamCodeInjectionResult result = proNSCLRepository.proNsclSanPhamCodeInjection(
                proNsclId,
                nguoiQuet,
                proCheckpointId,
                proKhsxId,
                checkedType,
                displayName,
                proNsclChiTietLoiId,
                checkedTime.getGioLam().toString(),
                rfidBarcode);

        log.info("Service: proNsclSanPhamCodeInjection - DONE stored procedure pro_nscl_sanpham_codeinjection!");

        // Còn 1 đoạn +- các cột đếm
        log.info("Service: proNsclSanPhamCodeInjection - Stored procedure result - ID: {}, Error: {}, Action: {}",
            result.getId(), result.getError(), result.getAction());

        if (checkedType.equals("LOI")) {
            log.info("Service: proNsclSanPhamCodeInjection - Processing LOI case - Action: {}", result.getAction());
            // LOI -> LOI
            log.debug("Service: proNsclSanPhamCodeInjection - Chuyen loai -> LOI, checkedType: {}, lastCheckedType: {}", checkedType, lastCheckedType);
            if (result.getAction().equals("CREATE") && lastCheckedType.equals("LOI")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: LOI -> LOI (decrease previous LOI)");
                decreaseHangLoiTheoGio(proNSCL, checkedTime.getGioLam());
            }
            // SUA -> LOI (không tăng tổng, chỉ chuyển đổi)
            else if (result.getAction().equals("UPDATE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: SUA -> LOI");
                // Đã xử lý trong stored procedure
            }
            // DAT -> LOI (không tăng tổng, chỉ chuyển đổi)
            else if (result.getAction().equals("DELETE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: DAT -> LOI (hoàn tác DAT)");
                // Hoàn tác
            } 
            // NULL -> LOI (tăng mới, đã validate ở trên)
            else if (result.getAction().equals("CREATE") && !lastCheckedType.equals("LOI")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: NULL -> LOI (increase LOI) - Already validated");
            }
            else {
                log.warn("Service: proNsclSanPhamCodeInjection - Unknown action for LOI: {}", result.getAction());
            }
        } else if (checkedType.equals("SUA")) {
            log.info("Service: proNsclSanPhamCodeInjection - Processing SUA case - Action: {}", result.getAction());
            // LOI -> SUA (tăng SUA, đã validate ở trên)
            if (result.getAction().equals("CREATE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: LOI -> SUA (decrease LOI, increase SUA) - Already validated");
                increaseHangTraTheoGio(proNSCL, checkedTime.getGioLam());
            } else if (result.getAction().equals("UPDATE")) {
                log.debug("Service: proNsclSanPhamCodeInjection - Case: UPDATE for SUA");
            } else if (result.getAction().equals("DELETE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: DELETE for SUA (hoàn tác)");
            } else {
                // case ERROR
                log.error("Service: proNsclSanPhamCodeInjection - Error occurred: {}", result.getError());
            }
        } else {
            // Đạt
            log.info("Service: proNsclSanPhamCodeInjection - Processing DAT case - Action: {}", result.getAction());
            // Null -> DAT (tăng mới, đã validate ở trên)
            log.debug("Service: proNsclSanPhamCodeInjection - Chuyen loai -> DAT, checkedType: {}, lastCheckedType: {}", checkedType, lastCheckedType);
            if (result.getAction().equals("CREATE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: NULL -> DAT (increase DAT) - Already validated");
                increaseHangDatTheoGio(proNSCL, checkedTime.getGioLam());
            }
            // LOI -> DAT (tăng DAT, đã validate ở trên)
            else if (result.getAction().equals("UPDATE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: LOI -> DAT (decrease LOI, increase DAT) - Already validated");
                increaseHangDatTheoGio(proNSCL, checkedTime.getGioLam());
            } else if (result.getAction().equals("DELETE")) {
                log.info("Service: proNsclSanPhamCodeInjection - Case: DELETE for DAT (hoàn tác)");
            } else {
                // case ERROR
                log.error("Service: proNsclSanPhamCodeInjection - Error occurred: {}", result.getError());
            }
        }

        // Lưu các thay đổi số lượng vào ProNSCL trước khi cập nhật TongMayRa
        // Lưu lastProNSCL nếu có thay đổi (đã giảm số lượng)
        if (lastProNscl != null && !checkedType.equals(lastCheckedType)) {
            ProNSCL lastProNSCLEntity = proNSCLRepository.getOne(lastProNscl.getProNsclId());
            proNSCLRepository.saveAndFlush(lastProNSCLEntity);
            log.debug("Service: proNsclSanPhamCodeInjection - Saved last ProNSCL (ID: {}) after decreasing quantities", lastProNSCLEntity.getId());
        }
        // Lưu current ProNSCL nếu có thay đổi (đã tăng số lượng)
        if (proNSCL != null) {
            proNSCLRepository.saveAndFlush(proNSCL);
            log.debug("Service: proNsclSanPhamCodeInjection - Saved current ProNSCL (ID: {}) after processing quantities", proNSCL.getId());
        }

        // Cập nhật TongMayRa sau khi đã lưu các thay đổi
        updateTongMayRaAfterIncrease(proCheckpointId, proKhsxId);
        log.info("Service: proNsclSanPhamCodeInjection - Completed code injection. Result ID: {}, Action: {}",
            result.getId(), result.getAction());
        return new CodeInjectionResult() {
            @Override
            public Long getId() {
                return result.getId();
            }

            @Override
            public String getError() {
                return result.getError();
            }

            @Override
            public String getAction() {
                return result.getAction();
            }
        };
    }

    /**
     * Lấy danh sách mã cây theo PO với thông tin chi tiết
     * 
     * @param po Mã PO cần tìm
     * @return Danh sách mã cây với thông tin Lot, số tem đã quét và tổng tem
     */
    @Override
    public List<MaCayByPODTO> getMaCayByPO(String po, String mahang) {
        log.debug("Service: getMaCayByPO - PO: {}, MaHang: {}", po, mahang);
        List<ProNSCLRepository.MaCayByPOProjection> projections = proNSCLRepository.findMaCayByPO(po, mahang);
        List<MaCayByPODTO> result = new ArrayList<>();
        for (ProNSCLRepository.MaCayByPOProjection projection : projections) {
            MaCayByPODTO dto = new MaCayByPODTO(
                projection.getCode(),
                projection.getLot(),
                projection.getSoTemDaQuet(),
                projection.getTongSoTem(),
                projection.getProductCode()
            );
            result.add(dto);
        }
        log.debug("Service: getMaCayByPO - Found {} codes for PO: {}, MaHang: {}", result.size(), po, mahang);
        return result;
    }

    /**
     * Lấy số lượng giới hạn BTP từ bảng Summary_PO_QuetNhan
     * 
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return Số lượng giới hạn BTP, null nếu không tìm thấy
     */
    @Override
    public Integer getQuantityLimitBTP(Long proKeHoachSanXuatID) {
        log.info("Service: getQuantityLimitBTP - ProKeHoachSanXuatID: {}", proKeHoachSanXuatID);
        
        if (proKeHoachSanXuatID == null) {
            log.warn("Service: getQuantityLimitBTP - ProKeHoachSanXuatID is null");
            return null;
        }
        
        Integer quantityLimit = proNSCLRepository.getQuantityLimitFromSummaryPO(proKeHoachSanXuatID);
        
        if (quantityLimit != null) {
            log.info("Service: getQuantityLimitBTP - Found quantity limit: {}", quantityLimit);
        } else {
            log.info("Service: getQuantityLimitBTP - No quantity limit found for ProKeHoachSanXuatID: {}", proKeHoachSanXuatID);
        }
        
        return quantityLimit;
    }

    /**
     * Lấy tổng may ra (tổng hàng đã kiểm: Sửa + Đạt) theo kế hoạch sản xuất
     * Gọi stored procedure SP_TongMayRa_ByKHSX
     * 
     * @param khsxId ID của kế hoạch sản xuất
     * @return TongMayRaDTO chứa tổng Qty, Fix và TongMayRa (Qty + Fix)
     */
    @Override
    public TongMayRaDTO getTongMayRaByKHSX(Long khsxId) {
        log.info("Service: getTongMayRaByKHSX - KHSX ID: {}", khsxId);
        
        if (khsxId == null) {
            log.warn("Service: getTongMayRaByKHSX - KHSX ID is null");
            return null;
        }
        
        ProNSCLRepository.TongMayRaProjection projection = proNSCLRepository.getTongMayRaByKHSX(khsxId);
        
        if (projection == null) {
            log.warn("Service: getTongMayRaByKHSX - No data found for KHSX ID: {}", khsxId);
            return null;
        }
        
        TongMayRaDTO dto = new TongMayRaDTO(
            projection.getSanphamId(),
            projection.getChuyenId(),
            projection.getTotalQty(),
            projection.getTotalFix(),
            projection.getTongMayRa()
        );
        
        log.info("Service: getTongMayRaByKHSX - Result: SanPhamId={}, ChuyenId={}, TotalQty={}, TotalFix={}, TongMayRa={}",
            dto.getSanphamId(), dto.getChuyenId(), dto.getTotalQty(), dto.getTotalFix(), dto.getTongMayRa());
        
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public PoFixPastDTO getPoFixPastDefects(String poNo, Long khsxId) {
        log.info("Service: getPoFixPastDefects - poNo: {}, khsxId: {}", poNo, khsxId);
        List<ProNSCL> pool = loadNsclPoolForKhsx(khsxId, poNo);
        int tongLoiQuaKhu = 0;
        List<ProNSCL> pastRows = filterPastNsclRows(pool);
        for (ProNSCL row : pastRows) {
            tongLoiQuaKhu += NsclMetricsAggregator.sumReject(row);
        }
        int soLoiRfidChuaSua = PoFixRfidSupport.countUnmatchedLoiRfid(proNSCLRepository, pastRows);
        return new PoFixPastDTO(poNo, khsxId, tongLoiQuaKhu, soLoiRfidChuaSua);
    }

    @Override
    public void applyPoFixDelta(ApplyPoFixRequestDTO request) {
        log.info("Service: applyPoFixDelta - poNo: {}, khsxId: {}, nsclId: {}, delta: {}",
            request.getPoNo(), request.getKhsxId(), request.getNsclId(), request.getSoLuongSua());

        int delta = request.getSoLuongSua();
        if (delta <= 0) {
            throw new BadRequestAlertException("Số lượng sửa phải lớn hơn 0", "", "so_luong_sua_invalid");
        }

        List<ProNSCL> pool = loadNsclPoolForKhsx(request.getKhsxId(), request.getPoNo());
        List<ProNSCL> pastRows = filterPastNsclRows(pool);

        int tongLoiQuaKhu = 0;
        for (ProNSCL row : pastRows) {
            tongLoiQuaKhu += NsclMetricsAggregator.sumReject(row);
        }
        if (delta > tongLoiQuaKhu) {
            throw new BadRequestAlertException(
                "Số lượng sửa vượt quá lỗi quá khứ còn lại (" + tongLoiQuaKhu + ")",
                "",
                "vuot_loi_qua_khu");
        }

        ProNSCL target = pool.stream()
            .filter(n -> n.getId().equals(request.getNsclId()))
            .findFirst()
            .orElseThrow(() -> new BadRequestAlertException("NSCL không thuộc pool PO/query 2", "", "nscl_khong_thuoc_po"));

        String nguoiQuet = null;
        try {
            nguoiQuet = SecurityUtils.getCurrentUserLogin().orElse(null);
        } catch (Exception e) {
            log.warn("Service: applyPoFixDelta - Không lấy được người quét: {}", e.getMessage());
        }

        // 1) RFID trước: insert SUA cho LOI (latest state, TOP = delta), pool query 2 / nscl quá khứ
        int rfidSuaInserted = PoFixRfidSupport.insertSuaForUnmatchedLoiRfid(
            proNSCLRepository, pastRows, delta, nguoiQuet);

        // 2) FIFO trừ reject quá khứ + cộng sua_po_luy_ke (không ghi fix_*h)
        int deducted = NsclRejectFifo.decreaseRejectFifo(pastRows, delta);
        if (deducted < delta) {
            throw new BadRequestAlertException("Không đủ lỗi quá khứ để trừ", "", "khong_du_loi_de_tru");
        }

        target.setSuaPoLuyKe(nullToZero(target.getSuaPoLuyKe()) + delta);
        proNSCLRepository.saveAll(pastRows);
        proNSCLRepository.saveAndFlush(target);

        updateTongMayRaAfterIncreaseForKhsx(request.getKhsxId());
        log.info(
            "Service: applyPoFixDelta - Done, rfidSuaInserted: {}, deducted: {}, sua_po on nscl {} now: {}",
            rfidSuaInserted, deducted, target.getId(), target.getSuaPoLuyKe());
    }

    private void updateTongMayRaAfterIncreaseForKhsx(Long khsxId) {
        Optional<ProKeHoachSanXuatDTO> proKHSXOpt = proKeHoachSanXuatService.findOne(khsxId);
        if (!proKHSXOpt.isPresent()) {
            return;
        }
        ProKeHoachSanXuatDTO proKHSX = proKHSXOpt.get();
        Optional<ProKiemKiemVuot> proKiemKiemVuotOpt = _proKiemKiemVuotService
            .findOneByChuyenIdAndProSanPhamId(proKHSX.getProChuyenId(), proKHSX.getProSanPhamId());
        if (!proKiemKiemVuotOpt.isPresent()) {
            return;
        }
        ProKiemKiemVuot proKiemKiemVuot = proKiemKiemVuotOpt.get();
        List<ProNSCL> pool = loadNsclPoolForKhsx(khsxId, null);
        int tongMayRa = NsclMetricsAggregator.aggregate(pool).getTongMayRa();
        proKiemKiemVuot.setSoLuongMayRa(tongMayRa);
        _proKiemKiemVuotService.save(proKiemKiemVuot);
    }

    @Override
    public int getTongMayRaPoolQuery2ByKhsxId(Long khsxId) {
        List<ProNSCL> pool = loadNsclPoolForKhsx(khsxId, null);
        return NsclMetricsAggregator.aggregate(pool).getTongMayRa();
    }

    private List<ProNSCL> loadNsclPoolForKhsx(Long khsxId, String poNo) {
        ProNSCLRepository.KhsxQuery2AnchorProjection anchor = requireQuery2Anchor(khsxId);
        String effectivePoNo = resolveEffectivePoNo(anchor, poNo);
        return proNSCLRepository.findNsclPoolByQuery2Mapping(
            effectivePoNo,
            anchor.getMaSanPham(),
            anchor.getCumPo(),
            anchor.getChuyenOidMapping(),
            anchor.getKhsxOidMapping()
        );
    }

    private ProNSCLRepository.KhsxQuery2AnchorProjection requireQuery2Anchor(Long khsxId) {
        return proNSCLRepository.findQuery2AnchorByKhsxId(khsxId)
            .orElseThrow(() -> new BadRequestAlertException(
                "Không tìm thấy KHSX hoặc mapping query 2", "", "khsx_mapping_not_found"));
    }

    private String resolveEffectivePoNo(ProNSCLRepository.KhsxQuery2AnchorProjection anchor, String poNo) {
        if (poNo != null && !poNo.trim().isEmpty()) {
            String trimmed = poNo.trim();
            String fromOmm = anchor.getPoNoFromOmm();
            String fromSp = anchor.getSpPo();
            if (fromOmm != null && !fromOmm.equalsIgnoreCase(trimmed)
                && fromSp != null && !fromSp.contains(trimmed)) {
                log.warn("Service: poNo {} may not match anchor OMM {} / sp.po {}", trimmed, fromOmm, fromSp);
            }
            return trimmed;
        }
        if (anchor.getPoNoFromOmm() != null && !anchor.getPoNoFromOmm().trim().isEmpty()) {
            return anchor.getPoNoFromOmm().trim();
        }
        if (anchor.getSpPo() != null && !anchor.getSpPo().trim().isEmpty()) {
            return anchor.getSpPo().trim();
        }
        throw new BadRequestAlertException("Không xác định được PO", "", "po_not_found");
    }

    private List<ProNSCL> filterPastNsclRows(List<ProNSCL> pool) {
        Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<ProNSCL> past = new ArrayList<>();
        for (ProNSCL row : pool) {
            if (row.getNgayTao() == null || row.getNgayTao().isBefore(startOfToday)) {
                past.add(row);
            }
        }
        past.sort(Comparator
            .comparing(ProNSCL::getNgayTao, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(ProNSCL::getId, Comparator.nullsLast(Comparator.naturalOrder())));
        return past;
    }

    private int nullToZero(Integer value) {
        return value != null ? value : 0;
    }

    private void validateDecathlonRfidForQualityCheck(String rfid) {
        if (rfid == null || rfid.trim().isEmpty()) {
            return;
        }
        try {
            temBarcodeTachCayService.validateDecathlonItemForQualityCheck(rfid);
        } catch (IllegalStateException e) {
            String errorKey = DecathlonRfidItemValidator.resolveErrorCode(e.getMessage());
            if (errorKey == null) {
                errorKey = "decathlon_item_validation_failed";
            }
            throw new BadRequestAlertException(e.getMessage(), "", errorKey);
        }
    }
}
