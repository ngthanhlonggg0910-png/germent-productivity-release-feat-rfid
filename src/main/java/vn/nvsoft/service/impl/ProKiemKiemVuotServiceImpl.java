package vn.nvsoft.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ProChuyen;
import vn.nvsoft.domain.ProKiemKiemVuot;
import vn.nvsoft.repository.ProChuyenRepository;
import vn.nvsoft.repository.ProKiemKiemVuotRepository;
import vn.nvsoft.service.ProChuyenService;
import vn.nvsoft.service.dto.ProChuyenDTO;
import vn.nvsoft.service.mapper.ProChuyenMapper;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProChuyen}.
 */
@Service
@Transactional
public class ProKiemKiemVuotServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ProKiemKiemVuotServiceImpl.class);

    private final ProKiemKiemVuotRepository _proKiemKiemVuotRepository;


    public ProKiemKiemVuotServiceImpl(ProKiemKiemVuotRepository proKiemKiemVuotRepository) {
        _proKiemKiemVuotRepository = proKiemKiemVuotRepository;
    }

    /**
     * Save a proChuyen.
     *
     * @param proKiemKiemVuot the entity to save.
     * @return the persisted entity.
     */
    public ProKiemKiemVuot save(ProKiemKiemVuot proKiemKiemVuot) {
        proKiemKiemVuot = _proKiemKiemVuotRepository.save(proKiemKiemVuot);
        return proKiemKiemVuot;
    }

    /**
     * Get all the proKiemKiemVuots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProKiemKiemVuot> findAll(Pageable pageable) {
        log.debug("Request to get all ProKiemKiemVuots");
        return _proKiemKiemVuotRepository.findAll(pageable);
    }


    /**
     * Get one proKiemKiemVuot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProKiemKiemVuot> findOne(Long id) {
        log.debug("Request to get ProKiemKiemVuot : {}", id);
        return _proKiemKiemVuotRepository.findById(id);
    }

    /**
     * Delete the proKiemKiemVuot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProKiemKiemVuot : {}", id);
        _proKiemKiemVuotRepository.deleteById(id);
    }

    public Optional<ProKiemKiemVuot> findOneByChuyenIdAndProSanPhamId(Long proChuyenId, Long proSanPhamId) {
        return _proKiemKiemVuotRepository.findOneByChuyenIdAndProSanPhamId(proChuyenId, proSanPhamId);
    }
}
