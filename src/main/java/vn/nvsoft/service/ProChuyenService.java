package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProChuyenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProChuyen}.
 */
public interface ProChuyenService {

    /**
     * Save a proChuyen.
     *
     * @param proChuyenDTO the entity to save.
     * @return the persisted entity.
     */
    ProChuyenDTO save(ProChuyenDTO proChuyenDTO);

    /**
     * Get all the proChuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProChuyenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proChuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProChuyenDTO> findOne(Long id);

    /**
     * Delete the "id" proChuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
