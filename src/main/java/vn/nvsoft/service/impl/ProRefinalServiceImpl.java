package vn.nvsoft.service.impl;

import com.google.common.collect.Lists;

import vn.nvsoft.domain.PrefinalTheoGio;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.service.*;
import vn.nvsoft.domain.ProRefinal;
import vn.nvsoft.repository.ProRefinalRepository;
import vn.nvsoft.service.dto.*;
import vn.nvsoft.service.mapper.ProRefinalMapper;
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
 * Service Implementation for managing {@link ProRefinal}.
 */
@Service
@Transactional
public class ProRefinalServiceImpl implements ProRefinalService {

    private final Logger log = LoggerFactory.getLogger(ProRefinalServiceImpl.class);

    private final ProRefinalRepository proRefinalRepository;

    private final ProRefinalMapper proRefinalMapper;
    private final CommonFactoryService m_commonFactoryService;
    private final ProNSCLService m_proNSCLService;
    private final SoLuongHangKiemService m_soLuongHangKiemService;
    private final DoAmService m_doAmService;
    private final ProRefinalChiTietLoiService m_proRefinalChiTietLoiService;

    public ProRefinalServiceImpl(ProRefinalRepository proRefinalRepository, ProRefinalMapper proRefinalMapper,
                                 CommonFactoryService commonFactoryService, ProNSCLService proNSCLService,
                                 SoLuongHangKiemService soLuongHangKiemService, DoAmService doAmService,
                                 ProRefinalChiTietLoiService proRefinalChiTietLoiService) {
        this.proRefinalRepository = proRefinalRepository;
        this.proRefinalMapper = proRefinalMapper;
        m_commonFactoryService = commonFactoryService;
        m_proNSCLService = proNSCLService;
        m_soLuongHangKiemService = soLuongHangKiemService;
        m_doAmService = doAmService;
        m_proRefinalChiTietLoiService = proRefinalChiTietLoiService;
    }

    /**
     * Save a proRefinal.
     *
     * @param proRefinalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProRefinalDTO save(ProRefinalDTO proRefinalDTO) {
        log.debug("Request to save ProRefinal : {}", proRefinalDTO);
        ProRefinal proRefinal = proRefinalMapper.toEntity(proRefinalDTO);
        proRefinal = proRefinalRepository.save(proRefinal);
        return proRefinalMapper.toDto(proRefinal);
    }

    /**
     * Get all the proRefinals.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProRefinalDTO> findAll() {
        log.debug("Request to get all ProRefinals");
        return proRefinalRepository.findAll().stream()
            .map(proRefinalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proRefinal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProRefinalDTO> findOne(Long id) {
        log.debug("Request to get ProRefinal : {}", id);
        return proRefinalRepository.findById(id)
            .map(proRefinalMapper::toDto);
    }

    /**
     * Delete the proRefinal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProRefinal : {}", id);
        proRefinalRepository.deleteById(id);
    }


    /**
     * Tu check point id cua Refinal -> lay ra KHSX ID
     * Tu KHSX ID lay ProNSCL de sum Hang Dat
     * @param checkPointRefinalId
     * @param proKHSXId
     */
    @Override
    public RefinalCreateInfoDTO getInfoToCreateRefinal(Long checkPointRefinalId, Long proKHSXId) {
        log.debug("Get info to create refinal for check point refinal {} and pro KHSX {}", checkPointRefinalId, proKHSXId);
        List<ProNSCL> proNSCLs = m_proNSCLService.findByProKeHoachSanXuat(proKHSXId);
        if (!proNSCLs.isEmpty()) {
            int hangDatChuaKiemRefinal = tongHangDatChuaKiemRefinal(proNSCLs.get(0), checkPointRefinalId, proKHSXId);
            List<SoLuongHangKiemDTO> soLuongHangKiems = m_soLuongHangKiemService.findAll();
            List<DoAmDTO> doAmDTOS = m_doAmService.findAll();
            RefinalCreateInfoDTO result = new RefinalCreateInfoDTO();
            result.setGioKiem(proRefinalRepository.getCurrentDate());
            result.setProNSCLId(proNSCLs.get(0).getId());
            result.setCheckPointID(checkPointRefinalId);
            result.setProKHSXId(proKHSXId);
            result.setDoAms(doAmDTOS);
            result.setHangDatHienTai(hangDatChuaKiemRefinal);
            result.setSoLuongHangKiems(soLuongHangKiems);
            return result;
        } else {
            log.warn("Pro ke Hoach San xuat {} not found", proKHSXId);
        }
        return null;
    }

    @Override
    public void createRefinal(RefinalAndChiTietDTO refinalToCreateDTO) {
        refinalToCreateDTO.getProRefinalDTO().setNgayGioKiem(proRefinalRepository.getCurrentDate());
        ProRefinalDTO refianl = save(refinalToCreateDTO.getProRefinalDTO());
        for (ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO : refinalToCreateDTO.getProRefinalChiTietLoiDTO()) {
            proRefinalChiTietLoiDTO.setProRefinalId(refianl.getId());
            proRefinalChiTietLoiDTO.setThoiGian(Instant.now());
            m_proRefinalChiTietLoiService.save(proRefinalChiTietLoiDTO);
        }
    }

    @Override
    public List<RefinalAndChiTietDTO> getRefinalAndChiTiets(Long proKHSXId) {
        List<RefinalAndChiTietDTO> results = Lists.newArrayList();
        List<ProRefinal> refinals = proRefinalRepository.findAllByProKHSXId(proKHSXId);
        for (ProRefinal refinal : refinals) {
            List<ProRefinalChiTietLoiDTO> chiTietLois = m_proRefinalChiTietLoiService.findAllByProRefinal(refinal);
            RefinalAndChiTietDTO dto = new RefinalAndChiTietDTO();
            dto.setProRefinalDTO(proRefinalMapper.toDto(refinal));
            dto.setProRefinalChiTietLoiDTO(chiTietLois);
            results.add(dto);
        }
        return results;
    }

    private int tongHangDatChuaKiemRefinal(ProNSCL proNSCL, Long checkPointRefinalId, Long proKHSXId) {
        List<ProRefinal> proRefinals = proRefinalRepository.findAllByProNSCLId(proNSCL.getId());
        int tongHangdDatDaKiemRefinal = tongHangDatDaKiemRefinal(proRefinals);
        int tongHangDatMayRaHienTai = tongHangDatMayRaDenHienTai(proNSCL);
        return tongHangDatMayRaHienTai - tongHangdDatDaKiemRefinal;
    }

    private int tongHangDatMayRaDenHienTai(ProNSCL proNSCL) {
        int sum = 0;
        sum += getValue(proNSCL.getQty1h());
        sum += getValue(proNSCL.getQty2h());
        sum += getValue(proNSCL.getQty3h());
        sum += getValue(proNSCL.getQty4h());
        sum += getValue(proNSCL.getQty5h());
        sum += getValue(proNSCL.getQty6h());
        sum += getValue(proNSCL.getQty7h());
        sum += getValue(proNSCL.getQty8h());
        sum += getValue(proNSCL.getQty9h());
        sum += getValue(proNSCL.getQty10h());
        sum += getValue(proNSCL.getQty11h());
        sum += getValue(proNSCL.getQty12h());
        sum += getValue(proNSCL.getQty13h());
        sum += getValue(proNSCL.getQty14h());
        sum += getValue(proNSCL.getFix1h());
        sum += getValue(proNSCL.getFix2h());
        sum += getValue(proNSCL.getFix3h());
        sum += getValue(proNSCL.getFix4h());
        sum += getValue(proNSCL.getFix5h());
        sum += getValue(proNSCL.getFix6h());
        sum += getValue(proNSCL.getFix7h());
        sum += getValue(proNSCL.getFix8h());
        sum += getValue(proNSCL.getFix9h());
        sum += getValue(proNSCL.getFix10h());
        sum += getValue(proNSCL.getFix11h());
        sum += getValue(proNSCL.getFix12h());
        sum += getValue(proNSCL.getFix13h());
        sum += getValue(proNSCL.getFix14h());

        return sum;
    }

    private int getValue(Integer value) {
        return value != null ? value : 0;
    }

    private int tongHangDatDaKiemRefinal(List<ProRefinal> proRefinals) {
        int sum = 0;
        for (ProRefinal proRefinal : proRefinals) {
            if (proRefinal.getHangDatHienTai() != null) {
                sum += proRefinal.getHangDatHienTai();
            }
        }
        return sum;
    }

    /*
    @Override
    public List<ProRefinalTheoGioDTO> getAllProRefinalTheoGios(Long chuyen) {
        log.debug("Get AllProRefinalTheoGios {}", chuyen);
        return proRefinalRepository.getAllProRefinalTheoGios(chuyen);
    }
    */
    @Override
    public List<PrefinalTheoGio> getAllProRefinalTheoGios(Long chuyen) {
        log.debug("Get AllProRefinalTheoGios {}", chuyen);
        return proRefinalRepository.getAllProRefinalTheoGios(chuyen);
    }
}
