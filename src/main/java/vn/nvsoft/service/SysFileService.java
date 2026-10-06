package vn.nvsoft.service;

import vn.nvsoft.service.dto.SysFileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SysFile}.
 */
public interface SysFileService {

    /**
     * Save a sysFile.
     *
     * @param sysFileDTO the entity to save.
     * @return the persisted entity.
     */
    SysFileDTO save(SysFileDTO sysFileDTO);

    /**
     * Get all the sysFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SysFileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sysFile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SysFileDTO> findOne(Long id);

    /**
     * Delete the "id" sysFile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
