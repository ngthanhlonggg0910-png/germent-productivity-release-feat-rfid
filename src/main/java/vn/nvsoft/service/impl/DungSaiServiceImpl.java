package vn.nvsoft.service.impl;

import vn.nvsoft.service.DungSaiService;
import vn.nvsoft.domain.DungSai;
import vn.nvsoft.repository.DungSaiRepository;
import vn.nvsoft.service.dto.DungSaiDTO;
import vn.nvsoft.service.mapper.DungSaiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DungSai}.
 */
@Service
@Transactional
public class DungSaiServiceImpl implements DungSaiService {

    private final Logger log = LoggerFactory.getLogger(DungSaiServiceImpl.class);

    private final DungSaiRepository dungSaiRepository;

    private final DungSaiMapper dungSaiMapper;

    public DungSaiServiceImpl(DungSaiRepository dungSaiRepository, DungSaiMapper dungSaiMapper) {
        this.dungSaiRepository = dungSaiRepository;
        this.dungSaiMapper = dungSaiMapper;
    }

    /**
     * Save a dungSai.
     *
     * @param dungSaiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DungSaiDTO save(DungSaiDTO dungSaiDTO) {
        log.debug("Request to save DungSai : {}", dungSaiDTO);
        DungSai dungSai = dungSaiMapper.toEntity(dungSaiDTO);
        dungSai = dungSaiRepository.save(dungSai);
        return dungSaiMapper.toDto(dungSai);
    }

    /**
     * Get all the dungSais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DungSaiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DungSais");
        return dungSaiRepository.findAll(pageable)
            .map(dungSaiMapper::toDto);
    }

    @Override
    public List<DungSaiDTO> findAllDungSai() {
        return dungSaiRepository.findAllByOrderByCmValueAsc().stream().filter(dungSai -> !dungSai.getCmLabel().contains("^")
            && !dungSai.getInchLabel().contains("^")
            && (dungSai.getKhongLoadTablet() == null || !dungSai.getKhongLoadTablet()))
            .map(dungSaiMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one dungSai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DungSaiDTO> findOne(Long id) {
        log.debug("Request to get DungSai : {}", id);
        return dungSaiRepository.findById(id)
            .map(dungSaiMapper::toDto);
    }

    /**
     * Delete the dungSai by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DungSai : {}", id);
        dungSaiRepository.deleteById(id);
    }
}
