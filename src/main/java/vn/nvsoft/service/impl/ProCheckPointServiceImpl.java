package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.domain.ProCheckPoint;
import vn.nvsoft.repository.ProCheckPointRepository;
import vn.nvsoft.service.dto.ProCheckPointDTO;
import vn.nvsoft.service.mapper.ProCheckPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProCheckPoint}.
 */
@Service
@Transactional
public class ProCheckPointServiceImpl implements ProCheckPointService {

    private final Logger log = LoggerFactory.getLogger(ProCheckPointServiceImpl.class);

    private final ProCheckPointRepository proCheckPointRepository;

    private final ProCheckPointMapper proCheckPointMapper;

    public ProCheckPointServiceImpl(ProCheckPointRepository proCheckPointRepository, ProCheckPointMapper proCheckPointMapper) {
        this.proCheckPointRepository = proCheckPointRepository;
        this.proCheckPointMapper = proCheckPointMapper;
    }

    /**
     * Save a proCheckPoint.
     *
     * @param proCheckPointDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProCheckPointDTO save(ProCheckPointDTO proCheckPointDTO) {
        log.debug("Request to save ProCheckPoint : {}", proCheckPointDTO);
        ProCheckPoint proCheckPoint = proCheckPointMapper.toEntity(proCheckPointDTO);
        proCheckPoint = proCheckPointRepository.save(proCheckPoint);
        return proCheckPointMapper.toDto(proCheckPoint);
    }

    /**
     * Get all the proCheckPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProCheckPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProCheckPoints");
        return proCheckPointRepository.findAll(pageable)
            .map(proCheckPointMapper::toDto);
    }


    /**
     * Get one proCheckPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProCheckPointDTO> findOne(Long id) {
        log.debug("Request to get ProCheckPoint : {}", id);
        return proCheckPointRepository.findById(id)
            .map(proCheckPointMapper::toDto);
    }

    @Override
    public Optional<ProCheckPoint> findOneProCheckPoint(Long id) {
        return proCheckPointRepository.findById(id);
    }

    /**
     * Delete the proCheckPoint by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProCheckPoint : {}", id);
        proCheckPointRepository.deleteById(id);
    }
}
