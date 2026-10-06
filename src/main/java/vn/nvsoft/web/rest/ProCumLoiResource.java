package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProCumLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProCumLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProCumLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProCumLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProCumLoiResource.class);

    private static final String ENTITY_NAME = "proCumLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProCumLoiService proCumLoiService;

    public ProCumLoiResource(ProCumLoiService proCumLoiService) {
        this.proCumLoiService = proCumLoiService;
    }

    /**
     * {@code POST  /pro-cum-lois} : Create a new proCumLoi.
     *
     * @param proCumLoiDTO the proCumLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proCumLoiDTO, or with status {@code 400 (Bad Request)} if the proCumLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-cum-lois")
    public ResponseEntity<ProCumLoiDTO> createProCumLoi(@RequestBody ProCumLoiDTO proCumLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProCumLoi : {}", proCumLoiDTO);
        if (proCumLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proCumLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProCumLoiDTO result = proCumLoiService.save(proCumLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-cum-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-cum-lois} : Updates an existing proCumLoi.
     *
     * @param proCumLoiDTO the proCumLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proCumLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proCumLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proCumLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-cum-lois")
    public ResponseEntity<ProCumLoiDTO> updateProCumLoi(@RequestBody ProCumLoiDTO proCumLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProCumLoi : {}", proCumLoiDTO);
        if (proCumLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProCumLoiDTO result = proCumLoiService.save(proCumLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proCumLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-cum-lois} : get all the proCumLois.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proCumLois in body.
     */
    @GetMapping("/pro-cum-lois")
    public ResponseEntity<List<ProCumLoiDTO>> getAllProCumLois(Pageable pageable) {
        log.debug("REST request to get a page of ProCumLois");
        Page<ProCumLoiDTO> page = proCumLoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/pro-cum-loi-with-count")
    public ResponseEntity<List<ProCumLoiDTO>> getAllAndCount() {
        log.info("========== API: GET /api/pro-cum-loi-with-count ==========");
        log.info("Request - Getting all defect groups with count");
        log.info("Action: Lấy danh sách nhóm lỗi (defect groups) để chọn loại lỗi");
        
        List<ProCumLoiDTO> allAndCount = proCumLoiService.findAllAndCount();
        
        if (allAndCount != null) {
            log.info("Response - Found {} defect groups", allAndCount.size());
            for (ProCumLoiDTO cumLoi : allAndCount) {
                log.info("  Defect Group - ID: {}, Ten: {}, Count: {}", 
                    cumLoi.getId(), cumLoi.getTen(), cumLoi.getCount());
            }
        } else {
            log.warn("Response - Defect groups list is null");
        }
        log.info("========== End API: GET /api/pro-cum-loi-with-count ==========");
        
        Optional<List<ProCumLoiDTO>> optional = Optional.ofNullable(allAndCount);
        return ResponseUtil.wrapOrNotFound(optional);
    }

    /**
     * {@code GET  /pro-cum-lois/:id} : get the "id" proCumLoi.
     *
     * @param id the id of the proCumLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proCumLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-cum-lois/{id}")
    public ResponseEntity<ProCumLoiDTO> getProCumLoi(@PathVariable Long id) {
        log.debug("REST request to get ProCumLoi : {}", id);
        Optional<ProCumLoiDTO> proCumLoiDTO = proCumLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proCumLoiDTO);
    }

    /**
     * {@code DELETE  /pro-cum-lois/:id} : delete the "id" proCumLoi.
     *
     * @param id the id of the proCumLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-cum-lois/{id}")
    public ResponseEntity<Void> deleteProCumLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProCumLoi : {}", id);
        proCumLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
