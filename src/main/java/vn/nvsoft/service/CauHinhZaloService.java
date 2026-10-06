package vn.nvsoft.service;

import vn.nvsoft.service.dto.CauHinhZaloDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.CauHinhZalo}.
 */
public interface CauHinhZaloService {

    /**
     * Save a cauHinhZalo.
     *
     * @param cauHinhZaloDTO the entity to save.
     * @return the persisted entity.
     */
    CauHinhZaloDTO save(CauHinhZaloDTO cauHinhZaloDTO);

    /**
     * Get all the cauHinhZalos.
     *
     * @return the list of entities.
     */
    List<CauHinhZaloDTO> findAll();


    /**
     * Get the "id" cauHinhZalo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CauHinhZaloDTO> findOne(Long id);

    /**
     * Delete the "id" cauHinhZalo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
