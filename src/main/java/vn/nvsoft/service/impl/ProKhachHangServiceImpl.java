package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProKhachHangService;
import vn.nvsoft.domain.ProKhachHang;
import vn.nvsoft.repository.ProKhachHangRepository;
import vn.nvsoft.service.dto.ProKhachHangDTO;
import vn.nvsoft.service.mapper.ProKhachHangMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProKhachHang}.
 */
@Service
@Transactional
public class ProKhachHangServiceImpl implements ProKhachHangService {

    private final Logger log = LoggerFactory.getLogger(ProKhachHangServiceImpl.class);

    private final ProKhachHangRepository proKhachHangRepository;

    private final ProKhachHangMapper proKhachHangMapper;

    public ProKhachHangServiceImpl(ProKhachHangRepository proKhachHangRepository, ProKhachHangMapper proKhachHangMapper) {
        this.proKhachHangRepository = proKhachHangRepository;
        this.proKhachHangMapper = proKhachHangMapper;
    }

    /**
     * Save a proKhachHang.
     *
     * @param proKhachHangDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProKhachHangDTO save(ProKhachHangDTO proKhachHangDTO) {
        log.debug("Request to save ProKhachHang : {}", proKhachHangDTO);
        ProKhachHang proKhachHang = proKhachHangMapper.toEntity(proKhachHangDTO);
        proKhachHang = proKhachHangRepository.save(proKhachHang);
        return proKhachHangMapper.toDto(proKhachHang);
    }

    /**
     * Get all the proKhachHangs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProKhachHangDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProKhachHangs");
        return proKhachHangRepository.findAll(pageable)
            .map(proKhachHangMapper::toDto);
    }


    /**
     * Get one proKhachHang by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProKhachHangDTO> findOne(Long id) {
        log.debug("Request to get ProKhachHang : {}", id);
        return proKhachHangRepository.findById(id)
            .map(proKhachHangMapper::toDto);
    }

    /**
     * Delete the proKhachHang by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProKhachHang : {}", id);
        proKhachHangRepository.deleteById(id);
    }
}
