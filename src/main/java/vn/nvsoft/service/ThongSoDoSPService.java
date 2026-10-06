package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.service.dto.ThongSoDoSPDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ThongSoDoSP}.
 */
public interface ThongSoDoSPService {

    /**
     * Save a thongSoDoSP.
     *
     * @param thongSoDoSPDTO the entity to save.
     * @return the persisted entity.
     */
    ThongSoDoSPDTO save(ThongSoDoSPDTO thongSoDoSPDTO);

    /**
     * Get all the thongSoDoSPS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThongSoDoSPDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thongSoDoSP.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongSoDoSPDTO> findOne(Long id);

    /**
     * Delete the "id" thongSoDoSP.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ThongSoDoSPDTO> getBySanPhamId(Long spId);

    List<ThongSoDoSPDTO> getBySanPhamIdAndChuyenId(Long spId, Long chuyenId);
}
