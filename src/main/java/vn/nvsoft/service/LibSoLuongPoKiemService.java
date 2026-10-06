package vn.nvsoft.service;

import vn.nvsoft.service.dto.LibSoLuongPoKiemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.LibSoLuongPoKiem}.
 */
public interface LibSoLuongPoKiemService {

    /**
     * Save a libSoLuongPoKiem.
     *
     * @param libSoLuongPoKiemDTO the entity to save.
     * @return the persisted entity.
     */
    LibSoLuongPoKiemDTO save(LibSoLuongPoKiemDTO libSoLuongPoKiemDTO);

    /**
     * Get all the libSoLuongPoKiems.
     *
     * @return the list of entities.
     */
    List<LibSoLuongPoKiemDTO> findAll();


    /**
     * Get the "id" libSoLuongPoKiem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LibSoLuongPoKiemDTO> findOne(Long id);

    /**
     * Delete the "id" libSoLuongPoKiem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
