package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProIssueCumLoiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProIssueCumLoi}.
 */
public interface ProIssueCumLoiService {

    /**
     * Save a proIssueCumLoi.
     *
     * @param proIssueCumLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProIssueCumLoiDTO save(ProIssueCumLoiDTO proIssueCumLoiDTO);

    /**
     * Get all the proIssueCumLois.
     *
     * @return the list of entities.
     */
    List<ProIssueCumLoiDTO> findAll();


    /**
     * Get the "id" proIssueCumLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProIssueCumLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proIssueCumLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProIssueCumLoiDTO> findAllWithCount();
}
