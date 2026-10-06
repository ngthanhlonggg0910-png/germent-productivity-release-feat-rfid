package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProNhomCongDoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProNhomCongDoan}.
 */
public interface ProNhomCongDoanService {

    /**
     * Save a proNhomCongDoan.
     *
     * @param proNhomCongDoanDTO the entity to save.
     * @return the persisted entity.
     */
    ProNhomCongDoanDTO save(ProNhomCongDoanDTO proNhomCongDoanDTO);

    /**
     * Get all the proNhomCongDoans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProNhomCongDoanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proNhomCongDoan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProNhomCongDoanDTO> findOne(Long id);

    /**
     * Delete the "id" proNhomCongDoan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
