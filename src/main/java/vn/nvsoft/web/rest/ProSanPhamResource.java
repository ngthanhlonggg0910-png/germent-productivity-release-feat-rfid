package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProSanPhamService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProSanPhamDTO;
import vn.nvsoft.service.dto.ProSanPhamCriteria;
import vn.nvsoft.service.ProSanPhamQueryService;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProSanPham}.
 */
@RestController
@RequestMapping("/api")
public class ProSanPhamResource {

    private final Logger log = LoggerFactory.getLogger(ProSanPhamResource.class);

    private static final String ENTITY_NAME = "proSanPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProSanPhamService proSanPhamService;

    private final ProSanPhamQueryService proSanPhamQueryService;

    public ProSanPhamResource(ProSanPhamService proSanPhamService, ProSanPhamQueryService proSanPhamQueryService) {
        this.proSanPhamService = proSanPhamService;
        this.proSanPhamQueryService = proSanPhamQueryService;
    }

    /**
     * {@code POST  /pro-san-phams} : Create a new proSanPham.
     *
     * @param proSanPhamDTO the proSanPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proSanPhamDTO, or with status {@code 400 (Bad Request)} if the proSanPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-san-phams")
    public ResponseEntity<ProSanPhamDTO> createProSanPham(@RequestBody ProSanPhamDTO proSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to save ProSanPham : {}", proSanPhamDTO);
        if (proSanPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new proSanPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProSanPhamDTO result = proSanPhamService.save(proSanPhamDTO);
        return ResponseEntity.created(new URI("/api/pro-san-phams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-san-phams} : Updates an existing proSanPham.
     *
     * @param proSanPhamDTO the proSanPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proSanPhamDTO,
     * or with status {@code 400 (Bad Request)} if the proSanPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proSanPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-san-phams")
    public ResponseEntity<ProSanPhamDTO> updateProSanPham(@RequestBody ProSanPhamDTO proSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to update ProSanPham : {}", proSanPhamDTO);
        if (proSanPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProSanPhamDTO result = proSanPhamService.save(proSanPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proSanPhamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-san-phams} : get all the proSanPhams.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proSanPhams in body.
     */
    @GetMapping("/pro-san-phams")
    public ResponseEntity<List<ProSanPhamDTO>> getAllProSanPhams(ProSanPhamCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ProSanPhams by criteria: {}", criteria);
        Page<ProSanPhamDTO> page = proSanPhamQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pro-san-phams/count} : count all the proSanPhams.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pro-san-phams/count")
    public ResponseEntity<Long> countProSanPhams(ProSanPhamCriteria criteria) {
        log.debug("REST request to count ProSanPhams by criteria: {}", criteria);
        return ResponseEntity.ok().body(proSanPhamQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pro-san-phams/:id} : get the "id" proSanPham.
     *
     * @param id the id of the proSanPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proSanPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-san-phams/{id}")
    public ResponseEntity<ProSanPhamDTO> getProSanPham(@PathVariable Long id) {
        log.debug("REST request to get ProSanPham : {}", id);
        Optional<ProSanPhamDTO> proSanPhamDTO = proSanPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proSanPhamDTO);
    }

    /**
     * {@code DELETE  /pro-san-phams/:id} : delete the "id" proSanPham.
     *
     * @param id the id of the proSanPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-san-phams/{id}")
    public ResponseEntity<Void> deleteProSanPham(@PathVariable Long id) {
        log.debug("REST request to delete ProSanPham : {}", id);
        proSanPhamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
