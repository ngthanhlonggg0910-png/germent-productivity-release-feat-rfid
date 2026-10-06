package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProNhomCongDoanService;
import vn.nvsoft.domain.ProNhomCongDoan;
import vn.nvsoft.repository.ProNhomCongDoanRepository;
import vn.nvsoft.service.dto.ProNhomCongDoanDTO;
import vn.nvsoft.service.mapper.ProNhomCongDoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProNhomCongDoan}.
 */
@Service
@Transactional
public class ProNhomCongDoanServiceImpl implements ProNhomCongDoanService {

    private final Logger log = LoggerFactory.getLogger(ProNhomCongDoanServiceImpl.class);

    private final ProNhomCongDoanRepository proNhomCongDoanRepository;

    private final ProNhomCongDoanMapper proNhomCongDoanMapper;

    public ProNhomCongDoanServiceImpl(ProNhomCongDoanRepository proNhomCongDoanRepository, ProNhomCongDoanMapper proNhomCongDoanMapper) {
        this.proNhomCongDoanRepository = proNhomCongDoanRepository;
        this.proNhomCongDoanMapper = proNhomCongDoanMapper;
    }

    /**
     * Save a proNhomCongDoan.
     *
     * @param proNhomCongDoanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProNhomCongDoanDTO save(ProNhomCongDoanDTO proNhomCongDoanDTO) {
        log.debug("Request to save ProNhomCongDoan : {}", proNhomCongDoanDTO);
        ProNhomCongDoan proNhomCongDoan = proNhomCongDoanMapper.toEntity(proNhomCongDoanDTO);
        proNhomCongDoan = proNhomCongDoanRepository.save(proNhomCongDoan);
        return proNhomCongDoanMapper.toDto(proNhomCongDoan);
    }

    /**
     * Get all the proNhomCongDoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProNhomCongDoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProNhomCongDoans");
        return proNhomCongDoanRepository.findAll(pageable)
            .map(proNhomCongDoanMapper::toDto);
    }


    /**
     * Get one proNhomCongDoan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProNhomCongDoanDTO> findOne(Long id) {
        log.debug("Request to get ProNhomCongDoan : {}", id);
        return proNhomCongDoanRepository.findById(id)
            .map(proNhomCongDoanMapper::toDto);
    }

    /**
     * Delete the proNhomCongDoan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProNhomCongDoan : {}", id);
        proNhomCongDoanRepository.deleteById(id);
    }
}
