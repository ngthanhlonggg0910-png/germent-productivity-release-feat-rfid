package vn.nvsoft.service.impl;

import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.domain.ProRefinal;
import vn.nvsoft.service.CommonFactoryService;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.service.ProRefinalChiTietLoiService;
import vn.nvsoft.domain.ProRefinalChiTietLoi;
import vn.nvsoft.repository.ProRefinalChiTietLoiRepository;
import vn.nvsoft.service.dto.ManufacturePlanViewModelDTO;
import vn.nvsoft.service.dto.ProRefinalChiTietLoiDTO;
import vn.nvsoft.service.mapper.ProRefinalChiTietLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProRefinalChiTietLoi}.
 */
@Service
@Transactional
public class ProRefinalChiTietLoiServiceImpl implements ProRefinalChiTietLoiService {

    private final Logger log = LoggerFactory.getLogger(ProRefinalChiTietLoiServiceImpl.class);

    private final ProRefinalChiTietLoiRepository proRefinalChiTietLoiRepository;

    private final ProRefinalChiTietLoiMapper proRefinalChiTietLoiMapper;

    public ProRefinalChiTietLoiServiceImpl(ProRefinalChiTietLoiRepository proRefinalChiTietLoiRepository, ProRefinalChiTietLoiMapper proRefinalChiTietLoiMapper) {
        this.proRefinalChiTietLoiRepository = proRefinalChiTietLoiRepository;
        this.proRefinalChiTietLoiMapper = proRefinalChiTietLoiMapper;
    }

    /**
     * Save a proRefinalChiTietLoi.
     *
     * @param proRefinalChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProRefinalChiTietLoiDTO save(ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO) {
        log.debug("Request to save ProRefinalChiTietLoi : {}", proRefinalChiTietLoiDTO);
        ProRefinalChiTietLoi proRefinalChiTietLoi = proRefinalChiTietLoiMapper.toEntity(proRefinalChiTietLoiDTO);
        proRefinalChiTietLoi = proRefinalChiTietLoiRepository.save(proRefinalChiTietLoi);
        return proRefinalChiTietLoiMapper.toDto(proRefinalChiTietLoi);
    }

    /**
     * Get all the proRefinalChiTietLois.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProRefinalChiTietLoiDTO> findAll() {
        log.debug("Request to get all ProRefinalChiTietLois");
        return proRefinalChiTietLoiRepository.findAll().stream()
            .map(proRefinalChiTietLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proRefinalChiTietLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProRefinalChiTietLoiDTO> findOne(Long id) {
        log.debug("Request to get ProRefinalChiTietLoi : {}", id);
        return proRefinalChiTietLoiRepository.findById(id)
            .map(proRefinalChiTietLoiMapper::toDto);
    }

    /**
     * Delete the proRefinalChiTietLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProRefinalChiTietLoi : {}", id);
        proRefinalChiTietLoiRepository.deleteById(id);
    }

    @Override
    public List<ProRefinalChiTietLoiDTO> findAllByProRefinal(ProRefinal refinal) {
        return proRefinalChiTietLoiRepository.findAllByProRefinal(refinal).stream()
            .map(proRefinalChiTietLoiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
