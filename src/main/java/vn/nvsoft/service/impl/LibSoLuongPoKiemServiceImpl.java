package vn.nvsoft.service.impl;

import vn.nvsoft.service.LibSoLuongPoKiemService;
import vn.nvsoft.domain.LibSoLuongPoKiem;
import vn.nvsoft.repository.LibSoLuongPoKiemRepository;
import vn.nvsoft.service.dto.LibSoLuongPoKiemDTO;
import vn.nvsoft.service.mapper.LibSoLuongPoKiemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LibSoLuongPoKiem}.
 */
@Service
@Transactional
public class LibSoLuongPoKiemServiceImpl implements LibSoLuongPoKiemService {

    private final Logger log = LoggerFactory.getLogger(LibSoLuongPoKiemServiceImpl.class);

    private final LibSoLuongPoKiemRepository libSoLuongPoKiemRepository;

    private final LibSoLuongPoKiemMapper libSoLuongPoKiemMapper;

    public LibSoLuongPoKiemServiceImpl(LibSoLuongPoKiemRepository libSoLuongPoKiemRepository, LibSoLuongPoKiemMapper libSoLuongPoKiemMapper) {
        this.libSoLuongPoKiemRepository = libSoLuongPoKiemRepository;
        this.libSoLuongPoKiemMapper = libSoLuongPoKiemMapper;
    }

    /**
     * Save a libSoLuongPoKiem.
     *
     * @param libSoLuongPoKiemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LibSoLuongPoKiemDTO save(LibSoLuongPoKiemDTO libSoLuongPoKiemDTO) {
        log.debug("Request to save LibSoLuongPoKiem : {}", libSoLuongPoKiemDTO);
        LibSoLuongPoKiem libSoLuongPoKiem = libSoLuongPoKiemMapper.toEntity(libSoLuongPoKiemDTO);
        libSoLuongPoKiem = libSoLuongPoKiemRepository.save(libSoLuongPoKiem);
        return libSoLuongPoKiemMapper.toDto(libSoLuongPoKiem);
    }

    /**
     * Get all the libSoLuongPoKiems.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LibSoLuongPoKiemDTO> findAll() {
        log.debug("Request to get all LibSoLuongPoKiems");
        return libSoLuongPoKiemRepository.findAll().stream()
            .map(libSoLuongPoKiemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one libSoLuongPoKiem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LibSoLuongPoKiemDTO> findOne(Long id) {
        log.debug("Request to get LibSoLuongPoKiem : {}", id);
        return libSoLuongPoKiemRepository.findById(id)
            .map(libSoLuongPoKiemMapper::toDto);
    }

    /**
     * Delete the libSoLuongPoKiem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LibSoLuongPoKiem : {}", id);
        libSoLuongPoKiemRepository.deleteById(id);
    }
}
