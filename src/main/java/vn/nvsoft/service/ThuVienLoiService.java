package vn.nvsoft.service;

import vn.nvsoft.service.dto.ThuVienLoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ThuVienLoi}.
 */
public interface ThuVienLoiService {

    /**
     * Save a thuVienLoi.
     *
     * @param thuVienLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ThuVienLoiDTO save(ThuVienLoiDTO thuVienLoiDTO);

    /**
     * Get all the thuVienLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThuVienLoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thuVienLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThuVienLoiDTO> findOne(Long id);

    /**
     * Delete the "id" thuVienLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
