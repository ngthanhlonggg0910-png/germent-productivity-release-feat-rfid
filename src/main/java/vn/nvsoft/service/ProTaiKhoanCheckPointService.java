package vn.nvsoft.service;

import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.service.dto.ProTaiKhoanCheckPointDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProTaiKhoanCheckPoint}.
 */
public interface ProTaiKhoanCheckPointService {

    /**
     * Save a proTaiKhoanCheckPoint.
     *
     * @param proTaiKhoanCheckPointDTO the entity to save.
     * @return the persisted entity.
     */
    ProTaiKhoanCheckPointDTO save(ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO);

    /**
     * Get all the proTaiKhoanCheckPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProTaiKhoanCheckPointDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proTaiKhoanCheckPoint.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProTaiKhoanCheckPointDTO> findOne(Long id);

    /**
     * Delete the "id" proTaiKhoanCheckPoint.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

//    /**
//     * Find all checkpoint that given user associated
//     * @param id the user id
//     */
//    Optional<List<ProTaiKhoanCheckPointDTO>> findAllCheckpointsOfUserId(Long id);

    /**
     * Find all checkpoint that given user associated
     * @param username the user name
     * @return
     */
    Optional<List<ProTaiKhoanCheckPoint>> findAllCheckpointsOfUserName(String username);

}
