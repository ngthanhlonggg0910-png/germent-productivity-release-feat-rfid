package vn.nvsoft.web.rest;

import java.util.ArrayList;
import vn.nvsoft.service.SysRoleHasFunctionService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.SysRoleHasFunctionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import vn.nvsoft.service.vm.SysGroupModuleVM;

/**
 * REST controller for managing {@link vn.nvsoft.domain.SysRoleHasFunction}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleHasFunctionResource {

    private final Logger log = LoggerFactory.getLogger(SysRoleHasFunctionResource.class);

    private static final String ENTITY_NAME = "sysRoleHasFunction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysRoleHasFunctionService sysRoleHasFunctionService;

    public SysRoleHasFunctionResource(SysRoleHasFunctionService sysRoleHasFunctionService) {
        this.sysRoleHasFunctionService = sysRoleHasFunctionService;
    }

    /**
     * {@code POST  /sys-role-has-functions} : Create a new sysRoleHasFunction.
     *
     * @param sysRoleHasFunctionDTO the sysRoleHasFunctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysRoleHasFunctionDTO, or with status {@code 400 (Bad Request)} if the sysRoleHasFunction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-role-has-functions")
    public ResponseEntity<SysRoleHasFunctionDTO> createSysRoleHasFunction(@RequestBody SysRoleHasFunctionDTO sysRoleHasFunctionDTO) throws URISyntaxException {
        log.debug("REST request to save SysRoleHasFunction : {}", sysRoleHasFunctionDTO);
        if (sysRoleHasFunctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysRoleHasFunction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRoleHasFunctionDTO result = sysRoleHasFunctionService.save(sysRoleHasFunctionDTO);
        return ResponseEntity.created(new URI("/api/sys-role-has-functions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-role-has-functions} : Updates an existing sysRoleHasFunction.
     *
     * @param sysRoleHasFunctionDTO the sysRoleHasFunctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysRoleHasFunctionDTO,
     * or with status {@code 400 (Bad Request)} if the sysRoleHasFunctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysRoleHasFunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-role-has-functions")
    public ResponseEntity<SysRoleHasFunctionDTO> updateSysRoleHasFunction(@RequestBody SysRoleHasFunctionDTO sysRoleHasFunctionDTO) throws URISyntaxException {
        log.debug("REST request to update SysRoleHasFunction : {}", sysRoleHasFunctionDTO);
        if (sysRoleHasFunctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRoleHasFunctionDTO result = sysRoleHasFunctionService.save(sysRoleHasFunctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysRoleHasFunctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-role-has-functions} : get all the sysRoleHasFunctions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysRoleHasFunctions in body.
     */
    @GetMapping("/sys-role-has-functions")
    public ResponseEntity<List<SysRoleHasFunctionDTO>> getAllSysRoleHasFunctions(Pageable pageable) {
        log.debug("REST request to get a page of SysRoleHasFunctions");
        Page<SysRoleHasFunctionDTO> page = sysRoleHasFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-role-has-functions/:id} : get the "id" sysRoleHasFunction.
     *
     * @param id the id of the sysRoleHasFunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysRoleHasFunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-role-has-functions/{id}")
    public ResponseEntity<SysRoleHasFunctionDTO> getSysRoleHasFunction(@PathVariable Long id) {
        log.debug("REST request to get SysRoleHasFunction : {}", id);
        Optional<SysRoleHasFunctionDTO> sysRoleHasFunctionDTO = sysRoleHasFunctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRoleHasFunctionDTO);
    }

    /**
     * {@code DELETE  /sys-role-has-functions/:id} : delete the "id" sysRoleHasFunction.
     *
     * @param id the id of the sysRoleHasFunctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-role-has-functions/{id}")
    public ResponseEntity<Void> deleteSysRoleHasFunction(@PathVariable Long id) {
        log.debug("REST request to delete SysRoleHasFunction : {}", id);
        sysRoleHasFunctionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /sys-role-has-functions/function} : get all the group module, function, permission of current login user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of SysGroupModuleVM in body.
     */
    @GetMapping("/sys-role-has-functions/function")
    public ResponseEntity<List<SysGroupModuleVM>> getAllPermissionOfCurrentUser() {
        log.debug("REST request to get all SysGroupModuleVM of current login user");
        List<SysGroupModuleVM> results = sysRoleHasFunctionService.getAllPermissions(true);
        return ResponseEntity.ok().body(results);
    }

    /**
     * {@code GET  /sys-role-has-functions/function/all} : get all the group module, function, permission of current login user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of SysGroupModuleVM in body.
     */
    @GetMapping("/sys-role-has-functions/function/all")
    public ResponseEntity<List<SysGroupModuleVM>> getAllPermissions() {
        log.debug("REST request to get all SysGroupModuleVM");
        List<SysGroupModuleVM> results;
        results = sysRoleHasFunctionService.getAllPermissions(false);
        return ResponseEntity.ok().body(results);
    }

    /**
     * {@code GET  /sys-role-has-functions/function/all-by-role} : get all the group module, function, permission of current login user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of SysGroupModuleVM in body.
     */
    @GetMapping("/sys-role-has-functions/function/all-by-role")
    public ResponseEntity<List<SysGroupModuleVM>> getAllPermissionsByRole(@RequestParam(name = "role") String role) {
        log.debug("REST request to get all SysGroupModuleVM of role " + role);
        List<SysGroupModuleVM> results;
        if (role == null || role.isEmpty()) {
            results = new ArrayList<>();
        } else {
            results = sysRoleHasFunctionService.getAllPermissionByRole(role);
        }
        return ResponseEntity.ok().body(results);
    }
}
