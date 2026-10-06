package vn.nvsoft.service.impl;

import vn.nvsoft.service.SysFunctionService;
import vn.nvsoft.domain.SysFunction;
import vn.nvsoft.repository.SysFunctionRepository;
import vn.nvsoft.service.dto.SysFunctionDTO;
import vn.nvsoft.service.mapper.SysFunctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysFunction}.
 */
@Service
@Transactional
public class SysFunctionServiceImpl implements SysFunctionService {

    private final Logger log = LoggerFactory.getLogger(SysFunctionServiceImpl.class);

    private final SysFunctionRepository sysFunctionRepository;

    private final SysFunctionMapper sysFunctionMapper;

    public SysFunctionServiceImpl(SysFunctionRepository sysFunctionRepository, SysFunctionMapper sysFunctionMapper) {
        this.sysFunctionRepository = sysFunctionRepository;
        this.sysFunctionMapper = sysFunctionMapper;
    }

    /**
     * Save a sysFunction.
     *
     * @param sysFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysFunctionDTO save(SysFunctionDTO sysFunctionDTO) {
        log.debug("Request to save SysFunction : {}", sysFunctionDTO);
        SysFunction sysFunction = sysFunctionMapper.toEntity(sysFunctionDTO);
        sysFunction = sysFunctionRepository.save(sysFunction);
        return sysFunctionMapper.toDto(sysFunction);
    }

    /**
     * Get all the sysFunctions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysFunctionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysFunctions");
        return sysFunctionRepository.findAll(pageable)
            .map(sysFunctionMapper::toDto);
    }


    /**
     * Get one sysFunction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysFunctionDTO> findOne(Long id) {
        log.debug("Request to get SysFunction : {}", id);
        return sysFunctionRepository.findById(id)
            .map(sysFunctionMapper::toDto);
    }

    /**
     * Delete the sysFunction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysFunction : {}", id);
        sysFunctionRepository.deleteById(id);
    }
}
