package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProAqlService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProAqlDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProAql}.
 */
@RestController
@RequestMapping("/api")
public class ProAqlResource {

    private final Logger log = LoggerFactory.getLogger(ProAqlResource.class);

    private static final String ENTITY_NAME = "proAql";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProAqlService proAqlService;

    public ProAqlResource(ProAqlService proAqlService) {
        this.proAqlService = proAqlService;
    }

    /**
     * {@code POST  /pro-aqls} : Create a new proAql.
     *
     * @param proAqlDTO the proAqlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proAqlDTO, or with status {@code 400 (Bad Request)} if the proAql has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-aqls")
    public ResponseEntity<ProAqlDTO> createProAql(@RequestBody ProAqlDTO proAqlDTO) throws URISyntaxException {
        log.debug("REST request to save ProAql : {}", proAqlDTO);
        if (proAqlDTO.getId() != null) {
            throw new BadRequestAlertException("A new proAql cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProAqlDTO result = proAqlService.save(proAqlDTO);
        return ResponseEntity.created(new URI("/api/pro-aqls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-aqls} : Updates an existing proAql.
     *
     * @param proAqlDTO the proAqlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proAqlDTO,
     * or with status {@code 400 (Bad Request)} if the proAqlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proAqlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-aqls")
    public ResponseEntity<ProAqlDTO> updateProAql(@RequestBody ProAqlDTO proAqlDTO) throws URISyntaxException {
        log.debug("REST request to update ProAql : {}", proAqlDTO);
        if (proAqlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProAqlDTO result = proAqlService.save(proAqlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proAqlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-aqls} : get all the proAqls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proAqls in body.
     */
    @GetMapping("/pro-aqls")
    public List<ProAqlDTO> getAllProAqls() {
        log.debug("REST request to get all ProAqls");
        return proAqlService.findAll();
    }

    /**
     * {@code GET  /pro-aqls/:id} : get the "id" proAql.
     *
     * @param id the id of the proAqlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proAqlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-aqls/{id}")
    public ResponseEntity<ProAqlDTO> getProAql(@PathVariable Long id) {
        log.debug("REST request to get ProAql : {}", id);
        Optional<ProAqlDTO> proAqlDTO = proAqlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proAqlDTO);
    }

    /**
     * {@code DELETE  /pro-aqls/:id} : delete the "id" proAql.
     *
     * @param id the id of the proAqlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-aqls/{id}")
    public ResponseEntity<Void> deleteProAql(@PathVariable Long id) {
        log.debug("REST request to delete ProAql : {}", id);
        proAqlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
