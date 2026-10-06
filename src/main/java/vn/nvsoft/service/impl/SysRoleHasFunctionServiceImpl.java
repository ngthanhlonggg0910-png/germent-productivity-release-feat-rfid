package vn.nvsoft.service.impl;

import java.util.ArrayList;
import java.util.List;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.SysRoleHasFunctionService;
import vn.nvsoft.domain.SysRoleHasFunction;
import vn.nvsoft.repository.SysRoleHasFunctionRepository;
import vn.nvsoft.service.dto.SysRoleHasFunctionDTO;
import vn.nvsoft.service.mapper.SysGroupModuleVMMapper;
import vn.nvsoft.service.mapper.SysRoleHasFunctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import vn.nvsoft.service.vm.SysGroupModuleVM;
import vn.nvsoft.service.vm.UserPermissionVM;

/**
 * Service Implementation for managing {@link SysRoleHasFunction}.
 */
@Service
@Transactional
public class SysRoleHasFunctionServiceImpl implements SysRoleHasFunctionService {

    private final Logger log = LoggerFactory.getLogger(SysRoleHasFunctionServiceImpl.class);

    private final SysRoleHasFunctionRepository sysRoleHasFunctionRepository;
    private final SysRoleHasFunctionMapper sysRoleHasFunctionMapper;

    private final SysGroupModuleVMMapper sysGroupModuleVMMapper;

    public SysRoleHasFunctionServiceImpl(SysRoleHasFunctionRepository sysRoleHasFunctionRepository,
        SysRoleHasFunctionMapper sysRoleHasFunctionMapper,
        SysGroupModuleVMMapper sysGroupModuleVMMapper) {
        this.sysRoleHasFunctionRepository = sysRoleHasFunctionRepository;
        this.sysRoleHasFunctionMapper = sysRoleHasFunctionMapper;
        this.sysGroupModuleVMMapper = sysGroupModuleVMMapper;
    }

    /**
     * Save a sysRoleHasFunction.
     *
     * @param sysRoleHasFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysRoleHasFunctionDTO save(SysRoleHasFunctionDTO sysRoleHasFunctionDTO) {
        log.debug("Request to save SysRoleHasFunction : {}", sysRoleHasFunctionDTO);
        SysRoleHasFunction sysRoleHasFunction = sysRoleHasFunctionMapper
            .toEntity(sysRoleHasFunctionDTO);
        sysRoleHasFunction = sysRoleHasFunctionRepository.save(sysRoleHasFunction);
        return sysRoleHasFunctionMapper.toDto(sysRoleHasFunction);
    }

    /**
     * Get all the sysRoleHasFunctions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysRoleHasFunctionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysRoleHasFunctions");
        return sysRoleHasFunctionRepository.findAll(pageable)
            .map(sysRoleHasFunctionMapper::toDto);
    }


    /**
     * Get one sysRoleHasFunction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRoleHasFunctionDTO> findOne(Long id) {
        log.debug("Request to get SysRoleHasFunction : {}", id);
        return sysRoleHasFunctionRepository.findById(id)
            .map(sysRoleHasFunctionMapper::toDto);
    }

    /**
     * Delete the sysRoleHasFunction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRoleHasFunction : {}", id);
        sysRoleHasFunctionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysGroupModuleVM> getAllPermissions(boolean filterByCurrentUser) {
        List<UserPermissionVM> userPermissions = null;
        if (filterByCurrentUser) {
            Optional<String> login = SecurityUtils.getCurrentUserLogin();
            if (login.isPresent()) {
                userPermissions = sysRoleHasFunctionRepository
                    .getAllPermissionOfCurrentUsers(login.get());
            }
        } else {
            userPermissions = sysRoleHasFunctionRepository.getAllPermissions();
        }
        return sysGroupModuleVMMapper.userPermissionVMToSysGroupModuleVMs(userPermissions);
    }

    @Override
    public List<SysGroupModuleVM> getAllPermissionByRole(String role) {
        if (role != null && !role.isEmpty()) {
            return sysGroupModuleVMMapper.userPermissionVMToSysGroupModuleVMs(sysRoleHasFunctionRepository.getAllPermissionByRole(role));
        }
        return new ArrayList<>();
    }


}
