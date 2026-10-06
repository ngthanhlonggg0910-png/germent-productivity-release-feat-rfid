package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProLoaiChuyenService;
import vn.nvsoft.domain.ProLoaiChuyen;
import vn.nvsoft.repository.ProLoaiChuyenRepository;
import vn.nvsoft.service.dto.ProLoaiChuyenDTO;
import vn.nvsoft.service.mapper.ProLoaiChuyenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProLoaiChuyen}.
 */
@Service
@Transactional
public class ProLoaiChuyenServiceImpl implements ProLoaiChuyenService {

    private final Logger log = LoggerFactory.getLogger(ProLoaiChuyenServiceImpl.class);

    private final ProLoaiChuyenRepository proLoaiChuyenRepository;

    private final ProLoaiChuyenMapper proLoaiChuyenMapper;

    public ProLoaiChuyenServiceImpl(ProLoaiChuyenRepository proLoaiChuyenRepository, ProLoaiChuyenMapper proLoaiChuyenMapper) {
        this.proLoaiChuyenRepository = proLoaiChuyenRepository;
        this.proLoaiChuyenMapper = proLoaiChuyenMapper;
    }

    /**
     * Save a proLoaiChuyen.
     *
     * @param proLoaiChuyenDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProLoaiChuyenDTO save(ProLoaiChuyenDTO proLoaiChuyenDTO) {
        log.debug("Request to save ProLoaiChuyen : {}", proLoaiChuyenDTO);
        ProLoaiChuyen proLoaiChuyen = proLoaiChuyenMapper.toEntity(proLoaiChuyenDTO);
        proLoaiChuyen = proLoaiChuyenRepository.save(proLoaiChuyen);
        return proLoaiChuyenMapper.toDto(proLoaiChuyen);
    }

    /**
     * Get all the proLoaiChuyens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProLoaiChuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProLoaiChuyens");
        return proLoaiChuyenRepository.findAll(pageable)
            .map(proLoaiChuyenMapper::toDto);
    }


    /**
     * Get one proLoaiChuyen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProLoaiChuyenDTO> findOne(Long id) {
        log.debug("Request to get ProLoaiChuyen : {}", id);
        return proLoaiChuyenRepository.findById(id)
            .map(proLoaiChuyenMapper::toDto);
    }

    /**
     * Delete the proLoaiChuyen by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProLoaiChuyen : {}", id);
        proLoaiChuyenRepository.deleteById(id);
    }
}
