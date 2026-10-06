package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProIssueLoiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProIssueLoi}.
 */
public interface ProIssueLoiService {

    /**
     * Save a proIssueLoi.
     *
     * @param proIssueLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProIssueLoiDTO save(ProIssueLoiDTO proIssueLoiDTO);

    /**
     * Get all the proIssueLois.
     *
     * @return the list of entities.
     */
    List<ProIssueLoiDTO> findAll();


    /**
     * Get the "id" proIssueLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProIssueLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proIssueLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProIssueLoiDTO> findAllByProIssueCumLoi(Long proIssueCumLoiId);
}
