package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProTenMuaService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProTenMuaDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProTenMua}.
 */
@RestController
@RequestMapping("/api")
public class ProTenMuaResource {

    private final Logger log = LoggerFactory.getLogger(ProTenMuaResource.class);

    private static final String ENTITY_NAME = "proTenMua";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProTenMuaService proTenMuaService;

    public ProTenMuaResource(ProTenMuaService proTenMuaService) {
        this.proTenMuaService = proTenMuaService;
    }

    /**
     * {@code POST  /pro-ten-muas} : Create a new proTenMua.
     *
     * @param proTenMuaDTO the proTenMuaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proTenMuaDTO, or with status {@code 400 (Bad Request)} if the proTenMua has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-ten-muas")
    public ResponseEntity<ProTenMuaDTO> createProTenMua(@RequestBody ProTenMuaDTO proTenMuaDTO) throws URISyntaxException {
        log.debug("REST request to save ProTenMua : {}", proTenMuaDTO);
        if (proTenMuaDTO.getId() != null) {
            throw new BadRequestAlertException("A new proTenMua cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProTenMuaDTO result = proTenMuaService.save(proTenMuaDTO);
        return ResponseEntity.created(new URI("/api/pro-ten-muas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-ten-muas} : Updates an existing proTenMua.
     *
     * @param proTenMuaDTO the proTenMuaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proTenMuaDTO,
     * or with status {@code 400 (Bad Request)} if the proTenMuaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proTenMuaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-ten-muas")
    public ResponseEntity<ProTenMuaDTO> updateProTenMua(@RequestBody ProTenMuaDTO proTenMuaDTO) throws URISyntaxException {
        log.debug("REST request to update ProTenMua : {}", proTenMuaDTO);
        if (proTenMuaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProTenMuaDTO result = proTenMuaService.save(proTenMuaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proTenMuaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-ten-muas} : get all the proTenMuas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proTenMuas in body.
     */
    @GetMapping("/pro-ten-muas")
    public ResponseEntity<List<ProTenMuaDTO>> getAllProTenMuas(Pageable pageable) {
        log.debug("REST request to get a page of ProTenMuas");
        Page<ProTenMuaDTO> page = proTenMuaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-ten-muas/:id} : get the "id" proTenMua.
     *
     * @param id the id of the proTenMuaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proTenMuaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-ten-muas/{id}")
    public ResponseEntity<ProTenMuaDTO> getProTenMua(@PathVariable Long id) {
        log.debug("REST request to get ProTenMua : {}", id);
        Optional<ProTenMuaDTO> proTenMuaDTO = proTenMuaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proTenMuaDTO);
    }

    /**
     * {@code DELETE  /pro-ten-muas/:id} : delete the "id" proTenMua.
     *
     * @param id the id of the proTenMuaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-ten-muas/{id}")
    public ResponseEntity<Void> deleteProTenMua(@PathVariable Long id) {
        log.debug("REST request to delete ProTenMua : {}", id);
        proTenMuaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
