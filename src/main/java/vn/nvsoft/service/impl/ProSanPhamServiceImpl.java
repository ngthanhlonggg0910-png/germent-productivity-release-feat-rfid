package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProSanPhamService;
import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.repository.ProSanPhamRepository;
import vn.nvsoft.service.dto.ProSanPhamDTO;
import vn.nvsoft.service.mapper.ProSanPhamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProSanPham}.
 */
@Service
@Transactional
public class ProSanPhamServiceImpl implements ProSanPhamService {

    private final Logger log = LoggerFactory.getLogger(ProSanPhamServiceImpl.class);

    private final ProSanPhamRepository proSanPhamRepository;

    private final ProSanPhamMapper proSanPhamMapper;

    public ProSanPhamServiceImpl(ProSanPhamRepository proSanPhamRepository, ProSanPhamMapper proSanPhamMapper) {
        this.proSanPhamRepository = proSanPhamRepository;
        this.proSanPhamMapper = proSanPhamMapper;
    }

    /**
     * Save a proSanPham.
     *
     * @param proSanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProSanPhamDTO save(ProSanPhamDTO proSanPhamDTO) {
        log.debug("Request to save ProSanPham : {}", proSanPhamDTO);
        ProSanPham proSanPham = proSanPhamMapper.toEntity(proSanPhamDTO);
        proSanPham = proSanPhamRepository.save(proSanPham);
        return proSanPhamMapper.toDto(proSanPham);
    }

    /**
     * Get all the proSanPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProSanPhamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProSanPhams");
        return proSanPhamRepository.findAll(pageable)
            .map(proSanPhamMapper::toDto);
    }


    /**
     * Get one proSanPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProSanPhamDTO> findOne(Long id) {
        log.debug("Request to get ProSanPham : {}", id);
        return proSanPhamRepository.findById(id)
            .map(proSanPhamMapper::toDto);
    }

    /**
     * Delete the proSanPham by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProSanPham : {}", id);
        proSanPhamRepository.deleteById(id);
    }
}
