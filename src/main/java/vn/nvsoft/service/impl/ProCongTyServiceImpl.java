package vn.nvsoft.service.impl;

import vn.nvsoft.service.ProCongTyService;
import vn.nvsoft.domain.ProCongTy;
import vn.nvsoft.repository.ProCongTyRepository;
import vn.nvsoft.service.dto.ProCongTyDTO;
import vn.nvsoft.service.mapper.ProCongTyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProCongTy}.
 */
@Service
@Transactional
public class ProCongTyServiceImpl implements ProCongTyService {

    private final Logger log = LoggerFactory.getLogger(ProCongTyServiceImpl.class);

    private final ProCongTyRepository proCongTyRepository;

    private final ProCongTyMapper proCongTyMapper;

    public ProCongTyServiceImpl(ProCongTyRepository proCongTyRepository, ProCongTyMapper proCongTyMapper) {
        this.proCongTyRepository = proCongTyRepository;
        this.proCongTyMapper = proCongTyMapper;
    }

    /**
     * Save a proCongTy.
     *
     * @param proCongTyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProCongTyDTO save(ProCongTyDTO proCongTyDTO) {
        log.debug("Request to save ProCongTy : {}", proCongTyDTO);
        ProCongTy proCongTy = proCongTyMapper.toEntity(proCongTyDTO);
        proCongTy = proCongTyRepository.save(proCongTy);
        return proCongTyMapper.toDto(proCongTy);
    }

    /**
     * Get all the proCongTies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProCongTyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProCongTies");
        return proCongTyRepository.findAll(pageable)
            .map(proCongTyMapper::toDto);
    }


    /**
     * Get one proCongTy by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProCongTyDTO> findOne(Long id) {
        log.debug("Request to get ProCongTy : {}", id);
        return proCongTyRepository.findById(id)
            .map(proCongTyMapper::toDto);
    }

    /**
     * Delete the proCongTy by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProCongTy : {}", id);
        proCongTyRepository.deleteById(id);
    }
}
