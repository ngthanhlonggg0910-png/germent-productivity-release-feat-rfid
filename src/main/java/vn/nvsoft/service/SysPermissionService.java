package vn.nvsoft.service;

import vn.nvsoft.service.dto.SysPermissionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SysPermission}.
 */
public interface SysPermissionService {

    /**
     * Save a sysPermission.
     *
     * @param sysPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    SysPermissionDTO save(SysPermissionDTO sysPermissionDTO);

    /**
     * Get all the sysPermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysPermissionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysPermission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysPermissionDTO> findOne(Long id);

    /**
     * Delete the "id" sysPermission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
