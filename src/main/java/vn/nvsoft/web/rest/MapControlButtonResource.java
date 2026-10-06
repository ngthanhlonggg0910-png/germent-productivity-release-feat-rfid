package vn.nvsoft.web.rest;

import vn.nvsoft.service.MapControlButtonService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.MapControlButtonDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.MapControlButton}.
 */
@RestController
@RequestMapping("/api")
public class MapControlButtonResource {

    private final Logger log = LoggerFactory.getLogger(MapControlButtonResource.class);

    private static final String ENTITY_NAME = "mapControlButton";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MapControlButtonService mapControlButtonService;

    public MapControlButtonResource(MapControlButtonService mapControlButtonService) {
        this.mapControlButtonService = mapControlButtonService;
    }

    /**
     * {@code POST  /map-control-buttons} : Create a new mapControlButton.
     *
     * @param mapControlButtonDTO the mapControlButtonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mapControlButtonDTO, or with status {@code 400 (Bad Request)} if the mapControlButton has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/map-control-buttons")
    public ResponseEntity<MapControlButtonDTO> createMapControlButton(@RequestBody MapControlButtonDTO mapControlButtonDTO) throws URISyntaxException {
        log.debug("REST request to save MapControlButton : {}", mapControlButtonDTO);
        if (mapControlButtonDTO.getId() != null) {
            throw new BadRequestAlertException("A new mapControlButton cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapControlButtonDTO result = mapControlButtonService.save(mapControlButtonDTO);
        return ResponseEntity.created(new URI("/api/map-control-buttons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /map-control-buttons} : Updates an existing mapControlButton.
     *
     * @param mapControlButtonDTO the mapControlButtonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mapControlButtonDTO,
     * or with status {@code 400 (Bad Request)} if the mapControlButtonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mapControlButtonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/map-control-buttons")
    public ResponseEntity<MapControlButtonDTO> updateMapControlButton(@RequestBody MapControlButtonDTO mapControlButtonDTO) throws URISyntaxException {
        log.debug("REST request to update MapControlButton : {}", mapControlButtonDTO);
        if (mapControlButtonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapControlButtonDTO result = mapControlButtonService.save(mapControlButtonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mapControlButtonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /map-control-buttons} : get all the mapControlButtons.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mapControlButtons in body.
     */
    @GetMapping("/map-control-buttons")
    public ResponseEntity<List<MapControlButtonDTO>> getAllMapControlButtons(Pageable pageable) {
        log.debug("REST request to get a page of MapControlButtons");
        Page<MapControlButtonDTO> page = mapControlButtonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /map-control-buttons/:id} : get the "id" mapControlButton.
     *
     * @param id the id of the mapControlButtonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mapControlButtonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/map-control-buttons/{id}")
    public ResponseEntity<MapControlButtonDTO> getMapControlButton(@PathVariable Long id) {
        log.debug("REST request to get MapControlButton : {}", id);
        Optional<MapControlButtonDTO> mapControlButtonDTO = mapControlButtonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mapControlButtonDTO);
    }

    /**
     * {@code DELETE  /map-control-buttons/:id} : delete the "id" mapControlButton.
     *
     * @param id the id of the mapControlButtonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/map-control-buttons/{id}")
    public ResponseEntity<Void> deleteMapControlButton(@PathVariable Long id) {
        log.debug("REST request to delete MapControlButton : {}", id);
        mapControlButtonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
