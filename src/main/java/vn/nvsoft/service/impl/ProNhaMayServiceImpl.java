package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProNhaMayService;
import vn.nvsoft.domain.ProNhaMay;
import vn.nvsoft.repository.ProNhaMayRepository;
import vn.nvsoft.service.dto.ProNhaMayDTO;
import vn.nvsoft.service.mapper.ProNhaMayMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProNhaMay}.
 */
@Service
@Transactional
public class ProNhaMayServiceImpl implements ProNhaMayService {

    private final Logger log = LoggerFactory.getLogger(ProNhaMayServiceImpl.class);

    private final ProNhaMayRepository proNhaMayRepository;

    private final ProNhaMayMapper proNhaMayMapper;

    public ProNhaMayServiceImpl(ProNhaMayRepository proNhaMayRepository, ProNhaMayMapper proNhaMayMapper) {
        this.proNhaMayRepository = proNhaMayRepository;
        this.proNhaMayMapper = proNhaMayMapper;
    }

    /**
     * Save a proNhaMay.
     *
     * @param proNhaMayDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProNhaMayDTO save(ProNhaMayDTO proNhaMayDTO) {
        log.debug("Request to save ProNhaMay : {}", proNhaMayDTO);
        ProNhaMay proNhaMay = proNhaMayMapper.toEntity(proNhaMayDTO);
        proNhaMay = proNhaMayRepository.save(proNhaMay);
        return proNhaMayMapper.toDto(proNhaMay);
    }

    /**
     * Get all the proNhaMays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProNhaMayDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProNhaMays");
        return proNhaMayRepository.findAll(pageable)
            .map(proNhaMayMapper::toDto);
    }


    /**
     * Get one proNhaMay by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProNhaMayDTO> findOne(Long id) {
        log.debug("Request to get ProNhaMay : {}", id);
        return proNhaMayRepository.findById(id)
            .map(proNhaMayMapper::toDto);
    }

    /**
     * Delete the proNhaMay by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProNhaMay : {}", id);
        proNhaMayRepository.deleteById(id);
    }
}
