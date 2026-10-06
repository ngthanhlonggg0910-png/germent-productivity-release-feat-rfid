package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProLoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProLoi}.
 */
public interface ProLoiService {

    /**
     * Save a proLoi.
     *
     * @param proLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProLoiDTO save(ProLoiDTO proLoiDTO);

    /**
     * Get all the proLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProLoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
