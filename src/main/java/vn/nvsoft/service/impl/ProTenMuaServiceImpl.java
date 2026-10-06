package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProTenMuaService;
import vn.nvsoft.domain.ProTenMua;
import vn.nvsoft.repository.ProTenMuaRepository;
import vn.nvsoft.service.dto.ProTenMuaDTO;
import vn.nvsoft.service.mapper.ProTenMuaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProTenMua}.
 */
@Service
@Transactional
public class ProTenMuaServiceImpl implements ProTenMuaService {

    private final Logger log = LoggerFactory.getLogger(ProTenMuaServiceImpl.class);

    private final ProTenMuaRepository proTenMuaRepository;

    private final ProTenMuaMapper proTenMuaMapper;

    public ProTenMuaServiceImpl(ProTenMuaRepository proTenMuaRepository, ProTenMuaMapper proTenMuaMapper) {
        this.proTenMuaRepository = proTenMuaRepository;
        this.proTenMuaMapper = proTenMuaMapper;
    }

    /**
     * Save a proTenMua.
     *
     * @param proTenMuaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProTenMuaDTO save(ProTenMuaDTO proTenMuaDTO) {
        log.debug("Request to save ProTenMua : {}", proTenMuaDTO);
        ProTenMua proTenMua = proTenMuaMapper.toEntity(proTenMuaDTO);
        proTenMua = proTenMuaRepository.save(proTenMua);
        return proTenMuaMapper.toDto(proTenMua);
    }

    /**
     * Get all the proTenMuas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProTenMuaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProTenMuas");
        return proTenMuaRepository.findAll(pageable)
            .map(proTenMuaMapper::toDto);
    }


    /**
     * Get one proTenMua by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProTenMuaDTO> findOne(Long id) {
        log.debug("Request to get ProTenMua : {}", id);
        return proTenMuaRepository.findById(id)
            .map(proTenMuaMapper::toDto);
    }

    /**
     * Delete the proTenMua by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProTenMua : {}", id);
        proTenMuaRepository.deleteById(id);
    }
}
