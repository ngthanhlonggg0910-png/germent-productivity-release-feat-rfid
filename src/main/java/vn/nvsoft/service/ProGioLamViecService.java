package vn.nvsoft.service;

import vn.nvsoft.domain.ProGioLamViec;
import vn.nvsoft.service.dto.ProGioLamViecDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProGioLamViec}.
 */
public interface ProGioLamViecService {

    /**
     * Save a proGioLamViec.
     *
     * @param proGioLamViecDTO the entity to save.
     * @return the persisted entity.
     */
    ProGioLamViecDTO save(ProGioLamViecDTO proGioLamViecDTO);

    /**
     * Get all the proGioLamViecs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProGioLamViecDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proGioLamViec.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProGioLamViecDTO> findOne(Long id);

    /**
     * Delete the "id" proGioLamViec.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    ProGioLamViecDTO getGioLamViecHienTai();
    ProGioLamViecDTO getGioLamViecHienTai(String chuyen);
}
