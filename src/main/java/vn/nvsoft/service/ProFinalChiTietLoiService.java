package vn.nvsoft.service;

import vn.nvsoft.domain.ProFinal;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProFinalChiTietLoi}.
 */
public interface ProFinalChiTietLoiService {

    /**
     * Save a proFinalChiTietLoi.
     *
     * @param proFinalChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProFinalChiTietLoiDTO save(ProFinalChiTietLoiDTO proFinalChiTietLoiDTO);

    /**
     * Get all the proFinalChiTietLois.
     *
     * @return the list of entities.
     */
    List<ProFinalChiTietLoiDTO> findAll();


    /**
     * Get the "id" proFinalChiTietLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProFinalChiTietLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proFinalChiTietLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProFinalChiTietLoiDTO> findAllByProFinal(ProFinal refinal);
}
