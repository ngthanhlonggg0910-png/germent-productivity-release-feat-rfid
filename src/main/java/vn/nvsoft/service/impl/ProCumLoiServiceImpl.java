package vn.nvsoft.service.impl;

import org.springframework.data.domain.Example;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.repository.ProLoiRepository;
import vn.nvsoft.service.ProCumLoiService;
import vn.nvsoft.domain.ProCumLoi;
import vn.nvsoft.repository.ProCumLoiRepository;
import vn.nvsoft.service.dto.ProCumLoiDTO;
import vn.nvsoft.service.mapper.ProCumLoiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ProCumLoi}.
 */
@Service
@Transactional
public class ProCumLoiServiceImpl implements ProCumLoiService {

    private final Logger log = LoggerFactory.getLogger(ProCumLoiServiceImpl.class);

    private final ProCumLoiRepository proCumLoiRepository;
    private final ProLoiRepository proLoiRepository;

    private final ProCumLoiMapper proCumLoiMapper;

    public ProCumLoiServiceImpl(ProCumLoiRepository proCumLoiRepository, ProLoiRepository proLoiRepository, ProCumLoiMapper proCumLoiMapper) {
        this.proCumLoiRepository = proCumLoiRepository;
        this.proLoiRepository = proLoiRepository;
        this.proCumLoiMapper = proCumLoiMapper;
    }

    /**
     * Save a proCumLoi.
     *
     * @param proCumLoiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProCumLoiDTO save(ProCumLoiDTO proCumLoiDTO) {
        log.debug("Request to save ProCumLoi : {}", proCumLoiDTO);
        ProCumLoi proCumLoi = proCumLoiMapper.toEntity(proCumLoiDTO);
        proCumLoi = proCumLoiRepository.save(proCumLoi);
        return proCumLoiMapper.toDto(proCumLoi);
    }

    /**
     * Get all the proCumLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProCumLoiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProCumLois");
        return proCumLoiRepository.findAll(pageable)
            .map(proCumLoiMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProCumLoiDTO> findAllAndCount() {
        log.debug("Request to get all ProCumLois");
        List<ProCumLoi> all = proCumLoiRepository.findAll();
        List<ProCumLoiDTO> dtos = new ArrayList<>();

        for (ProCumLoi cl : all) {
            ProLoi l = new ProLoi();
            l.setProCumLoi(cl);
            Example<ProLoi> example = Example.of(l);
            int count = proLoiRepository.findAll(example).size();

            ProCumLoiDTO proCumLoiDTO = proCumLoiMapper.toDto(cl);
            proCumLoiDTO.setCount(count);
            dtos.add(proCumLoiDTO);
        }
        sortComLoi(dtos);
        return dtos;
    }

    private void sortComLoi(List<ProCumLoiDTO> cumLoiDTOS) {
        cumLoiDTOS.sort((cl1, cl2) -> {
            if (cl1.getTen().equalsIgnoreCase("Cụm Lỗi Cấu Trúc")) {
                return -1;
            }
            return cl1.getId().compareTo(cl2.getId());
        });
    }

    /**
     * Get one proCumLoi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProCumLoiDTO> findOne(Long id) {
        log.debug("Request to get ProCumLoi : {}", id);
        return proCumLoiRepository.findById(id)
            .map(proCumLoiMapper::toDto);
    }

    /**
     * Delete the proCumLoi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProCumLoi : {}", id);
        proCumLoiRepository.deleteById(id);
    }


}
