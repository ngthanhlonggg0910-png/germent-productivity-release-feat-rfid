package vn.nvsoft.service.impl;

import vn.nvsoft.domain.ProIssueCumLoi;
import vn.nvsoft.service.ProIssueLoiService;
import vn.nvsoft.domain.ProIssueLoi;
import vn.nvsoft.repository.ProIssueLoiRepository;
import vn.nvsoft.service.dto.ProIssueLoiDTO;
import vn.nvsoft.service.mapper.ProIssueLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProIssueLoi}.
 */
@Service
@Transactional
public class ProIssueLoiServiceImpl implements ProIssueLoiService {

    private final Logger log = LoggerFactory.getLogger(ProIssueLoiServiceImpl.class);

    private final ProIssueLoiRepository proIssueLoiRepository;

    private final ProIssueLoiMapper proIssueLoiMapper;

    public ProIssueLoiServiceImpl(ProIssueLoiRepository proIssueLoiRepository, ProIssueLoiMapper proIssueLoiMapper) {
        this.proIssueLoiRepository = proIssueLoiRepository;
        this.proIssueLoiMapper = proIssueLoiMapper;
    }

    /**
     * Save a proIssueLoi.
     *
     * @param proIssueLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProIssueLoiDTO save(ProIssueLoiDTO proIssueLoiDTO) {
        log.debug("Request to save ProIssueLoi : {}", proIssueLoiDTO);
        ProIssueLoi proIssueLoi = proIssueLoiMapper.toEntity(proIssueLoiDTO);
        proIssueLoi = proIssueLoiRepository.save(proIssueLoi);
        return proIssueLoiMapper.toDto(proIssueLoi);
    }

    /**
     * Get all the proIssueLois.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProIssueLoiDTO> findAll() {
        log.debug("Request to get all ProIssueLois");
        return proIssueLoiRepository.findAll().stream()
            .map(proIssueLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proIssueLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProIssueLoiDTO> findOne(Long id) {
        log.debug("Request to get ProIssueLoi : {}", id);
        return proIssueLoiRepository.findById(id)
            .map(proIssueLoiMapper::toDto);
    }

    /**
     * Delete the proIssueLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProIssueLoi : {}", id);
        proIssueLoiRepository.deleteById(id);
    }

    @Override
    public List<ProIssueLoiDTO> findAllByProIssueCumLoi(Long proIssueCumLoiId) {
        ProIssueCumLoi proIssueCumLoi = new ProIssueCumLoi();
        proIssueCumLoi.setId(proIssueCumLoiId);
        List<ProIssueLoi> proIssueLois = proIssueLoiRepository.findAllByProIssueCumLoi(proIssueCumLoi);
        List<ProIssueLoiDTO> results = proIssueLoiMapper.toDto(proIssueLois);
        results.sort(Comparator.comparing(ProIssueLoiDTO::getStt));
        return results;
    }
}
