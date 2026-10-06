package vn.nvsoft.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.ButtonTracking;
import vn.nvsoft.domain.ChuyenTramSound;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.repository.ButtonTrackingRepository;
import vn.nvsoft.repository.SoDoChuyenRepository;
import vn.nvsoft.service.ButtonTrackingService;
import vn.nvsoft.service.dto.ButtonTrackingDTO;
import vn.nvsoft.service.mapper.ButtonTrackingMapper;

/**
 * Service Implementation for managing {@link ButtonTracking}.
 */
@Service
@Transactional
public class ChuyenTramSoundServiceImpl {

    private final Logger log = LoggerFactory.getLogger(ChuyenTramSoundServiceImpl.class);

    private final SoDoChuyenRepository m_soDoChuyenRepository;


    public ChuyenTramSoundServiceImpl(SoDoChuyenRepository soDoChuyenRepository) {
        m_soDoChuyenRepository = soDoChuyenRepository;
    }

    public Optional<ChuyenTramSound> findChuyenTramSoundByButton(NVButton nvButton) {
        return m_soDoChuyenRepository.findChuyenTramSoundByButton(nvButton.getId());
    }
}
