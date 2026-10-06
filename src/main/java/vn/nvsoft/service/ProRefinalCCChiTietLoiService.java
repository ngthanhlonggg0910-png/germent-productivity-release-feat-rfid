package vn.nvsoft.service;

import vn.nvsoft.domain.ProRefinalCC;
import vn.nvsoft.service.dto.ProRefinalCCChiTietLoiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProRefinalCCChiTietLoi}.
 */
public interface ProRefinalCCChiTietLoiService {

    /**
     * Save a proRefinalCCChiTietLoi.
     *
     * @param proRefinalCCChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProRefinalCCChiTietLoiDTO save(ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO);

    /**
     * Get all the proRefinalCCChiTietLois.
     *
     * @return the list of entities.
     */
    List<ProRefinalCCChiTietLoiDTO> findAll();


    /**
     * Get the "id" proRefinalCCChiTietLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProRefinalCCChiTietLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proRefinalCCChiTietLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProRefinalCCChiTietLoiDTO> findAllByProRefinalCC(ProRefinalCC refinal);
}
