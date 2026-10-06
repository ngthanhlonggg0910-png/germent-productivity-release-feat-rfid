package vn.nvsoft.web.rest.heso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.nvsoft.domain.heso.HeSo;
import vn.nvsoft.service.dto.heso.HeSoNhanVienDTO;
import vn.nvsoft.service.heso.HeSoService;

import java.util.Date;
import java.util.List;

/**
 * REST controller for managing {@link vn.nvsoft.domain.DeviceToken}.
 */
@RestController
@RequestMapping("/api/chuyen/he-so")
public class HeSoResource {

    private final Logger log = LoggerFactory.getLogger(HeSoResource.class);

    private static final String ENTITY_NAME = "chuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HeSoService m_heSoService;

    public HeSoResource(HeSoService heSoService) {
        m_heSoService = heSoService;
    }

    /**
     * {@code GET  /nhan-vien} : get DS He So Nhan Vien cua Chuyen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the List<HeSoNhanVienDTO> in body.
     */
    @GetMapping("/nhan-vien")
    public List<HeSoNhanVienDTO> getHeSoNhanViens() {
        log.debug("REST request to get He So Nhan Vien cua chuyen ");
        return m_heSoService.getDanhSachLaoCuaChuyen();
    }

    /**
     * {@code GET  /nhan-vien} : get DS He So Nhan Vien cua Chuyen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the List<HeSoNhanVienDTO> in body.
     */
    @GetMapping("/nhan-vien/by-date/{date}")
    public List<HeSoNhanVienDTO> getHeSoNhanVienByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        log.debug("REST request to get He So Nhan Vien cua chuyen by date {}", date.toString());
        return m_heSoService.getDanhSachLaoCuaChuyenByDate(date);
    }

    /**
     * {@code PUT  /nhan-vien} : reset he so nhan vien cua chuyen.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the void in body.
     */
    @PutMapping("/nhan-vien")
    public void resetHeSoCuaChuyen() {
        log.debug("REST request to reset he so nhan vien cua chuyen");
        m_heSoService.resetHeSoCuaChuyen();
    }

    /**
     * {@code POST  /nhan-vien} : Xac nhan da thay doi He So xong.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the void in body.
     */
    @PostMapping("/nhan-vien")
    public void xacNhanDaSuaXongHeSoChoChuyen(Long chuyenId,@RequestParam(value = "xac-nhan") Boolean xacNhan) {
        log.debug("REST request to Xac nhan da thay doi He So cua chuyen xac nhan {}", xacNhan);
        m_heSoService.xacNhanDaSuaXongHeSoChoChuyen(xacNhan);
    }

    /**
     * {@code PUT  /nhan-vien/update-he-so} : cap nhap he so cua nhan vien dua tren he so id.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the NangSuatChuyen in body.
     */
    @PutMapping("/nhan-vien/update-he-so")
    public void updateHeSoNhanVien(@RequestParam(value = "id") Long id, @RequestParam(value = "he-so") Double heSo) {
        log.debug("REST request to cap nhap he so cua nhan vien dua tren he so id {} he so {}", id, heSo);
        m_heSoService.updateHeSoNhanVien(id, heSo);
    }

    /**
     * {@code GET  /} : get DS He So.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the List<HeSoNhanVienDTO> in body.
     */
    @GetMapping()
    public List<HeSo> getHeSos() {
        log.debug("REST request to get He So");
        return m_heSoService.getHeSos();
    }
}
