package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProCumLoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProCumLoi}.
 */
public interface ProCumLoiService {

    /**
     * Save a proCumLoi.
     *
     * @param proCumLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProCumLoiDTO save(ProCumLoiDTO proCumLoiDTO);

    /**
     * Get all the proCumLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProCumLoiDTO> findAll(Pageable pageable);

    public List<ProCumLoiDTO> findAllAndCount();

    /**
     * Get the "id" proCumLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProCumLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proCumLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
