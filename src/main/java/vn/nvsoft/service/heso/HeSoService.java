package vn.nvsoft.service.heso;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.domain.heso.HeSo;
import vn.nvsoft.domain.heso.HeSoNhanVien;
import vn.nvsoft.filestorage.StorageProperties;
import vn.nvsoft.repository.CommonFactoryRepository;
import vn.nvsoft.repository.ProNSCLRepository;
import vn.nvsoft.repository.heso.HeSoRepository;
import vn.nvsoft.security.SecurityUtils;
import vn.nvsoft.service.CommonFactoryService;
import vn.nvsoft.service.UserService;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.ManufacturePlanViewModelDTO;
import vn.nvsoft.service.dto.UserDTO;
import vn.nvsoft.service.dto.heso.HeSoNhanVienDTO;
import vn.nvsoft.service.viewmodel.CheckpointLocationViewModel;
import vn.nvsoft.service.viewmodel.KhsxMau;
import vn.nvsoft.service.viewmodel.ManufacturePlanViewModel;
import vn.nvsoft.service.viewmodel.UserCheckPointViewModel;
import vn.nvsoft.service.viewmodel.UserWorkingLocationViewModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeSoService {

    private final Logger log = LoggerFactory.getLogger(HeSoService.class);

    private UserService m_userService;
    private HeSoRepository m_heHeSoRepository;
    private final StorageProperties m_storageProperties;
    private EgmfService m_egmfService;

    public HeSoService(
        UserService userService,
        HeSoRepository heSoRepository,
        StorageProperties storageProperties,
        EgmfService egmfService
    ) {
        this.m_userService = userService;
        this.m_heHeSoRepository = heSoRepository;
        m_storageProperties = storageProperties;
        m_egmfService = egmfService;
    }

    private Long getChuyenId() {
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            log.debug("Get thong tin chuyen base user {}", login.get());
            ThongTinChuyen thongTinChuyen = m_egmfService.getThongTinChuyen(login.get());
            if (thongTinChuyen != null && thongTinChuyen.getCumId() != null) {
                log.debug("He so cua chuyen {}", thongTinChuyen.getCumId());
                return thongTinChuyen.getCumId();
            } else {
                log.warn("CumId is null for user {}", login.get());
            }
        } else {
            log.warn("Can not get current sign in user!!!");
        }
        return null;
    }
    public List<HeSoNhanVienDTO> getDanhSachLaoCuaChuyen() {
        List<HeSoNhanVien> heSoNhanViens = m_heHeSoRepository.getDanhSachLaoCuaChuyen(getChuyenId());
        List<HeSoNhanVienDTO> resutls = heSoNhanViens.stream().map(heSoNhanVien -> new HeSoNhanVienDTO(heSoNhanVien, m_storageProperties)).collect(Collectors.toList());
        resutls.sort((hs1, hs2) -> {
            String[] names1 = hs1.getHoten().trim().split(" ");
            String[] names2 = hs2.getHoten().trim().split(" ");
            return names1[names1.length - 1].toLowerCase().compareTo(names2[names2.length-1].toLowerCase());
        });
        return resutls;
    }

    public List<HeSoNhanVienDTO> getDanhSachLaoCuaChuyenByDate(Date date) {
        List<HeSoNhanVien> heSoNhanViens = m_heHeSoRepository.getDanhSachLaoCuaChuyenByDate(getChuyenId(), date);
        List<HeSoNhanVienDTO> resutls = heSoNhanViens.stream().map(heSoNhanVien -> new HeSoNhanVienDTO(heSoNhanVien, m_storageProperties)).collect(Collectors.toList());
        resutls.sort((hs1, hs2) -> {
            String[] names1 = hs1.getHoten().trim().split(" ");
            String[] names2 = hs2.getHoten().trim().split(" ");
            return names1[names1.length - 1].toLowerCase().compareTo(names2[names2.length-1].toLowerCase());
        });
        return resutls;
    }

    public void resetHeSoCuaChuyen() {
        m_heHeSoRepository.resetHeSoCuaChuyen(getChuyenId());
    }

    public void updateHeSoNhanVien(Long id, Double heSo) {
        m_heHeSoRepository.updateHeSoNhanVien(id, heSo);
    }

    public List<HeSo> getHeSos() {
        return m_heHeSoRepository.getHeSos();
    }

    public void xacNhanDaSuaXongHeSoChoChuyen(Boolean xacNhan) {
        m_heHeSoRepository.xacNhanDaSuaXongHeSoChoChuyen(getChuyenId(), xacNhan);
    }
}
