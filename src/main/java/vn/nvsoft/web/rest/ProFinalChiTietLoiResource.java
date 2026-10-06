package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProFinalChiTietLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProFinalChiTietLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProFinalChiTietLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProFinalChiTietLoiResource.class);

    private static final String ENTITY_NAME = "proFinalChiTietLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProFinalChiTietLoiService proFinalChiTietLoiService;

    public ProFinalChiTietLoiResource(ProFinalChiTietLoiService proFinalChiTietLoiService) {
        this.proFinalChiTietLoiService = proFinalChiTietLoiService;
    }

    /**
     * {@code POST  /pro-final-chi-tiet-lois} : Create a new proFinalChiTietLoi.
     *
     * @param proFinalChiTietLoiDTO the proFinalChiTietLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proFinalChiTietLoiDTO, or with status {@code 400 (Bad Request)} if the proFinalChiTietLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-final-chi-tiet-lois")
    public ResponseEntity<ProFinalChiTietLoiDTO> createProFinalChiTietLoi(@RequestBody ProFinalChiTietLoiDTO proFinalChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProFinalChiTietLoi : {}", proFinalChiTietLoiDTO);
        if (proFinalChiTietLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proFinalChiTietLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProFinalChiTietLoiDTO result = proFinalChiTietLoiService.save(proFinalChiTietLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-final-chi-tiet-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-final-chi-tiet-lois} : Updates an existing proFinalChiTietLoi.
     *
     * @param proFinalChiTietLoiDTO the proFinalChiTietLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proFinalChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proFinalChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proFinalChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-final-chi-tiet-lois")
    public ResponseEntity<ProFinalChiTietLoiDTO> updateProFinalChiTietLoi(@RequestBody ProFinalChiTietLoiDTO proFinalChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProFinalChiTietLoi : {}", proFinalChiTietLoiDTO);
        if (proFinalChiTietLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProFinalChiTietLoiDTO result = proFinalChiTietLoiService.save(proFinalChiTietLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proFinalChiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-final-chi-tiet-lois} : get all the proFinalChiTietLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proFinalChiTietLois in body.
     */
    @GetMapping("/pro-final-chi-tiet-lois")
    public List<ProFinalChiTietLoiDTO> getAllProFinalChiTietLois() {
        log.debug("REST request to get all ProFinalChiTietLois");
        return proFinalChiTietLoiService.findAll();
    }

    /**
     * {@code GET  /pro-final-chi-tiet-lois/:id} : get the "id" proFinalChiTietLoi.
     *
     * @param id the id of the proFinalChiTietLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proFinalChiTietLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-final-chi-tiet-lois/{id}")
    public ResponseEntity<ProFinalChiTietLoiDTO> getProFinalChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to get ProFinalChiTietLoi : {}", id);
        Optional<ProFinalChiTietLoiDTO> proFinalChiTietLoiDTO = proFinalChiTietLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proFinalChiTietLoiDTO);
    }

    /**
     * {@code DELETE  /pro-final-chi-tiet-lois/:id} : delete the "id" proFinalChiTietLoi.
     *
     * @param id the id of the proFinalChiTietLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-final-chi-tiet-lois/{id}")
    public ResponseEntity<Void> deleteProFinalChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProFinalChiTietLoi : {}", id);
        proFinalChiTietLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
