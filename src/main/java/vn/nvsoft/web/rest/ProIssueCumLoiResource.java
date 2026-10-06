package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProIssueCumLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProIssueCumLoiDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProIssueCumLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProIssueCumLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProIssueCumLoiResource.class);

    private static final String ENTITY_NAME = "proIssueCumLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProIssueCumLoiService proIssueCumLoiService;

    public ProIssueCumLoiResource(ProIssueCumLoiService proIssueCumLoiService) {
        this.proIssueCumLoiService = proIssueCumLoiService;
    }

    /**
     * {@code POST  /pro-issue-cum-lois} : Create a new proIssueCumLoi.
     *
     * @param proIssueCumLoiDTO the proIssueCumLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proIssueCumLoiDTO, or with status {@code 400 (Bad Request)} if the proIssueCumLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-issue-cum-lois")
    public ResponseEntity<ProIssueCumLoiDTO> createProIssueCumLoi(@RequestBody ProIssueCumLoiDTO proIssueCumLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProIssueCumLoi : {}", proIssueCumLoiDTO);
        if (proIssueCumLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proIssueCumLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProIssueCumLoiDTO result = proIssueCumLoiService.save(proIssueCumLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-issue-cum-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-issue-cum-lois} : Updates an existing proIssueCumLoi.
     *
     * @param proIssueCumLoiDTO the proIssueCumLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proIssueCumLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proIssueCumLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proIssueCumLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-issue-cum-lois")
    public ResponseEntity<ProIssueCumLoiDTO> updateProIssueCumLoi(@RequestBody ProIssueCumLoiDTO proIssueCumLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProIssueCumLoi : {}", proIssueCumLoiDTO);
        if (proIssueCumLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProIssueCumLoiDTO result = proIssueCumLoiService.save(proIssueCumLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proIssueCumLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-issue-cum-lois} : get all the proIssueCumLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssueCumLois in body.
     */
    @GetMapping("/pro-issue-cum-lois")
    public List<ProIssueCumLoiDTO> getAllProIssueCumLois() {
        log.debug("REST request to get all ProIssueCumLois");
        return proIssueCumLoiService.findAll();
    }

    /**
     * {@code GET  /pro-issue-cum-lois/:id} : get the "id" proIssueCumLoi.
     *
     * @param id the id of the proIssueCumLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proIssueCumLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-issue-cum-lois/{id}")
    public ResponseEntity<ProIssueCumLoiDTO> getProIssueCumLoi(@PathVariable Long id) {
        log.debug("REST request to get ProIssueCumLoi : {}", id);
        Optional<ProIssueCumLoiDTO> proIssueCumLoiDTO = proIssueCumLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proIssueCumLoiDTO);
    }

    /**
     * {@code DELETE  /pro-issue-cum-lois/:id} : delete the "id" proIssueCumLoi.
     *
     * @param id the id of the proIssueCumLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-issue-cum-lois/{id}")
    public ResponseEntity<Void> deleteProIssueCumLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProIssueCumLoi : {}", id);
        proIssueCumLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-issue-cum-lois-with-count} : get all the proIssueCumLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssueCumLois in body.
     */
    @GetMapping("/pro-issue-cum-lois-with-count")
    public List<ProIssueCumLoiDTO> getAllProIssueCumLoisWithCount() {
        log.debug("REST request to get all ProIssueCumLois");
        return proIssueCumLoiService.findAllWithCount();
    }
}
