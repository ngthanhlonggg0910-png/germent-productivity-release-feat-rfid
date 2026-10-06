package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ThongSoDoSPSize}.
 */
public interface ThongSoDoSPSizeService {

    /**
     * Save a thongSoDoSPSize.
     *
     * @param thongSoDoSPSizeDTO the entity to save.
     * @return the persisted entity.
     */
    ThongSoDoSPSizeDTO save(ThongSoDoSPSizeDTO thongSoDoSPSizeDTO);

    /**
     * Get all the thongSoDoSPSizes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThongSoDoSPSizeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thongSoDoSPSize.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongSoDoSPSizeDTO> findOne(Long id);

    /**
     * Delete the "id" thongSoDoSPSize.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ThongSoDoSPSizeDTO> findAllByThongSoDoSP(ThongSoDoSP thongSoDoSP);
}
