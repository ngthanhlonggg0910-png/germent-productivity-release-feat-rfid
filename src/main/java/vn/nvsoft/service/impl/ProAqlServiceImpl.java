package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProAqlService;
import vn.nvsoft.domain.ProAql;
import vn.nvsoft.repository.ProAqlRepository;
import vn.nvsoft.service.dto.ProAqlDTO;
import vn.nvsoft.service.mapper.ProAqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProAql}.
 */
@Service
@Transactional
public class ProAqlServiceImpl implements ProAqlService {

    private final Logger log = LoggerFactory.getLogger(ProAqlServiceImpl.class);

    private final ProAqlRepository proAqlRepository;

    private final ProAqlMapper proAqlMapper;

    public ProAqlServiceImpl(ProAqlRepository proAqlRepository, ProAqlMapper proAqlMapper) {
        this.proAqlRepository = proAqlRepository;
        this.proAqlMapper = proAqlMapper;
    }

    /**
     * Save a proAql.
     *
     * @param proAqlDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProAqlDTO save(ProAqlDTO proAqlDTO) {
        log.debug("Request to save ProAql : {}", proAqlDTO);
        ProAql proAql = proAqlMapper.toEntity(proAqlDTO);
        proAql = proAqlRepository.save(proAql);
        return proAqlMapper.toDto(proAql);
    }

    /**
     * Get all the proAqls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProAqlDTO> findAll() {
        log.debug("Request to get all ProAqls");
        return proAqlRepository.findAll().stream()
            .map(proAqlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proAql by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProAqlDTO> findOne(Long id) {
        log.debug("Request to get ProAql : {}", id);
        return proAqlRepository.findById(id)
            .map(proAqlMapper::toDto);
    }

    /**
     * Delete the proAql by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProAql : {}", id);
        proAqlRepository.deleteById(id);
    }
}
