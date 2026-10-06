package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.NVButtonTrackingType}.
 */
public interface NVButtonTrackingTypeService {

    /**
     * Save a nVButtonTrackingType.
     *
     * @param nVButtonTrackingTypeDTO the entity to save.
     * @return the persisted entity.
     */
    NVButtonTrackingTypeDTO save(NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO);

    /**
     * Get all the nVButtonTrackingTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NVButtonTrackingTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nVButtonTrackingType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NVButtonTrackingTypeDTO> findOne(Long id);

    /**
     * Delete the "id" nVButtonTrackingType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<NVButtonTrackingTypeDTO> findAll();
}
