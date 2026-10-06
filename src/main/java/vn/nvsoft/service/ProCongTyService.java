package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProCongTyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProCongTy}.
 */
public interface ProCongTyService {

    /**
     * Save a proCongTy.
     *
     * @param proCongTyDTO the entity to save.
     * @return the persisted entity.
     */
    ProCongTyDTO save(ProCongTyDTO proCongTyDTO);

    /**
     * Get all the proCongTies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProCongTyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proCongTy.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProCongTyDTO> findOne(Long id);

    /**
     * Delete the "id" proCongTy.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
