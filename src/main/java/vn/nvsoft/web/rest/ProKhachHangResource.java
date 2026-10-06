package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProKhachHangService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProKhachHangDTO;
import vn.nvsoft.service.dto.ProKhachHangCriteria;
import vn.nvsoft.service.ProKhachHangQueryService;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProKhachHang}.
 */
@RestController
@RequestMapping("/api")
public class ProKhachHangResource {

    private final Logger log = LoggerFactory.getLogger(ProKhachHangResource.class);

    private static final String ENTITY_NAME = "proKhachHang";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProKhachHangService proKhachHangService;

    private final ProKhachHangQueryService proKhachHangQueryService;

    public ProKhachHangResource(ProKhachHangService proKhachHangService, ProKhachHangQueryService proKhachHangQueryService) {
        this.proKhachHangService = proKhachHangService;
        this.proKhachHangQueryService = proKhachHangQueryService;
    }

    /**
     * {@code POST  /pro-khach-hangs} : Create a new proKhachHang.
     *
     * @param proKhachHangDTO the proKhachHangDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proKhachHangDTO, or with status {@code 400 (Bad Request)} if the proKhachHang has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-khach-hangs")
    public ResponseEntity<ProKhachHangDTO> createProKhachHang(@RequestBody ProKhachHangDTO proKhachHangDTO) throws URISyntaxException {
        log.debug("REST request to save ProKhachHang : {}", proKhachHangDTO);
        if (proKhachHangDTO.getId() != null) {
            throw new BadRequestAlertException("A new proKhachHang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProKhachHangDTO result = proKhachHangService.save(proKhachHangDTO);
        return ResponseEntity.created(new URI("/api/pro-khach-hangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-khach-hangs} : Updates an existing proKhachHang.
     *
     * @param proKhachHangDTO the proKhachHangDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proKhachHangDTO,
     * or with status {@code 400 (Bad Request)} if the proKhachHangDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proKhachHangDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-khach-hangs")
    public ResponseEntity<ProKhachHangDTO> updateProKhachHang(@RequestBody ProKhachHangDTO proKhachHangDTO) throws URISyntaxException {
        log.debug("REST request to update ProKhachHang : {}", proKhachHangDTO);
        if (proKhachHangDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProKhachHangDTO result = proKhachHangService.save(proKhachHangDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proKhachHangDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-khach-hangs} : get all the proKhachHangs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proKhachHangs in body.
     */
    @GetMapping("/pro-khach-hangs")
    public ResponseEntity<List<ProKhachHangDTO>> getAllProKhachHangs(ProKhachHangCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ProKhachHangs by criteria: {}", criteria);
        Page<ProKhachHangDTO> page = proKhachHangQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pro-khach-hangs/count} : count all the proKhachHangs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pro-khach-hangs/count")
    public ResponseEntity<Long> countProKhachHangs(ProKhachHangCriteria criteria) {
        log.debug("REST request to count ProKhachHangs by criteria: {}", criteria);
        return ResponseEntity.ok().body(proKhachHangQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pro-khach-hangs/:id} : get the "id" proKhachHang.
     *
     * @param id the id of the proKhachHangDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proKhachHangDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-khach-hangs/{id}")
    public ResponseEntity<ProKhachHangDTO> getProKhachHang(@PathVariable Long id) {
        log.debug("REST request to get ProKhachHang : {}", id);
        Optional<ProKhachHangDTO> proKhachHangDTO = proKhachHangService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proKhachHangDTO);
    }

    /**
     * {@code DELETE  /pro-khach-hangs/:id} : delete the "id" proKhachHang.
     *
     * @param id the id of the proKhachHangDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-khach-hangs/{id}")
    public ResponseEntity<Void> deleteProKhachHang(@PathVariable Long id) {
        log.debug("REST request to delete ProKhachHang : {}", id);
        proKhachHangService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
