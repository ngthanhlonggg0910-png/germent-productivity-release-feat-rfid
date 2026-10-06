package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProRefinalChiTietLoiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProRefinalChiTietLoiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProRefinalChiTietLoi}.
 */
@RestController
@RequestMapping("/api")
public class ProRefinalChiTietLoiResource {

    private final Logger log = LoggerFactory.getLogger(ProRefinalChiTietLoiResource.class);

    private static final String ENTITY_NAME = "proRefinalChiTietLoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProRefinalChiTietLoiService proRefinalChiTietLoiService;

    public ProRefinalChiTietLoiResource(ProRefinalChiTietLoiService proRefinalChiTietLoiService) {
        this.proRefinalChiTietLoiService = proRefinalChiTietLoiService;
    }

    /**
     * {@code POST  /pro-refinal-chi-tiet-lois} : Create a new proRefinalChiTietLoi.
     *
     * @param proRefinalChiTietLoiDTO the proRefinalChiTietLoiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalChiTietLoiDTO, or with status {@code 400 (Bad Request)} if the proRefinalChiTietLoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinal-chi-tiet-lois")
    public ResponseEntity<ProRefinalChiTietLoiDTO> createProRefinalChiTietLoi(@RequestBody ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinalChiTietLoi : {}", proRefinalChiTietLoiDTO);
        if (proRefinalChiTietLoiDTO.getId() != null) {
            throw new BadRequestAlertException("A new proRefinalChiTietLoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProRefinalChiTietLoiDTO result = proRefinalChiTietLoiService.save(proRefinalChiTietLoiDTO);
        return ResponseEntity.created(new URI("/api/pro-refinal-chi-tiet-lois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-refinal-chi-tiet-lois} : Updates an existing proRefinalChiTietLoi.
     *
     * @param proRefinalChiTietLoiDTO the proRefinalChiTietLoiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRefinalChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proRefinalChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proRefinalChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-refinal-chi-tiet-lois")
    public ResponseEntity<ProRefinalChiTietLoiDTO> updateProRefinalChiTietLoi(@RequestBody ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO) throws URISyntaxException {
        log.debug("REST request to update ProRefinalChiTietLoi : {}", proRefinalChiTietLoiDTO);
        if (proRefinalChiTietLoiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProRefinalChiTietLoiDTO result = proRefinalChiTietLoiService.save(proRefinalChiTietLoiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proRefinalChiTietLoiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-refinal-chi-tiet-lois} : get all the proRefinalChiTietLois.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinalChiTietLois in body.
     */
    @GetMapping("/pro-refinal-chi-tiet-lois")
    public List<ProRefinalChiTietLoiDTO> getAllProRefinalChiTietLois() {
        log.debug("REST request to get all ProRefinalChiTietLois");
        return proRefinalChiTietLoiService.findAll();
    }

    /**
     * {@code GET  /pro-refinal-chi-tiet-lois/:id} : get the "id" proRefinalChiTietLoi.
     *
     * @param id the id of the proRefinalChiTietLoiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRefinalChiTietLoiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-refinal-chi-tiet-lois/{id}")
    public ResponseEntity<ProRefinalChiTietLoiDTO> getProRefinalChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to get ProRefinalChiTietLoi : {}", id);
        Optional<ProRefinalChiTietLoiDTO> proRefinalChiTietLoiDTO = proRefinalChiTietLoiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proRefinalChiTietLoiDTO);
    }

    /**
     * {@code DELETE  /pro-refinal-chi-tiet-lois/:id} : delete the "id" proRefinalChiTietLoi.
     *
     * @param id the id of the proRefinalChiTietLoiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-refinal-chi-tiet-lois/{id}")
    public ResponseEntity<Void> deleteProRefinalChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to delete ProRefinalChiTietLoi : {}", id);
        proRefinalChiTietLoiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
