package vn.nvsoft.service;

import vn.nvsoft.service.dto.KhacPhucLoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.KhacPhucLoi}.
 */
public interface KhacPhucLoiService {

    /**
     * Save a khacPhucLoi.
     *
     * @param khacPhucLoiDTO the entity to save.
     * @return the persisted entity.
     */
    KhacPhucLoiDTO save(KhacPhucLoiDTO khacPhucLoiDTO);

    /**
     * Get all the khacPhucLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KhacPhucLoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" khacPhucLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KhacPhucLoiDTO> findOne(Long id);

    /**
     * Delete the "id" khacPhucLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
