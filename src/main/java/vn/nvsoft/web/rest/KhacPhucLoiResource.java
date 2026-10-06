package vn.nvsoft.web.rest;

import vn.nvsoft.service.KhacPhucLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.KhacPhucLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.KhacPhucLoi}.
 */
@RestController
@RequestMapping("/api")
public class KhacPhucLoiResource {

    private final Logger log = LoggerFactory.getLogger(KhacPhucLoiResource.class);

    private static final String ENTITY_NAME = "khacPhucLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KhacPhucLoiService khacPhucLoiService;

    public KhacPhucLoiResource(KhacPhucLoiService khacPhucLoiService) {
        this.khacPhucLoiService = khacPhucLoiService;
    }

    /**
     * {@code POST  /khac-phuc-lois} : Create a new khacPhucLoi.
     *
     * @param khacPhucLoiDTO the khacPhucLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new khacPhucLoiDTO, or with status {@code 400 (Bad Request)} if the khacPhucLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/khac-phuc-lois")
    public ResponseEntity<KhacPhucLoiDTO> createKhacPhucLoi(@RequestBody KhacPhucLoiDTO khacPhucLoiDTO) throws URISyntaxException {
        log.debug("REST request to save KhacPhucLoi : {}", khacPhucLoiDTO);
        if (khacPhucLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new khacPhucLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KhacPhucLoiDTO result = khacPhucLoiService.save(khacPhucLoiDTO);
        return ResponseEntity.created(new URI("/api/khac-phuc-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /khac-phuc-lois} : Updates an existing khacPhucLoi.
     *
     * @param khacPhucLoiDTO the khacPhucLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated khacPhucLoiDTO,
     * or with status {@code 400 (Bad Request)} if the khacPhucLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the khacPhucLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/khac-phuc-lois")
    public ResponseEntity<KhacPhucLoiDTO> updateKhacPhucLoi(@RequestBody KhacPhucLoiDTO khacPhucLoiDTO) throws URISyntaxException {
        log.debug("REST request to update KhacPhucLoi : {}", khacPhucLoiDTO);
        if (khacPhucLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KhacPhucLoiDTO result = khacPhucLoiService.save(khacPhucLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, khacPhucLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /khac-phuc-lois} : get all the khacPhucLois.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of khacPhucLois in body.
     */
    @GetMapping("/khac-phuc-lois")
    public ResponseEntity<List<KhacPhucLoiDTO>> getAllKhacPhucLois(Pageable pageable) {
        log.debug("REST request to get a page of KhacPhucLois");
        Page<KhacPhucLoiDTO> page = khacPhucLoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /khac-phuc-lois/:id} : get the "id" khacPhucLoi.
     *
     * @param id the id of the khacPhucLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the khacPhucLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/khac-phuc-lois/{id}")
    public ResponseEntity<KhacPhucLoiDTO> getKhacPhucLoi(@PathVariable Long id) {
        log.debug("REST request to get KhacPhucLoi : {}", id);
        Optional<KhacPhucLoiDTO> khacPhucLoiDTO = khacPhucLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(khacPhucLoiDTO);
    }

    /**
     * {@code DELETE  /khac-phuc-lois/:id} : delete the "id" khacPhucLoi.
     *
     * @param id the id of the khacPhucLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/khac-phuc-lois/{id}")
    public ResponseEntity<Void> deleteKhacPhucLoi(@PathVariable Long id) {
        log.debug("REST request to delete KhacPhucLoi : {}", id);
        khacPhucLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
