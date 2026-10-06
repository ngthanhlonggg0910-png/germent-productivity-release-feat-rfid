package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProLoaiChuyenService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProLoaiChuyenDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProLoaiChuyen}.
 */
@RestController
@RequestMapping("/api")
public class ProLoaiChuyenResource {

    private final Logger log = LoggerFactory.getLogger(ProLoaiChuyenResource.class);

    private static final String ENTITY_NAME = "proLoaiChuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProLoaiChuyenService proLoaiChuyenService;

    public ProLoaiChuyenResource(ProLoaiChuyenService proLoaiChuyenService) {
        this.proLoaiChuyenService = proLoaiChuyenService;
    }

    /**
     * {@code POST  /pro-loai-chuyens} : Create a new proLoaiChuyen.
     *
     * @param proLoaiChuyenDTO the proLoaiChuyenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proLoaiChuyenDTO, or with status {@code 400 (Bad Request)} if the proLoaiChuyen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-loai-chuyens")
    public ResponseEntity<ProLoaiChuyenDTO> createProLoaiChuyen(@RequestBody ProLoaiChuyenDTO proLoaiChuyenDTO) throws URISyntaxException {
        log.debug("REST request to save ProLoaiChuyen : {}", proLoaiChuyenDTO);
        if (proLoaiChuyenDTO.getId() != null) {
            throw new BadRequestAlertException("A new proLoaiChuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProLoaiChuyenDTO result = proLoaiChuyenService.save(proLoaiChuyenDTO);
        return ResponseEntity.created(new URI("/api/pro-loai-chuyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-loai-chuyens} : Updates an existing proLoaiChuyen.
     *
     * @param proLoaiChuyenDTO the proLoaiChuyenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proLoaiChuyenDTO,
     * or with status {@code 400 (Bad Request)} if the proLoaiChuyenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proLoaiChuyenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-loai-chuyens")
    public ResponseEntity<ProLoaiChuyenDTO> updateProLoaiChuyen(@RequestBody ProLoaiChuyenDTO proLoaiChuyenDTO) throws URISyntaxException {
        log.debug("REST request to update ProLoaiChuyen : {}", proLoaiChuyenDTO);
        if (proLoaiChuyenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProLoaiChuyenDTO result = proLoaiChuyenService.save(proLoaiChuyenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proLoaiChuyenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-loai-chuyens} : get all the proLoaiChuyens.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proLoaiChuyens in body.
     */
    @GetMapping("/pro-loai-chuyens")
    public ResponseEntity<List<ProLoaiChuyenDTO>> getAllProLoaiChuyens(Pageable pageable) {
        log.debug("REST request to get a page of ProLoaiChuyens");
        Page<ProLoaiChuyenDTO> page = proLoaiChuyenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-loai-chuyens/:id} : get the "id" proLoaiChuyen.
     *
     * @param id the id of the proLoaiChuyenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proLoaiChuyenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-loai-chuyens/{id}")
    public ResponseEntity<ProLoaiChuyenDTO> getProLoaiChuyen(@PathVariable Long id) {
        log.debug("REST request to get ProLoaiChuyen : {}", id);
        Optional<ProLoaiChuyenDTO> proLoaiChuyenDTO = proLoaiChuyenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proLoaiChuyenDTO);
    }

    /**
     * {@code DELETE  /pro-loai-chuyens/:id} : delete the "id" proLoaiChuyen.
     *
     * @param id the id of the proLoaiChuyenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-loai-chuyens/{id}")
    public ResponseEntity<Void> deleteProLoaiChuyen(@PathVariable Long id) {
        log.debug("REST request to delete ProLoaiChuyen : {}", id);
        proLoaiChuyenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
