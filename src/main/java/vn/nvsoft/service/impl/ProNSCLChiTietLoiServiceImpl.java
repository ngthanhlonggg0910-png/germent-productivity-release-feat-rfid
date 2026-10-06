package vn.nvsoft.service.impl;

import java.util.List;
import vn.nvsoft.domain.enumeration.GioLam;
import vn.nvsoft.service.ProNSCLChiTietLoiService;
import vn.nvsoft.domain.ProNSCLChiTietLoi;
import vn.nvsoft.repository.ProNSCLChiTietLoiRepository;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.dto.WarningProNSCLChiTietLoiVO;
import vn.nvsoft.service.firebase.NotificationService;
import vn.nvsoft.service.mapper.ProNSCLChiTietLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProNSCLChiTietLoi}.
 */
@Service
@Transactional
public class ProNSCLChiTietLoiServiceImpl implements ProNSCLChiTietLoiService {

    private final Logger log = LoggerFactory.getLogger(ProNSCLChiTietLoiServiceImpl.class);

    private final ProNSCLChiTietLoiRepository proNSCLChiTietLoiRepository;

    private final ProNSCLChiTietLoiMapper proNSCLChiTietLoiMapper;
    private NotificationService m_notificationService;

    public ProNSCLChiTietLoiServiceImpl(ProNSCLChiTietLoiRepository proNSCLChiTietLoiRepository, ProNSCLChiTietLoiMapper proNSCLChiTietLoiMapper, NotificationService notificationService) {
        this.proNSCLChiTietLoiRepository = proNSCLChiTietLoiRepository;
        this.proNSCLChiTietLoiMapper = proNSCLChiTietLoiMapper;
        m_notificationService = notificationService;
    }

    /**
     * Save a proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProNSCLChiTietLoiDTO save(ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO) {
        log.debug("Request to save ProNSCLChiTietLoi : {}", proNSCLChiTietLoiDTO);
        ProNSCLChiTietLoi proNSCLChiTietLoi = proNSCLChiTietLoiMapper.toEntity(proNSCLChiTietLoiDTO);
        proNSCLChiTietLoi = proNSCLChiTietLoiRepository.saveAndFlush(proNSCLChiTietLoi);
        return proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLoi);
    }

    /**
     * Get all the proNSCLChiTietLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProNSCLChiTietLoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProNSCLChiTietLois");
        return proNSCLChiTietLoiRepository.findAll(pageable)
            .map(proNSCLChiTietLoiMapper::toDto);
    }


    /**
     * Get one proNSCLChiTietLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProNSCLChiTietLoiDTO> findOne(Long id) {
        log.debug("Request to get ProNSCLChiTietLoi : {}", id);
        return proNSCLChiTietLoiRepository.findById(id)
            .map(proNSCLChiTietLoiMapper::toDto);
    }

    /**
     * Delete the proNSCLChiTietLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProNSCLChiTietLoi : {}", id);
        try {
            deleteProNSCLSanPham(id);
            proNSCLChiTietLoiRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting ProNSCLChiTietLoi : {}", e.getMessage());
        }
    }

    public void deleteProNSCLSanPham(long proNsclChiTietLoiId) {
        log.debug("Request to delete ProNSCLSanPham by ProNSCLChiTietLoiId : {}", proNsclChiTietLoiId);
        proNSCLChiTietLoiRepository.deleteNSCLSanphamByProCTLId(proNsclChiTietLoiId);
    }

    public List<ProNSCLChiTietLoiDTO> getByNSCLId(Long nsclId) {
        List<ProNSCLChiTietLoi> proNSCLChiTietLois = proNSCLChiTietLoiRepository.findByProNSCLId(nsclId);
        if (proNSCLChiTietLois != null) {
            return proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLois);
        }
        return null;
    }

    public List<ProNSCLChiTietLoiDTO> getNSCLChiTietTheoGio(Long nsclId, GioLam gioLam) {
        List<ProNSCLChiTietLoi> proNSCLChiTietLois = proNSCLChiTietLoiRepository.findByProNSCLIdAndGioLam(nsclId, gioLam);
        if (proNSCLChiTietLois != null) {
            return proNSCLChiTietLoiMapper.toDto(proNSCLChiTietLois);
        }
        return null;
    }

    /**
     * Cap nhat truong warning trong ProNSCLChiTietLoi de enable/disable canh bao cho to truong
     */
    @Override
    public void updateWarningOfProNSCLChiTietLoi(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO) {
        proNSCLChiTietLoiRepository.updateWarning(warningProNSCLChiTietLoiVO.getProNSCLChiTietLoiIds(), warningProNSCLChiTietLoiVO.getWarning());
        m_notificationService.sendWarningMessageToLeader(warningProNSCLChiTietLoiVO);
    }
}
