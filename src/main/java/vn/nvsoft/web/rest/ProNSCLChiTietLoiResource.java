package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProNSCLChiTietLoiService;
import vn.nvsoft.service.ProNSCLService;
import vn.nvsoft.service.dto.NSCLTheoGioDTO;
import vn.nvsoft.service.dto.WarningProNSCLChiTietLoiVO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;

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

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProNSCLChiTietLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProNSCLChiTietLoiResource {



    private final Logger log = LoggerFactory.getLogger(ProNSCLChiTietLoiResource.class);

    private static final String ENTITY_NAME = "proNSCLChiTietLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProNSCLChiTietLoiService proNSCLChiTietLoiService;
    private final ProNSCLService proNSCLService;

    public ProNSCLChiTietLoiResource(ProNSCLChiTietLoiService proNSCLChiTietLoiService, ProNSCLService proNSCLService) {
        this.proNSCLChiTietLoiService = proNSCLChiTietLoiService;
        this.proNSCLService = proNSCLService;
    }

    /**
     * {@code POST  /pro-nscl-chi-tiet-lois} : Create a new proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiDTO the proNSCLChiTietLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNSCLChiTietLoiDTO, or with status {@code 400 (Bad Request)} if the proNSCLChiTietLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-nscl-chi-tiet-lois")
    public ResponseEntity<NSCLTheoGioDTO> createProNSCLChiTietLoi(@Valid @RequestBody ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO, @RequestParam(name = "first-time") Boolean firstTime) throws URISyntaxException {
        log.info("========== API: POST /api/pro-nscl-chi-tiet-lois (NÚT ĐỎ - TẠO CHI TIẾT LỖI) ==========");
        log.info("Request Body - CheckPointID: {}, KeHoachSanXuatID: {}, ProLoiID: {}, Dx: {}, Dy: {}, Front: {}, Inside: {}", 
            proNSCLChiTietLoiDTO.getCheckPointID(), 
            proNSCLChiTietLoiDTO.getKeHoachSanXuatID(),
            proNSCLChiTietLoiDTO.getProLoi() != null ? proNSCLChiTietLoiDTO.getProLoi().getId() : null,
            proNSCLChiTietLoiDTO.getDx(),
            proNSCLChiTietLoiDTO.getDy(),
            proNSCLChiTietLoiDTO.getFront(),
            proNSCLChiTietLoiDTO.getInside());
        log.info("Request Parameters - FirstTime: {}", firstTime);
        log.info("Action: Tạo chi tiết lỗi (LOI)");
        
        if (proNSCLChiTietLoiDTO.getId() != null) {
            log.error("A new proNSCLChiTietLoi cannot already have an ID: {}", proNSCLChiTietLoiDTO.getId());
            throw new BadRequestAlertException("A new proNSCLChiTietLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        // saving chi tiet loi
        ProNSCLChiTietLoiDTO chiTietLoiDTO = proNSCLChiTietLoiService.save(proNSCLChiTietLoiDTO);
        log.info("Saved chi tiet loi with ID: {}", chiTietLoiDTO != null ? chiTietLoiDTO.getId() : null);
        
        // increasing the defect counter in nscl
        NSCLTheoGioDTO result = null;
        if (chiTietLoiDTO != null) {
            log.info("Increasing hang loi counter in NSCL");
            result = proNSCLService.increaseHangLoiTheoGio(proNSCLChiTietLoiDTO.getCheckPointID(), proNSCLChiTietLoiDTO.getKeHoachSanXuatID(), proNSCLChiTietLoiDTO.getProLoi(), firstTime);
            
            if (result != null) {
                log.info("Response - Successfully created chi tiet loi and increased hang loi. NSCL ID: {}, Qty: {}, Reject (LOI): {}, ErrorRate: {}", 
                    result.getId(), result.getQty(), result.getReject(), result.getErrorRate());
            }
        } else {
            log.warn("Response - Chi tiet loi DTO is null after save");
        }
        
        log.info("========== End API: POST /api/pro-nscl-chi-tiet-lois ==========");
        
        return ResponseEntity.created(new URI("/api/pro-nscl-chi-tiet-lois/" + chiTietLoiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /pro-nscl-chi-tiet-lois/rfid} : Create a new proNSCLChiTietLoi with RFID.
     *
     * @param proNSCLChiTietLoiDTO the proNSCLChiTietLoiDTO to create.
     * @param firstTime xác định lần đầu
     * @param rfid mã RFID
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNSCLChiTietLoiDTO, or with status {@code 400 (Bad Request)} if the proNSCLChiTietLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-nscl-chi-tiet-lois/rfid")
    public ResponseEntity<NSCLTheoGioDTO> createProNSCLChiTietLoiByRFID(
            @Valid @RequestBody ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO,
            @RequestParam(name = "first-time") Boolean firstTime,
            @RequestParam(name = "rfid") String rfid
    ) throws URISyntaxException {
        log.info("========== API: POST /api/pro-nscl-chi-tiet-lois/rfid (NÚT ĐỎ - TẠO CHI TIẾT LỖI VỚI RFID) ==========");
        log.info("Request Body - CheckPointID: {}, KeHoachSanXuatID: {}, ProLoiID: {}, Dx: {}, Dy: {}", 
            proNSCLChiTietLoiDTO.getCheckPointID(), 
            proNSCLChiTietLoiDTO.getKeHoachSanXuatID(),
            proNSCLChiTietLoiDTO.getProLoi() != null ? proNSCLChiTietLoiDTO.getProLoi().getId() : null,
            proNSCLChiTietLoiDTO.getDx(),
            proNSCLChiTietLoiDTO.getDy());
        log.info("Request Parameters - RFID: {}, FirstTime: {}", rfid, firstTime);
        log.info("Action: Tạo chi tiết lỗi (LOI) với RFID");
        
        if (proNSCLChiTietLoiDTO.getId() != null) {
            log.error("A new proNSCLChiTietLoi cannot already have an ID: {}", proNSCLChiTietLoiDTO.getId());
            throw new BadRequestAlertException("A new proNSCLChiTietLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        // saving chi tiet loi
        ProNSCLChiTietLoiDTO chiTietLoiDTO = proNSCLChiTietLoiService.save(proNSCLChiTietLoiDTO);
        log.info("Saved chi tiet loi with ID: {}, RFID: {}", chiTietLoiDTO != null ? chiTietLoiDTO.getId() : null, rfid);
        
        // increasing the defect counter in nscl
        NSCLTheoGioDTO result = null;
        if (chiTietLoiDTO != null) {
            log.info("Increasing hang loi counter in NSCL with RFID");
            result = proNSCLService.increaseHangLoiTheoGio(proNSCLChiTietLoiDTO.getCheckPointID(), proNSCLChiTietLoiDTO.getKeHoachSanXuatID(), proNSCLChiTietLoiDTO.getProLoi(), firstTime, chiTietLoiDTO.getId(), rfid);
            
            if (result != null) {
                log.info("Response - Successfully created chi tiet loi with RFID and increased hang loi. NSCL ID: {}, Qty: {}, Reject (LOI): {}", 
                    result.getId(), result.getQty(), result.getReject());
            }
        } else {
            log.warn("Response - Chi tiet loi DTO is null after save");
        }
        
        log.info("========== End API: POST /api/pro-nscl-chi-tiet-lois/rfid ==========");
        
        return ResponseEntity.created(new URI("/api/pro-nscl-chi-tiet-lois/rfid/" + chiTietLoiDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, chiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-nscl-chi-tiet-lois} : Updates an existing proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiDTO the proNSCLChiTietLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNSCLChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proNSCLChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNSCLChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscl-chi-tiet-lois")
    public ResponseEntity<ProNSCLChiTietLoiDTO> updateProNSCLChiTietLoi(@Valid @RequestBody ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProNSCLChiTietLoi : {}", proNSCLChiTietLoiDTO);
        if (proNSCLChiTietLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProNSCLChiTietLoiDTO result = proNSCLChiTietLoiService.save(proNSCLChiTietLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proNSCLChiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-nscl-chi-tiet-lois} : get all the proNSCLChiTietLois.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNSCLChiTietLois in body.
     */
    @GetMapping("/pro-nscl-chi-tiet-lois")
    public ResponseEntity<List<ProNSCLChiTietLoiDTO>> getAllProNSCLChiTietLois(Pageable pageable) {
        log.debug("REST request to get a page of ProNSCLChiTietLois");
        Page<ProNSCLChiTietLoiDTO> page = proNSCLChiTietLoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-nscl-chi-tiet-lois/:id} : get the "id" proNSCLChiTietLoi.
     *
     * @param id the id of the proNSCLChiTietLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNSCLChiTietLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-nscl-chi-tiet-lois/{id}")
    public ResponseEntity<ProNSCLChiTietLoiDTO> getProNSCLChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to get ProNSCLChiTietLoi : {}", id);
        Optional<ProNSCLChiTietLoiDTO> proNSCLChiTietLoiDTO = proNSCLChiTietLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proNSCLChiTietLoiDTO);
    }

    /**
     * {@code DELETE  /pro-nscl-chi-tiet-lois/:id} : delete the "id" proNSCLChiTietLoi.
     *
     * @param id the id of the proNSCLChiTietLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-nscl-chi-tiet-lois/{id}")
    public ResponseEntity<Void> deleteProNSCLChiTietLoi(@PathVariable Long id) {
        log.info("========== API: DELETE /api/pro-nscl-chi-tiet-lois/{id} ==========");
        log.info("Request Parameters - ID: {}", id);
        log.info("Action: Xóa chi tiết lỗi");
        
        proNSCLChiTietLoiService.delete(id);
        
        log.info("Response - Successfully deleted chi tiet loi with ID: {}", id);
        log.info("========== End API: DELETE /api/pro-nscl-chi-tiet-lois/{id} ==========");
        
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code PUT  /pro-nscl-chi-tiet-lois/warning} : Updates warning of an existing proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiVO the proNSCLChiTietLoiVO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNSCLChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proNSCLChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNSCLChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nscl-chi-tiet-lois/warning")
    public ResponseEntity<Void> updateWarningOfProNSCLChiTietLoi(@Valid @RequestBody WarningProNSCLChiTietLoiVO proNSCLChiTietLoiVO) throws URISyntaxException {
        log.debug("REST request to update warning of ProNSCLChiTietLoi : {}", proNSCLChiTietLoiVO);
        proNSCLChiTietLoiService.updateWarningOfProNSCLChiTietLoi(proNSCLChiTietLoiVO);
        return ResponseEntity.ok().build();
    }
}
