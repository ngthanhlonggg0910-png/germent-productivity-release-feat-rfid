package vn.nvsoft.service;

import vn.nvsoft.service.dto.SysFunctionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SysFunction}.
 */
public interface SysFunctionService {

    /**
     * Save a sysFunction.
     *
     * @param sysFunctionDTO the entity to save.
     * @return the persisted entity.
     */
    SysFunctionDTO save(SysFunctionDTO sysFunctionDTO);

    /**
     * Get all the sysFunctions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysFunctionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysFunction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysFunctionDTO> findOne(Long id);

    /**
     * Delete the "id" sysFunction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
