package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.service.dto.ThongSoThucThiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ThongSoThucThi}.
 */
public interface ThongSoThucThiService {

    /**
     * Save a thongSoThucThi.
     *
     * @param thongSoThucThiDTO the entity to save.
     * @return the persisted entity.
     */
    ThongSoThucThiDTO save(ThongSoThucThiDTO thongSoThucThiDTO);

    /**
     * Get all the thongSoThucThis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThongSoThucThiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thongSoThucThi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongSoThucThiDTO> findOne(Long id);

    /**
     * Delete the "id" thongSoThucThi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all ThongSoThucThiDTO by ThongSoSPId
     * If not found, will create default 10 ThongSoThucThiDTO per ThongSoSPId per Size on ThongSoSPSize
     * @param thongSoSPId
     * @param keHoachSanXuatId
     * @return
     */
    List<ThongSoThucThiDTO> findAllByThongSoDoSPId(Long thongSoSPId, Long keHoachSanXuatId, Long checkPointId,String mausanpham);
}
