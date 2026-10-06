package vn.nvsoft.service.impl;

import java.time.Instant;
import vn.nvsoft.service.DeviceService;
import vn.nvsoft.domain.Device;
import vn.nvsoft.repository.DeviceRepository;
import vn.nvsoft.service.dto.DeviceDTO;
import vn.nvsoft.service.mapper.DeviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Device}.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository deviceRepository;

    private final DeviceMapper deviceMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    /**
     * Save a device.
     *
     * @param deviceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DeviceDTO save(DeviceDTO deviceDTO) {
        log.debug("Request to save Device : {}", deviceDTO);
        Device device = deviceMapper.toEntity(deviceDTO);
        device.setTime(Instant.now());
        device = deviceRepository.save(device);
        return deviceMapper.toDto(device);
    }

    /**
     * Get all the devices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DeviceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Devices");
        return deviceRepository.findAll(pageable)
            .map(deviceMapper::toDto);
    }


    /**
     * Get one device by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceDTO> findOne(Long id) {
        log.debug("Request to get Device : {}", id);
        return deviceRepository.findById(id)
            .map(deviceMapper::toDto);
    }

    /**
     * Delete the device by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Device : {}", id);
        deviceRepository.deleteById(id);
    }

    @Override
    public Optional<DeviceDTO> findOneByLwtTopic(String avtyTopic) {
        log.debug("Request to get Device by lwt topic: {}", avtyTopic);
        return deviceRepository.findOneByLwtTopic(avtyTopic).map(deviceMapper::toDto);
    }
}
