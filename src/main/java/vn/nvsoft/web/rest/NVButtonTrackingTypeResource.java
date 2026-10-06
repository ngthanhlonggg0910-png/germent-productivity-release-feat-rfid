package vn.nvsoft.web.rest;

import vn.nvsoft.service.NVButtonTrackingTypeService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.NVButtonTrackingType}.
 */
@RestController
@RequestMapping("/api")
public class NVButtonTrackingTypeResource {

    private final Logger log = LoggerFactory.getLogger(NVButtonTrackingTypeResource.class);

    private static final String ENTITY_NAME = "nVButtonTrackingType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NVButtonTrackingTypeService nVButtonTrackingTypeService;

    public NVButtonTrackingTypeResource(NVButtonTrackingTypeService nVButtonTrackingTypeService) {
        this.nVButtonTrackingTypeService = nVButtonTrackingTypeService;
    }

    /**
     * {@code POST  /nv-button-tracking-types} : Create a new nVButtonTrackingType.
     *
     * @param nVButtonTrackingTypeDTO the nVButtonTrackingTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nVButtonTrackingTypeDTO, or with status {@code 400 (Bad Request)} if the nVButtonTrackingType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nv-button-tracking-types")
    public ResponseEntity<NVButtonTrackingTypeDTO> createNVButtonTrackingType(@Valid @RequestBody NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO) throws URISyntaxException {
        log.debug("REST request to save NVButtonTrackingType : {}", nVButtonTrackingTypeDTO);
        if (nVButtonTrackingTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new nVButtonTrackingType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NVButtonTrackingTypeDTO result = nVButtonTrackingTypeService.save(nVButtonTrackingTypeDTO);
        return ResponseEntity.created(new URI("/api/nv-button-tracking-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nv-button-tracking-types} : Updates an existing nVButtonTrackingType.
     *
     * @param nVButtonTrackingTypeDTO the nVButtonTrackingTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nVButtonTrackingTypeDTO,
     * or with status {@code 400 (Bad Request)} if the nVButtonTrackingTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nVButtonTrackingTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nv-button-tracking-types")
    public ResponseEntity<NVButtonTrackingTypeDTO> updateNVButtonTrackingType(@Valid @RequestBody NVButtonTrackingTypeDTO nVButtonTrackingTypeDTO) throws URISyntaxException {
        log.debug("REST request to update NVButtonTrackingType : {}", nVButtonTrackingTypeDTO);
        if (nVButtonTrackingTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NVButtonTrackingTypeDTO result = nVButtonTrackingTypeService.save(nVButtonTrackingTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nVButtonTrackingTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nv-button-tracking-types} : get all the nVButtonTrackingTypes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nVButtonTrackingTypes in body.
     */
    @GetMapping("/nv-button-tracking-types")
    public ResponseEntity<List<NVButtonTrackingTypeDTO>> getAllNVButtonTrackingTypes(Pageable pageable) {
        log.debug("REST request to get a page of NVButtonTrackingTypes");
        Page<NVButtonTrackingTypeDTO> page = nVButtonTrackingTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nv-button-tracking-types/:id} : get the "id" nVButtonTrackingType.
     *
     * @param id the id of the nVButtonTrackingTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nVButtonTrackingTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nv-button-tracking-types/{id}")
    public ResponseEntity<NVButtonTrackingTypeDTO> getNVButtonTrackingType(@PathVariable Long id) {
        log.debug("REST request to get NVButtonTrackingType : {}", id);
        Optional<NVButtonTrackingTypeDTO> nVButtonTrackingTypeDTO = nVButtonTrackingTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nVButtonTrackingTypeDTO);
    }

    /**
     * {@code DELETE  /nv-button-tracking-types/:id} : delete the "id" nVButtonTrackingType.
     *
     * @param id the id of the nVButtonTrackingTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nv-button-tracking-types/{id}")
    public ResponseEntity<Void> deleteNVButtonTrackingType(@PathVariable Long id) {
        log.debug("REST request to delete NVButtonTrackingType : {}", id);
        nVButtonTrackingTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
