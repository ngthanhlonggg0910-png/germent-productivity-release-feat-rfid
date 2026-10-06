package vn.nvsoft.service;

import vn.nvsoft.domain.ProRefinal;
import vn.nvsoft.domain.ProRefinalChiTietLoi;
import vn.nvsoft.service.dto.ProRefinalChiTietLoiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProRefinalChiTietLoi}.
 */
public interface ProRefinalChiTietLoiService {

    /**
     * Save a proRefinalChiTietLoi.
     *
     * @param proRefinalChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProRefinalChiTietLoiDTO save(ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO);

    /**
     * Get all the proRefinalChiTietLois.
     *
     * @return the list of entities.
     */
    List<ProRefinalChiTietLoiDTO> findAll();


    /**
     * Get the "id" proRefinalChiTietLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProRefinalChiTietLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proRefinalChiTietLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProRefinalChiTietLoiDTO> findAllByProRefinal(ProRefinal refinal);

}
