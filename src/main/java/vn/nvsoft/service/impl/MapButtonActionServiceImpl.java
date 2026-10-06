package vn.nvsoft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.service.MapButtonActionService;
import vn.nvsoft.domain.MapButtonAction;
import vn.nvsoft.repository.MapButtonActionRepository;
import vn.nvsoft.service.dto.MapButtonActionDTO;
import vn.nvsoft.service.mapper.MapButtonActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MapButtonAction}.
 */
@Service
@Transactional
public class MapButtonActionServiceImpl implements MapButtonActionService {

    private final Logger log = LoggerFactory.getLogger(MapButtonActionServiceImpl.class);

    private final MapButtonActionRepository mapButtonActionRepository;

    private final MapButtonActionMapper mapButtonActionMapper;

    public MapButtonActionServiceImpl(MapButtonActionRepository mapButtonActionRepository, MapButtonActionMapper mapButtonActionMapper) {
        this.mapButtonActionRepository = mapButtonActionRepository;
        this.mapButtonActionMapper = mapButtonActionMapper;
    }

    /**
     * Save a mapButtonAction.
     *
     * @param mapButtonActionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MapButtonActionDTO save(MapButtonActionDTO mapButtonActionDTO) {
        log.debug("Request to save MapButtonAction : {}", mapButtonActionDTO);
        MapButtonAction mapButtonAction = mapButtonActionMapper.toEntity(mapButtonActionDTO);
        mapButtonAction = mapButtonActionRepository.save(mapButtonAction);
        return mapButtonActionMapper.toDto(mapButtonAction);
    }

    /**
     * Get all the mapButtonActions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapButtonActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapButtonActions");
        return mapButtonActionRepository.findAll(pageable)
            .map(mapButtonActionMapper::toDto);
    }


    /**
     * Get one mapButtonAction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapButtonActionDTO> findOne(Long id) {
        log.debug("Request to get MapButtonAction : {}", id);
        return mapButtonActionRepository.findById(id)
            .map(mapButtonActionMapper::toDto);
    }

    /**
     * Delete the mapButtonAction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapButtonAction : {}", id);
        mapButtonActionRepository.deleteById(id);
    }

    @Override
    public List<MapButtonActionDTO> findBySourceAliasStateAndTrackingType(Integer sourceButtonTrackingCurrentAliasState, NVButtonTrackingType trackingType) {
        log.debug("Request to find all MapButtonAction by source alias state {} and nvButton tracking type {}", sourceButtonTrackingCurrentAliasState, trackingType.getId());
        return mapButtonActionRepository.findBySourceAliasStateAndTrackingType(sourceButtonTrackingCurrentAliasState, trackingType).stream().map(mapButtonActionMapper::toDto).collect(Collectors.toList());
    }
}
