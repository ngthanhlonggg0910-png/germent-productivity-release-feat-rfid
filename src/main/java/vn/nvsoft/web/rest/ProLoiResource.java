package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.dto.ProLoiCriteria;
import vn.nvsoft.service.ProLoiQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProLoiResource.class);

    private static final String ENTITY_NAME = "proLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProLoiService proLoiService;

    private final ProLoiQueryService proLoiQueryService;

    public ProLoiResource(ProLoiService proLoiService, ProLoiQueryService proLoiQueryService) {
        this.proLoiService = proLoiService;
        this.proLoiQueryService = proLoiQueryService;
    }

    /**
     * {@code POST  /pro-lois} : Create a new proLoi.
     *
     * @param proLoiDTO the proLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proLoiDTO, or with status {@code 400 (Bad Request)} if the proLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-lois")
    public ResponseEntity<ProLoiDTO> createProLoi(@RequestBody ProLoiDTO proLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProLoi : {}", proLoiDTO);
        if (proLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProLoiDTO result = proLoiService.save(proLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-lois} : Updates an existing proLoi.
     *
     * @param proLoiDTO the proLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-lois")
    public ResponseEntity<ProLoiDTO> updateProLoi(@RequestBody ProLoiDTO proLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProLoi : {}", proLoiDTO);
        if (proLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProLoiDTO result = proLoiService.save(proLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-lois} : get all the proLois.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proLois in body.
     */
    @GetMapping("/pro-lois")
    public ResponseEntity<List<ProLoiDTO>> getAllProLois(ProLoiCriteria criteria, Pageable pageable) {
        log.info("========== API: GET /api/pro-lois ==========");
        log.info("Request Parameters - Criteria: {}", criteria);
        if (criteria != null && criteria.getProCumLoiId() != null) {
            log.info("  ProCumLoiId filter: {}", criteria.getProCumLoiId().getIn());
        }
        log.info("Action: Lấy danh sách lỗi (defects) theo nhóm lỗi");
        
        List<ProLoiDTO> list = proLoiQueryService.findByCriteria(criteria);
        
        if (list != null) {
            log.info("Response - Found {} defects", list.size());
            for (ProLoiDTO loi : list) {
                log.info("  Defect - ID: {}, TenLoi: {}, MaLoi: {}, MauSac: {}", 
                    loi.getId(), loi.getTenLoi(), loi.getMaLoi(), loi.getMauSac());
            }
        } else {
            log.warn("Response - Defects list is null");
        }
        log.info("========== End API: GET /api/pro-lois ==========");
        
        return ResponseEntity.ok().body(list);
    }

    /**
    * {@code GET  /pro-lois/count} : count all the proLois.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pro-lois/count")
    public ResponseEntity<Long> countProLois(ProLoiCriteria criteria) {
        log.debug("REST request to count ProLois by criteria: {}", criteria);
        return ResponseEntity.ok().body(proLoiQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pro-lois/:id} : get the "id" proLoi.
     *
     * @param id the id of the proLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-lois/{id}")
    public ResponseEntity<ProLoiDTO> getProLoi(@PathVariable Long id) {
        log.debug("REST request to get ProLoi : {}", id);
        Optional<ProLoiDTO> proLoiDTO = proLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proLoiDTO);
    }

    /**
     * {@code DELETE  /pro-lois/:id} : delete the "id" proLoi.
     *
     * @param id the id of the proLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-lois/{id}")
    public ResponseEntity<Void> deleteProLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProLoi : {}", id);
        proLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
