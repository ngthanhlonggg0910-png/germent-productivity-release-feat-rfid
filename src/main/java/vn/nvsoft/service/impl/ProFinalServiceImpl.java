package vn.nvsoft.service.impl;

import com.google.common.collect.Lists;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.repository.CommonFactoryRepository;
import vn.nvsoft.repository.ProRefinalRepository;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.ProFinalChiTietLoiService;
import vn.nvsoft.service.ProFinalService;
import vn.nvsoft.domain.ProFinal;
import vn.nvsoft.repository.ProFinalRepository;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.FinalAndChiTietDTO;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;
import vn.nvsoft.service.dto.ProFinalDTO;
import vn.nvsoft.service.mapper.KeHoachXuatHangMapper;
import vn.nvsoft.service.mapper.ProFinalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.service.viewmodel.KeHoachXuatHang;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProFinal}.
 */
@Service
@Transactional
public class ProFinalServiceImpl implements ProFinalService {

    private final Logger log = LoggerFactory.getLogger(ProFinalServiceImpl.class);

    private final ProFinalRepository proFinalRepository;

    private final ProFinalMapper proFinalMapper;
    private final ProFinalChiTietLoiService proFinalChiTietLoiService;
    private final ProRefinalRepository proRefinalRepository;
    private EgmfService m_egmfService;
    private final CommonFactoryRepository commonFactoryRepository;
    private final KeHoachXuatHangMapper keHoachXuatHangMapper;

    public ProFinalServiceImpl(ProFinalRepository proFinalRepository, ProFinalMapper proFinalMapper,
                               ProFinalChiTietLoiService proFinalChiTietLoiService, ProRefinalRepository proRefinalRepository,
                               EgmfService egmfService, CommonFactoryRepository commonFactoryRepository, KeHoachXuatHangMapper keHoachXuatHangMapper) {
        this.proFinalRepository = proFinalRepository;
        this.proFinalMapper = proFinalMapper;
        this.proFinalChiTietLoiService = proFinalChiTietLoiService;
        this.proRefinalRepository = proRefinalRepository;
        m_egmfService = egmfService;
        this.commonFactoryRepository = commonFactoryRepository;
        this.keHoachXuatHangMapper = keHoachXuatHangMapper;
    }

    /**
     * Save a proFinal.
     *
     * @param proFinalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProFinalDTO save(ProFinalDTO proFinalDTO) {
        log.debug("Request to save ProFinal : {}", proFinalDTO);
        proFinalDTO.setNguoiKiem(getUser());
        ProFinal proFinal = proFinalMapper.toEntity(proFinalDTO);
        proFinal = proFinalRepository.save(proFinal);
        if (proFinal.getKhxhId() != null && proFinal.getTongSoLoi() != null && proFinal.getMaxLoiChoPhep() != null) {
            if (proFinal.getTongSoLoi() <= proFinal.getMaxLoiChoPhep()) {
                m_egmfService.updateResultKeHoachXuatHang(Boolean.TRUE, proFinal.getKhxhId());
            } else {
                m_egmfService.updateResultKeHoachXuatHang(Boolean.FALSE, proFinal.getKhxhId());
            }
        }
        return proFinalMapper.toDto(proFinal);
    }

    /**
     * Get all the proFinals.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProFinalDTO> findAll() {
        log.debug("Request to get all ProFinals");
        return proFinalRepository.findAll().stream()
            .map(proFinalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proFinal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProFinalDTO> findOne(Long id) {
        log.debug("Request to get ProFinal : {}", id);
        return proFinalRepository.findById(id)
            .map(proFinalMapper::toDto);
    }

    /**
     * Delete the proFinal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProFinal : {}", id);
        proFinalRepository.deleteById(id);
    }


    @Override
    public List<FinalAndChiTietDTO> getAllProFinalAndChiTiet(Long khxhId) {
        List<FinalAndChiTietDTO> results = Lists.newArrayList();
        List<ProFinal> refinals = proFinalRepository.findAllByKhxhId(khxhId);
        for (ProFinal refinal : refinals) {
            List<ProFinalChiTietLoiDTO> chiTietLois = proFinalChiTietLoiService.findAllByProFinal(refinal);
            FinalAndChiTietDTO dto = new FinalAndChiTietDTO();
            dto.setProFinalDTO(proFinalMapper.toDto(refinal));
            dto.setProFinalChiTietLoiDTOS(chiTietLois);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void createFinal(FinalAndChiTietDTO refinalToCreateDTO) {
        refinalToCreateDTO.getProFinalDTO().setNgayGioKiem(proRefinalRepository.getCurrentDate().toInstant());
        String user = getUser();
        refinalToCreateDTO.getProFinalDTO().setNguoiKiem(user);
        ProFinalDTO refianl = save(refinalToCreateDTO.getProFinalDTO());
        for (ProFinalChiTietLoiDTO proRefinalChiTietLoiDTO : refinalToCreateDTO.getProFinalChiTietLoiDTOS()) {
            proRefinalChiTietLoiDTO.setProFinalId(refianl.getId());
            proRefinalChiTietLoiDTO.setThoiGian(Instant.now());
            if (proRefinalChiTietLoiDTO.getId() == null) {
                proRefinalChiTietLoiDTO.setNguoiKiem(user);
            }
            proFinalChiTietLoiService.save(proRefinalChiTietLoiDTO);
        }
    }

    @Override
    public List<KeHoachXuatHangDTO> getKeHoachXuatHang(Date date) {
        String tenChuyen = getChuyenId();
        List<KeHoachXuatHang> khxhs;
        if (tenChuyen != null) {
            log.info("Get Ke hoach xuat hang cua chuyen: " + tenChuyen);
            khxhs = commonFactoryRepository.getKeHoachXuatHangToDay(tenChuyen, date);
        } else {
            log.warn("Khong tim thay ten chuyen theo user login");
            khxhs = commonFactoryRepository.getKeHoachXuatHangToDay("", date);
        }
        List<KeHoachXuatHangDTO> results = new ArrayList<>();
        for (KeHoachXuatHang khxh : khxhs) {
            KeHoachXuatHangDTO tmp = this.keHoachXuatHangMapper.toDto(khxh);
            List<ProFinal> refinals = proFinalRepository.findAllByKhxhId(tmp.getId());
            if (refinals == null || refinals.isEmpty()) {
                tmp.setStatus(KeHoachXuatHangDTO.Status.PENDING);
            } else {
                tmp.setStatus(KeHoachXuatHangDTO.Status.FAIL);
                for (ProFinal refinal : refinals) {
                    if (refinal.getTongSoLoi() != null && refinal.getMaxLoiChoPhep() != null && refinal.getTongSoLoi() <= refinal.getMaxLoiChoPhep()) {
                        tmp.setStatus(KeHoachXuatHangDTO.Status.PASS);
                        break;
                    }
                }
            }
            results.add(tmp);
        }
        return results;
    }

    private String getUser() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            return login.get();
        } else {
            log.warn("Can not get current sign in user!!!");
        }
        return "";
    }

    private String getChuyenId() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            log.debug("Get thong tin chuyen base user {}", login.get());
            ThongTinChuyen thongTinChuyen = m_egmfService.getThongTinChuyen(login.get());
            if (thongTinChuyen != null && thongTinChuyen.getCumId() != null) {
                log.debug("He so cua chuyen {}", thongTinChuyen.getCumId());
                return thongTinChuyen.getTenCum();
            } else {
                log.warn("CumId is null for user {}", login.get());
            }
        } else {
            log.warn("Can not get current sign in user!!!");
        }
        return null;
    }
}
