package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProNhomCongDoanService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProNhomCongDoanDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProNhomCongDoan}.
 */
@RestController
@RequestMapping("/api")
public class ProNhomCongDoanResource {

    private final Logger log = LoggerFactory.getLogger(ProNhomCongDoanResource.class);

    private static final String ENTITY_NAME = "proNhomCongDoan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProNhomCongDoanService proNhomCongDoanService;

    public ProNhomCongDoanResource(ProNhomCongDoanService proNhomCongDoanService) {
        this.proNhomCongDoanService = proNhomCongDoanService;
    }

    /**
     * {@code POST  /pro-nhom-cong-doans} : Create a new proNhomCongDoan.
     *
     * @param proNhomCongDoanDTO the proNhomCongDoanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proNhomCongDoanDTO, or with status {@code 400 (Bad Request)} if the proNhomCongDoan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-nhom-cong-doans")
    public ResponseEntity<ProNhomCongDoanDTO> createProNhomCongDoan(@RequestBody ProNhomCongDoanDTO proNhomCongDoanDTO) throws URISyntaxException {
        log.debug("REST request to save ProNhomCongDoan : {}", proNhomCongDoanDTO);
        if (proNhomCongDoanDTO.getId() != null) {
            throw new BadRequestAlertException("A new proNhomCongDoan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProNhomCongDoanDTO result = proNhomCongDoanService.save(proNhomCongDoanDTO);
        return ResponseEntity.created(new URI("/api/pro-nhom-cong-doans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-nhom-cong-doans} : Updates an existing proNhomCongDoan.
     *
     * @param proNhomCongDoanDTO the proNhomCongDoanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNhomCongDoanDTO,
     * or with status {@code 400 (Bad Request)} if the proNhomCongDoanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNhomCongDoanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-nhom-cong-doans")
    public ResponseEntity<ProNhomCongDoanDTO> updateProNhomCongDoan(@RequestBody ProNhomCongDoanDTO proNhomCongDoanDTO) throws URISyntaxException {
        log.debug("REST request to update ProNhomCongDoan : {}", proNhomCongDoanDTO);
        if (proNhomCongDoanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProNhomCongDoanDTO result = proNhomCongDoanService.save(proNhomCongDoanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proNhomCongDoanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-nhom-cong-doans} : get all the proNhomCongDoans.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proNhomCongDoans in body.
     */
    @GetMapping("/pro-nhom-cong-doans")
    public ResponseEntity<List<ProNhomCongDoanDTO>> getAllProNhomCongDoans(Pageable pageable) {
        log.debug("REST request to get a page of ProNhomCongDoans");
        Page<ProNhomCongDoanDTO> page = proNhomCongDoanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-nhom-cong-doans/:id} : get the "id" proNhomCongDoan.
     *
     * @param id the id of the proNhomCongDoanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proNhomCongDoanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-nhom-cong-doans/{id}")
    public ResponseEntity<ProNhomCongDoanDTO> getProNhomCongDoan(@PathVariable Long id) {
        log.debug("REST request to get ProNhomCongDoan : {}", id);
        Optional<ProNhomCongDoanDTO> proNhomCongDoanDTO = proNhomCongDoanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proNhomCongDoanDTO);
    }

    /**
     * {@code DELETE  /pro-nhom-cong-doans/:id} : delete the "id" proNhomCongDoan.
     *
     * @param id the id of the proNhomCongDoanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-nhom-cong-doans/{id}")
    public ResponseEntity<Void> deleteProNhomCongDoan(@PathVariable Long id) {
        log.debug("REST request to delete ProNhomCongDoan : {}", id);
        proNhomCongDoanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
