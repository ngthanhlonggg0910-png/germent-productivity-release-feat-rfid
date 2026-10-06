package vn.nvsoft.service;

import vn.nvsoft.service.dto.ThongSoDoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ThongSoDo}.
 */
public interface ThongSoDoService {

    /**
     * Save a thongSoDo.
     *
     * @param thongSoDoDTO the entity to save.
     * @return the persisted entity.
     */
    ThongSoDoDTO save(ThongSoDoDTO thongSoDoDTO);

    /**
     * Get all the thongSoDos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ThongSoDoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" thongSoDo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ThongSoDoDTO> findOne(Long id);

    /**
     * Delete the "id" thongSoDo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
