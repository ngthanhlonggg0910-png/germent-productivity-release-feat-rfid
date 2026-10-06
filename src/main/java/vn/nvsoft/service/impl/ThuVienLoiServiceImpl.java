package vn.nvsoft.service.impl;

import vn.nvsoft.service.ThuVienLoiService;
import vn.nvsoft.domain.ThuVienLoi;
import vn.nvsoft.repository.ThuVienLoiRepository;
import vn.nvsoft.service.dto.ThuVienLoiDTO;
import vn.nvsoft.service.mapper.ThuVienLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ThuVienLoi}.
 */
@Service
@Transactional
public class ThuVienLoiServiceImpl implements ThuVienLoiService {

    private final Logger log = LoggerFactory.getLogger(ThuVienLoiServiceImpl.class);

    private final ThuVienLoiRepository thuVienLoiRepository;

    private final ThuVienLoiMapper thuVienLoiMapper;

    public ThuVienLoiServiceImpl(ThuVienLoiRepository thuVienLoiRepository, ThuVienLoiMapper thuVienLoiMapper) {
        this.thuVienLoiRepository = thuVienLoiRepository;
        this.thuVienLoiMapper = thuVienLoiMapper;
    }

    /**
     * Save a thuVienLoi.
     *
     * @param thuVienLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ThuVienLoiDTO save(ThuVienLoiDTO thuVienLoiDTO) {
        log.debug("Request to save ThuVienLoi : {}", thuVienLoiDTO);
        ThuVienLoi thuVienLoi = thuVienLoiMapper.toEntity(thuVienLoiDTO);
        thuVienLoi = thuVienLoiRepository.save(thuVienLoi);
        return thuVienLoiMapper.toDto(thuVienLoi);
    }

    /**
     * Get all the thuVienLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ThuVienLoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThuVienLois");
        return thuVienLoiRepository.findAll(pageable)
            .map(thuVienLoiMapper::toDto);
    }


    /**
     * Get one thuVienLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ThuVienLoiDTO> findOne(Long id) {
        log.debug("Request to get ThuVienLoi : {}", id);
        return thuVienLoiRepository.findById(id)
            .map(thuVienLoiMapper::toDto);
    }

    /**
     * Delete the thuVienLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThuVienLoi : {}", id);
        thuVienLoiRepository.deleteById(id);
    }
}
