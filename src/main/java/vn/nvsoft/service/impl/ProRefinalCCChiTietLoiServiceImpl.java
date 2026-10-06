package vn.nvsoft.service.impl;

import vn.nvsoft.domain.ProRefinalCC;
import vn.nvsoft.service.ProRefinalCCChiTietLoiService;
import vn.nvsoft.domain.ProRefinalCCChiTietLoi;
import vn.nvsoft.repository.ProRefinalCCChiTietLoiRepository;
import vn.nvsoft.service.dto.ProRefinalCCChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProRefinalCCChiTietLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProRefinalCCChiTietLoi}.
 */
@Service
@Transactional
public class ProRefinalCCChiTietLoiServiceImpl implements ProRefinalCCChiTietLoiService {

    private final Logger log = LoggerFactory.getLogger(ProRefinalCCChiTietLoiServiceImpl.class);

    private final ProRefinalCCChiTietLoiRepository proRefinalCCChiTietLoiRepository;

    private final ProRefinalCCChiTietLoiMapper proRefinalCCChiTietLoiMapper;

    public ProRefinalCCChiTietLoiServiceImpl(ProRefinalCCChiTietLoiRepository proRefinalCCChiTietLoiRepository, ProRefinalCCChiTietLoiMapper proRefinalCCChiTietLoiMapper) {
        this.proRefinalCCChiTietLoiRepository = proRefinalCCChiTietLoiRepository;
        this.proRefinalCCChiTietLoiMapper = proRefinalCCChiTietLoiMapper;
    }

    /**
     * Save a proRefinalCCChiTietLoi.
     *
     * @param proRefinalCCChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProRefinalCCChiTietLoiDTO save(ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO) {
        log.debug("Request to save ProRefinalCCChiTietLoi : {}", proRefinalCCChiTietLoiDTO);
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi = proRefinalCCChiTietLoiMapper.toEntity(proRefinalCCChiTietLoiDTO);
        proRefinalCCChiTietLoi = proRefinalCCChiTietLoiRepository.save(proRefinalCCChiTietLoi);
        return proRefinalCCChiTietLoiMapper.toDto(proRefinalCCChiTietLoi);
    }

    /**
     * Get all the proRefinalCCChiTietLois.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProRefinalCCChiTietLoiDTO> findAll() {
        log.debug("Request to get all ProRefinalCCChiTietLois");
        return proRefinalCCChiTietLoiRepository.findAll().stream()
            .map(proRefinalCCChiTietLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proRefinalCCChiTietLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProRefinalCCChiTietLoiDTO> findOne(Long id) {
        log.debug("Request to get ProRefinalCCChiTietLoi : {}", id);
        return proRefinalCCChiTietLoiRepository.findById(id)
            .map(proRefinalCCChiTietLoiMapper::toDto);
    }

    /**
     * Delete the proRefinalCCChiTietLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProRefinalCCChiTietLoi : {}", id);
        proRefinalCCChiTietLoiRepository.deleteById(id);
    }

    @Override
    public List<ProRefinalCCChiTietLoiDTO> findAllByProRefinalCC(ProRefinalCC refinal) {
        return proRefinalCCChiTietLoiRepository.findAllByProRefinalCC(refinal).stream()
            .map(proRefinalCCChiTietLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
