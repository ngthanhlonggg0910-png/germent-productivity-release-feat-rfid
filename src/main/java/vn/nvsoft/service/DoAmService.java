package vn.nvsoft.service;

import vn.nvsoft.service.dto.DoAmDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.DoAm}.
 */
public interface DoAmService {

    /**
     * Save a doAm.
     *
     * @param doAmDTO the entity to save.
     * @return the persisted entity.
     */
    DoAmDTO save(DoAmDTO doAmDTO);

    /**
     * Get all the doAms.
     *
     * @return the list of entities.
     */
    List<DoAmDTO> findAll();


    /**
     * Get the "id" doAm.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DoAmDTO> findOne(Long id);

    /**
     * Delete the "id" doAm.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
