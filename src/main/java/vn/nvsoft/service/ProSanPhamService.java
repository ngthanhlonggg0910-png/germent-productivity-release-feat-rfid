package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProSanPhamDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProSanPham}.
 */
public interface ProSanPhamService {

    /**
     * Save a proSanPham.
     *
     * @param proSanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    ProSanPhamDTO save(ProSanPhamDTO proSanPhamDTO);

    /**
     * Get all the proSanPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProSanPhamDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proSanPham.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProSanPhamDTO> findOne(Long id);

    /**
     * Delete the "id" proSanPham.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
