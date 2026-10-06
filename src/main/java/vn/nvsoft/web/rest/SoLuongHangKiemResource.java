package vn.nvsoft.web.rest;

import vn.nvsoft.service.SoLuongHangKiemService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.SoLuongHangKiemDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.SoLuongHangKiem}.
 */
@RestController
@RequestMapping("/api")
public class SoLuongHangKiemResource {

    private final Logger log = LoggerFactory.getLogger(SoLuongHangKiemResource.class);

    private static final String ENTITY_NAME = "soLuongHangKiem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoLuongHangKiemService soLuongHangKiemService;

    public SoLuongHangKiemResource(SoLuongHangKiemService soLuongHangKiemService) {
        this.soLuongHangKiemService = soLuongHangKiemService;
    }

    /**
     * {@code POST  /so-luong-hang-kiems} : Create a new soLuongHangKiem.
     *
     * @param soLuongHangKiemDTO the soLuongHangKiemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soLuongHangKiemDTO, or with status {@code 400 (Bad Request)} if the soLuongHangKiem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/so-luong-hang-kiems")
    public ResponseEntity<SoLuongHangKiemDTO> createSoLuongHangKiem(@RequestBody SoLuongHangKiemDTO soLuongHangKiemDTO) throws URISyntaxException {
        log.debug("REST request to save SoLuongHangKiem : {}", soLuongHangKiemDTO);
        if (soLuongHangKiemDTO.getId() != null) {
            throw new BadRequestAlertException("A new soLuongHangKiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SoLuongHangKiemDTO result = soLuongHangKiemService.save(soLuongHangKiemDTO);
        return ResponseEntity.created(new URI("/api/so-luong-hang-kiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /so-luong-hang-kiems} : Updates an existing soLuongHangKiem.
     *
     * @param soLuongHangKiemDTO the soLuongHangKiemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soLuongHangKiemDTO,
     * or with status {@code 400 (Bad Request)} if the soLuongHangKiemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soLuongHangKiemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/so-luong-hang-kiems")
    public ResponseEntity<SoLuongHangKiemDTO> updateSoLuongHangKiem(@RequestBody SoLuongHangKiemDTO soLuongHangKiemDTO) throws URISyntaxException {
        log.debug("REST request to update SoLuongHangKiem : {}", soLuongHangKiemDTO);
        if (soLuongHangKiemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SoLuongHangKiemDTO result = soLuongHangKiemService.save(soLuongHangKiemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soLuongHangKiemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /so-luong-hang-kiems} : get all the soLuongHangKiems.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soLuongHangKiems in body.
     */
    @GetMapping("/so-luong-hang-kiems")
    public List<SoLuongHangKiemDTO> getAllSoLuongHangKiems() {
        log.debug("REST request to get all SoLuongHangKiems");
        return soLuongHangKiemService.findAll();
    }

    /**
     * {@code GET  /so-luong-hang-kiems/:id} : get the "id" soLuongHangKiem.
     *
     * @param id the id of the soLuongHangKiemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soLuongHangKiemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/so-luong-hang-kiems/{id}")
    public ResponseEntity<SoLuongHangKiemDTO> getSoLuongHangKiem(@PathVariable Long id) {
        log.debug("REST request to get SoLuongHangKiem : {}", id);
        Optional<SoLuongHangKiemDTO> soLuongHangKiemDTO = soLuongHangKiemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soLuongHangKiemDTO);
    }

    /**
     * {@code DELETE  /so-luong-hang-kiems/:id} : delete the "id" soLuongHangKiem.
     *
     * @param id the id of the soLuongHangKiemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/so-luong-hang-kiems/{id}")
    public ResponseEntity<Void> deleteSoLuongHangKiem(@PathVariable Long id) {
        log.debug("REST request to delete SoLuongHangKiem : {}", id);
        soLuongHangKiemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
