package vn.nvsoft.service.impl;

import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.ProIssueService;
import vn.nvsoft.domain.ProIssue;
import vn.nvsoft.repository.ProIssueRepository;
import vn.nvsoft.service.dto.ProIssueDTO;
import vn.nvsoft.service.mapper.ProIssueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProIssue}.
 */
@Service
@Transactional
public class ProIssueServiceImpl implements ProIssueService {

    private final Logger log = LoggerFactory.getLogger(ProIssueServiceImpl.class);

    private final ProIssueRepository proIssueRepository;

    private final ProIssueMapper proIssueMapper;

    public ProIssueServiceImpl(ProIssueRepository proIssueRepository, ProIssueMapper proIssueMapper) {
        this.proIssueRepository = proIssueRepository;
        this.proIssueMapper = proIssueMapper;
    }

    /**
     * Save a proIssue.
     *
     * @param proIssueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProIssueDTO save(ProIssueDTO proIssueDTO) {
        log.debug("Request to save ProIssue : {}", proIssueDTO);
        ProIssue proIssue = proIssueMapper.toEntity(proIssueDTO);
        proIssue.setNguoiTao(getUser());
        proIssue = proIssueRepository.save(proIssue);
        return proIssueMapper.toDto(proIssue);
    }

    /**
     * Save a proIssue.
     *
     * @param proIssueDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProIssueDTO saveByUser(ProIssueDTO proIssueDTO) {
        log.debug("Request to save ProIssue : {}", proIssueDTO);
        ProIssue proIssue = proIssueMapper.toEntity(proIssueDTO);
        if (proIssue.getId() == null) {
            proIssue.setNgayGioBatDau(Instant.now());
        } else {
            proIssue.setNgayGioKetThuc(Instant.now());
        }
        proIssue.setNguoiTao(getUser());
        proIssue = proIssueRepository.save(proIssue);
        return proIssueMapper.toDto(proIssue);
    }

    /**
     * Get all the proIssues.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProIssueDTO> findAll() {
        log.debug("Request to get all ProIssues");
        return proIssueRepository.findAll().stream()
            .map(proIssueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proIssue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProIssueDTO> findOne(Long id) {
        log.debug("Request to get ProIssue : {}", id);
        return proIssueRepository.findById(id)
            .map(proIssueMapper::toDto);
    }

    /**
     * Delete the proIssue by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProIssue : {}", id);
        proIssueRepository.deleteById(id);
    }

    @Override
    public List<ProIssueDTO> findAllOnToDayOrInProgress() {
        return proIssueRepository.findAllProIssueToDayAndInProgress(new Date()).stream()
            .map(proIssueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    private String getUser() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            return login.get();
        }
        return "";
    }
}
