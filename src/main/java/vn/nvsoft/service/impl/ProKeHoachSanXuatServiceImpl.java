package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProKeHoachSanXuatService;
import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.repository.ProKeHoachSanXuatRepository;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.dto.ProKeHoachSanXuatActivationStatusDTO;
import vn.nvsoft.service.mapper.ProKeHoachSanXuatMapper;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProKeHoachSanXuat}.
 */
@Service
@Transactional
public class ProKeHoachSanXuatServiceImpl implements ProKeHoachSanXuatService {

    private static final String ENTITY_NAME = "proKeHoachSanXuat";

    /** Ngày sản xuất "cùng ngày" được tính theo lịch Việt Nam. */
    private static final ZoneId KHSX_DAY_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");

    private final Logger log = LoggerFactory.getLogger(ProKeHoachSanXuatServiceImpl.class);

    private final ProKeHoachSanXuatRepository proKeHoachSanXuatRepository;

    private final ProKeHoachSanXuatMapper proKeHoachSanXuatMapper;

    public ProKeHoachSanXuatServiceImpl(ProKeHoachSanXuatRepository proKeHoachSanXuatRepository, ProKeHoachSanXuatMapper proKeHoachSanXuatMapper) {
        this.proKeHoachSanXuatRepository = proKeHoachSanXuatRepository;
        this.proKeHoachSanXuatMapper = proKeHoachSanXuatMapper;
    }

    /**
     * Save a proKeHoachSanXuat.
     *
     * @param proKeHoachSanXuatDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProKeHoachSanXuatDTO save(ProKeHoachSanXuatDTO proKeHoachSanXuatDTO) {
        log.debug("Request to save ProKeHoachSanXuat : {}", proKeHoachSanXuatDTO);
        ProKeHoachSanXuat proKeHoachSanXuat = proKeHoachSanXuatMapper.toEntity(proKeHoachSanXuatDTO);
        proKeHoachSanXuat = proKeHoachSanXuatRepository.save(proKeHoachSanXuat);
        return proKeHoachSanXuatMapper.toDto(proKeHoachSanXuat);
    }

    /**
     * Get all the proKeHoachSanXuats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProKeHoachSanXuatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProKeHoachSanXuats");
        return proKeHoachSanXuatRepository.findAll(pageable)
            .map(proKeHoachSanXuatMapper::toDto);
    }


    /**
     * Get one proKeHoachSanXuat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProKeHoachSanXuatDTO> findOne(Long id) {
        log.debug("Request to get ProKeHoachSanXuat : {}", id);
        return proKeHoachSanXuatRepository.findById(id)
            .map(proKeHoachSanXuatMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProKeHoachSanXuatActivationStatusDTO> getActivationStatus(Long khsxId) {
        log.debug("Request to get activation status for ProKeHoachSanXuat id={}", khsxId);
        return proKeHoachSanXuatRepository.findById(khsxId)
            .map(e -> new ProKeHoachSanXuatActivationStatusDTO(e.getId(), e.getIsActived(), e.getActiveTime()));
    }

    /**
     * Delete the proKeHoachSanXuat by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProKeHoachSanXuat : {}", id);
        proKeHoachSanXuatRepository.deleteById(id);
    }

    @Override
    public Optional<ProKeHoachSanXuatDTO> activateExclusiveForChuyenDay(Long id) {
        log.debug("Request to activate exclusively ProKeHoachSanXuat id={}", id);
        Optional<ProKeHoachSanXuat> opt = proKeHoachSanXuatRepository.findById(id);
        if (!opt.isPresent()) {
            return Optional.empty();
        }
        ProKeHoachSanXuat row = opt.get();
        if (row.getProChuyen() == null || row.getProChuyen().getId() == null) {
            throw new BadRequestAlertException("Kế hoạch không gắn chuyền, không thể kích hoạt theo chuyền/ngày", ENTITY_NAME, "prochuyenrequired");
        }
        if (row.getNgaySanXuat() == null) {
            throw new BadRequestAlertException("Kế hoạch không có ngày sản xuất", ENTITY_NAME, "ngaysanxuatrequired");
        }
        Long chuyenId = row.getProChuyen().getId();
        Instant dayStart = dayStartInclusive(row.getNgaySanXuat());
        Instant dayEnd = dayEndExclusive(row.getNgaySanXuat());
        proKeHoachSanXuatRepository.deactivateForChuyenAndNgayWindow(chuyenId, dayStart, dayEnd);
        Instant now = Instant.now();
        proKeHoachSanXuatRepository.activateById(id, now);
        return proKeHoachSanXuatRepository.findById(id).map(proKeHoachSanXuatMapper::toDto);
    }

    @Override
    public Optional<ProKeHoachSanXuatDTO> activateExclusiveForChuyenDay(Long chuyenId, LocalDate ngaySanXuat, Long proKhsxId) {
        log.debug("Request to activate exclusively ProKeHoachSanXuat chuyenId={}, ngaySanXuat={}, proKhsxId={}", chuyenId, ngaySanXuat, proKhsxId);
        Optional<ProKeHoachSanXuat> opt = proKeHoachSanXuatRepository.findById(proKhsxId);
        if (!opt.isPresent()) {
            return Optional.empty();
        }
        ProKeHoachSanXuat row = opt.get();
        if (row.getProChuyen() == null || row.getProChuyen().getId() == null) {
            throw new BadRequestAlertException("Kế hoạch không gắn chuyền, không thể kích hoạt theo chuyền/ngày", ENTITY_NAME, "prochuyenrequired");
        }
        if (!chuyenId.equals(row.getProChuyen().getId())) {
            throw new BadRequestAlertException("chuyenId không khớp với kế hoạch", ENTITY_NAME, "chuyenmismatch");
        }
        if (row.getNgaySanXuat() == null) {
            throw new BadRequestAlertException("Kế hoạch không có ngày sản xuất", ENTITY_NAME, "ngaysanxuatrequired");
        }
        LocalDate rowDay = row.getNgaySanXuat().atZone(KHSX_DAY_ZONE).toLocalDate();
        if (!ngaySanXuat.equals(rowDay)) {
            throw new BadRequestAlertException("date không khớp ngày sản xuất của kế hoạch", ENTITY_NAME, "datemismatch");
        }
        Instant dayStart = ngaySanXuat.atStartOfDay(KHSX_DAY_ZONE).toInstant();
        Instant dayEnd = ngaySanXuat.plusDays(1).atStartOfDay(KHSX_DAY_ZONE).toInstant();
        proKeHoachSanXuatRepository.deactivateForChuyenAndNgayWindow(chuyenId, dayStart, dayEnd);
        proKeHoachSanXuatRepository.activateById(proKhsxId, Instant.now());
        return proKeHoachSanXuatRepository.findById(proKhsxId).map(proKeHoachSanXuatMapper::toDto);
    }

    private static Instant dayStartInclusive(Instant ngaySanXuat) {
        LocalDate d = ngaySanXuat.atZone(KHSX_DAY_ZONE).toLocalDate();
        return d.atStartOfDay(KHSX_DAY_ZONE).toInstant();
    }

    private static Instant dayEndExclusive(Instant ngaySanXuat) {
        LocalDate d = ngaySanXuat.atZone(KHSX_DAY_ZONE).toLocalDate();
        return d.plusDays(1).atStartOfDay(KHSX_DAY_ZONE).toInstant();
    }
}
