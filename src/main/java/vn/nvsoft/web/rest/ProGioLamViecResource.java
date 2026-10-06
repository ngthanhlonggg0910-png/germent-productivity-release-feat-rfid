package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProGioLamViecService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProGioLamViecDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProGioLamViec}.
 */
@RestController
@RequestMapping("/api")
public class ProGioLamViecResource {

    private final Logger log = LoggerFactory.getLogger(ProGioLamViecResource.class);

    private static final String ENTITY_NAME = "proGioLamViec";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProGioLamViecService proGioLamViecService;

    public ProGioLamViecResource(ProGioLamViecService proGioLamViecService) {
        this.proGioLamViecService = proGioLamViecService;
    }

    /**
     * {@code POST  /pro-gio-lam-viecs} : Create a new proGioLamViec.
     *
     * @param proGioLamViecDTO the proGioLamViecDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proGioLamViecDTO, or with status {@code 400 (Bad Request)} if the proGioLamViec has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-gio-lam-viecs")
    public ResponseEntity<ProGioLamViecDTO> createProGioLamViec(@Valid @RequestBody ProGioLamViecDTO proGioLamViecDTO) throws URISyntaxException {
        log.debug("REST request to save ProGioLamViec : {}", proGioLamViecDTO);
        if (proGioLamViecDTO.getId() != null) {
            throw new BadRequestAlertException("A new proGioLamViec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProGioLamViecDTO result = proGioLamViecService.save(proGioLamViecDTO);
        return ResponseEntity.created(new URI("/api/pro-gio-lam-viecs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-gio-lam-viecs} : Updates an existing proGioLamViec.
     *
     * @param proGioLamViecDTO the proGioLamViecDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proGioLamViecDTO,
     * or with status {@code 400 (Bad Request)} if the proGioLamViecDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proGioLamViecDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-gio-lam-viecs")
    public ResponseEntity<ProGioLamViecDTO> updateProGioLamViec(@Valid @RequestBody ProGioLamViecDTO proGioLamViecDTO) throws URISyntaxException {
        log.debug("REST request to update ProGioLamViec : {}", proGioLamViecDTO);
        if (proGioLamViecDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProGioLamViecDTO result = proGioLamViecService.save(proGioLamViecDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proGioLamViecDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-gio-lam-viecs} : get all the proGioLamViecs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proGioLamViecs in body.
     */
    @GetMapping("/pro-gio-lam-viecs")
    public ResponseEntity<List<ProGioLamViecDTO>> getAllProGioLamViecs(Pageable pageable) {
        log.debug("REST request to get a page of ProGioLamViecs");
        Page<ProGioLamViecDTO> page = proGioLamViecService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-gio-lam-viecs/:id} : get the "id" proGioLamViec.
     *
     * @param id the id of the proGioLamViecDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proGioLamViecDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-gio-lam-viecs/{id}")
    public ResponseEntity<ProGioLamViecDTO> getProGioLamViec(@PathVariable Long id) {
        log.debug("REST request to get ProGioLamViec : {}", id);
        Optional<ProGioLamViecDTO> proGioLamViecDTO = proGioLamViecService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proGioLamViecDTO);
    }

    @GetMapping("/current-working-hour}")
    public ResponseEntity<ProGioLamViecDTO> getCurrentWorkingHour() {
        log.debug("REST request to get getCurrentWorkingHour : {}");
        Optional<ProGioLamViecDTO> proGioLamViecDTO = Optional.ofNullable(proGioLamViecService.getGioLamViecHienTai());
        return ResponseUtil.wrapOrNotFound(proGioLamViecDTO);
    }

    /**
     * {@code DELETE  /pro-gio-lam-viecs/:id} : delete the "id" proGioLamViec.
     *
     * @param id the id of the proGioLamViecDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-gio-lam-viecs/{id}")
    public ResponseEntity<Void> deleteProGioLamViec(@PathVariable Long id) {
        log.debug("REST request to delete ProGioLamViec : {}", id);
        proGioLamViecService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
