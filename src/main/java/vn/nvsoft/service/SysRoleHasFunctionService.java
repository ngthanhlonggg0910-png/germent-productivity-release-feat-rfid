package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.service.dto.SysRoleHasFunctionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import vn.nvsoft.service.vm.SysGroupModuleVM;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SysRoleHasFunction}.
 */
public interface SysRoleHasFunctionService {

    /**
     * Save a sysRoleHasFunction.
     *
     * @param sysRoleHasFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    SysRoleHasFunctionDTO save(SysRoleHasFunctionDTO sysRoleHasFunctionDTO);

    /**
     * Get all the sysRoleHasFunctions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysRoleHasFunctionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysRoleHasFunction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysRoleHasFunctionDTO> findOne(Long id);

    /**
     * Delete the "id" sysRoleHasFunction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<SysGroupModuleVM> getAllPermissions(boolean filterByCurrentUser);

    List<SysGroupModuleVM> getAllPermissionByRole(String role);
}
