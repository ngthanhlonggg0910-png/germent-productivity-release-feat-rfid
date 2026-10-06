package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProCheckPointService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProCheckPointDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProCheckPoint}.
 */
@RestController
@RequestMapping("/api")
public class ProCheckPointResource {

    private final Logger log = LoggerFactory.getLogger(ProCheckPointResource.class);

    private static final String ENTITY_NAME = "proCheckPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProCheckPointService proCheckPointService;

    public ProCheckPointResource(ProCheckPointService proCheckPointService) {
        this.proCheckPointService = proCheckPointService;
    }

    /**
     * {@code POST  /pro-check-points} : Create a new proCheckPoint.
     *
     * @param proCheckPointDTO the proCheckPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proCheckPointDTO, or with status {@code 400 (Bad Request)} if the proCheckPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-check-points")
    public ResponseEntity<ProCheckPointDTO> createProCheckPoint(@RequestBody ProCheckPointDTO proCheckPointDTO) throws URISyntaxException {
        log.debug("REST request to save ProCheckPoint : {}", proCheckPointDTO);
        if (proCheckPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new proCheckPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProCheckPointDTO result = proCheckPointService.save(proCheckPointDTO);
        return ResponseEntity.created(new URI("/api/pro-check-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-check-points} : Updates an existing proCheckPoint.
     *
     * @param proCheckPointDTO the proCheckPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proCheckPointDTO,
     * or with status {@code 400 (Bad Request)} if the proCheckPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proCheckPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-check-points")
    public ResponseEntity<ProCheckPointDTO> updateProCheckPoint(@RequestBody ProCheckPointDTO proCheckPointDTO) throws URISyntaxException {
        log.debug("REST request to update ProCheckPoint : {}", proCheckPointDTO);
        if (proCheckPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProCheckPointDTO result = proCheckPointService.save(proCheckPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proCheckPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-check-points} : get all the proCheckPoints.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proCheckPoints in body.
     */
    @GetMapping("/pro-check-points")
    public ResponseEntity<List<ProCheckPointDTO>> getAllProCheckPoints(Pageable pageable) {
        log.debug("REST request to get a page of ProCheckPoints");
        Page<ProCheckPointDTO> page = proCheckPointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-check-points/:id} : get the "id" proCheckPoint.
     *
     * @param id the id of the proCheckPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proCheckPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-check-points/{id}")
    public ResponseEntity<ProCheckPointDTO> getProCheckPoint(@PathVariable Long id) {
        log.debug("REST request to get ProCheckPoint : {}", id);
        Optional<ProCheckPointDTO> proCheckPointDTO = proCheckPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proCheckPointDTO);
    }

    /**
     * {@code DELETE  /pro-check-points/:id} : delete the "id" proCheckPoint.
     *
     * @param id the id of the proCheckPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-check-points/{id}")
    public ResponseEntity<Void> deleteProCheckPoint(@PathVariable Long id) {
        log.debug("REST request to delete ProCheckPoint : {}", id);
        proCheckPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
