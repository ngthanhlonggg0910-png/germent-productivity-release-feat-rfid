package vn.nvsoft.service;

import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.service.dto.ProCheckPointDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProCheckPoint}.
 */
public interface ProCheckPointService {

    /**
     * Save a proCheckPoint.
     *
     * @param proCheckPointDTO the entity to save.
     * @return the persisted entity.
     */
    ProCheckPointDTO save(ProCheckPointDTO proCheckPointDTO);

    /**
     * Get all the proCheckPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProCheckPointDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proCheckPoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProCheckPointDTO> findOne(Long id);

    Optional<ProCheckPoint> findOneProCheckPoint(Long id);

    /**
     * Delete the "id" proCheckPoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
