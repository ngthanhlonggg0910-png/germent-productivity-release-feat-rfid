package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProIssueService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProIssueDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProIssue}.
 */
@RestController
@RequestMapping("/api")
public class ProIssueResource {

    private final Logger log = LoggerFactory.getLogger(ProIssueResource.class);

    private static final String ENTITY_NAME = "proIssue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProIssueService proIssueService;

    public ProIssueResource(ProIssueService proIssueService) {
        this.proIssueService = proIssueService;
    }

    /**
     * {@code POST  /pro-issues} : Create a new proIssue.
     *
     * @param proIssueDTO the proIssueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proIssueDTO, or with status {@code 400 (Bad Request)} if the proIssue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-issues")
    public ResponseEntity<ProIssueDTO> createProIssue(@RequestBody ProIssueDTO proIssueDTO) throws URISyntaxException {
        log.debug("REST request to save ProIssue : {}", proIssueDTO);
        if (proIssueDTO.getId() != null) {
            throw new BadRequestAlertException("A new proIssue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProIssueDTO result = proIssueService.save(proIssueDTO);
        return ResponseEntity.created(new URI("/api/pro-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /pro-issues/by-user} : Create a new proIssue.
     *
     * @param proIssueDTO the proIssueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proIssueDTO, or with status {@code 400 (Bad Request)} if the proIssue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-issues/by-user")
    public ResponseEntity<ProIssueDTO> createProIssueByUser(@RequestBody ProIssueDTO proIssueDTO) throws URISyntaxException {
        log.debug("REST request to save ProIssue : {}", proIssueDTO);
        if (proIssueDTO.getId() != null) {
            throw new BadRequestAlertException("A new proIssue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProIssueDTO result = proIssueService.saveByUser(proIssueDTO);
        return ResponseEntity.created(new URI("/api/pro-issues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-issues} : Updates an existing proIssue.
     *
     * @param proIssueDTO the proIssueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proIssueDTO,
     * or with status {@code 400 (Bad Request)} if the proIssueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proIssueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-issues")
    public ResponseEntity<ProIssueDTO> updateProIssue(@RequestBody ProIssueDTO proIssueDTO) throws URISyntaxException {
        log.debug("REST request to update ProIssue : {}", proIssueDTO);
        if (proIssueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProIssueDTO result = proIssueService.save(proIssueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proIssueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-issues/by-user} : Updates an existing proIssue.
     *
     * @param proIssueDTO the proIssueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proIssueDTO,
     * or with status {@code 400 (Bad Request)} if the proIssueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proIssueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-issues/by-user")
    public ResponseEntity<ProIssueDTO> updateProIssueByUser(@RequestBody ProIssueDTO proIssueDTO) throws URISyntaxException {
        log.debug("REST request to update ProIssue : {}", proIssueDTO);
        if (proIssueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProIssueDTO result = proIssueService.saveByUser(proIssueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proIssueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-issues} : get all the proIssues.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssues in body.
     */
    @GetMapping("/pro-issues")
    public List<ProIssueDTO> getAllProIssues() {
        log.debug("REST request to get all ProIssues");
        return proIssueService.findAll();
    }

    /**
     * {@code GET  /pro-issues/:id} : get the "id" proIssue.
     *
     * @param id the id of the proIssueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proIssueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-issues/{id}")
    public ResponseEntity<ProIssueDTO> getProIssue(@PathVariable Long id) {
        log.debug("REST request to get ProIssue : {}", id);
        Optional<ProIssueDTO> proIssueDTO = proIssueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proIssueDTO);
    }

    /**
     * {@code DELETE  /pro-issues/:id} : delete the "id" proIssue.
     *
     * @param id the id of the proIssueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-issues/{id}")
    public ResponseEntity<Void> deleteProIssue(@PathVariable Long id) {
        log.debug("REST request to delete ProIssue : {}", id);
        proIssueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-issues/to-day} : get all the proIssues on to day.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proIssues in body.
     */
    @GetMapping("/pro-issues/to-day")
    public List<ProIssueDTO> getAllProIssuesToDay() {
        log.debug("REST request to get all ProIssues in progress or today");
        return proIssueService.findAllOnToDayOrInProgress();
    }
}
