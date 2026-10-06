package vn.nvsoft.service.impl;

import vn.nvsoft.domain.ProIssueLoi;
import vn.nvsoft.service.ProIssueCumLoiService;
import vn.nvsoft.domain.ProIssueCumLoi;
import vn.nvsoft.repository.ProIssueCumLoiRepository;
import vn.nvsoft.service.ProIssueLoiService;
import vn.nvsoft.service.dto.ProIssueCumLoiDTO;
import vn.nvsoft.service.mapper.ProIssueCumLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProIssueCumLoi}.
 */
@Service
@Transactional
public class ProIssueCumLoiServiceImpl implements ProIssueCumLoiService {

    private final Logger log = LoggerFactory.getLogger(ProIssueCumLoiServiceImpl.class);

    private final ProIssueCumLoiRepository proIssueCumLoiRepository;

    private final ProIssueCumLoiMapper proIssueCumLoiMapper;
    private final ProIssueLoiService proIssueLoiService;

    public ProIssueCumLoiServiceImpl(ProIssueCumLoiRepository proIssueCumLoiRepository, ProIssueCumLoiMapper proIssueCumLoiMapper, ProIssueLoiService proIssueLoiService) {
        this.proIssueCumLoiRepository = proIssueCumLoiRepository;
        this.proIssueCumLoiMapper = proIssueCumLoiMapper;
        this.proIssueLoiService = proIssueLoiService;
    }

    /**
     * Save a proIssueCumLoi.
     *
     * @param proIssueCumLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProIssueCumLoiDTO save(ProIssueCumLoiDTO proIssueCumLoiDTO) {
        log.debug("Request to save ProIssueCumLoi : {}", proIssueCumLoiDTO);
        ProIssueCumLoi proIssueCumLoi = proIssueCumLoiMapper.toEntity(proIssueCumLoiDTO);
        proIssueCumLoi = proIssueCumLoiRepository.save(proIssueCumLoi);
        return proIssueCumLoiMapper.toDto(proIssueCumLoi);
    }

    /**
     * Get all the proIssueCumLois.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProIssueCumLoiDTO> findAll() {
        log.debug("Request to get all ProIssueCumLois");
        return proIssueCumLoiRepository.findAll().stream()
            .map(proIssueCumLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proIssueCumLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProIssueCumLoiDTO> findOne(Long id) {
        log.debug("Request to get ProIssueCumLoi : {}", id);
        return proIssueCumLoiRepository.findById(id)
            .map(proIssueCumLoiMapper::toDto);
    }

    /**
     * Delete the proIssueCumLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProIssueCumLoi : {}", id);
        proIssueCumLoiRepository.deleteById(id);
    }

    @Override
    public List<ProIssueCumLoiDTO> findAllWithCount() {
        List<ProIssueCumLoi> proIssueCumLois = this.proIssueCumLoiRepository.findAll();
        List<ProIssueCumLoiDTO> results = new ArrayList<>();
        for (ProIssueCumLoi issueCumLois : proIssueCumLois) {
            ProIssueCumLoiDTO  dto = this.proIssueCumLoiMapper.toDto(issueCumLois);
            dto.setCount(this.proIssueLoiService.findAllByProIssueCumLoi(issueCumLois.getId()).size());
            results.add(dto);
        }
        return results;
    }
}
