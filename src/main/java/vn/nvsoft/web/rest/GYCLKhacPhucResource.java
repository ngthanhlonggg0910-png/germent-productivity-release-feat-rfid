package vn.nvsoft.web.rest;

import vn.nvsoft.service.GYCLKhacPhucService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.GYCLKhacPhucDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.GYCLKhacPhuc}.
 */
@RestController
@RequestMapping("/api")
public class GYCLKhacPhucResource {

    private final Logger log = LoggerFactory.getLogger(GYCLKhacPhucResource.class);

    private static final String ENTITY_NAME = "gYCLKhacPhuc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GYCLKhacPhucService gYCLKhacPhucService;

    public GYCLKhacPhucResource(GYCLKhacPhucService gYCLKhacPhucService) {
        this.gYCLKhacPhucService = gYCLKhacPhucService;
    }

    /**
     * {@code POST  /gycl-khac-phucs} : Create a new gYCLKhacPhuc.
     *
     * @param gYCLKhacPhucDTO the gYCLKhacPhucDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gYCLKhacPhucDTO, or with status {@code 400 (Bad Request)} if the gYCLKhacPhuc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gycl-khac-phucs")
    public ResponseEntity<GYCLKhacPhucDTO> createGYCLKhacPhuc(@RequestBody GYCLKhacPhucDTO gYCLKhacPhucDTO) throws URISyntaxException {
        log.debug("REST request to save GYCLKhacPhuc : {}", gYCLKhacPhucDTO);
        if (gYCLKhacPhucDTO.getId() != null) {
            throw new BadRequestAlertException("A new gYCLKhacPhuc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GYCLKhacPhucDTO result = gYCLKhacPhucService.save(gYCLKhacPhucDTO);
        return ResponseEntity.created(new URI("/api/gycl-khac-phucs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gycl-khac-phucs} : Updates an existing gYCLKhacPhuc.
     *
     * @param gYCLKhacPhucDTO the gYCLKhacPhucDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gYCLKhacPhucDTO,
     * or with status {@code 400 (Bad Request)} if the gYCLKhacPhucDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gYCLKhacPhucDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gycl-khac-phucs")
    public ResponseEntity<GYCLKhacPhucDTO> updateGYCLKhacPhuc(@RequestBody GYCLKhacPhucDTO gYCLKhacPhucDTO) throws URISyntaxException {
        log.debug("REST request to update GYCLKhacPhuc : {}", gYCLKhacPhucDTO);
        if (gYCLKhacPhucDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GYCLKhacPhucDTO result = gYCLKhacPhucService.save(gYCLKhacPhucDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gYCLKhacPhucDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gycl-khac-phucs} : get all the gYCLKhacPhucs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gYCLKhacPhucs in body.
     */
    @GetMapping("/gycl-khac-phucs")
    public ResponseEntity<List<GYCLKhacPhucDTO>> getAllGYCLKhacPhucs(Pageable pageable) {
        log.debug("REST request to get a page of GYCLKhacPhucs");
        Page<GYCLKhacPhucDTO> page = gYCLKhacPhucService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gycl-khac-phucs/:id} : get the "id" gYCLKhacPhuc.
     *
     * @param id the id of the gYCLKhacPhucDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gYCLKhacPhucDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gycl-khac-phucs/{id}")
    public ResponseEntity<GYCLKhacPhucDTO> getGYCLKhacPhuc(@PathVariable Long id) {
        log.debug("REST request to get GYCLKhacPhuc : {}", id);
        Optional<GYCLKhacPhucDTO> gYCLKhacPhucDTO = gYCLKhacPhucService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gYCLKhacPhucDTO);
    }

    /**
     * {@code DELETE  /gycl-khac-phucs/:id} : delete the "id" gYCLKhacPhuc.
     *
     * @param id the id of the gYCLKhacPhucDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gycl-khac-phucs/{id}")
    public ResponseEntity<Void> deleteGYCLKhacPhuc(@PathVariable Long id) {
        log.debug("REST request to delete GYCLKhacPhuc : {}", id);
        gYCLKhacPhucService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
