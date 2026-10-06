package vn.nvsoft.service.impl;

import vn.nvsoft.service.SysPermissionService;
import vn.nvsoft.domain.SysPermission;
import vn.nvsoft.repository.SysPermissionRepository;
import vn.nvsoft.service.dto.SysPermissionDTO;
import vn.nvsoft.service.mapper.SysPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysPermission}.
 */
@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService {

    private final Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    private final SysPermissionRepository sysPermissionRepository;

    private final SysPermissionMapper sysPermissionMapper;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        this.sysPermissionRepository = sysPermissionRepository;
        this.sysPermissionMapper = sysPermissionMapper;
    }

    /**
     * Save a sysPermission.
     *
     * @param sysPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysPermissionDTO save(SysPermissionDTO sysPermissionDTO) {
        log.debug("Request to save SysPermission : {}", sysPermissionDTO);
        SysPermission sysPermission = sysPermissionMapper.toEntity(sysPermissionDTO);
        sysPermission = sysPermissionRepository.save(sysPermission);
        return sysPermissionMapper.toDto(sysPermission);
    }

    /**
     * Get all the sysPermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysPermissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysPermissions");
        return sysPermissionRepository.findAll(pageable)
            .map(sysPermissionMapper::toDto);
    }


    /**
     * Get one sysPermission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysPermissionDTO> findOne(Long id) {
        log.debug("Request to get SysPermission : {}", id);
        return sysPermissionRepository.findById(id)
            .map(sysPermissionMapper::toDto);
    }

    /**
     * Delete the sysPermission by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysPermission : {}", id);
        sysPermissionRepository.deleteById(id);
    }
}
