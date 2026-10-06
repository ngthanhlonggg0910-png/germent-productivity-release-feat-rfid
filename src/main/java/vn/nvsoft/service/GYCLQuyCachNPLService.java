package vn.nvsoft.service;

import vn.nvsoft.service.dto.GYCLQuyCachNPLDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.GYCLQuyCachNPL}.
 */
public interface GYCLQuyCachNPLService {

    /**
     * Save a gYCLQuyCachNPL.
     *
     * @param gYCLQuyCachNPLDTO the entity to save.
     * @return the persisted entity.
     */
    GYCLQuyCachNPLDTO save(GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO);

    /**
     * Get all the gYCLQuyCachNPLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GYCLQuyCachNPLDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gYCLQuyCachNPL.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GYCLQuyCachNPLDTO> findOne(Long id);

    /**
     * Delete the "id" gYCLQuyCachNPL.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
