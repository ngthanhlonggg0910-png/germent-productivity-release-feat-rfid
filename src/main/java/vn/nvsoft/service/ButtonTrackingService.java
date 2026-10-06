package vn.nvsoft.service;

import vn.nvsoft.domain.ButtonTracking;
import vn.nvsoft.service.dto.ButtonTrackingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ButtonTracking}.
 */
public interface ButtonTrackingService {

    /**
     * Save a buttonTracking.
     *
     * @param buttonTrackingDTO the entity to save.
     * @return the persisted entity.
     */
    ButtonTrackingDTO save(ButtonTrackingDTO buttonTrackingDTO);

    /**
     * Get all the buttonTrackings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ButtonTrackingDTO> findAll(Pageable pageable);
    /**
     * Get all the ButtonTrackingDTO where NVButton is {@code null}.
     *
     * @return the list of entities.
     */
    List<ButtonTrackingDTO> findAllWhereNVButtonIsNull();


    /**
     * Get the "id" buttonTracking.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ButtonTrackingDTO> findOne(Long id);

    /**
     * Delete the "id" buttonTracking.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    ButtonTracking save(ButtonTracking buttonTracking);
}
