package vn.nvsoft.web.rest;

import vn.nvsoft.service.SysFunctionService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.SysFunctionDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.SysFunction}.
 */
@RestController
@RequestMapping("/api")
public class SysFunctionResource {

    private final Logger log = LoggerFactory.getLogger(SysFunctionResource.class);

    private static final String ENTITY_NAME = "sysFunction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysFunctionService sysFunctionService;

    public SysFunctionResource(SysFunctionService sysFunctionService) {
        this.sysFunctionService = sysFunctionService;
    }

    /**
     * {@code POST  /sys-functions} : Create a new sysFunction.
     *
     * @param sysFunctionDTO the sysFunctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysFunctionDTO, or with status {@code 400 (Bad Request)} if the sysFunction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-functions")
    public ResponseEntity<SysFunctionDTO> createSysFunction(@Valid @RequestBody SysFunctionDTO sysFunctionDTO) throws URISyntaxException {
        log.debug("REST request to save SysFunction : {}", sysFunctionDTO);
        if (sysFunctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new sysFunction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysFunctionDTO result = sysFunctionService.save(sysFunctionDTO);
        return ResponseEntity.created(new URI("/api/sys-functions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-functions} : Updates an existing sysFunction.
     *
     * @param sysFunctionDTO the sysFunctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysFunctionDTO,
     * or with status {@code 400 (Bad Request)} if the sysFunctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysFunctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-functions")
    public ResponseEntity<SysFunctionDTO> updateSysFunction(@Valid @RequestBody SysFunctionDTO sysFunctionDTO) throws URISyntaxException {
        log.debug("REST request to update SysFunction : {}", sysFunctionDTO);
        if (sysFunctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysFunctionDTO result = sysFunctionService.save(sysFunctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sysFunctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-functions} : get all the sysFunctions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysFunctions in body.
     */
    @GetMapping("/sys-functions")
    public ResponseEntity<List<SysFunctionDTO>> getAllSysFunctions(Pageable pageable) {
        log.debug("REST request to get a page of SysFunctions");
        Page<SysFunctionDTO> page = sysFunctionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-functions/:id} : get the "id" sysFunction.
     *
     * @param id the id of the sysFunctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysFunctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-functions/{id}")
    public ResponseEntity<SysFunctionDTO> getSysFunction(@PathVariable Long id) {
        log.debug("REST request to get SysFunction : {}", id);
        Optional<SysFunctionDTO> sysFunctionDTO = sysFunctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysFunctionDTO);
    }

    /**
     * {@code DELETE  /sys-functions/:id} : delete the "id" sysFunction.
     *
     * @param id the id of the sysFunctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-functions/{id}")
    public ResponseEntity<Void> deleteSysFunction(@PathVariable Long id) {
        log.debug("REST request to delete SysFunction : {}", id);
        sysFunctionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
