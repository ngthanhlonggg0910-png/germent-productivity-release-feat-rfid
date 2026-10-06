package vn.nvsoft.web.rest;

import vn.nvsoft.service.MapButtonActionService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.MapButtonActionDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.MapButtonAction}.
 */
@RestController
@RequestMapping("/api")
public class MapButtonActionResource {

    private final Logger log = LoggerFactory.getLogger(MapButtonActionResource.class);

    private static final String ENTITY_NAME = "mapButtonAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MapButtonActionService mapButtonActionService;

    public MapButtonActionResource(MapButtonActionService mapButtonActionService) {
        this.mapButtonActionService = mapButtonActionService;
    }

    /**
     * {@code POST  /map-button-actions} : Create a new mapButtonAction.
     *
     * @param mapButtonActionDTO the mapButtonActionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mapButtonActionDTO, or with status {@code 400 (Bad Request)} if the mapButtonAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/map-button-actions")
    public ResponseEntity<MapButtonActionDTO> createMapButtonAction(@RequestBody MapButtonActionDTO mapButtonActionDTO) throws URISyntaxException {
        log.debug("REST request to save MapButtonAction : {}", mapButtonActionDTO);
        if (mapButtonActionDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapButtonAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapButtonActionDTO result = mapButtonActionService.save(mapButtonActionDTO);
        return ResponseEntity.created(new URI("/api/map-button-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /map-button-actions} : Updates an existing mapButtonAction.
     *
     * @param mapButtonActionDTO the mapButtonActionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mapButtonActionDTO,
     * or with status {@code 400 (Bad Request)} if the mapButtonActionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mapButtonActionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/map-button-actions")
    public ResponseEntity<MapButtonActionDTO> updateMapButtonAction(@RequestBody MapButtonActionDTO mapButtonActionDTO) throws URISyntaxException {
        log.debug("REST request to update MapButtonAction : {}", mapButtonActionDTO);
        if (mapButtonActionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapButtonActionDTO result = mapButtonActionService.save(mapButtonActionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mapButtonActionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /map-button-actions} : get all the mapButtonActions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mapButtonActions in body.
     */
    @GetMapping("/map-button-actions")
    public ResponseEntity<List<MapButtonActionDTO>> getAllMapButtonActions(Pageable pageable) {
        log.debug("REST request to get a page of MapButtonActions");
        Page<MapButtonActionDTO> page = mapButtonActionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /map-button-actions/:id} : get the "id" mapButtonAction.
     *
     * @param id the id of the mapButtonActionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mapButtonActionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/map-button-actions/{id}")
    public ResponseEntity<MapButtonActionDTO> getMapButtonAction(@PathVariable Long id) {
        log.debug("REST request to get MapButtonAction : {}", id);
        Optional<MapButtonActionDTO> mapButtonActionDTO = mapButtonActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mapButtonActionDTO);
    }

    /**
     * {@code DELETE  /map-button-actions/:id} : delete the "id" mapButtonAction.
     *
     * @param id the id of the mapButtonActionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/map-button-actions/{id}")
    public ResponseEntity<Void> deleteMapButtonAction(@PathVariable Long id) {
        log.debug("REST request to delete MapButtonAction : {}", id);
        mapButtonActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
