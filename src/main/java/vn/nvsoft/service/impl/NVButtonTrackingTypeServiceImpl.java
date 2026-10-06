package vn.nvsoft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import vn.nvsoft.service.NVButtonTrackingTypeService;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.repository.NVButtonTrackingTypeRepository;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;
import vn.nvsoft.service.mapper.NVButtonTrackingTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NVButtonTrackingType}.
 */
@Service
@Transactional
public class NVButtonTrackingTypeServiceImpl implements NVButtonTrackingTypeService {

    private final Logger log = LoggerFactory.getLogger(NVButtonTrackingTypeServiceImpl.class);

    private final NVButtonTrackingTypeRepository nVButtonTrackingTypeRepository;

    private final NVButtonTrackingTypeMapper nVButtonTrackingTypeMapper;

    public NVButtonTrackingTypeServiceImpl(NVButtonTrackingTypeRepository nVButtonTrackingTypeRepository, NVButtonTrackingTypeMapper nVButtonTrackingTypeMapper) {
        this.nVButtonTrackingTypeRepository = nVButtonTrackingTypeRepository;
        this.nVButtonTrackingTypeMapper = nVButtonTrackingTypeMapper;
    }

    /**
     * Save a nVButtonTrackingType.
     *
     * @param nVButtonTrackingTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NVButtonTrackingTypeDTO save(NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO) {
        log.debug("Request to save NVButtonTrackingType : {}", nVButtonTrackingTypeDTO);
        NVButtonTrackingType nVButtonTrackingType = nVButtonTrackingTypeMapper.toEntity(nVButtonTrackingTypeDTO);
        nVButtonTrackingType = nVButtonTrackingTypeRepository.save(nVButtonTrackingType);
        return nVButtonTrackingTypeMapper.toDto(nVButtonTrackingType);
    }

    /**
     * Get all the nVButtonTrackingTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NVButtonTrackingTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NVButtonTrackingTypes");
        return nVButtonTrackingTypeRepository.findAll(pageable)
            .map(nVButtonTrackingTypeMapper::toDto);
    }


    /**
     * Get one nVButtonTrackingType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NVButtonTrackingTypeDTO> findOne(Long id) {
        log.debug("Request to get NVButtonTrackingType : {}", id);
        return nVButtonTrackingTypeRepository.findById(id)
            .map(nVButtonTrackingTypeMapper::toDto);
    }

    /**
     * Delete the nVButtonTrackingType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NVButtonTrackingType : {}", id);
        nVButtonTrackingTypeRepository.deleteById(id);
    }

    @Override
    public List<NVButtonTrackingTypeDTO> findAll() {
        return nVButtonTrackingTypeRepository.findAll().stream().map(nVButtonTrackingTypeMapper::toDto).collect(Collectors.toList());
    }
}
