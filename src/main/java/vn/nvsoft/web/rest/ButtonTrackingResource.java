package vn.nvsoft.web.rest;

import vn.nvsoft.service.ButtonTrackingService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ButtonTrackingDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ButtonTracking}.
 */
@RestController
@RequestMapping("/api")
public class ButtonTrackingResource {

    private final Logger log = LoggerFactory.getLogger(ButtonTrackingResource.class);

    private static final String ENTITY_NAME = "buttonTracking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ButtonTrackingService buttonTrackingService;

    public ButtonTrackingResource(ButtonTrackingService buttonTrackingService) {
        this.buttonTrackingService = buttonTrackingService;
    }

    /**
     * {@code POST  /button-trackings} : Create a new buttonTracking.
     *
     * @param buttonTrackingDTO the buttonTrackingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new buttonTrackingDTO, or with status {@code 400 (Bad Request)} if the buttonTracking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/button-trackings")
    public ResponseEntity<ButtonTrackingDTO> createButtonTracking(@RequestBody ButtonTrackingDTO buttonTrackingDTO) throws URISyntaxException {
        log.debug("REST request to save ButtonTracking : {}", buttonTrackingDTO);
        if (buttonTrackingDTO.getId() != null) {
            throw new BadRequestAlertException("A new buttonTracking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ButtonTrackingDTO result = buttonTrackingService.save(buttonTrackingDTO);
        return ResponseEntity.created(new URI("/api/button-trackings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /button-trackings} : Updates an existing buttonTracking.
     *
     * @param buttonTrackingDTO the buttonTrackingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated buttonTrackingDTO,
     * or with status {@code 400 (Bad Request)} if the buttonTrackingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the buttonTrackingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/button-trackings")
    public ResponseEntity<ButtonTrackingDTO> updateButtonTracking(@RequestBody ButtonTrackingDTO buttonTrackingDTO) throws URISyntaxException {
        log.debug("REST request to update ButtonTracking : {}", buttonTrackingDTO);
        if (buttonTrackingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ButtonTrackingDTO result = buttonTrackingService.save(buttonTrackingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, buttonTrackingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /button-trackings} : get all the buttonTrackings.
     *

     * @param pageable the pagination information.

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of buttonTrackings in body.
     */
    @GetMapping("/button-trackings")
    public ResponseEntity<List<ButtonTrackingDTO>> getAllButtonTrackings(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("nvbutton-is-null".equals(filter)) {
            log.debug("REST request to get all ButtonTrackings where nVButton is null");
            return new ResponseEntity<>(buttonTrackingService.findAllWhereNVButtonIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ButtonTrackings");
        Page<ButtonTrackingDTO> page = buttonTrackingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /button-trackings/:id} : get the "id" buttonTracking.
     *
     * @param id the id of the buttonTrackingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the buttonTrackingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/button-trackings/{id}")
    public ResponseEntity<ButtonTrackingDTO> getButtonTracking(@PathVariable Long id) {
        log.debug("REST request to get ButtonTracking : {}", id);
        Optional<ButtonTrackingDTO> buttonTrackingDTO = buttonTrackingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(buttonTrackingDTO);
    }

    /**
     * {@code DELETE  /button-trackings/:id} : delete the "id" buttonTracking.
     *
     * @param id the id of the buttonTrackingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/button-trackings/{id}")
    public ResponseEntity<Void> deleteButtonTracking(@PathVariable Long id) {
        log.debug("REST request to delete ButtonTracking : {}", id);
        buttonTrackingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
