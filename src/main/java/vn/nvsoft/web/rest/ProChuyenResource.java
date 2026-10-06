package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProChuyenService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProChuyenDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProChuyen}.
 */
@RestController
@RequestMapping("/api")
public class ProChuyenResource {

    private final Logger log = LoggerFactory.getLogger(ProChuyenResource.class);

    private static final String ENTITY_NAME = "proChuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProChuyenService proChuyenService;

    public ProChuyenResource(ProChuyenService proChuyenService) {
        this.proChuyenService = proChuyenService;
    }

    /**
     * {@code POST  /pro-chuyens} : Create a new proChuyen.
     *
     * @param proChuyenDTO the proChuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proChuyenDTO, or with status {@code 400 (Bad Request)} if the proChuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-chuyens")
    public ResponseEntity<ProChuyenDTO> createProChuyen(@RequestBody ProChuyenDTO proChuyenDTO) throws URISyntaxException {
        log.debug("REST request to save ProChuyen : {}", proChuyenDTO);
        if (proChuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new proChuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProChuyenDTO result = proChuyenService.save(proChuyenDTO);
        return ResponseEntity.created(new URI("/api/pro-chuyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-chuyens} : Updates an existing proChuyen.
     *
     * @param proChuyenDTO the proChuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proChuyenDTO,
     * or with status {@code 400 (Bad Request)} if the proChuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proChuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-chuyens")
    public ResponseEntity<ProChuyenDTO> updateProChuyen(@RequestBody ProChuyenDTO proChuyenDTO) throws URISyntaxException {
        log.debug("REST request to update ProChuyen : {}", proChuyenDTO);
        if (proChuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProChuyenDTO result = proChuyenService.save(proChuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proChuyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-chuyens} : get all the proChuyens.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proChuyens in body.
     */
    @GetMapping("/pro-chuyens")
    public ResponseEntity<List<ProChuyenDTO>> getAllProChuyens(Pageable pageable) {
        log.debug("REST request to get a page of ProChuyens");
        Page<ProChuyenDTO> page = proChuyenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-chuyens/:id} : get the "id" proChuyen.
     *
     * @param id the id of the proChuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proChuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-chuyens/{id}")
    public ResponseEntity<ProChuyenDTO> getProChuyen(@PathVariable Long id) {
        log.debug("REST request to get ProChuyen : {}", id);
        Optional<ProChuyenDTO> proChuyenDTO = proChuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proChuyenDTO);
    }

    /**
     * {@code DELETE  /pro-chuyens/:id} : delete the "id" proChuyen.
     *
     * @param id the id of the proChuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-chuyens/{id}")
    public ResponseEntity<Void> deleteProChuyen(@PathVariable Long id) {
        log.debug("REST request to delete ProChuyen : {}", id);
        proChuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
