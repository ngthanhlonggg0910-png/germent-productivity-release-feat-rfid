package vn.nvsoft.web.rest.chuyen;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nvsoft.domain.chuyen.NangSuatChuyen;
import vn.nvsoft.domain.chuyen.NgayMayRa;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.service.DeviceTokenService;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.DeviceTokenDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.DeviceToken}.
 */
@RestController
@RequestMapping("/api/chuyen")
public class EgmfResource {

    private final Logger log = LoggerFactory.getLogger(EgmfResource.class);

    private static final String ENTITY_NAME = "chuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EgmfService m_egmfService;

    public EgmfResource(EgmfService egmfService) {
        m_egmfService = egmfService;
    }

    /**
     * {@code GET  /info} : get ThongTinChuyen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the ThongTinChuyen in body.
     */
    @GetMapping("/info")
    public ThongTinChuyen getThongTinChuyen(@RequestParam(value = "tk") String tkId) {
        log.debug("REST request to get thong tin chuyen {}", tkId);
        return m_egmfService.getThongTinChuyen(tkId);
    }

    /**
     * {@code GET  /nang-suat} : get Nang suat cua chuyen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the NangSuatChuyen in body.
     */
    @GetMapping("/nang-suat")
    public NangSuatChuyen getNangSuatChuyen(@RequestParam(value = "chuyen-id") Long chuyenId) {
        log.debug("REST request to get NangSuatChuyen {}", chuyenId);
        return m_egmfService.getNangSuatChuyen(chuyenId);
    }

    /**
     * {@code GET  /nang-suat-chuyen} : get Nang suat cua chuyen cua user dang dang nhap.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the NangSuatChuyen in body.
     */
    @GetMapping("/nang-suat-chuyen")
    public NangSuatChuyen getNangSuatChuyenCuaUserHienTai() {
        log.debug("REST request to get NangSuatChuyen cua user hien tai dang dang nhap");
        return m_egmfService.getNangSuatChuyenCuaUserHienTai();
    }

    /**
     * {@code POST  /chot-nang-suat-ngay} : chot nang suat ngay theo check point id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} va ngay gio chot nang suat ngay.
     */
    @PostMapping("/chot-nang-suat-ngay")
    public NgayMayRa updateChotNangSuatNgay(@RequestParam(value = "check-point-id") Long checkPointId) {
        log.debug("REST request to update Chot Nang Suat Ngay cua check point id {}", checkPointId);
        return m_egmfService.updateChotNangSuatNgay(checkPointId);
    }

    /**
     * {@code GET  /chot-nang-suat-ngay} : get ngay gio cuoi cung chot nang suat ngay theo check point id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} va ngay gio chot nang suat ngay cuoi cung.
     */
    @GetMapping("/chot-nang-suat-ngay")
    public NgayMayRa getChotNangSuatNgay(@RequestParam(value = "check-point-id") Long checkPointId) {
        log.debug("REST request to get ngay gio Chot Nang Suat Ngay cuoi cung cua check point id {}", checkPointId);
        return m_egmfService.getChotNangSuatNgay(checkPointId);
    }

}
