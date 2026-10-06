package vn.nvsoft.service;

import vn.nvsoft.service.dto.SysGroupModuleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SysGroupModule}.
 */
public interface SysGroupModuleService {

    /**
     * Save a sysGroupModule.
     *
     * @param sysGroupModuleDTO the entity to save.
     * @return the persisted entity.
     */
    SysGroupModuleDTO save(SysGroupModuleDTO sysGroupModuleDTO);

    /**
     * Get all the sysGroupModules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysGroupModuleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysGroupModule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysGroupModuleDTO> findOne(Long id);

    /**
     * Delete the "id" sysGroupModule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
