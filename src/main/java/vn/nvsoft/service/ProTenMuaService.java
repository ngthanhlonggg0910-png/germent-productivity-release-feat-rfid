package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProTenMuaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProTenMua}.
 */
public interface ProTenMuaService {

    /**
     * Save a proTenMua.
     *
     * @param proTenMuaDTO the entity to save.
     * @return the persisted entity.
     */
    ProTenMuaDTO save(ProTenMuaDTO proTenMuaDTO);

    /**
     * Get all the proTenMuas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProTenMuaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proTenMua.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProTenMuaDTO> findOne(Long id);

    /**
     * Delete the "id" proTenMua.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
