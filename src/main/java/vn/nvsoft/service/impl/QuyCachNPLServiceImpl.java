package vn.nvsoft.service.impl;

import vn.nvsoft.service.QuyCachNPLService;
import vn.nvsoft.domain.QuyCachNPL;
import vn.nvsoft.repository.QuyCachNPLRepository;
import vn.nvsoft.service.dto.QuyCachNPLDTO;
import vn.nvsoft.service.mapper.QuyCachNPLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QuyCachNPL}.
 */
@Service
@Transactional
public class QuyCachNPLServiceImpl implements QuyCachNPLService {

    private final Logger log = LoggerFactory.getLogger(QuyCachNPLServiceImpl.class);

    private final QuyCachNPLRepository quyCachNPLRepository;

    private final QuyCachNPLMapper quyCachNPLMapper;

    public QuyCachNPLServiceImpl(QuyCachNPLRepository quyCachNPLRepository, QuyCachNPLMapper quyCachNPLMapper) {
        this.quyCachNPLRepository = quyCachNPLRepository;
        this.quyCachNPLMapper = quyCachNPLMapper;
    }

    /**
     * Save a quyCachNPL.
     *
     * @param quyCachNPLDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public QuyCachNPLDTO save(QuyCachNPLDTO quyCachNPLDTO) {
        log.debug("Request to save QuyCachNPL : {}", quyCachNPLDTO);
        QuyCachNPL quyCachNPL = quyCachNPLMapper.toEntity(quyCachNPLDTO);
        quyCachNPL = quyCachNPLRepository.save(quyCachNPL);
        return quyCachNPLMapper.toDto(quyCachNPL);
    }

    /**
     * Get all the quyCachNPLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuyCachNPLDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuyCachNPLS");
        return quyCachNPLRepository.findAll(pageable)
            .map(quyCachNPLMapper::toDto);
    }


    /**
     * Get one quyCachNPL by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QuyCachNPLDTO> findOne(Long id) {
        log.debug("Request to get QuyCachNPL : {}", id);
        return quyCachNPLRepository.findById(id)
            .map(quyCachNPLMapper::toDto);
    }

    /**
     * Delete the quyCachNPL by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuyCachNPL : {}", id);
        quyCachNPLRepository.deleteById(id);
    }
}
