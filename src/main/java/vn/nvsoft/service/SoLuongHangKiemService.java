package vn.nvsoft.service;

import vn.nvsoft.service.dto.SoLuongHangKiemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.SoLuongHangKiem}.
 */
public interface SoLuongHangKiemService {

    /**
     * Save a soLuongHangKiem.
     *
     * @param soLuongHangKiemDTO the entity to save.
     * @return the persisted entity.
     */
    SoLuongHangKiemDTO save(SoLuongHangKiemDTO soLuongHangKiemDTO);

    /**
     * Get all the soLuongHangKiems.
     *
     * @return the list of entities.
     */
    List<SoLuongHangKiemDTO> findAll();


    /**
     * Get the "id" soLuongHangKiem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoLuongHangKiemDTO> findOne(Long id);

    /**
     * Delete the "id" soLuongHangKiem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
