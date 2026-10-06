package vn.nvsoft.web.rest;

import vn.nvsoft.service.DungSaiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.DungSaiDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.DungSai}.
 */
@RestController
@RequestMapping("/api")
public class DungSaiResource {

    private final Logger log = LoggerFactory.getLogger(DungSaiResource.class);

    private static final String ENTITY_NAME = "dungSai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DungSaiService dungSaiService;

    public DungSaiResource(DungSaiService dungSaiService) {
        this.dungSaiService = dungSaiService;
    }

    /**
     * {@code POST  /dung-sais} : Create a new dungSai.
     *
     * @param dungSaiDTO the dungSaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dungSaiDTO, or with status {@code 400 (Bad Request)} if the dungSai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dung-sais")
    public ResponseEntity<DungSaiDTO> createDungSai(@RequestBody DungSaiDTO dungSaiDTO) throws URISyntaxException {
        log.debug("REST request to save DungSai : {}", dungSaiDTO);
        if (dungSaiDTO.getId() != null) {
            throw new BadRequestAlertException("A new dungSai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DungSaiDTO result = dungSaiService.save(dungSaiDTO);
        return ResponseEntity.created(new URI("/api/dung-sais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dung-sais} : Updates an existing dungSai.
     *
     * @param dungSaiDTO the dungSaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dungSaiDTO,
     * or with status {@code 400 (Bad Request)} if the dungSaiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dungSaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dung-sais")
    public ResponseEntity<DungSaiDTO> updateDungSai(@RequestBody DungSaiDTO dungSaiDTO) throws URISyntaxException {
        log.debug("REST request to update DungSai : {}", dungSaiDTO);
        if (dungSaiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DungSaiDTO result = dungSaiService.save(dungSaiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dungSaiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dung-sais} : get all the dungSais.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dungSais in body.
     */
    @GetMapping("/dung-sais")
    public ResponseEntity<List<DungSaiDTO>> getAllDungSais(Pageable pageable) {
        log.debug("REST request to get a page of DungSais");
        return ResponseEntity.ok().body(dungSaiService.findAllDungSai());
    }

    /**
     * {@code GET  /dung-sais/:id} : get the "id" dungSai.
     *
     * @param id the id of the dungSaiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dungSaiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dung-sais/{id}")
    public ResponseEntity<DungSaiDTO> getDungSai(@PathVariable Long id) {
        log.debug("REST request to get DungSai : {}", id);
        Optional<DungSaiDTO> dungSaiDTO = dungSaiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dungSaiDTO);
    }

    /**
     * {@code DELETE  /dung-sais/:id} : delete the "id" dungSai.
     *
     * @param id the id of the dungSaiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dung-sais/{id}")
    public ResponseEntity<Void> deleteDungSai(@PathVariable Long id) {
        log.debug("REST request to delete DungSai : {}", id);
        dungSaiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
