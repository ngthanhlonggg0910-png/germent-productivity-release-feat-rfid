package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProCongDoanService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProCongDoanDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProCongDoan}.
 */
@RestController
@RequestMapping("/api")
public class ProCongDoanResource {

    private final Logger log = LoggerFactory.getLogger(ProCongDoanResource.class);

    private static final String ENTITY_NAME = "proCongDoan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProCongDoanService proCongDoanService;

    public ProCongDoanResource(ProCongDoanService proCongDoanService) {
        this.proCongDoanService = proCongDoanService;
    }

    /**
     * {@code POST  /pro-cong-doans} : Create a new proCongDoan.
     *
     * @param proCongDoanDTO the proCongDoanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proCongDoanDTO, or with status {@code 400 (Bad Request)} if the proCongDoan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-cong-doans")
    public ResponseEntity<ProCongDoanDTO> createProCongDoan(@RequestBody ProCongDoanDTO proCongDoanDTO) throws URISyntaxException {
        log.debug("REST request to save ProCongDoan : {}", proCongDoanDTO);
        if (proCongDoanDTO.getId() != null) {
            throw new BadRequestAlertException("A new proCongDoan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProCongDoanDTO result = proCongDoanService.save(proCongDoanDTO);
        return ResponseEntity.created(new URI("/api/pro-cong-doans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-cong-doans} : Updates an existing proCongDoan.
     *
     * @param proCongDoanDTO the proCongDoanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proCongDoanDTO,
     * or with status {@code 400 (Bad Request)} if the proCongDoanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proCongDoanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-cong-doans")
    public ResponseEntity<ProCongDoanDTO> updateProCongDoan(@RequestBody ProCongDoanDTO proCongDoanDTO) throws URISyntaxException {
        log.debug("REST request to update ProCongDoan : {}", proCongDoanDTO);
        if (proCongDoanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProCongDoanDTO result = proCongDoanService.save(proCongDoanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proCongDoanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-cong-doans} : get all the proCongDoans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proCongDoans in body.
     */
    @GetMapping("/pro-cong-doans")
    public ResponseEntity<List<ProCongDoanDTO>> getAllProCongDoans(Pageable pageable) {
        log.debug("REST request to get a page of ProCongDoans");
        Page<ProCongDoanDTO> page = proCongDoanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-cong-doans/:id} : get the "id" proCongDoan.
     *
     * @param id the id of the proCongDoanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proCongDoanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-cong-doans/{id}")
    public ResponseEntity<ProCongDoanDTO> getProCongDoan(@PathVariable Long id) {
        log.debug("REST request to get ProCongDoan : {}", id);
        Optional<ProCongDoanDTO> proCongDoanDTO = proCongDoanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proCongDoanDTO);
    }

    /**
     * {@code DELETE  /pro-cong-doans/:id} : delete the "id" proCongDoan.
     *
     * @param id the id of the proCongDoanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-cong-doans/{id}")
    public ResponseEntity<Void> deleteProCongDoan(@PathVariable Long id) {
        log.debug("REST request to delete ProCongDoan : {}", id);
        proCongDoanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
