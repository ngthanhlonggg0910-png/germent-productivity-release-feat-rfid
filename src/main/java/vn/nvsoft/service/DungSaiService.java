package vn.nvsoft.service;

import vn.nvsoft.service.dto.DungSaiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.DungSai}.
 */
public interface DungSaiService {

    /**
     * Save a dungSai.
     *
     * @param dungSaiDTO the entity to save.
     * @return the persisted entity.
     */
    DungSaiDTO save(DungSaiDTO dungSaiDTO);

    /**
     * Get all the dungSais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DungSaiDTO> findAll(Pageable pageable);
    List<DungSaiDTO> findAllDungSai();


    /**
     * Get the "id" dungSai.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DungSaiDTO> findOne(Long id);

    /**
     * Delete the "id" dungSai.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
