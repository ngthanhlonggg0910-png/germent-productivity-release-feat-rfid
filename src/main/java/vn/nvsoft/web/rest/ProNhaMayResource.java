package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProNhaMayService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProNhaMayDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProNhaMay}.
 */
@RestController
@RequestMapping("/api")
public class ProNhaMayResource {

    private final Logger log = LoggerFactory.getLogger(ProNhaMayResource.class);

    private static final String ENTITY_NAME = "proNhaMay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProNhaMayService proNhaMayService;

    public ProNhaMayResource(ProNhaMayService proNhaMayService) {
        this.proNhaMayService = proNhaMayService;
    }

    /**
     * {@code POST  /pro-nha-mays} : Create a new proNhaMay.
     *
     * @param proNhaMayDTO the proNhaMayDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNhaMayDTO, or with status {@code 400 (Bad Request)} if the proNhaMay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-nha-mays")
    public ResponseEntity<ProNhaMayDTO> createProNhaMay(@RequestBody ProNhaMayDTO proNhaMayDTO) throws URISyntaxException {
        log.debug("REST request to save ProNhaMay : {}", proNhaMayDTO);
        if (proNhaMayDTO.getId() != null) {
            throw new BadRequestAlertException("A new proNhaMay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProNhaMayDTO result = proNhaMayService.save(proNhaMayDTO);
        return ResponseEntity.created(new URI("/api/pro-nha-mays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-nha-mays} : Updates an existing proNhaMay.
     *
     * @param proNhaMayDTO the proNhaMayDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNhaMayDTO,
     * or with status {@code 400 (Bad Request)} if the proNhaMayDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNhaMayDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nha-mays")
    public ResponseEntity<ProNhaMayDTO> updateProNhaMay(@RequestBody ProNhaMayDTO proNhaMayDTO) throws URISyntaxException {
        log.debug("REST request to update ProNhaMay : {}", proNhaMayDTO);
        if (proNhaMayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProNhaMayDTO result = proNhaMayService.save(proNhaMayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proNhaMayDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-nha-mays} : get all the proNhaMays.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNhaMays in body.
     */
    @GetMapping("/pro-nha-mays")
    public ResponseEntity<List<ProNhaMayDTO>> getAllProNhaMays(Pageable pageable) {
        log.debug("REST request to get a page of ProNhaMays");
        Page<ProNhaMayDTO> page = proNhaMayService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-nha-mays/:id} : get the "id" proNhaMay.
     *
     * @param id the id of the proNhaMayDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNhaMayDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-nha-mays/{id}")
    public ResponseEntity<ProNhaMayDTO> getProNhaMay(@PathVariable Long id) {
        log.debug("REST request to get ProNhaMay : {}", id);
        Optional<ProNhaMayDTO> proNhaMayDTO = proNhaMayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proNhaMayDTO);
    }

    /**
     * {@code DELETE  /pro-nha-mays/:id} : delete the "id" proNhaMay.
     *
     * @param id the id of the proNhaMayDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-nha-mays/{id}")
    public ResponseEntity<Void> deleteProNhaMay(@PathVariable Long id) {
        log.debug("REST request to delete ProNhaMay : {}", id);
        proNhaMayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
