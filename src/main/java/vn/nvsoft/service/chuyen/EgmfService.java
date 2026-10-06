package vn.nvsoft.service.chuyen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.chuyen.NangSuatChuyen;
import vn.nvsoft.domain.chuyen.NgayMayRa;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.domain.chuyen.ThongTinToTruong;
import vn.nvsoft.repository.chuyen.EgmfRepository;
import vn.nvsoft.security.SecurityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EgmfService {
    private final Logger log = LoggerFactory.getLogger(EgmfService.class);

    private EgmfRepository m_egmfRepository;

    public EgmfService(EgmfRepository egmfRepository) {
        m_egmfRepository = egmfRepository;
    }

    /**
     * Get user leader
     * @param nsclId
     * @return
     */
    public List<ThongTinToTruong> getToTruongByNSCLId(Long nsclId) {
        return m_egmfRepository.getToTruongByNSCLId(nsclId);
    }

    /**
     * Get summary NangSuatChuyen
     * @param chuyenId
     * @return
     */
    public NangSuatChuyen getNangSuatChuyen(Long chuyenId) {
        return m_egmfRepository.getNangSuatChuyen(chuyenId);
    }

    /**
     * Get ThongTinChuyen to get chuyenId then use in getNangSuatChuyen
     * @param tkId
     * @return
     */
    public ThongTinChuyen getThongTinChuyen(String tkId) {
        return m_egmfRepository.getThongTinChuyen(tkId);
    }

    public NangSuatChuyen getNangSuatChuyenCuaUserHienTai() {
        //current user
        Optional<String> login = SecurityUtils.getCurrentUserLogin();
        if (login.isPresent()) {
            log.debug("Get thong tin chuyen base user {}", login.get());
            ThongTinChuyen thongTinChuyen = m_egmfRepository.getThongTinChuyen(login.get());
            if (thongTinChuyen != null && thongTinChuyen.getCumId() != null) {
                log.debug("Get nang suat chuyen {}", thongTinChuyen.getCumId());
                return m_egmfRepository.getNangSuatChuyen(thongTinChuyen.getCumId());
            } else {
                log.warn("CumId is null for user {}", login.get());
            }
        } else {
            log.warn("Can not get current sign in user!!!");
        }
        return null;
    }

    public void updateResultKeHoachXuatHang(Boolean passFail, Long khxhId) {
        log.debug("Update Ke hoach xuat hang ID {} to {}", khxhId, passFail);
        m_egmfRepository.updateResultKeHoachXuatHang(passFail, khxhId);
    }

    public NgayMayRa updateChotNangSuatNgay(Long checkPointId) {
        return m_egmfRepository.updateChotMayRaNgay(checkPointId);
    }

    public NgayMayRa getChotNangSuatNgay(Long checkPointId) {
        return m_egmfRepository.getChotMayRaNgayLanCuoi(checkPointId);
    }
}
