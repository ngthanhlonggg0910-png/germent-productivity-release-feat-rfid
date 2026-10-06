package vn.nvsoft.service.impl;

import vn.nvsoft.service.GYCLQuyCachNPLService;
import vn.nvsoft.domain.GYCLQuyCachNPL;
import vn.nvsoft.repository.GYCLQuyCachNPLRepository;
import vn.nvsoft.service.dto.GYCLQuyCachNPLDTO;
import vn.nvsoft.service.mapper.GYCLQuyCachNPLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GYCLQuyCachNPL}.
 */
@Service
@Transactional
public class GYCLQuyCachNPLServiceImpl implements GYCLQuyCachNPLService {

    private final Logger log = LoggerFactory.getLogger(GYCLQuyCachNPLServiceImpl.class);

    private final GYCLQuyCachNPLRepository gYCLQuyCachNPLRepository;

    private final GYCLQuyCachNPLMapper gYCLQuyCachNPLMapper;

    public GYCLQuyCachNPLServiceImpl(GYCLQuyCachNPLRepository gYCLQuyCachNPLRepository, GYCLQuyCachNPLMapper gYCLQuyCachNPLMapper) {
        this.gYCLQuyCachNPLRepository = gYCLQuyCachNPLRepository;
        this.gYCLQuyCachNPLMapper = gYCLQuyCachNPLMapper;
    }

    /**
     * Save a gYCLQuyCachNPL.
     *
     * @param gYCLQuyCachNPLDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GYCLQuyCachNPLDTO save(GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO) {
        log.debug("Request to save GYCLQuyCachNPL : {}", gYCLQuyCachNPLDTO);
        GYCLQuyCachNPL gYCLQuyCachNPL = gYCLQuyCachNPLMapper.toEntity(gYCLQuyCachNPLDTO);
        gYCLQuyCachNPL = gYCLQuyCachNPLRepository.save(gYCLQuyCachNPL);
        return gYCLQuyCachNPLMapper.toDto(gYCLQuyCachNPL);
    }

    /**
     * Get all the gYCLQuyCachNPLS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GYCLQuyCachNPLDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GYCLQuyCachNPLS");
        return gYCLQuyCachNPLRepository.findAll(pageable)
            .map(gYCLQuyCachNPLMapper::toDto);
    }


    /**
     * Get one gYCLQuyCachNPL by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GYCLQuyCachNPLDTO> findOne(Long id) {
        log.debug("Request to get GYCLQuyCachNPL : {}", id);
        return gYCLQuyCachNPLRepository.findById(id)
            .map(gYCLQuyCachNPLMapper::toDto);
    }

    /**
     * Delete the gYCLQuyCachNPL by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GYCLQuyCachNPL : {}", id);
        gYCLQuyCachNPLRepository.deleteById(id);
    }
}
