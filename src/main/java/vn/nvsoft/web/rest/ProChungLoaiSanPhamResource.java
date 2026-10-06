package vn.nvsoft.web.rest;

import vn.nvsoft.service.ProChungLoaiSanPhamService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProChungLoaiSanPhamDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ProChungLoaiSanPham}.
 */
@RestController
@RequestMapping("/api")
public class ProChungLoaiSanPhamResource {

    private final Logger log = LoggerFactory.getLogger(ProChungLoaiSanPhamResource.class);

    private static final String ENTITY_NAME = "proChungLoaiSanPham";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProChungLoaiSanPhamService proChungLoaiSanPhamService;

    public ProChungLoaiSanPhamResource(ProChungLoaiSanPhamService proChungLoaiSanPhamService) {
        this.proChungLoaiSanPhamService = proChungLoaiSanPhamService;
    }

    /**
     * {@code POST  /pro-chung-loai-san-phams} : Create a new proChungLoaiSanPham.
     *
     * @param proChungLoaiSanPhamDTO the proChungLoaiSanPhamDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proChungLoaiSanPhamDTO, or with status {@code 400 (Bad Request)} if the proChungLoaiSanPham has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-chung-loai-san-phams")
    public ResponseEntity<ProChungLoaiSanPhamDTO> createProChungLoaiSanPham(@RequestBody ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to save ProChungLoaiSanPham : {}", proChungLoaiSanPhamDTO);
        if (proChungLoaiSanPhamDTO.getId() != null) {
            throw new BadRequestAlertException("A new proChungLoaiSanPham cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProChungLoaiSanPhamDTO result = proChungLoaiSanPhamService.save(proChungLoaiSanPhamDTO);
        return ResponseEntity.created(new URI("/api/pro-chung-loai-san-phams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-chung-loai-san-phams} : Updates an existing proChungLoaiSanPham.
     *
     * @param proChungLoaiSanPhamDTO the proChungLoaiSanPhamDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proChungLoaiSanPhamDTO,
     * or with status {@code 400 (Bad Request)} if the proChungLoaiSanPhamDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proChungLoaiSanPhamDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-chung-loai-san-phams")
    public ResponseEntity<ProChungLoaiSanPhamDTO> updateProChungLoaiSanPham(@RequestBody ProChungLoaiSanPhamDTO proChungLoaiSanPhamDTO) throws URISyntaxException {
        log.debug("REST request to update ProChungLoaiSanPham : {}", proChungLoaiSanPhamDTO);
        if (proChungLoaiSanPhamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProChungLoaiSanPhamDTO result = proChungLoaiSanPhamService.save(proChungLoaiSanPhamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proChungLoaiSanPhamDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-chung-loai-san-phams} : get all the proChungLoaiSanPhams.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proChungLoaiSanPhams in body.
     */
    @GetMapping("/pro-chung-loai-san-phams")
    public ResponseEntity<List<ProChungLoaiSanPhamDTO>> getAllProChungLoaiSanPhams(Pageable pageable) {
        log.debug("REST request to get a page of ProChungLoaiSanPhams");
        Page<ProChungLoaiSanPhamDTO> page = proChungLoaiSanPhamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pro-chung-loai-san-phams/:id} : get the "id" proChungLoaiSanPham.
     *
     * @param id the id of the proChungLoaiSanPhamDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proChungLoaiSanPhamDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-chung-loai-san-phams/{id}")
    public ResponseEntity<ProChungLoaiSanPhamDTO> getProChungLoaiSanPham(@PathVariable Long id) {
        log.debug("REST request to get ProChungLoaiSanPham : {}", id);
        Optional<ProChungLoaiSanPhamDTO> proChungLoaiSanPhamDTO = proChungLoaiSanPhamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proChungLoaiSanPhamDTO);
    }

    /**
     * {@code DELETE  /pro-chung-loai-san-phams/:id} : delete the "id" proChungLoaiSanPham.
     *
     * @param id the id of the proChungLoaiSanPhamDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-chung-loai-san-phams/{id}")
    public ResponseEntity<Void> deleteProChungLoaiSanPham(@PathVariable Long id) {
        log.debug("REST request to delete ProChungLoaiSanPham : {}", id);
        proChungLoaiSanPhamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
