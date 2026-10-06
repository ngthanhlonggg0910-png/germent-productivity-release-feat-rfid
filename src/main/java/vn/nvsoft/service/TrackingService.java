package vn.nvsoft.service;

import vn.nvsoft.service.dto.TrackingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.Tracking}.
 */
public interface TrackingService {

    /**
     * Save a tracking.
     *
     * @param trackingDTO the entity to save.
     * @return the persisted entity.
     */
    TrackingDTO save(TrackingDTO trackingDTO);

    /**
     * Get all the trackings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TrackingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tracking.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TrackingDTO> findOne(Long id);

    /**
     * Delete the "id" tracking.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
