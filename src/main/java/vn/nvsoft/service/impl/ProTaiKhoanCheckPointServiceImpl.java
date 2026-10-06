package vn.nvsoft.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import vn.nvsoft.service.ProTaiKhoanCheckPointService;
import vn.nvsoft.domain.ProTaiKhoanCheckPoint;
import vn.nvsoft.repository.ProTaiKhoanCheckPointRepository;
import vn.nvsoft.service.dto.ProTaiKhoanCheckPointDTO;
import vn.nvsoft.service.mapper.ProTaiKhoanCheckPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProTaiKhoanCheckPoint}.
 */
@Service
@Transactional
public class ProTaiKhoanCheckPointServiceImpl implements ProTaiKhoanCheckPointService {

    private final Logger log = LoggerFactory.getLogger(ProTaiKhoanCheckPointServiceImpl.class);

    private final ProTaiKhoanCheckPointRepository proTaiKhoanCheckPointRepository;

    private final ProTaiKhoanCheckPointMapper proTaiKhoanCheckPointMapper;

    public ProTaiKhoanCheckPointServiceImpl(ProTaiKhoanCheckPointRepository proTaiKhoanCheckPointRepository, ProTaiKhoanCheckPointMapper proTaiKhoanCheckPointMapper) {
        this.proTaiKhoanCheckPointRepository = proTaiKhoanCheckPointRepository;
        this.proTaiKhoanCheckPointMapper = proTaiKhoanCheckPointMapper;
    }

    /**
     * Save a proTaiKhoanCheckPoint.
     *
     * @param proTaiKhoanCheckPointDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProTaiKhoanCheckPointDTO save(ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO) {
        log.debug("Request to save ProTaiKhoanCheckPoint : {}", proTaiKhoanCheckPointDTO);
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint = proTaiKhoanCheckPointMapper.toEntity(proTaiKhoanCheckPointDTO);
        proTaiKhoanCheckPoint = proTaiKhoanCheckPointRepository.save(proTaiKhoanCheckPoint);
        return proTaiKhoanCheckPointMapper.toDto(proTaiKhoanCheckPoint);
    }

    /**
     * Get all the proTaiKhoanCheckPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProTaiKhoanCheckPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProTaiKhoanCheckPoints");
        return proTaiKhoanCheckPointRepository.findAll(pageable)
            .map(proTaiKhoanCheckPointMapper::toDto);
    }


    /**
     * Get one proTaiKhoanCheckPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProTaiKhoanCheckPointDTO> findOne(Long id) {
        log.debug("Request to get ProTaiKhoanCheckPoint : {}", id);
        return proTaiKhoanCheckPointRepository.findById(id)
            .map(proTaiKhoanCheckPointMapper::toDto);
    }

    /**
     * Delete the proTaiKhoanCheckPoint by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProTaiKhoanCheckPoint : {}", id);
        proTaiKhoanCheckPointRepository.deleteById(id);
    }

//    @Override
//    public Optional<List<ProTaiKhoanCheckPoint>> findAllCheckpointsOfUserId(Long id) {
//        ProTaiKhoanCheckPoint instance = new ProTaiKhoanCheckPoint();
//        instance.setTaiKhoan();
//        Example<ProTaiKhoanCheckPoint> taiKhoanCheckPointExample = Example.of(instance);
//
//        return proTaiKhoanCheckPointRepository.findAll();
//    }

    @Override
    public Optional<List<ProTaiKhoanCheckPoint>> findAllCheckpointsOfUserName(String username) {
        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        ProTaiKhoanCheckPoint instance = new ProTaiKhoanCheckPoint();
        instance.setTaiKhoan(username);
        Example<ProTaiKhoanCheckPoint> taiKhoanCheckPointExample = Example.of(instance, caseInsensitiveExampleMatcher);
        return Optional.ofNullable(proTaiKhoanCheckPointRepository.findAll(taiKhoanCheckPointExample));
    }
}
