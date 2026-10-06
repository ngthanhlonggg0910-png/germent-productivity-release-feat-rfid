package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProCongTyService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProCongTyDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProCongTy}.
 */
@RestController
@RequestMapping("/api")
public class ProCongTyResource {

    private final Logger log = LoggerFactory.getLogger(ProCongTyResource.class);

    private static final String ENTITY_NAME = "proCongTy";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProCongTyService proCongTyService;

    public ProCongTyResource(ProCongTyService proCongTyService) {
        this.proCongTyService = proCongTyService;
    }

    /**
     * {@code POST  /pro-cong-ties} : Create a new proCongTy.
     *
     * @param proCongTyDTO the proCongTyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proCongTyDTO, or with status {@code 400 (Bad Request)} if the proCongTy has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-cong-ties")
    public ResponseEntity<ProCongTyDTO> createProCongTy(@RequestBody ProCongTyDTO proCongTyDTO) throws URISyntaxException {
        log.debug("REST request to save ProCongTy : {}", proCongTyDTO);
        if (proCongTyDTO.getId() != null) {
            throw new BadRequestAlertException("A new proCongTy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProCongTyDTO result = proCongTyService.save(proCongTyDTO);
        return ResponseEntity.created(new URI("/api/pro-cong-ties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-cong-ties} : Updates an existing proCongTy.
     *
     * @param proCongTyDTO the proCongTyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proCongTyDTO,
     * or with status {@code 400 (Bad Request)} if the proCongTyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proCongTyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-cong-ties")
    public ResponseEntity<ProCongTyDTO> updateProCongTy(@RequestBody ProCongTyDTO proCongTyDTO) throws URISyntaxException {
        log.debug("REST request to update ProCongTy : {}", proCongTyDTO);
        if (proCongTyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProCongTyDTO result = proCongTyService.save(proCongTyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proCongTyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-cong-ties} : get all the proCongTies.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proCongTies in body.
     */
    @GetMapping("/pro-cong-ties")
    public ResponseEntity<List<ProCongTyDTO>> getAllProCongTies(Pageable pageable) {
        log.debug("REST request to get a page of ProCongTies");
        Page<ProCongTyDTO> page = proCongTyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-cong-ties/:id} : get the "id" proCongTy.
     *
     * @param id the id of the proCongTyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proCongTyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-cong-ties/{id}")
    public ResponseEntity<ProCongTyDTO> getProCongTy(@PathVariable Long id) {
        log.debug("REST request to get ProCongTy : {}", id);
        Optional<ProCongTyDTO> proCongTyDTO = proCongTyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proCongTyDTO);
    }

    /**
     * {@code DELETE  /pro-cong-ties/:id} : delete the "id" proCongTy.
     *
     * @param id the id of the proCongTyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-cong-ties/{id}")
    public ResponseEntity<Void> deleteProCongTy(@PathVariable Long id) {
        log.debug("REST request to delete ProCongTy : {}", id);
        proCongTyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
