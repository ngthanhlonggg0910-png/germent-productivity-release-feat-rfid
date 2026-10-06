package vn.nvsoft.service.impl;

import vn.nvsoft.service.DoAmService;
import vn.nvsoft.domain.DoAm;
import vn.nvsoft.repository.DoAmRepository;
import vn.nvsoft.service.dto.DoAmDTO;
import vn.nvsoft.service.mapper.DoAmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DoAm}.
 */
@Service
@Transactional
public class DoAmServiceImpl implements DoAmService {

    private final Logger log = LoggerFactory.getLogger(DoAmServiceImpl.class);

    private final DoAmRepository doAmRepository;

    private final DoAmMapper doAmMapper;

    public DoAmServiceImpl(DoAmRepository doAmRepository, DoAmMapper doAmMapper) {
        this.doAmRepository = doAmRepository;
        this.doAmMapper = doAmMapper;
    }

    /**
     * Save a doAm.
     *
     * @param doAmDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DoAmDTO save(DoAmDTO doAmDTO) {
        log.debug("Request to save DoAm : {}", doAmDTO);
        DoAm doAm = doAmMapper.toEntity(doAmDTO);
        doAm = doAmRepository.save(doAm);
        return doAmMapper.toDto(doAm);
    }

    /**
     * Get all the doAms.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DoAmDTO> findAll() {
        log.debug("Request to get all DoAms");
        return doAmRepository.findAll().stream()
            .map(doAmMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one doAm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoAmDTO> findOne(Long id) {
        log.debug("Request to get DoAm : {}", id);
        return doAmRepository.findById(id)
            .map(doAmMapper::toDto);
    }

    /**
     * Delete the doAm by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DoAm : {}", id);
        doAmRepository.deleteById(id);
    }
}
