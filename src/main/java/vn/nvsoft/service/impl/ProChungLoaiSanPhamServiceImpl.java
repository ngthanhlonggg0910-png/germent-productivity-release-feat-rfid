package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProChungLoaiSanPhamService;
import vn.nvsoft.domain.ProChungLoaiSanPham;
import vn.nvsoft.repository.ProChungLoaiSanPhamRepository;
import vn.nvsoft.service.dto.ProChungLoaiSanPhamDTO;
import vn.nvsoft.service.mapper.ProChungLoaiSanPhamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProChungLoaiSanPham}.
 */
@Service
@Transactional
public class ProChungLoaiSanPhamServiceImpl implements ProChungLoaiSanPhamService {

    private final Logger log = LoggerFactory.getLogger(ProChungLoaiSanPhamServiceImpl.class);

    private final ProChungLoaiSanPhamRepository proChungLoaiSanPhamRepository;

    private final ProChungLoaiSanPhamMapper proChungLoaiSanPhamMapper;

    public ProChungLoaiSanPhamServiceImpl(ProChungLoaiSanPhamRepository proChungLoaiSanPhamRepository, ProChungLoaiSanPhamMapper proChungLoaiSanPhamMapper) {
        this.proChungLoaiSanPhamRepository = proChungLoaiSanPhamRepository;
        this.proChungLoaiSanPhamMapper = proChungLoaiSanPhamMapper;
    }

    /**
     * Save a proChungLoaiSanPham.
     *
     * @param proChungLoaiSanPhamDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProChungLoaiSanPhamDTO save(ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO) {
        log.debug("Request to save ProChungLoaiSanPham : {}", proChungLoaiSanPhamDTO);
        ProChungLoaiSanPham proChungLoaiSanPham = proChungLoaiSanPhamMapper.toEntity(proChungLoaiSanPhamDTO);
        proChungLoaiSanPham = proChungLoaiSanPhamRepository.save(proChungLoaiSanPham);
        return proChungLoaiSanPhamMapper.toDto(proChungLoaiSanPham);
    }

    /**
     * Get all the proChungLoaiSanPhams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProChungLoaiSanPhamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProChungLoaiSanPhams");
        return proChungLoaiSanPhamRepository.findAll(pageable)
            .map(proChungLoaiSanPhamMapper::toDto);
    }


    /**
     * Get one proChungLoaiSanPham by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProChungLoaiSanPhamDTO> findOne(Long id) {
        log.debug("Request to get ProChungLoaiSanPham : {}", id);
        return proChungLoaiSanPhamRepository.findById(id)
            .map(proChungLoaiSanPhamMapper::toDto);
    }

    /**
     * Delete the proChungLoaiSanPham by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProChungLoaiSanPham : {}", id);
        proChungLoaiSanPhamRepository.deleteById(id);
    }
}
