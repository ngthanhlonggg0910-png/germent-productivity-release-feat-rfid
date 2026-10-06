package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProKeHoachSanXuatService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.dto.ProKeHoachSanXuatActivationStatusDTO;
import vn.nvsoft.service.dto.ProKeHoachSanXuatCriteria;
import vn.nvsoft.service.ProKeHoachSanXuatQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProKeHoachSanXuat}.
 */
@RestController
@RequestMapping("/api")
public class ProKeHoachSanXuatResource {

    private final Logger log = LoggerFactory.getLogger(ProKeHoachSanXuatResource.class);

    private static final String ENTITY_NAME = "proKeHoachSanXuat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProKeHoachSanXuatService proKeHoachSanXuatService;

    private final ProKeHoachSanXuatQueryService proKeHoachSanXuatQueryService;

    public ProKeHoachSanXuatResource(ProKeHoachSanXuatService proKeHoachSanXuatService, ProKeHoachSanXuatQueryService proKeHoachSanXuatQueryService) {
        this.proKeHoachSanXuatService = proKeHoachSanXuatService;
        this.proKeHoachSanXuatQueryService = proKeHoachSanXuatQueryService;
    }

    /**
     * {@code POST  /pro-ke-hoach-san-xuats} : Create a new proKeHoachSanXuat.
     *
     * @param proKeHoachSanXuatDTO the proKeHoachSanXuatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proKeHoachSanXuatDTO, or with status {@code 400 (Bad Request)} if the proKeHoachSanXuat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-ke-hoach-san-xuats")
    public ResponseEntity<ProKeHoachSanXuatDTO> createProKeHoachSanXuat(@RequestBody ProKeHoachSanXuatDTO proKeHoachSanXuatDTO) throws URISyntaxException {
        log.debug("REST request to save ProKeHoachSanXuat : {}", proKeHoachSanXuatDTO);
        if (proKeHoachSanXuatDTO.getId() != null) {
            throw new BadRequestAlertException("A new proKeHoachSanXuat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProKeHoachSanXuatDTO result = proKeHoachSanXuatService.save(proKeHoachSanXuatDTO);
        return ResponseEntity.created(new URI("/api/pro-ke-hoach-san-xuats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-ke-hoach-san-xuats} : Updates an existing proKeHoachSanXuat.
     *
     * @param proKeHoachSanXuatDTO the proKeHoachSanXuatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proKeHoachSanXuatDTO,
     * or with status {@code 400 (Bad Request)} if the proKeHoachSanXuatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proKeHoachSanXuatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-ke-hoach-san-xuats")
    public ResponseEntity<ProKeHoachSanXuatDTO> updateProKeHoachSanXuat(@RequestBody ProKeHoachSanXuatDTO proKeHoachSanXuatDTO) throws URISyntaxException {
        log.debug("REST request to update ProKeHoachSanXuat : {}", proKeHoachSanXuatDTO);
        if (proKeHoachSanXuatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProKeHoachSanXuatDTO result = proKeHoachSanXuatService.save(proKeHoachSanXuatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proKeHoachSanXuatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-ke-hoach-san-xuats} : get all the proKeHoachSanXuats.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proKeHoachSanXuats in body.
     */
    @GetMapping("/pro-ke-hoach-san-xuats")
    public ResponseEntity<List<ProKeHoachSanXuatDTO>> getAllProKeHoachSanXuats(ProKeHoachSanXuatCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ProKeHoachSanXuats by criteria: {}", criteria);
        Page<ProKeHoachSanXuatDTO> page = proKeHoachSanXuatQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pro-ke-hoach-san-xuats/count} : count all the proKeHoachSanXuats.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pro-ke-hoach-san-xuats/count")
    public ResponseEntity<Long> countProKeHoachSanXuats(ProKeHoachSanXuatCriteria criteria) {
        log.debug("REST request to count ProKeHoachSanXuats by criteria: {}", criteria);
        return ResponseEntity.ok().body(proKeHoachSanXuatQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pro-ke-hoach-san-xuats/:id} : get the "id" proKeHoachSanXuat.
     *
     * @param id the id of the proKeHoachSanXuatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proKeHoachSanXuatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-ke-hoach-san-xuats/{id}")
    public ResponseEntity<ProKeHoachSanXuatDTO> getProKeHoachSanXuat(@PathVariable Long id) {
        log.debug("REST request to get ProKeHoachSanXuat : {}", id);
        Optional<ProKeHoachSanXuatDTO> proKeHoachSanXuatDTO = proKeHoachSanXuatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proKeHoachSanXuatDTO);
    }

    /**
     * {@code GET  /pro-ke-hoach-san-xuats/:id/activation-status} : trạng thái kích hoạt của kế hoạch (để hiển thị UI).
     * Body gồm {@code khsxId}, {@code isActived} (boolean hoặc null), {@code activeTime}.
     */
    @GetMapping("/pro-ke-hoach-san-xuats/{id}/activation-status")
    public ResponseEntity<ProKeHoachSanXuatActivationStatusDTO> getProKeHoachSanXuatActivationStatus(@PathVariable Long id) {
        log.debug("REST request to get activation status for ProKeHoachSanXuat : {}", id);
        Optional<ProKeHoachSanXuatActivationStatusDTO> dto = proKeHoachSanXuatService.getActivationStatus(id);
        return ResponseUtil.wrapOrNotFound(dto);
    }

    /**
     * {@code DELETE  /pro-ke-hoach-san-xuats/:id} : delete the "id" proKeHoachSanXuat.
     *
     * @param id the id of the proKeHoachSanXuatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-ke-hoach-san-xuats/{id}")
    public ResponseEntity<Void> deleteProKeHoachSanXuat(@PathVariable Long id) {
        log.debug("REST request to delete ProKeHoachSanXuat : {}", id);
        proKeHoachSanXuatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code PUT  /pro-ke-hoach-san-xuats/:id/activate-exclusive-for-chuyen-day} :
     * bản ghi {@code id} {@code isActived = true}; mọi kế hoạch khác cùng chuyền và cùng ngày sản xuất (theo Asia/Ho_Chi_Minh) {@code isActived = false}.
     */
    @PutMapping("/pro-ke-hoach-san-xuats/{id}/activate-exclusive-for-chuyen-day")
    public ResponseEntity<ProKeHoachSanXuatDTO> activateExclusiveForChuyenDay(@PathVariable Long id) {
        log.debug("REST request to activate exclusively ProKeHoachSanXuat : {}", id);
        Optional<ProKeHoachSanXuatDTO> result = proKeHoachSanXuatService.activateExclusiveForChuyenDay(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code PUT  /pro-ke-hoach-san-xuats/activate-exclusive-by-chuyen-day} :
     * kế hoạch {@code proKhsxId} {@code isActived = true}; mọi kế hoạch khác cùng {@code chuyenId} và cùng {@code date} (Asia/Ho_Chi_Minh) {@code isActived = false}.
     * Tham số phải khớp dữ liệu thực tế của bản ghi (chuyền và ngày).
     */
    @PutMapping("/pro-ke-hoach-san-xuats/activate-exclusive-by-chuyen-day")
    public ResponseEntity<ProKeHoachSanXuatDTO> activateExclusiveByChuyenDayParams(
        @RequestParam Long chuyenId,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
        @RequestParam("prokhsxid") Long proKhsxId
    ) {
        log.debug("REST request activate-exclusive-by-chuyen-day chuyenId={}, date={}, prokhsxid={}", chuyenId, date, proKhsxId);
        Optional<ProKeHoachSanXuatDTO> result = proKeHoachSanXuatService.activateExclusiveForChuyenDay(chuyenId, date, proKhsxId);
        return ResponseUtil.wrapOrNotFound(result);
    }
}
