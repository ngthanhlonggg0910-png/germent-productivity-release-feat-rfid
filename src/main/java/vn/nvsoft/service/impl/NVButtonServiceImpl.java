package vn.nvsoft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import vn.nvsoft.service.NVButtonService;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.repository.NVButtonRepository;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.mapper.NVButtonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link NVButton}.
 */
@Service
@Transactional
public class NVButtonServiceImpl implements NVButtonService {

    private final Logger log = LoggerFactory.getLogger(NVButtonServiceImpl.class);

    private final NVButtonRepository nVButtonRepository;

    private final NVButtonMapper nVButtonMapper;

    public NVButtonServiceImpl(NVButtonRepository nVButtonRepository, NVButtonMapper nVButtonMapper) {
        this.nVButtonRepository = nVButtonRepository;
        this.nVButtonMapper = nVButtonMapper;
    }

    /**
     * Save a nVButton.
     *
     * @param nVButtonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NVButtonDTO save(NVButtonDTO nVButtonDTO) {
        log.debug("Request to save NVButton : {}", nVButtonDTO);
        NVButton nVButton = nVButtonMapper.toEntity(nVButtonDTO);
        nVButton = nVButtonRepository.save(nVButton);
        return nVButtonMapper.toDto(nVButton);
    }

    /**
     * Get all the nVButtons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NVButtonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NVButtons");
        return nVButtonRepository.findAll(pageable)
            .map(nVButtonMapper::toDto);
    }


    /**
     * Get one nVButton by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NVButtonDTO> findOne(Long id) {
        log.debug("Request to get NVButton : {}", id);
        return nVButtonRepository.findById(id)
            .map(nVButtonMapper::toDto);
    }

    /**
     * Delete the nVButton by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NVButton : {}", id);
        nVButtonRepository.deleteById(id);
    }

    @Override
    public List<NVButtonDTO> findByCmdTopics(String cmndTopic) {
        log.debug("Request to find all NVButton by cmnd topic : {}", cmndTopic);
        return nVButtonRepository.findAllByCmndTopic(cmndTopic).stream().map(nVButtonMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<NVButton> findOneByStatTopic(String topic) {
        log.debug("Request to find all NVButton by Stat topic : {}", topic);
        return nVButtonRepository.findOneByStatTopic(topic);
    }

    @Override
    public NVButton save(NVButton nVButton) {
        log.debug("Request to save NVButton : {}", nVButton);
        return nVButtonRepository.save(nVButton);
    }

    @Override
    public List<NVButton> findByAvtyTopic(String avtyTopic) {
        log.debug("Request to find all NVButton by Avty topic : {}", avtyTopic);
        return nVButtonRepository.findByAvtyTopic(avtyTopic);
    }
}
