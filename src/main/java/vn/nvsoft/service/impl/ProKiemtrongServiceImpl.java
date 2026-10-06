package vn.nvsoft.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.domain.ProKiemtrongChiTietLoi;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.repository.ProKiemtrongChiTietLoiRepository;
import vn.nvsoft.repository.ProKiemtrongSanPhamRepository;
import vn.nvsoft.repository.ProKiemtrongSanPhamRepository.ProKiemtrongSanPhamProjection;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.service.ProKiemtrongService;
import vn.nvsoft.service.dto.CreateKiemTrongChiTietLoiRequest;
import vn.nvsoft.service.dto.KiemTrongSessionDTO;
import vn.nvsoft.service.dto.MarkKiemTrongSuaRequest;
import vn.nvsoft.service.dto.ProGioLamViecDTO;
import vn.nvsoft.service.dto.ProKiemtrongChiTietLoiDTO;
import vn.nvsoft.service.dto.ProKiemtrongSanPhamDTO;
import vn.nvsoft.service.mapper.ProKiemtrongChiTietLoiMapper;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;

@Service
@Transactional
public class ProKiemtrongServiceImpl implements ProKiemtrongService {

    private static final String ENTITY_NAME = "proKiemtrongChiTietLoi";

    private final Logger log = LoggerFactory.getLogger(ProKiemtrongServiceImpl.class);

    private final ProKiemtrongChiTietLoiRepository chiTietLoiRepository;
    private final ProKiemtrongSanPhamRepository sanPhamRepository;
    private final ProKiemtrongChiTietLoiMapper chiTietLoiMapper;
    private final ProGioLamViecService proGioLamViecService;
    private final ProCheckPointService proCheckPointService;

    public ProKiemtrongServiceImpl(
        ProKiemtrongChiTietLoiRepository chiTietLoiRepository,
        ProKiemtrongSanPhamRepository sanPhamRepository,
        ProKiemtrongChiTietLoiMapper chiTietLoiMapper,
        ProGioLamViecService proGioLamViecService,
        ProCheckPointService proCheckPointService) {
        this.chiTietLoiRepository = chiTietLoiRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.chiTietLoiMapper = chiTietLoiMapper;
        this.proGioLamViecService = proGioLamViecService;
        this.proCheckPointService = proCheckPointService;
    }

    @Override
    @Transactional(readOnly = true)
    public KiemTrongSessionDTO getSession(Long checkpointId, Long khsxId) {
        log.debug("Request to get Kiểm trong session checkpoint={}, khsx={}", checkpointId, khsxId);
        KiemTrongSessionDTO session = new KiemTrongSessionDTO();
        session.setCheckpointId(checkpointId);
        session.setKhsxId(khsxId);
        List<ProKiemtrongChiTietLoi> chiTietLois = chiTietLoiRepository
            .findByProCheckpointIdAndProKeHoachSanXuatIdOrderByThoiGianDesc(checkpointId, khsxId);
        session.setChiTietLois(chiTietLois.stream().map(chiTietLoiMapper::toDto).collect(Collectors.toList()));
        session.setSanPhams(
            sanPhamRepository.findLoiAndSuaByCheckpointAndKhsx(checkpointId, khsxId)
                .stream()
                .map(this::toSanPhamDto)
                .collect(Collectors.toList()));
        return session;
    }

    @Override
    public List<ProKiemtrongChiTietLoiDTO> addChiTietLoi(CreateKiemTrongChiTietLoiRequest request) {
        log.debug("Request to add Kiểm trong chi tiết lỗi: {}", request);
        if (request.getQuantity() < 1 || request.getQuantity() > 50) {
            throw new BadRequestAlertException("Quantity must be between 1 and 50", ENTITY_NAME, "invalidquantity");
        }

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(request.getCheckpointId());
        String checkedTime = gioLamHienTai != null && gioLamHienTai.getGioLam() != null
            ? gioLamHienTai.getGioLam().name()
            : null;
        String nguoiQuet = SecurityUtils.getCurrentUserLogin().orElse(null);
        Instant now = Instant.now();

        ProLoi proLoi = new ProLoi();
        proLoi.setId(request.getProLoiId());

        List<ProKiemtrongChiTietLoiDTO> results = new ArrayList<>();
        for (int i = 0; i < request.getQuantity(); i++) {
            ProKiemtrongChiTietLoi chiTietLoi = new ProKiemtrongChiTietLoi();
            chiTietLoi.setProCheckpointId(request.getCheckpointId());
            chiTietLoi.setProKeHoachSanXuatId(request.getKhsxId());
            chiTietLoi.setMaLoi(request.getMaLoi());
            chiTietLoi.setThoiGian(now);
            if (gioLamHienTai != null) {
                chiTietLoi.setGioLam(gioLamHienTai.getGioLam());
            }
            chiTietLoi.setDx(null);
            chiTietLoi.setDy(null);
            chiTietLoi.setIsFront(null);
            chiTietLoi.setIsInside(null);
            chiTietLoi.setBarcodeTachCay(request.getBarcodeTachCay());
            chiTietLoi.setProLoi(proLoi);
            chiTietLoi = chiTietLoiRepository.saveAndFlush(chiTietLoi);

            sanPhamRepository.insertLoiSanPham(
                request.getCheckpointId(),
                request.getKhsxId(),
                nguoiQuet,
                now,
                request.getRfidDisplayName(),
                chiTietLoi.getId(),
                checkedTime,
                Boolean.TRUE,
                request.getRfidHex(),
                request.getRfidBarcode());

            results.add(chiTietLoiMapper.toDto(chiTietLoi));
        }
        return results;
    }

    @Override
    public void deleteChiTietLoi(Long id) {
        log.debug("Request to delete Kiểm trong chi tiết lỗi: {}", id);
        sanPhamRepository.deleteByChiTietLoiId(id);
        chiTietLoiRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public int countLoiByRfid(Long checkpointId, Long khsxId, String rfidDisplayName) {
        if (rfidDisplayName == null || rfidDisplayName.trim().isEmpty()) {
            return 0;
        }
        return sanPhamRepository.countLoiByDisplayName(checkpointId, khsxId, rfidDisplayName.trim());
    }

    @Override
    public int deleteLoiByRfid(Long checkpointId, Long khsxId, String rfidDisplayName) {
        if (rfidDisplayName == null || rfidDisplayName.trim().isEmpty()) {
            throw new BadRequestAlertException("RFID display name is required", ENTITY_NAME, "rfidrequired");
        }
        String displayName = rfidDisplayName.trim();
        log.debug("Request to delete Kiểm trong lỗi by RFID={}, checkpoint={}, khsx={}",
            displayName, checkpointId, khsxId);
        List<Long> chiTietLoiIds = sanPhamRepository.findChiTietLoiIdsByDisplayName(
            checkpointId, khsxId, displayName);
        int deleted = 0;
        for (Long chiTietLoiId : chiTietLoiIds) {
            if (chiTietLoiId == null) {
                continue;
            }
            sanPhamRepository.deleteByChiTietLoiId(chiTietLoiId);
            if (chiTietLoiRepository.existsById(chiTietLoiId)) {
                chiTietLoiRepository.deleteById(chiTietLoiId);
            }
            deleted++;
        }
        return deleted;
    }

    @Override
    public KiemTrongSessionDTO markSuaByRfid(MarkKiemTrongSuaRequest request) {
        if (request.getRfidDisplayName() == null || request.getRfidDisplayName().trim().isEmpty()) {
            throw new BadRequestAlertException("RFID display name is required", ENTITY_NAME, "rfidrequired");
        }
        String displayName = request.getRfidDisplayName().trim();
        log.debug("Request to mark Kiểm trong SUA for RFID={}, checkpoint={}, khsx={}",
            displayName, request.getCheckpointId(), request.getKhsxId());

        int loiCount = sanPhamRepository.countLoiByDisplayName(
            request.getCheckpointId(), request.getKhsxId(), displayName);
        if (loiCount < 1) {
            throw new BadRequestAlertException(
                "RFID has no LOI record to fix", ENTITY_NAME, "noloiforrfid");
        }

        String latestType = sanPhamRepository
            .findLoiAndSuaByCheckpointAndKhsx(request.getCheckpointId(), request.getKhsxId())
            .stream()
            .filter(r -> displayName.equals(r.getDisplayname()))
            .map(ProKiemtrongSanPhamProjection::getCheckedType)
            .findFirst()
            .orElse(null);
        if (!"LOI".equals(latestType)) {
            throw new BadRequestAlertException(
                "RFID latest state is not LOI", ENTITY_NAME, "notactivetoloi");
        }

        ProGioLamViecDTO gioLamHienTai = getGioLamViecHienTaiCuaChuyen(request.getCheckpointId());
        String checkedTime = gioLamHienTai != null && gioLamHienTai.getGioLam() != null
            ? gioLamHienTai.getGioLam().name()
            : null;
        String nguoiQuet = SecurityUtils.getCurrentUserLogin().orElse(null);
        Instant now = Instant.now();

        sanPhamRepository.insertSuaSanPham(
            request.getCheckpointId(),
            request.getKhsxId(),
            nguoiQuet,
            now,
            displayName,
            checkedTime,
            Boolean.TRUE,
            request.getRfidHex(),
            request.getRfidBarcode());

        return getSession(request.getCheckpointId(), request.getKhsxId());
    }

    private ProGioLamViecDTO getGioLamViecHienTaiCuaChuyen(Long proCheckPointId) {
        Optional<ProCheckPoint> checkPoint = proCheckPointService.findOneProCheckPoint(proCheckPointId);
        if (!checkPoint.isPresent()) {
            return null;
        }
        String chuyen = checkPoint.get().getProChuyen().getStt().toString();
        return proGioLamViecService.getGioLamViecHienTai(chuyen);
    }

    private ProKiemtrongSanPhamDTO toSanPhamDto(ProKiemtrongSanPhamProjection projection) {
        ProKiemtrongSanPhamDTO dto = new ProKiemtrongSanPhamDTO();
        dto.setId(projection.getId());
        dto.setProCheckpointId(projection.getProCheckpointId());
        dto.setProKhsxId(projection.getProKhsxId());
        dto.setNguoiquet(projection.getNguoiquet());
        dto.setThoigianquet(projection.getThoigianquet());
        dto.setCheckedType(projection.getCheckedType());
        dto.setDisplayname(projection.getDisplayname());
        dto.setProKiemtrongChiTietLoiId(projection.getProKiemtrongChiTietLoiId());
        dto.setCheckedTime(projection.getCheckedTime());
        dto.setIsTracking(projection.getIsTracking());
        dto.setValueHEX(projection.getValueHEX());
        dto.setRfidBarcode(projection.getRfidBarcode());
        return dto;
    }
}
