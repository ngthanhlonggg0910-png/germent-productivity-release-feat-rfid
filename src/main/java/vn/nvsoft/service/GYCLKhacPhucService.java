package vn.nvsoft.service;

import vn.nvsoft.service.dto.GYCLKhacPhucDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.GYCLKhacPhuc}.
 */
public interface GYCLKhacPhucService {

    /**
     * Save a gYCLKhacPhuc.
     *
     * @param gYCLKhacPhucDTO the entity to save.
     * @return the persisted entity.
     */
    GYCLKhacPhucDTO save(GYCLKhacPhucDTO gYCLKhacPhucDTO);

    /**
     * Get all the gYCLKhacPhucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GYCLKhacPhucDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gYCLKhacPhuc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GYCLKhacPhucDTO> findOne(Long id);

    /**
     * Delete the "id" gYCLKhacPhuc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
