package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.domain.MapControlButton;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.service.dto.MapControlButtonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.MapControlButton}.
 */
public interface MapControlButtonService {

    /**
     * Save a mapControlButton.
     *
     * @param mapControlButtonDTO the entity to save.
     * @return the persisted entity.
     */
    MapControlButtonDTO save(MapControlButtonDTO mapControlButtonDTO);

    /**
     * Get all the mapControlButtons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MapControlButtonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mapControlButton.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MapControlButtonDTO> findOne(Long id);

    /**
     * Delete the "id" mapControlButton.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<MapControlButton> findAllBySourceButton(NVButton sourceButton);

    List<MapControlButton> findAllByTargetButton(NVButton targetButton);
}
