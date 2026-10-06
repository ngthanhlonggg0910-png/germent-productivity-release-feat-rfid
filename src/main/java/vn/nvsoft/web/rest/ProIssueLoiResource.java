package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProIssueLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProIssueLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProIssueLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProIssueLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProIssueLoiResource.class);

    private static final String ENTITY_NAME = "proIssueLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProIssueLoiService proIssueLoiService;

    public ProIssueLoiResource(ProIssueLoiService proIssueLoiService) {
        this.proIssueLoiService = proIssueLoiService;
    }

    /**
     * {@code POST  /pro-issue-lois} : Create a new proIssueLoi.
     *
     * @param proIssueLoiDTO the proIssueLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proIssueLoiDTO, or with status {@code 400 (Bad Request)} if the proIssueLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-issue-lois")
    public ResponseEntity<ProIssueLoiDTO> createProIssueLoi(@RequestBody ProIssueLoiDTO proIssueLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProIssueLoi : {}", proIssueLoiDTO);
        if (proIssueLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proIssueLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProIssueLoiDTO result = proIssueLoiService.save(proIssueLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-issue-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-issue-lois} : Updates an existing proIssueLoi.
     *
     * @param proIssueLoiDTO the proIssueLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proIssueLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proIssueLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proIssueLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-issue-lois")
    public ResponseEntity<ProIssueLoiDTO> updateProIssueLoi(@RequestBody ProIssueLoiDTO proIssueLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProIssueLoi : {}", proIssueLoiDTO);
        if (proIssueLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProIssueLoiDTO result = proIssueLoiService.save(proIssueLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proIssueLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-issue-lois} : get all the proIssueLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssueLois in body.
     */
    @GetMapping("/pro-issue-lois")
    public List<ProIssueLoiDTO> getAllProIssueLois() {
        log.debug("REST request to get all ProIssueLois");
        return proIssueLoiService.findAll();
    }

    /**
     * {@code GET  /pro-issue-lois/:id} : get the "id" proIssueLoi.
     *
     * @param id the id of the proIssueLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proIssueLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-issue-lois/{id}")
    public ResponseEntity<ProIssueLoiDTO> getProIssueLoi(@PathVariable Long id) {
        log.debug("REST request to get ProIssueLoi : {}", id);
        Optional<ProIssueLoiDTO> proIssueLoiDTO = proIssueLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proIssueLoiDTO);
    }

    /**
     * {@code DELETE  /pro-issue-lois/:id} : delete the "id" proIssueLoi.
     *
     * @param id the id of the proIssueLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-issue-lois/{id}")
    public ResponseEntity<Void> deleteProIssueLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProIssueLoi : {}", id);
        proIssueLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-issue-lois/by-cum-loi-id/} : get all the proIssueLois by proIssueCumLoi ID
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssueLois in body.
     */
    @GetMapping("/pro-issue-lois/by-cum-loi-id/{id}")
    public List<ProIssueLoiDTO> getAllProIssueLoisByProIssueCumLoi(@PathVariable Long id) {
        log.debug("REST request to get all ProIssueLois by pro issue cum loi {}", id);
        return proIssueLoiService.findAllByProIssueCumLoi(id);
    }
}
