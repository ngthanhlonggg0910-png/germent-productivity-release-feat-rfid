package vn.nvsoft.service.impl;

import vn.nvsoft.service.DeviceTokenService;
import vn.nvsoft.domain.DeviceToken;
import vn.nvsoft.repository.DeviceTokenRepository;
import vn.nvsoft.service.dto.DeviceTokenDTO;
import vn.nvsoft.service.mapper.DeviceTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DeviceToken}.
 */
@Service
@Transactional
public class DeviceTokenServiceImpl implements DeviceTokenService {

    private final Logger log = LoggerFactory.getLogger(DeviceTokenServiceImpl.class);

    private final DeviceTokenRepository deviceTokenRepository;

    private final DeviceTokenMapper deviceTokenMapper;

    public DeviceTokenServiceImpl(DeviceTokenRepository deviceTokenRepository, DeviceTokenMapper deviceTokenMapper) {
        this.deviceTokenRepository = deviceTokenRepository;
        this.deviceTokenMapper = deviceTokenMapper;
    }

    @Override
    public DeviceTokenDTO save(DeviceTokenDTO deviceTokenDTO) {
        log.debug("Request to save DeviceToken : {}", deviceTokenDTO);
        DeviceToken deviceToken = deviceTokenMapper.toEntity(deviceTokenDTO);
        deviceToken = deviceTokenRepository.save(deviceToken);
        return deviceTokenMapper.toDto(deviceToken);
    }

    @Override
    public DeviceTokenDTO createOrUpdate(DeviceTokenDTO deviceTokenDTO) {
        log.info("Service: createOrUpdate DeviceToken - User: {}", deviceTokenDTO.getUser());
        log.debug("Service: createOrUpdate DeviceToken - Full DTO: {}", deviceTokenDTO);
        
        DeviceToken deviceToken = deviceTokenMapper.toEntity(deviceTokenDTO);
        Optional<DeviceToken> result = deviceTokenRepository.findOneByUser(deviceToken.getUser());
        
        if (result.isPresent()) {
            log.info("Service: createOrUpdate DeviceToken - Found existing device token for user: {}, updating device", deviceToken.getUser());
            deviceToken = result.get();
            deviceToken.setDevice(deviceTokenDTO.getDevice());
        } else {
            log.info("Service: createOrUpdate DeviceToken - No existing device token found for user: {}, creating new", deviceToken.getUser());
        }
        
        deviceToken = deviceTokenRepository.save(deviceToken);
        log.info("Service: createOrUpdate DeviceToken - Saved device token with ID: {} for user: {}", 
            deviceToken.getId(), deviceToken.getUser());

        return deviceTokenMapper.toDto(deviceToken);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviceTokenDTO> findAll() {
        log.debug("Request to get all DeviceTokens");
        return deviceTokenRepository.findAll().stream()
            .map(deviceTokenMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceTokenDTO> findOne(Long id) {
        log.debug("Request to get DeviceToken : {}", id);
        return deviceTokenRepository.findById(id)
            .map(deviceTokenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DeviceToken : {}", id);
        deviceTokenRepository.deleteById(id);
    }

    /**
     * Get list device token base on list user name
     * @param userNames
     * @return
     */
    @Override
    public List<String> findDeviceTokenByUserName(List<String> userNames) {
        log.debug("Get device token of user {}.", userNames);
        return deviceTokenRepository.findDeviceTokenByUserName(userNames).stream().map(DeviceToken::getDevice).collect(Collectors.toList());
    }
}
