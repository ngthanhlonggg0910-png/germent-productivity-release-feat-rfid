package vn.nvsoft.web.rest;

import vn.nvsoft.service.DoAmService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.DoAmDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.DoAm}.
 */
@RestController
@RequestMapping("/api")
public class DoAmResource {

    private final Logger log = LoggerFactory.getLogger(DoAmResource.class);

    private static final String ENTITY_NAME = "doAm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoAmService doAmService;

    public DoAmResource(DoAmService doAmService) {
        this.doAmService = doAmService;
    }

    /**
     * {@code POST  /do-ams} : Create a new doAm.
     *
     * @param doAmDTO the doAmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doAmDTO, or with status {@code 400 (Bad Request)} if the doAm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/do-ams")
    public ResponseEntity<DoAmDTO> createDoAm(@RequestBody DoAmDTO doAmDTO) throws URISyntaxException {
        log.debug("REST request to save DoAm : {}", doAmDTO);
        if (doAmDTO.getId() != null) {
            throw new BadRequestAlertException("A new doAm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoAmDTO result = doAmService.save(doAmDTO);
        return ResponseEntity.created(new URI("/api/do-ams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /do-ams} : Updates an existing doAm.
     *
     * @param doAmDTO the doAmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doAmDTO,
     * or with status {@code 400 (Bad Request)} if the doAmDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doAmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/do-ams")
    public ResponseEntity<DoAmDTO> updateDoAm(@RequestBody DoAmDTO doAmDTO) throws URISyntaxException {
        log.debug("REST request to update DoAm : {}", doAmDTO);
        if (doAmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoAmDTO result = doAmService.save(doAmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doAmDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /do-ams} : get all the doAms.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doAms in body.
     */
    @GetMapping("/do-ams")
    public List<DoAmDTO> getAllDoAms() {
        log.debug("REST request to get all DoAms");
        return doAmService.findAll();
    }

    /**
     * {@code GET  /do-ams/:id} : get the "id" doAm.
     *
     * @param id the id of the doAmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doAmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/do-ams/{id}")
    public ResponseEntity<DoAmDTO> getDoAm(@PathVariable Long id) {
        log.debug("REST request to get DoAm : {}", id);
        Optional<DoAmDTO> doAmDTO = doAmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doAmDTO);
    }

    /**
     * {@code DELETE  /do-ams/:id} : delete the "id" doAm.
     *
     * @param id the id of the doAmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/do-ams/{id}")
    public ResponseEntity<Void> deleteDoAm(@PathVariable Long id) {
        log.debug("REST request to delete DoAm : {}", id);
        doAmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
