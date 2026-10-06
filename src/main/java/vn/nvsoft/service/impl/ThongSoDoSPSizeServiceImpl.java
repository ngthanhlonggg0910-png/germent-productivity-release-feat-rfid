package vn.nvsoft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.service.ThongSoDoSPSizeService;
import vn.nvsoft.domain.ThongSoDoSPSize;
import vn.nvsoft.repository.ThongSoDoSPSizeRepository;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;
import vn.nvsoft.service.mapper.ThongSoDoSPSizeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ThongSoDoSPSize}.
 */
@Service
@Transactional
public class ThongSoDoSPSizeServiceImpl implements ThongSoDoSPSizeService {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoSPSizeServiceImpl.class);

    private final ThongSoDoSPSizeRepository thongSoDoSPSizeRepository;

    private final ThongSoDoSPSizeMapper thongSoDoSPSizeMapper;

    public ThongSoDoSPSizeServiceImpl(ThongSoDoSPSizeRepository thongSoDoSPSizeRepository, ThongSoDoSPSizeMapper thongSoDoSPSizeMapper) {
        this.thongSoDoSPSizeRepository = thongSoDoSPSizeRepository;
        this.thongSoDoSPSizeMapper = thongSoDoSPSizeMapper;
    }

    /**
     * Save a thongSoDoSPSize.
     *
     * @param thongSoDoSPSizeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ThongSoDoSPSizeDTO save(ThongSoDoSPSizeDTO thongSoDoSPSizeDTO) {
        log.debug("Request to save ThongSoDoSPSize : {}", thongSoDoSPSizeDTO);
        ThongSoDoSPSize thongSoDoSPSize = thongSoDoSPSizeMapper.toEntity(thongSoDoSPSizeDTO);
        thongSoDoSPSize = thongSoDoSPSizeRepository.save(thongSoDoSPSize);
        return thongSoDoSPSizeMapper.toDto(thongSoDoSPSize);
    }

    /**
     * Get all the thongSoDoSPSizes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ThongSoDoSPSizeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThongSoDoSPSizes");
        return thongSoDoSPSizeRepository.findAll(pageable)
            .map(thongSoDoSPSizeMapper::toDto);
    }


    /**
     * Get one thongSoDoSPSize by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ThongSoDoSPSizeDTO> findOne(Long id) {
        log.debug("Request to get ThongSoDoSPSize : {}", id);
        return thongSoDoSPSizeRepository.findById(id)
            .map(thongSoDoSPSizeMapper::toDto);
    }

    /**
     * Delete the thongSoDoSPSize by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThongSoDoSPSize : {}", id);
        thongSoDoSPSizeRepository.deleteById(id);
    }

    @Override
    public List<ThongSoDoSPSizeDTO> findAllByThongSoDoSP(ThongSoDoSP thongSoDoSP) {
        return thongSoDoSPSizeRepository.findByThongSoDoSP(thongSoDoSP)
            .stream().map(thongSoDoSPSizeMapper::toDto).collect(Collectors.toList());
    }
}
