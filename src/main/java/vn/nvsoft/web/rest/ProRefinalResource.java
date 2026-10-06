package vn.nvsoft.web.rest;

import vn.nvsoft.domain.PrefinalTheoGio;
import vn.nvsoft.service.ProRefinalService;
import vn.nvsoft.service.dto.RefinalAndChiTietDTO;
import vn.nvsoft.service.dto.RefinalCreateInfoDTO;
import vn.nvsoft.service.dto.ProRefinalTheoGioDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProRefinalDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProRefinal}.
 */
@RestController
@RequestMapping("/api")
public class ProRefinalResource {

    private final Logger log = LoggerFactory.getLogger(ProRefinalResource.class);

    private static final String ENTITY_NAME = "proRefinal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProRefinalService proRefinalService;

    public ProRefinalResource(ProRefinalService proRefinalService) {
        this.proRefinalService = proRefinalService;
    }

    /**
     * {@code POST  /pro-refinals} : Create a new proRefinal.
     *
     * @param proRefinalDTO the proRefinalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalDTO, or with status {@code 400 (Bad Request)} if the proRefinal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinals")
    public ResponseEntity<ProRefinalDTO> createProRefinal(@RequestBody ProRefinalDTO proRefinalDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinal : {}", proRefinalDTO);
        if (proRefinalDTO.getId() != null) {
            throw new BadRequestAlertException("A new proRefinal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProRefinalDTO result = proRefinalService.save(proRefinalDTO);
        return ResponseEntity.created(new URI("/api/pro-refinals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-refinals} : Updates an existing proRefinal.
     *
     * @param proRefinalDTO the proRefinalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRefinalDTO,
     * or with status {@code 400 (Bad Request)} if the proRefinalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proRefinalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-refinals")
    public ResponseEntity<ProRefinalDTO> updateProRefinal(@RequestBody ProRefinalDTO proRefinalDTO) throws URISyntaxException {
        log.debug("REST request to update ProRefinal : {}", proRefinalDTO);
        if (proRefinalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProRefinalDTO result = proRefinalService.save(proRefinalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proRefinalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-refinals} : get all the proRefinals.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-refinals")
    public List<ProRefinalDTO> getAllProRefinals() {
        log.debug("REST request to get all ProRefinals");
        return proRefinalService.findAll();
    }

    /**
     * {@code GET  /pro-refinals/:id} : get the "id" proRefinal.
     *
     * @param id the id of the proRefinalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRefinalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-refinals/{id}")
    public ResponseEntity<ProRefinalDTO> getProRefinal(@PathVariable Long id) {
        log.debug("REST request to get ProRefinal : {}", id);
        Optional<ProRefinalDTO> proRefinalDTO = proRefinalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proRefinalDTO);
    }

    /**
     * {@code GET  /pro-refinals/refinal-theo-gio/:stt} : get the "stt" chuyen.
     *
     * @param id the id of the proRefinalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRefinalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-refinals/refinal-theo-gio/{id}")
    public List<PrefinalTheoGio> getProRefinalTheoGio(@PathVariable Long id) {
        log.debug("REST request to get ProRefinalTheoGio : {}", id);
        return proRefinalService.getAllProRefinalTheoGios(id);
    }


    /**
     * {@code DELETE  /pro-refinals/:id} : delete the "id" proRefinal.
     *
     * @param id the id of the proRefinalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-refinals/{id}")
    public ResponseEntity<Void> deleteProRefinal(@PathVariable Long id) {
        log.debug("REST request to delete ProRefinal : {}", id);
        proRefinalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-refinals/today} : get all the proRefinals to day.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-refinals/today/{khsx-id}")
    public List<RefinalAndChiTietDTO> getAllProRefinalsToday(@PathVariable("khsx-id") Long khsxId) {
        log.debug("REST request to get all ProRefinals today of khsx {}", khsxId);
        return proRefinalService.getRefinalAndChiTiets(khsxId);
    }

    /**
     * {@code GET  /pro-refinals/create} : get information to create refinal.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-refinals/create/{khsx-id}/{check-point-id}")
    public RefinalCreateInfoDTO getRefinalInfoToCreate(@PathVariable("khsx-id") Long khsxId, @PathVariable("check-point-id") Long checkPointId) {
        return proRefinalService.getInfoToCreateRefinal(checkPointId, khsxId);
    }

    /**
     * {@code POST  /pro-refinals/create} : Create a new proRefinal and chi tiet.
     *
     * @param refinalToCreateDTO the refinalToCreateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalDTO, or with status {@code 400 (Bad Request)} if the proRefinal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinals/create")
    public ResponseEntity<Void> createProRefinalAndDetail(@RequestBody RefinalAndChiTietDTO refinalToCreateDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinal and chi tiet: {}", refinalToCreateDTO);

        proRefinalService.createRefinal(refinalToCreateDTO);
        return ResponseEntity.ok().build();
    }


}
