package vn.nvsoft.web.rest;

import vn.nvsoft.service.LibSoLuongPoKiemService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.LibSoLuongPoKiemDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.LibSoLuongPoKiem}.
 */
@RestController
@RequestMapping("/api")
public class LibSoLuongPoKiemResource {

    private final Logger log = LoggerFactory.getLogger(LibSoLuongPoKiemResource.class);

    private static final String ENTITY_NAME = "libSoLuongPoKiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LibSoLuongPoKiemService libSoLuongPoKiemService;

    public LibSoLuongPoKiemResource(LibSoLuongPoKiemService libSoLuongPoKiemService) {
        this.libSoLuongPoKiemService = libSoLuongPoKiemService;
    }

    /**
     * {@code POST  /lib-so-luong-po-kiems} : Create a new libSoLuongPoKiem.
     *
     * @param libSoLuongPoKiemDTO the libSoLuongPoKiemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new libSoLuongPoKiemDTO, or with status {@code 400 (Bad Request)} if the libSoLuongPoKiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lib-so-luong-po-kiems")
    public ResponseEntity<LibSoLuongPoKiemDTO> createLibSoLuongPoKiem(@RequestBody LibSoLuongPoKiemDTO libSoLuongPoKiemDTO) throws URISyntaxException {
        log.debug("REST request to save LibSoLuongPoKiem : {}", libSoLuongPoKiemDTO);
        if (libSoLuongPoKiemDTO.getId() != null) {
            throw new BadRequestAlertException("A new libSoLuongPoKiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LibSoLuongPoKiemDTO result = libSoLuongPoKiemService.save(libSoLuongPoKiemDTO);
        return ResponseEntity.created(new URI("/api/lib-so-luong-po-kiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lib-so-luong-po-kiems} : Updates an existing libSoLuongPoKiem.
     *
     * @param libSoLuongPoKiemDTO the libSoLuongPoKiemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated libSoLuongPoKiemDTO,
     * or with status {@code 400 (Bad Request)} if the libSoLuongPoKiemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the libSoLuongPoKiemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lib-so-luong-po-kiems")
    public ResponseEntity<LibSoLuongPoKiemDTO> updateLibSoLuongPoKiem(@RequestBody LibSoLuongPoKiemDTO libSoLuongPoKiemDTO) throws URISyntaxException {
        log.debug("REST request to update LibSoLuongPoKiem : {}", libSoLuongPoKiemDTO);
        if (libSoLuongPoKiemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LibSoLuongPoKiemDTO result = libSoLuongPoKiemService.save(libSoLuongPoKiemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, libSoLuongPoKiemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lib-so-luong-po-kiems} : get all the libSoLuongPoKiems.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of libSoLuongPoKiems in body.
     */
    @GetMapping("/lib-so-luong-po-kiems")
    public List<LibSoLuongPoKiemDTO> getAllLibSoLuongPoKiems() {
        log.debug("REST request to get all LibSoLuongPoKiems");
        return libSoLuongPoKiemService.findAll();
    }

    /**
     * {@code GET  /lib-so-luong-po-kiems/:id} : get the "id" libSoLuongPoKiem.
     *
     * @param id the id of the libSoLuongPoKiemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the libSoLuongPoKiemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lib-so-luong-po-kiems/{id}")
    public ResponseEntity<LibSoLuongPoKiemDTO> getLibSoLuongPoKiem(@PathVariable Long id) {
        log.debug("REST request to get LibSoLuongPoKiem : {}", id);
        Optional<LibSoLuongPoKiemDTO> libSoLuongPoKiemDTO = libSoLuongPoKiemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(libSoLuongPoKiemDTO);
    }

    /**
     * {@code DELETE  /lib-so-luong-po-kiems/:id} : delete the "id" libSoLuongPoKiem.
     *
     * @param id the id of the libSoLuongPoKiemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lib-so-luong-po-kiems/{id}")
    public ResponseEntity<Void> deleteLibSoLuongPoKiem(@PathVariable Long id) {
        log.debug("REST request to delete LibSoLuongPoKiem : {}", id);
        libSoLuongPoKiemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
