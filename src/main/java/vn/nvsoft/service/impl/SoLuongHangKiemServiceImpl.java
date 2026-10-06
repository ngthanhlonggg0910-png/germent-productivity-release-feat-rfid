package vn.nvsoft.service.impl;

import vn.nvsoft.service.SoLuongHangKiemService;
import vn.nvsoft.domain.SoLuongHangKiem;
import vn.nvsoft.repository.SoLuongHangKiemRepository;
import vn.nvsoft.service.dto.SoLuongHangKiemDTO;
import vn.nvsoft.service.mapper.SoLuongHangKiemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SoLuongHangKiem}.
 */
@Service
@Transactional
public class SoLuongHangKiemServiceImpl implements SoLuongHangKiemService {

    private final Logger log = LoggerFactory.getLogger(SoLuongHangKiemServiceImpl.class);

    private final SoLuongHangKiemRepository soLuongHangKiemRepository;

    private final SoLuongHangKiemMapper soLuongHangKiemMapper;

    public SoLuongHangKiemServiceImpl(SoLuongHangKiemRepository soLuongHangKiemRepository, SoLuongHangKiemMapper soLuongHangKiemMapper) {
        this.soLuongHangKiemRepository = soLuongHangKiemRepository;
        this.soLuongHangKiemMapper = soLuongHangKiemMapper;
    }

    /**
     * Save a soLuongHangKiem.
     *
     * @param soLuongHangKiemDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SoLuongHangKiemDTO save(SoLuongHangKiemDTO soLuongHangKiemDTO) {
        log.debug("Request to save SoLuongHangKiem : {}", soLuongHangKiemDTO);
        SoLuongHangKiem soLuongHangKiem = soLuongHangKiemMapper.toEntity(soLuongHangKiemDTO);
        soLuongHangKiem = soLuongHangKiemRepository.save(soLuongHangKiem);
        return soLuongHangKiemMapper.toDto(soLuongHangKiem);
    }

    /**
     * Get all the soLuongHangKiems.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SoLuongHangKiemDTO> findAll() {
        log.debug("Request to get all SoLuongHangKiems");
        return soLuongHangKiemRepository.findAll().stream()
            .map(soLuongHangKiemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one soLuongHangKiem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SoLuongHangKiemDTO> findOne(Long id) {
        log.debug("Request to get SoLuongHangKiem : {}", id);
        return soLuongHangKiemRepository.findById(id)
            .map(soLuongHangKiemMapper::toDto);
    }

    /**
     * Delete the soLuongHangKiem by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SoLuongHangKiem : {}", id);
        soLuongHangKiemRepository.deleteById(id);
    }
}
