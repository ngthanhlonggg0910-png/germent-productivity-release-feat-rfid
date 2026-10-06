package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProLoaiChuyenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProLoaiChuyen}.
 */
public interface ProLoaiChuyenService {

    /**
     * Save a proLoaiChuyen.
     *
     * @param proLoaiChuyenDTO the entity to save.
     * @return the persisted entity.
     */
    ProLoaiChuyenDTO save(ProLoaiChuyenDTO proLoaiChuyenDTO);

    /**
     * Get all the proLoaiChuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProLoaiChuyenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proLoaiChuyen.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProLoaiChuyenDTO> findOne(Long id);

    /**
     * Delete the "id" proLoaiChuyen.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
