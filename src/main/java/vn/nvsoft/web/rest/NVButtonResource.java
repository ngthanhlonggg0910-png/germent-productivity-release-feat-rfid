package vn.nvsoft.web.rest;

import vn.nvsoft.service.NVButtonService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.NVButtonDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.NVButton}.
 */
@RestController
@RequestMapping("/api")
public class NVButtonResource {

    private final Logger log = LoggerFactory.getLogger(NVButtonResource.class);

    private static final String ENTITY_NAME = "nVButton";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NVButtonService nVButtonService;

    public NVButtonResource(NVButtonService nVButtonService) {
        this.nVButtonService = nVButtonService;
    }

    /**
     * {@code POST  /nv-buttons} : Create a new nVButton.
     *
     * @param nVButtonDTO the nVButtonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nVButtonDTO, or with status {@code 400 (Bad Request)} if the nVButton has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nv-buttons")
    public ResponseEntity<NVButtonDTO> createNVButton(@Valid @RequestBody NVButtonDTO nVButtonDTO) throws URISyntaxException {
        log.debug("REST request to save NVButton : {}", nVButtonDTO);
        if (nVButtonDTO.getId() != null) {
            throw new BadRequestAlertException("A new nVButton cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NVButtonDTO result = nVButtonService.save(nVButtonDTO);
        return ResponseEntity.created(new URI("/api/nv-buttons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nv-buttons} : Updates an existing nVButton.
     *
     * @param nVButtonDTO the nVButtonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nVButtonDTO,
     * or with status {@code 400 (Bad Request)} if the nVButtonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nVButtonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nv-buttons")
    public ResponseEntity<NVButtonDTO> updateNVButton(@Valid @RequestBody NVButtonDTO nVButtonDTO) throws URISyntaxException {
        log.debug("REST request to update NVButton : {}", nVButtonDTO);
        if (nVButtonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NVButtonDTO result = nVButtonService.save(nVButtonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nVButtonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nv-buttons} : get all the nVButtons.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nVButtons in body.
     */
    @GetMapping("/nv-buttons")
    public ResponseEntity<List<NVButtonDTO>> getAllNVButtons(Pageable pageable) {
        log.debug("REST request to get a page of NVButtons");
        Page<NVButtonDTO> page = nVButtonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nv-buttons/:id} : get the "id" nVButton.
     *
     * @param id the id of the nVButtonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nVButtonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nv-buttons/{id}")
    public ResponseEntity<NVButtonDTO> getNVButton(@PathVariable Long id) {
        log.debug("REST request to get NVButton : {}", id);
        Optional<NVButtonDTO> nVButtonDTO = nVButtonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nVButtonDTO);
    }

    /**
     * {@code DELETE  /nv-buttons/:id} : delete the "id" nVButton.
     *
     * @param id the id of the nVButtonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nv-buttons/{id}")
    public ResponseEntity<Void> deleteNVButton(@PathVariable Long id) {
        log.debug("REST request to delete NVButton : {}", id);
        nVButtonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
