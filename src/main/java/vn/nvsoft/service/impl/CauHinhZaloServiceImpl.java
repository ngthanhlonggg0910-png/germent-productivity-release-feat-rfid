package vn.nvsoft.service.impl;

import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.domain.CauHinhZalo;
import vn.nvsoft.repository.CauHinhZaloRepository;
import vn.nvsoft.service.dto.CauHinhZaloDTO;
import vn.nvsoft.service.mapper.CauHinhZaloMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CauHinhZalo}.
 */
@Service
@Transactional
public class CauHinhZaloServiceImpl implements CauHinhZaloService {

    private final Logger log = LoggerFactory.getLogger(CauHinhZaloServiceImpl.class);

    private final CauHinhZaloRepository cauHinhZaloRepository;

    private final CauHinhZaloMapper cauHinhZaloMapper;

    public CauHinhZaloServiceImpl(CauHinhZaloRepository cauHinhZaloRepository, CauHinhZaloMapper cauHinhZaloMapper) {
        this.cauHinhZaloRepository = cauHinhZaloRepository;
        this.cauHinhZaloMapper = cauHinhZaloMapper;
    }

    /**
     * Save a cauHinhZalo.
     *
     * @param cauHinhZaloDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CauHinhZaloDTO save(CauHinhZaloDTO cauHinhZaloDTO) {
        log.debug("Request to save CauHinhZalo : {}", cauHinhZaloDTO);
        CauHinhZalo cauHinhZalo = cauHinhZaloMapper.toEntity(cauHinhZaloDTO);
        cauHinhZalo = cauHinhZaloRepository.save(cauHinhZalo);
        return cauHinhZaloMapper.toDto(cauHinhZalo);
    }

    /**
     * Get all the cauHinhZalos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CauHinhZaloDTO> findAll() {
        log.debug("Request to get all CauHinhZalos");
        return cauHinhZaloRepository.findAll().stream()
            .map(cauHinhZaloMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one cauHinhZalo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CauHinhZaloDTO> findOne(Long id) {
        log.debug("Request to get CauHinhZalo : {}", id);
        return cauHinhZaloRepository.findById(id)
            .map(cauHinhZaloMapper::toDto);
    }

    /**
     * Delete the cauHinhZalo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CauHinhZalo : {}", id);
        cauHinhZaloRepository.deleteById(id);
    }
}
