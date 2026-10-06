package vn.nvsoft.web.rest;

import vn.nvsoft.service.DeviceTokenService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.DeviceTokenDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.DeviceToken}.
 */
@RestController
@RequestMapping("/api")
public class DeviceTokenResource {

    private final Logger log = LoggerFactory.getLogger(DeviceTokenResource.class);

    private static final String ENTITY_NAME = "deviceToken";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeviceTokenService deviceTokenService;

    public DeviceTokenResource(DeviceTokenService deviceTokenService) {
        this.deviceTokenService = deviceTokenService;
    }

    /**
     * {@code POST  /device-tokens} : Create a new deviceToken.
     *
     * @param deviceTokenDTO the deviceTokenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deviceTokenDTO, or with status {@code 400 (Bad Request)} if the deviceToken has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/device-tokens")
    public ResponseEntity<DeviceTokenDTO> createDeviceToken(@Valid @RequestBody DeviceTokenDTO deviceTokenDTO) throws URISyntaxException {
        log.debug("REST request to save DeviceToken : {}", deviceTokenDTO);
        if (deviceTokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new deviceToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeviceTokenDTO result = deviceTokenService.save(deviceTokenDTO);
        return ResponseEntity.created(new URI("/api/device-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /device-tokens} : Updates an existing deviceToken.
     *
     * @param deviceTokenDTO the deviceTokenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceTokenDTO,
     * or with status {@code 400 (Bad Request)} if the deviceTokenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviceTokenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/device-tokens")
    public ResponseEntity<DeviceTokenDTO> updateDeviceToken(@Valid @RequestBody DeviceTokenDTO deviceTokenDTO) throws URISyntaxException {
        log.debug("REST request to update DeviceToken : {}", deviceTokenDTO);
        if (deviceTokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeviceTokenDTO result = deviceTokenService.save(deviceTokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deviceTokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /device-tokens/create-update} : Updates an existing deviceToken.
     *
     * @param deviceTokenDTO the deviceTokenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deviceTokenDTO,
     * or with status {@code 400 (Bad Request)} if the deviceTokenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deviceTokenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/device-tokens/create-update")
    public ResponseEntity<DeviceTokenDTO> createOrUpdateDeviceToken(@Valid @RequestBody DeviceTokenDTO deviceTokenDTO) throws URISyntaxException {
        log.info("========== API: PUT /api/device-tokens/create-update ==========");
        log.info("Request Body - User: {}, Device: {}", deviceTokenDTO.getUser(), deviceTokenDTO.getDevice());
        log.debug("Full DeviceTokenDTO: {}", deviceTokenDTO);

        DeviceTokenDTO result = deviceTokenService.createOrUpdate(deviceTokenDTO);

        log.info("Response - DeviceToken ID: {}, User: {}", result.getId(), result.getUser());
        log.info("========== End API: PUT /api/device-tokens/create-update ==========");

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * {@code GET  /device-tokens} : get all the deviceTokens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deviceTokens in body.
     */
    @GetMapping("/device-tokens")
    public List<DeviceTokenDTO> getAllDeviceTokens() {
        log.debug("REST request to get all DeviceTokens");
        return deviceTokenService.findAll();
    }

    /**
     * {@code GET  /device-tokens/:id} : get the "id" deviceToken.
     *
     * @param id the id of the deviceTokenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deviceTokenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/device-tokens/{id}")
    public ResponseEntity<DeviceTokenDTO> getDeviceToken(@PathVariable Long id) {
        log.debug("REST request to get DeviceToken : {}", id);
        Optional<DeviceTokenDTO> deviceTokenDTO = deviceTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deviceTokenDTO);
    }

    /**
     * {@code DELETE  /device-tokens/:id} : delete the "id" deviceToken.
     *
     * @param id the id of the deviceTokenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/device-tokens/{id}")
    public ResponseEntity<Void> deleteDeviceToken(@PathVariable Long id) {
        log.debug("REST request to delete DeviceToken : {}", id);
        deviceTokenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
