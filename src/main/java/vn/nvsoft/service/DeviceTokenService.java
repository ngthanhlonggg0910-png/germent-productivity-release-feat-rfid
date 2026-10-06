package vn.nvsoft.service;

import vn.nvsoft.service.dto.DeviceTokenDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.DeviceToken}.
 */
public interface DeviceTokenService {

    /**
     * Save a deviceToken.
     *
     * @param deviceTokenDTO the entity to save.
     * @return the persisted entity.
     */
    DeviceTokenDTO save(DeviceTokenDTO deviceTokenDTO);

    DeviceTokenDTO createOrUpdate(DeviceTokenDTO deviceTokenDTO);

    /**
     * Get all the deviceTokens.
     *
     * @return the list of entities.
     */
    List<DeviceTokenDTO> findAll();


    /**
     * Get the "id" deviceToken.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeviceTokenDTO> findOne(Long id);

    /**
     * Delete the "id" deviceToken.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get device token base on user name
     * @param userNames
     * @return
     */
    List<String> findDeviceTokenByUserName(List<String> userNames);
}
