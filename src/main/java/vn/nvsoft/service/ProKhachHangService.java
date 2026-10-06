package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProKhachHangDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProKhachHang}.
 */
public interface ProKhachHangService {

    /**
     * Save a proKhachHang.
     *
     * @param proKhachHangDTO the entity to save.
     * @return the persisted entity.
     */
    ProKhachHangDTO save(ProKhachHangDTO proKhachHangDTO);

    /**
     * Get all the proKhachHangs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProKhachHangDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proKhachHang.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProKhachHangDTO> findOne(Long id);

    /**
     * Delete the "id" proKhachHang.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
