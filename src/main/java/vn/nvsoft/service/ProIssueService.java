package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProIssueDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProIssue}.
 */
public interface ProIssueService {

    /**
     * Save a proIssue.
     *
     * @param proIssueDTO the entity to save.
     * @return the persisted entity.
     */
    ProIssueDTO save(ProIssueDTO proIssueDTO);

    /**
     * Save a proIssue.
     *
     * @param proIssueDTO the entity to save.
     * @return the persisted entity.
     */
    ProIssueDTO saveByUser(ProIssueDTO proIssueDTO);

    /**
     * Get all the proIssues.
     *
     * @return the list of entities.
     */
    List<ProIssueDTO> findAll();


    /**
     * Get the "id" proIssue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProIssueDTO> findOne(Long id);

    /**
     * Delete the "id" proIssue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProIssueDTO> findAllOnToDayOrInProgress();
}
