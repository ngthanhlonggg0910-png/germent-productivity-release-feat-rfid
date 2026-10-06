package vn.nvsoft.service.impl;

import vn.nvsoft.service.DoKimMdService;
import vn.nvsoft.domain.DoKimMd;
import vn.nvsoft.repository.DoKimMdRepository;
import vn.nvsoft.service.dto.DoKimMdDTO;
import vn.nvsoft.service.mapper.DoKimMdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DoKimMd}.
 */
@Service
@Transactional
public class DoKimMdServiceImpl implements DoKimMdService {

    private final Logger log = LoggerFactory.getLogger(DoKimMdServiceImpl.class);

    private final DoKimMdRepository doKimMdRepository;

    private final DoKimMdMapper doKimMdMapper;

    public DoKimMdServiceImpl(DoKimMdRepository doKimMdRepository, DoKimMdMapper doKimMdMapper) {
        this.doKimMdRepository = doKimMdRepository;
        this.doKimMdMapper = doKimMdMapper;
    }

    /**
     * Save a doKimMd.
     *
     * @param doKimMdDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DoKimMdDTO save(DoKimMdDTO doKimMdDTO) {
        log.debug("Request to save DoKimMd : {}", doKimMdDTO);
        DoKimMd doKimMd = doKimMdMapper.toEntity(doKimMdDTO);
        doKimMd = doKimMdRepository.save(doKimMd);
        return doKimMdMapper.toDto(doKimMd);
    }

    /**
     * Get all the doKimMds.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DoKimMdDTO> findAll() {
        log.debug("Request to get all DoKimMds");
        return doKimMdRepository.findAll().stream()
            .map(doKimMdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one doKimMd by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DoKimMdDTO> findOne(Long id) {
        log.debug("Request to get DoKimMd : {}", id);
        return doKimMdRepository.findById(id)
            .map(doKimMdMapper::toDto);
    }

    /**
     * Delete the doKimMd by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DoKimMd : {}", id);
        doKimMdRepository.deleteById(id);
    }
}
