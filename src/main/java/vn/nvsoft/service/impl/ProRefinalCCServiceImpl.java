package vn.nvsoft.service.impl;

import com.google.common.collect.Lists;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.repository.CommonFactoryRepository;
import vn.nvsoft.repository.ProRefinalRepository;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.*;
import vn.nvsoft.domain.ProRefinalCC;
import vn.nvsoft.repository.ProRefinalCCRepository;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.*;
import vn.nvsoft.service.mapper.KeHoachXuatHangMapper;
import vn.nvsoft.service.mapper.ProRefinalCCMapper;
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
 * Service Implementation for managing {@link ProRefinalCC}.
 */
@Service
@Transactional
public class ProRefinalCCServiceImpl implements ProRefinalCCService {

    private final Logger log = LoggerFactory.getLogger(ProRefinalCCServiceImpl.class);

    private final ProRefinalCCRepository proRefinalCCRepository;

    private final ProRefinalCCMapper proRefinalCCMapper;
    private final DoAmService m_doAmService;
    private final ProRefinalRepository proRefinalRepository;
    private final ProAqlService proAqlService;
    private final DoKimMdService doKimMdService;
    private EgmfService m_egmfService;
    private final CommonFactoryRepository commonFactoryRepository;
    private final KeHoachXuatHangMapper keHoachXuatHangMapper;
    private final ProRefinalCCChiTietLoiService proRefinalCCChiTietLoiService;
    private final LibSoLuongPoKiemService libSoLuongPoKiemService;

    public ProRefinalCCServiceImpl(ProRefinalCCRepository proRefinalCCRepository, ProRefinalCCMapper proRefinalCCMapper,
                                   DoAmService doAmService, ProRefinalRepository proRefinalRepository, ProAqlService proAqlService,
                                   DoKimMdService doKimMdService, EgmfService egmfService, CommonFactoryRepository commonFactoryRepository, KeHoachXuatHangMapper keHoachXuatHangMapper,
                                   ProRefinalCCChiTietLoiService proRefinalCCChiTietLoiService, LibSoLuongPoKiemService libSoLuongPoKiemService) {
        this.proRefinalCCRepository = proRefinalCCRepository;
        this.proRefinalCCMapper = proRefinalCCMapper;
        m_doAmService = doAmService;
        this.proRefinalRepository = proRefinalRepository;
        this.proAqlService = proAqlService;
        this.doKimMdService = doKimMdService;
        m_egmfService = egmfService;
        this.commonFactoryRepository = commonFactoryRepository;
        this.keHoachXuatHangMapper = keHoachXuatHangMapper;
        this.proRefinalCCChiTietLoiService = proRefinalCCChiTietLoiService;
        this.libSoLuongPoKiemService =libSoLuongPoKiemService;
    }

    /**
     * Save a proRefinalCC.
     *
     * @param proRefinalCCDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProRefinalCCDTO save(ProRefinalCCDTO proRefinalCCDTO) {
        log.debug("Request to save ProRefinalCC : {}", proRefinalCCDTO);
        proRefinalCCDTO.setNguoiKiem(getUser());
        ProRefinalCC proRefinalCC = proRefinalCCMapper.toEntity(proRefinalCCDTO);
        proRefinalCC = proRefinalCCRepository.save(proRefinalCC);
        return proRefinalCCMapper.toDto(proRefinalCC);
    }

    /**
     * Get all the proRefinalCCS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProRefinalCCDTO> findAll() {
        log.debug("Request to get all ProRefinalCCS");
        return proRefinalCCRepository.findAll().stream()
            .map(proRefinalCCMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proRefinalCC by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProRefinalCCDTO> findOne(Long id) {
        log.debug("Request to get ProRefinalCC : {}", id);
        return proRefinalCCRepository.findById(id)
            .map(proRefinalCCMapper::toDto);
    }

    /**
     * Delete the proRefinalCC by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProRefinalCC : {}", id);
        proRefinalCCRepository.deleteById(id);
    }

    @Override
    public RefinalCCCreateInfoDTO getInfoToCreateRefinalCC(Long khxhId) {
        RefinalCCCreateInfoDTO result = new RefinalCCCreateInfoDTO();
        result.setDoAms(m_doAmService.findAll());
        result.setNgayGioKiem(proRefinalRepository.getCurrentDate());
        List<ProAqlDTO> aqls = proAqlService.findAll();
        result.setProAqls(aqls);
        Set<String> aqlValues = aqls.stream().map(aql -> aql.getAql()).collect(Collectors.toSet());
        List<String> tmps = new ArrayList<>(aqlValues);
        tmps.sort(Comparator.naturalOrder());
        result.setAqls(tmps);
        List<DoKimMdDTO> dokims = this.doKimMdService.findAll();
        Set<String> doKimValues = dokims.stream().map(dokim -> dokim.getDisplay()).collect(Collectors.toSet());
        tmps = new ArrayList<>(doKimValues);
        tmps.sort(Comparator.naturalOrder());
        result.setDoKimMds(tmps);
        Set<Integer> soLuongPoKiems = this.libSoLuongPoKiemService.findAll().stream().map(lib -> lib.getValue()).collect(Collectors.toSet());
        List<Integer> tmpInts = new ArrayList<>(soLuongPoKiems);
        tmpInts.sort(Comparator.naturalOrder());
        result.setSoLuongPoKiems(tmpInts);
        return result;
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
            List<ProRefinalCC> refinals = proRefinalCCRepository.findAllByKhxhId(tmp.getId());
            if (refinals == null || refinals.isEmpty()) {
                tmp.setStatus(KeHoachXuatHangDTO.Status.PENDING);
            } else {
                tmp.setStatus(KeHoachXuatHangDTO.Status.FAIL);
                for (ProRefinalCC refinal : refinals) {
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

    @Override
    public List<RefinalCCAndChiTietDTO> getAllProRefinalCCAndChiTiet(Long khxhId) {
        List<RefinalCCAndChiTietDTO> results = Lists.newArrayList();
        List<ProRefinalCC> refinals = proRefinalCCRepository.findAllByKhxhId(khxhId);
        for (ProRefinalCC refinal : refinals) {
            List<ProRefinalCCChiTietLoiDTO> chiTietLois = proRefinalCCChiTietLoiService.findAllByProRefinalCC(refinal);
            RefinalCCAndChiTietDTO dto = new RefinalCCAndChiTietDTO();
            dto.setProRefinalCCDTO(proRefinalCCMapper.toDto(refinal));
            dto.setProRefinalCCChiTietLoiDTO(chiTietLois);
            results.add(dto);
        }
        return results;
    }

    @Override
    public void createRefinalCC(RefinalCCAndChiTietDTO refinalToCreateDTO) {
        refinalToCreateDTO.getProRefinalCCDTO().setNgayGioKiem(proRefinalRepository.getCurrentDate().toInstant());
        String user = getUser();
        refinalToCreateDTO.getProRefinalCCDTO().setNguoiKiem(user);
        ProRefinalCCDTO refianl = save(refinalToCreateDTO.getProRefinalCCDTO());
        for (ProRefinalCCChiTietLoiDTO proRefinalChiTietLoiDTO : refinalToCreateDTO.getProRefinalCCChiTietLoiDTO()) {
            proRefinalChiTietLoiDTO.setProRefinalCCId(refianl.getId());
            proRefinalChiTietLoiDTO.setThoiGian(Instant.now());
            if (proRefinalChiTietLoiDTO.getId() == null) {
                proRefinalChiTietLoiDTO.setNguoiKiem(user);
            }
            proRefinalCCChiTietLoiService.save(proRefinalChiTietLoiDTO);
        }
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

    private String getUser() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            return login.get();
        }
        return "";
    }
}
