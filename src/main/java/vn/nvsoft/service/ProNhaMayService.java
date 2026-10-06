package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProNhaMayDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProNhaMay}.
 */
public interface ProNhaMayService {

    /**
     * Save a proNhaMay.
     *
     * @param proNhaMayDTO the entity to save.
     * @return the persisted entity.
     */
    ProNhaMayDTO save(ProNhaMayDTO proNhaMayDTO);

    /**
     * Get all the proNhaMays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProNhaMayDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proNhaMay.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProNhaMayDTO> findOne(Long id);

    /**
     * Delete the "id" proNhaMay.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
