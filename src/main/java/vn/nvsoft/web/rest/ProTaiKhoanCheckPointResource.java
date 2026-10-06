package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProTaiKhoanCheckPointService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProTaiKhoanCheckPointDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProTaiKhoanCheckPoint}.
 */
@RestController
@RequestMapping("/api")
public class ProTaiKhoanCheckPointResource {

    private final Logger log = LoggerFactory.getLogger(ProTaiKhoanCheckPointResource.class);

    private static final String ENTITY_NAME = "proTaiKhoanCheckPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProTaiKhoanCheckPointService proTaiKhoanCheckPointService;

    public ProTaiKhoanCheckPointResource(ProTaiKhoanCheckPointService proTaiKhoanCheckPointService) {
        this.proTaiKhoanCheckPointService = proTaiKhoanCheckPointService;
    }

    /**
     * {@code POST  /pro-tai-khoan-check-points} : Create a new proTaiKhoanCheckPoint.
     *
     * @param proTaiKhoanCheckPointDTO the proTaiKhoanCheckPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proTaiKhoanCheckPointDTO, or with status {@code 400 (Bad Request)} if the proTaiKhoanCheckPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-tai-khoan-check-points")
    public ResponseEntity<ProTaiKhoanCheckPointDTO> createProTaiKhoanCheckPoint(@RequestBody ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO) throws URISyntaxException {
        log.debug("REST request to save ProTaiKhoanCheckPoint : {}", proTaiKhoanCheckPointDTO);
        if (proTaiKhoanCheckPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new proTaiKhoanCheckPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProTaiKhoanCheckPointDTO result = proTaiKhoanCheckPointService.save(proTaiKhoanCheckPointDTO);
        return ResponseEntity.created(new URI("/api/pro-tai-khoan-check-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-tai-khoan-check-points} : Updates an existing proTaiKhoanCheckPoint.
     *
     * @param proTaiKhoanCheckPointDTO the proTaiKhoanCheckPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proTaiKhoanCheckPointDTO,
     * or with status {@code 400 (Bad Request)} if the proTaiKhoanCheckPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proTaiKhoanCheckPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-tai-khoan-check-points")
    public ResponseEntity<ProTaiKhoanCheckPointDTO> updateProTaiKhoanCheckPoint(@RequestBody ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO) throws URISyntaxException {
        log.debug("REST request to update ProTaiKhoanCheckPoint : {}", proTaiKhoanCheckPointDTO);
        if (proTaiKhoanCheckPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProTaiKhoanCheckPointDTO result = proTaiKhoanCheckPointService.save(proTaiKhoanCheckPointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proTaiKhoanCheckPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-tai-khoan-check-points} : get all the proTaiKhoanCheckPoints.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proTaiKhoanCheckPoints in body.
     */
    @GetMapping("/pro-tai-khoan-check-points")
    public ResponseEntity<List<ProTaiKhoanCheckPointDTO>> getAllProTaiKhoanCheckPoints(Pageable pageable) {
        log.debug("REST request to get a page of ProTaiKhoanCheckPoints");
        Page<ProTaiKhoanCheckPointDTO> page = proTaiKhoanCheckPointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-tai-khoan-check-points/:id} : get the "id" proTaiKhoanCheckPoint.
     *
     * @param id the id of the proTaiKhoanCheckPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proTaiKhoanCheckPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-tai-khoan-check-points/{id}")
    public ResponseEntity<ProTaiKhoanCheckPointDTO> getProTaiKhoanCheckPoint(@PathVariable Long id) {
        log.debug("REST request to get ProTaiKhoanCheckPoint : {}", id);
        Optional<ProTaiKhoanCheckPointDTO> proTaiKhoanCheckPointDTO = proTaiKhoanCheckPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proTaiKhoanCheckPointDTO);
    }

    /**
     * {@code DELETE  /pro-tai-khoan-check-points/:id} : delete the "id" proTaiKhoanCheckPoint.
     *
     * @param id the id of the proTaiKhoanCheckPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-tai-khoan-check-points/{id}")
    public ResponseEntity<Void> deleteProTaiKhoanCheckPoint(@PathVariable Long id) {
        log.debug("REST request to delete ProTaiKhoanCheckPoint : {}", id);
        proTaiKhoanCheckPointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
