package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProCongDoanService;
import vn.nvsoft.domain.ProCongDoan;
import vn.nvsoft.repository.ProCongDoanRepository;
import vn.nvsoft.service.dto.ProCongDoanDTO;
import vn.nvsoft.service.mapper.ProCongDoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProCongDoan}.
 */
@Service
@Transactional
public class ProCongDoanServiceImpl implements ProCongDoanService {

    private final Logger log = LoggerFactory.getLogger(ProCongDoanServiceImpl.class);

    private final ProCongDoanRepository proCongDoanRepository;

    private final ProCongDoanMapper proCongDoanMapper;

    public ProCongDoanServiceImpl(ProCongDoanRepository proCongDoanRepository, ProCongDoanMapper proCongDoanMapper) {
        this.proCongDoanRepository = proCongDoanRepository;
        this.proCongDoanMapper = proCongDoanMapper;
    }

    /**
     * Save a proCongDoan.
     *
     * @param proCongDoanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProCongDoanDTO save(ProCongDoanDTO proCongDoanDTO) {
        log.debug("Request to save ProCongDoan : {}", proCongDoanDTO);
        ProCongDoan proCongDoan = proCongDoanMapper.toEntity(proCongDoanDTO);
        proCongDoan = proCongDoanRepository.save(proCongDoan);
        return proCongDoanMapper.toDto(proCongDoan);
    }

    /**
     * Get all the proCongDoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProCongDoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProCongDoans");
        return proCongDoanRepository.findAll(pageable)
            .map(proCongDoanMapper::toDto);
    }


    /**
     * Get one proCongDoan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProCongDoanDTO> findOne(Long id) {
        log.debug("Request to get ProCongDoan : {}", id);
        return proCongDoanRepository.findById(id)
            .map(proCongDoanMapper::toDto);
    }

    /**
     * Delete the proCongDoan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProCongDoan : {}", id);
        proCongDoanRepository.deleteById(id);
    }
}
