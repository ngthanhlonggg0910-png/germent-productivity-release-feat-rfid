package vn.nvsoft.service.impl;

import vn.nvsoft.service.GYCLKhacPhucService;
import vn.nvsoft.domain.GYCLKhacPhuc;
import vn.nvsoft.repository.GYCLKhacPhucRepository;
import vn.nvsoft.service.dto.GYCLKhacPhucDTO;
import vn.nvsoft.service.mapper.GYCLKhacPhucMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GYCLKhacPhuc}.
 */
@Service
@Transactional
public class GYCLKhacPhucServiceImpl implements GYCLKhacPhucService {

    private final Logger log = LoggerFactory.getLogger(GYCLKhacPhucServiceImpl.class);

    private final GYCLKhacPhucRepository gYCLKhacPhucRepository;

    private final GYCLKhacPhucMapper gYCLKhacPhucMapper;

    public GYCLKhacPhucServiceImpl(GYCLKhacPhucRepository gYCLKhacPhucRepository, GYCLKhacPhucMapper gYCLKhacPhucMapper) {
        this.gYCLKhacPhucRepository = gYCLKhacPhucRepository;
        this.gYCLKhacPhucMapper = gYCLKhacPhucMapper;
    }

    /**
     * Save a gYCLKhacPhuc.
     *
     * @param gYCLKhacPhucDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GYCLKhacPhucDTO save(GYCLKhacPhucDTO gYCLKhacPhucDTO) {
        log.debug("Request to save GYCLKhacPhuc : {}", gYCLKhacPhucDTO);
        GYCLKhacPhuc gYCLKhacPhuc = gYCLKhacPhucMapper.toEntity(gYCLKhacPhucDTO);
        gYCLKhacPhuc = gYCLKhacPhucRepository.save(gYCLKhacPhuc);
        return gYCLKhacPhucMapper.toDto(gYCLKhacPhuc);
    }

    /**
     * Get all the gYCLKhacPhucs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GYCLKhacPhucDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GYCLKhacPhucs");
        return gYCLKhacPhucRepository.findAll(pageable)
            .map(gYCLKhacPhucMapper::toDto);
    }


    /**
     * Get one gYCLKhacPhuc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GYCLKhacPhucDTO> findOne(Long id) {
        log.debug("Request to get GYCLKhacPhuc : {}", id);
        return gYCLKhacPhucRepository.findById(id)
            .map(gYCLKhacPhucMapper::toDto);
    }

    /**
     * Delete the gYCLKhacPhuc by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GYCLKhacPhuc : {}", id);
        gYCLKhacPhucRepository.deleteById(id);
    }
}
