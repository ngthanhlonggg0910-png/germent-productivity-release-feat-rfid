package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.service.dto.MapButtonActionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.MapButtonAction}.
 */
public interface MapButtonActionService {

    /**
     * Save a mapButtonAction.
     *
     * @param mapButtonActionDTO the entity to save.
     * @return the persisted entity.
     */
    MapButtonActionDTO save(MapButtonActionDTO mapButtonActionDTO);

    /**
     * Get all the mapButtonActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MapButtonActionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapButtonAction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MapButtonActionDTO> findOne(Long id);

    /**
     * Delete the "id" mapButtonAction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<MapButtonActionDTO> findBySourceAliasStateAndTrackingType(Integer sourceButtonTrackingCurrentAliasState, NVButtonTrackingType trackingType);
}
