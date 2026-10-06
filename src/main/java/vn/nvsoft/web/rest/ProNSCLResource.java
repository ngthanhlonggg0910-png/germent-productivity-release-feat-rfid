package vn.nvsoft.web.rest;

import com.google.common.collect.Lists;
import java.time.Instant;
import java.util.ArrayList;

import vn.nvsoft.domain.NangSuatLaoDongNgay;
import vn.nvsoft.domain.NsclTheoGio;
import vn.nvsoft.domain.NsclTheoGioChuyenThongTinChung;
import vn.nvsoft.domain.nscl.MaHangChinhPhu;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.service.dto.NSCLTheoGioDTO;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiTheoGioHienTai;
import vn.nvsoft.service.dto.TopLoi;
import vn.nvsoft.service.dto.nscl.MaHangChinhPhuDTO;
import vn.nvsoft.service.dto.MaCayByPODTO;
import vn.nvsoft.service.dto.ApplyPoFixRequestDTO;
import vn.nvsoft.service.dto.PoFixPastDTO;
import vn.nvsoft.service.dto.TongMayRaDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProNSCLDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import vn.nvsoft.web.rest.vm.ChiTietLoiVM;
import vn.nvsoft.web.rest.vm.DangLoiVM;
import vn.nvsoft.web.rest.vm.NSCLTrenTruyenRealTimeVM;
import vn.nvsoft.web.rest.vm.NSCLTrenTruyenTheoDonHangVM;
import vn.nvsoft.web.rest.vm.NSCLTrenTruyenTheoGioVM;
import vn.nvsoft.web.rest.vm.NSCLTrenTruyenTopErrorVM;
import vn.nvsoft.web.rest.vm.NSTruyenTheoGioLamViecVM;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProNSCL}.
 */
@RestController
@RequestMapping("/api")
public class ProNSCLResource {

    private final Logger log = LoggerFactory.getLogger(ProNSCLResource.class);

    private static final String ENTITY_NAME = "proNSCL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProNSCLService proNSCLService;

    public ProNSCLResource(ProNSCLService proNSCLService) {
        this.proNSCLService = proNSCLService;
    }

    /**
     * {@code POST  /pro-nscls} : Create a new proNSCL.
     *
     * @param proNSCLDTO the proNSCLDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNSCLDTO, or with status {@code 400 (Bad Request)} if the proNSCL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-nscls")
    public ResponseEntity<ProNSCLDTO> createProNSCL(@Valid @RequestBody ProNSCLDTO proNSCLDTO) throws URISyntaxException {
        log.debug("REST request to save ProNSCL : {}", proNSCLDTO);
        if (proNSCLDTO.getId() != null) {
            throw new BadRequestAlertException("A new proNSCL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProNSCLDTO result = proNSCLService.save(proNSCLDTO);
        return ResponseEntity.created(new URI("/api/pro-nscls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-nscls} : Updates an existing proNSCL.
     *
     * @param proNSCLDTO the proNSCLDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNSCLDTO,
     * or with status {@code 400 (Bad Request)} if the proNSCLDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNSCLDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls")
    public ResponseEntity<ProNSCLDTO> updateProNSCL(@Valid @RequestBody ProNSCLDTO proNSCLDTO) throws URISyntaxException {
        log.debug("REST request to update ProNSCL : {}", proNSCLDTO);
        if (proNSCLDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProNSCLDTO result = proNSCLService.save(proNSCLDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proNSCLDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-nscls} : get all the proNSCLS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNSCLS in body.
     */
    @GetMapping("/pro-nscls")
    public ResponseEntity<List<ProNSCLDTO>> getAllProNSCLS(Pageable pageable) {
        log.debug("REST request to get a page of ProNSCLS");
        Page<ProNSCLDTO> page = proNSCLService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-nscls/:id} : get the "id" proNSCL.
     *
     * @param id the id of the proNSCLDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNSCLDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-nscls/{id}")
    public ResponseEntity<ProNSCLDTO> getProNSCL(@PathVariable Long id) {
        log.debug("REST request to get ProNSCL : {}", id);
        Optional<ProNSCLDTO> proNSCLDTO = proNSCLService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proNSCLDTO);
    }

    /**
     * {@code DELETE  /pro-nscls/:id} : delete the "id" proNSCL.
     *
     * @param id the id of the proNSCLDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-nscls/{id}")
    public ResponseEntity<Void> deleteProNSCL(@PathVariable Long id) {
        log.debug("REST request to delete ProNSCL : {}", id);
        proNSCLService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-nscls/theo-gio-hien-tai/:proCheckPointID/:proKeHoachSanXuatID} : get the .
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNSCLDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID}")
    public NSCLTheoGioDTO getNSCLTheoGioHienTai(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID) {
        log.info("========== API: GET /api/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID} ==========");
        log.info("Request Parameters - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);

        NSCLTheoGioDTO nsclTheoGio = proNSCLService.getNSCLTheoGio(proCheckPointID, proKeHoachSanXuatID);

        if (nsclTheoGio != null) {
            log.info("Response - NSCL ID: {}, Qty: {}, Man (DAT): {}, Fix (SUA): {}, Reject (LOI): {}, ErrorRate: {}, QualifiedRate: {}",
                nsclTheoGio.getId(), nsclTheoGio.getQty(), nsclTheoGio.getMan(),
                nsclTheoGio.getFix(), nsclTheoGio.getReject(), nsclTheoGio.getErrorRate(), nsclTheoGio.getQualifiedRate());
            if (nsclTheoGio.getGioLamViecDTO() != null) {
                log.info("  GioLamViec - ID: {}, GioLam: {}, GioBatDau: {}, GioKetThuc: {}",
                    nsclTheoGio.getGioLamViecDTO().getId(),
                    nsclTheoGio.getGioLamViecDTO().getGioLam(),
                    nsclTheoGio.getGioLamViecDTO().getGioBatDau(),
                    nsclTheoGio.getGioLamViecDTO().getGioKetThuc());
            }
            if (nsclTheoGio.getChiTietLoiDTOS() != null) {
                log.info("  ChiTietLoi - Number of defects: {}", nsclTheoGio.getChiTietLoiDTOS().size());
            }
        } else {
            log.warn("Response - NSCL theo gio is null (will create new one)");
        }
        log.info("========== End API: GET /api/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID} ==========");

        return nsclTheoGio;
    }

    @GetMapping("/pro-nscls/all-defects/{nsclId}")
    public List<ProNSCLChiTietLoiDTO> getAllDetailDefects(@PathVariable Long nsclId) {
        return proNSCLService.getAllDetailDefects(nsclId);
    }

    /**
     * {@code GET  /pro-nscls/luy-ke/{proCheckPointID}/{proKeHoachSanXuatID} : get NSCL với tổng lũy kế của tất cả các khung giờ.
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return the {@link NSCLTheoGioDTO} với tổng lũy kế (tổng của qty1h+qty2h+...+qty14h, man1h+man2h+...+man14h, ...)
     */
    @GetMapping("/pro-nscls/luy-ke/{proCheckPointID}/{proKeHoachSanXuatID}")
    public NSCLTheoGioDTO getNSCLTheoGioLuyKe(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID) {
        log.info("========== API: GET /api/pro-nscls/luy-ke/{proCheckPointID}/{proKeHoachSanXuatID} ==========");
        log.info("Request Parameters - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Action: Lấy NSCL với tổng lũy kế cho tất cả các khung giờ");

        NSCLTheoGioDTO nsclTheoGio = proNSCLService.getNSCLTheoGioLuyKe(proCheckPointID, proKeHoachSanXuatID);

        if (nsclTheoGio != null) {
            log.info("Response - NSCL ID: {}, Qty (lũy kế): {}, Man (lũy kế): {}, Fix (lũy kế): {}, Reject (lũy kế): {}, ErrorRate: {}, QualifiedRate: {}",
                nsclTheoGio.getId(), nsclTheoGio.getQty(), nsclTheoGio.getMan(),
                nsclTheoGio.getFix(), nsclTheoGio.getReject(), nsclTheoGio.getErrorRate(), nsclTheoGio.getQualifiedRate());
            if (nsclTheoGio.getChiTietLoiDTOS() != null) {
                log.info("  ChiTietLoi - Number of defects: {}", nsclTheoGio.getChiTietLoiDTOS().size());
            }
        } else {
            log.warn("Response - NSCL lũy kế is null");
        }
        log.info("========== End API: GET /api/pro-nscls/luy-ke/{proCheckPointID}/{proKeHoachSanXuatID} ==========");

        return nsclTheoGio;
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/:proCheckPointID/:proKeHoachSanXuatID} : Tang hang dat theo gio lam viec hien tai.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if the NSCLTheoGioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the NSCLTheoGioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID}")
    public NSCLTheoGioDTO updateHangDatTheoGioHienTai(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID) throws URISyntaxException {
        log.info("========== API: PUT /api/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID} (NÚT XANH - DAT) ==========");
        log.info("Request Parameters - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Action: Tăng hàng đạt (DAT)");

        if (proCheckPointID == null || proKeHoachSanXuatID == null) {
            log.error("Invalid parameters - ProCheckPointID or ProKeHoachSanXuatID is null");
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NSCLTheoGioDTO result = proNSCLService.increaseHangDatTheoGio(proCheckPointID, proKeHoachSanXuatID);

        if (result != null) {
            log.info("Response - Successfully increased hang dat. NSCL ID: {}, Qty: {}, Man (DAT): {}, ErrorRate: {}",
                result.getId(), result.getQty(), result.getMan(), result.getErrorRate());
        } else {
            log.warn("Response - Result is null");
        }
        log.info("========== End API: PUT /api/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID} ==========");

        return result;
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/:proCheckPointID/:proKeHoachSanXuatID/rfid} : Tang hang dat theo gio lam viec hien tai.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if the NSCLTheoGioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the NSCLTheoGioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/rfid/{proCheckPointID}/{proKeHoachSanXuatID}/{rfid}")
    public NSCLTheoGioDTO updateHangDatTheoGioHienTaiByRFID(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID, @PathVariable String rfid) throws URISyntaxException {
        log.debug("REST request to update hang dat theo gio hien tai cua check point id: {} va ke hoach san xuat id: {} by rfid {}", proCheckPointID, proKeHoachSanXuatID, rfid);
        if (proCheckPointID == null || proKeHoachSanXuatID == null || rfid == null || rfid.isEmpty()) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return proNSCLService.increaseHangDatTheoGio(proCheckPointID, proKeHoachSanXuatID, rfid);
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/:proCheckPointID/:proKeHoachSanXuatID/:proLoiID} : Tang hang loi theo gio lam viec hien tai.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if the NSCLTheoGioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the NSCLTheoGioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/{proCheckPointID}/{proKeHoachSanXuatID}/{proLoiID}")
    public NSCLTheoGioDTO updateHangLoiTheoGioHienTai(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID, @PathVariable Long proLoiID,
        @Valid @RequestBody ProLoiDTO proLoiDTO, @RequestParam(name = "first-time") Boolean firstTime) throws URISyntaxException {
        log.debug("REST request to update hang loi theo gio hien tai cua check point id: {}, ke hoach san xuat id: {} voi loi id: {}", proCheckPointID, proKeHoachSanXuatID, proLoiID);
        if (proCheckPointID == null || proKeHoachSanXuatID == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return proNSCLService.increaseHangLoiTheoGio(proCheckPointID, proKeHoachSanXuatID, proLoiDTO, firstTime);
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/fixed/:proCheckPointID/:proKeHoachSanXuatID} : Tang hang dat theo gio lam viec hien tai.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if the NSCLTheoGioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the NSCLTheoGioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/fixed/{proCheckPointID}/{proKeHoachSanXuatID}")
    public NSCLTheoGioDTO updateHangTraTheoGioHienTai(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID) throws URISyntaxException {
        log.info("========== API: PUT /api/pro-nscls/theo-gio-hien-tai/fixed/{proCheckPointID}/{proKeHoachSanXuatID} (NÚT VÀNG - SUA) ==========");
        log.info("Request Parameters - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Action: Tăng hàng sửa (SUA)");

        if (proCheckPointID == null || proKeHoachSanXuatID == null) {
            log.error("Invalid parameters - ProCheckPointID or ProKeHoachSanXuatID is null");
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NSCLTheoGioDTO result = proNSCLService.increaseHangTraTheoGio(proCheckPointID, proKeHoachSanXuatID);

        if (result != null) {
            log.info("Response - Successfully increased hang sua. NSCL ID: {}, Qty: {}, Fix (SUA): {}, ErrorRate: {}",
                result.getId(), result.getQty(), result.getFix(), result.getErrorRate());
        } else {
            log.warn("Response - Result is null");
        }
        log.info("========== End API: PUT /api/pro-nscls/theo-gio-hien-tai/fixed/{proCheckPointID}/{proKeHoachSanXuatID} ==========");

        return result;
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/fixed/rfid/{proCheckPointID}/{proKeHoachSanXuatID}/{rfid}} : Tang hang tra theo gio lam viec hien tai bằng RFID.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if the NSCLTheoGioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the NSCLTheoGioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/fixed/rfid/{proCheckPointID}/{proKeHoachSanXuatID}/{rfid}")
    public NSCLTheoGioDTO updateHangTraTheoGioHienTaiByRFID(@PathVariable Long proCheckPointID, @PathVariable Long proKeHoachSanXuatID, @PathVariable String rfid) throws URISyntaxException {
        log.debug("REST request to update hang tra theo gio hien tai cua check point id: {} va ke hoach san xuat id: {} by rfid {}", proCheckPointID, proKeHoachSanXuatID, rfid);
        if (proCheckPointID == null || proKeHoachSanXuatID == null || rfid == null || rfid.isEmpty()) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return proNSCLService.increaseHangTraTheoGio(proCheckPointID, proKeHoachSanXuatID, rfid);
    }

    /**
     * {@code PUT  /pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID}} : Giảm hàng sửa (SUA) và tăng hàng lỗi (LOI) theo lũy kế.
     * Trường hợp không có RFID.
     *
     * @param proCheckPointID ID của checkpoint
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated NSCLTheoGioDTO,
     * or with status {@code 400 (Bad Request)} if validation fails,
     * or with status {@code 500 (Internal Server Error)} if the operation couldn't be completed.
     */
    @PutMapping("/pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID}")
    public ResponseEntity<NSCLTheoGioDTO> decreaseHangTraAndIncreaseHangLoiTheoGioHienTai(
            @PathVariable Long proCheckPointID,
            @PathVariable Long proKeHoachSanXuatID) {
        log.info("========== API: PUT /api/pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID} ==========");
        log.info("Request Parameters - ProCheckPointID: {}, ProKeHoachSanXuatID: {}", proCheckPointID, proKeHoachSanXuatID);
        log.info("Action: Giảm hàng sửa (SUA) và tăng hàng lỗi (LOI) - Không có RFID");

        try {
            // Validate parameters
            if (proCheckPointID == null || proKeHoachSanXuatID == null) {
                log.error("Invalid parameters - ProCheckPointID or ProKeHoachSanXuatID is null");
                throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
            }

            // Call service
            NSCLTheoGioDTO result = proNSCLService.decreaseHangTraAndIncreaseHangLoiTheoGio(proCheckPointID, proKeHoachSanXuatID);

            if (result != null) {
                log.info("Response - Successfully decreased hang sua and increased hang loi. NSCL ID: {}, Qty: {}, Fix (SUA): {}, Reject (LOI): {}, ErrorRate: {}",
                    result.getId(), result.getQty(), result.getFix(), result.getReject(), result.getErrorRate());
                log.info("========== End API: PUT /api/pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID} (SUCCESS) ==========");
                return ResponseEntity.ok().body(result);
            } else {
                log.warn("Response - Result is null");
                log.info("========== End API: PUT /api/pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID} (NULL RESULT) ==========");
                throw new BadRequestAlertException("Không thể thực hiện thao tác. Kết quả trả về null.", ENTITY_NAME, "null_result");
            }
        } catch (BadRequestAlertException e) {
            // Re-throw BadRequestAlertException để ExceptionTranslator xử lý
            log.error("BadRequestAlertException - {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Catch các exception khác và log
            log.error("Unexpected error in decreaseHangTraAndIncreaseHangLoiTheoGioHienTai - ProCheckPointID: {}, ProKeHoachSanXuatID: {}, Error: {}",
                proCheckPointID, proKeHoachSanXuatID, e.getMessage(), e);
            log.info("========== End API: PUT /api/pro-nscls/theo-gio-hien-tai/decrease-fixed/{proCheckPointID}/{proKeHoachSanXuatID} (ERROR) ==========");
            throw new BadRequestAlertException(
                "Lỗi hệ thống: " + (e.getMessage() != null ? e.getMessage() : "Không thể thực hiện thao tác"),
                ENTITY_NAME,
                "system_error"
            );
        }
    }

    /**
     * Dung hien thi bieu do nscl cua chuyen theo gio
     * LCD_Truc_Quan_Chuyen_NangSuatChatLuongTheoGio
     * @param stt Chuyen so
     * @return
     */
    @GetMapping("/pro-nscls/theo-gio-hien-tai/theo-chuyen/{stt}")
    public ResponseEntity<List<NsclTheoGio>> getBaoCaoNSCLTrenTruyenRealTime(@PathVariable Integer stt) {
        List<NsclTheoGio> results = proNSCLService.getNSCLTheoGioTrenChuyen(stt);
        return ResponseEntity.ok().body(results);
    }

    /**
     * Dung hien thi bieu do nscl thong tin chung nhu ma hang
     * LCD_Truc_Quan_Chuyen_ThongTinChung
     * @param stt Chuyen so
     * @return
     */
    @GetMapping("/pro-nscls/theo-gio-hien-tai/theo-chuyen-thong-tin-chung/{stt}")
    public ResponseEntity<NsclTheoGioChuyenThongTinChung> getNSCLTheoGioTrenChuyenThongTinChung(@PathVariable Integer stt) {
        NsclTheoGioChuyenThongTinChung results = proNSCLService.getNSCLTheoGioTrenChuyenThongTinChung(stt);
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/pro-nscls/theo-gio-hien-tai/top-loi-chi-tiet-loi/{stt}")
    public ResponseEntity<ProNSCLChiTietLoiTheoGioHienTai> getNSCLTheoGioTrenChuyenTopLoi(@PathVariable Integer stt) {
        ProNSCLChiTietLoiTheoGioHienTai results = proNSCLService.getChiTietNSCLTheoThoiGianTrenChuyen(stt);

        return ResponseEntity.ok().body(results);
    }

    /**
     * Dung hien thi tren tivi Nang suat LB/ngay
     * @param stt Chuyen so
     * @return
     */
    @GetMapping("/pro-nscls/theo-gio-hien-tai/nang-suat-lao-dong-ngay/{stt}")
    public ResponseEntity<NangSuatLaoDongNgay> getNangSuatLaoDongNgayTrenChuyen(@PathVariable Integer stt) {
        NangSuatLaoDongNgay results = proNSCLService.getNangSuatLaoDongNgayTrenChuyen(stt);
        return ResponseEntity.ok().body(results);
    }

    /**
     * {@code GET  /pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID}} : get ma hang chinh/phu cho user chon truoc khi update NSCL.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNSCLS in body.
     */
    @GetMapping("/pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID}")
    public ResponseEntity<List<MaHangChinhPhu>> getMaHangChinhPhuByProNSCL(@PathVariable Long proKeHoachSanXuatID) {
        log.info("========== API: GET /api/pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID} ==========");
        log.info("Request Parameters - ProKeHoachSanXuatID: {}", proKeHoachSanXuatID);

        List<MaHangChinhPhu> maHangList = proNSCLService.findMaHangChinhPhuByProKHSXId(proKeHoachSanXuatID);

        if (maHangList != null) {
            log.info("Response - Number of ma hang chinh/phu: {}", maHangList.size());
            for (MaHangChinhPhu maHang : maHangList) {
                log.info("  MaHang - MaChungLoai: {}, TenChungLoaiTiengViet: {}", maHang.getMaChungLoai(), maHang.getTenChungLoaiTiengViet());
            }
        } else {
            log.info("Response - No ma hang chinh/phu found (empty list)");
        }
        log.info("========== End API: GET /api/pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID} ==========");

        return ResponseEntity.ok().body(maHangList);
    }

    /**
     * {@code PUT  /pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID}} : update ma hang chinh/phu cho user chon truoc khi update NSCL.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNSCLS in body.
     */
    @PutMapping("/pro-nscls/ma-chinh-phu/{proKeHoachSanXuatID}")
    public ResponseEntity<Void> updateMaHangChinhPhuByProNSCL(@PathVariable Long proKeHoachSanXuatID, @Valid @RequestBody MaHangChinhPhuDTO maHangChinhPhuDTO) {
        log.debug("REST request to update ma hang chinh phu cua Pro KHSX ID {}, ma hang chinh/phu: {}", proKeHoachSanXuatID, maHangChinhPhuDTO.getMaChungLoai());
        proNSCLService.updateMaChungLoaiToProKHSX(proKeHoachSanXuatID, maHangChinhPhuDTO.getMaChungLoai());
        return ResponseEntity.ok().build();
    }

    /**
     * {@code GET  /pro-nscls/theo-gio-hien-tai-by-barcode/:proCheckPointID?barcode=xxx} : get NSCL theo gio hien tai by barcode.
     *
     * @param proCheckPointID the checkpoint id
     * @param barcode the barcode string
     * @return the {@link NSCLTheoGioDTO}
     */
    @GetMapping("/pro-nscls/theo-gio-hien-tai-by-barcode/{proCheckPointID}")
    public NSCLTheoGioDTO getNSCLTheoGioByBarcode(@PathVariable Long proCheckPointID, @RequestParam String barcode) {
        log.debug("REST request to get NSCL theo gio hien tai by barcode, checkpoint id: {}, barcode: {}", proCheckPointID, barcode);
        return proNSCLService.getNSCLTheoGioByBarcode(proCheckPointID, barcode);
    }

    /**
     * {@code GET  /pro-nscls/checked/{displayName}} : Kiểm tra đã kiểm tra NSCL với displayName (rfid/barcode)
     *
     * @param displayName rfid hoặc barcode
     * @return true nếu đã kiểm tra, false nếu chưa
     */
    @GetMapping("/pro-nscls/checked/{displayName}")
    public ResponseEntity<Boolean> isCheckedNSCLByDisplayName(@PathVariable String displayName) {
        log.info("========== API: GET /api/pro-nscls/checked/{displayName} ==========");
        log.info("Request Parameters - DisplayName: {}", displayName);
        log.info("Action: Kiểm tra đã quét mã (RFID/Barcode) chưa");

        boolean checked = proNSCLService.isCheckedNSCLByDisplayName(displayName);

        log.info("Response - DisplayName '{}' checked: {}", displayName, checked);
        log.info("========== End API: GET /api/pro-nscls/checked/{displayName} ==========");

        return ResponseEntity.ok(checked);
    }

        /**
     * {@code GET  /pro-nscls/checked-type/{displayName}} : Lấy trạng thái checked_type mới nhất của displayname (rfid/barcode)
     *
     * @param displayName rfid hoặc barcode
     * @return checked_type mới nhất (DAT, SUA, LOI, ...)
     */
    @GetMapping("/pro-nscls/checked-type/{displayName}")
    public ResponseEntity<String> getLatestCheckedTypeByDisplayName(@PathVariable String displayName) {
        log.info("========== API: GET /api/pro-nscls/checked-type/{displayName} ==========");
        log.info("Request Parameters - DisplayName: {}", displayName);
        log.info("Action: Lấy checked_type mới nhất của displayName");

        String checkedType = proNSCLService.getLatestCheckedTypeByDisplayName(displayName);

        log.info("Response - DisplayName '{}' latest checked_type: {}", displayName, checkedType);
        log.info("========== End API: GET /api/pro-nscls/checked-type/{displayName} ==========");

        return ResponseEntity.ok(checkedType);
    }

    /**
     * {@code GET  /pro-nscls/fix-rfid-count/{proNsclId}} : Lấy số lượng fix (SUA) có RFID theo pro_nscl_id
     *
     * @param proNsclId ID của ProNSCL
     * @return số lượng fix có RFID
     */
    @GetMapping("/pro-nscls/fix-rfid-count/{proNsclId}")
    public ResponseEntity<Long> getFixCountWithRfidByProNsclId(@PathVariable Long proNsclId) {
        log.info("========== API: GET /api/pro-nscls/fix-rfid-count/{proNsclId} ==========");
        log.info("Request Parameters - ProNsclId: {}", proNsclId);
        log.info("Action: Lấy số lượng fix (SUA) có RFID");

        Long count = proNSCLService.getFixCountWithRfidByProNsclId(proNsclId);

        log.info("Response - ProNsclId {} has {} fix items with RFID", proNsclId, count);
        log.info("========== End API: GET /api/pro-nscls/fix-rfid-count/{proNsclId} ==========");

        return ResponseEntity.ok(count);
    }

    /**
     * {@code POST  /pro-nscls/codeinjection} : Gọi stored procedure pro_nscl_sanpham_codeinjection
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body kết quả trả về 3 trường id, error, action
     */
    /**
     * {@code POST  /pro-nscls/codeinjection} : Gọi stored procedure pro_nscl_sanpham_codeinjection với các tham số đơn giản hóa
     *
     * @return the {@link ResponseEntity} với 3 trường id, error, action
     */
    @PostMapping("/pro-nscls/codeinjection")
    public ResponseEntity<ProNSCLService.CodeInjectionResult> proNsclSanPhamCodeInjection(@RequestBody SimpleCodeInjectionRequest request) {
        log.info("========== API: POST /api/pro-nscls/codeinjection (NÚT MÀU - TỔNG HỢP) ==========");
        log.info("Request Body - ProCheckpointId: {}, ProKhsxId: {}, CheckedType: {}, DisplayName: {}, FirstTime: {}, ChiTietLoiId: {}, ValueHEX: {}, RfidBarcode: {}",
            request.getProCheckpointId(), request.getProKhsxId(), request.getCheckedType(),
            request.getDisplayName(), request.isFirstTime(), request.getChiTietLoiId(), request.getValueHEX(), request.getRfidBarcode());
        log.info("Action: Code injection với checkedType: {} (DAT=Xanh, SUA=Vàng, LOI=Đỏ)", request.getCheckedType());

        ProNSCLService.CodeInjectionResult result = proNSCLService.proNsclSanPhamCodeInjection(
            request.getProCheckpointId(),
            request.getProKhsxId(),
            request.getCheckedType(),
            request.getDisplayName(),
            request.isFirstTime(),
            request.getChiTietLoiId(),
            request.getValueHEX(),
            request.getRfidBarcode()
        );

        if (result != null) {
            log.info("Response - Code injection result - ID: {}, Action: {}, Error: {}",
                result.getId(), result.getAction(), result.getError());
        } else {
            log.warn("Response - Code injection result is null");
        }
        log.info("========== End API: POST /api/pro-nscls/codeinjection ==========");

        return ResponseEntity.ok(result);
    }

    /**
     * DTO đơn giản cho request codeinjection
     */
    public static class SimpleCodeInjectionRequest {
    private Long proCheckpointId;
    private Long proKhsxId;
    private String checkedType;
    private String displayName;
    private boolean isFirstTime;
    private Long chiTietLoiId;
    private String valueHEX;
    private String rfidBarcode;  // Thêm field mới

    public Long getProCheckpointId() { return proCheckpointId; }
    public void setProCheckpointId(Long proCheckpointId) { this.proCheckpointId = proCheckpointId; }
    public Long getProKhsxId() { return proKhsxId; }
    public void setProKhsxId(Long proKhsxId) { this.proKhsxId = proKhsxId; }
    public String getCheckedType() { return checkedType; }
    public void setCheckedType(String checkedType) { this.checkedType = checkedType; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public boolean isFirstTime() { return isFirstTime; }
    public void setFirstTime(boolean firstTime) { isFirstTime = firstTime; }
    public Long getChiTietLoiId() { return chiTietLoiId; }
    public void setChiTietLoiId(Long chiTietLoiId) { this.chiTietLoiId = chiTietLoiId; }
    public String getValueHEX() { return valueHEX; }
    public void setValueHEX(String valueHEX) { this.valueHEX = valueHEX; }
    public String getRfidBarcode() { return rfidBarcode; }
    public void setRfidBarcode(String rfidBarcode) { this.rfidBarcode = rfidBarcode; }
    }

    /**
     * {@code GET  /pro-nscls/ma-cay-by-po} : Lấy danh sách mã cây theo PO (và mã hàng) với thông tin chi tiết
     *
     * @param po     Mã PO cần tìm
     * @param mahang (tùy chọn) Mã hàng (ProductCode) để lọc thêm; null/rỗng thì không lọc theo mã hàng
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and danh sách mã cây với thông tin Lot, số tem đã quét và tổng tem
     */
    @GetMapping("/pro-nscls/ma-cay-by-po")
    public ResponseEntity<List<MaCayByPODTO>> getMaCayByPO(
            @RequestParam String po,
            @RequestParam(required = false) String mahang) {
        log.info("========== API: GET /api/pro-nscls/ma-cay-by-po?po={}&mahang={} ==========", po, mahang);
        log.info("Request Parameters - PO: {}, MaHang: {}", po, mahang);
        log.info("Action: Lấy danh sách mã cây theo PO (và mã hàng) với thông tin chi tiết");

        List<MaCayByPODTO> maCayList = proNSCLService.getMaCayByPO(po, mahang);

        if (maCayList != null && !maCayList.isEmpty()) {
            log.info("Response - Found {} mã cây for PO: {}", maCayList.size(), po);
            for (MaCayByPODTO dto : maCayList) {
                log.debug("  - Code: {}, Lot: {}, SoTemDaQuet: {}, TongSoTem: {}", 
                    dto.getCode(), dto.getLot(), dto.getSoTemDaQuet(), dto.getTongSoTem());
            }
        } else {
            log.info("Response - No mã cây found for PO: {}", po);
        }
        log.info("========== End API: GET /api/pro-nscls/ma-cay-by-po ==========");

        return ResponseEntity.ok(maCayList);
    }

    /**
     * {@code GET  /pro-nscls/quantity-limit-btp/{proKeHoachSanXuatID}} : Lấy số lượng giới hạn BTP từ Summary_PO_QuetNhan
     *
     * @param proKeHoachSanXuatID ID của kế hoạch sản xuất
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and số lượng giới hạn BTP, hoặc null nếu không tìm thấy
     */
    @GetMapping("/pro-nscls/quantity-limit-btp/{proKeHoachSanXuatID}")
    public ResponseEntity<Integer> getQuantityLimitBTP(@PathVariable Long proKeHoachSanXuatID) {
        log.info("========== API: GET /api/pro-nscls/quantity-limit-btp/{} ==========", proKeHoachSanXuatID);
        log.info("Request Parameters - ProKeHoachSanXuatID: {}", proKeHoachSanXuatID);
        log.info("Action: Lấy số lượng giới hạn BTP từ bảng Summary_PO_QuetNhan");

        Integer quantityLimit = proNSCLService.getQuantityLimitBTP(proKeHoachSanXuatID);

        if (quantityLimit != null) {
            log.info("Response - Quantity Limit BTP: {}", quantityLimit);
        } else {
            log.info("Response - No quantity limit found for ProKeHoachSanXuatID: {}", proKeHoachSanXuatID);
        }
        log.info("========== End API: GET /api/pro-nscls/quantity-limit-btp/{} ==========", proKeHoachSanXuatID);

        return ResponseEntity.ok(quantityLimit);
    }

    /**
     * {@code GET  /pro-nscls/tong-may-ra/{khsxId}} : Lấy tổng may ra (tổng hàng đã kiểm: Sửa + Đạt) theo KHSX
     * Gọi stored procedure SP_TongMayRa_ByKHSX
     *
     * @param khsxId ID của kế hoạch sản xuất
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and TongMayRaDTO chứa tổng Qty, Fix và TongMayRa
     */
    @GetMapping("/pro-nscls/tong-may-ra/{khsxId}")
    public ResponseEntity<TongMayRaDTO> getTongMayRaByKHSX(@PathVariable Long khsxId) {
        log.info("========== API: GET /api/pro-nscls/tong-may-ra/{} ==========", khsxId);
        log.info("Request Parameters - KHSX ID: {}", khsxId);
        log.info("Action: Lấy tổng may ra (tổng hàng đã kiểm: Sửa + Đạt) theo lịch sử KHSX");

        TongMayRaDTO result = proNSCLService.getTongMayRaByKHSX(khsxId);

        if (result != null) {
            log.info("Response - SanPhamId: {}, ChuyenId: {}, TotalQty (Đạt): {}, TotalFix (Sửa): {}, TongMayRa (Đạt + Sửa): {}",
                result.getSanphamId(), result.getChuyenId(), result.getTotalQty(), result.getTotalFix(), result.getTongMayRa());
        } else {
            log.info("Response - No data found for KHSX ID: {}", khsxId);
        }
        log.info("========== End API: GET /api/pro-nscls/tong-may-ra/{} ==========", khsxId);

        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET /pro-nscls/po-fix/past-defects} : Tổng lỗi quá khứ (đến hết hôm qua) trong pool query 2.
     */
    @GetMapping("/pro-nscls/po-fix/past-defects")
    public ResponseEntity<PoFixPastDTO> getPoFixPastDefects(
        @RequestParam String poNo,
        @RequestParam Long khsxId) {
        log.info("========== API: GET /api/pro-nscls/po-fix/past-defects poNo={}, khsxId={} ==========", poNo, khsxId);
        PoFixPastDTO result = proNSCLService.getPoFixPastDefects(poNo, khsxId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code POST /pro-nscls/po-fix} : Delta sửa PO (pool query 2): insert SUA LOI RFID (tối đa delta),
     * FIFO trừ reject quá khứ, cộng sua_po_luy_ke (không ghi fix_*h).
     */
    @PostMapping("/pro-nscls/po-fix")
    public ResponseEntity<Void> applyPoFix(@Valid @RequestBody ApplyPoFixRequestDTO request) {
        log.info("========== API: POST /api/pro-nscls/po-fix ==========");
        proNSCLService.applyPoFixDelta(request);
        return ResponseEntity.ok().build();
    }
}
