package vn.nvsoft.service;

import vn.nvsoft.service.dto.GopYChatLuongDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.GopYChatLuong}.
 */
public interface GopYChatLuongService {

    /**
     * Save a gopYChatLuong.
     *
     * @param gopYChatLuongDTO the entity to save.
     * @return the persisted entity.
     */
    GopYChatLuongDTO save(GopYChatLuongDTO gopYChatLuongDTO);

    /**
     * Get all the gopYChatLuongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GopYChatLuongDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gopYChatLuong.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GopYChatLuongDTO> findOne(Long id);

    /**
     * Delete the "id" gopYChatLuong.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
