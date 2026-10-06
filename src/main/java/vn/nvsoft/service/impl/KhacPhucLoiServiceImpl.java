package vn.nvsoft.service.impl;

import vn.nvsoft.service.KhacPhucLoiService;
import vn.nvsoft.domain.KhacPhucLoi;
import vn.nvsoft.repository.KhacPhucLoiRepository;
import vn.nvsoft.service.dto.KhacPhucLoiDTO;
import vn.nvsoft.service.mapper.KhacPhucLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link KhacPhucLoi}.
 */
@Service
@Transactional
public class KhacPhucLoiServiceImpl implements KhacPhucLoiService {

    private final Logger log = LoggerFactory.getLogger(KhacPhucLoiServiceImpl.class);

    private final KhacPhucLoiRepository khacPhucLoiRepository;

    private final KhacPhucLoiMapper khacPhucLoiMapper;

    public KhacPhucLoiServiceImpl(KhacPhucLoiRepository khacPhucLoiRepository, KhacPhucLoiMapper khacPhucLoiMapper) {
        this.khacPhucLoiRepository = khacPhucLoiRepository;
        this.khacPhucLoiMapper = khacPhucLoiMapper;
    }

    /**
     * Save a khacPhucLoi.
     *
     * @param khacPhucLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public KhacPhucLoiDTO save(KhacPhucLoiDTO khacPhucLoiDTO) {
        log.debug("Request to save KhacPhucLoi : {}", khacPhucLoiDTO);
        KhacPhucLoi khacPhucLoi = khacPhucLoiMapper.toEntity(khacPhucLoiDTO);
        khacPhucLoi = khacPhucLoiRepository.save(khacPhucLoi);
        return khacPhucLoiMapper.toDto(khacPhucLoi);
    }

    /**
     * Get all the khacPhucLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KhacPhucLoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KhacPhucLois");
        return khacPhucLoiRepository.findAll(pageable)
            .map(khacPhucLoiMapper::toDto);
    }


    /**
     * Get one khacPhucLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KhacPhucLoiDTO> findOne(Long id) {
        log.debug("Request to get KhacPhucLoi : {}", id);
        return khacPhucLoiRepository.findById(id)
            .map(khacPhucLoiMapper::toDto);
    }

    /**
     * Delete the khacPhucLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KhacPhucLoi : {}", id);
        khacPhucLoiRepository.deleteById(id);
    }
}
