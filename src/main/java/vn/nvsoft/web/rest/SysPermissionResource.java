package vn.nvsoft.web.rest;

import vn.nvsoft.service.SysPermissionService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.SysPermissionDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.SysPermission}.
 */
@RestController
@RequestMapping("/api")
public class SysPermissionResource {

    private final Logger log = LoggerFactory.getLogger(SysPermissionResource.class);

    private static final String ENTITY_NAME = "sysPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysPermissionService sysPermissionService;

    public SysPermissionResource(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    /**
     * {@code POST  /sys-permissions} : Create a new sysPermission.
     *
     * @param sysPermissionDTO the sysPermissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysPermissionDTO, or with status {@code 400 (Bad Request)} if the sysPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-permissions")
    public ResponseEntity<SysPermissionDTO> createSysPermission(@Valid @RequestBody SysPermissionDTO sysPermissionDTO) throws URISyntaxException {
        log.debug("REST request to save SysPermission : {}", sysPermissionDTO);
        if (sysPermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPermissionDTO result = sysPermissionService.save(sysPermissionDTO);
        return ResponseEntity.created(new URI("/api/sys-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-permissions} : Updates an existing sysPermission.
     *
     * @param sysPermissionDTO the sysPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the sysPermissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-permissions")
    public ResponseEntity<SysPermissionDTO> updateSysPermission(@Valid @RequestBody SysPermissionDTO sysPermissionDTO) throws URISyntaxException {
        log.debug("REST request to update SysPermission : {}", sysPermissionDTO);
        if (sysPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysPermissionDTO result = sysPermissionService.save(sysPermissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysPermissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-permissions} : get all the sysPermissions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysPermissions in body.
     */
    @GetMapping("/sys-permissions")
    public ResponseEntity<List<SysPermissionDTO>> getAllSysPermissions(Pageable pageable) {
        log.debug("REST request to get a page of SysPermissions");
        Page<SysPermissionDTO> page = sysPermissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-permissions/:id} : get the "id" sysPermission.
     *
     * @param id the id of the sysPermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysPermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-permissions/{id}")
    public ResponseEntity<SysPermissionDTO> getSysPermission(@PathVariable Long id) {
        log.debug("REST request to get SysPermission : {}", id);
        Optional<SysPermissionDTO> sysPermissionDTO = sysPermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPermissionDTO);
    }

    /**
     * {@code DELETE  /sys-permissions/:id} : delete the "id" sysPermission.
     *
     * @param id the id of the sysPermissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-permissions/{id}")
    public ResponseEntity<Void> deleteSysPermission(@PathVariable Long id) {
        log.debug("REST request to delete SysPermission : {}", id);
        sysPermissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
