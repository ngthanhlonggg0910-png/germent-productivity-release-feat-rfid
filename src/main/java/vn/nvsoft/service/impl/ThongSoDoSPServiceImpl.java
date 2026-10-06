package vn.nvsoft.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.service.ThongSoDoSPService;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.repository.ThongSoDoSPRepository;
import vn.nvsoft.service.dto.ThongSoDoSPDTO;
import vn.nvsoft.service.mapper.ThongSoDoSPMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ThongSoDoSP}.
 */
@Service
@Transactional
public class ThongSoDoSPServiceImpl implements ThongSoDoSPService {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoSPServiceImpl.class);

    private final ThongSoDoSPRepository thongSoDoSPRepository;

    private final ThongSoDoSPMapper thongSoDoSPMapper;

    public ThongSoDoSPServiceImpl(ThongSoDoSPRepository thongSoDoSPRepository, ThongSoDoSPMapper thongSoDoSPMapper) {
        this.thongSoDoSPRepository = thongSoDoSPRepository;
        this.thongSoDoSPMapper = thongSoDoSPMapper;
    }

    /**
     * Save a thongSoDoSP.
     *
     * @param thongSoDoSPDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ThongSoDoSPDTO save(ThongSoDoSPDTO thongSoDoSPDTO) {
        log.debug("Request to save ThongSoDoSP : {}", thongSoDoSPDTO);
        ThongSoDoSP thongSoDoSP = thongSoDoSPMapper.toEntity(thongSoDoSPDTO);
        thongSoDoSP = thongSoDoSPRepository.save(thongSoDoSP);
        return thongSoDoSPMapper.toDto(thongSoDoSP);
    }

    /**
     * Get all the thongSoDoSPS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ThongSoDoSPDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThongSoDoSPS");
        return thongSoDoSPRepository.findAll(pageable)
            .map(thongSoDoSPMapper::toDto);
    }


    /**
     * Get one thongSoDoSP by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ThongSoDoSPDTO> findOne(Long id) {
        log.debug("Request to get ThongSoDoSP : {}", id);
        return thongSoDoSPRepository.findById(id)
            .map(thongSoDoSPMapper::toDto);
    }

    /**
     * Delete the thongSoDoSP by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThongSoDoSP : {}", id);
        thongSoDoSPRepository.deleteById(id);
    }

    /**
     * Get all thongSoDoSP by ProSanPham Id
     * @param spId ProSanPham id
     * @return List ThongSoDoSPDTO
     */
    @Override
    public List<ThongSoDoSPDTO> getBySanPhamId(Long spId) {
        log.debug("Request to get ThongSoDoSP by San Pham id : {}", spId);
        ProSanPham proSp = new ProSanPham();
        proSp.setId(spId);
        List<ThongSoDoSP> thongSoDoSPS = thongSoDoSPRepository.findAllByProSanPham(proSp);
        thongSoDoSPS.sort(Comparator.comparing(ThongSoDoSP::getStt));
        List<ThongSoDoSPDTO> results = thongSoDoSPS.stream().filter(thongSoDoSP -> !Boolean.TRUE.equals(thongSoDoSP.isRevised())).map(thongSoDoSPMapper::toDto).collect(Collectors.toList());
        results.sort(Comparator.comparing(ThongSoDoSPDTO::getStt));
        return results;
    }

    /**
     * Get all thongSoDoSP by ProSanPham Id and chuyen id
     * @param spId ProSanPham id
     * @return List ThongSoDoSPDTO
     */
    @Override
    public List<ThongSoDoSPDTO> getBySanPhamIdAndChuyenId(Long spId, Long chuyenId) {
        log.debug("Request to get ThongSoDoSP by San Pham id {} and chuyen id {}", spId, chuyenId);
        List<ThongSoDoSP> thongSoDoSPS = thongSoDoSPRepository.findAllByProSanPhamAndChuyenId(spId, chuyenId);
        thongSoDoSPS.sort(Comparator.comparing(ThongSoDoSP::getStt));
        List<ThongSoDoSPDTO> results = thongSoDoSPS.stream().filter(thongSoDoSP -> !Boolean.TRUE.equals(thongSoDoSP.isRevised())).map(thongSoDoSPMapper::toDto).collect(Collectors.toList());
        results.sort(Comparator.comparing(ThongSoDoSPDTO::getStt));
        return results;
    }
}
