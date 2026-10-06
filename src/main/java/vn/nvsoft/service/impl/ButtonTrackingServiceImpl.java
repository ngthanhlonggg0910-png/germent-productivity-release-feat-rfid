package vn.nvsoft.service.impl;

import vn.nvsoft.service.ButtonTrackingService;
import vn.nvsoft.domain.ButtonTracking;
import vn.nvsoft.repository.ButtonTrackingRepository;
import vn.nvsoft.service.dto.ButtonTrackingDTO;
import vn.nvsoft.service.mapper.ButtonTrackingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ButtonTracking}.
 */
@Service
@Transactional
public class ButtonTrackingServiceImpl implements ButtonTrackingService {

    private final Logger log = LoggerFactory.getLogger(ButtonTrackingServiceImpl.class);

    private final ButtonTrackingRepository buttonTrackingRepository;

    private final ButtonTrackingMapper buttonTrackingMapper;

    public ButtonTrackingServiceImpl(ButtonTrackingRepository buttonTrackingRepository, ButtonTrackingMapper buttonTrackingMapper) {
        this.buttonTrackingRepository = buttonTrackingRepository;
        this.buttonTrackingMapper = buttonTrackingMapper;
    }

    /**
     * Save a buttonTracking.
     *
     * @param buttonTrackingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ButtonTrackingDTO save(ButtonTrackingDTO buttonTrackingDTO) {
        log.debug("Request to save ButtonTracking : {}", buttonTrackingDTO);
        ButtonTracking buttonTracking = buttonTrackingMapper.toEntity(buttonTrackingDTO);
        buttonTracking = buttonTrackingRepository.save(buttonTracking);
        return buttonTrackingMapper.toDto(buttonTracking);
    }

    /**
     * Get all the buttonTrackings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ButtonTrackingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ButtonTrackings");
        return buttonTrackingRepository.findAll(pageable)
            .map(buttonTrackingMapper::toDto);
    }



    /**
    *  Get all the buttonTrackings where NVButton is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ButtonTrackingDTO> findAllWhereNVButtonIsNull() {
        log.debug("Request to get all buttonTrackings where NVButton is null");
        return StreamSupport
            .stream(buttonTrackingRepository.findAll().spliterator(), false)
            .filter(buttonTracking -> buttonTracking.getNVButton() == null)
            .map(buttonTrackingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one buttonTracking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ButtonTrackingDTO> findOne(Long id) {
        log.debug("Request to get ButtonTracking : {}", id);
        return buttonTrackingRepository.findById(id)
            .map(buttonTrackingMapper::toDto);
    }

    /**
     * Delete the buttonTracking by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ButtonTracking : {}", id);
        buttonTrackingRepository.deleteById(id);
    }

    @Override
    public ButtonTracking save(ButtonTracking buttonTracking) {
        log.debug("Request to save ButtonTracking : {}", buttonTracking);
        return buttonTrackingRepository.save(buttonTracking);
    }
}
