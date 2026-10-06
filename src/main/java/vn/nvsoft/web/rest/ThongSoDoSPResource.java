package vn.nvsoft.web.rest;

import vn.nvsoft.service.ThongSoDoSPService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ThongSoDoSPDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.ThongSoDoSP}.
 */
@RestController
@RequestMapping("/api")
public class ThongSoDoSPResource {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoSPResource.class);

    private static final String ENTITY_NAME = "thongSoDoSP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongSoDoSPService thongSoDoSPService;

    public ThongSoDoSPResource(ThongSoDoSPService thongSoDoSPService) {
        this.thongSoDoSPService = thongSoDoSPService;
    }

    /**
     * {@code POST  /thong-so-do-sps} : Create a new thongSoDoSP.
     *
     * @param thongSoDoSPDTO the thongSoDoSPDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongSoDoSPDTO, or with status {@code 400 (Bad Request)} if the thongSoDoSP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thong-so-do-sps")
    public ResponseEntity<ThongSoDoSPDTO> createThongSoDoSP(@RequestBody ThongSoDoSPDTO thongSoDoSPDTO) throws URISyntaxException {
        log.debug("REST request to save ThongSoDoSP : {}", thongSoDoSPDTO);
        if (thongSoDoSPDTO.getId() != null) {
            throw new BadRequestAlertException("A new thongSoDoSP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongSoDoSPDTO result = thongSoDoSPService.save(thongSoDoSPDTO);
        return ResponseEntity.created(new URI("/api/thong-so-do-sps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thong-so-do-sps} : Updates an existing thongSoDoSP.
     *
     * @param thongSoDoSPDTO the thongSoDoSPDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongSoDoSPDTO,
     * or with status {@code 400 (Bad Request)} if the thongSoDoSPDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongSoDoSPDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thong-so-do-sps")
    public ResponseEntity<ThongSoDoSPDTO> updateThongSoDoSP(@RequestBody ThongSoDoSPDTO thongSoDoSPDTO) throws URISyntaxException {
        log.debug("REST request to update ThongSoDoSP : {}", thongSoDoSPDTO);
        if (thongSoDoSPDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongSoDoSPDTO result = thongSoDoSPService.save(thongSoDoSPDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongSoDoSPDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /thong-so-do-sps} : get all the thongSoDoSPS.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoDoSPS in body.
     */
    @GetMapping("/thong-so-do-sps")
    public ResponseEntity<List<ThongSoDoSPDTO>> getAllThongSoDoSPS(Pageable pageable) {
        log.debug("REST request to get a page of ThongSoDoSPS");
        Page<ThongSoDoSPDTO> page = thongSoDoSPService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-so-do-sps/:id} : get the "id" thongSoDoSP.
     *
     * @param id the id of the thongSoDoSPDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongSoDoSPDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thong-so-do-sps/{id}")
    public ResponseEntity<ThongSoDoSPDTO> getThongSoDoSP(@PathVariable Long id) {
        log.debug("REST request to get ThongSoDoSP : {}", id);
        Optional<ThongSoDoSPDTO> thongSoDoSPDTO = thongSoDoSPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongSoDoSPDTO);
    }

    /**
     * {@code DELETE  /thong-so-do-sps/:id} : delete the "id" thongSoDoSP.
     *
     * @param id the id of the thongSoDoSPDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thong-so-do-sps/{id}")
    public ResponseEntity<Void> deleteThongSoDoSP(@PathVariable Long id) {
        log.debug("REST request to delete ThongSoDoSP : {}", id);
        thongSoDoSPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /thong-so-do-sps/by-pro-sp/:proSPId} : get all the thongSoDoSPS by ProSanPham Id
     *

     * @param proSPId ProSanPham Id

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoDoSPS in body.
     */
    @GetMapping("/thong-so-do-sps/by-pro-sp/{proSPId}")
    public ResponseEntity<List<ThongSoDoSPDTO>> getAllThongSoDoSPSByProSanPhamID(@PathVariable Long proSPId) {
        log.debug("REST request to get a list of ThongSoDoSPS by ProSanPham Id");
        List<ThongSoDoSPDTO> results = thongSoDoSPService.getBySanPhamId(proSPId);
        return ResponseEntity.ok(results);
    }

    /**
     * {@code GET  /thong-so-do-sps/by-pro-sp-and-chuyen/:proSPId/:chuyen} : get all the thongSoDoSPS by ProSanPham Id
     *

     * @param proSPId ProSanPham Id
     * @param proSPId ProSanPham Id

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoDoSPS in body.
     */
    @GetMapping("/thong-so-do-sps/by-pro-sp-and-chuyen/{proSPId}/{chuyen}")
    public ResponseEntity<List<ThongSoDoSPDTO>> getAllThongSoDoSPSByProSanPhamIDAndChuyen(@PathVariable Long proSPId, @PathVariable Long chuyen) {
        log.debug("REST request to get a list of ThongSoDoSPS by ProSanPham Id and chuyen");
        List<ThongSoDoSPDTO> results = thongSoDoSPService.getBySanPhamIdAndChuyenId(proSPId, chuyen);
        return ResponseEntity.ok(results);
    }
}
