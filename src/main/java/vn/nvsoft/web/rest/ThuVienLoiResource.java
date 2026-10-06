package vn.nvsoft.web.rest;

import vn.nvsoft.service.ThuVienLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ThuVienLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ThuVienLoi}.
 */
@RestController
@RequestMapping("/api")
public class ThuVienLoiResource {

    private final Logger log = LoggerFactory.getLogger(ThuVienLoiResource.class);

    private static final String ENTITY_NAME = "thuVienLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThuVienLoiService thuVienLoiService;

    public ThuVienLoiResource(ThuVienLoiService thuVienLoiService) {
        this.thuVienLoiService = thuVienLoiService;
    }

    /**
     * {@code POST  /thu-vien-lois} : Create a new thuVienLoi.
     *
     * @param thuVienLoiDTO the thuVienLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thuVienLoiDTO, or with status {@code 400 (Bad Request)} if the thuVienLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thu-vien-lois")
    public ResponseEntity<ThuVienLoiDTO> createThuVienLoi(@RequestBody ThuVienLoiDTO thuVienLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ThuVienLoi : {}", thuVienLoiDTO);
        if (thuVienLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new thuVienLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThuVienLoiDTO result = thuVienLoiService.save(thuVienLoiDTO);
        return ResponseEntity.created(new URI("/api/thu-vien-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thu-vien-lois} : Updates an existing thuVienLoi.
     *
     * @param thuVienLoiDTO the thuVienLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thuVienLoiDTO,
     * or with status {@code 400 (Bad Request)} if the thuVienLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thuVienLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thu-vien-lois")
    public ResponseEntity<ThuVienLoiDTO> updateThuVienLoi(@RequestBody ThuVienLoiDTO thuVienLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ThuVienLoi : {}", thuVienLoiDTO);
        if (thuVienLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThuVienLoiDTO result = thuVienLoiService.save(thuVienLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thuVienLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /thu-vien-lois} : get all the thuVienLois.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thuVienLois in body.
     */
    @GetMapping("/thu-vien-lois")
    public ResponseEntity<List<ThuVienLoiDTO>> getAllThuVienLois(Pageable pageable) {
        log.debug("REST request to get a page of ThuVienLois");
        Page<ThuVienLoiDTO> page = thuVienLoiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thu-vien-lois/:id} : get the "id" thuVienLoi.
     *
     * @param id the id of the thuVienLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thuVienLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thu-vien-lois/{id}")
    public ResponseEntity<ThuVienLoiDTO> getThuVienLoi(@PathVariable Long id) {
        log.debug("REST request to get ThuVienLoi : {}", id);
        Optional<ThuVienLoiDTO> thuVienLoiDTO = thuVienLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thuVienLoiDTO);
    }

    /**
     * {@code DELETE  /thu-vien-lois/:id} : delete the "id" thuVienLoi.
     *
     * @param id the id of the thuVienLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thu-vien-lois/{id}")
    public ResponseEntity<Void> deleteThuVienLoi(@PathVariable Long id) {
        log.debug("REST request to delete ThuVienLoi : {}", id);
        thuVienLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
