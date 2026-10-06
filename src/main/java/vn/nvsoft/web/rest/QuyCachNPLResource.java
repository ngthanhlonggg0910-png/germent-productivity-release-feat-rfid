package vn.nvsoft.web.rest;

import vn.nvsoft.service.QuyCachNPLService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.QuyCachNPLDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.QuyCachNPL}.
 */
@RestController
@RequestMapping("/api")
public class QuyCachNPLResource {

    private final Logger log = LoggerFactory.getLogger(QuyCachNPLResource.class);

    private static final String ENTITY_NAME = "quyCachNPL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuyCachNPLService quyCachNPLService;

    public QuyCachNPLResource(QuyCachNPLService quyCachNPLService) {
        this.quyCachNPLService = quyCachNPLService;
    }

    /**
     * {@code POST  /quy-cach-npls} : Create a new quyCachNPL.
     *
     * @param quyCachNPLDTO the quyCachNPLDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quyCachNPLDTO, or with status {@code 400 (Bad Request)} if the quyCachNPL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quy-cach-npls")
    public ResponseEntity<QuyCachNPLDTO> createQuyCachNPL(@RequestBody QuyCachNPLDTO quyCachNPLDTO) throws URISyntaxException {
        log.debug("REST request to save QuyCachNPL : {}", quyCachNPLDTO);
        if (quyCachNPLDTO.getId() != null) {
            throw new BadRequestAlertException("A new quyCachNPL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuyCachNPLDTO result = quyCachNPLService.save(quyCachNPLDTO);
        return ResponseEntity.created(new URI("/api/quy-cach-npls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quy-cach-npls} : Updates an existing quyCachNPL.
     *
     * @param quyCachNPLDTO the quyCachNPLDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quyCachNPLDTO,
     * or with status {@code 400 (Bad Request)} if the quyCachNPLDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quyCachNPLDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quy-cach-npls")
    public ResponseEntity<QuyCachNPLDTO> updateQuyCachNPL(@RequestBody QuyCachNPLDTO quyCachNPLDTO) throws URISyntaxException {
        log.debug("REST request to update QuyCachNPL : {}", quyCachNPLDTO);
        if (quyCachNPLDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuyCachNPLDTO result = quyCachNPLService.save(quyCachNPLDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quyCachNPLDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quy-cach-npls} : get all the quyCachNPLS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quyCachNPLS in body.
     */
    @GetMapping("/quy-cach-npls")
    public ResponseEntity<List<QuyCachNPLDTO>> getAllQuyCachNPLS(Pageable pageable) {
        log.debug("REST request to get a page of QuyCachNPLS");
        Page<QuyCachNPLDTO> page = quyCachNPLService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quy-cach-npls/:id} : get the "id" quyCachNPL.
     *
     * @param id the id of the quyCachNPLDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quyCachNPLDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quy-cach-npls/{id}")
    public ResponseEntity<QuyCachNPLDTO> getQuyCachNPL(@PathVariable Long id) {
        log.debug("REST request to get QuyCachNPL : {}", id);
        Optional<QuyCachNPLDTO> quyCachNPLDTO = quyCachNPLService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quyCachNPLDTO);
    }

    /**
     * {@code DELETE  /quy-cach-npls/:id} : delete the "id" quyCachNPL.
     *
     * @param id the id of the quyCachNPLDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quy-cach-npls/{id}")
    public ResponseEntity<Void> deleteQuyCachNPL(@PathVariable Long id) {
        log.debug("REST request to delete QuyCachNPL : {}", id);
        quyCachNPLService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
