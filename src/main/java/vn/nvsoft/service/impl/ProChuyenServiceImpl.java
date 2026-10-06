package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProChuyenService;
import vn.nvsoft.domain.ProChuyen;
import vn.nvsoft.repository.ProChuyenRepository;
import vn.nvsoft.service.dto.ProChuyenDTO;
import vn.nvsoft.service.mapper.ProChuyenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProChuyen}.
 */
@Service
@Transactional
public class ProChuyenServiceImpl implements ProChuyenService {

    private final Logger log = LoggerFactory.getLogger(ProChuyenServiceImpl.class);

    private final ProChuyenRepository proChuyenRepository;

    private final ProChuyenMapper proChuyenMapper;

    public ProChuyenServiceImpl(ProChuyenRepository proChuyenRepository, ProChuyenMapper proChuyenMapper) {
        this.proChuyenRepository = proChuyenRepository;
        this.proChuyenMapper = proChuyenMapper;
    }

    /**
     * Save a proChuyen.
     *
     * @param proChuyenDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProChuyenDTO save(ProChuyenDTO proChuyenDTO) {
        log.debug("Request to save ProChuyen : {}", proChuyenDTO);
        ProChuyen proChuyen = proChuyenMapper.toEntity(proChuyenDTO);
        proChuyen = proChuyenRepository.save(proChuyen);
        return proChuyenMapper.toDto(proChuyen);
    }

    /**
     * Get all the proChuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProChuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProChuyens");
        return proChuyenRepository.findAll(pageable)
            .map(proChuyenMapper::toDto);
    }


    /**
     * Get one proChuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProChuyenDTO> findOne(Long id) {
        log.debug("Request to get ProChuyen : {}", id);
        return proChuyenRepository.findById(id)
            .map(proChuyenMapper::toDto);
    }

    /**
     * Delete the proChuyen by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProChuyen : {}", id);
        proChuyenRepository.deleteById(id);
    }
}
