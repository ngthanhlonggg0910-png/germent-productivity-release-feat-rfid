package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProChungLoaiSanPhamDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProChungLoaiSanPham}.
 */
public interface ProChungLoaiSanPhamService {

    /**
     * Save a proChungLoaiSanPham.
     *
     * @param proChungLoaiSanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    ProChungLoaiSanPhamDTO save(ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO);

    /**
     * Get all the proChungLoaiSanPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProChungLoaiSanPhamDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proChungLoaiSanPham.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProChungLoaiSanPhamDTO> findOne(Long id);

    /**
     * Delete the "id" proChungLoaiSanPham.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
