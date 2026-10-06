package vn.nvsoft.web.rest;

import org.springframework.format.annotation.DateTimeFormat;
import vn.nvsoft.service.ProRefinalCCService;
import vn.nvsoft.service.dto.RefinalCCAndChiTietDTO;
import vn.nvsoft.service.dto.RefinalCCCreateInfoDTO;
import vn.nvsoft.service.viewmodel.KeHoachXuatHang;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProRefinalCCDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProRefinalCC}.
 */
@RestController
@RequestMapping("/api")
public class ProRefinalCCResource {

    private final Logger log = LoggerFactory.getLogger(ProRefinalCCResource.class);

    private static final String ENTITY_NAME = "proRefinalCC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProRefinalCCService proRefinalCCService;

    public ProRefinalCCResource(ProRefinalCCService proRefinalCCService) {
        this.proRefinalCCService = proRefinalCCService;
    }

    /**
     * {@code POST  /pro-refinal-ccs} : Create a new proRefinalCC.
     *
     * @param proRefinalCCDTO the proRefinalCCDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalCCDTO, or with status {@code 400 (Bad Request)} if the proRefinalCC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinal-ccs")
    public ResponseEntity<ProRefinalCCDTO> createProRefinalCC(@RequestBody ProRefinalCCDTO proRefinalCCDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinalCC : {}", proRefinalCCDTO);
        if (proRefinalCCDTO.getId() != null) {
            throw new BadRequestAlertException("A new proRefinalCC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProRefinalCCDTO result = proRefinalCCService.save(proRefinalCCDTO);
        return ResponseEntity.created(new URI("/api/pro-refinal-ccs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-refinal-ccs} : Updates an existing proRefinalCC.
     *
     * @param proRefinalCCDTO the proRefinalCCDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proRefinalCCDTO,
     * or with status {@code 400 (Bad Request)} if the proRefinalCCDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proRefinalCCDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-refinal-ccs")
    public ResponseEntity<ProRefinalCCDTO> updateProRefinalCC(@RequestBody ProRefinalCCDTO proRefinalCCDTO) throws URISyntaxException {
        log.debug("REST request to update ProRefinalCC : {}", proRefinalCCDTO);
        if (proRefinalCCDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProRefinalCCDTO result = proRefinalCCService.save(proRefinalCCDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proRefinalCCDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-refinal-ccs} : get all the proRefinalCCS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinalCCS in body.
     */
    @GetMapping("/pro-refinal-ccs")
    public List<ProRefinalCCDTO> getAllProRefinalCCS() {
        log.debug("REST request to get all ProRefinalCCS");
        return proRefinalCCService.findAll();
    }

    /**
     * {@code GET  /pro-refinal-ccs/:id} : get the "id" proRefinalCC.
     *
     * @param id the id of the proRefinalCCDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proRefinalCCDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-refinal-ccs/{id}")
    public ResponseEntity<ProRefinalCCDTO> getProRefinalCC(@PathVariable Long id) {
        log.debug("REST request to get ProRefinalCC : {}", id);
        Optional<ProRefinalCCDTO> proRefinalCCDTO = proRefinalCCService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proRefinalCCDTO);
    }

    /**
     * {@code DELETE  /pro-refinal-ccs/:id} : delete the "id" proRefinalCC.
     *
     * @param id the id of the proRefinalCCDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-refinal-ccs/{id}")
    public ResponseEntity<Void> deleteProRefinalCC(@PathVariable Long id) {
        log.debug("REST request to delete ProRefinalCC : {}", id);
        proRefinalCCService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-refinal-ccs/create/} : get information to create refinal cuoi cung.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-refinal-ccs/create/{khxh-id}")
    public RefinalCCCreateInfoDTO getRefinalCCInfoToCreate(@PathVariable("khxh-id") Long khxhId) {
        return proRefinalCCService.getInfoToCreateRefinalCC(khxhId);
    }

    /**
     * {@code GET  /pro-refinal-ccs/khxh} : get ke hoach xuat hang theo ngay hien tai va chuyen.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-refinal-ccs/khxh/{date}")
    public List<KeHoachXuatHangDTO> getKeHoachXuatHang(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return proRefinalCCService.getKeHoachXuatHang(date);
    }

    /**
     * {@code GET  /pro-refinal-ccs/refinal-cc-chi-tiet/} : get all the RefinalCCAndChiTietDTO cua ke hoach san xuat id
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinalCCS in body.
     */
    @GetMapping("/pro-refinal-ccs/refinal-cc-chi-tiet/{khxh-id}")
    public List<RefinalCCAndChiTietDTO> getAllProRefinalCCAndChiTiet(@PathVariable("khxh-id") Long khxhId) {
        log.debug("REST request to get all getAllProRefinalCCAndChiTiet cua khxh {}", khxhId);
        return proRefinalCCService.getAllProRefinalCCAndChiTiet(khxhId);
    }

    /**
     * {@code POST  /pro-refinal-ccs/create} : Create a new proRefinalCC and chi tiet.
     *
     * @param refinalToCreateDTO the refinalToCreateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalDTO, or with status {@code 400 (Bad Request)} if the proRefinal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-refinal-ccs/create")
    public ResponseEntity<Void> createProRefinalCCAndDetail(@RequestBody RefinalCCAndChiTietDTO refinalToCreateDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinalCC and chi tiet: {}", refinalToCreateDTO);

        proRefinalCCService.createRefinalCC(refinalToCreateDTO);
        return ResponseEntity.ok().build();
    }
}
