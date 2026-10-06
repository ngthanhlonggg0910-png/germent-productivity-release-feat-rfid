package vn.nvsoft.service.impl;

import java.util.List;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.service.MapControlButtonService;
import vn.nvsoft.domain.MapControlButton;
import vn.nvsoft.repository.MapControlButtonRepository;
import vn.nvsoft.service.dto.MapControlButtonDTO;
import vn.nvsoft.service.mapper.MapControlButtonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MapControlButton}.
 */
@Service
@Transactional
public class MapControlButtonServiceImpl implements MapControlButtonService {

    private final Logger log = LoggerFactory.getLogger(MapControlButtonServiceImpl.class);

    private final MapControlButtonRepository mapControlButtonRepository;

    private final MapControlButtonMapper mapControlButtonMapper;

    public MapControlButtonServiceImpl(MapControlButtonRepository mapControlButtonRepository, MapControlButtonMapper mapControlButtonMapper) {
        this.mapControlButtonRepository = mapControlButtonRepository;
        this.mapControlButtonMapper = mapControlButtonMapper;
    }

    /**
     * Save a mapControlButton.
     *
     * @param mapControlButtonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MapControlButtonDTO save(MapControlButtonDTO mapControlButtonDTO) {
        log.debug("Request to save MapControlButton : {}", mapControlButtonDTO);
        MapControlButton mapControlButton = mapControlButtonMapper.toEntity(mapControlButtonDTO);
        mapControlButton = mapControlButtonRepository.save(mapControlButton);
        return mapControlButtonMapper.toDto(mapControlButton);
    }

    /**
     * Get all the mapControlButtons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MapControlButtonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MapControlButtons");
        return mapControlButtonRepository.findAll(pageable)
            .map(mapControlButtonMapper::toDto);
    }


    /**
     * Get one mapControlButton by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MapControlButtonDTO> findOne(Long id) {
        log.debug("Request to get MapControlButton : {}", id);
        return mapControlButtonRepository.findById(id)
            .map(mapControlButtonMapper::toDto);
    }

    /**
     * Delete the mapControlButton by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MapControlButton : {}", id);
        mapControlButtonRepository.deleteById(id);
    }

    @Override
    public List<MapControlButton> findAllBySourceButton(NVButton sourceButton) {
        return mapControlButtonRepository.findAllBySourceButton(sourceButton);
    }

    @Override
    public List<MapControlButton> findAllByTargetButton(NVButton targetButton) {
        return mapControlButtonRepository.findAllByTargetButton(targetButton);
    }
}
