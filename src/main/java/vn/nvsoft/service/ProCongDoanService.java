package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProCongDoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProCongDoan}.
 */
public interface ProCongDoanService {

    /**
     * Save a proCongDoan.
     *
     * @param proCongDoanDTO the entity to save.
     * @return the persisted entity.
     */
    ProCongDoanDTO save(ProCongDoanDTO proCongDoanDTO);

    /**
     * Get all the proCongDoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProCongDoanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proCongDoan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProCongDoanDTO> findOne(Long id);

    /**
     * Delete the "id" proCongDoan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
