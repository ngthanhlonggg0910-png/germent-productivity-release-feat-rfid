package vn.nvsoft.web.rest;

import vn.nvsoft.service.ThongSoDoSPSizeService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ThongSoDoSPSize}.
 */
@RestController
@RequestMapping("/api")
public class ThongSoDoSPSizeResource {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoSPSizeResource.class);

    private static final String ENTITY_NAME = "thongSoDoSPSize";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongSoDoSPSizeService thongSoDoSPSizeService;

    public ThongSoDoSPSizeResource(ThongSoDoSPSizeService thongSoDoSPSizeService) {
        this.thongSoDoSPSizeService = thongSoDoSPSizeService;
    }

    /**
     * {@code POST  /thong-so-do-sp-sizes} : Create a new thongSoDoSPSize.
     *
     * @param thongSoDoSPSizeDTO the thongSoDoSPSizeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongSoDoSPSizeDTO, or with status {@code 400 (Bad Request)} if the thongSoDoSPSize has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thong-so-do-sp-sizes")
    public ResponseEntity<ThongSoDoSPSizeDTO> createThongSoDoSPSize(@RequestBody ThongSoDoSPSizeDTO thongSoDoSPSizeDTO) throws URISyntaxException {
        log.debug("REST request to save ThongSoDoSPSize : {}", thongSoDoSPSizeDTO);
        if (thongSoDoSPSizeDTO.getId() != null) {
            throw new BadRequestAlertException("A new thongSoDoSPSize cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongSoDoSPSizeDTO result = thongSoDoSPSizeService.save(thongSoDoSPSizeDTO);
        return ResponseEntity.created(new URI("/api/thong-so-do-sp-sizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thong-so-do-sp-sizes} : Updates an existing thongSoDoSPSize.
     *
     * @param thongSoDoSPSizeDTO the thongSoDoSPSizeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongSoDoSPSizeDTO,
     * or with status {@code 400 (Bad Request)} if the thongSoDoSPSizeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongSoDoSPSizeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thong-so-do-sp-sizes")
    public ResponseEntity<ThongSoDoSPSizeDTO> updateThongSoDoSPSize(@RequestBody ThongSoDoSPSizeDTO thongSoDoSPSizeDTO) throws URISyntaxException {
        log.debug("REST request to update ThongSoDoSPSize : {}", thongSoDoSPSizeDTO);
        if (thongSoDoSPSizeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongSoDoSPSizeDTO result = thongSoDoSPSizeService.save(thongSoDoSPSizeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongSoDoSPSizeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /thong-so-do-sp-sizes} : get all the thongSoDoSPSizes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoDoSPSizes in body.
     */
    @GetMapping("/thong-so-do-sp-sizes")
    public ResponseEntity<List<ThongSoDoSPSizeDTO>> getAllThongSoDoSPSizes(Pageable pageable) {
        log.debug("REST request to get a page of ThongSoDoSPSizes");
        Page<ThongSoDoSPSizeDTO> page = thongSoDoSPSizeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-so-do-sp-sizes/:id} : get the "id" thongSoDoSPSize.
     *
     * @param id the id of the thongSoDoSPSizeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongSoDoSPSizeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thong-so-do-sp-sizes/{id}")
    public ResponseEntity<ThongSoDoSPSizeDTO> getThongSoDoSPSize(@PathVariable Long id) {
        log.debug("REST request to get ThongSoDoSPSize : {}", id);
        Optional<ThongSoDoSPSizeDTO> thongSoDoSPSizeDTO = thongSoDoSPSizeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongSoDoSPSizeDTO);
    }

    /**
     * {@code DELETE  /thong-so-do-sp-sizes/:id} : delete the "id" thongSoDoSPSize.
     *
     * @param id the id of the thongSoDoSPSizeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thong-so-do-sp-sizes/{id}")
    public ResponseEntity<Void> deleteThongSoDoSPSize(@PathVariable Long id) {
        log.debug("REST request to delete ThongSoDoSPSize : {}", id);
        thongSoDoSPSizeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
