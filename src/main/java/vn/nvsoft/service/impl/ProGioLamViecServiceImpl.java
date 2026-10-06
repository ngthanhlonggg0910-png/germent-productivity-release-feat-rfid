package vn.nvsoft.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.domain.ProGioLamViec;
import vn.nvsoft.repository.ProGioLamViecRepository;
import vn.nvsoft.service.dto.ProGioLamViecDTO;
import vn.nvsoft.service.mapper.ProGioLamViecMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProGioLamViec}.
 */
@Service
@Transactional
public class ProGioLamViecServiceImpl implements ProGioLamViecService {

    private final Logger log = LoggerFactory.getLogger(ProGioLamViecServiceImpl.class);

    private final ProGioLamViecRepository proGioLamViecRepository;

    private final ProGioLamViecMapper proGioLamViecMapper;

    public ProGioLamViecServiceImpl(ProGioLamViecRepository proGioLamViecRepository, ProGioLamViecMapper proGioLamViecMapper) {
        this.proGioLamViecRepository = proGioLamViecRepository;
        this.proGioLamViecMapper = proGioLamViecMapper;
    }

    /**
     * Save a proGioLamViec.
     *
     * @param proGioLamViecDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProGioLamViecDTO save(ProGioLamViecDTO proGioLamViecDTO) {
        log.debug("Request to save ProGioLamViec : {}", proGioLamViecDTO);
        ProGioLamViec proGioLamViec = proGioLamViecMapper.toEntity(proGioLamViecDTO);
        proGioLamViec = proGioLamViecRepository.save(proGioLamViec);
        return proGioLamViecMapper.toDto(proGioLamViec);
    }

    /**
     * Get all the proGioLamViecs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProGioLamViecDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProGioLamViecs");
        return proGioLamViecRepository.findAll(pageable)
            .map(proGioLamViecMapper::toDto);
    }


    /**
     * Get one proGioLamViec by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProGioLamViecDTO> findOne(Long id) {
        log.debug("Request to get ProGioLamViec : {}", id);
        return proGioLamViecRepository.findById(id)
            .map(proGioLamViecMapper::toDto);
    }

    /**
     * Delete the proGioLamViec by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProGioLamViec : {}", id);
        proGioLamViecRepository.deleteById(id);
    }

    @Override
    public ProGioLamViecDTO getGioLamViecHienTai() {
        List<ProGioLamViec> gioLamViecs = proGioLamViecRepository.getGioLamViecHienTai();
        if (gioLamViecs != null && !gioLamViecs.isEmpty()) {
            return proGioLamViecMapper.toDto(gioLamViecs.get(0));
        }
        return null;
    }

    @Override
    public ProGioLamViecDTO getGioLamViecHienTai(String chuyen) {
        List<ProGioLamViec> gioLamViecs = proGioLamViecRepository.getGioLamViecHienTai();
        if (gioLamViecs != null && !gioLamViecs.isEmpty()) {
            LocalDate today = LocalDate.now();
            for (ProGioLamViec gioLamViec : gioLamViecs) {
                if (gioLamViec.getChuyen() != null) {
                    //check thu 7
                    if (DayOfWeek.SATURDAY.equals(today.getDayOfWeek())) {
                        if (gioLamViec.getThuBay() != null && gioLamViec.getThuBay()) {
                            String[] chuyens = gioLamViec.getChuyen().trim().split(",");
                            for (String s : chuyens) {
                                if (s.trim().equalsIgnoreCase(chuyen)) {
                                    return proGioLamViecMapper.toDto(gioLamViec);
                                }
                            }
                        }
                    } else {
                        String[] chuyens = gioLamViec.getChuyen().trim().split(",");
                        for (String s : chuyens) {
                            if (s.trim().equalsIgnoreCase(chuyen)) {
                                return proGioLamViecMapper.toDto(gioLamViec);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
