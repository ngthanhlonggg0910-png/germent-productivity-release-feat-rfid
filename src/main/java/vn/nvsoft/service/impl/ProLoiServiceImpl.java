package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProLoiService;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.repository.ProLoiRepository;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.mapper.ProLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProLoi}.
 */
@Service
@Transactional
public class ProLoiServiceImpl implements ProLoiService {

    private final Logger log = LoggerFactory.getLogger(ProLoiServiceImpl.class);

    private final ProLoiRepository proLoiRepository;

    private final ProLoiMapper proLoiMapper;

    public ProLoiServiceImpl(ProLoiRepository proLoiRepository, ProLoiMapper proLoiMapper) {
        this.proLoiRepository = proLoiRepository;
        this.proLoiMapper = proLoiMapper;
    }

    /**
     * Save a proLoi.
     *
     * @param proLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProLoiDTO save(ProLoiDTO proLoiDTO) {
        log.debug("Request to save ProLoi : {}", proLoiDTO);
        ProLoi proLoi = proLoiMapper.toEntity(proLoiDTO);
        proLoi = proLoiRepository.save(proLoi);
        return proLoiMapper.toDto(proLoi);
    }

    /**
     * Get all the proLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProLoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProLois");
        return proLoiRepository.findAll(pageable)
            .map(proLoiMapper::toDto);
    }


    /**
     * Get one proLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProLoiDTO> findOne(Long id) {
        log.debug("Request to get ProLoi : {}", id);
        return proLoiRepository.findById(id)
            .map(proLoiMapper::toDto);
    }

    /**
     * Delete the proLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProLoi : {}", id);
        proLoiRepository.deleteById(id);
    }
}
