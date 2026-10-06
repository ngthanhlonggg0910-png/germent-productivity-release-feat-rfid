package vn.nvsoft.service;

import vn.nvsoft.service.dto.DoKimMdDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.DoKimMd}.
 */
public interface DoKimMdService {

    /**
     * Save a doKimMd.
     *
     * @param doKimMdDTO the entity to save.
     * @return the persisted entity.
     */
    DoKimMdDTO save(DoKimMdDTO doKimMdDTO);

    /**
     * Get all the doKimMds.
     *
     * @return the list of entities.
     */
    List<DoKimMdDTO> findAll();


    /**
     * Get the "id" doKimMd.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DoKimMdDTO> findOne(Long id);

    /**
     * Delete the "id" doKimMd.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
