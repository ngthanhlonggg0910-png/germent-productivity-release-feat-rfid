package vn.nvsoft.service.impl;

import vn.nvsoft.domain.ProFinal;
import vn.nvsoft.service.ProFinalChiTietLoiService;
import vn.nvsoft.domain.ProFinalChiTietLoi;
import vn.nvsoft.repository.ProFinalChiTietLoiRepository;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProFinalChiTietLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProFinalChiTietLoi}.
 */
@Service
@Transactional
public class ProFinalChiTietLoiServiceImpl implements ProFinalChiTietLoiService {

    private final Logger log = LoggerFactory.getLogger(ProFinalChiTietLoiServiceImpl.class);

    private final ProFinalChiTietLoiRepository proFinalChiTietLoiRepository;

    private final ProFinalChiTietLoiMapper proFinalChiTietLoiMapper;

    public ProFinalChiTietLoiServiceImpl(ProFinalChiTietLoiRepository proFinalChiTietLoiRepository, ProFinalChiTietLoiMapper proFinalChiTietLoiMapper) {
        this.proFinalChiTietLoiRepository = proFinalChiTietLoiRepository;
        this.proFinalChiTietLoiMapper = proFinalChiTietLoiMapper;
    }

    /**
     * Save a proFinalChiTietLoi.
     *
     * @param proFinalChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProFinalChiTietLoiDTO save(ProFinalChiTietLoiDTO proFinalChiTietLoiDTO) {
        log.debug("Request to save ProFinalChiTietLoi : {}", proFinalChiTietLoiDTO);
        ProFinalChiTietLoi proFinalChiTietLoi = proFinalChiTietLoiMapper.toEntity(proFinalChiTietLoiDTO);
        proFinalChiTietLoi = proFinalChiTietLoiRepository.save(proFinalChiTietLoi);
        return proFinalChiTietLoiMapper.toDto(proFinalChiTietLoi);
    }

    /**
     * Get all the proFinalChiTietLois.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProFinalChiTietLoiDTO> findAll() {
        log.debug("Request to get all ProFinalChiTietLois");
        return proFinalChiTietLoiRepository.findAll().stream()
            .map(proFinalChiTietLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proFinalChiTietLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProFinalChiTietLoiDTO> findOne(Long id) {
        log.debug("Request to get ProFinalChiTietLoi : {}", id);
        return proFinalChiTietLoiRepository.findById(id)
            .map(proFinalChiTietLoiMapper::toDto);
    }

    /**
     * Delete the proFinalChiTietLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProFinalChiTietLoi : {}", id);
        proFinalChiTietLoiRepository.deleteById(id);
    }

    @Override
    public List<ProFinalChiTietLoiDTO> findAllByProFinal(ProFinal refinal) {
        return proFinalChiTietLoiMapper.toDto(proFinalChiTietLoiRepository.findAllByProFinal(refinal));
    }
}
