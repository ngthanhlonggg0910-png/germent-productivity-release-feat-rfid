package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.service.dto.NVButtonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.NVButton}.
 */
public interface NVButtonService {

    /**
     * Save a nVButton.
     *
     * @param nVButtonDTO the entity to save.
     * @return the persisted entity.
     */
    NVButtonDTO save(NVButtonDTO nVButtonDTO);

    /**
     * Get all the nVButtons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NVButtonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" nVButton.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NVButtonDTO> findOne(Long id);

    /**
     * Delete the "id" nVButton.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<NVButtonDTO> findByCmdTopics(String cmndTopic);

    Optional<NVButton> findOneByStatTopic(String topic);

    NVButton save(NVButton nVButton);

    List<NVButton> findByAvtyTopic(String topic);
}
