package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProRefinalCCChiTietLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProRefinalCCChiTietLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProRefinalCCChiTietLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProRefinalCCChiTietLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProRefinalCCChiTietLoiResource.class);

    private static final String ENTITY_NAME = "proRefinalCCChiTietLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProRefinalCCChiTietLoiService proRefinalCCChiTietLoiService;

    public ProRefinalCCChiTietLoiResource(ProRefinalCCChiTietLoiService proRefinalCCChiTietLoiService) {
        this.proRefinalCCChiTietLoiService = proRefinalCCChiTietLoiService;
    }

    /**
     * {@code POST  /pro-refinal-cc-chi-tiet-lois} : Create a new proRefinalCCChiTietLoi.
     *
     * @param proRefinalCCChiTietLoiDTO the proRefinalCCChiTietLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalCCChiTietLoiDTO, or with status {@code 400 (Bad Request)} if the proRefinalCCChiTietLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinal-cc-chi-tiet-lois")
    public ResponseEntity<ProRefinalCCChiTietLoiDTO> createProRefinalCCChiTietLoi(@RequestBody ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinalCCChiTietLoi : {}", proRefinalCCChiTietLoiDTO);
        if (proRefinalCCChiTietLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proRefinalCCChiTietLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProRefinalCCChiTietLoiDTO result = proRefinalCCChiTietLoiService.save(proRefinalCCChiTietLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-refinal-cc-chi-tiet-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-refinal-cc-chi-tiet-lois} : Updates an existing proRefinalCCChiTietLoi.
     *
     * @param proRefinalCCChiTietLoiDTO the proRefinalCCChiTietLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRefinalCCChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proRefinalCCChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proRefinalCCChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-refinal-cc-chi-tiet-lois")
    public ResponseEntity<ProRefinalCCChiTietLoiDTO> updateProRefinalCCChiTietLoi(@RequestBody ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProRefinalCCChiTietLoi : {}", proRefinalCCChiTietLoiDTO);
        if (proRefinalCCChiTietLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProRefinalCCChiTietLoiDTO result = proRefinalCCChiTietLoiService.save(proRefinalCCChiTietLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proRefinalCCChiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-refinal-cc-chi-tiet-lois} : get all the proRefinalCCChiTietLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinalCCChiTietLois in body.
     */
    @GetMapping("/pro-refinal-cc-chi-tiet-lois")
    public List<ProRefinalCCChiTietLoiDTO> getAllProRefinalCCChiTietLois() {
        log.debug("REST request to get all ProRefinalCCChiTietLois");
        return proRefinalCCChiTietLoiService.findAll();
    }

    /**
     * {@code GET  /pro-refinal-cc-chi-tiet-lois/:id} : get the "id" proRefinalCCChiTietLoi.
     *
     * @param id the id of the proRefinalCCChiTietLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRefinalCCChiTietLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-refinal-cc-chi-tiet-lois/{id}")
    public ResponseEntity<ProRefinalCCChiTietLoiDTO> getProRefinalCCChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to get ProRefinalCCChiTietLoi : {}", id);
        Optional<ProRefinalCCChiTietLoiDTO> proRefinalCCChiTietLoiDTO = proRefinalCCChiTietLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proRefinalCCChiTietLoiDTO);
    }

    /**
     * {@code DELETE  /pro-refinal-cc-chi-tiet-lois/:id} : delete the "id" proRefinalCCChiTietLoi.
     *
     * @param id the id of the proRefinalCCChiTietLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-refinal-cc-chi-tiet-lois/{id}")
    public ResponseEntity<Void> deleteProRefinalCCChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProRefinalCCChiTietLoi : {}", id);
        proRefinalCCChiTietLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
