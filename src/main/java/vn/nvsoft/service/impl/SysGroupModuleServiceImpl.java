package vn.nvsoft.service.impl;

import vn.nvsoft.service.SysGroupModuleService;
import vn.nvsoft.domain.SysGroupModule;
import vn.nvsoft.repository.SysGroupModuleRepository;
import vn.nvsoft.service.dto.SysGroupModuleDTO;
import vn.nvsoft.service.mapper.SysGroupModuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysGroupModule}.
 */
@Service
@Transactional
public class SysGroupModuleServiceImpl implements SysGroupModuleService {

    private final Logger log = LoggerFactory.getLogger(SysGroupModuleServiceImpl.class);

    private final SysGroupModuleRepository sysGroupModuleRepository;

    private final SysGroupModuleMapper sysGroupModuleMapper;

    public SysGroupModuleServiceImpl(SysGroupModuleRepository sysGroupModuleRepository, SysGroupModuleMapper sysGroupModuleMapper) {
        this.sysGroupModuleRepository = sysGroupModuleRepository;
        this.sysGroupModuleMapper = sysGroupModuleMapper;
    }

    /**
     * Save a sysGroupModule.
     *
     * @param sysGroupModuleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysGroupModuleDTO save(SysGroupModuleDTO sysGroupModuleDTO) {
        log.debug("Request to save SysGroupModule : {}", sysGroupModuleDTO);
        SysGroupModule sysGroupModule = sysGroupModuleMapper.toEntity(sysGroupModuleDTO);
        sysGroupModule = sysGroupModuleRepository.save(sysGroupModule);
        return sysGroupModuleMapper.toDto(sysGroupModule);
    }

    /**
     * Get all the sysGroupModules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysGroupModuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysGroupModules");
        return sysGroupModuleRepository.findAll(pageable)
            .map(sysGroupModuleMapper::toDto);
    }


    /**
     * Get one sysGroupModule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysGroupModuleDTO> findOne(Long id) {
        log.debug("Request to get SysGroupModule : {}", id);
        return sysGroupModuleRepository.findById(id)
            .map(sysGroupModuleMapper::toDto);
    }

    /**
     * Delete the sysGroupModule by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysGroupModule : {}", id);
        sysGroupModuleRepository.deleteById(id);
    }
}
