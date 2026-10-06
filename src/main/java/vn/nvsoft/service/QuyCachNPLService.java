package vn.nvsoft.service;

import vn.nvsoft.service.dto.QuyCachNPLDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.QuyCachNPL}.
 */
public interface QuyCachNPLService {

    /**
     * Save a quyCachNPL.
     *
     * @param quyCachNPLDTO the entity to save.
     * @return the persisted entity.
     */
    QuyCachNPLDTO save(QuyCachNPLDTO quyCachNPLDTO);

    /**
     * Get all the quyCachNPLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuyCachNPLDTO> findAll(Pageable pageable);


    /**
     * Get the "id" quyCachNPL.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuyCachNPLDTO> findOne(Long id);

    /**
     * Delete the "id" quyCachNPL.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
