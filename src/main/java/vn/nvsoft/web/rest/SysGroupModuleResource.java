package vn.nvsoft.web.rest;

import vn.nvsoft.service.SysGroupModuleService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.SysGroupModuleDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.SysGroupModule}.
 */
@RestController
@RequestMapping("/api")
public class SysGroupModuleResource {

    private final Logger log = LoggerFactory.getLogger(SysGroupModuleResource.class);

    private static final String ENTITY_NAME = "sysGroupModule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysGroupModuleService sysGroupModuleService;

    public SysGroupModuleResource(SysGroupModuleService sysGroupModuleService) {
        this.sysGroupModuleService = sysGroupModuleService;
    }

    /**
     * {@code POST  /sys-group-modules} : Create a new sysGroupModule.
     *
     * @param sysGroupModuleDTO the sysGroupModuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysGroupModuleDTO, or with status {@code 400 (Bad Request)} if the sysGroupModule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-group-modules")
    public ResponseEntity<SysGroupModuleDTO> createSysGroupModule(@Valid @RequestBody SysGroupModuleDTO sysGroupModuleDTO) throws URISyntaxException {
        log.debug("REST request to save SysGroupModule : {}", sysGroupModuleDTO);
        if (sysGroupModuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysGroupModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysGroupModuleDTO result = sysGroupModuleService.save(sysGroupModuleDTO);
        return ResponseEntity.created(new URI("/api/sys-group-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-group-modules} : Updates an existing sysGroupModule.
     *
     * @param sysGroupModuleDTO the sysGroupModuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysGroupModuleDTO,
     * or with status {@code 400 (Bad Request)} if the sysGroupModuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysGroupModuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-group-modules")
    public ResponseEntity<SysGroupModuleDTO> updateSysGroupModule(@Valid @RequestBody SysGroupModuleDTO sysGroupModuleDTO) throws URISyntaxException {
        log.debug("REST request to update SysGroupModule : {}", sysGroupModuleDTO);
        if (sysGroupModuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysGroupModuleDTO result = sysGroupModuleService.save(sysGroupModuleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysGroupModuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-group-modules} : get all the sysGroupModules.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysGroupModules in body.
     */
    @GetMapping("/sys-group-modules")
    public ResponseEntity<List<SysGroupModuleDTO>> getAllSysGroupModules(Pageable pageable) {
        log.debug("REST request to get a page of SysGroupModules");
        Page<SysGroupModuleDTO> page = sysGroupModuleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-group-modules/:id} : get the "id" sysGroupModule.
     *
     * @param id the id of the sysGroupModuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysGroupModuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-group-modules/{id}")
    public ResponseEntity<SysGroupModuleDTO> getSysGroupModule(@PathVariable Long id) {
        log.debug("REST request to get SysGroupModule : {}", id);
        Optional<SysGroupModuleDTO> sysGroupModuleDTO = sysGroupModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysGroupModuleDTO);
    }

    /**
     * {@code DELETE  /sys-group-modules/:id} : delete the "id" sysGroupModule.
     *
     * @param id the id of the sysGroupModuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-group-modules/{id}")
    public ResponseEntity<Void> deleteSysGroupModule(@PathVariable Long id) {
        log.debug("REST request to delete SysGroupModule : {}", id);
        sysGroupModuleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
