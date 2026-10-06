package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProAqlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProAql}.
 */
public interface ProAqlService {

    /**
     * Save a proAql.
     *
     * @param proAqlDTO the entity to save.
     * @return the persisted entity.
     */
    ProAqlDTO save(ProAqlDTO proAqlDTO);

    /**
     * Get all the proAqls.
     *
     * @return the list of entities.
     */
    List<ProAqlDTO> findAll();


    /**
     * Get the "id" proAql.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProAqlDTO> findOne(Long id);

    /**
     * Delete the "id" proAql.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
