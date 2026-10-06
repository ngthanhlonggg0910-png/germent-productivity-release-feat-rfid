package vn.nvsoft.web.rest;

import vn.nvsoft.service.ThongSoThucThiService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ThongSoThucThiDTO;

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

import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ThongSoThucThi}.
 */
@RestController
@RequestMapping("/api")
public class ThongSoThucThiResource {

    private final Logger log = LoggerFactory.getLogger(ThongSoThucThiResource.class);

    private static final String ENTITY_NAME = "thongSoThucThi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongSoThucThiService thongSoThucThiService;

    public ThongSoThucThiResource(ThongSoThucThiService thongSoThucThiService) {
        this.thongSoThucThiService = thongSoThucThiService;
    }

    /**
     * {@code POST  /thong-so-thuc-this} : Create a new thongSoThucThi.
     *
     * @param thongSoThucThiDTO the thongSoThucThiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongSoThucThiDTO, or with status {@code 400 (Bad Request)} if the thongSoThucThi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thong-so-thuc-this")
    public ResponseEntity<ThongSoThucThiDTO> createThongSoThucThi(@RequestBody ThongSoThucThiDTO thongSoThucThiDTO) throws URISyntaxException {
        log.debug("REST request to save ThongSoThucThi : {}", thongSoThucThiDTO);
        if (thongSoThucThiDTO.getId() != null) {
            throw new BadRequestAlertException("A new thongSoThucThi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongSoThucThiDTO result = thongSoThucThiService.save(thongSoThucThiDTO);
        return ResponseEntity.created(new URI("/api/thong-so-thuc-this/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thong-so-thuc-this} : Updates an existing thongSoThucThi.
     *
     * @param thongSoThucThiDTO the thongSoThucThiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongSoThucThiDTO,
     * or with status {@code 400 (Bad Request)} if the thongSoThucThiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongSoThucThiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thong-so-thuc-this")
    public ResponseEntity<ThongSoThucThiDTO> updateThongSoThucThi(@RequestBody ThongSoThucThiDTO thongSoThucThiDTO) throws URISyntaxException {
        log.debug("REST request to update ThongSoThucThi : {}", thongSoThucThiDTO);
        if (thongSoThucThiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongSoThucThiDTO result = thongSoThucThiService.save(thongSoThucThiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongSoThucThiDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /thong-so-thuc-this} : get all the thongSoThucThis.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoThucThis in body.
     */
    @GetMapping("/thong-so-thuc-this")
    public ResponseEntity<List<ThongSoThucThiDTO>> getAllThongSoThucThis(Pageable pageable) {
        log.debug("REST request to get a page of ThongSoThucThis");
        Page<ThongSoThucThiDTO> page = thongSoThucThiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-so-thuc-this/:id} : get the "id" thongSoThucThi.
     *
     * @param id the id of the thongSoThucThiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongSoThucThiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thong-so-thuc-this/{id}")
    public ResponseEntity<ThongSoThucThiDTO> getThongSoThucThi(@PathVariable Long id) {
        log.debug("REST request to get ThongSoThucThi : {}", id);
        Optional<ThongSoThucThiDTO> thongSoThucThiDTO = thongSoThucThiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongSoThucThiDTO);
    }

    /**
     * {@code DELETE  /thong-so-thuc-this/:id} : delete the "id" thongSoThucThi.
     *
     * @param id the id of the thongSoThucThiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thong-so-thuc-this/{id}")
    public ResponseEntity<Void> deleteThongSoThucThi(@PathVariable Long id) {
        log.debug("REST request to delete ThongSoThucThi : {}", id);
        thongSoThucThiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /thong-so-thuc-this/by-thong-so-do-sp/:thongSoSPId} : get all the thongSoThucThis.
     *It will auto create ThongSoThucThi if not found

     * @param thongSoSPId ThongSoSP id.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoThucThis in body.
     */
    @GetMapping("/thong-so-thuc-this/by-thong-so-do-sp/{thongSoSPId}/khsx/{keHoachSanXuatId}/{checkPointId}")
    public ResponseEntity<List<ThongSoThucThiDTO>> getAllThongSoThucThisByThongSoDoSP(@PathVariable("thongSoSPId") Long thongSoSPId,
                                                                                      @PathVariable("keHoachSanXuatId") Long keHoachSanXuatId,
                                                                                      @PathVariable("checkPointId") Long checkPointId,
																					  @RequestParam("mau-sp") String mausanpham) {
        try {
            mausanpham = URLDecoder.decode(mausanpham, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            log.warn("Decode error: {}", e);
        }
        log.debug("REST request to get a list of ThongSoThucThis by ThongSoSPId {} of KHSX {} on check point {} --MAU SAN PHAM {}", thongSoSPId, keHoachSanXuatId, checkPointId, mausanpham);
        List<ThongSoThucThiDTO> results = thongSoThucThiService.findAllByThongSoDoSPId(thongSoSPId, keHoachSanXuatId, checkPointId,mausanpham);
        return ResponseEntity.ok(results);
    }
}
