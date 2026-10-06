package vn.nvsoft.web.rest;

import vn.nvsoft.service.GYCLQuyCachNPLService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.GYCLQuyCachNPLDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.GYCLQuyCachNPL}.
 */
@RestController
@RequestMapping("/api")
public class GYCLQuyCachNPLResource {

    private final Logger log = LoggerFactory.getLogger(GYCLQuyCachNPLResource.class);

    private static final String ENTITY_NAME = "gYCLQuyCachNPL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GYCLQuyCachNPLService gYCLQuyCachNPLService;

    public GYCLQuyCachNPLResource(GYCLQuyCachNPLService gYCLQuyCachNPLService) {
        this.gYCLQuyCachNPLService = gYCLQuyCachNPLService;
    }

    /**
     * {@code POST  /gycl-quy-cach-npls} : Create a new gYCLQuyCachNPL.
     *
     * @param gYCLQuyCachNPLDTO the gYCLQuyCachNPLDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gYCLQuyCachNPLDTO, or with status {@code 400 (Bad Request)} if the gYCLQuyCachNPL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gycl-quy-cach-npls")
    public ResponseEntity<GYCLQuyCachNPLDTO> createGYCLQuyCachNPL(@RequestBody GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO) throws URISyntaxException {
        log.debug("REST request to save GYCLQuyCachNPL : {}", gYCLQuyCachNPLDTO);
        if (gYCLQuyCachNPLDTO.getId() != null) {
            throw new BadRequestAlertException("A new gYCLQuyCachNPL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GYCLQuyCachNPLDTO result = gYCLQuyCachNPLService.save(gYCLQuyCachNPLDTO);
        return ResponseEntity.created(new URI("/api/gycl-quy-cach-npls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gycl-quy-cach-npls} : Updates an existing gYCLQuyCachNPL.
     *
     * @param gYCLQuyCachNPLDTO the gYCLQuyCachNPLDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gYCLQuyCachNPLDTO,
     * or with status {@code 400 (Bad Request)} if the gYCLQuyCachNPLDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gYCLQuyCachNPLDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gycl-quy-cach-npls")
    public ResponseEntity<GYCLQuyCachNPLDTO> updateGYCLQuyCachNPL(@RequestBody GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO) throws URISyntaxException {
        log.debug("REST request to update GYCLQuyCachNPL : {}", gYCLQuyCachNPLDTO);
        if (gYCLQuyCachNPLDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GYCLQuyCachNPLDTO result = gYCLQuyCachNPLService.save(gYCLQuyCachNPLDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gYCLQuyCachNPLDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gycl-quy-cach-npls} : get all the gYCLQuyCachNPLS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gYCLQuyCachNPLS in body.
     */
    @GetMapping("/gycl-quy-cach-npls")
    public ResponseEntity<List<GYCLQuyCachNPLDTO>> getAllGYCLQuyCachNPLS(Pageable pageable) {
        log.debug("REST request to get a page of GYCLQuyCachNPLS");
        Page<GYCLQuyCachNPLDTO> page = gYCLQuyCachNPLService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gycl-quy-cach-npls/:id} : get the "id" gYCLQuyCachNPL.
     *
     * @param id the id of the gYCLQuyCachNPLDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gYCLQuyCachNPLDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gycl-quy-cach-npls/{id}")
    public ResponseEntity<GYCLQuyCachNPLDTO> getGYCLQuyCachNPL(@PathVariable Long id) {
        log.debug("REST request to get GYCLQuyCachNPL : {}", id);
        Optional<GYCLQuyCachNPLDTO> gYCLQuyCachNPLDTO = gYCLQuyCachNPLService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gYCLQuyCachNPLDTO);
    }

    /**
     * {@code DELETE  /gycl-quy-cach-npls/:id} : delete the "id" gYCLQuyCachNPL.
     *
     * @param id the id of the gYCLQuyCachNPLDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gycl-quy-cach-npls/{id}")
    public ResponseEntity<Void> deleteGYCLQuyCachNPL(@PathVariable Long id) {
        log.debug("REST request to delete GYCLQuyCachNPL : {}", id);
        gYCLQuyCachNPLService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
