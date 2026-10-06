package vn.nvsoft.web.rest;

import vn.nvsoft.service.DoKimMdService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.DoKimMdDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.DoKimMd}.
 */
@RestController
@RequestMapping("/api")
public class DoKimMdResource {

    private final Logger log = LoggerFactory.getLogger(DoKimMdResource.class);

    private static final String ENTITY_NAME = "doKimMd";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DoKimMdService doKimMdService;

    public DoKimMdResource(DoKimMdService doKimMdService) {
        this.doKimMdService = doKimMdService;
    }

    /**
     * {@code POST  /do-kim-mds} : Create a new doKimMd.
     *
     * @param doKimMdDTO the doKimMdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new doKimMdDTO, or with status {@code 400 (Bad Request)} if the doKimMd has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/do-kim-mds")
    public ResponseEntity<DoKimMdDTO> createDoKimMd(@RequestBody DoKimMdDTO doKimMdDTO) throws URISyntaxException {
        log.debug("REST request to save DoKimMd : {}", doKimMdDTO);
        if (doKimMdDTO.getId() != null) {
            throw new BadRequestAlertException("A new doKimMd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoKimMdDTO result = doKimMdService.save(doKimMdDTO);
        return ResponseEntity.created(new URI("/api/do-kim-mds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /do-kim-mds} : Updates an existing doKimMd.
     *
     * @param doKimMdDTO the doKimMdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated doKimMdDTO,
     * or with status {@code 400 (Bad Request)} if the doKimMdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doKimMdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/do-kim-mds")
    public ResponseEntity<DoKimMdDTO> updateDoKimMd(@RequestBody DoKimMdDTO doKimMdDTO) throws URISyntaxException {
        log.debug("REST request to update DoKimMd : {}", doKimMdDTO);
        if (doKimMdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoKimMdDTO result = doKimMdService.save(doKimMdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, doKimMdDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /do-kim-mds} : get all the doKimMds.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of doKimMds in body.
     */
    @GetMapping("/do-kim-mds")
    public List<DoKimMdDTO> getAllDoKimMds() {
        log.debug("REST request to get all DoKimMds");
        return doKimMdService.findAll();
    }

    /**
     * {@code GET  /do-kim-mds/:id} : get the "id" doKimMd.
     *
     * @param id the id of the doKimMdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the doKimMdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/do-kim-mds/{id}")
    public ResponseEntity<DoKimMdDTO> getDoKimMd(@PathVariable Long id) {
        log.debug("REST request to get DoKimMd : {}", id);
        Optional<DoKimMdDTO> doKimMdDTO = doKimMdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(doKimMdDTO);
    }

    /**
     * {@code DELETE  /do-kim-mds/:id} : delete the "id" doKimMd.
     *
     * @param id the id of the doKimMdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/do-kim-mds/{id}")
    public ResponseEntity<Void> deleteDoKimMd(@PathVariable Long id) {
        log.debug("REST request to delete DoKimMd : {}", id);
        doKimMdService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
